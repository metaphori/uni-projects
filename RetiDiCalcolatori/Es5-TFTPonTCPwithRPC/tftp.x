/* Definizione di Sintassi Astratta in XDR per proto applicativo TFTP */

const MAX_FILE_NAME_LENGTH 		= 500; /* max lunghezza del nome del file */
const MAX_DAT_LENGTH	 	 	= 512; /* max lunghezza di un blocco dati */
const MAX_STRING_ERR_LEN	 	= 100; /* max lunghezza della stringa di errore */

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

enum Mode { OCTET, ASCII };

typedef string errstr_t<MAX_STRING_ERR_LEN>;
typedef string filename_t<MAX_FILE_NAME_LENGTH>;
typedef opaque data_block_t<MAX_DAT_LENGTH>;

struct request_t {
	filename_t fn;
	Mode mode;
};


typedef unsigned int sess_t; /* session identifier type*/

struct error_t { /* ERR */
	sess_t sess_id;
	errstr_t err;
	ErrCode code;
};

union res_t switch(bool success){ /* returned on requests/putting data */
	case FALSE: error_t err;
	case TRUE: sess_t sess_id;
};

struct datapacket_t { /* passed when putting data */
	sess_t sess_id;
	data_block_t blk;
};

union dataret_t switch(bool success){
		case FALSE: error_t err;
		case TRUE: data_block_t blk;
};
struct data_t { /* returned when getting data */
	sess_t sess_id;
	dataret_t data;
};

program TFTP_PROG {
	version TFTP_VERS {
		
		res_t GET_FILE(request_t) = 1;
		res_t PUT_FILE(request_t) = 2;
		
		data_t GET_BLOCK(sess_t) = 3;
		res_t PUT_BLOCK(datapacket_t) = 4;
		
		void ERROR(error_t) = 5;	
		
	} = 1;
} = 0x20000000;
	
	
	
	
	
	
	
	
	
	
	
	


