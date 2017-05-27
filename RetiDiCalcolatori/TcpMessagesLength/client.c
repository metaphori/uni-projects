#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <sys/socket.h>
#include <errno.h>
#include <netinet/tcp.h> // for TCP_NODELAY

#define MSG_MAX 100000

int sendmessage(int fd, char *buff, int len);
int writench(int fd, char *buff, int len);

int main(int argc, char **argv){
	
	if(argc!=3){ printf("USAGE: client <servaddr> <servport>\n"); }
	char *serveraddr = argv[1];
	int serverport = atoi(argv[2]);

	// create client socket
	int sfd;
	if( (sfd = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP)) < 0){
		perror("Cannot create socket: ");
		return -1;
	}
	
	// bind client socket to ephimeral port
	struct sockaddr_in sin;
	sin.sin_family = AF_INET;
	sin.sin_port = 0;
	sin.sin_addr.s_addr = INADDR_ANY;
	if ( bind(sfd, (struct sockaddr *) &sin, sizeof(struct sockaddr_in)) < 0){
		perror("Cannot bind socket: ");
		return -1;
	}
	
	// (association) server address
	struct sockaddr_in servaddr;
	servaddr.sin_family = AF_INET;
	servaddr.sin_port = htons(serverport);
	servaddr.sin_addr.s_addr = inet_addr(serveraddr);
	
	char cmdLine[MSG_MAX];

	if( connect(sfd, (struct sockaddr*)&servaddr, sizeof(struct sockaddr_in)) < 0){
		perror("Cannot connect to tcp server: ");
		return -1;
	}

	int flag = 1;
	if( setsockopt(sfd, IPPROTO_TCP, TCP_NODELAY, (char*)&flag, sizeof(flag)) < 0){
		perror("Cannot disable NAGLE: ");
		return -1;
	}

	int interaction = 0;
	
	for (; ;){
		printf("\n> ");
		memset(cmdLine, 0, MSG_MAX);
		gets(cmdLine);
		
		if( strcmp(cmdLine, "quit") == 0 ) break;
		
		int len = strlen(cmdLine);
		int nw = sendmessage(sfd, cmdLine, len);
		if(nw > 0){
			printf("* Message sent correctly.\n");
		} else{
			printf("* Cannot send any msg.\n");
		}
		
	}
	
	close(sfd);	
	
	return 0;
}


int sendmessage(int fd, char *buff, int len){
	uint16_t ln = htons(len);
	int nw = writench(fd, (char*)&ln, sizeof(uint16_t));
	if(nw < 0) return -1;
	
	return writench(fd, buff, len);
}

int writench(int fd, char *buff, int len){
	int start = 0;
	int nleft = len;
	while(nleft!=0){
		int nw = write(fd, &buff[len-nleft], nleft);
		if(nw < 0)
			return -1;
		nleft = nleft-nw;		
	}
	return len;
}





