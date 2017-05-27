package it.unibo.baseEnv.basicgui;

import javax.accessibility.Accessible;
 

public interface ITextGui extends Accessible{

	/**
	*Get the current content of the input device.
	*@return the current text.
	*/
	public String readln();

	/**
	*Set a content in the input device.
	*@param msg the text to be set as current input.
	*/
	public void setText( String msg );

	/**
	*Set the backgrounf color of the input device.
	*@param color the color to be set.
	*/
	public void setBgColor( java.awt.Color color);

	/**
	*Set the font of the input device.
	*@param font the font to be set.
	*/
	public void setTheFont( java.awt.Font font );

}