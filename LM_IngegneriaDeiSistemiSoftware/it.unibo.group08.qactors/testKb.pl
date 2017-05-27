%==============================================
% USER DEFINED
% system configuration: testKb.pl
%==============================================
context( ctx1, "192.168.43.229", "TCP", "8010" ).

qactor( qa1a, ctx1 ).
qactor( qa1b, ctx1 ). 
 

%==============================================
% MEMENTO
%==============================================
%%% --------------------------------------------------
% context( NAME, HOST, PROTOCOL, PORT )
% PROTOCOL : "TCP" | "UDP" | "SERIAL" | ...
%%% --------------------------------------------------

%%% --------------------------------------------------
% msg( MSGTYPE, SENDER, RECEIVER, CONTENT, SEQNUM )
% MSGTYPE : dispatch request answer
%%% --------------------------------------------------

%%% --------------------------------------------------
%  "/dev/tty.usbserial-A9007UX1" 	// Mac OS X 
%  "/dev/ttyUSB0" 					// Linux
%  "COM31"							// Windows  
%%% --------------------------------------------------