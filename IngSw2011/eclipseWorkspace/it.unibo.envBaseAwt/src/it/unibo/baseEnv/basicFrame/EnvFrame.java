/*
 * Created on 13-feb-2005
  */
package it.unibo.baseEnv.basicFrame;

 
import it.unibo.baseEnv.basicgui.input.CmdPanel;
import it.unibo.baseEnv.basicgui.input.InputPanel;
import it.unibo.baseEnv.basicgui.input.SimpleInputPanel;
import it.unibo.baseEnv.basicgui.output.OutDevPanel;
import it.unibo.is.interfaces.IActivity;
import it.unibo.is.interfaces.IBasicEnvAwt;
import it.unibo.is.interfaces.IContactSystem;
import it.unibo.is.interfaces.IOutputView;
import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.WindowEvent;

 
/**
@author Antonio Natali
*/
public class EnvFrame extends Frame implements IBasicEnvAwt {
 	private static int numOfFrames = 0;
	protected  OutDevPanel outDev;		//dispositivo virtuale di uscita
	protected  SimpleInputPanel simpleInputPanel;
	protected  InputPanel inputPanel;
// 	protected java.awt.Container internalPanel = new Panel();
 	protected String logo;						//denota il nome della applicazione
//  	protected Frame frame = null;
 	protected Label statusBar;
// 	protected java.applet.AppletContext appletCtx;
 	protected boolean withFrame = true;
  	protected Panel ioDevPanel = null;
 	protected Panel cmdPanel;
 	protected boolean isStandAlone = true;
	protected String statusS ="Default logo";
	protected IContactSystem contactSystem = null;
	protected int nRows = 0;
	private int nPanel = 0;
	private Component[]   curPanels = new Component[5];


	/**
	* This constructor is used by the broswer and <b>must</b> be defined.
	*/
	public EnvFrame(String logo, IContactSystem contactSystem){
		this(logo);
		this.contactSystem = contactSystem;
	}
  
	public EnvFrame(){
		this("envFrane");
	}

	/**
	* This constructor shoud be used by a standalone application.
	*/
	public EnvFrame(String logo){
		this(logo,"Basic environment");
	}

	public EnvFrame(String logo, String statusS){
		this.logo=logo;
		this.statusS = statusS;
	}

    public IOutputView getOutput(){
    	return outDev;
    }
    
    public String readln(){
    	if( inputPanel != null ) 
    		return inputPanel.realdn() ;
    	else return "noInputDevice";
    }
    
 	public  void println( String msg ){
		if( outDev != null ) outDev.println( msg );
		else System.out.println( msg );
	}

    public  IOutputView getOutputView(){
    	return outDev;
    }

	/**
	*	Entry point.
	*/
	public void init(){
	  isStandAlone = true;
      this.setTitle(logo);
	  creaEntita_local();
	  configuraIO_local();
	  setEnvVisible(true);
	  //println("This is the output device area");
 	  setFrame();
 	}//init

 	public void close(){
		System.out.println("EnvFrame " + logo + " close");
		System.exit(0);
//		try{
//			if( contactSystem != null ) contactSystem.terminate();
//		}catch(Exception e){
//			e.printStackTrace();
//		} 
	}
	

	/**
	 * Return true if it is a standalone application.
	 * @return true if it is a standalone application; false otherwise.
	 */
	public  boolean isStandAlone(){
		return isStandAlone;
	}

	/**
	*  Create application entities.
	*/
	protected void creaEntita_local(){
//		internalPanel = getContentPane();
//		add(internalPanel);

		//Dispositivo virtuale di uscita
		outDev = new OutDevPanel(20,60);

		statusBar = new Label();
		writeOnStatusBar(statusS,14);
}

	/**
	*  Performs I/O configuration.
	*/
	protected void configuraIO_local(){

		cmdPanel = new Panel();
//	  	cmdPanel.setLayout(new BorderLayout() );

		ioDevPanel = new Panel();
		ioDevPanel.setLayout(new BorderLayout() );
 		ioDevPanel.add( BorderLayout.NORTH, cmdPanel );
		ioDevPanel.add( BorderLayout.SOUTH, outDev );

		//Fissa il layout del componente
//		internalPanel.setLayout(new BorderLayout() );
         //inserisci il dispositivo virtuale di uscita
		this.add(BorderLayout.NORTH,ioDevPanel);
		this.add(BorderLayout.SOUTH,statusBar);

//		inputPanel = new SimpleInputPanel( );
//        addPanel( inputPanel );
		validate();
 	}//configuraIO


	public void addInputPanel(){
		simpleInputPanel = new SimpleInputPanel( );
        addPanel( simpleInputPanel );		
		validate();
	}
 	/*
	* Gets the maximum number of panels that can be inserted in the GUI.
	*@return the maximum number of panels that can be inserted
	*/
	public int getMaxNumOfPanels(){
		return 5;
	}

	/*
	* Gets the current number of panels.
	*@return the maximum number of panels that can be inserted
	*/
	public int getNumOfPanels(){
		return nPanel;
	}

	public void addCmdPanel(String name, String[] commands, IActivity activity){
    CmdPanel applPanel = new CmdPanel(name, commands, activity);
 		addPanel( applPanel );
	}

	/**
	*  Remove the last panel inserted.
	*/
	public void removeLastPanel(){
		if( nPanel > 0 ) removePanel( curPanels[nPanel-1] );
	}

	/**
	*  Add a new panel in the current free position.
  	* @param p : the panel to be added
	*/
    public void addPanel(Panel p ) {
 		if( cmdPanel != null ) ioDevPanel.remove( cmdPanel );
// 		System.out.println("*** addPanel");
        if( setPanel(p,nPanel) ){
         	curPanels[nPanel] = p;
			nPanel++;
		}
        cmdPanel.validate();
 		ioDevPanel.add( cmdPanel );
 		validate();
     }
    
    public void addPanel(Component p ) {
 		if( cmdPanel != null ) ioDevPanel.remove( cmdPanel );
        if( setPanel(p,nPanel) ){
         	curPanels[nPanel] = p;
			nPanel++;
		}
        cmdPanel.validate();
 		ioDevPanel.add( cmdPanel );
     }

    public void addComponent(Component p ) {
 		if( cmdPanel != null ) ioDevPanel.remove( cmdPanel );
        if( setPanel(p,nPanel) ){
         	curPanels[nPanel] = p;
			nPanel++;
		}
        cmdPanel.validate();
 		ioDevPanel.add( cmdPanel );
     }

    
    public boolean setPanel(Component p, int nPanel ) {
        if ( nPanel == 0 ){  cmdPanel.add(BorderLayout.NORTH,p); return true;}
        if ( nPanel == 1 ){  cmdPanel.add(BorderLayout.CENTER,p); return true;}
        if ( nPanel == 2 ){  cmdPanel.add(BorderLayout.WEST,p); return true;}
        if ( nPanel == 3 ){  cmdPanel.add(BorderLayout.EAST,p); return true;}
        if ( nPanel == 4 ){  cmdPanel.add(BorderLayout.SOUTH,p); return true;}
        return false;
    }

    public void removePanel( Component p ) {
   	int i;
         for( i = 0; i<nPanel; i++ ){
			if( curPanels[ i ] == p ) break;
		}

		if( i == nPanel ) return;

   		//println(getName() +" removing panel  of index "  + i + " su " + nPanel ) ;
        cmdPanel.remove( curPanels[ i ] );

        for( int k = i+1; k < nPanel; k++ ){
   	        //println(getName() +" shifting panel  "  + k ) ;
			cmdPanel.remove( curPanels[k] );
			curPanels[k-1] = curPanels[k];
			setPanel(curPanels[k-1],k-1);
		}

        nPanel--;
        cmdPanel.validate();
		ioDevPanel.add( cmdPanel );
     }

	@Override
	public void removePanel( Panel p) {
		removePanel((Component)p);
	}


	/**
	* Write a message on the window status bar.
	*@param s the message.
	*@param size the font size
	*/
	public void writeOnStatusBar( String s, int size){
		statusBar.setFont( new java.awt.Font( "Arial", java.awt.Font.BOLD, size));
		statusBar.setText(s);
	}

	/**
	* Returns the name of the window.
	* @return the name of the window.
	*/
	public String  getName(){
		return logo;
	}

	/**
	* Set / reset the visibility of the environment GUI.
	*@param b the flag.
	*/
	public void setEnvVisible( boolean b ){
 		setVisible( b );
 	}


	//==============================================
	/**
	* Set a new frame for the applet.
	*/
	private void setFrame(){
		int i;
		if( !withFrame ) return;
		createFrame();
	}//setFrame

	/**
	* Create a new frame for the applet.
	*/
	private void createFrame(){
 		this.setSize(850,600);
 		this.addWindowListener( new MyWindowListener(this) );
 		this.setVisible(true);
	}
 
    protected class MyWindowListener implements java.awt.event.WindowListener{
    	private EnvFrame frame;
    	public MyWindowListener( EnvFrame frame){
    		this.frame = frame;
    	}
		@Override
		public void windowActivated(WindowEvent arg0) {
			System.out.println("***  " + arg0);
		}

		@Override
		public void windowClosed(WindowEvent arg0) {
			System.out.println("*** close");
			frame.close();		
		}

		@Override
		public void windowClosing(WindowEvent arg0) {
		}

		@Override
		public void windowDeactivated(WindowEvent arg0) {
		}

		@Override
		public void windowDeiconified(WindowEvent arg0) {
		}

		@Override
		public void windowIconified(WindowEvent arg0) {
		}

		@Override
		public void windowOpened(WindowEvent arg0) {
 		}
    	
    }


 
	public void processEvent( AWTEvent e) {
//		System.out.println("*** e" + e);
		if( e.toString().contains("WINDOW_CLOSING")) System.exit(0);
			
	}


public static void main(String[] args){
	EnvFrame env = new EnvFrame( "ContaKm"  );
	env.init();
	env.println("hello world");
}

}


