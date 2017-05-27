// $ANTLR 3.5.1 /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g 2014-02-13 23:21:30

package lpemc.rc.minifun;

import lpemc.rc.minifun.ast.*;
import java.util.HashMap;
import java.util.ArrayList;
import lpemc.rc.minifun.TypeSystem.TMappings;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class MiniFunParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "ARROW", "ASS", "BOOLTYPE", "CLOSECOMMENT", 
		"CLPAR", "COL", "COMMA", "CRPAR", "DIV", "ELSE", "EMPTY", "EQ", "ERR", 
		"FALSE", "FIRST", "FUN", "GENERICTYPE", "GEQ", "ID", "IF", "IN", "INTTYPE", 
		"LANGLE", "LEQ", "LET", "LISTTYPE", "LPAR", "LSQRPAR", "LSTCONS", "L_AND", 
		"L_NOT", "L_OR", "MINUS", "MODULO", "NAT", "OPENCOMMENT", "PIPE", "PLUS", 
		"PRINT", "RANGLE", "REST", "RPAR", "RSQRPAR", "SEMIC", "THEN", "TIMES", 
		"TRUE", "VAR", "WHITESPACES"
	};
	public static final int EOF=-1;
	public static final int ARROW=4;
	public static final int ASS=5;
	public static final int BOOLTYPE=6;
	public static final int CLOSECOMMENT=7;
	public static final int CLPAR=8;
	public static final int COL=9;
	public static final int COMMA=10;
	public static final int CRPAR=11;
	public static final int DIV=12;
	public static final int ELSE=13;
	public static final int EMPTY=14;
	public static final int EQ=15;
	public static final int ERR=16;
	public static final int FALSE=17;
	public static final int FIRST=18;
	public static final int FUN=19;
	public static final int GENERICTYPE=20;
	public static final int GEQ=21;
	public static final int ID=22;
	public static final int IF=23;
	public static final int IN=24;
	public static final int INTTYPE=25;
	public static final int LANGLE=26;
	public static final int LEQ=27;
	public static final int LET=28;
	public static final int LISTTYPE=29;
	public static final int LPAR=30;
	public static final int LSQRPAR=31;
	public static final int LSTCONS=32;
	public static final int L_AND=33;
	public static final int L_NOT=34;
	public static final int L_OR=35;
	public static final int MINUS=36;
	public static final int MODULO=37;
	public static final int NAT=38;
	public static final int OPENCOMMENT=39;
	public static final int PIPE=40;
	public static final int PLUS=41;
	public static final int PRINT=42;
	public static final int RANGLE=43;
	public static final int REST=44;
	public static final int RPAR=45;
	public static final int RSQRPAR=46;
	public static final int SEMIC=47;
	public static final int THEN=48;
	public static final int TIMES=49;
	public static final int TRUE=50;
	public static final int VAR=51;
	public static final int WHITESPACES=52;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public MiniFunParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public MiniFunParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	@Override public String[] getTokenNames() { return MiniFunParser.tokenNames; }
	@Override public String getGrammarFileName() { return "/mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g"; }


	  /* approach to symbol table: LIST OF TABLES */
	  protected ArrayList<HashMap<String,STEntry>> symbolTable  = new ArrayList<HashMap<String,STEntry>>();
		protected int nesting_level = 0;
		protected int var_offset = 1;
		
		protected java.util.Stack<Integer> varoffsets = new java.util.Stack<Integer>();
		
		protected String currFunction = null;



	// $ANTLR start "prog"
	// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:39:1: prog returns [Node ast] :lb= letBlock ;
	public final Node prog() throws RecognitionException {
		Node ast = null;


		Node lb =null;

		try {
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:40:3: (lb= letBlock )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:40:5: lb= letBlock
			{
			 
			    var_offset = 1; 
			    symbolTable.add( new HashMap<String,STEntry>() );  /* adding symbol table for globals */
			    varoffsets.push(new Integer(1));
			    
			pushFollow(FOLLOW_letBlock_in_prog60);
			lb=letBlock();
			state._fsp--;

			 ast = new ProgNode(lb); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return ast;
	}
	// $ANTLR end "prog"



	// $ANTLR start "letBlock"
	// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:47:1: letBlock returns [Node ast] : LET d= declist IN e= exp SEMIC ;
	public final Node letBlock() throws RecognitionException {
		Node ast = null;


		ArrayList<IdentifierDeclNode> d =null;
		Node e =null;

		try {
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:48:2: ( LET d= declist IN e= exp SEMIC )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:48:4: LET d= declist IN e= exp SEMIC
			{
			match(input,LET,FOLLOW_LET_in_letBlock76); 
			pushFollow(FOLLOW_declist_in_letBlock83);
			d=declist();
			state._fsp--;

			match(input,IN,FOLLOW_IN_in_letBlock87); 
			pushFollow(FOLLOW_exp_in_letBlock91);
			e=exp();
			state._fsp--;

			match(input,SEMIC,FOLLOW_SEMIC_in_letBlock93); 
			 
					  ast = new LetBlockNode(d, e); 
					
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return ast;
	}
	// $ANTLR end "letBlock"



	// $ANTLR start "declist"
	// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:55:1: declist returns [ArrayList<IdentifierDeclNode> astList] : ( VAR id= ID COL t= type ASS e= exp SEMIC | FUN id= ID ( LANGLE gentype= GENERICTYPE ( COMMA gentype_n= GENERICTYPE )* RANGLE )? COL t= type LPAR (arg= ID COL argt= type ( COMMA narg= ID COL nargt= type )* )? RPAR CLPAR (e= exp |e= letBlock ) CRPAR SEMIC | comment )* ;
	public final ArrayList<IdentifierDeclNode> declist() throws RecognitionException {
		ArrayList<IdentifierDeclNode> astList = null;


		Token id=null;
		Token gentype=null;
		Token gentype_n=null;
		Token arg=null;
		Token narg=null;
		TypeNode t =null;
		Node e =null;
		TypeNode argt =null;
		TypeNode nargt =null;

		try {
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:56:2: ( ( VAR id= ID COL t= type ASS e= exp SEMIC | FUN id= ID ( LANGLE gentype= GENERICTYPE ( COMMA gentype_n= GENERICTYPE )* RANGLE )? COL t= type LPAR (arg= ID COL argt= type ( COMMA narg= ID COL nargt= type )* )? RPAR CLPAR (e= exp |e= letBlock ) CRPAR SEMIC | comment )* )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:56:4: ( VAR id= ID COL t= type ASS e= exp SEMIC | FUN id= ID ( LANGLE gentype= GENERICTYPE ( COMMA gentype_n= GENERICTYPE )* RANGLE )? COL t= type LPAR (arg= ID COL argt= type ( COMMA narg= ID COL nargt= type )* )? RPAR CLPAR (e= exp |e= letBlock ) CRPAR SEMIC | comment )*
			{
			 
				    astList = new ArrayList<IdentifierDeclNode>(); 
				    HashMap<String,STEntry> currTable = symbolTable.get(nesting_level);
				    
				    var_offset = varoffsets.peek();
				    //System.out.println("Var offset at start of this declist is " + var_offset);
				  
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:64:3: ( VAR id= ID COL t= type ASS e= exp SEMIC | FUN id= ID ( LANGLE gentype= GENERICTYPE ( COMMA gentype_n= GENERICTYPE )* RANGLE )? COL t= type LPAR (arg= ID COL argt= type ( COMMA narg= ID COL nargt= type )* )? RPAR CLPAR (e= exp |e= letBlock ) CRPAR SEMIC | comment )*
			loop6:
			while (true) {
				int alt6=4;
				switch ( input.LA(1) ) {
				case VAR:
					{
					alt6=1;
					}
					break;
				case FUN:
					{
					alt6=2;
					}
					break;
				case OPENCOMMENT:
					{
					alt6=3;
					}
					break;
				}
				switch (alt6) {
				case 1 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:64:4: VAR id= ID COL t= type ASS e= exp SEMIC
					{
					match(input,VAR,FOLLOW_VAR_in_declist123); 
					id=(Token)match(input,ID,FOLLOW_ID_in_declist127); 
					match(input,COL,FOLLOW_COL_in_declist129); 
					pushFollow(FOLLOW_type_in_declist133);
					t=type();
					state._fsp--;

					match(input,ASS,FOLLOW_ASS_in_declist135); 
					pushFollow(FOLLOW_exp_in_declist139);
					e=exp();
					state._fsp--;

					match(input,SEMIC,FOLLOW_SEMIC_in_declist141); 
					 
								VarDeclNode vdn = new VarDeclNode((id!=null?id.getText():null), t, e);
								astList.add(vdn);
								
								STEntry entry = new STEntry(vdn, var_offset++);
								entry.setProperty("kind", "var");
								
								if(t instanceof FunTypeNode){
								  // se la variabile è di tipo funzione (i.e. è un riferimento a funzione)
								  // allora incrementiamo l'offset per fare posto alla sua chiusura
								  var_offset++;
								  //System.out.println("Var " + (id!=null?id.getText():null) + " is of a function type");
								  entry.setProperty("kind", "fun");
								}
								
								// update the top of stack's var_offset level
					      varoffsets.pop();
					      varoffsets.push(var_offset);		
								
								STEntry previous = currTable.put((id!=null?id.getText():null), entry);
								if(previous!=null){
								  	// error: identifier already declared
								  	System.out.println("Parsing error: multiple decl for identifier " + (id!=null?id.getText():null) +
								   	 " at line " + (id!=null?id.getLine():0));
								   	System.exit(1);
								}
							
					}
					break;
				case 2 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:96:3: FUN id= ID ( LANGLE gentype= GENERICTYPE ( COMMA gentype_n= GENERICTYPE )* RANGLE )? COL t= type LPAR (arg= ID COL argt= type ( COMMA narg= ID COL nargt= type )* )? RPAR CLPAR (e= exp |e= letBlock ) CRPAR SEMIC
					{
					 ArrayList<GenericTypeNode> genTypes = new ArrayList<GenericTypeNode>(); 
					match(input,FUN,FOLLOW_FUN_in_declist171); 
					id=(Token)match(input,ID,FOLLOW_ID_in_declist175); 
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:98:3: ( LANGLE gentype= GENERICTYPE ( COMMA gentype_n= GENERICTYPE )* RANGLE )?
					int alt2=2;
					int LA2_0 = input.LA(1);
					if ( (LA2_0==LANGLE) ) {
						alt2=1;
					}
					switch (alt2) {
						case 1 :
							// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:98:4: LANGLE gentype= GENERICTYPE ( COMMA gentype_n= GENERICTYPE )* RANGLE
							{
							match(input,LANGLE,FOLLOW_LANGLE_in_declist181); 
							gentype=(Token)match(input,GENERICTYPE,FOLLOW_GENERICTYPE_in_declist185); 
							 genTypes.add(new GenericTypeNode((gentype!=null?gentype.getText():null))); 
							// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:99:5: ( COMMA gentype_n= GENERICTYPE )*
							loop1:
							while (true) {
								int alt1=2;
								int LA1_0 = input.LA(1);
								if ( (LA1_0==COMMA) ) {
									alt1=1;
								}

								switch (alt1) {
								case 1 :
									// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:99:6: COMMA gentype_n= GENERICTYPE
									{
									match(input,COMMA,FOLLOW_COMMA_in_declist195); 
									gentype_n=(Token)match(input,GENERICTYPE,FOLLOW_GENERICTYPE_in_declist199); 
									 genTypes.add(new GenericTypeNode((gentype!=null?gentype.getText():null))); 
									}
									break;

								default :
									break loop1;
								}
							}

							match(input,RANGLE,FOLLOW_RANGLE_in_declist208); 
							}
							break;

					}

					match(input,COL,FOLLOW_COL_in_declist215); 
					pushFollow(FOLLOW_type_in_declist219);
					t=type();
					state._fsp--;

					match(input,LPAR,FOLLOW_LPAR_in_declist224); 
					 
									FunDeclNode funNode = new FunDeclNode((id!=null?id.getText():null), t);
									currFunction = (id!=null?id.getText():null);
									funNode.setGenericTypes(genTypes);
									
									// the following line was meant to copy declarations of upper levels
									//  to the set of local declarations of this level
									//  so that function references returned as values would be able
									//  to access to variables that may be gone out of scope at the moment of call
									//funNode.copyToLocals(symbolTable, nesting_level, varoffsets);
									
									//System.out.println("Fun " + (id!=null?id.getText():null) + " has offset " + var_offset);				
									STEntry entry = new STEntry(funNode, var_offset++);			
									
									var_offset++; // un ulteriore incremento in quanto le funzioni memorizzano anche il FP del momento della
									// dichiarazione, che corrisponde alla sua CHIUSURA
									// in modo tale che quando una funz. è referenziata si porta dietro anche la chiusura
									// Ciò servirà per consentire al chiamante di impostare l'access link!
									// FunDeclNode dopo avere fatto il push dell'indirizzo della funzione, fa il push di FP
									
									// update the top of stack's var_offset level
					        varoffsets.pop();
					        varoffsets.push(var_offset);
									
									entry.setProperty("kind", "fun");
									
									/* Check for multiple declaration */
									STEntry previous = currTable.put((id!=null?id.getText():null), entry);
									if(previous!=null){ // error: identifier already declared
										System.out.println("Parsing error: multiple decl for identifier " + (id!=null?id.getText():null) +
										 " at line " + (id!=null?id.getLine():0));
										System.exit(1);
									}
									
									/* create a new symbol table for declarations local to the function */
									HashMap<String,STEntry> funSymbolTable = new HashMap<String,STEntry>(); 
									symbolTable.add(funSymbolTable);
									nesting_level++;
									ArrayList<FunArgNode> argNodes = new ArrayList<FunArgNode>();
									FunArgNode argnode;
									int arg_offset = -1;
								
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:145:4: (arg= ID COL argt= type ( COMMA narg= ID COL nargt= type )* )?
					int alt4=2;
					int LA4_0 = input.LA(1);
					if ( (LA4_0==ID) ) {
						alt4=1;
					}
					switch (alt4) {
						case 1 :
							// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:145:5: arg= ID COL argt= type ( COMMA narg= ID COL nargt= type )*
							{
							arg=(Token)match(input,ID,FOLLOW_ID_in_declist238); 
							match(input,COL,FOLLOW_COL_in_declist240); 
							pushFollow(FOLLOW_type_in_declist244);
							argt=type();
							state._fsp--;


										  argnode = new FunArgNode((arg!=null?arg.getText():null), argt, funNode);
										  argNodes.add(argnode);
											STEntry fentry = null;
											
											/*
											fentry.setProperty("kind", argt.getKind());
											arg_offset -= argt.getNumOfElems();
											*/
											if(argt instanceof FunTypeNode){
											  fentry = new STEntry(argnode, arg_offset-1);
											  fentry.setProperty("kind","fun");
											  arg_offset--;
											  arg_offset--; // se l'arg è di tipo funzione, tengo un posto per la closure
											} else{
											  fentry = new STEntry(argnode, arg_offset--);
											  fentry.setProperty("kind","var");
											}
											
											previous = funSymbolTable.put((arg!=null?arg.getText():null), fentry);
										
							// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:168:4: ( COMMA narg= ID COL nargt= type )*
							loop3:
							while (true) {
								int alt3=2;
								int LA3_0 = input.LA(1);
								if ( (LA3_0==COMMA) ) {
									alt3=1;
								}

								switch (alt3) {
								case 1 :
									// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:168:5: COMMA narg= ID COL nargt= type
									{
									match(input,COMMA,FOLLOW_COMMA_in_declist260); 
									narg=(Token)match(input,ID,FOLLOW_ID_in_declist264); 
									match(input,COL,FOLLOW_COL_in_declist266); 
									pushFollow(FOLLOW_type_in_declist270);
									nargt=type();
									state._fsp--;


													argnode = new FunArgNode((narg!=null?narg.getText():null), nargt, funNode);
													argNodes.add(argnode);
													STEntry entry_n = null;
									        
									        if(nargt instanceof FunTypeNode){
									          //System.out.println("Setting offset of " + (narg!=null?narg.getText():null) + " to " + (arg_offset-1));
									          entry_n = new STEntry(argnode, arg_offset-1);
									          entry_n.setProperty("kind","fun");
									          arg_offset--;
									          arg_offset--; // se l'arg è di tipo funzione, tengo un posto per la closure
									        } else{
									          entry_n = new STEntry(argnode, arg_offset--);
									          entry_n.setProperty("kind","var");
									        }
									        
									        /* Check for multiple declaration of a param */
													previous = funSymbolTable.put((narg!=null?narg.getText():null), entry_n);
													if(previous!=null){
														// error: identifier already declared
														System.out.println("Parsing error: multiple decl for parameter " + (id!=null?id.getText():null) +
														 " at line " + (id!=null?id.getLine():0));
														System.exit(1);
													}
												
									}
									break;

								default :
									break loop3;
								}
							}

							}
							break;

					}

					match(input,RPAR,FOLLOW_RPAR_in_declist289); 
					match(input,CLPAR,FOLLOW_CLPAR_in_declist291); 
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:196:4: (e= exp |e= letBlock )
					int alt5=2;
					int LA5_0 = input.LA(1);
					if ( (LA5_0==EMPTY||(LA5_0 >= FALSE && LA5_0 <= FIRST)||(LA5_0 >= ID && LA5_0 <= IF)||(LA5_0 >= LPAR && LA5_0 <= LSQRPAR)||LA5_0==L_NOT||LA5_0==NAT||LA5_0==PIPE||LA5_0==PRINT||LA5_0==REST||LA5_0==TRUE) ) {
						alt5=1;
					}
					else if ( (LA5_0==LET) ) {
						alt5=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 5, 0, input);
						throw nvae;
					}

					switch (alt5) {
						case 1 :
							// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:196:5: e= exp
							{
							pushFollow(FOLLOW_exp_in_declist300);
							e=exp();
							state._fsp--;

							}
							break;
						case 2 :
							// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:198:5: e= letBlock
							{
							varoffsets.push(2); 
							pushFollow(FOLLOW_letBlock_in_declist325);
							e=letBlock();
							state._fsp--;

							 varoffsets.pop(); var_offset = varoffsets.peek(); 
							}
							break;

					}

					match(input,CRPAR,FOLLOW_CRPAR_in_declist335); 
					match(input,SEMIC,FOLLOW_SEMIC_in_declist354); 

									funNode.setParams(argNodes);
									funNode.setBody(e);
									symbolTable.remove(nesting_level--); 
									astList.add(funNode);
								
					}
					break;
				case 3 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:216:4: comment
					{
					pushFollow(FOLLOW_comment_in_declist371);
					comment();
					state._fsp--;

					}
					break;

				default :
					break loop6;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return astList;
	}
	// $ANTLR end "declist"



	// $ANTLR start "exp"
	// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:221:1: exp returns [Node ast] : v= value ( EQ vnext= value | ( LEQ vnext= value ) | ( GEQ vnext= value ) )* ;
	public final Node exp() throws RecognitionException {
		Node ast = null;


		Node v =null;
		Node vnext =null;

		try {
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:222:2: (v= value ( EQ vnext= value | ( LEQ vnext= value ) | ( GEQ vnext= value ) )* )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:222:4: v= value ( EQ vnext= value | ( LEQ vnext= value ) | ( GEQ vnext= value ) )*
			{
			pushFollow(FOLLOW_value_in_exp403);
			v=value();
			state._fsp--;

			 ast = v; 
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:223:3: ( EQ vnext= value | ( LEQ vnext= value ) | ( GEQ vnext= value ) )*
			loop7:
			while (true) {
				int alt7=4;
				switch ( input.LA(1) ) {
				case EQ:
					{
					alt7=1;
					}
					break;
				case LEQ:
					{
					alt7=2;
					}
					break;
				case GEQ:
					{
					alt7=3;
					}
					break;
				}
				switch (alt7) {
				case 1 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:223:4: EQ vnext= value
					{
					match(input,EQ,FOLLOW_EQ_in_exp410); 
					pushFollow(FOLLOW_value_in_exp414);
					vnext=value();
					state._fsp--;

					 ast = new EqNode(ast, vnext); 
					}
					break;
				case 2 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:225:3: ( LEQ vnext= value )
					{
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:225:3: ( LEQ vnext= value )
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:225:4: LEQ vnext= value
					{
					match(input,LEQ,FOLLOW_LEQ_in_exp427); 
					pushFollow(FOLLOW_value_in_exp431);
					vnext=value();
					state._fsp--;

					 ast = new LessEqNode(ast, vnext); 
					}

					}
					break;
				case 3 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:227:3: ( GEQ vnext= value )
					{
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:227:3: ( GEQ vnext= value )
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:227:4: GEQ vnext= value
					{
					match(input,GEQ,FOLLOW_GEQ_in_exp445); 
					pushFollow(FOLLOW_value_in_exp449);
					vnext=value();
					state._fsp--;

					 ast = new GreaterEqNode(ast, vnext); 
					}

					}
					break;

				default :
					break loop7;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return ast;
	}
	// $ANTLR end "exp"



	// $ANTLR start "value"
	// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:232:1: value returns [Node ast] : t= term ( PLUS tnext= term | MINUS tnext= term | L_OR tnext= term )* ;
	public final Node value() throws RecognitionException {
		Node ast = null;


		Node t =null;
		Node tnext =null;

		try {
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:233:2: (t= term ( PLUS tnext= term | MINUS tnext= term | L_OR tnext= term )* )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:233:4: t= term ( PLUS tnext= term | MINUS tnext= term | L_OR tnext= term )*
			{
			pushFollow(FOLLOW_term_in_value537);
			t=term();
			state._fsp--;

			 ast =  t; 
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:234:3: ( PLUS tnext= term | MINUS tnext= term | L_OR tnext= term )*
			loop8:
			while (true) {
				int alt8=4;
				switch ( input.LA(1) ) {
				case PLUS:
					{
					alt8=1;
					}
					break;
				case MINUS:
					{
					alt8=2;
					}
					break;
				case L_OR:
					{
					alt8=3;
					}
					break;
				}
				switch (alt8) {
				case 1 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:234:4: PLUS tnext= term
					{
					match(input,PLUS,FOLLOW_PLUS_in_value544); 
					pushFollow(FOLLOW_term_in_value548);
					tnext=term();
					state._fsp--;

					 ast = new PlusNode(ast, tnext); 
					}
					break;
				case 2 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:236:3: MINUS tnext= term
					{
					match(input,MINUS,FOLLOW_MINUS_in_value561); 
					pushFollow(FOLLOW_term_in_value565);
					tnext=term();
					state._fsp--;

					 ast = new MinusNode(ast, tnext); 
					}
					break;
				case 3 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:238:3: L_OR tnext= term
					{
					match(input,L_OR,FOLLOW_L_OR_in_value577); 
					pushFollow(FOLLOW_term_in_value581);
					tnext=term();
					state._fsp--;

					 ast = new ORNode(ast, tnext); 
					}
					break;

				default :
					break loop8;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return ast;
	}
	// $ANTLR end "value"



	// $ANTLR start "term"
	// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:241:1: term returns [Node ast] : f= factor ( TIMES fnext= factor | DIV fnext= factor | L_AND fnext= factor | MODULO fnext= factor )* ;
	public final Node term() throws RecognitionException {
		Node ast = null;


		Node f =null;
		Node fnext =null;

		try {
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:242:2: (f= factor ( TIMES fnext= factor | DIV fnext= factor | L_AND fnext= factor | MODULO fnext= factor )* )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:242:4: f= factor ( TIMES fnext= factor | DIV fnext= factor | L_AND fnext= factor | MODULO fnext= factor )*
			{
			pushFollow(FOLLOW_factor_in_term605);
			f=factor();
			state._fsp--;

			 ast = f;  
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:243:3: ( TIMES fnext= factor | DIV fnext= factor | L_AND fnext= factor | MODULO fnext= factor )*
			loop9:
			while (true) {
				int alt9=5;
				switch ( input.LA(1) ) {
				case TIMES:
					{
					alt9=1;
					}
					break;
				case DIV:
					{
					alt9=2;
					}
					break;
				case L_AND:
					{
					alt9=3;
					}
					break;
				case MODULO:
					{
					alt9=4;
					}
					break;
				}
				switch (alt9) {
				case 1 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:243:4: TIMES fnext= factor
					{
					match(input,TIMES,FOLLOW_TIMES_in_term613); 
					pushFollow(FOLLOW_factor_in_term617);
					fnext=factor();
					state._fsp--;

					 ast = new TimesNode(ast, fnext); 
					}
					break;
				case 2 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:245:3: DIV fnext= factor
					{
					match(input,DIV,FOLLOW_DIV_in_term631); 
					pushFollow(FOLLOW_factor_in_term635);
					fnext=factor();
					state._fsp--;

					 ast = new DivNode(ast, fnext); 
					}
					break;
				case 3 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:247:3: L_AND fnext= factor
					{
					match(input,L_AND,FOLLOW_L_AND_in_term649); 
					pushFollow(FOLLOW_factor_in_term653);
					fnext=factor();
					state._fsp--;

					 ast = new ANDNode(ast, fnext); 
					}
					break;
				case 4 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:249:3: MODULO fnext= factor
					{
					match(input,MODULO,FOLLOW_MODULO_in_term666); 
					pushFollow(FOLLOW_factor_in_term670);
					fnext=factor();
					state._fsp--;

					 ast = new MODNode(ast, fnext); 
					}
					break;

				default :
					break loop9;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return ast;
	}
	// $ANTLR end "term"



	// $ANTLR start "factor"
	// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:252:1: factor returns [Node ast] : (n= NAT | TRUE | FALSE | EMPTY | LPAR e= exp RPAR | L_NOT LPAR e= exp RPAR |id= ID ( LANGLE (t1= type ) ( COMMA tn= type )* RANGLE )? ( LPAR (e= exp ( COMMA enext= exp )* )? RPAR )? | IF e1= exp THEN CLPAR e2= exp CRPAR ELSE CLPAR e3= exp CRPAR | LSQRPAR h= exp LSTCONS t= exp RSQRPAR | PIPE e= exp ( COMMA en= exp )* PIPE | FIRST LPAR lst= exp RPAR | REST LPAR lst= exp RPAR | PRINT LPAR e= exp RPAR );
	public final Node factor() throws RecognitionException {
		Node ast = null;


		Token n=null;
		Token id=null;
		Node e =null;
		TypeNode t1 =null;
		TypeNode tn =null;
		Node enext =null;
		Node e1 =null;
		Node e2 =null;
		Node e3 =null;
		Node h =null;
		Node t =null;
		Node en =null;
		Node lst =null;

		try {
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:253:2: (n= NAT | TRUE | FALSE | EMPTY | LPAR e= exp RPAR | L_NOT LPAR e= exp RPAR |id= ID ( LANGLE (t1= type ) ( COMMA tn= type )* RANGLE )? ( LPAR (e= exp ( COMMA enext= exp )* )? RPAR )? | IF e1= exp THEN CLPAR e2= exp CRPAR ELSE CLPAR e3= exp CRPAR | LSQRPAR h= exp LSTCONS t= exp RSQRPAR | PIPE e= exp ( COMMA en= exp )* PIPE | FIRST LPAR lst= exp RPAR | REST LPAR lst= exp RPAR | PRINT LPAR e= exp RPAR )
			int alt16=13;
			switch ( input.LA(1) ) {
			case NAT:
				{
				alt16=1;
				}
				break;
			case TRUE:
				{
				alt16=2;
				}
				break;
			case FALSE:
				{
				alt16=3;
				}
				break;
			case EMPTY:
				{
				alt16=4;
				}
				break;
			case LPAR:
				{
				alt16=5;
				}
				break;
			case L_NOT:
				{
				alt16=6;
				}
				break;
			case ID:
				{
				alt16=7;
				}
				break;
			case IF:
				{
				alt16=8;
				}
				break;
			case LSQRPAR:
				{
				alt16=9;
				}
				break;
			case PIPE:
				{
				alt16=10;
				}
				break;
			case FIRST:
				{
				alt16=11;
				}
				break;
			case REST:
				{
				alt16=12;
				}
				break;
			case PRINT:
				{
				alt16=13;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 16, 0, input);
				throw nvae;
			}
			switch (alt16) {
				case 1 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:253:4: n= NAT
					{
					n=(Token)match(input,NAT,FOLLOW_NAT_in_factor693); 
					 ast = new NatNode(Integer.parseInt((n!=null?n.getText():null))); 
					}
					break;
				case 2 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:254:4: TRUE
					{
					match(input,TRUE,FOLLOW_TRUE_in_factor700); 
					 ast = new BoolNode(true); 
					}
					break;
				case 3 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:255:4: FALSE
					{
					match(input,FALSE,FOLLOW_FALSE_in_factor707); 
					 ast = new BoolNode(false); 
					}
					break;
				case 4 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:256:4: EMPTY
					{
					match(input,EMPTY,FOLLOW_EMPTY_in_factor714); 
					 ast = new ListEmptyNode(); 
					}
					break;
				case 5 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:257:4: LPAR e= exp RPAR
					{
					match(input,LPAR,FOLLOW_LPAR_in_factor723); 
					pushFollow(FOLLOW_exp_in_factor727);
					e=exp();
					state._fsp--;

					match(input,RPAR,FOLLOW_RPAR_in_factor729); 
					 ast = e; 
					}
					break;
				case 6 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:258:4: L_NOT LPAR e= exp RPAR
					{
					match(input,L_NOT,FOLLOW_L_NOT_in_factor736); 
					match(input,LPAR,FOLLOW_LPAR_in_factor738); 
					pushFollow(FOLLOW_exp_in_factor742);
					e=exp();
					state._fsp--;

					match(input,RPAR,FOLLOW_RPAR_in_factor744); 
					 ast = new NOTNode(e); 
					}
					break;
				case 7 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:259:4: id= ID ( LANGLE (t1= type ) ( COMMA tn= type )* RANGLE )? ( LPAR (e= exp ( COMMA enext= exp )* )? RPAR )?
					{
					id=(Token)match(input,ID,FOLLOW_ID_in_factor753); 
					 
									boolean found = false;
									int found_level = nesting_level;
									STEntry found_entry = null;
									
									for(int currlevel = nesting_level; currlevel>=0; currlevel--){
										HashMap<String,STEntry> currTable = symbolTable.get(currlevel);
										
										STEntry entry = currTable.get((id!=null?id.getText():null));
										if(entry!=null){
											found = true;
											found_level = currlevel;
											found_entry = entry;
											
							        if(currFunction!=null && (nesting_level-found_level)>0 && found_level>0){
							          /*System.out.println(currFunction + " makes use of the following identifier at "+
							            "higher level (but not top-level): " + (id!=null?id.getText():null));*/
							          // THESE IDENTIFIERS SHOULD BE COPIED TO THE ENVIRONMENT OF ENCLOSING FUN TO ENABLE FULL CLOSURES!! 
							        }						
											
											if(entry.getProperty("kind").equals("var")){
											  ast = new VarNode(entry, nesting_level-found_level);  
											} else if(entry.getProperty("kind").equals("fun")){
											  ast = new FunRefNode(entry, nesting_level-found_level);
											}
											break;
										}
									}
								  if(!found){
								    	// error: identifier not declared
								    	System.out.println("Parsing error: identifier not declared '" + (id!=null?id.getText():null) +
								    	 "' used at line " + (id!=null?id.getLine():0));
								    	System.exit(1);				
								  }
								
					 ArrayList<TypeNode> typesInBrackets = new ArrayList<TypeNode>(); 
							  TMappings mappings = null;
							
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:301:3: ( LANGLE (t1= type ) ( COMMA tn= type )* RANGLE )?
					int alt11=2;
					int LA11_0 = input.LA(1);
					if ( (LA11_0==LANGLE) ) {
						alt11=1;
					}
					switch (alt11) {
						case 1 :
							// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:301:5: LANGLE (t1= type ) ( COMMA tn= type )* RANGLE
							{
							match(input,LANGLE,FOLLOW_LANGLE_in_factor777); 
							 mappings = null; typesInBrackets.clear(); 
							// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:302:5: (t1= type )
							// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:302:6: t1= type
							{
							pushFollow(FOLLOW_type_in_factor788);
							t1=type();
							state._fsp--;

							}

							 typesInBrackets.add(t1); 
							// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:303:5: ( COMMA tn= type )*
							loop10:
							while (true) {
								int alt10=2;
								int LA10_0 = input.LA(1);
								if ( (LA10_0==COMMA) ) {
									alt10=1;
								}

								switch (alt10) {
								case 1 :
									// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:303:6: COMMA tn= type
									{
									match(input,COMMA,FOLLOW_COMMA_in_factor798); 
									pushFollow(FOLLOW_type_in_factor802);
									tn=type();
									state._fsp--;

									 typesInBrackets.add(tn); 
									}
									break;

								default :
									break loop10;
								}
							}

							match(input,RANGLE,FOLLOW_RANGLE_in_factor815); 
							 
									    ((FunRefNode)ast).setTypeParams(typesInBrackets);
									    
									    /*
									    if(TypeSystem.AllTheseTypes(typesInBrackets, TypeSystem.ARE_CONCRETE)){
									      // proceed with instantiation
									      FunDeclNode fdn = (FunDeclNode) ((FunRefNode)ast).getRelativeDecl();
									      mappings = fdn.createTypeMappings(typesInBrackets);
									      //((FunRefNode)ast).instantiateGenTypes(mappings); 
									    } 
									    */
									  
							}
							break;

					}

					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:319:3: ( LPAR (e= exp ( COMMA enext= exp )* )? RPAR )?
					int alt14=2;
					int LA14_0 = input.LA(1);
					if ( (LA14_0==LPAR) ) {
						alt14=1;
					}
					switch (alt14) {
						case 1 :
							// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:319:4: LPAR (e= exp ( COMMA enext= exp )* )? RPAR
							{
							match(input,LPAR,FOLLOW_LPAR_in_factor833); 
							 ArrayList<Node> actualArgs = new ArrayList<Node>(); 
							// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:320:5: (e= exp ( COMMA enext= exp )* )?
							int alt13=2;
							int LA13_0 = input.LA(1);
							if ( (LA13_0==EMPTY||(LA13_0 >= FALSE && LA13_0 <= FIRST)||(LA13_0 >= ID && LA13_0 <= IF)||(LA13_0 >= LPAR && LA13_0 <= LSQRPAR)||LA13_0==L_NOT||LA13_0==NAT||LA13_0==PIPE||LA13_0==PRINT||LA13_0==REST||LA13_0==TRUE) ) {
								alt13=1;
							}
							switch (alt13) {
								case 1 :
									// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:320:6: e= exp ( COMMA enext= exp )*
									{
									pushFollow(FOLLOW_exp_in_factor844);
									e=exp();
									state._fsp--;

									 
											    /* first expression (actual argument) */
											    actualArgs.add(e);
											  
									// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:324:5: ( COMMA enext= exp )*
									loop12:
									while (true) {
										int alt12=2;
										int LA12_0 = input.LA(1);
										if ( (LA12_0==COMMA) ) {
											alt12=1;
										}

										switch (alt12) {
										case 1 :
											// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:324:6: COMMA enext= exp
											{
											match(input,COMMA,FOLLOW_COMMA_in_factor853); 
											pushFollow(FOLLOW_exp_in_factor857);
											enext=exp();
											state._fsp--;


													    /* subsequent expression (actual argument) */
													    actualArgs.add(enext);
													  
											}
											break;

										default :
											break loop12;
										}
									}

									}
									break;

							}

							match(input,RPAR,FOLLOW_RPAR_in_factor873); 

									    ast = new FunCallNode((FunRefNode)ast, nesting_level-found_level, actualArgs);
							        
							        /*
							        if(mappings!=null)
							          ast.instantiateGenTypes(mappings);
							        */
									  
							}
							break;

					}

					}
					break;
				case 8 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:339:4: IF e1= exp THEN CLPAR e2= exp CRPAR ELSE CLPAR e3= exp CRPAR
					{
					match(input,IF,FOLLOW_IF_in_factor889); 
					pushFollow(FOLLOW_exp_in_factor893);
					e1=exp();
					state._fsp--;

					match(input,THEN,FOLLOW_THEN_in_factor895); 
					match(input,CLPAR,FOLLOW_CLPAR_in_factor897); 
					pushFollow(FOLLOW_exp_in_factor901);
					e2=exp();
					state._fsp--;

					match(input,CRPAR,FOLLOW_CRPAR_in_factor903); 
					match(input,ELSE,FOLLOW_ELSE_in_factor914); 
					match(input,CLPAR,FOLLOW_CLPAR_in_factor916); 
					pushFollow(FOLLOW_exp_in_factor920);
					e3=exp();
					state._fsp--;

					match(input,CRPAR,FOLLOW_CRPAR_in_factor922); 
					 ast = new IfNode(e1, e2, e3); 
					}
					break;
				case 9 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:342:4: LSQRPAR h= exp LSTCONS t= exp RSQRPAR
					{
					match(input,LSQRPAR,FOLLOW_LSQRPAR_in_factor932); 
					pushFollow(FOLLOW_exp_in_factor936);
					h=exp();
					state._fsp--;

					match(input,LSTCONS,FOLLOW_LSTCONS_in_factor938); 
					pushFollow(FOLLOW_exp_in_factor942);
					t=exp();
					state._fsp--;

					match(input,RSQRPAR,FOLLOW_RSQRPAR_in_factor944); 
					 ast = new ListConsNode(h, t); 
					}
					break;
				case 10 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:344:4: PIPE e= exp ( COMMA en= exp )* PIPE
					{
					match(input,PIPE,FOLLOW_PIPE_in_factor953); 
					 ArrayList<Node> exps = new ArrayList<Node>(); 
					pushFollow(FOLLOW_exp_in_factor963);
					e=exp();
					state._fsp--;

					 exps.add(e); 
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:346:4: ( COMMA en= exp )*
					loop15:
					while (true) {
						int alt15=2;
						int LA15_0 = input.LA(1);
						if ( (LA15_0==COMMA) ) {
							alt15=1;
						}

						switch (alt15) {
						case 1 :
							// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:346:5: COMMA en= exp
							{
							match(input,COMMA,FOLLOW_COMMA_in_factor971); 
							pushFollow(FOLLOW_exp_in_factor975);
							en=exp();
							state._fsp--;

							 exps.add(en); 
							}
							break;

						default :
							break loop15;
						}
					}

					match(input,PIPE,FOLLOW_PIPE_in_factor986); 
					 ast = Utils.CreateListFromLiteral(exps); 
					}
					break;
				case 11 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:348:4: FIRST LPAR lst= exp RPAR
					{
					match(input,FIRST,FOLLOW_FIRST_in_factor993); 
					match(input,LPAR,FOLLOW_LPAR_in_factor995); 
					pushFollow(FOLLOW_exp_in_factor999);
					lst=exp();
					state._fsp--;

					match(input,RPAR,FOLLOW_RPAR_in_factor1001); 
					 ast = new ListFirstNode(lst); 
					}
					break;
				case 12 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:350:4: REST LPAR lst= exp RPAR
					{
					match(input,REST,FOLLOW_REST_in_factor1010); 
					match(input,LPAR,FOLLOW_LPAR_in_factor1012); 
					pushFollow(FOLLOW_exp_in_factor1016);
					lst=exp();
					state._fsp--;

					match(input,RPAR,FOLLOW_RPAR_in_factor1018); 
					 ast = new ListRestNode(lst); 
					}
					break;
				case 13 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:352:4: PRINT LPAR e= exp RPAR
					{
					match(input,PRINT,FOLLOW_PRINT_in_factor1027); 
					match(input,LPAR,FOLLOW_LPAR_in_factor1029); 
					pushFollow(FOLLOW_exp_in_factor1033);
					e=exp();
					state._fsp--;

					match(input,RPAR,FOLLOW_RPAR_in_factor1035); 
					 ast = new PrintNode(e); 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return ast;
	}
	// $ANTLR end "factor"



	// $ANTLR start "type"
	// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:356:1: type returns [TypeNode ast] : ( INTTYPE | BOOLTYPE | LISTTYPE LSQRPAR t= type RSQRPAR | LPAR (argType1= type ( COMMA argTypeN= type )* )? RPAR ARROW retType= type |gt= GENERICTYPE );
	public final TypeNode type() throws RecognitionException {
		TypeNode ast = null;


		Token gt=null;
		TypeNode t =null;
		TypeNode argType1 =null;
		TypeNode argTypeN =null;
		TypeNode retType =null;

		try {
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:357:2: ( INTTYPE | BOOLTYPE | LISTTYPE LSQRPAR t= type RSQRPAR | LPAR (argType1= type ( COMMA argTypeN= type )* )? RPAR ARROW retType= type |gt= GENERICTYPE )
			int alt19=5;
			switch ( input.LA(1) ) {
			case INTTYPE:
				{
				alt19=1;
				}
				break;
			case BOOLTYPE:
				{
				alt19=2;
				}
				break;
			case LISTTYPE:
				{
				alt19=3;
				}
				break;
			case LPAR:
				{
				alt19=4;
				}
				break;
			case GENERICTYPE:
				{
				alt19=5;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 19, 0, input);
				throw nvae;
			}
			switch (alt19) {
				case 1 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:357:4: INTTYPE
					{
					match(input,INTTYPE,FOLLOW_INTTYPE_in_type1056); 
					 ast = new IntTypeNode(); 
					}
					break;
				case 2 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:358:4: BOOLTYPE
					{
					match(input,BOOLTYPE,FOLLOW_BOOLTYPE_in_type1063); 
					 ast = new BoolTypeNode(); 
					}
					break;
				case 3 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:359:4: LISTTYPE LSQRPAR t= type RSQRPAR
					{
					match(input,LISTTYPE,FOLLOW_LISTTYPE_in_type1070); 
					match(input,LSQRPAR,FOLLOW_LSQRPAR_in_type1072); 
					pushFollow(FOLLOW_type_in_type1076);
					t=type();
					state._fsp--;

					match(input,RSQRPAR,FOLLOW_RSQRPAR_in_type1078); 
					 ast = new ListTypeNode(t); 
					}
					break;
				case 4 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:360:4: LPAR (argType1= type ( COMMA argTypeN= type )* )? RPAR ARROW retType= type
					{
					match(input,LPAR,FOLLOW_LPAR_in_type1086); 
					 ArrayList<TypeNode> argTypes = new ArrayList<TypeNode>(); 
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:361:5: (argType1= type ( COMMA argTypeN= type )* )?
					int alt18=2;
					int LA18_0 = input.LA(1);
					if ( (LA18_0==BOOLTYPE||LA18_0==GENERICTYPE||LA18_0==INTTYPE||(LA18_0 >= LISTTYPE && LA18_0 <= LPAR)) ) {
						alt18=1;
					}
					switch (alt18) {
						case 1 :
							// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:361:6: argType1= type ( COMMA argTypeN= type )*
							{
							pushFollow(FOLLOW_type_in_type1097);
							argType1=type();
							state._fsp--;

							 argTypes.add(argType1); 
							// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:362:5: ( COMMA argTypeN= type )*
							loop17:
							while (true) {
								int alt17=2;
								int LA17_0 = input.LA(1);
								if ( (LA17_0==COMMA) ) {
									alt17=1;
								}

								switch (alt17) {
								case 1 :
									// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:362:6: COMMA argTypeN= type
									{
									match(input,COMMA,FOLLOW_COMMA_in_type1106); 
									pushFollow(FOLLOW_type_in_type1110);
									argTypeN=type();
									state._fsp--;

									 argTypes.add(argTypeN); 
									}
									break;

								default :
									break loop17;
								}
							}

							}
							break;

					}

					match(input,RPAR,FOLLOW_RPAR_in_type1128); 
					match(input,ARROW,FOLLOW_ARROW_in_type1130); 
					pushFollow(FOLLOW_type_in_type1134);
					retType=type();
					state._fsp--;

					 ast = new FunTypeNode(argTypes, retType); 
					}
					break;
				case 5 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:365:5: gt= GENERICTYPE
					{
					gt=(Token)match(input,GENERICTYPE,FOLLOW_GENERICTYPE_in_type1144); 
					 ast = new GenericTypeNode((gt!=null?gt.getText():null)); 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return ast;
	}
	// $ANTLR end "type"



	// $ANTLR start "comment"
	// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:367:1: comment : OPENCOMMENT (~ CLOSECOMMENT )* CLOSECOMMENT ;
	public final void comment() throws RecognitionException {
		try {
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:367:9: ( OPENCOMMENT (~ CLOSECOMMENT )* CLOSECOMMENT )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:367:11: OPENCOMMENT (~ CLOSECOMMENT )* CLOSECOMMENT
			{
			match(input,OPENCOMMENT,FOLLOW_OPENCOMMENT_in_comment1157); 
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:367:23: (~ CLOSECOMMENT )*
			loop20:
			while (true) {
				int alt20=2;
				int LA20_0 = input.LA(1);
				if ( ((LA20_0 >= ARROW && LA20_0 <= BOOLTYPE)||(LA20_0 >= CLPAR && LA20_0 <= WHITESPACES)) ) {
					alt20=1;
				}

				switch (alt20) {
				case 1 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:
					{
					if ( (input.LA(1) >= ARROW && input.LA(1) <= BOOLTYPE)||(input.LA(1) >= CLPAR && input.LA(1) <= WHITESPACES) ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;

				default :
					break loop20;
				}
			}

			match(input,CLOSECOMMENT,FOLLOW_CLOSECOMMENT_in_comment1165); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "comment"

	// Delegated rules



	public static final BitSet FOLLOW_letBlock_in_prog60 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LET_in_letBlock76 = new BitSet(new long[]{0x0008008001080000L});
	public static final BitSet FOLLOW_declist_in_letBlock83 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_IN_in_letBlock87 = new BitSet(new long[]{0x00041544C0C64000L});
	public static final BitSet FOLLOW_exp_in_letBlock91 = new BitSet(new long[]{0x0000800000000000L});
	public static final BitSet FOLLOW_SEMIC_in_letBlock93 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_VAR_in_declist123 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_ID_in_declist127 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_COL_in_declist129 = new BitSet(new long[]{0x0000000062100040L});
	public static final BitSet FOLLOW_type_in_declist133 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_ASS_in_declist135 = new BitSet(new long[]{0x00041544C0C64000L});
	public static final BitSet FOLLOW_exp_in_declist139 = new BitSet(new long[]{0x0000800000000000L});
	public static final BitSet FOLLOW_SEMIC_in_declist141 = new BitSet(new long[]{0x0008008000080002L});
	public static final BitSet FOLLOW_FUN_in_declist171 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_ID_in_declist175 = new BitSet(new long[]{0x0000000004000200L});
	public static final BitSet FOLLOW_LANGLE_in_declist181 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_GENERICTYPE_in_declist185 = new BitSet(new long[]{0x0000080000000400L});
	public static final BitSet FOLLOW_COMMA_in_declist195 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_GENERICTYPE_in_declist199 = new BitSet(new long[]{0x0000080000000400L});
	public static final BitSet FOLLOW_RANGLE_in_declist208 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_COL_in_declist215 = new BitSet(new long[]{0x0000000062100040L});
	public static final BitSet FOLLOW_type_in_declist219 = new BitSet(new long[]{0x0000000040000000L});
	public static final BitSet FOLLOW_LPAR_in_declist224 = new BitSet(new long[]{0x0000200000400000L});
	public static final BitSet FOLLOW_ID_in_declist238 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_COL_in_declist240 = new BitSet(new long[]{0x0000000062100040L});
	public static final BitSet FOLLOW_type_in_declist244 = new BitSet(new long[]{0x0000200000000400L});
	public static final BitSet FOLLOW_COMMA_in_declist260 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_ID_in_declist264 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_COL_in_declist266 = new BitSet(new long[]{0x0000000062100040L});
	public static final BitSet FOLLOW_type_in_declist270 = new BitSet(new long[]{0x0000200000000400L});
	public static final BitSet FOLLOW_RPAR_in_declist289 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_CLPAR_in_declist291 = new BitSet(new long[]{0x00041544D0C64000L});
	public static final BitSet FOLLOW_exp_in_declist300 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_letBlock_in_declist325 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_CRPAR_in_declist335 = new BitSet(new long[]{0x0000800000000000L});
	public static final BitSet FOLLOW_SEMIC_in_declist354 = new BitSet(new long[]{0x0008008000080002L});
	public static final BitSet FOLLOW_comment_in_declist371 = new BitSet(new long[]{0x0008008000080002L});
	public static final BitSet FOLLOW_value_in_exp403 = new BitSet(new long[]{0x0000000008208002L});
	public static final BitSet FOLLOW_EQ_in_exp410 = new BitSet(new long[]{0x00041544C0C64000L});
	public static final BitSet FOLLOW_value_in_exp414 = new BitSet(new long[]{0x0000000008208002L});
	public static final BitSet FOLLOW_LEQ_in_exp427 = new BitSet(new long[]{0x00041544C0C64000L});
	public static final BitSet FOLLOW_value_in_exp431 = new BitSet(new long[]{0x0000000008208002L});
	public static final BitSet FOLLOW_GEQ_in_exp445 = new BitSet(new long[]{0x00041544C0C64000L});
	public static final BitSet FOLLOW_value_in_exp449 = new BitSet(new long[]{0x0000000008208002L});
	public static final BitSet FOLLOW_term_in_value537 = new BitSet(new long[]{0x0000021800000002L});
	public static final BitSet FOLLOW_PLUS_in_value544 = new BitSet(new long[]{0x00041544C0C64000L});
	public static final BitSet FOLLOW_term_in_value548 = new BitSet(new long[]{0x0000021800000002L});
	public static final BitSet FOLLOW_MINUS_in_value561 = new BitSet(new long[]{0x00041544C0C64000L});
	public static final BitSet FOLLOW_term_in_value565 = new BitSet(new long[]{0x0000021800000002L});
	public static final BitSet FOLLOW_L_OR_in_value577 = new BitSet(new long[]{0x00041544C0C64000L});
	public static final BitSet FOLLOW_term_in_value581 = new BitSet(new long[]{0x0000021800000002L});
	public static final BitSet FOLLOW_factor_in_term605 = new BitSet(new long[]{0x0002002200001002L});
	public static final BitSet FOLLOW_TIMES_in_term613 = new BitSet(new long[]{0x00041544C0C64000L});
	public static final BitSet FOLLOW_factor_in_term617 = new BitSet(new long[]{0x0002002200001002L});
	public static final BitSet FOLLOW_DIV_in_term631 = new BitSet(new long[]{0x00041544C0C64000L});
	public static final BitSet FOLLOW_factor_in_term635 = new BitSet(new long[]{0x0002002200001002L});
	public static final BitSet FOLLOW_L_AND_in_term649 = new BitSet(new long[]{0x00041544C0C64000L});
	public static final BitSet FOLLOW_factor_in_term653 = new BitSet(new long[]{0x0002002200001002L});
	public static final BitSet FOLLOW_MODULO_in_term666 = new BitSet(new long[]{0x00041544C0C64000L});
	public static final BitSet FOLLOW_factor_in_term670 = new BitSet(new long[]{0x0002002200001002L});
	public static final BitSet FOLLOW_NAT_in_factor693 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TRUE_in_factor700 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FALSE_in_factor707 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_EMPTY_in_factor714 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LPAR_in_factor723 = new BitSet(new long[]{0x00041544C0C64000L});
	public static final BitSet FOLLOW_exp_in_factor727 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_RPAR_in_factor729 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_L_NOT_in_factor736 = new BitSet(new long[]{0x0000000040000000L});
	public static final BitSet FOLLOW_LPAR_in_factor738 = new BitSet(new long[]{0x00041544C0C64000L});
	public static final BitSet FOLLOW_exp_in_factor742 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_RPAR_in_factor744 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_factor753 = new BitSet(new long[]{0x0000000044000002L});
	public static final BitSet FOLLOW_LANGLE_in_factor777 = new BitSet(new long[]{0x0000000062100040L});
	public static final BitSet FOLLOW_type_in_factor788 = new BitSet(new long[]{0x0000080000000400L});
	public static final BitSet FOLLOW_COMMA_in_factor798 = new BitSet(new long[]{0x0000000062100040L});
	public static final BitSet FOLLOW_type_in_factor802 = new BitSet(new long[]{0x0000080000000400L});
	public static final BitSet FOLLOW_RANGLE_in_factor815 = new BitSet(new long[]{0x0000000040000002L});
	public static final BitSet FOLLOW_LPAR_in_factor833 = new BitSet(new long[]{0x00043544C0C64000L});
	public static final BitSet FOLLOW_exp_in_factor844 = new BitSet(new long[]{0x0000200000000400L});
	public static final BitSet FOLLOW_COMMA_in_factor853 = new BitSet(new long[]{0x00041544C0C64000L});
	public static final BitSet FOLLOW_exp_in_factor857 = new BitSet(new long[]{0x0000200000000400L});
	public static final BitSet FOLLOW_RPAR_in_factor873 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IF_in_factor889 = new BitSet(new long[]{0x00041544C0C64000L});
	public static final BitSet FOLLOW_exp_in_factor893 = new BitSet(new long[]{0x0001000000000000L});
	public static final BitSet FOLLOW_THEN_in_factor895 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_CLPAR_in_factor897 = new BitSet(new long[]{0x00041544C0C64000L});
	public static final BitSet FOLLOW_exp_in_factor901 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_CRPAR_in_factor903 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_ELSE_in_factor914 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_CLPAR_in_factor916 = new BitSet(new long[]{0x00041544C0C64000L});
	public static final BitSet FOLLOW_exp_in_factor920 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_CRPAR_in_factor922 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LSQRPAR_in_factor932 = new BitSet(new long[]{0x00041544C0C64000L});
	public static final BitSet FOLLOW_exp_in_factor936 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_LSTCONS_in_factor938 = new BitSet(new long[]{0x00041544C0C64000L});
	public static final BitSet FOLLOW_exp_in_factor942 = new BitSet(new long[]{0x0000400000000000L});
	public static final BitSet FOLLOW_RSQRPAR_in_factor944 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PIPE_in_factor953 = new BitSet(new long[]{0x00041544C0C64000L});
	public static final BitSet FOLLOW_exp_in_factor963 = new BitSet(new long[]{0x0000010000000400L});
	public static final BitSet FOLLOW_COMMA_in_factor971 = new BitSet(new long[]{0x00041544C0C64000L});
	public static final BitSet FOLLOW_exp_in_factor975 = new BitSet(new long[]{0x0000010000000400L});
	public static final BitSet FOLLOW_PIPE_in_factor986 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FIRST_in_factor993 = new BitSet(new long[]{0x0000000040000000L});
	public static final BitSet FOLLOW_LPAR_in_factor995 = new BitSet(new long[]{0x00041544C0C64000L});
	public static final BitSet FOLLOW_exp_in_factor999 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_RPAR_in_factor1001 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_REST_in_factor1010 = new BitSet(new long[]{0x0000000040000000L});
	public static final BitSet FOLLOW_LPAR_in_factor1012 = new BitSet(new long[]{0x00041544C0C64000L});
	public static final BitSet FOLLOW_exp_in_factor1016 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_RPAR_in_factor1018 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PRINT_in_factor1027 = new BitSet(new long[]{0x0000000040000000L});
	public static final BitSet FOLLOW_LPAR_in_factor1029 = new BitSet(new long[]{0x00041544C0C64000L});
	public static final BitSet FOLLOW_exp_in_factor1033 = new BitSet(new long[]{0x0000200000000000L});
	public static final BitSet FOLLOW_RPAR_in_factor1035 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INTTYPE_in_type1056 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_BOOLTYPE_in_type1063 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LISTTYPE_in_type1070 = new BitSet(new long[]{0x0000000080000000L});
	public static final BitSet FOLLOW_LSQRPAR_in_type1072 = new BitSet(new long[]{0x0000000062100040L});
	public static final BitSet FOLLOW_type_in_type1076 = new BitSet(new long[]{0x0000400000000000L});
	public static final BitSet FOLLOW_RSQRPAR_in_type1078 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LPAR_in_type1086 = new BitSet(new long[]{0x0000200062100040L});
	public static final BitSet FOLLOW_type_in_type1097 = new BitSet(new long[]{0x0000200000000400L});
	public static final BitSet FOLLOW_COMMA_in_type1106 = new BitSet(new long[]{0x0000000062100040L});
	public static final BitSet FOLLOW_type_in_type1110 = new BitSet(new long[]{0x0000200000000400L});
	public static final BitSet FOLLOW_RPAR_in_type1128 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_ARROW_in_type1130 = new BitSet(new long[]{0x0000000062100040L});
	public static final BitSet FOLLOW_type_in_type1134 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_GENERICTYPE_in_type1144 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_OPENCOMMENT_in_comment1157 = new BitSet(new long[]{0x001FFFFFFFFFFFF0L});
	public static final BitSet FOLLOW_CLOSECOMMENT_in_comment1165 = new BitSet(new long[]{0x0000000000000002L});
}
