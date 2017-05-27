****** Roberto Casadei
****** 0000440575
****** Esercitazione 1: Superserver inetd
****** 8/11/2011
******

***************** 1. AVVIO DEL SUPERSERVER **********************
$ ./inetd &
[1] 3590
--- Registering services ---

catSEQ ::= [/bin/cat] [tcp] [7005] [wait] {} {} {} {} {} (0 args)

catCONC ::= [/bin/cat] [tcp] [7006] [nowait] {} {} {} {} {} (0 args)

cshCONC ::= [/bin/csh] [tcp] [7007] [nowait] {-i} {} {} {} {} (1 args)

cshSEQ ::= [/bin/csh] [tcp] [7008] [wait] {-i} {} {} {} {} (1 args)

echoCL ::= [/home/roby/Desktop/RETICALC/ES1-SuperServer/udpecho] [udp] [7009] [nowait] {} {} {} {} {} (0 args)


***************** 2. TEST DI SERVIZI TCP **********************
$ nc -v 127.0.0.1 7007
Connection to 127.0.0.1 7007 port [tcp/afs3-bos] succeeded!
> ls
inetd
inetd.c
inetd.c~
readme.txt
readme.txt~
services.conf
services.conf~
udpecho
udpecho.c
udpecho.c~
udpechoclient
udpechoclient.c
udpechoclient.c~
> pwd
/home/roby/Desktop/RETICALC/ES1-SuperServer
> ps -al
F S   UID   PID  PPID  C PRI  NI ADDR SZ WCHAN  TTY          TIME CMD
0 S  1000  3590  1618  0  80   0 -   492 poll_s pts/0    00:00:00 inetd
0 S  1000  3593  1618  0  80   0 -   917 poll_s pts/0    00:00:00 nc
0 S  1000  3594  3590  0  80   0 -  1111 rt_sig pts/0    00:00:00 csh
0 R  1000  3597  3594  0  80   0 -  1193 -      pts/0    00:00:00 ps
^C

***************** 3. TEST DI SERVIZI UDP **********************

$ ./udpechoclient 127.0.0.1 7009
>>hello, tcp/ip
ECHO: hello, tcp/ip
>>Hi, man
ECHO: Hi, man
>>exit
$ 

***************** 4. TEST DI SERVIZI SEQUENZIALI **********************

term1$ nc -v 127.0.0.1 7005
Connection to 127.0.0.1 7005 port [tcp/afs3-volser] succeeded!
hi
hi
^C

term2$ nc -v 127.0.0.1 7005
Connection to 127.0.0.1 7005 port [tcp/afs3-volser] succeeded!
ping
ping

La dinamica è la seguente: term1 si collega per primo al servizio 'cat' sequenziale e usufruisce del servizio. Se term2 si collega 
anch'esso e prova a scrivere, non riceve alcuna risposta. Non appena term1 chiude l'interazione, il servizio si prende carico della
connessione iniziata da term2, e quest'ultimo riceve risposta.

***************** 5. TEST DI SERVIZI CONCORRENTI **********************

Si utilizza lo stesso procedimento del punto 3), contattando però un servizio "nowait". Si noterà che l'interazione può essere
portata avanti da entrambi i terminali (che corrispondono a due client distinti).

***************** 6. NOTE SULL'IMPLEMENTAZIONE **********************

La gestione di servizi SEQUENZIALI si realizza attraverso un flag ( SSTATUS = { ACTIVE, INACTIVE } ).
Quando è in esecuzione un servizio sequenziale, il flag viene settato su INACTIVE, il che provoca l'esclusione del relativo
socket file descriptor dal fd_set di lettura passato alla select().
Il superserver registra i segnali SIGCHLD, perciò le morti dei processi figli scatenano un handler. Nell'handler, si scorre
la lista di servizi in cerca di quello associato al pid del processo morto, e lo stato viene settato come ACTIVE. Come conseguenza,
il socket file descriptor è incluso nell'insieme fd_set, e il servizio ritorna disponibile.
Poiché i segnali causano lo sblocco della select(), essa non rimane bloccata senza che un server sequenziale terminato sia
settato come ACTIVE.

