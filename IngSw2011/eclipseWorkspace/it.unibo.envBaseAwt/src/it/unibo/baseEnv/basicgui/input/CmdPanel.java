/*
  */
package it.unibo.baseEnv.basicgui.input;

import it.unibo.is.interfaces.IActivity;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionListener;

public class CmdPanel extends Panel{
 protected String logo;
 protected String[] cmdNames;
 protected  ActionListener  actionListener ;
 protected IActivity activity;

 	public CmdPanel(String logo, String[] cmdNames, IActivity activity ){
  		this.logo 			= logo;
		this.cmdNames 		= cmdNames;
		this.activity		= activity;
		setActionListener( );
 		configure(   ); 		
 	}
  	
 	/*
 	 * Template method
 	 */
 	protected void setActionListener( ){
 		System.out.println("DefaultInputHandler set");
		actionListener = new DefaultInputHandler();	
 	}
 	
 	protected void configure(   ){
		BorderLayout bdl = new BorderLayout();
		Panel cmdPanel = createCmdPanel(  );
 		setLayout( bdl );
		this.add(BorderLayout.NORTH, cmdPanel );
   	}//configure

	protected Panel createCmdPanel(  ){
	Panel cmdPanel = new Panel();
	CmdButton cmdButton ;
		int n = cmdNames.length;
		GridLayout layout = new GridLayout( 1,n ) ;
		cmdPanel.setLayout( layout );
	  		for( int i=0; i<n; i++ ){
				cmdButton 	 = new CmdButton( cmdNames[i], actionListener);
				cmdPanel.add( cmdNames[i],cmdButton );
	 		}
	  	return cmdPanel;
	 }//createCmdPanel

	/*
	 * LOCAL CLASS	
	 */	
	private class DefaultInputHandler implements ActionListener {
 		public void actionPerformed(java.awt.event.ActionEvent evt){
			 String curCmd = evt.getActionCommand();
//			 System.out.println("*** " + curCmd);
			 activity.execAction( "cmd//"+curCmd ) ;//new Intent(curCmd) );
		 }
	}	
	
 	
}
