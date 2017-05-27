package lpemc.rc.minifun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import antlr.collections.Stack;

import lpemc.rc.minifun.TypeSystem.TMappings;
import lpemc.rc.minifun.ast.ConcreteTypeNode;
import lpemc.rc.minifun.ast.FunArgNode;
import lpemc.rc.minifun.ast.GenericTypeNode;
import lpemc.rc.minifun.ast.Node;
import lpemc.rc.minifun.ast.TypeNode;

/**
 * This class centralizes some logic of the type system for MiniFun
 *  and contains various utility method that can be used by the AST nodes
 *  to easily perform their typechecking logic.
 *  
 * @author Roberto Casadei
 *
 */
public class TypeSystem {
	
	/* types */
	public static final String Tbool 		= "bool";
	public static final String Tint  		= "int";
	public static final String Terror  	= "error";
	public static final String Tnotype	= "notype";
	public static final String TlistOfInt = "listOfInt";
	public static final String TlistOfBool = "listOfBool";
	public static final String TlistOfAny = "listOfAny";
	public static final String Tgeneric = "genericType";
	
	public static final String TRUE_REPR = "1";
	public static final String FALSE_REPR = "0";
	public static final String NULLPOINTER = "-1";
	
	/* generic declarations cannot typecheck until their type params are instantiated */
	public static final String GEN_DECL = "genericDeclaration";
	
	/*********************/
	/** utility methods **/
	/*********************/
	
	public static boolean IsPrimitiveType(String s){
		if(	s.equals(Tbool) ||
			s.equals(Tint))
			return true;
		return false;
	}
	
	/* The following methods are useful for checking type compatibility */
	
	public static boolean areCompatible(Node n1, Node n2){
		return areCompatible(n1.typeCheck(), n2.typeCheck());
	}
	
	public static boolean areCompatible(String t1, String t2){
		return isCompatible(t1,t2) && isCompatible(t2,t1);
	}
	
	public static boolean isCompatible(Node n1, Node n2){
		return isCompatible(n1.typeCheck(), n2.typeCheck());
	}
	
	public static boolean isCompatible(String t1, String t2){
		boolean compatible = true;
		
		if(t1.equals(Terror))
			compatible = false;
		
		if(t1.equals(TlistOfBool) && (!t2.equals(TlistOfBool) && !t2.equals(TlistOfAny)) ||
		   t1.equals(TlistOfInt) && (!t2.equals(TlistOfInt) && !t2.equals(TlistOfAny)) ||
		   t1.equals(TlistOfAny) && (!isList(t2)) ||
		   isListOfLists(t1) && (!t2.equals(t1) && !t2.equals(TlistOfAny))){
			compatible = false;
		}
		
		if( (t1.equals(Tint) && !t2.equals(Tint) ) ||
		    (t1.equals(Tbool) && !t2.equals(Tbool) ) )
			compatible = false;
		
		if(IsFunctionType(t1) && !FunctionTypesCompatible(t1,t2)){
			Utils.log("ERROR: Function types are different " + t1 + " and " + t2);
			compatible = false;
		}
		
		if(compatible==true){ 
			return true; 
		}
		else {
			Utils.log("The two types are not compatible: " + t1 + " and " + t2);
			return false;
		}
	}
	
	/*******************************************************************/
	/* Utility methods useful for function types, i.e. types of the form
	 * 		(ArgT1, ..., ArgTn) -> RetType
	 */
	
	public static boolean FunctionTypesCompatible(String s1, String s2){
		// TODO: better checks
		return s1.equals(s2);
	}
	
	/**
	 * Checks if the string parameter is the string representation of a function type.
	 */
	public static boolean IsFunctionType(String s){
		// TODO: better check
		return s.contains("->");
	}
	
	/**
	 * @param s A string such as "(...arg types...)->retType
	 * @return Terror if the string is malformed or retType instead.
	 */
	public static String GetFunctionResultType(String s) {
		// function types:  (arg1T, ..., argNT)->retT
		
		int lpars = 0;
		int rpars = 0;
		int last_rpar_index = -1;
		BufferedReader br = new BufferedReader(new StringReader(s));
		char c = 'x';
		int mychar = -1;
		int i = 0;
		try {
			while( (mychar=br.read())!=-1){
				c = (char)mychar;
				
				if(c=='('){
					lpars++;
				} else if(c==')'){
					rpars++;
					last_rpar_index = i;
				} else{
					i++;
					continue;
				}
				i++;
				
				if(lpars==rpars){
					br.close();
					return s.substring(last_rpar_index+1+2); // skip '->' after last rpar ')'
				}
			}
		} catch (IOException e) {
			return Terror;
		}
		return Terror;
		
	}

	public static String GetFunctionTypeRepr(ArrayList<TypeNode> typesOfArgs,
			TypeNode resultType, TMappings mappings) {
		ArrayList<String> typeReprOfArgs = new ArrayList<String>();
		int nargs = typesOfArgs.size();
		for(TypeNode tn : typesOfArgs){
				typeReprOfArgs.add(tn.typeCheck(mappings));
		}
		String resultTypeRepr = resultType.typeCheck(mappings);
		
		return GetFunctionTypeRepr(typeReprOfArgs, resultTypeRepr);
	}	
	
	public static String GetFunctionTypeRepr(ArrayList<String> typesOfArgs, String resultType){
		String ts = "(";
		int nargs = typesOfArgs.size();
		if(nargs>0){
			for(int i=0; i<nargs; i++){
				ts+=(i==0?"":",")+typesOfArgs.get(i);
			}
		}
		ts += ")->" + resultType;
		
		return ts;		
	}
	
	/*************************************************************/
	/* Utility methods for dealing with lists and lists of lists */
	
	public static boolean isList(String str){
		return str.startsWith("listOf");
	}
	public static boolean isListOfLists(String str){
		if(isList(str) && str.contains("ListOf")) return true;
		return false;
	}
	public static String CheckListType(Node head, Node tail, TMappings mappings) {
		String headT = head.typeCheck(mappings);
		String tailT = tail.typeCheck(mappings);
		
		if(IsPrimitiveType(headT) && (tailT.equals(ListOf(headT)) || tailT.equals(TlistOfAny)))
			return ListOf(headT);
		
		/*
		if( IsFunctionType(headT) && (tailT.equals(ListOf(headT)) || tailT.equals(TlistOfAny))){
			return ListOf(headT);
		}
		*/
		/*
		 * NOTA: per prevedere la possibilità di creare liste di riferimenti a funzioni,
		 * 		occorre modificare la generazione di codice per i nodi lista in modo 
		 * 		da gestire il fatto che i riferimenti a funzione occupano due celle (una 
		 * 		per l'indirizzo del codice della funzione e una per l'indirizzo dell'Access Link)
		 */
		
		
		if( headT.equals(TlistOfAny) && IsPrimitiveType(ListElemType(tailT))) return Terror;
		
		if( isList(headT) && isList(tailT) ){
			if(headT.equals(TlistOfAny) && isList(tailT)) return tailT;
			if(tailT.equals(TlistOfAny)) return ListOf(headT);
			
			int hlevels = ListOfListsLevels(headT);
			String hlastType = ListOfListsLastType(headT);
			int tlevels = ListOfListsLevels(tailT);
			String tlastType = ListOfListsLastType(tailT);
			if(hlevels==(tlevels-1) && hlastType.equals(tlastType))
				return ListOf(headT);
		}
		
		Utils.log("List type does not typecheck:\nHead type:" + headT + "\nTail type:"+tailT);
		
		return TypeSystem.Terror;
	}	
	public static String ListOf(String headT){
		return "listOf"+Utils.capitalize(headT);
	}
	public static String ListOfLists(int levels, TypeNode lastType){
		String retT = "listOf";
		for(int i=levels; i>0; i--){
			retT += "ListOf";
		}
		retT += lastType.typeCheck();
		return retT;
	}
	public static int ListOfListsLevels(String lol){
		return Utils.countOccurrences("ListOf", lol);
	}
	public static String ListOfListsLastType(String lol){
		int lastListOf = lol.lastIndexOf("ListOf");
		if(lastListOf==-1){
			return lol.substring("listOf".length());
		}else{
			return lol.substring(lastListOf+"ListOf".length());
		}
	}
	public static String ListElemType(String listType){
		if(listType.length()<("listOf".length())) return Terror;
		return Utils.DowncaseFirstLetter(listType.substring("listOf".length()));
	}

	/**
	 * @param genTypes: a list of types T1,...,Tn
	 * @return a string "<T1,...,Tn>"
	 */
	public static String TypesInBracketsRepr(ArrayList<? extends TypeNode> genTypes) {
		if(genTypes==null || genTypes.size()==0) return "";
		
		String typespec = "<";
		for(TypeNode tn : genTypes){
			typespec += tn.getStringRepr()+",";
		}
		typespec = typespec.substring(0, typespec.length()-1)+">";
		return typespec;
	}
	
	/**
	 * It represents a set of mappings, i.e. couples <GenericType, ConcreteType>
	 * 	which are used to perform the instantiations of generic types used in function declarations.
	 * NOTE: the concrete type may be temporarily null if a function reference uses a generic type that is 
	 * 	introduced by the parent definition and is not instantiated yet.
	 * 
	 * @author Roberto Casadei
	 *
	 */
	public static class TMappings extends java.util.HashMap<GenericTypeNode, ConcreteTypeNode> {
		public String toText(){
			String txt = "Mappings:";
			for(GenericTypeNode gtn : keySet()){
				if(get(gtn)!=null){
					txt += "\n"+gtn.getStringRepr() + " --> " + get(gtn).getStringRepr();
				} else{
					txt += "\n"+gtn.getStringRepr() + " --> null"; 
				}
			}
			return txt;
		}
	}

	/**
	 * It completes the old set of mappings with the mappings of the new one.
	 * 
	 * @param olds : previous set of mappings
	 * @param news : current set of mappings
	 * @return the updated set of mappings
	 */
	public static TMappings GenMappings(TMappings olds,
			TMappings news) {
		if(olds==null) return news;
		if(news==null) return olds;
		
		TMappings maps = (TMappings)olds.clone();
		for(GenericTypeNode gtn : news.keySet()){
			if(news.get(gtn)!=null)
				maps.put(gtn, news.get(gtn));
		}
		
		return maps;
	}
	
	/****************************/
	/**** Deprecated methods ****/

	public static int ARE_CONCRETE = 1<<0;
	public static int ARE_GENERIC = 1<<1;
	public static boolean AllTheseTypes(ArrayList<TypeNode> types, int check){
		for(TypeNode tn : types){
			if(check==ARE_CONCRETE && !(tn instanceof ConcreteTypeNode)) return false;
			if(check==ARE_GENERIC && !(tn instanceof GenericTypeNode)) return false;
 		}
		return true;
	}
	
	public static String nearestCommonType(Node n1, Node n2){
		return nearestCommonType(n1.typeCheck(), n2.typeCheck());
	}
	
	public static String nearestCommonType(String t1, String t2){
		if(t1.equals(Tint) && t2.equals(Tint)) return Tint;
		if(t1==Tbool && t2==Tbool) return Tbool;
		return Terror;
	}
	
}
