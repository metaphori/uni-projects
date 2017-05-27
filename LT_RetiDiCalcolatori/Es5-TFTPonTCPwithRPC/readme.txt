****** Roberto Casadei
****** 0000440575
****** Esercitazione 5: TFTP su TCP con RPC
****** 14/12/2011
******

***************** 1. AVVIO DEL SERVER TFTP **********************
Il server (lo skeleton), puo' essere avviato specificando un TRACE_LEVEL sulla linea di comando (1,2,3 in ordine crescente di verbosita').

	$ ./server <trace_level>

Per fare questo e' stato modificato il server skeleton, affinche' venisse letto l'argomento da linea di comando e impostasse il trace level.


***************** 2. AVVIO DEL CLIENT TFTP **********************
Il client deve conoscere a priori la macchina su cui e' disponibile il servizio RPC. Dovra' percio' ricevere in ingresso l'indirizzo o il nome di tale macchina.
Inoltre, anche il client puo' essere avviato con un trace level. 
	
	$ ./client <servername/addr> [trace_level]
	

***************** 3. TEST SULLE FUNZIONALITA' DI BASE ************************
Si e' verificato se fosse possibile ricevere (GET) e inviare file (PUT).

	$ ls -l
	.......
	-rw-rw-r-- 1 roby roby      6702 2011-12-14 01:29 client.c
	.......
	-rw-rw-r-- 1 roby roby      7481 2011-12-14 02:52 server.c
	.......
	
	(il server e' gia' attivo)
	$ ./client 127.0.0.1 1
	
		> put client.c xxx
		[WRQ Success]
		* The request has been satisfied.

		> get yyy server.c
		[RRQ Success]
		* The request has been satisfied.
		
		>quit
	
	$ ls -l
	.......
	-rw-rw-r-- 1 roby roby      6702 2011-12-14 01:29 client.c
	.......
	-rw-rw-r-- 1 roby roby      7549 2011-12-14 02:52 server.c
	.......
	-rw-rw-r-- 1 roby roby      6702 2011-12-14 03:16 xxx
	-rw-rw-r-- 1 roby roby      7549 2011-12-14 03:16 yyy
	
	$ diff client.c xxx
	
	$ diff server.c yyy
	
	$


***************** 4. TEST IN CONDIZIONI LIMITE **********************
Per verificare il corretto funzionamento dell'applicazione, sono stati effettuati i seguenti test aggiuntivi:

A) Invio / ricezione di un file di grosse dimensioni e di dimensione multipla di 512

	$ ls -l
	-rw-rw-r-- 1 roby roby 152506368 2011-12-14 02:40 aaa
	......
	
Il file 'aaa' e' circa 145 MB ed e' di dimensione multipla di 512 byte.

	$ ./client 127.0.0.1 1 
		> get bbb aaa
		[RRQ Success]
		* The request has been satisfied.
		
		> put bbb ccc
		[WRQ Success]
		* The request has been satisfied.
		
		> quit
		
	$ ls -l
	-rw-rw-r-- 1 roby roby 152506368 2011-12-14 03:30 aaa
	-rw-rw-r-- 1 roby roby 152506368 2011-12-14 03:34 bbb
	-rw-rw-r-- 1 roby roby 152506368 2011-12-14 03:38 ccc
	.......
	
	$ diff aaa ccc
	
	$
	
B) Il server deve rifiutare nuove richieste se gia' ce n'e' una attiva (e non e' passato troppo tempo dall'ultima operazione):
Se durante le operazioni del caso A), viene avviato un altro client e questo tenta di effettuare una richiesta, si ottiene:
	
	$ ./client 127.0.0.1 1
		> put client.c zzz
		(ERR(0): Another file transfer is already running.)
		* ERR received.
		
		> get zzz client.c
		(ERR(0): Another file transfer is already running.)
		* ERR received.
		
Il "non e' passato troppo tempo dall'ultima operazione" e' stato impostato ad 1 secondo. Per verificare che a seguito di ritardi cospicui (superiori ad 1 secondo) sia possibile avviare un'altra sessione di file transfer, si e' modificato *temporaneamente* il client introducendo uno sleep() di qualche secondo.
Il ritardo e' cosi' grande che un nuovo client puo' effettuare una richiesta d'inizio di file transfer. La vecchia sessione di file transfer viene chiusa e il "vecchio" client ricevera' un errore dovuto al fatto di aver fornito un id di sessione errato.
(Ci possono essere problemi di corse critiche ma sarebbero casi molto rari e quindi si e' deciso di non considerare la cosa.)

C) I file remoti non possono essere sovrascritti

	> put client.c client.c
		(ERR(6): The file already exist.)
		* ERR received.


******************** 4. NOTE SULL'IMPLEMENTAZIONE **********************
I file transfer sono caratterizzati da un identificativo di sessione che permette, in secondo luogo, il riconoscimento del client.
Il problema viene sollevato dal fatto che, una volta creata una nuova sessione a causa di ritardi dovuti alla mancata esecuzione di operazioni di scrittura/lettura di blocchi per un certo tempo (1 secondo), il "vecchio" client potrebbe continuare ad eseguire tali operazioni. Se cio' accade, il server deve poter riconoscere che e' avvenuto qualcosa di anomalo.

Allo scopo, e' stato introdotto un identificativo di sessione, che viene restituito dalle RPC di inizio sessione (GET_FILE e PUT_FILE), e che il client deve passare ad ogni operazione di lettura/scrittura di un blocco di dati (GET_BLOCK e PUT_BLOCK). Se tale cookie non corrisponde a quello corrente, viene restituito un errore al client, che interrompera' il suo trasferimento che era ancora in corso.



