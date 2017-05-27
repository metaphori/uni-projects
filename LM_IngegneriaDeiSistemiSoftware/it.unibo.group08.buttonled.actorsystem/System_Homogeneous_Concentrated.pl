%==============================================
% USER DEFINED
% system configuration: System_Homogeneous_Concentrated.pl
%==============================================
context( ctxofbutton, "0.0.0.0",  "TCP", "8010" ).
context( ctxofcontrol,   "0.0.0.0", "TCP",  "8020" ).     
context( ctxofled,   "0.0.0.0", "TCP",  "8030" ).      
 
qactor( qacontrol, ctxofcontrol ).
qactor( qabutton, ctxofbutton ).
qactor( qaled, ctxofled ).



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
