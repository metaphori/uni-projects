/*
  */
package it.unibo.baseEnv.basicgui.input;


import it.unibo.is.interfaces.IActivity;
import it.unibo.is.interfaces.IOutputView;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionListener;

/**
 * @author Antonio Natali
 */
public class InputPanel extends Panel{
protected String logo;
protected String content;
protected String curCmd;

protected String cmd;
protected IOutputView outputView;
protected TextField inputField ;
protected  IActivity  action;
protected ActionListener  actionListener;

 	public InputPanel(String logo, String content, IOutputView outputView, IActivity  action){
 		this.logo 		= logo;
		this.action		= action;
		this.content	= content;
		this.outputView = outputView;
		actionListener = new InputHandler();
//		outputView.addOutput("InputPanel created");
		configure(   );
	}
 	
 	public InputPanel(String logo, String content, IOutputView outputView, IActivity action, String cmd){
 		this(logo, content, outputView, action);
 		
 		this.cmd = cmd;
 	}
 	
 	public String realdn(){
  		return curCmd;
 	}

 	protected void configure(   ){
		BorderLayout bdl = new BorderLayout();
 		Panel inpPanel = createInputPanel(logo,content );
		setLayout( bdl );
 		this.add(BorderLayout.SOUTH, inpPanel );
  	}//configure

 	
	protected Panel createInputPanel(String prompt, String content ){
		Panel inputPanel 	= new Panel();
		inputField   		= new TextField(20);
		inputField.setText( content );
		CmdButton goButton = new CmdButton( prompt, actionListener  );
		inputPanel.setLayout( new BorderLayout() );
		inputPanel.add( BorderLayout.CENTER, inputField );
		inputPanel.add( BorderLayout.EAST, goButton );
		return inputPanel;
	}//createInputPanel
	
/*
 * LOCAL CLASS	
 */
	protected class InputHandler implements ActionListener {
 		 public void actionPerformed(java.awt.event.ActionEvent evt){
 			curCmd	= inputField .getText();
// 			outputView.addOutput("InputHandler in InputPanel:"+curCmd);
 			action.execAction( cmd+"//"+curCmd ) ; 
		 }
	}	
}
