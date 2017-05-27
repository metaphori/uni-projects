#include "tftp.h"

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <sys/socket.h>
#include <errno.h>

#define MSG_MAX				8192
#define MAX_BUFF			8192
#define MAX_NUM_BLOCK		65535

#define ACK_TIMEOUT			{ 0, 80000 }	// 80 ms
#define DAT_TIMEOUT			{ 0, 400000 }	// 400 ms


#define CLIENT_TIMEOUT		{ 60, 0 }		// 60 s

enum ServerResults { CANNOT_RECEIVE=-200, CANNOT_SEND, FILE_ERROR, ERR_RECEIVED, NOT_WHAT_I_EXPECTED, GENERIC_ERROR, TIMEOUT_EXP, 
	OK = 1, NO_MORE};

// GLOBALS
XDR xdrsEnc;
XDR xdrsDec;
char bufferEnc[MAX_BUFF];
char bufferDec[MAX_BUFF];

int handleSession(int rfd, int wfd, int DEBUG_ON);

int main(int argc, char **argv){

	// XDR stream initialization
	xdrmem_create( &xdrsEnc, bufferEnc, MAX_BUFF, XDR_ENCODE);
	xdrmem_create( &xdrsDec, bufferDec, MAX_BUFF, XDR_DECODE);
	memset(bufferEnc, 0, MAX_BUFF);
	memset(bufferDec, 0, MAX_BUFF);

	int DEBUG_ON = 0;

	if(argc==3 || argc==4 && strcmp(argv[1], "-standalone")==0){
		/***************************************************************/
		/*********************** STANDALONE MODE ***********************/
		/***************************************************************/
		
		int port = atoi(argv[2]);
		int sfd;
		
		if(argc==4)
			DEBUG_ON = atoi(argv[3]);
		
		if(DEBUG_ON) printf("STANDALONE MODE. [port %d] \n", port); fflush(stdout);
		
		// socket server di associazione
		if( (sfd = socket(AF_INET, SOCK_DGRAM, IPPROTO_UDP)) < 0){
			perror("Cannot create socket (assoc): ");
			return -1;
		}

		// bind socket server di assoc a porta well-known passata come arg
		struct sockaddr_in sin;
		sin.sin_family = AF_INET;
		sin.sin_port = htons( port );
		sin.sin_addr.s_addr = INADDR_ANY;
		if ( bind(sfd, (struct sockaddr *) &sin, sizeof(struct sockaddr_in)) < 0){
			perror("Cannot bind socket (assoc): ");
			return -1;
		}
		
		if(DEBUG_ON) printf("WAITING FOR DGRAMS.\n"); fflush(stdout);

		// receiving dgrams and starting session (SEQUENTIAL)
		struct sockaddr_in client;
		int clilen = sizeof(struct sockaddr_in);
		char pdu[MSG_MAX];
		
		int nbytes;
		if ( (nbytes = recvfrom( sfd, pdu, MSG_MAX, 0, (struct sockaddr *) &client, &clilen)) < 0){
			perror("Cannot receive dgrams: ");
			return -1;
		}
		
		if(DEBUG_ON) { printf("pdu received ==> %s", pdu); fflush(stdout); }
		
		// socket server di comunicazione
		int serv;
		if ( (serv = socket(AF_INET, SOCK_DGRAM, IPPROTO_UDP)) < 0){
			perror("Cannot create socket (comm): ");
			return -1;
		}

		// bind socket server di comunicaz a porta effimera
		struct sockaddr_in sineph;
		sineph.sin_family = AF_INET;
		sineph.sin_port = 0;
		sineph.sin_addr.s_addr = INADDR_ANY;
		if ( bind(serv, (struct sockaddr *) &sineph, sizeof(struct sockaddr_in)) < 0){
			perror("Cannot bind socket (comm): ");
			return -1;
		}
		
		struct sockaddr_in newaddr;
		int addrsize = sizeof(struct sockaddr_in);
		if ( getsockname(serv, (struct sockaddr *) &newaddr, &addrsize ) < 0 ){
			perror("Cannot obtain socket info: ");
			return -1;
		}
					
		if ( sendto( serv, pdu, nbytes, 0, (struct sockaddr *) &newaddr, sizeof(struct sockaddr_in)) < 0){
			perror("Cannot send dgram: ");
			return -1;
		}
					
		if ( connect(serv, (struct sockaddr *) &client, sizeof(struct sockaddr_in)) < 0){
			perror("{ Impossibile connettere il socket udp alla porta effimera }");
		}
				
		handleSession(serv, serv, DEBUG_ON);

		/* CLEANUP */
		close(sfd);
	
	}
	else{
		/***************************************************************/
		/************************* FILTRO UNIX *************************/
		/***************************************************************/

		if(argc==3)
			DEBUG_ON = atoi(argv[3]);
		
		stdout = NULL;
		
		handleSession(0, 1, DEBUG_ON);
	}
	
	return 0;
}

int handleSession(int rfd, int wfd, int DEBUG_ON){

	if(DEBUG_ON) { printf("[HANDLING SESSION]"); fflush(stdout); }

	int filetransfers = 0;
	
	TftpMsg tftpMsg;
	fd_set readySet;
	
	for( ;; ) {
		
		FD_ZERO( &readySet );
		FD_SET(rfd, &readySet);	
		struct timeval timeout = CLIENT_TIMEOUT;
		// FIRST TIMEOUT -- WAITING for REQUESTS
		int sel = select( rfd+1, &readySet, NULL, NULL, &timeout);
		if( sel < 0 ){
			if ( errno == EINTR) { continue; }
			else{
				sendERR( wfd, NOT_DEFINED, "Server error on I/O multiplexing" );
				perror("Select error: ");
				return GENERIC_ERROR;
			}
		} else if (sel == 0 ){ // timeout terminato ==> fine sessione di file transfer
			if(filetransfers==0) continue;
			if(DEBUG_ON) { printf("\n* Timeout expired. No more clients.\n"); fflush(stdout); }
			xdr_free( (xdrproc_t) xdr_TftpMsg, (char*) &tftpMsg);
			return NO_MORE;
		} else { // ci sono fd pronti
			filetransfers++;
			
			memset(&tftpMsg, 0, sizeof(TftpMsg));
			// handling the request
			int waitRes = waitForMsg(rfd, &tftpMsg, NULL); 
			if ( waitRes > 0 ){
				int res;
				
				if ( tftpMsg.opType == RRQ ){
					if(DEBUG_ON) { printf("Asked to read filename: %s\n", tftpMsg.TftpMsg_u.rrqMsg.fileName); fflush(stdout); }
					
					res = sendFile( rfd, wfd, tftpMsg.TftpMsg_u.rrqMsg.fileName, DEBUG_ON); // SEND FILE
										
				} else if( tftpMsg.opType == WRQ ){
					if(DEBUG_ON) { printf("Asked to write filename: %s\n", tftpMsg.TftpMsg_u.wrqMsg.fileName); fflush(stdout); }
					
					res = receiveFile( rfd, wfd, tftpMsg.TftpMsg_u.wrqMsg.fileName, DEBUG_ON); // RECEIVE FILE
										
				}
				// communicating the result
				switch(res){
					case TIMEOUT_EXP:
						printf("\n* Timeout expiration.\n");
						continue;
					case CANNOT_RECEIVE:
					case CANNOT_SEND:
						if(DEBUG_ON) { printf("\n* Communication error.\n"); fflush(stdout); }
						continue;
					case FILE_ERROR:
						if(DEBUG_ON) { printf("\n* File error.\n"); fflush(stdout); }
						continue;
					case ERR_RECEIVED:
						if(DEBUG_ON) { printf("\n* Err received.\n"); fflush(stdout); }
						continue;
					case NOT_WHAT_I_EXPECTED:
						if(DEBUG_ON) { printf("\n* I received not what I expected.\n"); fflush(stdout); }
						continue;
					case GENERIC_ERROR:
						if(DEBUG_ON) { printf("\n* Server error.\n"); fflush(stdout); }
						continue;
					case OK:
						if(DEBUG_ON) { printf("\n* OK.\n"); fflush(stdout); }
						continue;	
				}
				
			} else if(waitRes < 0){
				sendERR( wfd, NOT_DEFINED, "Server error. Cannot receive any command." );
				if(DEBUG_ON) { printf("* Cannot receive any command."); fflush(stdout); }
				exit(-1);
			}
			
		}
	
	} // end for
	
}

/** RECEIVE A FILE FROM CLIENT */
int receiveFile(int rfd, int wfd, char *localfn, int DEBUG_ON){

	TftpMsg tftpMsg;
	
	int RES = OK;

	if ( sendACK( wfd, 0) > 0 ){
		
		FILE *fr;
		// check if file already exists
		fr = fopen( localfn, "r");
		if( fr != NULL ){
			sendERR( wfd, FILE_ALREADY_EXIST, "File already exists.");
			fclose(fr);
			return FILE_ERROR;
		}	
		
		// opening file in write-mode
		FILE *fp;
		fp = fopen( localfn, "w");
		if(fp==NULL){
			sendERR( wfd, ILL_OP_TFTP, "Server error. Cannot write remote file.");
			return FILE_ERROR;
		}
		
		u_int blocktorecv = 1;
		int nattempts = 3;
		for(; nattempts>0 ;) {
		
			// waiting for DAT
			struct timeval datTimeout = DAT_TIMEOUT;
			memset(&tftpMsg, 0, sizeof(TftpMsg));
			int waitRes =  waitForMsg(rfd, &tftpMsg, &datTimeout);		
			if(waitRes > 0){
							
				if( tftpMsg.opType == ERR) {
					if(DEBUG_ON) { printf("[ERR(code=%d) recvd: %s]", tftpMsg.TftpMsg_u.errMsg.errCode, 
						tftpMsg.TftpMsg_u.errMsg.errStr); fflush(stdout); }
					RES = ERR_RECEIVED;
					break;
				}
					
				// DAT (N) received		
				if( tftpMsg.TftpMsg_u.datMsg.blkn==blocktorecv ){
					
					// sending ACK (N)
					if(sendACK(wfd, tftpMsg.TftpMsg_u.datMsg.blkn) > 0){
						if(DEBUG_ON) { printf("[->ACK(%d)]", tftpMsg.TftpMsg_u.datMsg.blkn); fflush(stdout); }
					}else{
						if(DEBUG_ON) { printf("[Cannot send ACK(%d)]", tftpMsg.TftpMsg_u.datMsg.blkn); fflush(stdout); }
						RES = CANNOT_SEND;
						break;
					}
					
					// writing the received data block to file
					u_int towrite = (u_int)tftpMsg.TftpMsg_u.datMsg.dat.dat_len;
					if(DEBUG_ON) { printf("[DAT(%d)=%d]", blocktorecv, towrite); fflush(stdout); }
					int written = fwrite( tftpMsg.TftpMsg_u.datMsg.dat.dat_val, 1, towrite, fp );
					
					if(written < MAX_DAT_LENGTH){ // if block data < MAX_DA_LENGTH, we've finished
						if(DEBUG_ON) { printf("-- FINISH TO WRITE (%d) --", towrite); fflush(stdout); }
						RES = OK;
						break;
					}
					else if(written==MAX_DAT_LENGTH){ // more data
						nattempts = 3;
						if(blocktorecv < MAX_NUM_BLOCK)
							blocktorecv++;
						else
							blocktorecv=0;
					} else {
						sendERR(wfd, NOT_DEFINED, "Server error. Write error.");
						RES = FILE_ERROR;
						break;
					}
					
				}
				else{
					if(DEBUG_ON) { printf("[Not what I expected (blkn=%d)]", tftpMsg.TftpMsg_u.datMsg.blkn); fflush(stdout); }
					sendERR(wfd, NOT_DEFINED, "Communication error. Not what I expected (DAT).");
					RES = NOT_WHAT_I_EXPECTED;
					break;	
				}
			} else if(waitRes == 0){ // timeout expired (waiting for DAT)
				if(DEBUG_ON) { 
					struct timeval tv;
					gettimeofday(&tv, NULL);
					printf("{s=%lu..us=%lu}", tv.tv_sec, tv.tv_usec); 
				}
				nattempts--;
				continue;
			} else{
				sendERR(wfd, NOT_DEFINED, "Server error. Cannot receive.");
				RES = CANNOT_RECEIVE;
				break;
			}
		}
						
		fclose( fp );
		xdr_free( (xdrproc_t)xdr_TftpMsg, (char*)&tftpMsg);
		
		if(nattempts==0) RES = TIMEOUT_EXP;
		
		return RES;
		
	} else{
		if(DEBUG_ON) { printf("[Cannot send ack 0]"); fflush(stdout); }
		return CANNOT_SEND;
	}

}

/** SENDS A FILE TO CLIENT */
int sendFile(int rfd, int wfd, char *localfn, int DEBUG_ON){

	TftpMsg tftpMsg;
								
	// opening file to send
	FILE *fp;
	fp = fopen(localfn, "r");
	if(fp==NULL){
		sendERR( wfd, FILE_NOT_FOUND, "Server error. File not found.");
		return FILE_ERROR;
	}
	
	int nattempts = 3;
	int RES = OK;
	int nbread; int lastbread = 0, verylastbread = 0;
	char block[MAX_DAT_LENGTH];
	int blocktosend = 1;
	for(; nattempts > 0 ;) {
	
		// READING A BLOCK FROM FILE and HANDLING SENDING OF DAT WITH ZERO DATA
		if(nattempts == 3){ // read 512 (MAX_DAT_LENGTH) bytes
			verylastbread = nbread;
			nbread = fread(block, 1, MAX_DAT_LENGTH, fp); 
			if(nbread==0){ 
			
				if(lastbread == MAX_DAT_LENGTH || verylastbread == MAX_DAT_LENGTH){
					lastbread = MAX_DAT_LENGTH;
					
					// sending DAT with ZERO DATA
					if( sendDAT( wfd, blocktosend, "", 0) > 0){
						if(DEBUG_ON) { printf("[DAT0data]"); fflush(stdout); }
					}else {
						if(DEBUG_ON) { printf("[FILE PUT ERROR]"); fflush(stdout); }
						RES = CANNOT_SEND;
						break;
					}
					
					// waiting for ACK to DAT-with-ZERO_DATA
					struct timeval ackTimeout = ACK_TIMEOUT;
					memset(&tftpMsg, 0, sizeof(TftpMsg));
					int res = waitForMsg(wfd, &tftpMsg, &ackTimeout);
					if(res <= 0 ){
						nbread = MAX_DAT_LENGTH;
						continue;
					} else{
						if(DEBUG_ON) { printf("[FINISH]"); fflush(stdout); }
						break;	
					}
				}
				else{
					RES = OK;
					break;
				}	
			} else if(nbread<0){ // read error
				sendERR( wfd, NOT_DEFINED, "Server error. Cannot read the file");
				RES = FILE_ERROR;
				break;
			}
		}
		
		// SENDING DAT
		if( sendDAT( wfd, blocktosend, block, nbread) > 0){
			if(DEBUG_ON) { printf("[->DAT(%d)]", blocktosend); fflush(stdout); }
		}else {
			if(DEBUG_ON) { printf("[CANNOT SEND DAT(%d)]", blocktosend); fflush(stdout); }
			RES = CANNOT_SEND;
			break;
		}
		
		// WAITING FOR ACK
		struct timeval ackTimeout = ACK_TIMEOUT;
		memset(&tftpMsg, 0, sizeof(TftpMsg));
		int waitRes = waitForMsg(rfd, &tftpMsg, &ackTimeout);
		if( waitRes > 0 ){
			if(tftpMsg.opType == ACK){
				if( tftpMsg.TftpMsg_u.ackMsg.blkn==blocktosend ) {
					if(DEBUG_ON) { printf("[ACK(%d)]", blocktosend); fflush(stdout); }
					nattempts = 3;
					lastbread = nbread;
					// a block has been correctly sent, now we'll send another one 
					if(blocktosend < MAX_NUM_BLOCK)
						blocktosend++;
					else
						blocktosend=0;
				}
				else{
					continue; // I can receive another ack for the delays	
				}
			} else if(tftpMsg.opType == ERR){
				if(DEBUG_ON) { printf("[ERR(%d): %s]", tftpMsg.TftpMsg_u.errMsg.errCode, tftpMsg.TftpMsg_u.errMsg.errStr); fflush(stdout); }
				RES = ERR_RECEIVED;
				break;
			}
			else{
				fprintf(stderr, "I received a OPCODE=%d", tftpMsg.opType);
				sendERR( wfd, NOT_DEFINED, "Server error. Not what I expected.");
				RES = NOT_WHAT_I_EXPECTED;
				break;			
			}
		} 
		else if( waitRes == 0){ // timeout expired
			nattempts--;
			continue;
		} else{
			if(DEBUG_ON) { printf("[Cannot recv]"); fflush(stdout); }
			sendERR( wfd, NOT_DEFINED, "Server error. Cannot receive any message.");
			RES = CANNOT_RECEIVE;
			break;
		}
	} 
	
	fclose(fp);
	xdr_free( (xdrproc_t)xdr_TftpMsg, (char *) &tftpMsg);
	
	if( nattempts==0 )	RES = TIMEOUT_EXP;

	return RES;
}


/* ************************************************** */
/* ************** TFTP PROTOCOL ENTITY ************** */
/* ************************************************** */

int waitForMsg( int sfd, TftpMsg *msg, struct timeval *tout) {
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
		return readMsg(sfd, msg);
	}
	// timeout handling end
}

int readMsg(int fd, TftpMsg *msg) {
		int nr = read(fd, bufferDec, MSG_MAX);
		if( nr <= 0 ){ return -1; }
		return decodeMsg( msg );
}

int writeMsg( int fd, TftpMsg *msg ) {
	
	if( encodeMsg(msg) > 0 ){
		return write(fd, bufferEnc, xdr_getpos(&xdrsEnc));
	} else{
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

int decodeMsg( TftpMsg *msg ){
	if( xdr_setpos(&xdrsDec, 0) == FALSE ) return FALSE;
	if( xdr_TftpMsg(&xdrsDec, msg) == TRUE )
		return xdr_getpos(&xdrsDec);
	else
		return -1;
}


