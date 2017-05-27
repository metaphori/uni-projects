package it.unibo.ccc.gui;

import java.awt.event.ActionListener;

import it.unibo.baseEnv.basicgui.input.CmdPanel;
import it.unibo.ccc.domain.SysKb;
import it.unibo.is.interfaces.IActivity;

public class ChiefCmdPanel extends CmdPanel {

	public ChiefCmdPanel(String logo, String[] cmdNames, IActivity activity) {
		super( logo, cmdNames, activity  );
	}
	
 	@Override
 	protected void setActionListener( ){
 		actionListener = new MyInputHandler();	
 	}
 	 	
	/*
	 * LOCAL CLASS	
	 */
	protected class MyInputHandler implements ActionListener {
		 public void actionPerformed(java.awt.event.ActionEvent evt){
			 String curCmd = evt.getActionCommand();
//			 System.out.println("*** " + curCmd);
			 activity.execAction( "cmd//"+curCmd ) ; 
		 }
	}	
	
}
