****** Roberto Casadei
****** 0000440575
****** Esercitazione 4: TFTP su UDP con XDR
****** 10/12/2011
******

***************** 1. AVVIO DEL SERVER TFTP **********************
Il server TFTP può essere avviato sia in modalità standalone sia come filtro UNIX.
Accetta un parametro in ingresso, corrispondente alla porta well-know del server di associazione:
	
	$ ./server [-standalone] <wellknown_port> [DEBUG_ON=0|1]
	

***************** 2. AVVIO DEL CLIENT TFTP **********************
Al client devono essere passati l'indirizzo e la porta UDP well-know del server TFTP:

	$ ./client <server_addr> <server_port> [DEBUG_ON=0|1]
	

***************** 3. TEST SULLE FUNZIONALITA' DI BASE ************************
Al client fondamentalmente deve essere possibile inviare e ricevere file.	
	(assumo il server gia' attivo sulla porta 7000, avviato in modalità standalone o attraverso INETD)
	
	$ ./client 127.0.0.1 7000
	
		> h
			----------------------------------------------
	  		HELP. Commands:
	  		get <localFileName> <remoteFileName>
	  		put <localFileName> <remoteFileName>
			----------------------------------------------
	
		> put client.c client_copy.c

			* The request has been satisfied.

		> get client_copy2.c client_copy.c

			* The request has been satisfied.
	
		> quit
	
	$ ls -l
		.......................
		-rwxrwxr-x 1 roby roby    30507 2011-12-09 12:04 client
		-rw-rw-r-- 1 roby roby    12197 2011-12-09 12:04 client.c
		-rw-rw-r-- 1 roby roby    12135 2011-12-09 12:04 client.c~
		-rw-rw-r-- 1 roby roby    12197 2011-12-09 12:18 client_copy2.c
		-rw-rw-r-- 1 roby roby    12197 2011-12-09 12:18 client_copy.c
		.......................
	
	$ diff client.c client_copy.c
	
	$ echo "a" >> client_copy.c
	
	$ diff client_copy.c client_copy2.c
		469a470
		> a
	
	$
	

***************** 4. TEST IN CONDIZIONI CRITICHE **********************
Condizioni critiche:
1) Invio/ricezione di file di grosse dimensioni, in particolare di dim. superiore a (2^16 - 1 * 512) byte = circa 32 Mb
	Cio' consente di verificare la trasmissione nel caso di wraparound (ossia del fatto che il campo BLKN possa valere fino a 2^16-1; cio' non deve porre limiti alla dimensione dei file trasmessi), ed inoltre di testare il dimensionamento dei timeout di ACK e DAT (in quanto l'invio di un grosso numero di pacchetti consente di verificare perdite piu' frequenti).
	Nell'esempio in questione il file è di circa 70 Mb. 
	
	$ ls -l
		-rw-rw-r-- 1 roby roby 71338496 2011-12-09 11:11 aaa
		....................................................
	
	$ ./client 127.0.0.1 7000
	
		> put aaa bbb
			* The request has been satisfied.
		> quit
	
	$ ls -l
		-rw-rw-r-- 1 roby roby 71338496 2011-12-09 11:11 aaa
		-rw-rw-r-- 1 roby roby 71338496 2011-12-09 11:19 bbb
		....................................................
	
	$ diff aaa bbb
	
	$ 

2) Invio/ricezione di un file di dimensione multipla di 512.
	In questo caso, il protocollo richiede l'invio di un pacchetto DAT "vuoto" (cioe' senza dati).
	Gia' contemplato nel caso 1) in quanto la dimensione del file trasmesso 'aaa' è 71338496 = 512 * 139333
	

***************** 5. DIMENSIONAMENTO DEI TIMEOUT **********************
Nell'applicazioni vi sono tre tipi di timeout:
1- per la ritrasmissione di DAT (quando attendiamo degli ACK) 
2- quando attendiamo un DAT, per accorgersi della scomparsa dell'interlocutore
3- quando aspettiamo una richiesta RRQ/WRQ da parte del client

A parte il primo, i restanti devono essere dimensionati tenendo conto del RTT medio.
Con un'analisi dei pacchetti con Wireshark, risulta che i pacchetti (complessivi di tutti i livelli) che viaggiano in rete sono di circa 60 byte per gli RRQ, WRQ e gli ACK, mentre i DAT (che in genere viaggiano pieni - con un payload di 512 byte - tranne l'ultimo) sono di circa 560 byte. Tra gli ACK e i DAT vi è perciò un rapporto circa 1 a 10. Ciò dev'essere rispecchiato nella scelta dei valori di timeout.
Il test relativo all'invio di file di grosse dimensioni ha aiutato a verificare la possibilità di perdere pacchetti, cosicché, infine, si è optato per il seguente dimensionamento:
1- attesa ACK => circa 80 ms
2- attesa DAT => circa 400 ms
3- attesa RRQ/WRK => 60 s 


***************** 6. NOTE SULL'IMPLEMENTAZIONE **********************
Si è cercato di mantenere separati i livelli dell'applicazione utente, del livello applicazione (TFTP) e del livello di presentazione (XDR).

Il client e il server dispongono di funzioni che complessivamente formano la Protocol Entity TFTP.
Le funzioni encodeMsg() e decodeMsg() si interfacciano con il livello di presentazione (XDR).
Le funzioni writeMsg() e readMsg() sono le funzioni che operano a livello di rete.
Le funzioni waitForMsg(), sendRequest, sendACK, sendERR, sendDAT sono il cuore  della PE TFTP e vengono utilizzate direttamente dalle applicazioni client e server per realizzare le funzionalità d'alto livello sendFile() e receiveFile().


