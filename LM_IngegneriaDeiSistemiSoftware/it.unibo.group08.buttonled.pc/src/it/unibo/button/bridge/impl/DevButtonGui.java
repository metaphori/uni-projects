package it.unibo.button.bridge.impl;
import java.awt.Color;
import java.awt.Panel;

import it.unibo.baseEnv.basicFrame.EnvFrame;
import it.unibo.buttonled.bridge.impl.DevButtonImpl;
import it.unibo.buttonled.devices.SysKbBridge;
import it.unibo.domain.interfaces.IDevButtonGui;
import it.unibo.is.interfaces.IBasicEnvAwt;
import it.unibo.is.interfaces.IBasicUniboEnv;

public class DevButtonGui extends DevButtonImpl implements IDevButtonGui {
protected String[] cmd;
protected Panel cmdPanel;
	public DevButtonGui(String name, IBasicEnvAwt env, String[] cmd) {
		super( name, env);
		this.cmd = cmd;
		configure();
	}
	protected void configure() {
		cmdPanel = ((IBasicEnvAwt)env).addCmdPanel("", cmd, this);
	}
	@Override
	public Panel getPanel(){
		return cmdPanel;
	}
	/*
	 * --------------------------------------
	 * Main (rapid check)
	 * --------------------------------------
	 */
	public static void main(String args[]) throws Exception {
		IBasicEnvAwt env = new EnvFrame("DevButtonGui", Color.white, Color.BLACK);
		env.init();
 		new DevButtonGui("b0", env , new String[] {
				SysKbBridge.repLow, SysKbBridge.repHigh  });
	}

}
