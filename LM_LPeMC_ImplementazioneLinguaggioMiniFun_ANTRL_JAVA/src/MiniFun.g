grammar MiniFun;

@header {
package lpemc.rc.minifun;

import lpemc.rc.minifun.ast.*;
import java.util.HashMap;
import java.util.ArrayList;
import lpemc.rc.minifun.TypeSystem.TMappings;
}

@lexer::header {
package lpemc.rc.minifun;
}
@lexer::members { private boolean incomment = false; } 

@members {
  /* approach to symbol table: LIST OF TABLES */
  protected ArrayList<HashMap<String,STEntry>> symbolTable  = new ArrayList<HashMap<String,STEntry>>();
	protected int nesting_level = 0;
	protected int var_offset = 1;
	
	protected java.util.Stack<Integer> varoffsets = new java.util.Stack<Integer>();
	
	protected String currFunction = null;
}

/*------------------------------------------------------------------
 * PARSER RULES
 *------------------------------------------------------------------*/

/*prog  returns [Node ast]
  : LET { symbolTable.add( new HashMap<String,STEntry>() );  } // symbol table for globals
    d=declist
    IN e=exp SEMIC
    { $ast = new ProgNode($d.astList, $e.ast); } ;
*/

prog returns [Node ast]
  : { 
    var_offset = 1; 
    symbolTable.add( new HashMap<String,STEntry>() );  /* adding symbol table for globals */
    varoffsets.push(new Integer(1));
    }
    lb=letBlock { $ast = new ProgNode($lb.ast); } ;

letBlock	returns [Node ast]
	:	LET 
		d=declist
		IN e=exp SEMIC
		{ 
		  $ast = new LetBlockNode($d.astList, $e.ast); 
		} ;
		
declist	returns [ArrayList<IdentifierDeclNode> astList] 
	:	{ 
	    $astList = new ArrayList<IdentifierDeclNode>(); 
	    HashMap<String,STEntry> currTable = symbolTable.get(nesting_level);
	    
	    var_offset = varoffsets.peek();
	    //System.out.println("Var offset at start of this declist is " + var_offset);
	  } 
		// variable declaration
		(VAR id=ID COL t=type ASS e=exp SEMIC 
		{ 
			VarDeclNode vdn = new VarDeclNode($id.text, $t.ast, $e.ast);
			$astList.add(vdn);
			
			STEntry entry = new STEntry(vdn, var_offset++);
			entry.setProperty("kind", "var");
			
			if($t.ast instanceof FunTypeNode){
			  // se la variabile è di tipo funzione (i.e. è un riferimento a funzione)
			  // allora incrementiamo l'offset per fare posto alla sua chiusura
			  var_offset++;
			  //System.out.println("Var " + $id.text + " is of a function type");
			  entry.setProperty("kind", "fun");
			}
			
			// update the top of stack's var_offset level
      varoffsets.pop();
      varoffsets.push(var_offset);		
			
			STEntry previous = currTable.put($id.text, entry);
			if(previous!=null){
			  	// error: identifier already declared
			  	System.out.println("Parsing error: multiple decl for identifier " + $id.text +
			   	 " at line " + $id.line);
			   	System.exit(1);
			}
		} 
		|
		/* ************************ */
		/* * function declaration * */
		/* ************************ */
		{ ArrayList<GenericTypeNode> genTypes = new ArrayList<GenericTypeNode>(); }
		FUN id=ID 
		(LANGLE gentype=GENERICTYPE { genTypes.add(new GenericTypeNode($gentype.text)); } 
		  (COMMA gentype_n=GENERICTYPE { genTypes.add(new GenericTypeNode($gentype.text)); })* 
		RANGLE)? 
		COL t=type
			LPAR 
			{ 
				FunDeclNode funNode = new FunDeclNode($id.text, $t.ast);
				currFunction = $id.text;
				funNode.setGenericTypes(genTypes);
				
				// the following line was meant to copy declarations of upper levels
				//  to the set of local declarations of this level
				//  so that function references returned as values would be able
				//  to access to variables that may be gone out of scope at the moment of call
				//funNode.copyToLocals(symbolTable, nesting_level, varoffsets);
				
				//System.out.println("Fun " + $id.text + " has offset " + var_offset);				
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
				STEntry previous = currTable.put($id.text, entry);
				if(previous!=null){ // error: identifier already declared
					System.out.println("Parsing error: multiple decl for identifier " + $id.text +
					 " at line " + $id.line);
					System.exit(1);
				}
				
				/* create a new symbol table for declarations local to the function */
				HashMap<String,STEntry> funSymbolTable = new HashMap<String,STEntry>(); 
				symbolTable.add(funSymbolTable);
				nesting_level++;
				ArrayList<FunArgNode> argNodes = new ArrayList<FunArgNode>();
				FunArgNode argnode;
				int arg_offset = -1;
			}
			(arg=ID COL argt=type // first argument
			{
			  argnode = new FunArgNode($arg.text, $argt.ast, funNode);
			  argNodes.add(argnode);
				STEntry fentry = null;
				
				/*
				fentry.setProperty("kind", $argt.ast.getKind());
				arg_offset -= $argt.ast.getNumOfElems();
				*/
				if($argt.ast instanceof FunTypeNode){
				  fentry = new STEntry(argnode, arg_offset-1);
				  fentry.setProperty("kind","fun");
				  arg_offset--;
				  arg_offset--; // se l'arg è di tipo funzione, tengo un posto per la closure
				} else{
				  fentry = new STEntry(argnode, arg_offset--);
				  fentry.setProperty("kind","var");
				}
				
				previous = funSymbolTable.put($arg.text, fentry);
			}
			
			(COMMA narg=ID COL nargt=type
			{
				argnode = new FunArgNode($narg.text, $nargt.ast, funNode);
				argNodes.add(argnode);
				STEntry entry_n = null;
        
        if($nargt.ast instanceof FunTypeNode){
          //System.out.println("Setting offset of " + $narg.text + " to " + (arg_offset-1));
          entry_n = new STEntry(argnode, arg_offset-1);
          entry_n.setProperty("kind","fun");
          arg_offset--;
          arg_offset--; // se l'arg è di tipo funzione, tengo un posto per la closure
        } else{
          entry_n = new STEntry(argnode, arg_offset--);
          entry_n.setProperty("kind","var");
        }
        
        /* Check for multiple declaration of a param */
				previous = funSymbolTable.put($narg.text, entry_n);
				if(previous!=null){
					// error: identifier already declared
					System.out.println("Parsing error: multiple decl for parameter " + $id.text +
					 " at line " + $id.line);
					System.exit(1);
				}
			}
			)*)? 
			RPAR CLPAR 
			(e=exp 
			  | 
			 {varoffsets.push(2); }  
			 e=letBlock { varoffsets.pop(); var_offset = varoffsets.peek(); } 
			) CRPAR
			  /* NOTE: nel nuovo let block le definizioni in declist saranno variabili/funzioni locali alla funzione.
			  La funzione, generando il codice del suo corpo (il blocco LET..IN) posizionerà i valori delle dichiarazioni
			  dopo il "return address" e prima del corpo della funzione (sempre generato attraverso generazione codice del
			  blocco LET..IN e, in particolare, la parte relativa all'espressione)
			  ====> per questo motivo l'offset inizia da 2 */
			  /* NOTE: non incrementiamo il nesting_level per il letBlock, che userà la stessa symbol table della
			    funzione */
			SEMIC {
				funNode.setParams(argNodes);
				funNode.setBody($e.ast);
				symbolTable.remove(nesting_level--); 
				$astList.add(funNode);
			}
		 |
		 /* fuori dalle dichiarazioni posso mettere anche commenti */
		 comment
		 )* ;
		 

 	 
exp	returns [Node ast]
	:	v = value { $ast = $v.ast; }
		(EQ vnext=value { $ast = new EqNode($ast, $vnext.ast); }
		  |
		(LEQ vnext=value { $ast = new LessEqNode($ast, $vnext.ast); })
		  |
		(GEQ vnext=value { $ast = new GreaterEqNode($ast, $vnext.ast); })  
		)*               // per opzionalita' e per possibilita' di ripetere
                     // associativita' a sinistra per default
    ;
                     
value	returns [Node ast]
	:	t=term	{ $ast =  $t.ast; }
		(PLUS tnext=term { $ast = new PlusNode($ast, $tnext.ast); } 
		  |
		MINUS tnext=term { $ast = new MinusNode($ast, $tnext.ast); }
		  |
		L_OR tnext=term { $ast = new ORNode($ast, $tnext.ast); } 
		)* ;

term	returns [Node ast]
	:	f=factor { $ast = $f.ast;  } 
		(TIMES fnext=factor { $ast = new TimesNode($ast, $fnext.ast); } 
		   |
		DIV fnext=factor { $ast = new DivNode($ast, $fnext.ast); } 
		   |
		L_AND fnext=factor { $ast = new ANDNode($ast, $fnext.ast); } 
		  |
		MODULO fnext=factor { $ast = new MODNode($ast, $fnext.ast); }
		)* ;

factor	returns [Node ast]
	:	n=NAT	{ $ast = new NatNode(Integer.parseInt($n.text)); }
	|	TRUE	{ $ast = new BoolNode(true); }
	|	FALSE	{ $ast = new BoolNode(false); }
	|	EMPTY   { $ast = new ListEmptyNode(); }
	|	LPAR e=exp RPAR { $ast = $e.ast; }
	| L_NOT LPAR e=exp RPAR { $ast = new NOTNode($e.ast); }
	|	id=ID	{ 
				boolean found = false;
				int found_level = nesting_level;
				STEntry found_entry = null;
				
				for(int currlevel = nesting_level; currlevel>=0; currlevel--){
					HashMap<String,STEntry> currTable = symbolTable.get(currlevel);
					
					STEntry entry = currTable.get($id.text);
					if(entry!=null){
						found = true;
						found_level = currlevel;
						found_entry = entry;
						
		        if(currFunction!=null && (nesting_level-found_level)>0 && found_level>0){
		          /*System.out.println(currFunction + " makes use of the following identifier at "+
		            "higher level (but not top-level): " + $id.text);*/
		          // THESE IDENTIFIERS SHOULD BE COPIED TO THE ENVIRONMENT OF ENCLOSING FUN TO ENABLE FULL CLOSURES!! 
		        }						
						
						if(entry.getProperty("kind").equals("var")){
						  $ast = new VarNode(entry, nesting_level-found_level);  
						} else if(entry.getProperty("kind").equals("fun")){
						  $ast = new FunRefNode(entry, nesting_level-found_level);
						}
						break;
					}
				}
			  if(!found){
			    	// error: identifier not declared
			    	System.out.println("Parsing error: identifier not declared '" + $id.text +
			    	 "' used at line " + $id.line);
			    	System.exit(1);				
			  }
			}
		
		// if a part <A,...,Z> is found, two cases are possible
		//    i) generic types are used => we need to delay instantiation
		//    ii) concrete types are used => we can instantiate the generic types
		{ ArrayList<TypeNode> typesInBrackets = new ArrayList<TypeNode>(); 
		  TMappings mappings = null;
		}
		( LANGLE { mappings = null; typesInBrackets.clear(); }
		  (t1=type) { typesInBrackets.add($t1.ast); }
		  (COMMA tn=type { typesInBrackets.add($tn.ast); } )*  
		  RANGLE { 
		    ((FunRefNode)$ast).setTypeParams(typesInBrackets);
		    
		    /*
		    if(TypeSystem.AllTheseTypes(typesInBrackets, TypeSystem.ARE_CONCRETE)){
		      // proceed with instantiation
		      FunDeclNode fdn = (FunDeclNode) ((FunRefNode)$ast).getRelativeDecl();
		      mappings = fdn.createTypeMappings(typesInBrackets);
		      //((FunRefNode)$ast).instantiateGenTypes(mappings); 
		    } 
		    */
		  }
		)?
		
		// *** function call ***
		(LPAR { ArrayList<Node> actualArgs = new ArrayList<Node>(); }
		  (e=exp { 
		    /* first expression (actual argument) */
		    actualArgs.add($e.ast);
		  }
		  (COMMA enext=exp {
		    /* subsequent expression (actual argument) */
		    actualArgs.add($enext.ast);
		  }
		  )*)? 
		RPAR{
		    $ast = new FunCallNode((FunRefNode)$ast, nesting_level-found_level, actualArgs);
        
        /*
        if(mappings!=null)
          $ast.instantiateGenTypes(mappings);
        */
		  }
		  
		)?
	|	IF e1=exp THEN CLPAR e2=exp CRPAR
		       ELSE CLPAR e3=exp CRPAR 
		{ $ast = new IfNode($e1.ast, $e2.ast, $e3.ast); }
	|	LSQRPAR h=exp LSTCONS t=exp RSQRPAR
		{ $ast = new ListConsNode($h.ast, $t.ast); }
	| PIPE { ArrayList<Node> exps = new ArrayList<Node>(); } 
	  e=exp { exps.add($e.ast); }
	  (COMMA en=exp { exps.add($en.ast); } )* 
	  PIPE { $ast = Utils.CreateListFromLiteral(exps); }
	|	FIRST LPAR lst=exp RPAR
		{ $ast = new ListFirstNode($lst.ast); }
	|	REST LPAR lst=exp RPAR
		{ $ast = new ListRestNode($lst.ast); }
	|	PRINT LPAR e=exp RPAR	{ $ast = new PrintNode($e.ast); }
	/*| LAMBDA ft=functionSignature CLPAR e=exp CRPAR { $ast = new LambdaNode($ft.ast, $e.ast); }*/
	;
	
type	returns [TypeNode ast]
	: INTTYPE { $ast = new IntTypeNode(); }
	| BOOLTYPE { $ast = new BoolTypeNode(); }
	| LISTTYPE LSQRPAR t=type RSQRPAR { $ast = new ListTypeNode($t.ast); } 
	| LPAR { ArrayList<TypeNode> argTypes = new ArrayList<TypeNode>(); }
    (argType1=type { argTypes.add($argType1.ast); }
    (COMMA argTypeN=type { argTypes.add($argTypeN.ast); })*
    )? 
    RPAR ARROW retType=type { $ast = new FunTypeNode(argTypes, $retType.ast); }
  | gt=GENERICTYPE { $ast = new GenericTypeNode($gt.text); }  ;
	
comment : OPENCOMMENT (~CLOSECOMMENT)* CLOSECOMMENT ;

/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/

/* keywords */
LET	:	'let';
IN	:	'in';
IF	:	'if';
THEN	:	'then';
ELSE	:	'else';
PRINT	:	'print';
VAR		:	'var';

/* for functions */
FUN		:	'fun';
ARROW : '->' ;
/* for generics */
LANGLE : '<';
RANGLE : '>';

/* for lists */
FIRST   :   'first';
REST    :   'rest';
EMPTY   :   'empty';
LSTCONS :   '::';
LSQRPAR :   '[';
RSQRPAR :   ']';
PIPE    :   '|';


/* types */
INTTYPE	:	 'int';
BOOLTYPE:	'bool';
LISTTYPE:   'list';
NAT	:	('1'..'9')('0'..'9')* | '0';
TRUE	:	'true';
FALSE	:	'false';


/* general */

/* operators */
ASS	:	'=';
EQ	:	'==';
LEQ : '<=';
GEQ : '>=';

PLUS	:	'+';
MINUS : '-';
L_OR : '||';

TIMES	:	'*';
MODULO: '%';
DIV : '/';
L_AND : '&&';
L_NOT : 'not';

/* notation elements */
COMMA :	',';
COL	:	':';
SEMIC	:	';';
LPAR	:	'(';
RPAR	:	')';
CLPAR	:	'{';
CRPAR	:	'}';

// ID priority must be lower with respect to other keywords
GENERICTYPE : ('A'..'Z');
ID  : ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9')* ;

WHITESPACES
	:	(' ' | '\t' | '\r' | '\n')+ { skip(); };

OPENCOMMENT : '/*' { incomment=true; } ;
CLOSECOMMENT : '*/'{ incomment=false; };


ERR	:	. { 
    if(!incomment){
      System.out.println("Invalid char: "+$text); 
      throw new RuntimeException("LEXER ERROR: token not recognized");
    }  
};
