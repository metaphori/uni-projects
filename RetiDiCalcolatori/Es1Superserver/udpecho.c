#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_LINE 1024

int main(int argc, char **argv){

	char line[MAX_LINE];
	
	for(;;){
		
		int rbytes = read(0, line, MAX_LINE, 0);
		if(rbytes < 0){
			perror("Si e' verificato un errore durante la ricezione della richiesta.");
			exit(-1);
		}
	
		int sbytes = write(1, line, sizeof(line), 0);
		if(sbytes < 0){
			perror("Si e' verificato un errore durante l'invio della risposta.");
			exit(-1);
		}
		
		memset(line, 0, MAX_LINE);
	}
	
	close(0);
	close(1);
	
	return 0;
}
