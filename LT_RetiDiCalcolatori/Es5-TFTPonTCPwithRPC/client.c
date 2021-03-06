#include "tftp.h" // automatically generated by rpcgen

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>

#define MSG_MAX				8192
#define MAX_BUFF			8192
#define MAX_NUM_BLOCK		65535

void helpmsg();

enum ClientResults { REQUEST_FAILED=-200, FILE_ERROR, CANNOT_RECEIVE, CANNOT_SEND, ERR_RECEIVED, GENERIC_ERROR, OK = 1};

int TRACE_LEVEL = 0;

int main(int argc, char **argv){
	
	if(argc!=2 && argc!=3){
		printf("USAGE:		tftpclient	<server_addr> [TRACE-LEVEL=0,1,2]\n\n");
		return 0;
	}
	char *serveraddr = argv[1];
	
	if(argc==3)
		TRACE_LEVEL = atoi(argv[2]);

	CLIENT *handle;
	handle = clnt_create(serveraddr, TFTP_PROG, TFTP_VERS, "tcp");
	if ( handle == NULL ){
		perror("Cannot create the communication infrastructure with the server.\n");
		clnt_pcreateerror(serveraddr); exit(1);
	}
	handle->cl_auth = authnone_create(); //authsys_create_default();

	char cmdLine[MSG_MAX];
	char cmd[11];
	char remotefn[MAX_FILE_NAME_LENGTH];
	char localfn[MAX_FILE_NAME_LENGTH];	
	
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
		
			res = receiveFile( localfn, remotefn, handle);
			
		}
		else if(strcmp(cmd, "put") == 0){ // PUT => WRQ
			
			res = sendFile( localfn, remotefn, handle);
		
		}else{
			printf("Sorry, but I don't understand your command.\n");
			helpmsg();		
			continue;
		}
		
		switch(res){
			case FILE_ERROR:
				printf("\n* File error. Maybe it's impossible to open/read/write it..\n");
				continue;
			case REQUEST_FAILED:
				printf("\n* The request cannot be performed.\n");
				return -1;
			case CANNOT_RECEIVE:
			case CANNOT_SEND:
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
	clnt_destroy(handle);
	
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


/** SEND FILE FROM SERVER */
int sendFile(char *localfn, char *remotefn, CLIENT *handle){
	
	int RET = OK;
	
	sess_t sess_id = 0;
	res_t *result;
	
	request_t request;
	request.fn = remotefn;
	request.mode = OCTET;
	
	result = put_file_1(&request, handle); // RPC corresponding to sending WRQ
	if(result == NULL){
		clnt_perror(handle, "PUT_FILE failed"); return REQUEST_FAILED;
	}
	if (result->success == TRUE) { // SUCCESS
		if(TRACE_LEVEL>=2){ printf("[WRQ Success]"); fflush(stdout); }
		
		sess_id = (result->res_t_u).sess_id; // register the session id for the current file transfe	
		if(TRACE_LEVEL>=2){ printf("[SessionID=%d]", sess_id); }
		
		FILE *fp = fopen(localfn, "r");
		if(fp==NULL){
			RET = FILE_ERROR;
		}
		else{
			
			datapacket_t *dblock;
			dblock = (datapacket_t*) malloc( sizeof(datapacket_t) );
			char buffer[MAX_DAT_LENGTH];
			int nread;
			do{
				memset(buffer, 0, MAX_DAT_LENGTH);
				nread = fread(buffer, 1, MAX_DAT_LENGTH, fp);
				if(nread<0){
					RET = FILE_ERROR;
					error_t erro;
					erro.err = "Couldn't read from file.";
					erro.code = NOT_DEFINED;
					error_1( &erro, handle); // RPC corresponding to sending ERR
					break;
				}

				(dblock->blk).data_block_t_len = nread;
				(dblock->blk).data_block_t_val = (char*)malloc(nread);
				memcpy((dblock->blk).data_block_t_val, buffer, nread);
				dblock->sess_id = sess_id;
				
				res_t *res_put;
				res_put = put_block_1(dblock, handle); // RPC corresponding to sending DAT
				if(res_put == NULL){
					clnt_perror(handle, "PUT_BLOCK failed"); 
					RET = CANNOT_SEND;
					break;
				}
				if(res_put->success == TRUE){
					if(TRACE_LEVEL>=3){ printf("[BLOCK written]\n"); fflush(stdout); }
				}
				else{
					RET = ERR_RECEIVED;
					if(TRACE_LEVEL>=1){ printf("(ERR(%d): %s)\n", (res_put->res_t_u).err.code, (res_put->res_t_u).err.err); fflush(stdout); }
				}
				
				//xdr_free( (xdrproc_t)xdr_data_block_t, (char*)(dblock->blk).data_block_t_val);
				//free( (dblock->blk).data_block_t_val );
				
				xdr_free( (xdrproc_t)xdr_res_t, (char*)res_put);
				
			} while( nread==MAX_DAT_LENGTH && RET==OK );
			
			xdr_free( (xdrproc_t)xdr_datapacket_t, (char*)dblock );
			//free( dblock );
			
			fclose(fp);
		}
	}
	else{ // ERR
		if(TRACE_LEVEL>=1){ printf("(ERR(%d): %s)", (result->res_t_u).err.code, (result->res_t_u).err.err); fflush(stdout); }
		return ERR_RECEIVED;
	}
	
	xdr_free( (xdrproc_t) xdr_res_t, (char*)result);
	
	return RET;
}

/** RECEIVE FILE FROM SERVER */
int receiveFile(char *localfn, char *remotefn, CLIENT *handle){		

	int RET = OK;

	res_t *result;
	request_t request;
	request.fn = remotefn;
	request.mode = OCTET;
	
	sess_t sess_id;
	
	result = get_file_1(&request, handle); // RPC corresponding to sending WRQ
	if(result == NULL){
		clnt_perror(handle, "GET_FILE failed"); return REQUEST_FAILED;
	}
	if (result->success == TRUE) { // SUCCESS
		if(TRACE_LEVEL>=2){ printf("[RRQ Success]"); fflush(stdout); }
		
		sess_id = (result->res_t_u).sess_id; // register the session id for the current file transfer
		if(TRACE_LEVEL>=2){ printf("[SessionID=%d]", sess_id); }
		
		FILE *file = fopen(localfn, "w");
		if(file==NULL){
			RET = FILE_ERROR;
			error_t erro;
			erro.err = "Couldn't open the file in write mode.";
			erro.code = NOT_DEFINED;
			error_1( &erro, handle); // RPC corresponding to sending ERR
		}
		
		data_t *blk;
		u_int datlen = 0;
		do {
			blk = get_block_1(&sess_id, handle); // RPC corresponding to receiving DAT
			if(blk == NULL ){
				clnt_perror(handle, "GET_BLOCK failed");
				RET = CANNOT_RECEIVE;
				break;
			}
			if((blk->data).success == FALSE){ // err received
				if(TRACE_LEVEL>=1){ printf("(ERR(%d): %s)",(blk->data).dataret_t_u.err.code, (blk->data).dataret_t_u.err.err); }
				RET = ERR_RECEIVED;
				break;
			} else{ // recvd a block successfully
				datlen =(blk->data).dataret_t_u.blk.data_block_t_len;
				char *datcontent = (blk->data).dataret_t_u.blk.data_block_t_val;
				if(TRACE_LEVEL>=3){ printf("I read a block of %d bytes.\n", datlen ); fflush(stdout); }
				
				int nw = fwrite( datcontent, 1, datlen, file);
				if (nw!=datlen){
					RET = FILE_ERROR;
					error_t erro;
					erro.err = "Write error.";
					erro.code = NOT_DEFINED;
					error_1( &erro, handle); // RPC corrisponding to sending ERR
					break;
				}
				
			}
			xdr_free( (xdrproc_t)xdr_data_t, (char*)blk);
		}while( datlen==MAX_DAT_LENGTH && RET==OK);
		
		if(RET!=OK && blk!=NULL)
			xdr_free( (xdrproc_t)xdr_data_t, (char*)blk);
			
		if(file!=NULL) fclose(file);
	}
	else{ // ERR received
		if(TRACE_LEVEL>=1){ printf("(ERR(%d): %s)", (result->res_t_u).err.code, (result->res_t_u).err.err); fflush(stdout); }
		RET = ERR_RECEIVED;
	}
	
	xdr_free( (xdrproc_t)xdr_res_t, (char*)result);
	
	return RET;
}

