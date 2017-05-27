#include "tftp.h"

#include <stdio.h>

#define ELAPSED_USEC_MIN 1000000 // 1s

// two distinct (it helps me to avoid mixed use of WRQ/GET_BLOCK and RRQ/PUT_BLOCK) global file handles 
FILE *file_get_h;
FILE *file_put_h;
sess_t sess_id = 0;
bool_t sess_active = FALSE;
// time of the last operation ( get_block / put_block )
struct timeval time_last_op;

int TRACE_LEVEL = 0;

void SET_TRACE_LEVEL(int i){
	TRACE_LEVEL = i;
}

// prototypes
sess_t new_session(void);
void close_session(void);

res_t * get_file_1_svc(request_t * request, struct svc_req *rq) {
	static res_t *res;
	static bool_t err = FALSE;
	
	//printf("[REQ from %d]", (int)rq->rq_clntcred);
	
	if(err==TRUE)
		free ( (res->res_t_u).err.err ); // free previous err string
	err = FALSE;
	free(res); // free previous res_t
	
	res = (res_t*) malloc ( sizeof(res_t) );
	
	char *fn = request->fn;
	int mode = request->mode;
	if(TRACE_LEVEL>=1){ printf("Asking for %s with mode %d.\n", fn, mode); }
	
	char *errstring;
	int errcode;
	
	if(sess_active == TRUE){ // a file transfer is already running
		struct timeval now;
		gettimeofday(&now, NULL);
		unsigned long elapsed = (now.tv_sec*1000000+now.tv_usec - time_last_op.tv_sec*1000000 -time_last_op.tv_usec);
		if(TRACE_LEVEL>=2) { printf("Elapsed %lu.\n", elapsed); }
		if( elapsed < ELAPSED_USEC_MIN ){
			err = TRUE;
			errstring = "Another file transfer is already running.";
			res->success = FALSE;
			(res->res_t_u).err.sess_id = 0;
			(res->res_t_u).err.code = NOT_DEFINED;
			(res->res_t_u).err.err = (char*) malloc( strlen(errstring)+1 ); 
			strcpy((res->res_t_u).err.err, errstring);
			return res;		
		}
		
		// (a lot of time without any op) ok, let's start another file transfer
		
		close_session();
		
	}	
	
	if(mode!=OCTET){
		err = TRUE;
		errstring = "The only supported mode is OCTET.";
		res->success = FALSE;
		(res->res_t_u).err.sess_id = 0;
		(res->res_t_u).err.code = NOT_DEFINED;
		(res->res_t_u).err.err = (char*) malloc( strlen(errstring)+1 ); 
		strcpy((res->res_t_u).err.err, errstring);
		return res;	
	}
	
	file_get_h = fopen(fn, "r"); // open file for reading
	if(file_get_h==NULL){
		err = TRUE;
		errstring = "Cannot open the file.";
		res->success = FALSE;
		(res->res_t_u).err.sess_id = 0;
		(res->res_t_u).err.code = NOT_DEFINED;
		(res->res_t_u).err.err = (char*) malloc( strlen(errstring)+1 ); 
		strcpy((res->res_t_u).err.err, errstring);
		return res;	
	}
	
	res->success = TRUE;
	(res->res_t_u).sess_id = new_session(); 
	return res;
}

res_t * put_file_1_svc(request_t *request, struct svc_req *rq){
	static res_t *res;
	static bool_t err = FALSE;
	
	if(err==TRUE)
		free ( (res->res_t_u).err.err ); // free previous err string
	err = FALSE;
	free(res); // free previous res_t
	
	res = (res_t*) malloc ( sizeof(res_t) );
	
	char *fn = request->fn;
	int mode = request->mode;
	if(TRACE_LEVEL>=1){ printf("Asking to write %s with mode %d.\n", fn, mode); }
	
	char *errstring;
	int errcode;
	
	if(sess_active == TRUE){ // a file transfer is already running
		struct timeval now;
		gettimeofday(&now, NULL);
		unsigned long elapsed = (now.tv_sec*1000000+now.tv_usec - time_last_op.tv_sec*1000000 -time_last_op.tv_usec);
		if(TRACE_LEVEL>=2) { printf("Elapsed %lu.\n", elapsed); }
		if( elapsed < ELAPSED_USEC_MIN ){
			err = TRUE;
			errstring = "Another file transfer is already running.";
			res->success = FALSE;
			(res->res_t_u).err.sess_id = 0;
			(res->res_t_u).err.code = NOT_DEFINED;
			(res->res_t_u).err.err = (char*) malloc( strlen(errstring)+1 ); 
			strcpy((res->res_t_u).err.err, errstring);
			return res;		
		}
		
		// (a lot of time without any op) ok, let's start another file transfer
		
		close_session();
	}		
	
	if(mode!=OCTET){
		err = TRUE;
		errstring = "The only supported mode is OCTET.";
		res->success = FALSE;
		(res->res_t_u).err.sess_id = 0;
		(res->res_t_u).err.code = NOT_DEFINED;
		(res->res_t_u).err.err = (char*) malloc( strlen(errstring)+1 ); 
		strcpy((res->res_t_u).err.err, errstring);
		return res;	
	}
	
	FILE *fp = fopen(fn, "r");
	if(fp!=NULL){
		err = TRUE;
		errstring = "The file already exist.";
		res->success = FALSE;
		(res->res_t_u).err.sess_id = 0;
		(res->res_t_u).err.code = FILE_ALREADY_EXIST;
		(res->res_t_u).err.err = (char*) malloc( strlen(errstring)+1 ); 
		strcpy((res->res_t_u).err.err, errstring);
		return res;	
	}
	// if file doesn't exist
	
	file_put_h = fopen(fn, "w");
	if(file_put_h == NULL){
		err = TRUE;
		errstring = "Cannot open the file to write (maybe it doesn't exist).";
		res->success = FALSE;
		(res->res_t_u).err.sess_id = 0;
		(res->res_t_u).err.code = NOT_DEFINED;
		(res->res_t_u).err.err = (char*) malloc( strlen(errstring)+1 ); 
		strcpy((res->res_t_u).err.err, errstring);
		return res;	
	}
	
	res->success = TRUE;
	(res->res_t_u).sess_id = new_session(); 
	return res;
}


/* ******************************* */
/* ***** GET A BLOCK OF DATA ***** */
/* ******************************* */
data_t * get_block_1_svc(sess_t *sid, struct svc_req *rq){
	static data_t *res;
	static bool_t err = FALSE;
	static int nread = 0;
	
	if(err==TRUE)
		free ( (res->data).dataret_t_u.err.err ); // free previous err string
	else if(nread>0)
		free ((res->data).dataret_t_u.blk.data_block_t_val);
		
	err = FALSE;
	free(res); // free previous data_t
	
	res = (data_t*) malloc (sizeof(data_t));

	if(TRACE_LEVEL>=3){ printf("Asking to get a block of file.\n"); }	
	
	char *errstring;
	
	if( sess_active == FALSE ){ // no transfer session is running
		err = TRUE;
		errstring = "No transfer session has been initiated.";
		(res->data).success = FALSE;
		(res->data).dataret_t_u.err.sess_id = 0;
		(res->data).dataret_t_u.err.code = NOT_DEFINED;
		(res->data).dataret_t_u.err.err = (char*) malloc( strlen(errstring)+1 ); 
		strcpy((res->data).dataret_t_u.err.err, errstring);
		return res;			
	}
	
	if( *sid != sess_id ){ // wrong session id
		err = TRUE;
		errstring = "Wrong session id provided.";
		(res->data).success = FALSE;
		(res->data).dataret_t_u.err.sess_id = 0;
		(res->data).dataret_t_u.err.code = ACCESS_VIOLATION;
		(res->data).dataret_t_u.err.err = (char*) malloc( strlen(errstring)+1 ); 
		strcpy((res->data).dataret_t_u.err.err, errstring);
		return res;		
	}
	
	// correct session id
	
	if(file_get_h==NULL){
		err = TRUE;
		errstring = "Cannot get a block from file. File is not available.";
		(res->data).success = FALSE;
		(res->data).dataret_t_u.err.sess_id = sess_id;
		(res->data).dataret_t_u.err.code = NOT_DEFINED;
		(res->data).dataret_t_u.err.err = (char*) malloc( strlen(errstring)+1 ); 
		strcpy((res->data).dataret_t_u.err.err, errstring);
		close_session();
		return res;	
	}
	
	char datablock[MAX_DAT_LENGTH];
	nread = fread(datablock , 1, MAX_DAT_LENGTH, file_get_h);
	if(nread<0){
		err = TRUE;
		errstring = "Cannot read from file.";
		(res->data).success = FALSE;
		(res->data).dataret_t_u.err.sess_id = sess_id;
		(res->data).dataret_t_u.err.code = NOT_DEFINED;
		(res->data).dataret_t_u.err.err = (char*) malloc( strlen(errstring)+1 ); 
		strcpy((res->data).dataret_t_u.err.err, errstring);
		close_session();
		return res;	
	}

	(res->data).dataret_t_u.blk.data_block_t_val = (char*)malloc(nread);
	memcpy( (res->data).dataret_t_u.blk.data_block_t_val, datablock, nread);
	(res->data).dataret_t_u.blk.data_block_t_len = nread;

	if(nread<MAX_DAT_LENGTH && file_get_h!=NULL){
		if(TRACE_LEVEL>=1){ printf("Finished.\n"); }
		close_session();
	}
		
	gettimeofday(&time_last_op, NULL); // set time of last op
	
	res->sess_id = sess_id; 
	(res->data).success = TRUE;
	return res;
}


/* ******************************* */
/* ***** PUT A BLOCK OF DATA ***** */
/* ******************************* */
res_t * put_block_1_svc(datapacket_t *data, struct svc_req *rq){
	// data (input arg) must be freed by the server stub
	static res_t *res;
	static bool_t err = FALSE;
	static int nwritten = 0;
	
	if(err==TRUE)
		free ( (res->res_t_u).err.err ); // free previous err string
	err = FALSE;
	free(res); // free previous res_t
	
	res = (res_t*) malloc (sizeof(res_t));

	if(TRACE_LEVEL>=3){ printf("Asking to put a block of file.\n");	}

	int errcode;
	char *errstring;
	
	if( sess_active == FALSE ){ // no transfer session is running
		err = TRUE;
		errstring = "No transfer session has been initiated.";
		res->success = FALSE;
		(res->res_t_u).err.sess_id = 0;
		(res->res_t_u).err.code = NOT_DEFINED;
		(res->res_t_u).err.err = (char*) malloc( strlen(errstring)+1 ); 
		strcpy((res->res_t_u).err.err, errstring);
		return res;			
	}	
	
	if( data->sess_id != sess_id){ // wrong session id
		err = TRUE;
		errstring = "Wrong session id provided.";
		res->success = FALSE;
		(res->res_t_u).err.sess_id = 0;
		(res->res_t_u).err.code = NOT_DEFINED;
		(res->res_t_u).err.err = (char*) malloc( strlen(errstring)+1 ); 
		strcpy((res->res_t_u).err.err, errstring);
		return res;		
	}
	
	// CORRECT SESSION ID
	
	if(file_put_h==NULL){ // file handle is not available
		err = TRUE;
		errstring = "Cannot put a block from file. File is not available.";
		res->success = FALSE;
		(res->res_t_u).err.sess_id = sess_id;
		(res->res_t_u).err.code = NOT_DEFINED;
		(res->res_t_u).err.err = (char*) malloc( strlen(errstring)+1 ); 
		strcpy((res->res_t_u).err.err, errstring);
		close_session();
		return res;	
	}

	nwritten = fwrite( (data->blk).data_block_t_val , 1, (data->blk).data_block_t_len, file_put_h);
	if(nwritten<0){
		err = TRUE;
		errstring = "Cannot write to file.";
		res->success = FALSE;
		(res->res_t_u).err.sess_id = sess_id;
		(res->res_t_u).err.code = NOT_DEFINED;
		(res->res_t_u).err.err = (char*) malloc( strlen(errstring)+1 ); 
		strcpy((res->res_t_u).err.err, errstring);
		close_session();		
		return res;	
	}
	if(nwritten<MAX_DAT_LENGTH){ // => last write
		if(TRACE_LEVEL>=1){ printf("Finished.\n"); }
		close_session();
	} 

	gettimeofday(&time_last_op, NULL); // set time of last op
	
	res->success = TRUE;
	(res->res_t_u).sess_id = sess_id; 
	return res;
}


void * error_1_svc(error_t *err, struct svc_req *rq){
	static char res;
	
	if(sess_active && sess_id==(err->sess_id))
		close_session();
	
	if(TRACE_LEVEL>=1){ printf("ERR(%d) received: %s.\n", err->code, err->err); }
			
	return (void*)&res;
}

sess_t new_session(void){
	sess_id = (sess_t) time( (unsigned) NULL );
	sess_active = TRUE;
	return sess_id;
}

void close_session(void){
	
	sess_id = 0;
	if(file_get_h!=NULL){ // && ftell(file_get_h)!=-1){
		fclose(file_get_h);	
	}
	file_get_h = NULL;
	
	if(file_put_h!=NULL){ //&& ftell(file_get_h)!=-1){
		fclose(file_put_h);
	}	
	file_put_h = NULL;

	sess_active = FALSE;
}
