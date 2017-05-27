package it.unibo.ccc.gui;
import it.unibo.ccc.domain.SysKb;
import it.unibo.ccc.domain.SysKb.SpeedFormat;
import it.unibo.contaKm.domain.Display;
import it.unibo.contaKm.view.DisplayGui;
import it.unibo.is.interfaces.IBasicEnvAwt;
import it.unibo.is.interfaces.IOutputView;

public class DisplayWithPanel extends Display {
	private IOutputView out;
	private SpeedFormat formato;
	
	public DisplayWithPanel(IBasicEnvAwt env){
		super(env);
		out = new DisplayGui(env);
		this.formato = SysKb.SpeedBaseFormat;
	}
	
	public DisplayWithPanel(IBasicEnvAwt env, SpeedFormat format){
		this(env);
		this.formato = format;
	}
	
	public void display(){
		String toShow = adaptValue(curVal);
		out.addOutput(toShow);
	}
	
	public String adaptValue(String val){
		double speed = Double.parseDouble(val);
		double speedval = SysKb.FromFormatTo(SysKb.SpeedBaseFormat, formato, speed);
		return new String(""+ speedval + SysKb.SpeedFormatString(formato));
	}
	
	
}
