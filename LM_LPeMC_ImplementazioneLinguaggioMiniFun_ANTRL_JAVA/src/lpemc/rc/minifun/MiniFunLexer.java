// $ANTLR 3.5.1 /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g 2014-02-13 23:21:31

package lpemc.rc.minifun;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class MiniFunLexer extends Lexer {
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
	 private boolean incomment = false; 

	// delegates
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public MiniFunLexer() {} 
	public MiniFunLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public MiniFunLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "/mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g"; }

	// $ANTLR start "LET"
	public final void mLET() throws RecognitionException {
		try {
			int _type = LET;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:374:5: ( 'let' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:374:7: 'let'
			{
			match("let"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LET"

	// $ANTLR start "IN"
	public final void mIN() throws RecognitionException {
		try {
			int _type = IN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:375:4: ( 'in' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:375:6: 'in'
			{
			match("in"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "IN"

	// $ANTLR start "IF"
	public final void mIF() throws RecognitionException {
		try {
			int _type = IF;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:376:4: ( 'if' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:376:6: 'if'
			{
			match("if"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "IF"

	// $ANTLR start "THEN"
	public final void mTHEN() throws RecognitionException {
		try {
			int _type = THEN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:377:6: ( 'then' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:377:8: 'then'
			{
			match("then"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "THEN"

	// $ANTLR start "ELSE"
	public final void mELSE() throws RecognitionException {
		try {
			int _type = ELSE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:378:6: ( 'else' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:378:8: 'else'
			{
			match("else"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ELSE"

	// $ANTLR start "PRINT"
	public final void mPRINT() throws RecognitionException {
		try {
			int _type = PRINT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:379:7: ( 'print' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:379:9: 'print'
			{
			match("print"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PRINT"

	// $ANTLR start "VAR"
	public final void mVAR() throws RecognitionException {
		try {
			int _type = VAR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:380:6: ( 'var' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:380:8: 'var'
			{
			match("var"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "VAR"

	// $ANTLR start "FUN"
	public final void mFUN() throws RecognitionException {
		try {
			int _type = FUN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:383:6: ( 'fun' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:383:8: 'fun'
			{
			match("fun"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FUN"

	// $ANTLR start "ARROW"
	public final void mARROW() throws RecognitionException {
		try {
			int _type = ARROW;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:384:7: ( '->' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:384:9: '->'
			{
			match("->"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ARROW"

	// $ANTLR start "LANGLE"
	public final void mLANGLE() throws RecognitionException {
		try {
			int _type = LANGLE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:386:8: ( '<' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:386:10: '<'
			{
			match('<'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LANGLE"

	// $ANTLR start "RANGLE"
	public final void mRANGLE() throws RecognitionException {
		try {
			int _type = RANGLE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:387:8: ( '>' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:387:10: '>'
			{
			match('>'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RANGLE"

	// $ANTLR start "FIRST"
	public final void mFIRST() throws RecognitionException {
		try {
			int _type = FIRST;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:390:9: ( 'first' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:390:13: 'first'
			{
			match("first"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FIRST"

	// $ANTLR start "REST"
	public final void mREST() throws RecognitionException {
		try {
			int _type = REST;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:391:9: ( 'rest' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:391:13: 'rest'
			{
			match("rest"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "REST"

	// $ANTLR start "EMPTY"
	public final void mEMPTY() throws RecognitionException {
		try {
			int _type = EMPTY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:392:9: ( 'empty' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:392:13: 'empty'
			{
			match("empty"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EMPTY"

	// $ANTLR start "LSTCONS"
	public final void mLSTCONS() throws RecognitionException {
		try {
			int _type = LSTCONS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:393:9: ( '::' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:393:13: '::'
			{
			match("::"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LSTCONS"

	// $ANTLR start "LSQRPAR"
	public final void mLSQRPAR() throws RecognitionException {
		try {
			int _type = LSQRPAR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:394:9: ( '[' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:394:13: '['
			{
			match('['); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LSQRPAR"

	// $ANTLR start "RSQRPAR"
	public final void mRSQRPAR() throws RecognitionException {
		try {
			int _type = RSQRPAR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:395:9: ( ']' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:395:13: ']'
			{
			match(']'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RSQRPAR"

	// $ANTLR start "PIPE"
	public final void mPIPE() throws RecognitionException {
		try {
			int _type = PIPE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:396:9: ( '|' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:396:13: '|'
			{
			match('|'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PIPE"

	// $ANTLR start "INTTYPE"
	public final void mINTTYPE() throws RecognitionException {
		try {
			int _type = INTTYPE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:400:9: ( 'int' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:400:12: 'int'
			{
			match("int"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INTTYPE"

	// $ANTLR start "BOOLTYPE"
	public final void mBOOLTYPE() throws RecognitionException {
		try {
			int _type = BOOLTYPE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:401:9: ( 'bool' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:401:11: 'bool'
			{
			match("bool"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "BOOLTYPE"

	// $ANTLR start "LISTTYPE"
	public final void mLISTTYPE() throws RecognitionException {
		try {
			int _type = LISTTYPE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:402:9: ( 'list' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:402:13: 'list'
			{
			match("list"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LISTTYPE"

	// $ANTLR start "NAT"
	public final void mNAT() throws RecognitionException {
		try {
			int _type = NAT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:403:5: ( ( '1' .. '9' ) ( '0' .. '9' )* | '0' )
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( ((LA2_0 >= '1' && LA2_0 <= '9')) ) {
				alt2=1;
			}
			else if ( (LA2_0=='0') ) {
				alt2=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 2, 0, input);
				throw nvae;
			}

			switch (alt2) {
				case 1 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:403:7: ( '1' .. '9' ) ( '0' .. '9' )*
					{
					if ( (input.LA(1) >= '1' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:403:17: ( '0' .. '9' )*
					loop1:
					while (true) {
						int alt1=2;
						int LA1_0 = input.LA(1);
						if ( ((LA1_0 >= '0' && LA1_0 <= '9')) ) {
							alt1=1;
						}

						switch (alt1) {
						case 1 :
							// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:
							{
							if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							break loop1;
						}
					}

					}
					break;
				case 2 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:403:31: '0'
					{
					match('0'); 
					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NAT"

	// $ANTLR start "TRUE"
	public final void mTRUE() throws RecognitionException {
		try {
			int _type = TRUE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:404:6: ( 'true' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:404:8: 'true'
			{
			match("true"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TRUE"

	// $ANTLR start "FALSE"
	public final void mFALSE() throws RecognitionException {
		try {
			int _type = FALSE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:405:7: ( 'false' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:405:9: 'false'
			{
			match("false"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FALSE"

	// $ANTLR start "ASS"
	public final void mASS() throws RecognitionException {
		try {
			int _type = ASS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:411:5: ( '=' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:411:7: '='
			{
			match('='); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ASS"

	// $ANTLR start "EQ"
	public final void mEQ() throws RecognitionException {
		try {
			int _type = EQ;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:412:4: ( '==' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:412:6: '=='
			{
			match("=="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EQ"

	// $ANTLR start "LEQ"
	public final void mLEQ() throws RecognitionException {
		try {
			int _type = LEQ;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:413:5: ( '<=' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:413:7: '<='
			{
			match("<="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LEQ"

	// $ANTLR start "GEQ"
	public final void mGEQ() throws RecognitionException {
		try {
			int _type = GEQ;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:414:5: ( '>=' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:414:7: '>='
			{
			match(">="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "GEQ"

	// $ANTLR start "PLUS"
	public final void mPLUS() throws RecognitionException {
		try {
			int _type = PLUS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:416:6: ( '+' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:416:8: '+'
			{
			match('+'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PLUS"

	// $ANTLR start "MINUS"
	public final void mMINUS() throws RecognitionException {
		try {
			int _type = MINUS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:417:7: ( '-' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:417:9: '-'
			{
			match('-'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MINUS"

	// $ANTLR start "L_OR"
	public final void mL_OR() throws RecognitionException {
		try {
			int _type = L_OR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:418:6: ( '||' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:418:8: '||'
			{
			match("||"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "L_OR"

	// $ANTLR start "TIMES"
	public final void mTIMES() throws RecognitionException {
		try {
			int _type = TIMES;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:420:7: ( '*' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:420:9: '*'
			{
			match('*'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TIMES"

	// $ANTLR start "MODULO"
	public final void mMODULO() throws RecognitionException {
		try {
			int _type = MODULO;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:421:7: ( '%' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:421:9: '%'
			{
			match('%'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MODULO"

	// $ANTLR start "DIV"
	public final void mDIV() throws RecognitionException {
		try {
			int _type = DIV;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:422:5: ( '/' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:422:7: '/'
			{
			match('/'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DIV"

	// $ANTLR start "L_AND"
	public final void mL_AND() throws RecognitionException {
		try {
			int _type = L_AND;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:423:7: ( '&&' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:423:9: '&&'
			{
			match("&&"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "L_AND"

	// $ANTLR start "L_NOT"
	public final void mL_NOT() throws RecognitionException {
		try {
			int _type = L_NOT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:424:7: ( 'not' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:424:9: 'not'
			{
			match("not"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "L_NOT"

	// $ANTLR start "COMMA"
	public final void mCOMMA() throws RecognitionException {
		try {
			int _type = COMMA;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:427:7: ( ',' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:427:9: ','
			{
			match(','); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COMMA"

	// $ANTLR start "COL"
	public final void mCOL() throws RecognitionException {
		try {
			int _type = COL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:428:5: ( ':' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:428:7: ':'
			{
			match(':'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COL"

	// $ANTLR start "SEMIC"
	public final void mSEMIC() throws RecognitionException {
		try {
			int _type = SEMIC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:429:7: ( ';' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:429:9: ';'
			{
			match(';'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SEMIC"

	// $ANTLR start "LPAR"
	public final void mLPAR() throws RecognitionException {
		try {
			int _type = LPAR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:430:6: ( '(' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:430:8: '('
			{
			match('('); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LPAR"

	// $ANTLR start "RPAR"
	public final void mRPAR() throws RecognitionException {
		try {
			int _type = RPAR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:431:6: ( ')' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:431:8: ')'
			{
			match(')'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RPAR"

	// $ANTLR start "CLPAR"
	public final void mCLPAR() throws RecognitionException {
		try {
			int _type = CLPAR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:432:7: ( '{' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:432:9: '{'
			{
			match('{'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CLPAR"

	// $ANTLR start "CRPAR"
	public final void mCRPAR() throws RecognitionException {
		try {
			int _type = CRPAR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:433:7: ( '}' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:433:9: '}'
			{
			match('}'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CRPAR"

	// $ANTLR start "GENERICTYPE"
	public final void mGENERICTYPE() throws RecognitionException {
		try {
			int _type = GENERICTYPE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:436:13: ( ( 'A' .. 'Z' ) )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:
			{
			if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "GENERICTYPE"

	// $ANTLR start "ID"
	public final void mID() throws RecognitionException {
		try {
			int _type = ID;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:437:5: ( ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )* )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:437:7: ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )*
			{
			if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:437:26: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )*
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( ((LA3_0 >= '0' && LA3_0 <= '9')||(LA3_0 >= 'A' && LA3_0 <= 'Z')||(LA3_0 >= 'a' && LA3_0 <= 'z')) ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop3;
				}
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ID"

	// $ANTLR start "WHITESPACES"
	public final void mWHITESPACES() throws RecognitionException {
		try {
			int _type = WHITESPACES;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:440:2: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:440:4: ( ' ' | '\\t' | '\\r' | '\\n' )+
			{
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:440:4: ( ' ' | '\\t' | '\\r' | '\\n' )+
			int cnt4=0;
			loop4:
			while (true) {
				int alt4=2;
				int LA4_0 = input.LA(1);
				if ( ((LA4_0 >= '\t' && LA4_0 <= '\n')||LA4_0=='\r'||LA4_0==' ') ) {
					alt4=1;
				}

				switch (alt4) {
				case 1 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:
					{
					if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt4 >= 1 ) break loop4;
					EarlyExitException eee = new EarlyExitException(4, input);
					throw eee;
				}
				cnt4++;
			}

			 skip(); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WHITESPACES"

	// $ANTLR start "OPENCOMMENT"
	public final void mOPENCOMMENT() throws RecognitionException {
		try {
			int _type = OPENCOMMENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:442:13: ( '/*' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:442:15: '/*'
			{
			match("/*"); 

			 incomment=true; 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "OPENCOMMENT"

	// $ANTLR start "CLOSECOMMENT"
	public final void mCLOSECOMMENT() throws RecognitionException {
		try {
			int _type = CLOSECOMMENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:443:14: ( '*/' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:443:16: '*/'
			{
			match("*/"); 

			 incomment=false; 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CLOSECOMMENT"

	// $ANTLR start "ERR"
	public final void mERR() throws RecognitionException {
		try {
			int _type = ERR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:446:5: ( . )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:446:7: .
			{
			matchAny(); 
			 
			    if(!incomment){
			      System.out.println("Invalid char: "+getText()); 
			      throw new RuntimeException("LEXER ERROR: token not recognized");
			    }  

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ERR"

	@Override
	public void mTokens() throws RecognitionException {
		// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:8: ( LET | IN | IF | THEN | ELSE | PRINT | VAR | FUN | ARROW | LANGLE | RANGLE | FIRST | REST | EMPTY | LSTCONS | LSQRPAR | RSQRPAR | PIPE | INTTYPE | BOOLTYPE | LISTTYPE | NAT | TRUE | FALSE | ASS | EQ | LEQ | GEQ | PLUS | MINUS | L_OR | TIMES | MODULO | DIV | L_AND | L_NOT | COMMA | COL | SEMIC | LPAR | RPAR | CLPAR | CRPAR | GENERICTYPE | ID | WHITESPACES | OPENCOMMENT | CLOSECOMMENT | ERR )
		int alt5=49;
		alt5 = dfa5.predict(input);
		switch (alt5) {
			case 1 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:10: LET
				{
				mLET(); 

				}
				break;
			case 2 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:14: IN
				{
				mIN(); 

				}
				break;
			case 3 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:17: IF
				{
				mIF(); 

				}
				break;
			case 4 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:20: THEN
				{
				mTHEN(); 

				}
				break;
			case 5 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:25: ELSE
				{
				mELSE(); 

				}
				break;
			case 6 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:30: PRINT
				{
				mPRINT(); 

				}
				break;
			case 7 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:36: VAR
				{
				mVAR(); 

				}
				break;
			case 8 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:40: FUN
				{
				mFUN(); 

				}
				break;
			case 9 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:44: ARROW
				{
				mARROW(); 

				}
				break;
			case 10 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:50: LANGLE
				{
				mLANGLE(); 

				}
				break;
			case 11 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:57: RANGLE
				{
				mRANGLE(); 

				}
				break;
			case 12 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:64: FIRST
				{
				mFIRST(); 

				}
				break;
			case 13 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:70: REST
				{
				mREST(); 

				}
				break;
			case 14 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:75: EMPTY
				{
				mEMPTY(); 

				}
				break;
			case 15 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:81: LSTCONS
				{
				mLSTCONS(); 

				}
				break;
			case 16 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:89: LSQRPAR
				{
				mLSQRPAR(); 

				}
				break;
			case 17 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:97: RSQRPAR
				{
				mRSQRPAR(); 

				}
				break;
			case 18 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:105: PIPE
				{
				mPIPE(); 

				}
				break;
			case 19 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:110: INTTYPE
				{
				mINTTYPE(); 

				}
				break;
			case 20 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:118: BOOLTYPE
				{
				mBOOLTYPE(); 

				}
				break;
			case 21 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:127: LISTTYPE
				{
				mLISTTYPE(); 

				}
				break;
			case 22 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:136: NAT
				{
				mNAT(); 

				}
				break;
			case 23 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:140: TRUE
				{
				mTRUE(); 

				}
				break;
			case 24 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:145: FALSE
				{
				mFALSE(); 

				}
				break;
			case 25 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:151: ASS
				{
				mASS(); 

				}
				break;
			case 26 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:155: EQ
				{
				mEQ(); 

				}
				break;
			case 27 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:158: LEQ
				{
				mLEQ(); 

				}
				break;
			case 28 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:162: GEQ
				{
				mGEQ(); 

				}
				break;
			case 29 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:166: PLUS
				{
				mPLUS(); 

				}
				break;
			case 30 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:171: MINUS
				{
				mMINUS(); 

				}
				break;
			case 31 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:177: L_OR
				{
				mL_OR(); 

				}
				break;
			case 32 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:182: TIMES
				{
				mTIMES(); 

				}
				break;
			case 33 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:188: MODULO
				{
				mMODULO(); 

				}
				break;
			case 34 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:195: DIV
				{
				mDIV(); 

				}
				break;
			case 35 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:199: L_AND
				{
				mL_AND(); 

				}
				break;
			case 36 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:205: L_NOT
				{
				mL_NOT(); 

				}
				break;
			case 37 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:211: COMMA
				{
				mCOMMA(); 

				}
				break;
			case 38 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:217: COL
				{
				mCOL(); 

				}
				break;
			case 39 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:221: SEMIC
				{
				mSEMIC(); 

				}
				break;
			case 40 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:227: LPAR
				{
				mLPAR(); 

				}
				break;
			case 41 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:232: RPAR
				{
				mRPAR(); 

				}
				break;
			case 42 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:237: CLPAR
				{
				mCLPAR(); 

				}
				break;
			case 43 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:243: CRPAR
				{
				mCRPAR(); 

				}
				break;
			case 44 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:249: GENERICTYPE
				{
				mGENERICTYPE(); 

				}
				break;
			case 45 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:261: ID
				{
				mID(); 

				}
				break;
			case 46 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:264: WHITESPACES
				{
				mWHITESPACES(); 

				}
				break;
			case 47 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:276: OPENCOMMENT
				{
				mOPENCOMMENT(); 

				}
				break;
			case 48 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:288: CLOSECOMMENT
				{
				mCLOSECOMMENT(); 

				}
				break;
			case 49 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/MiniFun.g:1:301: ERR
				{
				mERR(); 

				}
				break;

		}
	}


	protected DFA5 dfa5 = new DFA5(this);
	static final String DFA5_eotS =
		"\1\uffff\7\46\1\63\1\65\1\67\1\46\1\72\2\uffff\1\76\1\46\2\uffff\1\102"+
		"\1\uffff\1\105\1\uffff\1\110\1\43\1\46\6\uffff\1\121\3\uffff\2\46\1\uffff"+
		"\1\126\1\127\11\46\6\uffff\1\46\6\uffff\1\46\12\uffff\1\46\10\uffff\1"+
		"\144\1\46\1\146\2\uffff\5\46\1\154\1\155\4\46\1\162\1\uffff\1\163\1\uffff"+
		"\1\164\1\165\1\166\2\46\2\uffff\2\46\1\173\1\174\5\uffff\1\175\1\176\1"+
		"\177\1\u0080\6\uffff";
	static final String DFA5_eofS =
		"\u0081\uffff";
	static final String DFA5_minS =
		"\1\0\1\145\1\146\1\150\1\154\1\162\2\141\1\76\2\75\1\145\1\72\2\uffff"+
		"\1\174\1\157\2\uffff\1\75\1\uffff\1\57\1\uffff\1\52\1\46\1\157\6\uffff"+
		"\1\60\3\uffff\1\164\1\163\1\uffff\2\60\1\145\1\165\1\163\1\160\1\151\1"+
		"\162\1\156\1\162\1\154\6\uffff\1\163\6\uffff\1\157\12\uffff\1\164\10\uffff"+
		"\1\60\1\164\1\60\2\uffff\1\156\2\145\1\164\1\156\2\60\2\163\1\164\1\154"+
		"\1\60\1\uffff\1\60\1\uffff\3\60\1\171\1\164\2\uffff\1\164\1\145\2\60\5"+
		"\uffff\4\60\6\uffff";
	static final String DFA5_maxS =
		"\1\uffff\1\151\1\156\1\162\1\155\1\162\1\141\1\165\1\76\2\75\1\145\1\72"+
		"\2\uffff\1\174\1\157\2\uffff\1\75\1\uffff\1\57\1\uffff\1\52\1\46\1\157"+
		"\6\uffff\1\172\3\uffff\1\164\1\163\1\uffff\2\172\1\145\1\165\1\163\1\160"+
		"\1\151\1\162\1\156\1\162\1\154\6\uffff\1\163\6\uffff\1\157\12\uffff\1"+
		"\164\10\uffff\1\172\1\164\1\172\2\uffff\1\156\2\145\1\164\1\156\2\172"+
		"\2\163\1\164\1\154\1\172\1\uffff\1\172\1\uffff\3\172\1\171\1\164\2\uffff"+
		"\1\164\1\145\2\172\5\uffff\4\172\6\uffff";
	static final String DFA5_acceptS =
		"\15\uffff\1\20\1\21\2\uffff\2\26\1\uffff\1\35\1\uffff\1\41\3\uffff\1\45"+
		"\1\47\1\50\1\51\1\52\1\53\1\uffff\1\55\1\56\1\61\2\uffff\1\55\13\uffff"+
		"\1\11\1\36\1\33\1\12\1\34\1\13\1\uffff\1\17\1\46\1\20\1\21\1\37\1\22\1"+
		"\uffff\1\26\1\32\1\31\1\35\1\60\1\40\1\41\1\57\1\42\1\43\1\uffff\1\45"+
		"\1\47\1\50\1\51\1\52\1\53\1\54\1\56\3\uffff\1\2\1\3\14\uffff\1\1\1\uffff"+
		"\1\23\5\uffff\1\7\1\10\4\uffff\1\44\1\25\1\4\1\27\1\5\4\uffff\1\15\1\24"+
		"\1\16\1\6\1\14\1\30";
	static final String DFA5_specialS =
		"\1\0\u0080\uffff}>";
	static final String[] DFA5_transitionS = {
			"\11\43\2\42\2\43\1\42\22\43\1\42\4\43\1\26\1\30\1\43\1\34\1\35\1\25\1"+
			"\24\1\32\1\10\1\43\1\27\1\22\11\21\1\14\1\33\1\11\1\23\1\12\2\43\32\40"+
			"\1\15\1\43\1\16\3\43\1\41\1\20\2\41\1\4\1\7\2\41\1\2\2\41\1\1\1\41\1"+
			"\31\1\41\1\5\1\41\1\13\1\41\1\3\1\41\1\6\4\41\1\36\1\17\1\37\uff82\43",
			"\1\44\3\uffff\1\45",
			"\1\50\7\uffff\1\47",
			"\1\51\11\uffff\1\52",
			"\1\53\1\54",
			"\1\55",
			"\1\56",
			"\1\61\7\uffff\1\60\13\uffff\1\57",
			"\1\62",
			"\1\64",
			"\1\66",
			"\1\70",
			"\1\71",
			"",
			"",
			"\1\75",
			"\1\77",
			"",
			"",
			"\1\101",
			"",
			"\1\104",
			"",
			"\1\107",
			"\1\111",
			"\1\112",
			"",
			"",
			"",
			"",
			"",
			"",
			"\12\46\7\uffff\32\46\6\uffff\32\46",
			"",
			"",
			"",
			"\1\123",
			"\1\124",
			"",
			"\12\46\7\uffff\32\46\6\uffff\23\46\1\125\6\46",
			"\12\46\7\uffff\32\46\6\uffff\32\46",
			"\1\130",
			"\1\131",
			"\1\132",
			"\1\133",
			"\1\134",
			"\1\135",
			"\1\136",
			"\1\137",
			"\1\140",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\141",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\142",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\143",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\12\46\7\uffff\32\46\6\uffff\32\46",
			"\1\145",
			"\12\46\7\uffff\32\46\6\uffff\32\46",
			"",
			"",
			"\1\147",
			"\1\150",
			"\1\151",
			"\1\152",
			"\1\153",
			"\12\46\7\uffff\32\46\6\uffff\32\46",
			"\12\46\7\uffff\32\46\6\uffff\32\46",
			"\1\156",
			"\1\157",
			"\1\160",
			"\1\161",
			"\12\46\7\uffff\32\46\6\uffff\32\46",
			"",
			"\12\46\7\uffff\32\46\6\uffff\32\46",
			"",
			"\12\46\7\uffff\32\46\6\uffff\32\46",
			"\12\46\7\uffff\32\46\6\uffff\32\46",
			"\12\46\7\uffff\32\46\6\uffff\32\46",
			"\1\167",
			"\1\170",
			"",
			"",
			"\1\171",
			"\1\172",
			"\12\46\7\uffff\32\46\6\uffff\32\46",
			"\12\46\7\uffff\32\46\6\uffff\32\46",
			"",
			"",
			"",
			"",
			"",
			"\12\46\7\uffff\32\46\6\uffff\32\46",
			"\12\46\7\uffff\32\46\6\uffff\32\46",
			"\12\46\7\uffff\32\46\6\uffff\32\46",
			"\12\46\7\uffff\32\46\6\uffff\32\46",
			"",
			"",
			"",
			"",
			"",
			""
	};

	static final short[] DFA5_eot = DFA.unpackEncodedString(DFA5_eotS);
	static final short[] DFA5_eof = DFA.unpackEncodedString(DFA5_eofS);
	static final char[] DFA5_min = DFA.unpackEncodedStringToUnsignedChars(DFA5_minS);
	static final char[] DFA5_max = DFA.unpackEncodedStringToUnsignedChars(DFA5_maxS);
	static final short[] DFA5_accept = DFA.unpackEncodedString(DFA5_acceptS);
	static final short[] DFA5_special = DFA.unpackEncodedString(DFA5_specialS);
	static final short[][] DFA5_transition;

	static {
		int numStates = DFA5_transitionS.length;
		DFA5_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA5_transition[i] = DFA.unpackEncodedString(DFA5_transitionS[i]);
		}
	}

	protected class DFA5 extends DFA {

		public DFA5(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 5;
			this.eot = DFA5_eot;
			this.eof = DFA5_eof;
			this.min = DFA5_min;
			this.max = DFA5_max;
			this.accept = DFA5_accept;
			this.special = DFA5_special;
			this.transition = DFA5_transition;
		}
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( LET | IN | IF | THEN | ELSE | PRINT | VAR | FUN | ARROW | LANGLE | RANGLE | FIRST | REST | EMPTY | LSTCONS | LSQRPAR | RSQRPAR | PIPE | INTTYPE | BOOLTYPE | LISTTYPE | NAT | TRUE | FALSE | ASS | EQ | LEQ | GEQ | PLUS | MINUS | L_OR | TIMES | MODULO | DIV | L_AND | L_NOT | COMMA | COL | SEMIC | LPAR | RPAR | CLPAR | CRPAR | GENERICTYPE | ID | WHITESPACES | OPENCOMMENT | CLOSECOMMENT | ERR );";
		}
		@Override
		public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
			IntStream input = _input;
			int _s = s;
			switch ( s ) {
					case 0 : 
						int LA5_0 = input.LA(1);
						s = -1;
						if ( (LA5_0=='l') ) {s = 1;}
						else if ( (LA5_0=='i') ) {s = 2;}
						else if ( (LA5_0=='t') ) {s = 3;}
						else if ( (LA5_0=='e') ) {s = 4;}
						else if ( (LA5_0=='p') ) {s = 5;}
						else if ( (LA5_0=='v') ) {s = 6;}
						else if ( (LA5_0=='f') ) {s = 7;}
						else if ( (LA5_0=='-') ) {s = 8;}
						else if ( (LA5_0=='<') ) {s = 9;}
						else if ( (LA5_0=='>') ) {s = 10;}
						else if ( (LA5_0=='r') ) {s = 11;}
						else if ( (LA5_0==':') ) {s = 12;}
						else if ( (LA5_0=='[') ) {s = 13;}
						else if ( (LA5_0==']') ) {s = 14;}
						else if ( (LA5_0=='|') ) {s = 15;}
						else if ( (LA5_0=='b') ) {s = 16;}
						else if ( ((LA5_0 >= '1' && LA5_0 <= '9')) ) {s = 17;}
						else if ( (LA5_0=='0') ) {s = 18;}
						else if ( (LA5_0=='=') ) {s = 19;}
						else if ( (LA5_0=='+') ) {s = 20;}
						else if ( (LA5_0=='*') ) {s = 21;}
						else if ( (LA5_0=='%') ) {s = 22;}
						else if ( (LA5_0=='/') ) {s = 23;}
						else if ( (LA5_0=='&') ) {s = 24;}
						else if ( (LA5_0=='n') ) {s = 25;}
						else if ( (LA5_0==',') ) {s = 26;}
						else if ( (LA5_0==';') ) {s = 27;}
						else if ( (LA5_0=='(') ) {s = 28;}
						else if ( (LA5_0==')') ) {s = 29;}
						else if ( (LA5_0=='{') ) {s = 30;}
						else if ( (LA5_0=='}') ) {s = 31;}
						else if ( ((LA5_0 >= 'A' && LA5_0 <= 'Z')) ) {s = 32;}
						else if ( (LA5_0=='a'||(LA5_0 >= 'c' && LA5_0 <= 'd')||(LA5_0 >= 'g' && LA5_0 <= 'h')||(LA5_0 >= 'j' && LA5_0 <= 'k')||LA5_0=='m'||LA5_0=='o'||LA5_0=='q'||LA5_0=='s'||LA5_0=='u'||(LA5_0 >= 'w' && LA5_0 <= 'z')) ) {s = 33;}
						else if ( ((LA5_0 >= '\t' && LA5_0 <= '\n')||LA5_0=='\r'||LA5_0==' ') ) {s = 34;}
						else if ( ((LA5_0 >= '\u0000' && LA5_0 <= '\b')||(LA5_0 >= '\u000B' && LA5_0 <= '\f')||(LA5_0 >= '\u000E' && LA5_0 <= '\u001F')||(LA5_0 >= '!' && LA5_0 <= '$')||LA5_0=='\''||LA5_0=='.'||(LA5_0 >= '?' && LA5_0 <= '@')||LA5_0=='\\'||(LA5_0 >= '^' && LA5_0 <= '`')||(LA5_0 >= '~' && LA5_0 <= '\uFFFF')) ) {s = 35;}
						if ( s>=0 ) return s;
						break;
			}
			NoViableAltException nvae =
				new NoViableAltException(getDescription(), 5, _s, input);
			error(nvae);
			throw nvae;
		}
	}

}
