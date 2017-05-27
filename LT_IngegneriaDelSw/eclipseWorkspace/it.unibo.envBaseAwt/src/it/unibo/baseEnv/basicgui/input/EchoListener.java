package it.unibo.baseEnv.basicgui.input;

import it.unibo.is.interfaces.IBasicEnv;


/**
* A listener of button events that writes the event name (action command) on
* the virtual output device of the environment <tt>env</tt> given in the
* construction phase.
* @author <em>ANatali</em>, DEIS, University of Bologna
*/
public class EchoListener implements java.awt.event.ActionListener{
protected IBasicEnv env;


	public EchoListener( IBasicEnv env ){
 			this.env = env;
     }

/**
* Reacts to a button click by echoing on the virtual output device
* the name of the button.
*/
 public void actionPerformed(java.awt.event.ActionEvent evt){
	    String arg=evt.getActionCommand();
 		env.println(arg);
 }

}//ButtonListener