/*
 * Please do not edit this file.
 * It was generated using rpcgen.
 */

#include "tftp.h"

bool_t
xdr_OpType (XDR *xdrs, OpType *objp)
{
	register int32_t *buf;

	 if (!xdr_enum (xdrs, (enum_t *) objp))
		 return FALSE;
	return TRUE;
}

bool_t
xdr_Mode (XDR *xdrs, Mode *objp)
{
	register int32_t *buf;

	 if (!xdr_enum (xdrs, (enum_t *) objp))
		 return FALSE;
	return TRUE;
}

bool_t
xdr_RrqAndWrqMsg (XDR *xdrs, RrqAndWrqMsg *objp)
{
	register int32_t *buf;

	 if (!xdr_string (xdrs, &objp->fileName, MAX_FILE_NAME_LENGTH))
		 return FALSE;
	 if (!xdr_Mode (xdrs, &objp->mode))
		 return FALSE;
	return TRUE;
}

bool_t
xdr_DatMsg (XDR *xdrs, DatMsg *objp)
{
	register int32_t *buf;

	 if (!xdr_u_int (xdrs, &objp->blkn))
		 return FALSE;
	 if (!xdr_bytes (xdrs, (char **)&objp->dat.dat_val, (u_int *) &objp->dat.dat_len, MAX_DAT_LENGTH))
		 return FALSE;
	return TRUE;
}

bool_t
xdr_AckMsg (XDR *xdrs, AckMsg *objp)
{
	register int32_t *buf;

	 if (!xdr_u_int (xdrs, &objp->blkn))
		 return FALSE;
	return TRUE;
}

bool_t
xdr_ErrCode (XDR *xdrs, ErrCode *objp)
{
	register int32_t *buf;

	 if (!xdr_enum (xdrs, (enum_t *) objp))
		 return FALSE;
	return TRUE;
}

bool_t
xdr_ErrMsg (XDR *xdrs, ErrMsg *objp)
{
	register int32_t *buf;

	 if (!xdr_ErrCode (xdrs, &objp->errCode))
		 return FALSE;
	 if (!xdr_string (xdrs, &objp->errStr, MAX_STRING_ERR_LEN))
		 return FALSE;
	return TRUE;
}

bool_t
xdr_TftpMsg (XDR *xdrs, TftpMsg *objp)
{
	register int32_t *buf;

	 if (!xdr_OpType (xdrs, &objp->opType))
		 return FALSE;
	switch (objp->opType) {
	case RRQ:
		 if (!xdr_RrqAndWrqMsg (xdrs, &objp->TftpMsg_u.rrqMsg))
			 return FALSE;
		break;
	case WRQ:
		 if (!xdr_RrqAndWrqMsg (xdrs, &objp->TftpMsg_u.wrqMsg))
			 return FALSE;
		break;
	case DAT:
		 if (!xdr_DatMsg (xdrs, &objp->TftpMsg_u.datMsg))
			 return FALSE;
		break;
	case ACK:
		 if (!xdr_AckMsg (xdrs, &objp->TftpMsg_u.ackMsg))
			 return FALSE;
		break;
	case ERR:
		 if (!xdr_ErrMsg (xdrs, &objp->TftpMsg_u.errMsg))
			 return FALSE;
		break;
	default:
		return FALSE;
	}
	return TRUE;
}
