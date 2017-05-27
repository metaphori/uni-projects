package it.unibo.contaKm.view;
import it.unibo.is.interfaces.IOutputView;

public class OutDevStandard implements  IOutputView{
private String msg; 
 
public  void print( String msg ){
	this.msg = msg;
	System.out.print(msg);
}//print

public  void println( String msg ){
	this.msg = msg;
	System.out.println(msg);
}//println

public void addOutput(String arg0) {
 	println(msg);
}

public String getCurVal() {
 	return msg;
}

}//OutDevStandard
