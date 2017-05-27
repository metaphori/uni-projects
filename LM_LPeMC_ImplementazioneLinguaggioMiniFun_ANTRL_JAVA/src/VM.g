grammar VM;

@header {
    package lpemc.rc.vm;

    import java.util.HashMap;
    import java.util.ArrayList;
}

@members {
    private int[] code = new int[ExecuteVM.CODESIZE];    
    
    // instruction index
    private int i = 0;
    
    private ArrayList<String> labels = new ArrayList<String>();
    
    // addresses of label definitions
    private HashMap<String,Integer> labelAddress = new HashMap<String,Integer>();    
    // keep track at addresses where label-def-addresses must be injected into
    private ArrayList<Integer> addresses  = new ArrayList<Integer>();
    
    public int[] createCode() throws Exception {
       assembly();
       return code;
    }
}

@lexer::header { package lpemc.rc.vm; }

@lexer::members { private boolean incomment = false; } 

/*------------------------------------------------------------------
 * PARSER RULES
 *------------------------------------------------------------------*/

assembly: ( 
      OPENCOMMENT (~CLOSECOMMENT)* CLOSECOMMENT // for comments
    | PUSH e=NUMBER   
      { code[i++] = PUSH; 
			  code[i++] = Integer.parseInt($e.text); }
		| PUSH e=LABEL 
		  { code[i++] = PUSH; 
		    labels.add($e.text); 
		    addresses.add(new Integer(i)); 
		    code[i++] = 0; }
		| STOREWORD { code[i++] = STOREWORD; }
		| LOADWORD { code[i++] = LOADWORD; }
    | STORERETADDR { code[i++] = STORERETADDR; }
    | LOADRETADDR { code[i++] = LOADRETADDR; }
    | STORERETVAL { code[i++] = STORERETVAL; }
    | LOADRETVAL { code[i++] = LOADRETVAL; }
    | STOREHEAPPT { code[i++] = STOREHEAPPT; }
    | LOADHEAPPT { code[i++] = LOADHEAPPT; }
    | STOREFRAMEPT { code[i++] = STOREFRAMEPT; }
    | LOADFRAMEPT { code[i++] = LOADFRAMEPT; }    
    | STORETEMP { code[i++] = STORETEMP; }
    | LOADTEMP { code[i++] = LOADTEMP; }     
    | COPYTOFRAMEPT { code[i++] = COPYTOFRAMEPT; }
	  | POP		    {code[i++] = POP;}	
	  | ADD		    {code[i++] = ADD;}
	  | SUB		    {code[i++] = SUB;}
	  | MULT	    {code[i++] = MULT;}
    | DIV       {code[i++] = DIV;}
    | OR       {code[i++] = OR;}
    | XOR      {code[i++] = XOR;}
    | MOD      {code[i++] = MOD;}
    | NOT      {code[i++] = NOT;}
    | AND       {code[i++] = AND;}	  
	  | e=LABEL COL     
	    { if(labelAddress.containsKey($e.text))
	        System.out.println("Label '" + $e.text + "' is overridden by a subsequent declaration");
	      labelAddress.put($e.text,new Integer(i));
	    }
	  | BRANCH e=LABEL  
	    {  code[i++] = BRANCH;
	  		 labels.add($e.text);
	  		 addresses.add(new Integer(i)); 
	  		 code[i++] = 0; }
	  | BRANCHLESS e=LABEL 
	    {  code[i++] = BRANCHLESS;
	       labels.add($e.text);
	       addresses.add(new Integer(i)); 
	  		 code[i++] = 0; }
	  | BRANCHGREATER e=LABEL 
      {  code[i++] = BRANCHGREATER;
         labels.add($e.text);
         addresses.add(new Integer(i)); 
         code[i++] = 0; }	  		 
	  | BRANCHEQUAL e=LABEL 
	    { code[i++] = BRANCHEQUAL;
	      labels.add($e.text);
	      addresses.add(new Integer(i)); 
	      code[i++] = 0; }
    | JUMPS           {code[i++] = JUMPS; }
	  | PRINT           {code[i++] = PRINT;}
	  | HALT            {code[i++] = HALT;}
	  )* {  for (int ind=0; ind<labels.size(); ind++) {
        	  Integer t = labelAddress.get(labels.get(ind));
        	  Integer a = addresses.get(ind);
        	  code[a.intValue()]=t.intValue(); 
          }
       }
 ;
 	 
/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/

PUSH  	 : 'push' ; 	// constant in the stack
STOREWORD   : 'sw' ;
LOADWORD    : 'lw' ;
STORERETADDR : 'sra';
LOADRETADDR : 'lra';
STORERETVAL : 'srv';
LOADRETVAL : 'lrv';
STOREFRAMEPT : 'sfp';
LOADFRAMEPT : 'lfp';
COPYTOFRAMEPT : 'cfp';
STOREHEAPPT : 'shp';
LOADHEAPPT : 'lhp';
STORETEMP : 'stemp';
LOADTEMP : 'ltemp';
POP	 : 'pop' ; 	// removes top of stack
ADD	 : 'add' ;  	// add two values from the stack
SUB	 : 'sub' ;	// sub two values from the stack
MULT	 : 'mult' ;  	// mult two values from the stack
DIV	 : 'div' ;	// div two values from the stack
MOD  : 'mod' ;
OR   : 'or' ;
NOT  : 'not';
AND  : 'and' ;
XOR  : 'xor';
BRANCH	 : 'b' ;	// jump to label
BRANCHLESS:'bless' ;	// jump to label if top <= next
BRANCHGREATER:'bgt'; // jump to label if top >= next
BRANCHEQUAL:'beq';
JUMPS: 'js';
PRINT	 : 'print' ;	// print top of stack
HALT	 : 'halt' ;	// stop executions

OPENCOMMENT : '/*' { incomment=true; } ;
CLOSECOMMENT : '*/'{ incomment=false; };

COL	 : ':' ;
LABEL	 : ('a'..'z'|'A'..'Z')('a'..'z' | 'A'..'Z' | '0'..'9')* ;
NUMBER	 : '0' | ('-')?(('1'..'9')('0'..'9')*) ;

WHITESP  : ( '\t' | ' ' | '\r' | '\n' )+    { skip(); } ;
 
ERR   	 : . 
  { 
    if(!incomment){
      System.out.println("Invalid char: "+$text); 
      throw new RuntimeException("ERROR DURING LEXING");
    }
  } ; 
