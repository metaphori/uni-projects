// $ANTLR 3.5.1 /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g 2014-02-13 23:21:29

    package lpemc.rc.vm;

    import java.util.HashMap;
    import java.util.ArrayList;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class VMParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "ADD", "AND", "BRANCH", "BRANCHEQUAL", 
		"BRANCHGREATER", "BRANCHLESS", "CLOSECOMMENT", "COL", "COPYTOFRAMEPT", 
		"DIV", "ERR", "HALT", "JUMPS", "LABEL", "LOADFRAMEPT", "LOADHEAPPT", "LOADRETADDR", 
		"LOADRETVAL", "LOADTEMP", "LOADWORD", "MOD", "MULT", "NOT", "NUMBER", 
		"OPENCOMMENT", "OR", "POP", "PRINT", "PUSH", "STOREFRAMEPT", "STOREHEAPPT", 
		"STORERETADDR", "STORERETVAL", "STORETEMP", "STOREWORD", "SUB", "WHITESP", 
		"XOR"
	};
	public static final int EOF=-1;
	public static final int ADD=4;
	public static final int AND=5;
	public static final int BRANCH=6;
	public static final int BRANCHEQUAL=7;
	public static final int BRANCHGREATER=8;
	public static final int BRANCHLESS=9;
	public static final int CLOSECOMMENT=10;
	public static final int COL=11;
	public static final int COPYTOFRAMEPT=12;
	public static final int DIV=13;
	public static final int ERR=14;
	public static final int HALT=15;
	public static final int JUMPS=16;
	public static final int LABEL=17;
	public static final int LOADFRAMEPT=18;
	public static final int LOADHEAPPT=19;
	public static final int LOADRETADDR=20;
	public static final int LOADRETVAL=21;
	public static final int LOADTEMP=22;
	public static final int LOADWORD=23;
	public static final int MOD=24;
	public static final int MULT=25;
	public static final int NOT=26;
	public static final int NUMBER=27;
	public static final int OPENCOMMENT=28;
	public static final int OR=29;
	public static final int POP=30;
	public static final int PRINT=31;
	public static final int PUSH=32;
	public static final int STOREFRAMEPT=33;
	public static final int STOREHEAPPT=34;
	public static final int STORERETADDR=35;
	public static final int STORERETVAL=36;
	public static final int STORETEMP=37;
	public static final int STOREWORD=38;
	public static final int SUB=39;
	public static final int WHITESP=40;
	public static final int XOR=41;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public VMParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public VMParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	@Override public String[] getTokenNames() { return VMParser.tokenNames; }
	@Override public String getGrammarFileName() { return "/mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g"; }


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



	// $ANTLR start "assembly"
	// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:37:1: assembly : ( OPENCOMMENT (~ CLOSECOMMENT )* CLOSECOMMENT | PUSH e= NUMBER | PUSH e= LABEL | STOREWORD | LOADWORD | STORERETADDR | LOADRETADDR | STORERETVAL | LOADRETVAL | STOREHEAPPT | LOADHEAPPT | STOREFRAMEPT | LOADFRAMEPT | STORETEMP | LOADTEMP | COPYTOFRAMEPT | POP | ADD | SUB | MULT | DIV | OR | XOR | MOD | NOT | AND |e= LABEL COL | BRANCH e= LABEL | BRANCHLESS e= LABEL | BRANCHGREATER e= LABEL | BRANCHEQUAL e= LABEL | JUMPS | PRINT | HALT )* ;
	public final void assembly() throws RecognitionException {
		Token e=null;

		try {
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:37:9: ( ( OPENCOMMENT (~ CLOSECOMMENT )* CLOSECOMMENT | PUSH e= NUMBER | PUSH e= LABEL | STOREWORD | LOADWORD | STORERETADDR | LOADRETADDR | STORERETVAL | LOADRETVAL | STOREHEAPPT | LOADHEAPPT | STOREFRAMEPT | LOADFRAMEPT | STORETEMP | LOADTEMP | COPYTOFRAMEPT | POP | ADD | SUB | MULT | DIV | OR | XOR | MOD | NOT | AND |e= LABEL COL | BRANCH e= LABEL | BRANCHLESS e= LABEL | BRANCHGREATER e= LABEL | BRANCHEQUAL e= LABEL | JUMPS | PRINT | HALT )* )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:37:11: ( OPENCOMMENT (~ CLOSECOMMENT )* CLOSECOMMENT | PUSH e= NUMBER | PUSH e= LABEL | STOREWORD | LOADWORD | STORERETADDR | LOADRETADDR | STORERETVAL | LOADRETVAL | STOREHEAPPT | LOADHEAPPT | STOREFRAMEPT | LOADFRAMEPT | STORETEMP | LOADTEMP | COPYTOFRAMEPT | POP | ADD | SUB | MULT | DIV | OR | XOR | MOD | NOT | AND |e= LABEL COL | BRANCH e= LABEL | BRANCHLESS e= LABEL | BRANCHGREATER e= LABEL | BRANCHEQUAL e= LABEL | JUMPS | PRINT | HALT )*
			{
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:37:11: ( OPENCOMMENT (~ CLOSECOMMENT )* CLOSECOMMENT | PUSH e= NUMBER | PUSH e= LABEL | STOREWORD | LOADWORD | STORERETADDR | LOADRETADDR | STORERETVAL | LOADRETVAL | STOREHEAPPT | LOADHEAPPT | STOREFRAMEPT | LOADFRAMEPT | STORETEMP | LOADTEMP | COPYTOFRAMEPT | POP | ADD | SUB | MULT | DIV | OR | XOR | MOD | NOT | AND |e= LABEL COL | BRANCH e= LABEL | BRANCHLESS e= LABEL | BRANCHGREATER e= LABEL | BRANCHEQUAL e= LABEL | JUMPS | PRINT | HALT )*
			loop2:
			while (true) {
				int alt2=35;
				switch ( input.LA(1) ) {
				case OPENCOMMENT:
					{
					alt2=1;
					}
					break;
				case PUSH:
					{
					int LA2_3 = input.LA(2);
					if ( (LA2_3==NUMBER) ) {
						alt2=2;
					}
					else if ( (LA2_3==LABEL) ) {
						alt2=3;
					}

					}
					break;
				case STOREWORD:
					{
					alt2=4;
					}
					break;
				case LOADWORD:
					{
					alt2=5;
					}
					break;
				case STORERETADDR:
					{
					alt2=6;
					}
					break;
				case LOADRETADDR:
					{
					alt2=7;
					}
					break;
				case STORERETVAL:
					{
					alt2=8;
					}
					break;
				case LOADRETVAL:
					{
					alt2=9;
					}
					break;
				case STOREHEAPPT:
					{
					alt2=10;
					}
					break;
				case LOADHEAPPT:
					{
					alt2=11;
					}
					break;
				case STOREFRAMEPT:
					{
					alt2=12;
					}
					break;
				case LOADFRAMEPT:
					{
					alt2=13;
					}
					break;
				case STORETEMP:
					{
					alt2=14;
					}
					break;
				case LOADTEMP:
					{
					alt2=15;
					}
					break;
				case COPYTOFRAMEPT:
					{
					alt2=16;
					}
					break;
				case POP:
					{
					alt2=17;
					}
					break;
				case ADD:
					{
					alt2=18;
					}
					break;
				case SUB:
					{
					alt2=19;
					}
					break;
				case MULT:
					{
					alt2=20;
					}
					break;
				case DIV:
					{
					alt2=21;
					}
					break;
				case OR:
					{
					alt2=22;
					}
					break;
				case XOR:
					{
					alt2=23;
					}
					break;
				case MOD:
					{
					alt2=24;
					}
					break;
				case NOT:
					{
					alt2=25;
					}
					break;
				case AND:
					{
					alt2=26;
					}
					break;
				case LABEL:
					{
					alt2=27;
					}
					break;
				case BRANCH:
					{
					alt2=28;
					}
					break;
				case BRANCHLESS:
					{
					alt2=29;
					}
					break;
				case BRANCHGREATER:
					{
					alt2=30;
					}
					break;
				case BRANCHEQUAL:
					{
					alt2=31;
					}
					break;
				case JUMPS:
					{
					alt2=32;
					}
					break;
				case PRINT:
					{
					alt2=33;
					}
					break;
				case HALT:
					{
					alt2=34;
					}
					break;
				}
				switch (alt2) {
				case 1 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:38:7: OPENCOMMENT (~ CLOSECOMMENT )* CLOSECOMMENT
					{
					match(input,OPENCOMMENT,FOLLOW_OPENCOMMENT_in_assembly52); 
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:38:19: (~ CLOSECOMMENT )*
					loop1:
					while (true) {
						int alt1=2;
						int LA1_0 = input.LA(1);
						if ( ((LA1_0 >= ADD && LA1_0 <= BRANCHLESS)||(LA1_0 >= COL && LA1_0 <= XOR)) ) {
							alt1=1;
						}

						switch (alt1) {
						case 1 :
							// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:
							{
							if ( (input.LA(1) >= ADD && input.LA(1) <= BRANCHLESS)||(input.LA(1) >= COL && input.LA(1) <= XOR) ) {
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
							break loop1;
						}
					}

					match(input,CLOSECOMMENT,FOLLOW_CLOSECOMMENT_in_assembly60); 
					}
					break;
				case 2 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:39:7: PUSH e= NUMBER
					{
					match(input,PUSH,FOLLOW_PUSH_in_assembly69); 
					e=(Token)match(input,NUMBER,FOLLOW_NUMBER_in_assembly73); 
					 code[i++] = PUSH; 
								  code[i++] = Integer.parseInt((e!=null?e.getText():null)); 
					}
					break;
				case 3 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:42:5: PUSH e= LABEL
					{
					match(input,PUSH,FOLLOW_PUSH_in_assembly90); 
					e=(Token)match(input,LABEL,FOLLOW_LABEL_in_assembly94); 
					 code[i++] = PUSH; 
							    labels.add((e!=null?e.getText():null)); 
							    addresses.add(new Integer(i)); 
							    code[i++] = 0; 
					}
					break;
				case 4 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:47:5: STOREWORD
					{
					match(input,STOREWORD,FOLLOW_STOREWORD_in_assembly107); 
					 code[i++] = STOREWORD; 
					}
					break;
				case 5 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:48:5: LOADWORD
					{
					match(input,LOADWORD,FOLLOW_LOADWORD_in_assembly115); 
					 code[i++] = LOADWORD; 
					}
					break;
				case 6 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:49:7: STORERETADDR
					{
					match(input,STORERETADDR,FOLLOW_STORERETADDR_in_assembly125); 
					 code[i++] = STORERETADDR; 
					}
					break;
				case 7 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:50:7: LOADRETADDR
					{
					match(input,LOADRETADDR,FOLLOW_LOADRETADDR_in_assembly135); 
					 code[i++] = LOADRETADDR; 
					}
					break;
				case 8 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:51:7: STORERETVAL
					{
					match(input,STORERETVAL,FOLLOW_STORERETVAL_in_assembly145); 
					 code[i++] = STORERETVAL; 
					}
					break;
				case 9 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:52:7: LOADRETVAL
					{
					match(input,LOADRETVAL,FOLLOW_LOADRETVAL_in_assembly155); 
					 code[i++] = LOADRETVAL; 
					}
					break;
				case 10 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:53:7: STOREHEAPPT
					{
					match(input,STOREHEAPPT,FOLLOW_STOREHEAPPT_in_assembly165); 
					 code[i++] = STOREHEAPPT; 
					}
					break;
				case 11 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:54:7: LOADHEAPPT
					{
					match(input,LOADHEAPPT,FOLLOW_LOADHEAPPT_in_assembly175); 
					 code[i++] = LOADHEAPPT; 
					}
					break;
				case 12 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:55:7: STOREFRAMEPT
					{
					match(input,STOREFRAMEPT,FOLLOW_STOREFRAMEPT_in_assembly185); 
					 code[i++] = STOREFRAMEPT; 
					}
					break;
				case 13 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:56:7: LOADFRAMEPT
					{
					match(input,LOADFRAMEPT,FOLLOW_LOADFRAMEPT_in_assembly195); 
					 code[i++] = LOADFRAMEPT; 
					}
					break;
				case 14 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:57:7: STORETEMP
					{
					match(input,STORETEMP,FOLLOW_STORETEMP_in_assembly209); 
					 code[i++] = STORETEMP; 
					}
					break;
				case 15 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:58:7: LOADTEMP
					{
					match(input,LOADTEMP,FOLLOW_LOADTEMP_in_assembly219); 
					 code[i++] = LOADTEMP; 
					}
					break;
				case 16 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:59:7: COPYTOFRAMEPT
					{
					match(input,COPYTOFRAMEPT,FOLLOW_COPYTOFRAMEPT_in_assembly234); 
					 code[i++] = COPYTOFRAMEPT; 
					}
					break;
				case 17 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:60:6: POP
					{
					match(input,POP,FOLLOW_POP_in_assembly243); 
					code[i++] = POP;
					}
					break;
				case 18 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:61:6: ADD
					{
					match(input,ADD,FOLLOW_ADD_in_assembly258); 
					code[i++] = ADD;
					}
					break;
				case 19 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:62:6: SUB
					{
					match(input,SUB,FOLLOW_SUB_in_assembly272); 
					code[i++] = SUB;
					}
					break;
				case 20 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:63:6: MULT
					{
					match(input,MULT,FOLLOW_MULT_in_assembly286); 
					code[i++] = MULT;
					}
					break;
				case 21 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:64:7: DIV
					{
					match(input,DIV,FOLLOW_DIV_in_assembly300); 
					code[i++] = DIV;
					}
					break;
				case 22 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:65:7: OR
					{
					match(input,OR,FOLLOW_OR_in_assembly316); 
					code[i++] = OR;
					}
					break;
				case 23 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:66:7: XOR
					{
					match(input,XOR,FOLLOW_XOR_in_assembly332); 
					code[i++] = XOR;
					}
					break;
				case 24 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:67:7: MOD
					{
					match(input,MOD,FOLLOW_MOD_in_assembly347); 
					code[i++] = MOD;
					}
					break;
				case 25 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:68:7: NOT
					{
					match(input,NOT,FOLLOW_NOT_in_assembly362); 
					code[i++] = NOT;
					}
					break;
				case 26 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:69:7: AND
					{
					match(input,AND,FOLLOW_AND_in_assembly377); 
					code[i++] = AND;
					}
					break;
				case 27 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:70:6: e= LABEL COL
					{
					e=(Token)match(input,LABEL,FOLLOW_LABEL_in_assembly397); 
					match(input,COL,FOLLOW_COL_in_assembly399); 
					 if(labelAddress.containsKey((e!=null?e.getText():null)))
						        System.out.println("Label '" + (e!=null?e.getText():null) + "' is overridden by a subsequent declaration");
						      labelAddress.put((e!=null?e.getText():null),new Integer(i));
						    
					}
					break;
				case 28 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:75:6: BRANCH e= LABEL
					{
					match(input,BRANCH,FOLLOW_BRANCH_in_assembly418); 
					e=(Token)match(input,LABEL,FOLLOW_LABEL_in_assembly422); 
					  code[i++] = BRANCH;
						  		 labels.add((e!=null?e.getText():null));
						  		 addresses.add(new Integer(i)); 
						  		 code[i++] = 0; 
					}
					break;
				case 29 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:80:6: BRANCHLESS e= LABEL
					{
					match(input,BRANCHLESS,FOLLOW_BRANCHLESS_in_assembly438); 
					e=(Token)match(input,LABEL,FOLLOW_LABEL_in_assembly442); 
					  code[i++] = BRANCHLESS;
						       labels.add((e!=null?e.getText():null));
						       addresses.add(new Integer(i)); 
						  		 code[i++] = 0; 
					}
					break;
				case 30 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:85:6: BRANCHGREATER e= LABEL
					{
					match(input,BRANCHGREATER,FOLLOW_BRANCHGREATER_in_assembly457); 
					e=(Token)match(input,LABEL,FOLLOW_LABEL_in_assembly461); 
					  code[i++] = BRANCHGREATER;
					         labels.add((e!=null?e.getText():null));
					         addresses.add(new Integer(i)); 
					         code[i++] = 0; 
					}
					break;
				case 31 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:90:6: BRANCHEQUAL e= LABEL
					{
					match(input,BRANCHEQUAL,FOLLOW_BRANCHEQUAL_in_assembly483); 
					e=(Token)match(input,LABEL,FOLLOW_LABEL_in_assembly487); 
					 code[i++] = BRANCHEQUAL;
						      labels.add((e!=null?e.getText():null));
						      addresses.add(new Integer(i)); 
						      code[i++] = 0; 
					}
					break;
				case 32 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:95:7: JUMPS
					{
					match(input,JUMPS,FOLLOW_JUMPS_in_assembly503); 
					code[i++] = JUMPS; 
					}
					break;
				case 33 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:96:6: PRINT
					{
					match(input,PRINT,FOLLOW_PRINT_in_assembly522); 
					code[i++] = PRINT;
					}
					break;
				case 34 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:97:6: HALT
					{
					match(input,HALT,FOLLOW_HALT_in_assembly541); 
					code[i++] = HALT;
					}
					break;

				default :
					break loop2;
				}
			}

			  for (int ind=0; ind<labels.size(); ind++) {
			        	  Integer t = labelAddress.get(labels.get(ind));
			        	  Integer a = addresses.get(ind);
			        	  code[a.intValue()]=t.intValue(); 
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
	}
	// $ANTLR end "assembly"

	// Delegated rules



	public static final BitSet FOLLOW_OPENCOMMENT_in_assembly52 = new BitSet(new long[]{0x000003FFFFFFFFF0L});
	public static final BitSet FOLLOW_CLOSECOMMENT_in_assembly60 = new BitSet(new long[]{0x000002FFF7FFB3F2L});
	public static final BitSet FOLLOW_PUSH_in_assembly69 = new BitSet(new long[]{0x0000000008000000L});
	public static final BitSet FOLLOW_NUMBER_in_assembly73 = new BitSet(new long[]{0x000002FFF7FFB3F2L});
	public static final BitSet FOLLOW_PUSH_in_assembly90 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_LABEL_in_assembly94 = new BitSet(new long[]{0x000002FFF7FFB3F2L});
	public static final BitSet FOLLOW_STOREWORD_in_assembly107 = new BitSet(new long[]{0x000002FFF7FFB3F2L});
	public static final BitSet FOLLOW_LOADWORD_in_assembly115 = new BitSet(new long[]{0x000002FFF7FFB3F2L});
	public static final BitSet FOLLOW_STORERETADDR_in_assembly125 = new BitSet(new long[]{0x000002FFF7FFB3F2L});
	public static final BitSet FOLLOW_LOADRETADDR_in_assembly135 = new BitSet(new long[]{0x000002FFF7FFB3F2L});
	public static final BitSet FOLLOW_STORERETVAL_in_assembly145 = new BitSet(new long[]{0x000002FFF7FFB3F2L});
	public static final BitSet FOLLOW_LOADRETVAL_in_assembly155 = new BitSet(new long[]{0x000002FFF7FFB3F2L});
	public static final BitSet FOLLOW_STOREHEAPPT_in_assembly165 = new BitSet(new long[]{0x000002FFF7FFB3F2L});
	public static final BitSet FOLLOW_LOADHEAPPT_in_assembly175 = new BitSet(new long[]{0x000002FFF7FFB3F2L});
	public static final BitSet FOLLOW_STOREFRAMEPT_in_assembly185 = new BitSet(new long[]{0x000002FFF7FFB3F2L});
	public static final BitSet FOLLOW_LOADFRAMEPT_in_assembly195 = new BitSet(new long[]{0x000002FFF7FFB3F2L});
	public static final BitSet FOLLOW_STORETEMP_in_assembly209 = new BitSet(new long[]{0x000002FFF7FFB3F2L});
	public static final BitSet FOLLOW_LOADTEMP_in_assembly219 = new BitSet(new long[]{0x000002FFF7FFB3F2L});
	public static final BitSet FOLLOW_COPYTOFRAMEPT_in_assembly234 = new BitSet(new long[]{0x000002FFF7FFB3F2L});
	public static final BitSet FOLLOW_POP_in_assembly243 = new BitSet(new long[]{0x000002FFF7FFB3F2L});
	public static final BitSet FOLLOW_ADD_in_assembly258 = new BitSet(new long[]{0x000002FFF7FFB3F2L});
	public static final BitSet FOLLOW_SUB_in_assembly272 = new BitSet(new long[]{0x000002FFF7FFB3F2L});
	public static final BitSet FOLLOW_MULT_in_assembly286 = new BitSet(new long[]{0x000002FFF7FFB3F2L});
	public static final BitSet FOLLOW_DIV_in_assembly300 = new BitSet(new long[]{0x000002FFF7FFB3F2L});
	public static final BitSet FOLLOW_OR_in_assembly316 = new BitSet(new long[]{0x000002FFF7FFB3F2L});
	public static final BitSet FOLLOW_XOR_in_assembly332 = new BitSet(new long[]{0x000002FFF7FFB3F2L});
	public static final BitSet FOLLOW_MOD_in_assembly347 = new BitSet(new long[]{0x000002FFF7FFB3F2L});
	public static final BitSet FOLLOW_NOT_in_assembly362 = new BitSet(new long[]{0x000002FFF7FFB3F2L});
	public static final BitSet FOLLOW_AND_in_assembly377 = new BitSet(new long[]{0x000002FFF7FFB3F2L});
	public static final BitSet FOLLOW_LABEL_in_assembly397 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_COL_in_assembly399 = new BitSet(new long[]{0x000002FFF7FFB3F2L});
	public static final BitSet FOLLOW_BRANCH_in_assembly418 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_LABEL_in_assembly422 = new BitSet(new long[]{0x000002FFF7FFB3F2L});
	public static final BitSet FOLLOW_BRANCHLESS_in_assembly438 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_LABEL_in_assembly442 = new BitSet(new long[]{0x000002FFF7FFB3F2L});
	public static final BitSet FOLLOW_BRANCHGREATER_in_assembly457 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_LABEL_in_assembly461 = new BitSet(new long[]{0x000002FFF7FFB3F2L});
	public static final BitSet FOLLOW_BRANCHEQUAL_in_assembly483 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_LABEL_in_assembly487 = new BitSet(new long[]{0x000002FFF7FFB3F2L});
	public static final BitSet FOLLOW_JUMPS_in_assembly503 = new BitSet(new long[]{0x000002FFF7FFB3F2L});
	public static final BitSet FOLLOW_PRINT_in_assembly522 = new BitSet(new long[]{0x000002FFF7FFB3F2L});
	public static final BitSet FOLLOW_HALT_in_assembly541 = new BitSet(new long[]{0x000002FFF7FFB3F2L});
}
