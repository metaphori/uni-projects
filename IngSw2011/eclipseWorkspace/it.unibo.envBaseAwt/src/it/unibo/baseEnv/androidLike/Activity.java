package it.unibo.baseEnv.androidLike;

import it.unibo.is.interfaces.IActivity;
import it.unibo.is.interfaces.IBasicEnv;
import it.unibo.is.interfaces.IIntent;

public abstract class Activity implements IActivity{
	protected IBasicEnv env;
	
	public Activity( ){
//		onCreate( new Bundle() );
 	}
	
//	public Activity(IBasicEnv context){
//		this.env = context;
// 	}

	public void setEnv(IBasicEnv env) throws Exception{
 		this.env = env;		
//		initGui();
//		initSupport(view);		
	}

	protected abstract void onCreate(Bundle savedInstanceState);
	public abstract void execAction( );
	public abstract void execAction( String cmd );
	public abstract void execAction(IIntent input);
}
