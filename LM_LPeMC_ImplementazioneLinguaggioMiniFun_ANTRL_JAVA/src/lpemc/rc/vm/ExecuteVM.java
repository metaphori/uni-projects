package lpemc.rc.vm;

import lpemc.rc.minifun.Utils;
import lpemc.rc.minifun.Utils.LogBuffer;

public class ExecuteVM {
    
    public static final int MEMSIZE = 10000;
    public static final int CODESIZE = 10000;
    
    private int[] code;
    private int[] memory = new int[MEMSIZE];

    /* REGISTRIES */
    private int ip = 0; // instruction pointer
    private int sp = MEMSIZE; // stack pointer
    /* frame pointer: punta, per tutta la durata della procedura, 
     * alla prima locazione di memoria del record di attivazione in 
     * modo che si possa far riferimento al top dello stack in maniera relativa ad essa*/
    private int fp = MEMSIZE;
    private int hp = 0; // heap pointer
    private int ra = 0; // return address
    private int rv = 0; // return value
    private int temp = 0; // register for temporary values
    
    protected LogBuffer out;
    
    public ExecuteVM(int[] code) {
      this.code = code;
      this.out = null;
    }
    
    public void cpu() {
    	int bytecode;
    	int v1,v2;
    	int address;
    	try{
    		while ( true ) {
    			bytecode = code[ip++]; // fetch
    			switch ( bytecode ) {
    			case VMParser.PUSH:
    				push( code[ip++] );
    				break;
    				// sw (mette nella cella indirizzata dal top dello stack, il valore sotto al top)
    			case VMParser.STOREWORD: 
    				v2=pop(); // dest addr
    				v1=pop(); // value
    				memstore(v2,v1);
    				break;
    				// lw (mette sullo stack il contenuto della cella puntata dal top dello stack)
    			case VMParser.LOADWORD:
    				v1=pop(); // source addr
    				push(memget(v1)); // put pointed value on top of stack 
    				break;
    				// sra (mette in RA il top dello stack)
    			case VMParser.STORERETADDR:
    				v1=pop(); 
    				ra = v1;
    				break;
    				// lra (mette il contenuto di RA in cima allo stack)
    			case VMParser.LOADRETADDR:
    				push(ra); 
    				break;
    				// srv (mette in RV il top dello stack)
    			case VMParser.STORERETVAL:
    				v1=pop(); 
    				rv = v1;
    				break;
    				// lrv (mette il contenuto del registro RV in cima allo stack)
    			case VMParser.LOADRETVAL:
    				push(rv);
    				break;        	  
    			case VMParser.STORETEMP:
    				v1=pop(); 
    				temp = v1;
    				break;
    				// lrv (mette il contenuto del registro RV in cima allo stack)
    			case VMParser.LOADTEMP:
    				push(temp);
    				break;         	
    				// shp (mette in HP il top dello stack)
    			case VMParser.STOREHEAPPT:
    				v1=pop(); 
    				hp = v1;
    				break;
    				// lhp (mette il contenuto del registro HP in cima allo stack)
    			case VMParser.LOADHEAPPT:
    				push(hp); // load current HEAP POINTER value on top of stack
    				break;  
    				// sfp (mette in FP il top dello stack)
    			case VMParser.STOREFRAMEPT:
    				v1=pop();
    				fp = v1;
    				break;
    				// lfp (mette il contenuto del registro FP in cima allo stack)
    			case VMParser.LOADFRAMEPT:
    				push(fp);
    				break;  
    				// cfp (copia il valore nello stack pointer dentro al registro FP)
    			case VMParser.COPYTOFRAMEPT:
    				fp = sp;
    				break;     
    			case VMParser.POP:
    				pop();
    				break;
    			case VMParser.ADD :
    				v2=pop();
    				v1=pop();
    				push(v1 + v2);
    				break;
    			case VMParser.SUB :
    				v2=pop();
    				v1=pop();
    				push(v1 - v2);
    				break;
    			case VMParser.MULT :
    				v2=pop();
    				v1=pop();
    				push(v1 * v2);
    				break;
    			case VMParser.DIV :
    				v2=pop();
    				v1=pop();
    				push(v1 / v2);
    				break;
    			case VMParser.MOD :
    				v2=pop();
    				v1=pop();
    				push(v1 % v2);
    				break;
    			case VMParser.OR :
    				v2=pop();
    				v1=pop();
    				push(v1 | v2);
    				break;
    			case VMParser.XOR :
    				v2=pop();
    				v1=pop();
    				push(v1 ^ v2);
    				break;              
    			case VMParser.NOT :
    				v1=pop();
    				push(~v1);
    				break;                  
    			case VMParser.AND :
    				v2=pop();
    				v1=pop();
    				push(v1 & v2);
    				break;              
    			case VMParser.BRANCH :
    				address = code[ip++];
    				ip = address;
    				break;
    			case VMParser.BRANCHLESS :
    				address = code[ip++];
    				v2=pop();
    				v1=pop();
    				if (v1 <= v2){ ip = address; }
    				break;
    			case VMParser.BRANCHGREATER :
    				address = code[ip++];
    				v2=pop();
    				v1=pop();
    				if (v1 >= v2){ ip = address; }
    				break;            
    				// beq LABEL (come bless ma con controllo di uguaglianza)
    			case VMParser.BRANCHEQUAL:
    				address = code[ip++];
    				v2=pop();
    				v1=pop();
    				if (v1 == v2){ ip = address; } 
    				break;
    				// js (salta all'istruzione indicata dal top dello stack, 
    				// e memorizza nel registro RA il valore attualmente nell'instruction pointer)
    			case VMParser.JUMPS:
    				v1 = pop();
    				ra = ip; // store current instruction point
    				//Utils.log("Jumping to " + v1 + " => " + code[v1]);
    				ip = v1; // jump to address pointed at top of stack
    				break; 
    			case VMParser.PRINT :
    				println(memory[sp]);
    				break;
    			case VMParser.HALT :
    				return;
    			}
    		}
    	}catch(Exception exc){
    		exc.printStackTrace();
    		System.out.println("IP="+ip+"\nSP="+sp+"\nRA="+ra+"\nRV="+rv+"\nFP="+fp+"\nHP="+hp);
    		System.exit(1);
    	}
    }
    
    private int pop() {
      return memory[sp++];
    }
    
    private void push(int b) {
      memory[--sp] = b;
    }
    
    private int memget(int pos) {
    	return memory[pos];
    }
    
    private void memstore(int pos, int val){
    	memory[pos] = val;
    }
    
    
    /* non-core utility methods */
    public void println(int i){
    	if(out==null)
    		System.out.println(""+i);
    	else
    		out.log(""+i);
    }
    
    public void setOutputBuffer(LogBuffer lb){
    	this.out = lb;
    }
    
}