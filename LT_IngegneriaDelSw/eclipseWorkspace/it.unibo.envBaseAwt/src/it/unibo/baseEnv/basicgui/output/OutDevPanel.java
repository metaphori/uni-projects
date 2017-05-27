package it.unibo.baseEnv.basicgui.output;

import it.unibo.is.interfaces.IOutputView;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Panel;
import java.awt.ScrollPane;
import java.awt.TextArea;

/**
 * <font color="#800040">
 * Un dispositivo di virtuale di uscita.
 * @version 	 October 2002
 * @see           <a href="../../../util0/OutDevPanel.java" target="javaSource">sorgente</a>
 * @author 		 <em>ANatali</em>, DEIS, University of Bologna.
 * </font>
 */
public class OutDevPanel extends Panel implements  IOutputView{
/**
* Variabile che denota il dispositivo virtuale di output.
*/
protected TextArea  outDev;
protected java.awt.Color bgColor = java.awt.Color.white;

/**
*	Costruttore
*/


public OutDevPanel( int nRow, int nCol ){
	configura(nRow, nCol );
}//OutDevPanel

public OutDevPanel( int nRow, int nCol, java.awt.Color color ){
	bgColor = color;
	configura(nRow, nCol);
}//OutDevPanel

/**
* Configurazione.
* La TextArea che rappresenta il dispositivo di uscita
* viene inserita in un JScrollPane per abilitare lo scrolling
*/
protected void configura(int nRow, int nCol){
//ScrollPane scrollPanel;
	outDev        = new TextArea(nRow,nCol);
	outDev.setFont( new Font( "Arial",Font.BOLD,14) );
	outDev.setBackground(bgColor);
//	scrollPanel = new ScrollPane(  );
//	scrollPanel.add(outDev);
//	setLayout( new java.awt.BorderLayout() );
//	add( "Center",scrollPanel ); //layout di default = FlowLayout
	this.setLayout(new java.awt.BorderLayout());
	add( BorderLayout.CENTER,outDev );
	validate();
}//configura

public synchronized void setBgColor( java.awt.Color c){
	outDev.setBackground(c);
	validate();
}

/**
*	Visualizzazione di una stringa sul dispositivo virtuale di uscita.
*/
public synchronized void print( String msg ){
	outDev.append(msg);
	outDev.validate();
}//print

/**
* Visualizzazione di una stringa sul dispositivo virtuale di uscita
* con ritorno a capo.
*/
public synchronized void println( String msg ){
	outDev.append(msg+"\n");
	outDev.validate();
}//println

/**
* Pulisce.
*/

public synchronized void clear(  ){
	outDev.setText("");
	outDev.validate();
}//clear

public synchronized String read(  ){
	return outDev.getText();
}//read

@Override
public synchronized String getCurVal() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void addOutput(String msg) {
	println( msg );	
}

}//OutDevPanel
