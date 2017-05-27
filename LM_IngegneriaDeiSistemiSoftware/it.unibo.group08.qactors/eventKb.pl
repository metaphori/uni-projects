%==============================================
% DEFINED BY THE SYSTEM DESIGNER
% system configuration: evenKb.pl
%==============================================
context( ctxevent, "localhost", "TCP", "8010" ).

qactor( evloopactor, ctxevent ).
 
 

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
