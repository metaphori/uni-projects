
package it.unibo.baseEnv.basicgui.input;

import java.awt.Button;
import java.awt.event.ActionListener;



public class CmdButton extends Button {
	protected ActionListener  actionListener;
	protected String  command;


	 public CmdButton(  String command, ActionListener  actionListener  ){
			this.actionListener	= actionListener;
 			this.command 		= command;
			setActionCommand(command);
 			setLabel( command );
			addActionListener(actionListener);
		}


	public String getName(){
		return command;
	}

}
