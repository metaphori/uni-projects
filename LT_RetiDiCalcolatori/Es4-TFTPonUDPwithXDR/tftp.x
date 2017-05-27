/* Definizione di Sintassi Astratta in XDR per proto applicativo TFTP */

const MAX_FILE_NAME_LENGTH 		= 500; /* max lunghezza del nome del file */
const MAX_DAT_LENGTH	 	 	= 512; /* max lunghezza di un blocco dati */
const MAX_STRING_ERR_LEN	 	= 100; /* max lunghezza della stringa di
                                     errore */
enum OpType {
	RRQ = 0x01,	/* PDU di richiesta operazione get */
	WRQ = 0x02,	/* PDU di richiesta operazione put */
	DAT = 0x03,	/* PDU di trasferimento di un blocco dati */
	ACK = 0x04,	/* PDU di ACK */
	ERR  = 0x05	/* PDU di errore */
};/* enumerato che identifica i 5 sottotipi di PDU del protocollo TFTP */

enum Mode { OCTET, ASCII };

struct RrqAndWrqMsg {
	string    	fileName<MAX_FILE_NAME_LENGTH>;
	Mode    	mode;
}; /* tipo XDR per i pacchetti RRQ e WRQ */

struct DatMsg {
	unsigned int    		blkn;
	opaque     	dat<MAX_DAT_LENGTH>;
}; /* tipo XDR per il pacchetto DAT */

struct AckMsg {
	unsigned int    blkn;
}; /* tipo XDR per il pacchetto ACK */

enum ErrCode {
  NOT_DEFINED         = 0, /* errore non definito: vedi stringa di errore se presente */
  FILE_NOT_FOUND      = 1,
  ACCESS_VIOLATION    = 2,
  DISK_FULL           = 3, /* disco pieno o allocazione eccessiva */
  ILL_OP_TFTP         = 4, /* operazione tftp illegale */
  UNKNOWN_PORT        = 5,
  FILE_ALREADY_EXIST  = 6,
  NO_SUCH_USER        = 7
 }; /* enumerato XDR che definisce i codici ci errore */

struct ErrMsg {
	ErrCode      	errCode;
	string         	errStr<MAX_STRING_ERR_LEN>;
}; /* tipo XDR per il pacchetto ERR */

union TftpMsg switch (OpType opType) {
	case RRQ:   RrqAndWrqMsg	rrqMsg;
	case WRQ:	RrqAndWrqMsg	wrqMsg;
	case DAT:	DatMsg			datMsg;
	case ACK:	AckMsg			ackMsg;
	case ERR:	ErrMsg			errMsg;
}; /* tipo XDR per generico PDU TFTP tra client e server */

