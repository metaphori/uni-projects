package it.unibo.button.bridge.impl;
import java.awt.Color;

import it.unibo.baseEnv.basicFrame.EnvFrame;
import it.unibo.buttonled.bridge.impl.DevButtonImpl;
import it.unibo.domain.interfaces.IDevButtonStdinGui;
import it.unibo.is.interfaces.IBasicEnvAwt;
import it.unibo.is.interfaces.IBasicUniboEnv;

public class DevButtonStdinGui extends DevButtonImpl implements IDevButtonStdinGui {
	protected boolean pressed = false;

	public DevButtonStdinGui(String name,IBasicEnvAwt env) {
		super(name,env);
		println("STARTS wirh gui ");
		new InputGuiReadThread(env, this).start();
	}
	/*
	 * Main (rapid check)
	 */
	public static void main(String args[]) throws Exception {
		IBasicEnvAwt env = new EnvFrame("DevButtonStdinGui", Color.lightGray, Color.BLACK);
		env.init();
 		env.addInputPanel();
		new DevButtonStdinGui("b0",env);
	}
/*
* InputGuiReadThread
*/
private class InputGuiReadThread extends Thread {
protected IBasicUniboEnv env;
protected DevButtonStdinGui dev;
protected boolean running = true;
protected int dt = 5000;
protected String curS = "";

		public InputGuiReadThread(IBasicUniboEnv env, DevButtonStdinGui dev) {
			this.env = env;
			this.dev = dev;
		}
		public void run() {
			try {
				while (running) {
					env.println("PLEASE write 0 aor 1. I'm working in polling mode dt (msec)="
							+ dt);
					String inpS = env.readln(); // NON Blocking
					if (inpS.length() > 0)
						elab(inpS);
					// POLLING
					Thread.sleep(dt);
				}
			} catch (Exception e) {
				dev.update(false);
			}
		}// run

		protected void elab(String inpS) {
			if (curS.equals(inpS)) {
				// env.println("as before ... ");
				return;
			}
			for( int i=0; i<inpS.length(); i++ ){
 				if( inpS.charAt(i) == '1' ){
 					env.println("InputGuiReadThread update true "  );
					dev.update(true);
				}else if( inpS.charAt(i) == '0' ){
 					env.println("InputGuiReadThread update false "  );
					dev.update(false);
				}
			}
			curS = inpS;
		}
	}
}
