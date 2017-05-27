#include <stdio.h>
#include <stdlib.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>

#include <unistd.h>
#include <string.h>

#define MAX_LINE 1024

int main(int argc, char **argv){
	
	if(argc!=3){
		printf("USAGE: udpechoclient [addr] [port]\n");
		exit(-1);
	}
	
	char addr[50];
	strcpy(addr, argv[1]); 
	int port = atoi(argv[2]);
	
	int sfd = socket(AF_INET, SOCK_DGRAM, IPPROTO_UDP);
	if( sfd < 0 ){
		perror(" <Cannot create the socket> ");
		exit(-1);
	}
	
	struct sockaddr_in client;
	client.sin_family = AF_INET;
	client.sin_port = 0; // effimera
	client.sin_addr.s_addr = INADDR_ANY;
	
	
	if ( bind(sfd, (struct sockaddr *) &client, sizeof(client)) < 0){
		perror(" <Cannot bind the socket> ");
		exit(-1);
	}
	
	struct sockaddr_in serv;
	serv.sin_family = AF_INET;
	serv.sin_port = htons(port);
	serv.sin_addr.s_addr = inet_addr(addr);
	int servsize = sizeof(struct sockaddr_in);

	char line[MAX_LINE];
	char resp[MAX_LINE];

	for(;;){
		
		printf(">>");
		gets(line);
		
		if (strcmp(line, "exit")==0)
			break;
		
		if ( sendto(sfd, line, sizeof(line), 0, (struct sockaddr *) &serv, sizeof(serv)) < 0 ){
			perror(" <Cannot send any msg> ");
			exit(-1);
		}
		
		recvfrom(sfd, resp, sizeof(resp), 0, (struct sockaddr *) &serv, &servsize);
		
		printf("ECHO: %s\n", resp);
		
		memset(resp, 0, MAX_LINE);
		memset(line, 0, MAX_LINE);
		
	}
	
	close(sfd);
	
	return 0;
}
