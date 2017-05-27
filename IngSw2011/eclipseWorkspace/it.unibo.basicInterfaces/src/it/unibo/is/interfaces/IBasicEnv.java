package it.unibo.is.interfaces;
 
 
import it.unibo.is.interfaces.IOutputView;

import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * <font color="#800040">
*	Defines the set of features provided by a basic environment.
 * </font>
 * @author 		 <em>ANatali</em>, DEIS, University of Bologna.
*/
public interface IBasicEnv {
	/**
	*	Entry point: initializes the environment.
	*/
	public void init();

	/**
	 * Write on the status bar
	 * @param s
	 * @param size
	 */
	public void writeOnStatusBar( String s, int size);

	/**
	 * Predicato: true se l'applicazione non è entro un browser
	 * @return true in case of a standalone application 
	 */
	public boolean isStandAlone();
	
	/**
	*	Add a panel in the environment.
	*/
	public void addInputPanel( );
	public void addPanel( JPanel  p );
	public void addPanel( JComponent  p );
	/**
	*	Builds a command panel (of class CmdPanel) and adds it to the environment.
	*/
	public void addCmdPanel(String name, String[] commands, IActivity activity);
	/**
	*	Remove a panel form the environment.
	*/
	public void removePanel( javax.swing.JPanel p);
	/**
	*	Return the output virtual device.
	*/
	public IOutputView getOutputView();
	/**
	*	Read a string from the virtual input device of class <tt>SimpleInputPanel</tt>.
	*/
	public  String readln(  );
	
	/**
	*	Print a string on the virtual output device of class <tt>OutDevPanel</tt>.
	*/
	public void println( String msg );	
	/**
	 * 
	 * @return the numer of panels
	 */
	public int getNumOfPanels();
	/**
	*	Makes the GUI environment visibile.
	*/
	public void setEnvVisible( boolean b );
	
	public void close(   );
 }