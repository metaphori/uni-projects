package it.unibo.button.bridge.impl;
import java.io.IOException;

import it.unibo.buttonled.bridge.impl.DevButtonImpl;
import it.unibo.domain.interfaces.IConstants;
import it.unibo.domain.interfaces.IDevButtonStdin;


/*
 * Look at the input device (with a synch read)
 * and then update the observers
 */
public class DevButtonStdin extends DevButtonImpl implements IDevButtonStdin {
 
	public DevButtonStdin(String name) {
		super(name,null);
		println("STARTS on System.in");
		new StdinReadThread(this).start();
	}

 
/*
* Main (rapid check)	
*/
		public static void main(String args[]) throws Exception {
 	 		new DevButtonStdin("b0" );
	 	}
	
/*
 * StdinReadThread
 */
	private class StdinReadThread extends Thread {
		protected DevButtonStdin dev;
		protected boolean running = true;

		public StdinReadThread(DevButtonStdin dev) {
			this.dev = dev;
		}

		public void run() {
			try {
				while (running) {
					System.out.print("DevButtonStdin please PRESS "); // 97 ...
																		// 13 10
					lookAtPressed();
					dev.update(true);
					if (!running)
						break;
					Thread.sleep(IConstants.PRESSTIME);
					// System.out.print("BUTTON returns to false " );
					dev.update(false);
				}
			} catch (Exception e) {
				dev.update(false);
			}
		}// run

		protected void lookAtPressed() throws IOException {
			int n = System.in.read();
			// consume until the end of line
			while (n != 10) {
				n = System.in.read();
			}
		}
	}

}
