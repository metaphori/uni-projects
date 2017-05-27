/*
  */
package it.unibo.baseEnv.basicgui.input;

import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.TextField;
 


/**
 * @author Antonio Natali
 */
public class SimpleInputPanel extends Panel{
private TextField inputField ;
 

 	public SimpleInputPanel()  {
 		configure( );
	}
 	
 	public String realdn(){
  		return inputField.getText();
 	}

 	protected void configure(   ){
		BorderLayout bdl = new BorderLayout();
 		Panel inpPanel = createInputPanel( );
		setLayout( bdl );
 		this.add(BorderLayout.SOUTH, inpPanel );
  	}//configure

 	
	protected Panel createInputPanel(  ){
		Panel inputPanel 	= new Panel();
		inputField   		= new TextField(20);
  		inputPanel.setLayout( new BorderLayout() );
		inputPanel.add( BorderLayout.CENTER, inputField );
 		return inputPanel;
	}//createInputPanel
	
 
}
