package it.unibo.baseEnv.basicgui.output;

 
import it.unibo.baseEnv.basicgui.ITextGui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;


/**
* output panel made of an output field and a button, The click on the
* button is transformed (by a <tt>StringoutputHandler</tt>) in a
* call to the <tt>askAction</tt> defined in the <tt>ISpeechPort</tt> port
* injected in the StringOutputPanel at construction time.
*/
public class StringViewer extends Panel implements ITextGui{
	/**
	 *	Pannello dei campi di ingresso.
	 */
	private  Panel fieldPanel = new Panel();

	/**
	 *	Campi di ingresso.
	 */
	private  TextField outputField=null;

	/**
	 *	Pulsante di comando.
	 */
  	protected  String askPortName, content;
  	protected int numOfColumns;

	/**
	 * Builds an output panel made of a  text field
  	 */
	public StringViewer(    ){
		this("", 10, "" );
	}

	/**
	 * Builds an output panel made of a  text field
	 *@param prompt :  prompt for the user
 	 */
	public StringViewer(  String prompt, int numOfColumns  ){
		this(prompt, numOfColumns, "" );
	}


	/**
	 *	Costruttore.
	 *@param numOfColumns number of columens in the output field
	 *@param prompt :  a string usefule for the user
 	 *@param content :  initial content
	 *@param askPort : something to call
	 */
	public StringViewer(  String msg, int numOfColumns, String content ){
 		this.numOfColumns = numOfColumns;
 		this.content   	= content;
		createEntities( msg );
	}//StringOutputPanel

	/**
	*Create the local resources.
	*/
	protected void createEntities(String prompt){
		outputField = new TextField(numOfColumns);
		outputField.setText( content );

		setLayout( new BorderLayout() );
		this.add( BorderLayout.WEST, new Label(prompt) );
		this.add( BorderLayout.CENTER, outputField );
		
		
 	}

	/**
	*@return the current output text.
	*/
	public String readln(){
		return outputField.getText();
	}

	/**
	*Set a content in the output device.
	*@param msg the text to be written.
	*/
	public void setText( String msg ){
		outputField.setText( msg );
		validate();
	}

	/**
	*Set the background color
	*@param c the color to be set.
	*/
	public void setBgColor( java.awt.Color c){
		outputField.setBackground(c);
		validate();
	}

	/**
	*Set the fornt
	*@param font the font to be set.
	*/
	public void setTheFont( Font font ){
		outputField.setFont(font);
		validate();
	}

 



}//StringOutputPanel

