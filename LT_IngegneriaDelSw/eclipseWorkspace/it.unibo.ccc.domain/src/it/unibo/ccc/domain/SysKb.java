package it.unibo.ccc.domain;

public class SysKb {
	
	public final static double SpeedMaxValue = 5000; // 1000 km/h

	public static final int DT = 2000; // 2 s
	public static final int R = 5; // 5 attempts
	public static final int DD = 100; // 100 m
	
	public final static int ACCELERATED_SIMULATION = 100;
	
	public static final String command = "cmd";
	public static final String working = "working";
	public static final String notWorking = "notWorking";
	
	public static final String incSpeed = "incSpeed";	// for vehicle simulation
	public static final String decSpeed = "decSpeed";   // for vehicle simulation

	public static final String start = "start";
	public static final String stop = "stop";
	public static final String setSpeed = "setSpeed";
	public static final String register = "register";
	
	public static String hostName = null;
	public static int serverPort = 8888;
	
	public static enum SpeedFormat { KmPerS, KmPerH, MPerS };
	public static SpeedFormat SpeedChiefFormat = SpeedFormat.KmPerS;
	public static SpeedFormat SpeedBaseFormat = SpeedFormat.KmPerH;
	
	public static double FromFormatTo( SpeedFormat inf, SpeedFormat outf, double value){
		switch(inf){
		case MPerS:
			switch(outf){
				case MPerS: return value;
				case KmPerS: return (value/1000);
				case KmPerH: return (3600 * value / 1000);
			}
		case KmPerS:
			switch(outf){
				case KmPerS: return value;
				case KmPerH: return value*3600;
				case MPerS: return value*1000;
			}
		case KmPerH:
			switch(outf){
				case KmPerH: return value;
				case KmPerS: return value/3600;
				case MPerS: return (1000*value/3600);
			}
		}
		
		return -1;
	}

	
	public static boolean IsSpeedOk(double speed){
		if(speed<0 || speed>SpeedMaxValue) return false; 
		// TODO: quale intervallo di velocita'
		return true;
	}	

	public static double SpeedTolerance = 0.0005;
	public static enum SpeedCompare { IsLower, IsEqual, IsGreater };
	
	public static SpeedCompare CompareSpeed(double refspeed, double compspeed){
		double diff = refspeed - compspeed;
		if( diff <= -SpeedTolerance ) return SpeedCompare.IsLower;
		else if( diff <= SpeedTolerance ) return SpeedCompare.IsEqual;
		else return SpeedCompare.IsGreater;
	}
	
	public static String SpeedFormatString(SpeedFormat f){
		switch(f){
			case KmPerS: return "Km/sec";
			case KmPerH: return "Km/h";
			case MPerS: return "m/sec";
			default: return " <undefined format> ";
		}
	}
	
	
	public static long GiveMillisDelay(SpeedFormat f, double v){
		double valkmh = FromFormatTo(f, SpeedFormat.KmPerH, v);
		long l =  (long)(3600/valkmh*1000);
		return l / ACCELERATED_SIMULATION;
	}
	
	public static long GiveMillisDelayFor(double v, int metres ){
		long l = 	(long) (1000 * metres / SysKb.FromFormatTo(SysKb.SpeedBaseFormat, 
				SysKb.SpeedFormat.MPerS, v));
		return l / ACCELERATED_SIMULATION;
	}
	
	public static String getCmdContent( String cmd ){
		if( cmd.indexOf("//")==-1)
			return "";
		
		return cmd.substring(cmd.indexOf("//")+2);
	}
	public static String getCmdScheme( String cmd ){
		if( cmd.indexOf("//")==-1)
			return "";
		
		return cmd.substring(0,cmd.indexOf("//"));
	}	
	
}
