// $ANTLR 3.5.1 /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g 2014-02-13 23:21:29
 package lpemc.rc.vm; 

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class VMLexer extends Lexer {
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
	 private boolean incomment = false; 

	// delegates
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public VMLexer() {} 
	public VMLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public VMLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "/mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g"; }

	// $ANTLR start "PUSH"
	public final void mPUSH() throws RecognitionException {
		try {
			int _type = PUSH;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:110:9: ( 'push' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:110:11: 'push'
			{
			match("push"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PUSH"

	// $ANTLR start "STOREWORD"
	public final void mSTOREWORD() throws RecognitionException {
		try {
			int _type = STOREWORD;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:111:13: ( 'sw' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:111:15: 'sw'
			{
			match("sw"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "STOREWORD"

	// $ANTLR start "LOADWORD"
	public final void mLOADWORD() throws RecognitionException {
		try {
			int _type = LOADWORD;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:112:13: ( 'lw' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:112:15: 'lw'
			{
			match("lw"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LOADWORD"

	// $ANTLR start "STORERETADDR"
	public final void mSTORERETADDR() throws RecognitionException {
		try {
			int _type = STORERETADDR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:113:14: ( 'sra' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:113:16: 'sra'
			{
			match("sra"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "STORERETADDR"

	// $ANTLR start "LOADRETADDR"
	public final void mLOADRETADDR() throws RecognitionException {
		try {
			int _type = LOADRETADDR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:114:13: ( 'lra' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:114:15: 'lra'
			{
			match("lra"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LOADRETADDR"

	// $ANTLR start "STORERETVAL"
	public final void mSTORERETVAL() throws RecognitionException {
		try {
			int _type = STORERETVAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:115:13: ( 'srv' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:115:15: 'srv'
			{
			match("srv"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "STORERETVAL"

	// $ANTLR start "LOADRETVAL"
	public final void mLOADRETVAL() throws RecognitionException {
		try {
			int _type = LOADRETVAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:116:12: ( 'lrv' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:116:14: 'lrv'
			{
			match("lrv"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LOADRETVAL"

	// $ANTLR start "STOREFRAMEPT"
	public final void mSTOREFRAMEPT() throws RecognitionException {
		try {
			int _type = STOREFRAMEPT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:117:14: ( 'sfp' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:117:16: 'sfp'
			{
			match("sfp"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "STOREFRAMEPT"

	// $ANTLR start "LOADFRAMEPT"
	public final void mLOADFRAMEPT() throws RecognitionException {
		try {
			int _type = LOADFRAMEPT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:118:13: ( 'lfp' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:118:15: 'lfp'
			{
			match("lfp"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LOADFRAMEPT"

	// $ANTLR start "COPYTOFRAMEPT"
	public final void mCOPYTOFRAMEPT() throws RecognitionException {
		try {
			int _type = COPYTOFRAMEPT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:119:15: ( 'cfp' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:119:17: 'cfp'
			{
			match("cfp"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COPYTOFRAMEPT"

	// $ANTLR start "STOREHEAPPT"
	public final void mSTOREHEAPPT() throws RecognitionException {
		try {
			int _type = STOREHEAPPT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:120:13: ( 'shp' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:120:15: 'shp'
			{
			match("shp"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "STOREHEAPPT"

	// $ANTLR start "LOADHEAPPT"
	public final void mLOADHEAPPT() throws RecognitionException {
		try {
			int _type = LOADHEAPPT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:121:12: ( 'lhp' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:121:14: 'lhp'
			{
			match("lhp"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LOADHEAPPT"

	// $ANTLR start "STORETEMP"
	public final void mSTORETEMP() throws RecognitionException {
		try {
			int _type = STORETEMP;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:122:11: ( 'stemp' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:122:13: 'stemp'
			{
			match("stemp"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "STORETEMP"

	// $ANTLR start "LOADTEMP"
	public final void mLOADTEMP() throws RecognitionException {
		try {
			int _type = LOADTEMP;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:123:10: ( 'ltemp' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:123:12: 'ltemp'
			{
			match("ltemp"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LOADTEMP"

	// $ANTLR start "POP"
	public final void mPOP() throws RecognitionException {
		try {
			int _type = POP;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:124:6: ( 'pop' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:124:8: 'pop'
			{
			match("pop"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "POP"

	// $ANTLR start "ADD"
	public final void mADD() throws RecognitionException {
		try {
			int _type = ADD;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:125:6: ( 'add' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:125:8: 'add'
			{
			match("add"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ADD"

	// $ANTLR start "SUB"
	public final void mSUB() throws RecognitionException {
		try {
			int _type = SUB;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:126:6: ( 'sub' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:126:8: 'sub'
			{
			match("sub"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SUB"

	// $ANTLR start "MULT"
	public final void mMULT() throws RecognitionException {
		try {
			int _type = MULT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:127:7: ( 'mult' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:127:9: 'mult'
			{
			match("mult"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MULT"

	// $ANTLR start "DIV"
	public final void mDIV() throws RecognitionException {
		try {
			int _type = DIV;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:128:6: ( 'div' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:128:8: 'div'
			{
			match("div"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DIV"

	// $ANTLR start "MOD"
	public final void mMOD() throws RecognitionException {
		try {
			int _type = MOD;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:129:6: ( 'mod' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:129:8: 'mod'
			{
			match("mod"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MOD"

	// $ANTLR start "OR"
	public final void mOR() throws RecognitionException {
		try {
			int _type = OR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:130:6: ( 'or' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:130:8: 'or'
			{
			match("or"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "OR"

	// $ANTLR start "NOT"
	public final void mNOT() throws RecognitionException {
		try {
			int _type = NOT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:131:6: ( 'not' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:131:8: 'not'
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
	// $ANTLR end "NOT"

	// $ANTLR start "AND"
	public final void mAND() throws RecognitionException {
		try {
			int _type = AND;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:132:6: ( 'and' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:132:8: 'and'
			{
			match("and"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "AND"

	// $ANTLR start "XOR"
	public final void mXOR() throws RecognitionException {
		try {
			int _type = XOR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:133:6: ( 'xor' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:133:8: 'xor'
			{
			match("xor"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "XOR"

	// $ANTLR start "BRANCH"
	public final void mBRANCH() throws RecognitionException {
		try {
			int _type = BRANCH;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:134:9: ( 'b' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:134:11: 'b'
			{
			match('b'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "BRANCH"

	// $ANTLR start "BRANCHLESS"
	public final void mBRANCHLESS() throws RecognitionException {
		try {
			int _type = BRANCHLESS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:135:11: ( 'bless' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:135:12: 'bless'
			{
			match("bless"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "BRANCHLESS"

	// $ANTLR start "BRANCHGREATER"
	public final void mBRANCHGREATER() throws RecognitionException {
		try {
			int _type = BRANCHGREATER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:136:14: ( 'bgt' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:136:15: 'bgt'
			{
			match("bgt"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "BRANCHGREATER"

	// $ANTLR start "BRANCHEQUAL"
	public final void mBRANCHEQUAL() throws RecognitionException {
		try {
			int _type = BRANCHEQUAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:137:12: ( 'beq' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:137:13: 'beq'
			{
			match("beq"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "BRANCHEQUAL"

	// $ANTLR start "JUMPS"
	public final void mJUMPS() throws RecognitionException {
		try {
			int _type = JUMPS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:138:6: ( 'js' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:138:8: 'js'
			{
			match("js"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "JUMPS"

	// $ANTLR start "PRINT"
	public final void mPRINT() throws RecognitionException {
		try {
			int _type = PRINT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:139:8: ( 'print' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:139:10: 'print'
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

	// $ANTLR start "HALT"
	public final void mHALT() throws RecognitionException {
		try {
			int _type = HALT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:140:7: ( 'halt' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:140:9: 'halt'
			{
			match("halt"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "HALT"

	// $ANTLR start "OPENCOMMENT"
	public final void mOPENCOMMENT() throws RecognitionException {
		try {
			int _type = OPENCOMMENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:142:13: ( '/*' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:142:15: '/*'
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
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:143:14: ( '*/' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:143:16: '*/'
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

	// $ANTLR start "COL"
	public final void mCOL() throws RecognitionException {
		try {
			int _type = COL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:145:6: ( ':' )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:145:8: ':'
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

	// $ANTLR start "LABEL"
	public final void mLABEL() throws RecognitionException {
		try {
			int _type = LABEL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:146:8: ( ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )* )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:146:10: ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )*
			{
			if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:146:29: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' )*
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( ((LA1_0 >= '0' && LA1_0 <= '9')||(LA1_0 >= 'A' && LA1_0 <= 'Z')||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:
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
					break loop1;
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
	// $ANTLR end "LABEL"

	// $ANTLR start "NUMBER"
	public final void mNUMBER() throws RecognitionException {
		try {
			int _type = NUMBER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:147:9: ( '0' | ( '-' )? ( ( '1' .. '9' ) ( '0' .. '9' )* ) )
			int alt4=2;
			int LA4_0 = input.LA(1);
			if ( (LA4_0=='0') ) {
				alt4=1;
			}
			else if ( (LA4_0=='-'||(LA4_0 >= '1' && LA4_0 <= '9')) ) {
				alt4=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 4, 0, input);
				throw nvae;
			}

			switch (alt4) {
				case 1 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:147:11: '0'
					{
					match('0'); 
					}
					break;
				case 2 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:147:17: ( '-' )? ( ( '1' .. '9' ) ( '0' .. '9' )* )
					{
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:147:17: ( '-' )?
					int alt2=2;
					int LA2_0 = input.LA(1);
					if ( (LA2_0=='-') ) {
						alt2=1;
					}
					switch (alt2) {
						case 1 :
							// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:147:18: '-'
							{
							match('-'); 
							}
							break;

					}

					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:147:23: ( ( '1' .. '9' ) ( '0' .. '9' )* )
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:147:24: ( '1' .. '9' ) ( '0' .. '9' )*
					{
					if ( (input.LA(1) >= '1' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:147:34: ( '0' .. '9' )*
					loop3:
					while (true) {
						int alt3=2;
						int LA3_0 = input.LA(1);
						if ( ((LA3_0 >= '0' && LA3_0 <= '9')) ) {
							alt3=1;
						}

						switch (alt3) {
						case 1 :
							// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:
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
							break loop3;
						}
					}

					}

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
	// $ANTLR end "NUMBER"

	// $ANTLR start "WHITESP"
	public final void mWHITESP() throws RecognitionException {
		try {
			int _type = WHITESP;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:149:10: ( ( '\\t' | ' ' | '\\r' | '\\n' )+ )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:149:12: ( '\\t' | ' ' | '\\r' | '\\n' )+
			{
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:149:12: ( '\\t' | ' ' | '\\r' | '\\n' )+
			int cnt5=0;
			loop5:
			while (true) {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( ((LA5_0 >= '\t' && LA5_0 <= '\n')||LA5_0=='\r'||LA5_0==' ') ) {
					alt5=1;
				}

				switch (alt5) {
				case 1 :
					// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:
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
					if ( cnt5 >= 1 ) break loop5;
					EarlyExitException eee = new EarlyExitException(5, input);
					throw eee;
				}
				cnt5++;
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
	// $ANTLR end "WHITESP"

	// $ANTLR start "ERR"
	public final void mERR() throws RecognitionException {
		try {
			int _type = ERR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:151:9: ( . )
			// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:151:11: .
			{
			matchAny(); 
			 
			    if(!incomment){
			      System.out.println("Invalid char: "+getText()); 
			      throw new RuntimeException("ERROR DURING LEXING");
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
		// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:1:8: ( PUSH | STOREWORD | LOADWORD | STORERETADDR | LOADRETADDR | STORERETVAL | LOADRETVAL | STOREFRAMEPT | LOADFRAMEPT | COPYTOFRAMEPT | STOREHEAPPT | LOADHEAPPT | STORETEMP | LOADTEMP | POP | ADD | SUB | MULT | DIV | MOD | OR | NOT | AND | XOR | BRANCH | BRANCHLESS | BRANCHGREATER | BRANCHEQUAL | JUMPS | PRINT | HALT | OPENCOMMENT | CLOSECOMMENT | COL | LABEL | NUMBER | WHITESP | ERR )
		int alt6=38;
		alt6 = dfa6.predict(input);
		switch (alt6) {
			case 1 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:1:10: PUSH
				{
				mPUSH(); 

				}
				break;
			case 2 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:1:15: STOREWORD
				{
				mSTOREWORD(); 

				}
				break;
			case 3 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:1:25: LOADWORD
				{
				mLOADWORD(); 

				}
				break;
			case 4 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:1:34: STORERETADDR
				{
				mSTORERETADDR(); 

				}
				break;
			case 5 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:1:47: LOADRETADDR
				{
				mLOADRETADDR(); 

				}
				break;
			case 6 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:1:59: STORERETVAL
				{
				mSTORERETVAL(); 

				}
				break;
			case 7 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:1:71: LOADRETVAL
				{
				mLOADRETVAL(); 

				}
				break;
			case 8 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:1:82: STOREFRAMEPT
				{
				mSTOREFRAMEPT(); 

				}
				break;
			case 9 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:1:95: LOADFRAMEPT
				{
				mLOADFRAMEPT(); 

				}
				break;
			case 10 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:1:107: COPYTOFRAMEPT
				{
				mCOPYTOFRAMEPT(); 

				}
				break;
			case 11 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:1:121: STOREHEAPPT
				{
				mSTOREHEAPPT(); 

				}
				break;
			case 12 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:1:133: LOADHEAPPT
				{
				mLOADHEAPPT(); 

				}
				break;
			case 13 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:1:144: STORETEMP
				{
				mSTORETEMP(); 

				}
				break;
			case 14 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:1:154: LOADTEMP
				{
				mLOADTEMP(); 

				}
				break;
			case 15 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:1:163: POP
				{
				mPOP(); 

				}
				break;
			case 16 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:1:167: ADD
				{
				mADD(); 

				}
				break;
			case 17 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:1:171: SUB
				{
				mSUB(); 

				}
				break;
			case 18 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:1:175: MULT
				{
				mMULT(); 

				}
				break;
			case 19 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:1:180: DIV
				{
				mDIV(); 

				}
				break;
			case 20 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:1:184: MOD
				{
				mMOD(); 

				}
				break;
			case 21 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:1:188: OR
				{
				mOR(); 

				}
				break;
			case 22 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:1:191: NOT
				{
				mNOT(); 

				}
				break;
			case 23 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:1:195: AND
				{
				mAND(); 

				}
				break;
			case 24 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:1:199: XOR
				{
				mXOR(); 

				}
				break;
			case 25 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:1:203: BRANCH
				{
				mBRANCH(); 

				}
				break;
			case 26 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:1:210: BRANCHLESS
				{
				mBRANCHLESS(); 

				}
				break;
			case 27 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:1:221: BRANCHGREATER
				{
				mBRANCHGREATER(); 

				}
				break;
			case 28 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:1:235: BRANCHEQUAL
				{
				mBRANCHEQUAL(); 

				}
				break;
			case 29 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:1:247: JUMPS
				{
				mJUMPS(); 

				}
				break;
			case 30 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:1:253: PRINT
				{
				mPRINT(); 

				}
				break;
			case 31 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:1:259: HALT
				{
				mHALT(); 

				}
				break;
			case 32 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:1:264: OPENCOMMENT
				{
				mOPENCOMMENT(); 

				}
				break;
			case 33 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:1:276: CLOSECOMMENT
				{
				mCLOSECOMMENT(); 

				}
				break;
			case 34 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:1:289: COL
				{
				mCOL(); 

				}
				break;
			case 35 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:1:293: LABEL
				{
				mLABEL(); 

				}
				break;
			case 36 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:1:299: NUMBER
				{
				mNUMBER(); 

				}
				break;
			case 37 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:1:306: WHITESP
				{
				mWHITESP(); 

				}
				break;
			case 38 :
				// /mnt/D/important/School/LM/y1s1_LinguaggiDiProgrammazioneEModelli/lab/eclipseWorkspace/LPeMC_11_22_Assignment/src/VM.g:1:314: ERR
				{
				mERR(); 

				}
				break;

		}
	}


	protected DFA6 dfa6 = new DFA6(this);
	static final String DFA6_eotS =
		"\1\uffff\12\32\1\62\2\32\2\26\3\uffff\1\26\3\uffff\3\32\1\uffff\1\75\5"+
		"\32\1\104\12\32\1\120\5\32\1\uffff\1\126\1\32\5\uffff\1\32\1\131\1\32"+
		"\1\uffff\1\133\1\134\1\135\1\136\1\32\1\140\1\uffff\1\141\1\142\1\143"+
		"\1\144\1\32\1\146\1\147\1\150\1\32\1\152\1\153\1\uffff\1\154\1\155\1\32"+
		"\1\157\1\160\1\uffff\1\32\1\162\1\uffff\1\32\4\uffff\1\32\5\uffff\1\32"+
		"\3\uffff\1\166\4\uffff\1\32\2\uffff\1\170\1\uffff\1\171\1\172\1\173\1"+
		"\uffff\1\174\5\uffff";
	static final String DFA6_eofS =
		"\175\uffff";
	static final String DFA6_minS =
		"\1\0\1\157\3\146\1\144\1\157\1\151\1\162\2\157\1\60\1\163\1\141\1\52\1"+
		"\57\3\uffff\1\61\3\uffff\1\163\1\160\1\151\1\uffff\1\60\1\141\2\160\1"+
		"\145\1\142\1\60\1\141\2\160\1\145\1\160\2\144\1\154\1\144\1\166\1\60\1"+
		"\164\1\162\1\145\1\164\1\161\1\uffff\1\60\1\154\5\uffff\1\150\1\60\1\156"+
		"\1\uffff\4\60\1\155\1\60\1\uffff\4\60\1\155\3\60\1\164\2\60\1\uffff\2"+
		"\60\1\163\2\60\1\uffff\1\164\1\60\1\uffff\1\164\4\uffff\1\160\5\uffff"+
		"\1\160\3\uffff\1\60\4\uffff\1\163\2\uffff\1\60\1\uffff\3\60\1\uffff\1"+
		"\60\5\uffff";
	static final String DFA6_maxS =
		"\1\uffff\1\165\2\167\1\146\1\156\1\165\1\151\1\162\2\157\1\172\1\163\1"+
		"\141\1\52\1\57\3\uffff\1\71\3\uffff\1\163\1\160\1\151\1\uffff\1\172\1"+
		"\166\2\160\1\145\1\142\1\172\1\166\2\160\1\145\1\160\2\144\1\154\1\144"+
		"\1\166\1\172\1\164\1\162\1\145\1\164\1\161\1\uffff\1\172\1\154\5\uffff"+
		"\1\150\1\172\1\156\1\uffff\4\172\1\155\1\172\1\uffff\4\172\1\155\3\172"+
		"\1\164\2\172\1\uffff\2\172\1\163\2\172\1\uffff\1\164\1\172\1\uffff\1\164"+
		"\4\uffff\1\160\5\uffff\1\160\3\uffff\1\172\4\uffff\1\163\2\uffff\1\172"+
		"\1\uffff\3\172\1\uffff\1\172\5\uffff";
	static final String DFA6_acceptS =
		"\20\uffff\1\42\1\43\1\44\1\uffff\1\44\1\45\1\46\3\uffff\1\43\27\uffff"+
		"\1\31\2\uffff\1\40\1\41\1\42\1\44\1\45\3\uffff\1\2\6\uffff\1\3\13\uffff"+
		"\1\25\5\uffff\1\35\2\uffff\1\17\1\uffff\1\4\1\6\1\10\1\13\1\uffff\1\21"+
		"\1\5\1\7\1\11\1\14\1\uffff\1\12\1\20\1\27\1\uffff\1\24\1\23\1\26\1\30"+
		"\1\uffff\1\33\1\34\1\uffff\1\1\3\uffff\1\22\1\uffff\1\37\1\36\1\15\1\16"+
		"\1\32";
	static final String DFA6_specialS =
		"\1\0\174\uffff}>";
	static final String[] DFA6_transitionS = {
			"\11\26\2\25\2\26\1\25\22\26\1\25\11\26\1\17\2\26\1\23\1\26\1\16\1\22"+
			"\11\24\1\20\6\26\32\21\6\26\1\5\1\13\1\4\1\7\3\21\1\15\1\21\1\14\1\21"+
			"\1\3\1\6\1\11\1\10\1\1\2\21\1\2\4\21\1\12\2\21\uff85\26",
			"\1\30\2\uffff\1\31\2\uffff\1\27",
			"\1\35\1\uffff\1\36\11\uffff\1\34\1\uffff\1\37\1\40\1\uffff\1\33",
			"\1\43\1\uffff\1\44\11\uffff\1\42\1\uffff\1\45\2\uffff\1\41",
			"\1\46",
			"\1\47\11\uffff\1\50",
			"\1\52\5\uffff\1\51",
			"\1\53",
			"\1\54",
			"\1\55",
			"\1\56",
			"\12\32\7\uffff\32\32\6\uffff\4\32\1\61\1\32\1\60\4\32\1\57\16\32",
			"\1\63",
			"\1\64",
			"\1\65",
			"\1\66",
			"",
			"",
			"",
			"\11\70",
			"",
			"",
			"",
			"\1\72",
			"\1\73",
			"\1\74",
			"",
			"\12\32\7\uffff\32\32\6\uffff\32\32",
			"\1\76\24\uffff\1\77",
			"\1\100",
			"\1\101",
			"\1\102",
			"\1\103",
			"\12\32\7\uffff\32\32\6\uffff\32\32",
			"\1\105\24\uffff\1\106",
			"\1\107",
			"\1\110",
			"\1\111",
			"\1\112",
			"\1\113",
			"\1\114",
			"\1\115",
			"\1\116",
			"\1\117",
			"\12\32\7\uffff\32\32\6\uffff\32\32",
			"\1\121",
			"\1\122",
			"\1\123",
			"\1\124",
			"\1\125",
			"",
			"\12\32\7\uffff\32\32\6\uffff\32\32",
			"\1\127",
			"",
			"",
			"",
			"",
			"",
			"\1\130",
			"\12\32\7\uffff\32\32\6\uffff\32\32",
			"\1\132",
			"",
			"\12\32\7\uffff\32\32\6\uffff\32\32",
			"\12\32\7\uffff\32\32\6\uffff\32\32",
			"\12\32\7\uffff\32\32\6\uffff\32\32",
			"\12\32\7\uffff\32\32\6\uffff\32\32",
			"\1\137",
			"\12\32\7\uffff\32\32\6\uffff\32\32",
			"",
			"\12\32\7\uffff\32\32\6\uffff\32\32",
			"\12\32\7\uffff\32\32\6\uffff\32\32",
			"\12\32\7\uffff\32\32\6\uffff\32\32",
			"\12\32\7\uffff\32\32\6\uffff\32\32",
			"\1\145",
			"\12\32\7\uffff\32\32\6\uffff\32\32",
			"\12\32\7\uffff\32\32\6\uffff\32\32",
			"\12\32\7\uffff\32\32\6\uffff\32\32",
			"\1\151",
			"\12\32\7\uffff\32\32\6\uffff\32\32",
			"\12\32\7\uffff\32\32\6\uffff\32\32",
			"",
			"\12\32\7\uffff\32\32\6\uffff\32\32",
			"\12\32\7\uffff\32\32\6\uffff\32\32",
			"\1\156",
			"\12\32\7\uffff\32\32\6\uffff\32\32",
			"\12\32\7\uffff\32\32\6\uffff\32\32",
			"",
			"\1\161",
			"\12\32\7\uffff\32\32\6\uffff\32\32",
			"",
			"\1\163",
			"",
			"",
			"",
			"",
			"\1\164",
			"",
			"",
			"",
			"",
			"",
			"\1\165",
			"",
			"",
			"",
			"\12\32\7\uffff\32\32\6\uffff\32\32",
			"",
			"",
			"",
			"",
			"\1\167",
			"",
			"",
			"\12\32\7\uffff\32\32\6\uffff\32\32",
			"",
			"\12\32\7\uffff\32\32\6\uffff\32\32",
			"\12\32\7\uffff\32\32\6\uffff\32\32",
			"\12\32\7\uffff\32\32\6\uffff\32\32",
			"",
			"\12\32\7\uffff\32\32\6\uffff\32\32",
			"",
			"",
			"",
			"",
			""
	};

	static final short[] DFA6_eot = DFA.unpackEncodedString(DFA6_eotS);
	static final short[] DFA6_eof = DFA.unpackEncodedString(DFA6_eofS);
	static final char[] DFA6_min = DFA.unpackEncodedStringToUnsignedChars(DFA6_minS);
	static final char[] DFA6_max = DFA.unpackEncodedStringToUnsignedChars(DFA6_maxS);
	static final short[] DFA6_accept = DFA.unpackEncodedString(DFA6_acceptS);
	static final short[] DFA6_special = DFA.unpackEncodedString(DFA6_specialS);
	static final short[][] DFA6_transition;

	static {
		int numStates = DFA6_transitionS.length;
		DFA6_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA6_transition[i] = DFA.unpackEncodedString(DFA6_transitionS[i]);
		}
	}

	protected class DFA6 extends DFA {

		public DFA6(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 6;
			this.eot = DFA6_eot;
			this.eof = DFA6_eof;
			this.min = DFA6_min;
			this.max = DFA6_max;
			this.accept = DFA6_accept;
			this.special = DFA6_special;
			this.transition = DFA6_transition;
		}
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( PUSH | STOREWORD | LOADWORD | STORERETADDR | LOADRETADDR | STORERETVAL | LOADRETVAL | STOREFRAMEPT | LOADFRAMEPT | COPYTOFRAMEPT | STOREHEAPPT | LOADHEAPPT | STORETEMP | LOADTEMP | POP | ADD | SUB | MULT | DIV | MOD | OR | NOT | AND | XOR | BRANCH | BRANCHLESS | BRANCHGREATER | BRANCHEQUAL | JUMPS | PRINT | HALT | OPENCOMMENT | CLOSECOMMENT | COL | LABEL | NUMBER | WHITESP | ERR );";
		}
		@Override
		public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
			IntStream input = _input;
			int _s = s;
			switch ( s ) {
					case 0 : 
						int LA6_0 = input.LA(1);
						s = -1;
						if ( (LA6_0=='p') ) {s = 1;}
						else if ( (LA6_0=='s') ) {s = 2;}
						else if ( (LA6_0=='l') ) {s = 3;}
						else if ( (LA6_0=='c') ) {s = 4;}
						else if ( (LA6_0=='a') ) {s = 5;}
						else if ( (LA6_0=='m') ) {s = 6;}
						else if ( (LA6_0=='d') ) {s = 7;}
						else if ( (LA6_0=='o') ) {s = 8;}
						else if ( (LA6_0=='n') ) {s = 9;}
						else if ( (LA6_0=='x') ) {s = 10;}
						else if ( (LA6_0=='b') ) {s = 11;}
						else if ( (LA6_0=='j') ) {s = 12;}
						else if ( (LA6_0=='h') ) {s = 13;}
						else if ( (LA6_0=='/') ) {s = 14;}
						else if ( (LA6_0=='*') ) {s = 15;}
						else if ( (LA6_0==':') ) {s = 16;}
						else if ( ((LA6_0 >= 'A' && LA6_0 <= 'Z')||(LA6_0 >= 'e' && LA6_0 <= 'g')||LA6_0=='i'||LA6_0=='k'||(LA6_0 >= 'q' && LA6_0 <= 'r')||(LA6_0 >= 't' && LA6_0 <= 'w')||(LA6_0 >= 'y' && LA6_0 <= 'z')) ) {s = 17;}
						else if ( (LA6_0=='0') ) {s = 18;}
						else if ( (LA6_0=='-') ) {s = 19;}
						else if ( ((LA6_0 >= '1' && LA6_0 <= '9')) ) {s = 20;}
						else if ( ((LA6_0 >= '\t' && LA6_0 <= '\n')||LA6_0=='\r'||LA6_0==' ') ) {s = 21;}
						else if ( ((LA6_0 >= '\u0000' && LA6_0 <= '\b')||(LA6_0 >= '\u000B' && LA6_0 <= '\f')||(LA6_0 >= '\u000E' && LA6_0 <= '\u001F')||(LA6_0 >= '!' && LA6_0 <= ')')||(LA6_0 >= '+' && LA6_0 <= ',')||LA6_0=='.'||(LA6_0 >= ';' && LA6_0 <= '@')||(LA6_0 >= '[' && LA6_0 <= '`')||(LA6_0 >= '{' && LA6_0 <= '\uFFFF')) ) {s = 22;}
						if ( s>=0 ) return s;
						break;
			}
			NoViableAltException nvae =
				new NoViableAltException(getDescription(), 6, _s, input);
			error(nvae);
			throw nvae;
		}
	}

}
