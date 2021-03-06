/*
 * Please do not edit this file.
 * It was generated using rpcgen.
 */

#ifndef _TFTP_H_RPCGEN
#define _TFTP_H_RPCGEN

#include <rpc/rpc.h>


#ifdef __cplusplus
extern "C" {
#endif

#define MAX_FILE_NAME_LENGTH 500
#define MAX_DAT_LENGTH 512
#define MAX_STRING_ERR_LEN 100

enum ErrCode {
	NOT_DEFINED = 0,
	FILE_NOT_FOUND = 1,
	ACCESS_VIOLATION = 2,
	DISK_FULL = 3,
	ILL_OP_TFTP = 4,
	UNKNOWN_PORT = 5,
	FILE_ALREADY_EXIST = 6,
	NO_SUCH_USER = 7,
};
typedef enum ErrCode ErrCode;

enum Mode {
	OCTET = 0,
	ASCII = 1,
};
typedef enum Mode Mode;

typedef char *errstr_t;

typedef char *filename_t;

typedef struct {
	u_int data_block_t_len;
	char *data_block_t_val;
} data_block_t;

struct request_t {
	filename_t fn;
	Mode mode;
};
typedef struct request_t request_t;

typedef u_int sess_t;

struct error_t {
	sess_t sess_id;
	errstr_t err;
	ErrCode code;
};
typedef struct error_t error_t;

struct res_t {
	bool_t success;
	union {
		error_t err;
		sess_t sess_id;
	} res_t_u;
};
typedef struct res_t res_t;

struct datapacket_t {
	sess_t sess_id;
	data_block_t blk;
};
typedef struct datapacket_t datapacket_t;

struct dataret_t {
	bool_t success;
	union {
		error_t err;
		data_block_t blk;
	} dataret_t_u;
};
typedef struct dataret_t dataret_t;

struct data_t {
	sess_t sess_id;
	dataret_t data;
};
typedef struct data_t data_t;

#define TFTP_PROG 0x20000000
#define TFTP_VERS 1

#if defined(__STDC__) || defined(__cplusplus)
#define GET_FILE 1
extern  res_t * get_file_1(request_t *, CLIENT *);
extern  res_t * get_file_1_svc(request_t *, struct svc_req *);
#define PUT_FILE 2
extern  res_t * put_file_1(request_t *, CLIENT *);
extern  res_t * put_file_1_svc(request_t *, struct svc_req *);
#define GET_BLOCK 3
extern  data_t * get_block_1(sess_t *, CLIENT *);
extern  data_t * get_block_1_svc(sess_t *, struct svc_req *);
#define PUT_BLOCK 4
extern  res_t * put_block_1(datapacket_t *, CLIENT *);
extern  res_t * put_block_1_svc(datapacket_t *, struct svc_req *);
#define ERROR 5
extern  void * error_1(error_t *, CLIENT *);
extern  void * error_1_svc(error_t *, struct svc_req *);
extern int tftp_prog_1_freeresult (SVCXPRT *, xdrproc_t, caddr_t);

#else /* K&R C */
#define GET_FILE 1
extern  res_t * get_file_1();
extern  res_t * get_file_1_svc();
#define PUT_FILE 2
extern  res_t * put_file_1();
extern  res_t * put_file_1_svc();
#define GET_BLOCK 3
extern  data_t * get_block_1();
extern  data_t * get_block_1_svc();
#define PUT_BLOCK 4
extern  res_t * put_block_1();
extern  res_t * put_block_1_svc();
#define ERROR 5
extern  void * error_1();
extern  void * error_1_svc();
extern int tftp_prog_1_freeresult ();
#endif /* K&R C */

/* the xdr functions */

#if defined(__STDC__) || defined(__cplusplus)
extern  bool_t xdr_ErrCode (XDR *, ErrCode*);
extern  bool_t xdr_Mode (XDR *, Mode*);
extern  bool_t xdr_errstr_t (XDR *, errstr_t*);
extern  bool_t xdr_filename_t (XDR *, filename_t*);
extern  bool_t xdr_data_block_t (XDR *, data_block_t*);
extern  bool_t xdr_request_t (XDR *, request_t*);
extern  bool_t xdr_sess_t (XDR *, sess_t*);
extern  bool_t xdr_error_t (XDR *, error_t*);
extern  bool_t xdr_res_t (XDR *, res_t*);
extern  bool_t xdr_datapacket_t (XDR *, datapacket_t*);
extern  bool_t xdr_dataret_t (XDR *, dataret_t*);
extern  bool_t xdr_data_t (XDR *, data_t*);

#else /* K&R C */
extern bool_t xdr_ErrCode ();
extern bool_t xdr_Mode ();
extern bool_t xdr_errstr_t ();
extern bool_t xdr_filename_t ();
extern bool_t xdr_data_block_t ();
extern bool_t xdr_request_t ();
extern bool_t xdr_sess_t ();
extern bool_t xdr_error_t ();
extern bool_t xdr_res_t ();
extern bool_t xdr_datapacket_t ();
extern bool_t xdr_dataret_t ();
extern bool_t xdr_data_t ();

#endif /* K&R C */

#ifdef __cplusplus
}
#endif

#endif /* !_TFTP_H_RPCGEN */
