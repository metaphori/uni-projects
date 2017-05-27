package it.unibo.contaKm.domain;

public class SysKb {

	public final static String command ="cmd";
	public final static String answer ="answer";
	public final static String input ="input";
	public final static String startS ="start";
	public final static String stopS ="stop";
	public final static String incS ="inc";
	public final static String getValS ="getVal";
	public final static String ipS ="IP";
	public static String hostName =null;
	public final static int serverPort = 80;
	
	public final static String tcpS ="TCP";
	public final static String udpS ="UDP";
	public final static String protocol = tcpS;
	
	public static String getCmdContent( String cmd ){
		return cmd.substring(cmd.indexOf("//")+2);
	}
	public static String getCmdscheme( String cmd ){
		return cmd.substring(0,cmd.indexOf("//"));
	}
	
}
