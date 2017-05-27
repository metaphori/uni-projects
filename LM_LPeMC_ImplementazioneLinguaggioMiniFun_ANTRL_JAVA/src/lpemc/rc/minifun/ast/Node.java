package lpemc.rc.minifun.ast;

import java.util.HashMap;

import lpemc.rc.minifun.TypeSystem;
import lpemc.rc.minifun.TypeSystem.TMappings;
import lpemc.rc.minifun.Utils;

public abstract class Node {
  /** static members **/
  public static int NOTYPE = -99;
  
  /** instance members */
  protected boolean typeChecked = false;
  protected String typecheckedNodeType = TypeSystem.Terror;
  protected Utils.LogBuffer log = new Utils.LogBuffer();
  protected boolean generic = false;  
  
  /** ABSTRACT METHODS **/
  public abstract String getStringRepr();
  
  public abstract String genCode(); /* code gen for stack-based VM */
  
  /** CONCRETE METHODS */
  public String typeCheck(){
	  return typeCheck(null);
  }
  
  public String typeCheck(TMappings mappings){
	  String tcl = typeCheckLogic(mappings);
	  if(tcl.equals(TypeSystem.Terror)){
		  Utils.log("The following node does NOT typecheck:");
		  Utils.log(this.getStringRepr());
		  this.onError();
	  }
	  
	  return tcl;
  }
  
  public String typeCheckLogic(TMappings mappings){ 
	return TypeSystem.Terror; // by default  
  }
  
  public void onError(){
	  //System.exit(1);
	  Utils.log(log.getText());
  }
    
	  
}