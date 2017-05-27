%==============================================
% USER DEFINED
% system configuration: sysKb.pl
%==============================================
context( ctxofreceiver, "192.168.43.229",  "TCP", "8010" ).  % 192.168.43.229 is PC
context( ctxofsender,   "192.168.43.229", "TCP",  "8020" ).     % 192.168.43.71 is Android
 
qactor( qasender, ctxofsender ).
qactor( qareceiver, ctxofreceiver ).



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
%  "/dev/tty.usbserial-A9007UX1"  // Mac OS X 
%  "/dev/ttyUSB0"    // Linux
%  "COM31"           // Windows  
%%% --------------------------------------------------
