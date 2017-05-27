#include "tftp.h" // automatically generated by rpcgen

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <sys/socket.h>
#include <errno.h>
#include <rpc/xdr.h>


#define MSG_MAX				8192
#define MAX_BUFF			8192
#define MAX_NUM_BLOCK		65535

#define ACK_TIMEOUT			{ 0, 80000 }	// 80 ms
#define DAT_TIMEOUT			{ 0, 400000 }	// 400 ms

void helpmsg();

// XDR streams and their buffers
XDR xdrsEnc;
XDR xdrsDec;
char bufferEnc[MAX_BUFF];
char bufferDec[MAX_BUFF];

enum ClientResults { CANNOT_LOCATE_SERVER=-200, LOCAL_FILE_NOT_EXIST, REQUEST_FAILED, CANNOT_RECEIVE, CANNOT_SEND, TIMEOUT_EXP, 
	ERR_RECEIVED, NOT_WHAT_I_EXPECTED, GENERIC_ERROR, OK = 1};

int main(int argc, char **argv){
	
	if(argc!=3 && argc!=4){
		printf("USAGE:		tftpclient	<server_addr> <server_port> [DEBUG_ON=0||1]\n\n");
		return 0;
	}
	char *serveraddr = argv[1];
	int serverport = atoi(argv[2]);
	
	int DEBUG_ON = 0;
	if(argc==4)
		DEBUG_ON = atoi(argv[3]);


	// XDR streams init
	xdrmem_create( &xdrsEnc, bufferEnc, MAX_BUFF, XDR_ENCODE);
	xdrmem_create( &xdrsDec, bufferDec, MAX_BUFF, XDR_DECODE);
	
	
	// create client socket
	int sfd;
	if( (sfd = socket(AF_INET, SOCK_DGRAM, IPPROTO_UDP)) < 0){
		perror("Cannot create socket: ");
		return -1;
	}
	
	// bind client socket to ephimeral port
	struct sockaddr_in sin;
	sin.sin_family = AF_INET;
	sin.sin_port = 0;
	sin.sin_addr.s_addr = INADDR_ANY;
	if ( bind(sfd, (struct sockaddr *) &sin, sizeof(struct sockaddr_in)) < 0){
		perror("Cannot bind socket: ");
		return -1;
	}
	
	// (association) server address
	struct sockaddr_in servaddr;
	servaddr.sin_family = AF_INET;
	servaddr.sin_port = htons(serverport);
	servaddr.sin_addr.s_addr = inet_addr(serveraddr);
	
	struct sockaddr_in *serv;
	serv = &servaddr;
	
	char cmdLine[MSG_MAX];
	char cmd[11];
	char remotefn[MAX_FILE_NAME_LENGTH];
	char localfn[MAX_FILE_NAME_LENGTH];
	
	// TFTP message
	TftpMsg msg;
	
	int interaction = 0;
	
	for (; ;){
		printf("\n> ");
		gets(cmdLine);
		
		if( strcmp(cmdLine, "quit")==0 ){
			break;
		} else if( strcmp(cmdLine, "h")==0 ){
			helpmsg();
			continue;
		}
		
		memset(cmd, 0, 5);
		memset(localfn, 0, MAX_FILE_NAME_LENGTH);
		memset(remotefn, 0, MAX_FILE_NAME_LENGTH);
		if ( sscanf(cmdLine, "%10s %400s %400[^\n]", cmd, localfn, remotefn) != 3){
			printf("Sorry, but I don't understand your command.\n");
			helpmsg();
			continue;
		}
		
		int res;
		if( strcmp(cmd, "get")== 0){ // GET => RRQ
		
			res = receiveFile( sfd, localfn, remotefn, serv, DEBUG_ON);
		}
		else if(strcmp(cmd, "put") == 0){ // PUT => WRQ
			
			res = sendFile( sfd, localfn, remotefn, serv, DEBUG_ON);
		
		}else{
			printf("Sorry, but I don't understand your command.\n");
			helpmsg();		
			continue;
		}
		
		struct sockaddr_in saddr;
		switch(res){
			case TIMEOUT_EXP:
				printf("\n* Timeout expiration.\n");
				continue;
			case LOCAL_FILE_NOT_EXIST:
				printf("\n* The local file doesn't exist.\n");
				continue;
			case CANNOT_LOCATE_SERVER:
				printf("\n* Cannot locate the server.");
			case REQUEST_FAILED:
				printf("\n* The request cannot be performed.\n");
				return -1;
			case CANNOT_RECEIVE:
			case CANNOT_SEND:
			case NOT_WHAT_I_EXPECTED:
				printf("\n* Communication error.\n");
				return -1;
			case GENERIC_ERROR:
				printf("\n* Client error.\n");
				return -1;
			case ERR_RECEIVED:
				printf("\n* ERR received.\n");
				continue;
			case OK:
				printf("\n* The request has been satisfied.\n");
				continue;
		}
		
	}
	
	/* cleanup */
	close(sfd);
	
	return 0;
}

void helpmsg(){
			printf("----------------------------------------------\n");
			printf("  HELP. Commands:\n");
			printf("  get <localFileName> <remoteFileName>\n");
			printf("  put <localFileName> <remoteFileName>\n");
			printf("  quit\n");
			printf("----------------------------------------------\n");
}


void printServerInfo(struct sockaddr_in *serv){
	printf("[Server = %s : %d]", (char*)inet_ntoa(serv->sin_addr), ntohs(serv->sin_port) );
	fflush(stdout);
}

/** SEND FILE FROM SERVER */
int sendFile(int sfd, char *localfn, char *remotefn, struct sockaddr_in *serv, int DEBUG_ON){
	if(DEBUG_ON){ printServerInfo(serv); }

	TftpMsg msg;
			
	// opening the local file to send
	FILE *fp;
	if( (fp = fopen(localfn, "r")) == NULL){
		if(DEBUG_ON) { printf("[Local file doesn't exist]"); fflush(stdout); }
		return LOCAL_FILE_NOT_EXIST;		
	}
			
	if( sendRequest( sfd, WRQ, remotefn, serv) < 0 )
		return REQUEST_FAILED;
		
	// timeout and attempts management
	int nattempts = 3;			
					
	int lastbread=0, verylastbread=0;
	int nbread;
	char block[MAX_DAT_LENGTH];
	int blocktosend = 0;
	int start = 0;
	int RES = OK;
	for(; nattempts>0 ;) {
		
		struct timeval ackTimeout = ACK_TIMEOUT;
		
		memset(&msg, 0, sizeof(TftpMsg));
		int waitres = waitForMsg(sfd, &msg, &ackTimeout, blocktosend==0?TRUE:FALSE);		
		if( waitres > 0 ){
						
			// (c/s udp paradigm) getting communication server addr
			if(start==0){ 
				int addrlen = sizeof(struct sockaddr_in);
				if(getpeername(sfd, (struct sockaddr *) serv, &addrlen) < 0)
					return CANNOT_LOCATE_SERVER;
				start = 1;
			}
			
			// if recv ACK(N), wait for the next block of data
			if(msg.opType==ACK){
				if(msg.TftpMsg_u.ackMsg.blkn==blocktosend){
					if(DEBUG_ON) { printf("[ACK(%d)]", blocktosend); fflush(stdout); }
					nattempts = 3;
					if(blocktosend < MAX_NUM_BLOCK) // wraparound handling
						blocktosend++;
					else
						blocktosend=0;
				}
				else{
					continue; // I can receive another ack for the delays	
				}
			} else if(msg.opType==ERR){
				printf("* ERR(code=%d) from server: %s\n", msg.TftpMsg_u.errMsg.errCode, msg.TftpMsg_u.errMsg.errStr );
				RES = ERR_RECEIVED;
				break;
			}else{
				sendERR( sfd, ILL_OP_TFTP, "Not what I expected");
				RES = NOT_WHAT_I_EXPECTED;
				break;
			}
			
		} else if( waitres == 0 ){ // timeout expired
			if(DEBUG_ON) { 
				struct timeval tv;
				gettimeofday(&tv, NULL);
				printf("{s=%lu..us=%lu}", tv.tv_sec, tv.tv_usec); 
			}
			nattempts--;
			continue;
		} else{
			sendERR( sfd, ILL_OP_TFTP, "Client error");
			RES = CANNOT_RECEIVE;
			break;
		}
	
		// read 512 (MAX_DAT_LENGTH) bytes
		if(nattempts == 3){
			verylastbread = nbread;
			nbread = fread(block, 1, MAX_DAT_LENGTH, fp);
			if(nbread==0){
								 
				if(lastbread == MAX_DAT_LENGTH || verylastbread == MAX_DAT_LENGTH){
					lastbread = MAX_DAT_LENGTH;
					if(DEBUG_ON) { printf("[DAT0DATA]"); fflush(stdout); }
					sendDAT( sfd, blocktosend, NULL, 0 ); // send DAT with zero data
				}
								
				if(DEBUG_ON) { printf("[FINISH]"); fflush(stdout); }
				break;
			} else if(nbread<0){
				sendERR( sfd, ILL_OP_TFTP, "Client error");
				RES = GENERIC_ERROR;
				break;
			}
		}
		
		// send DAT
		if( sendDAT( sfd, blocktosend, block, nbread) > 0){
			if(DEBUG_ON) { printf("[->DAT(%d)]", blocktosend); fflush(stdout); }
		}else {
			if(DEBUG_ON) { printf("[FILE PUT ERROR]"); fflush(stdout); }
			break;
		}
		
	} 
	
	fclose(fp);	
	xdr_free((xdrproc_t)xdr_TftpMsg, (char*)&msg);	
					
	if(nattempts==0) RES = TIMEOUT_EXP;

	return RES;
}

/** RECEIVE FILE FROM SERVER */
int receiveFile(int sfd, char *localfn, char *remotefn, struct sockaddr_in *serv, int DEBUG_ON){		
	if(DEBUG_ON){ printServerInfo(serv); }
			
	TftpMsg msg;
			
	// send RRQ
	if( sendRequest( sfd, RRQ, remotefn, serv ) < 0)
		return REQUEST_FAILED;
			
	// open file in write-mode
	FILE *fp;
	if ( (fp = fopen(localfn, "w")) == NULL ){
		sendERR(sfd, NOT_DEFINED, "Client error. Cannot open file to write.");
		return GENERIC_ERROR;
	}
	
	u_int blocktorecv = 1;
	int nattempts = 3;
	int RES = OK;
	for (; nattempts > 0 ;){
		struct timeval datTimeout = DAT_TIMEOUT;
		memset(&msg, 0, sizeof(TftpMsg));
		// wait for DAT
		int waitRes = waitForMsg(sfd, &msg, &datTimeout, blocktorecv == 1 ? TRUE : FALSE);
		if( waitRes >0 ){
					
			// (c/s udp paradigm) getting communication server addr
			if( blocktorecv == 1){
				int addrlen = sizeof(struct sockaddr_in);
				if( getpeername(sfd, (struct sockaddr *) serv, &addrlen) < 0 )
					return CANNOT_LOCATE_SERVER;		
			}
			
			// recvd ERR	
			if( msg.opType == ERR){
				printf("\nERR(%d)=> %s\n", msg.TftpMsg_u.errMsg.errCode, msg.TftpMsg_u.errMsg.errStr);
				RES = ERR_RECEIVED;
				break;
			}
					
			// recvd DAT(N)
			if ( msg.TftpMsg_u.datMsg.blkn == blocktorecv){
				if(DEBUG_ON) { printf("[DAT(%d)]", blocktorecv); fflush(stdout); }
				int tw = msg.TftpMsg_u.datMsg.dat.dat_len;
				char *buff = msg.TftpMsg_u.datMsg.dat.dat_val;
				if( fwrite(buff, 1, tw, fp) < 0){
					sendERR(sfd, ILL_OP_TFTP, "Client error. Cannot write file.");
					RES = GENERIC_ERROR;
					break;
				}
				// send ACK(N)
				if( sendACK(sfd, blocktorecv) > 0){
					if(DEBUG_ON) { printf("[->ACK(%d)]", blocktorecv); fflush(stdout); }
					if ( tw < MAX_DAT_LENGTH ){
						if(DEBUG_ON) { printf("[FINISH]"); fflush(stdout); }
						break;
					}
				}
				else{
					RES = CANNOT_SEND;
					break;
				}
				nattempts = 3;
				if(blocktorecv < MAX_NUM_BLOCK)
					blocktorecv++;
				else
					blocktorecv=0;
			}
			else if( msg.TftpMsg_u.datMsg.blkn == (blocktorecv-1)){ // recvd ACK(N-1) => send ack again
				if( sendACK(sfd, blocktorecv-1) > 0)
					nattempts--;
			}
		} else if( waitRes == 0){ // timeout expired
			nattempts--;
			continue;
		} else{
			sendERR(sfd, NOT_DEFINED, "Client error. Cannot receive");
			RES = CANNOT_RECEIVE;
			break;
		}
	}
	
	fclose(fp);
	xdr_free((xdrproc_t)xdr_TftpMsg, (char*)&msg);	
	
	if(nattempts==0) RES = TIMEOUT_EXP;

	return RES;
}

/* ************************************************** */
/* ************** TFTP PROTOCOL ENTITY ************** */
/* ************************************************** */

/** Waits for a TFTP message */
int waitForMsg( int sfd, TftpMsg *msg, struct timeval *tout, bool_t connect) {
	// timeout handling
	fd_set readySet;
	FD_ZERO( &readySet );
	FD_SET(sfd, &readySet);	
	int sel = select( sfd+1, &readySet, NULL, NULL, tout);
	if( sel < 0 ){
		if ( errno == EINTR) { return -3; }
		else{
			perror("Select error: ");
			exit(-1);
		}
	} else if (sel == 0 ){ // timeout terminato
		return 0;
	}
	else{ // ok
		return readMsg(sfd, msg, connect);
	}
	// timeout handling end
}

/** Reads a PDU and decode it */
int readMsg(int fd, TftpMsg *msg, bool_t conn) {
		int nr;
		if( conn == TRUE ){ // connect to communication server (to handle udp interaction paradigm)
			struct sockaddr_in serv;
			int slen = sizeof(serv);
			nr = recvfrom(fd, bufferDec, MSG_MAX, 0, (struct sockaddr *)&serv, &slen);
			connect(fd, (struct sockaddr *) &serv, slen);
		} else{
			nr = read(fd, bufferDec, MSG_MAX);
		}
		
		if( nr <= 0 ){ return -1; }
		
		return decodeMsg( msg );
}

/** Encodes a PDU and writes it */
int writeMsg( int fd, TftpMsg *msg) {
	
	if( encodeMsg(msg) > 0 ){
		return write(fd, bufferEnc, xdr_getpos(&xdrsEnc));
	} else{
		return -1;
	}
}

int sendRequest( int sfd, OpType code, char *fn, struct sockaddr_in *serv){
	if(code!=RRQ && code!=WRQ) return FALSE;
	
	// BUILD RRQ/WRK message
	TftpMsg msg;
	memset(&msg, 0, sizeof(msg));
	msg.opType = code;
	msg.TftpMsg_u.rrqMsg.fileName = fn;
	msg.TftpMsg_u.rrqMsg.mode = OCTET;
	
	if ( encodeMsg(&msg ) > 0 ){
		int nsent;
		if(serv!=NULL)
			nsent = sendto(sfd, bufferEnc, xdr_getpos(&xdrsEnc), 0, (struct sockaddr *)serv, sizeof(struct sockaddr_in));
		else
			nsent = read(sfd, bufferEnc, xdr_getpos(&xdrsEnc));
		return nsent;
	}
	else{
		perror("{ Error during encoding of the XDR stream (req) }");
		return -1;
	}
	
}

int sendACK( int sfd, u_int blkn ){
	TftpMsg tmsg;
	memset(&tmsg, 0, sizeof(TftpMsg));
	
	tmsg.opType = ACK;
	tmsg.TftpMsg_u.ackMsg.blkn = blkn;

	return writeMsg(sfd, &tmsg);
}

int sendDAT(int sfd, u_int blkn, char *data, int datalen){
	TftpMsg tmsg;
	memset(&tmsg, 0, sizeof(TftpMsg));
	
	tmsg.opType = DAT;
	tmsg.TftpMsg_u.datMsg.blkn = blkn;
	tmsg.TftpMsg_u.datMsg.dat.dat_len = datalen;
	tmsg.TftpMsg_u.datMsg.dat.dat_val = data;

	return writeMsg(sfd, &tmsg);
}

int sendERR(int sfd, int errCode, char *errStr){
	TftpMsg tmsg;
	memset(&tmsg, 0, sizeof(TftpMsg));
	
	tmsg.opType = ERR;
	tmsg.TftpMsg_u.errMsg.errCode = errCode;
	tmsg.TftpMsg_u.errMsg.errStr = errStr;

	return writeMsg(sfd, &tmsg);
}

/* ************************************** */
/* Codifica e decodifica di messaggi TFTP */
/* ************************************** */
int encodeMsg( TftpMsg *msg){
	xdr_setpos(&xdrsEnc, 0);
	if(xdr_TftpMsg( &xdrsEnc, msg ) == FALSE ) return -1;
	return xdr_getpos(&xdrsEnc);
}

int decodeMsg( TftpMsg *msg){
	if( xdr_setpos(&xdrsDec, 0) == FALSE ) return FALSE;
	if( xdr_TftpMsg(&xdrsDec, msg) == TRUE )
		return xdr_getpos(&xdrsDec);
	else
		return -1;
}