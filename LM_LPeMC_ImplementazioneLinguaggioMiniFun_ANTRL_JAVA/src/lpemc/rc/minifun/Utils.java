package lpemc.rc.minifun;

import java.util.ArrayList;

import lpemc.rc.minifun.ast.ListConsNode;
import lpemc.rc.minifun.ast.ListEmptyNode;
import lpemc.rc.minifun.ast.Node;

public class Utils {
	public static final String NONE_LEVEL = "NONE";
	public static final String DEBUG_LEVEL = "DEBUG";
	public static final String INFO_LEVEL = "INFO";
	
	public static final int SCOPE_VM = 1<<0;
	public static final int SCOPE_CODEGEN = 1<<1;
	public static final int SCOPE_TYPECHECK = 1<<2;
	public static final String NULL_POINTER = "-1";
	
	public static int SCOPE = SCOPE_VM | SCOPE_CODEGEN | SCOPE_TYPECHECK;
	public static String LEVEL = NONE_LEVEL;
	
	private static long inc = 0;
	
	private static String functionCode = "";
	public static void addFunctionCode(String code){
		functionCode += code;
	}
	public static String getFunctionCode(){
		return functionCode;
	}

	public static String genLabel(String str) {
		return str + (Utils.inc++);
	}
	
	public static void log(String str, String level){
		if(level.equals(DEBUG_LEVEL)){
			System.out.println("[DEBUG] " + str);
		} else if(level.equals(INFO_LEVEL)){
			System.out.println("[INFO] " + str);
		}
		System.out.flush();
	}
	
	public static void log(String str, int scope){
		if( (scope & SCOPE)==scope ){
			log(toScopeString(scope)+ " " +str, DEBUG_LEVEL);
		}
	}
	
	public static String toScopeString(int scope){
		switch(scope){
			case SCOPE_VM: return "VM";
			case SCOPE_CODEGEN: return "CODEGEN";
			case SCOPE_TYPECHECK: return "TYPECHECK";
			default: return "";
		}
	}
	
	public static int countOccurrences(String substr, String str){
		int occ = 0;
		int i = -1;
		while( (i=str.indexOf(substr,i+1))!=-1 ){
			occ++;
		}
		return occ;
	}
	
	public static void log(String str){
		log(str, LEVEL);
	}
	public static String DowncaseFirstLetter(String str) {
		if(str=="") return "";
		return str.substring(0, 1).toLowerCase()+str.substring(1);
	}
	public static String capitalize(String str) {
		if(str=="") return "";
		return str.substring(0, 1).toUpperCase()+str.substring(1);
	}
	
	public static class LogBuffer {
		protected String buffer = "";
		
		public void log(String str){ buffer += str + "\n"; }
		
		public String getText(){ return buffer; }
		
		public void clear(){ buffer=""; }
	}

	public static Node CreateListFromLiteral(ArrayList<Node> exps) {
		ListConsNode lcn;
		ListEmptyNode len = new ListEmptyNode();
		if(exps.size()==0){ return len; }
		
		lcn = new ListConsNode(exps.get(exps.size()-1), len);
		
		for(int i = exps.size()-2; i>=0; i--){
			lcn = new ListConsNode(exps.get(i), lcn);
		}
		return lcn;
	}
	
}
