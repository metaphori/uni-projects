#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h> // execle()
#include <errno.h> // errno, EINTR..
#include <signal.h> // signal(), SIGCHLD
#include <sys/types.h>

#include <netinet/in.h> // IPPROTO..
#include <sys/socket.h>

typedef enum SSTATUS { ACTIVE, INACTIVE } SSTATUS;

struct Service {
	char name[20];
	char path[200];
	char pname[20];
	char proto[4];
	int port;
	char wait[7];
	
	char arg0[20];
	char arg1[20];
	char arg2[20];
	char arg3[20];
	char arg4[20];
	int nargs;

	int sfd;
	int pid;
	SSTATUS status;
};
typedef struct Service SERV;

void handleSIGCHLD(int pid);
int readline(FILE *fp, char *line, int maxlinesize);
char * extractname(char *str, int maxsize);

#define SERV_MAX 40
#define LINE_MAX 1024
#define MSG_MAX	5120

// GLOBALS
int nserv = 0;
SERV sset[SERV_MAX];
fd_set readySet;
	
int main(int argc, char **argv, char **envp){	
	FILE *fp = fopen("services.conf", "r");
	char line[LINE_MAX];
	
	/* ***********************************************************************************
	******************************** 1. configurazione ***********************************
	*********************************************************************************** */
	
	printf("--- Registering services ---\n");
	while ( readline(fp, line, LINE_MAX) > 0 ){
		
		sset[nserv].status = ACTIVE;
		
		sscanf(line, "%s ::= %s %s %d %s %s %s %s %s %s",
			sset[nserv].name,
			sset[nserv].path,
			sset[nserv].proto,
			&sset[nserv].port,
			sset[nserv].wait,
			sset[nserv].arg0, sset[nserv].arg1, sset[nserv].arg2, sset[nserv].arg3, sset[nserv].arg4);
		
		strncpy( sset[nserv].pname, extractname(sset[nserv].path, sizeof(sset[nserv].path)), sizeof( sset[nserv].pname ) ); 
		
		if( strlen(sset[nserv].arg0) == 0)
			sset[nserv].nargs = 0;
		else if(strlen(sset[nserv].arg1) == 0)
			sset[nserv].nargs = 1;
		else if(strlen(sset[nserv].arg2) == 0)
			sset[nserv].nargs = 2;
		else if(strlen(sset[nserv].arg3) == 0)
			sset[nserv].nargs = 3;
		else if(strlen(sset[nserv].arg4) == 0)
			sset[nserv].nargs = 4;
		else 
			sset[nserv].nargs = 5;
			
		printf("\n%s ::= [%s] [%s] [%d] [%s] {%s} {%s} {%s} {%s} {%s} (%d args)\n",
			sset[nserv].name,
			sset[nserv].path,
			sset[nserv].proto,
			sset[nserv].port,
			sset[nserv].wait,
			sset[nserv].arg0, sset[nserv].arg1, sset[nserv].arg2, sset[nserv].arg3, sset[nserv].arg4,
			sset[nserv].nargs);
			
		if( (sset[nserv].sfd = socket(AF_INET,
									strcmp(sset[nserv].proto,"tcp")==0 ? SOCK_STREAM : SOCK_DGRAM,
									strcmp(sset[nserv].proto,"tcp")==0 ? IPPROTO_TCP : IPPROTO_UDP)) < 0){
			sset[nserv].status = INACTIVE;
			perror("Errore durante la creazione del socket.");
		}

		// BIND del socket creato per il servizio ad una PORTA WELL-KNOWN
		struct sockaddr_in sin;
		sin.sin_family = AF_INET;
		sin.sin_port = htons( sset[nserv].port );
		sin.sin_addr.s_addr = INADDR_ANY;
		if ( bind(sset[nserv].sfd, (struct sockaddr *) &sin, sizeof(sin)) < 0){
			sset[nserv].status = INACTIVE;
			perror("Errore durante il binding del socket. (Risorsa gia' utilizzata?)");
		} 
		/*else{
			FD_SET( sset[nserv].sfd, &readySet );
		}*/
		
		// se TCP, LISTEN()
		if( strcmp(sset[nserv].proto, "tcp") == 0 ){
			listen ( sset[nserv].sfd, 5 );
		}
		
		// printf ("--- socket: %d --- ", sset[nserv].sfd);	
		
		nserv++;
		memset(line, 0, LINE_MAX);
	}
	
	// registrazione per notifiche morte dei processi server figli
	signal(SIGCHLD, handleSIGCHLD);
	
	/* ***********************************************************************************
	******************************** 2. gestione richieste *******************************
	*********************************************************************************** */
	
	// printf("\n>>> waiting for requests <<<\n\n");
	for ( ; ; ){
		
		FD_ZERO(&readySet);
		int j;
		for(j=0; j<nserv; j++){
			if( sset[j].status == ACTIVE)
				FD_SET( sset[j].sfd, &readySet);
		}
		
		int rs = select(nserv+4, &readySet, NULL, NULL, NULL);
		
		if(rs < 0){
			if(errno == EINTR) // select interrotta da interrupt sw
				continue;
			// errore
			perror("{ I/O multiplexing with select() error }");
			exit(-1);
		}
		
		// ok, ci sono socket pronti

		int k;
		for( k = 0; k < nserv; k++ ){
			
			if( FD_ISSET( sset[k].sfd , &readySet) ){
				
				if ( strcmp(sset[k].wait, "wait") == 0){ // GESTIONE DI SERVER SEQUENZIALI
					sset[k].status = INACTIVE;
				}
					
				/*	printf("-- contattato il servizio {%s}", sset[k].name);
				fflush(stdout); */
				
				int newsfd;
				struct sockaddr_in peer;
				int addrlen = sizeof(peer);
				
				if( strcmp( sset[k].proto, "tcp") == 0){ // servizio tcp
					newsfd = accept( sset[k].sfd, (struct sockaddr *) &peer, &addrlen);
				} 
				else { // servizio udp
					// prendo in carico delle richieste udp con recvfrom() da cui deduco indirizzo client (e ottengo il dgram) 
					char msg[MSG_MAX];
					
					int msglength;
					if ( ( msglength = recvfrom( sset[k].sfd, msg, MSG_MAX, 0, (struct sockaddr *) &peer, &addrlen) ) < 0){
						perror("{ errore durante ricezione datagram }");
					}
					
					// printf("{ Msg rcvd \"%s\" from %s:%d.", msg, (char*)inet_ntoa(peer.sin_addr), ntohs(peer.sin_port));
					
					// crea socket e binda a porta effimera
					newsfd = socket(AF_INET, SOCK_DGRAM, IPPROTO_UDP);
					
					struct sockaddr_in sin;
					sin.sin_family = AF_INET;
					sin.sin_port = 0; // porta effimera
					sin.sin_addr.s_addr = INADDR_ANY;
					if ( bind(newsfd, (struct sockaddr *) &sin, sizeof(sin)) < 0){
						perror("{ Impossibile bindare socket udp a porta effimera }");
					}
					
					struct sockaddr_in newsfaddr;
					newsfaddr.sin_family = AF_INET;
					int sinsize = sizeof(struct sockaddr_in);
					if ( getsockname(newsfd, (struct sockaddr *) &newsfaddr, &sinsize ) < 0 ){
						perror("{ Errore nell'ottenere il socket }");
					}
					//printf("{ Nuovo socket bindato a %s:%d }", (char*)inet_ntoa(nsockaddr.sin_addr), ntohs(nsockaddr.sin_port));
					
					// 1. invio dgram alla porta effimera
					if ( sendto( newsfd, msg, msglength, 0, (struct sockaddr *) &newsfaddr, sizeof(struct sockaddr_in)) < 0){
						perror("{ Impossibile mandare il dgram alla porta effimera }");
					}
					
					// 2. connessione porta effimera (newsfd) al client (peer)
					if ( connect(newsfd, (struct sockaddr *) &peer, sizeof(peer)) < 0){
						perror("{ Impossibile connettere il socket udp alla porta effimera }");
					}
				}
				
				// printf("[NSOCKFD=%d]", newsfd);
				// fflush(stdout);
				
				int pid = fork();
				if(pid == 0){ // processo server figlio 
					int j;
					for(j=4; j<4+nserv; j++)
							close( sset[j].sfd );
					
					close(0);
					close(1);
					dup(newsfd);
					dup(newsfd);
					
					switch( sset[j].nargs ){
						case 0: execle( sset[k].path, sset[k].pname, (char *)NULL, envp);
						case 1: execle( sset[k].path, sset[k].pname, sset[k].arg0, (char *)NULL, envp);
						case 2: execle( sset[k].path, sset[k].pname, sset[k].arg0, sset[k].arg1, (char *)NULL, envp);
						case 3: execle( sset[k].path, sset[k].pname, sset[k].arg0, sset[k].arg1, sset[k].arg2, (char *)NULL, envp);
						case 4: execle( sset[k].path, sset[k].pname, 
							sset[k].arg0, sset[k].arg1, sset[k].arg2, sset[k].arg3, (char *)NULL, envp);		
						case 5:	execle( sset[j].path, sset[k].pname, 
							sset[k].arg0, sset[k].arg1, sset[k].arg2, sset[k].arg3, sset[k].arg4, (char *)NULL, envp);
					} 
				}
				else if(pid<0){ // errore della fork
					perror("{ Fork error }");
				}
				else{ // processo padre
					sset[k].pid = pid;
					close(newsfd);
				}
			} // end if isset
		} // end for
	}
	
	// cleanup
	int k;
	for (k=0; k<nserv; k++){
		close( sset[k].sfd );
	}
	
	return 0;
}


// pericolo corsa critica tra server figlio sequenziale e registrazione come attivo
void handleSIGCHLD(int pid){
	// figlio è morto
	int pdied = wait(NULL);
	//printf("[Child %d DIED]", pdied);
	//fflush(stdout);
	
	// riattivare servizi sequenziali
	int j;
	for(j=0; j<nserv; j++){
		if( sset[j].pid == pdied ){
			//printf("[Child FOUND - set ACTIVE]");
			//fflush(stdout);
			//FD_SET(sset[j].sfd, &readySet);
			sset[j].status = ACTIVE;
			break;
		}
	}
}

char * extractname(char *pathname, int len){
	int i,k;
	char *name;
	name = (char *)malloc(len);
	for(i=0, k=0; i<len && pathname[i]!='\0'; i++){
		if( pathname[i] == '/'){
			memset(name, 0, len);
			k=0;
			continue;
		}
		name[k++] = pathname[i];
	}
	name[k]='\0';
	return name;
}

int readline(FILE *fp, char *line, int maxline){
	int nch = 0;
	char ch;	
	while ( (ch = fgetc(fp))!=EOF && nch<maxline ){
		if(ch=='\n') break;
		line[nch++] = ch;
	}
	line[nch]='\0';
	return nch;
}

