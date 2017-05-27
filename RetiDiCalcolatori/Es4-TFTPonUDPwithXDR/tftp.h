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
#define MAX_MODE_LENGTH 6
#define MAX_DAT_LENGTH 512
#define MAX_STRING_ERR_LEN 100

enum OpType {
	RRQ = 0x01,
	WRQ = 0x02,
	DAT = 0x03,
	ACK = 0x04,
	ERR = 0x05,
};
typedef enum OpType OpType;

enum Mode {
	OCTET = 0,
	ASCII = 1,
};
typedef enum Mode Mode;

struct RrqAndWrqMsg {
	char *fileName;
	Mode mode;
};
typedef struct RrqAndWrqMsg RrqAndWrqMsg;

struct DatMsg {
	u_int blkn;
	struct {
		u_int dat_len;
		char *dat_val;
	} dat;
};
typedef struct DatMsg DatMsg;

struct AckMsg {
	u_int blkn;
};
typedef struct AckMsg AckMsg;

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

struct ErrMsg {
	ErrCode errCode;
	char *errStr;
};
typedef struct ErrMsg ErrMsg;

struct TftpMsg {
	OpType opType;
	union {
		RrqAndWrqMsg rrqMsg;
		RrqAndWrqMsg wrqMsg;
		DatMsg datMsg;
		AckMsg ackMsg;
		ErrMsg errMsg;
	} TftpMsg_u;
};
typedef struct TftpMsg TftpMsg;

/* the xdr functions */

#if defined(__STDC__) || defined(__cplusplus)
extern  bool_t xdr_OpType (XDR *, OpType*);
extern  bool_t xdr_Mode (XDR *, Mode*);
extern  bool_t xdr_RrqAndWrqMsg (XDR *, RrqAndWrqMsg*);
extern  bool_t xdr_DatMsg (XDR *, DatMsg*);
extern  bool_t xdr_AckMsg (XDR *, AckMsg*);
extern  bool_t xdr_ErrCode (XDR *, ErrCode*);
extern  bool_t xdr_ErrMsg (XDR *, ErrMsg*);
extern  bool_t xdr_TftpMsg (XDR *, TftpMsg*);

#else /* K&R C */
extern bool_t xdr_OpType ();
extern bool_t xdr_Mode ();
extern bool_t xdr_RrqAndWrqMsg ();
extern bool_t xdr_DatMsg ();
extern bool_t xdr_AckMsg ();
extern bool_t xdr_ErrCode ();
extern bool_t xdr_ErrMsg ();
extern bool_t xdr_TftpMsg ();

#endif /* K&R C */

#ifdef __cplusplus
}
#endif

#endif /* !_TFTP_H_RPCGEN */
