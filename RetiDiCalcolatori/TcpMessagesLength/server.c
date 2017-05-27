#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <sys/socket.h>
#include <errno.h>

#define MSG_MAX 100000

int readmessage(int fd, char *buff, int maxsize);

int main(int argc, char **argv){
	
	if(argc!=2){ printf("USAGE: server <port>\n"); }
	int serverport = atoi(argv[1]);

	// create client socket
	int sfd;
	if( (sfd = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP)) < 0){
		perror("Cannot create socket: ");
		return -1;
	}
	
	// bind client socket to well known port
	struct sockaddr_in sin;
	sin.sin_family = AF_INET;
	sin.sin_port = htons(serverport);
	sin.sin_addr.s_addr = INADDR_ANY;
	if ( bind(sfd, (struct sockaddr *) &sin, sizeof(struct sockaddr_in)) < 0){
		perror("Cannot bind socket: ");
		return -1;
	}
	
	listen(sfd, 5);
	
	struct sockaddr_in client;
	int clilen = sizeof(struct sockaddr_in);
	char msg[MSG_MAX];
	int cfd;
	
	while(1){
	
		int cfd = accept(sfd, (struct sockaddr *)&client, &clilen); // creates a new connection
		
		while(1){
			memset(msg, 0, MSG_MAX);
			int len = readmessage(cfd, msg, MSG_MAX);
		
			if(len > 0 ){
				printf("I read the message(len=%d) <<<%s>>>\n\n", len, msg);
			} else{
				printf("ERROR.\n");
				break;
			}
		}
		
		close(cfd);
	}
	
	close(cfd);
	close(sfd);	
	
	return 0;
}

int readmessage(int fd, char *buff, int maxsize){
	printf("<R>");
	memset(buff, 0, maxsize);
	
	int nr;
	int length;
	if( (nr = recv(fd, &length, 2, MSG_WAITALL)) < 0){
		return -1;
	}
	
	int len = ntohs(length);
	if(len < 0) return -1;
	
	memset(buff, 0, maxsize);
	return readnch(fd, buff, len );
}

int readnch(int fd, char *buff, int len){
	
	int nleft = len;
	while(nleft!=0){
		int nr = read(fd, buff, nleft);
		printf("R%d--", nr);
		if(nr<=0)
			return -1;
		nleft -= nr;
		buff +=nr;
	}
	return len;
}






