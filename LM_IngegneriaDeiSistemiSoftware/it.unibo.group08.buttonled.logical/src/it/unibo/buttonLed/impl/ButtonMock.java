package it.unibo.buttonled.impl;
import it.unibo.buttonLed.interfaces.IButton;
import it.unibo.buttonled.bridge.impl.DevButtonImpl;
 
public class ButtonMock extends DevButtonImpl implements IButton {
	public ButtonMock( String input ) throws Exception{
		super("",null);	//temporary name
		if( isADefaultRep(input) )
			throw new Exception("Not yet implemented");
		else //we assume the input as a name
			this.name = "button("+input+")";
	}
 	private boolean isADefaultRep(String rep){
		//rep should match button(NAME,BOOL)
		return rep.contains("button(") && rep.contains(")");
	}	
	@Override
	public boolean isPressed()  {
 		return isPressed;
	}
	@Override
	public void high() {
		isPressed = true;
		update(isPressed);
 	}
	@Override
	public void low() {
		isPressed = false;	
		update(isPressed);
	}
 
 }
