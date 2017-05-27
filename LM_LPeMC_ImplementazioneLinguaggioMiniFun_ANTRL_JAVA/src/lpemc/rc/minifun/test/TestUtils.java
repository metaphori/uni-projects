package lpemc.rc.minifun.test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;

import lpemc.rc.minifun.MiniFunLexer;
import lpemc.rc.minifun.MiniFunParser;
import lpemc.rc.minifun.Utils;
import lpemc.rc.minifun.Utils.LogBuffer;
import lpemc.rc.minifun.ast.Node;
import lpemc.rc.vm.ExecuteVM;
import lpemc.rc.vm.VMLexer;
import lpemc.rc.vm.VMParser;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;

public class TestUtils {
	
	public static Node parse(String code){
		MiniFunLexer lex = new MiniFunLexer(new ANTLRStringStream(code));
		CommonTokenStream tokens = new CommonTokenStream(lex);
		MiniFunParser parser = new MiniFunParser(tokens);
        try {
			return parser.prog();
		} catch (Exception e) {
			System.out.println(code);
			return null;
		}
	}
	
	public static String output(String code){
		Node root = parse(code);

        String asm = root.genCode();
        
        VMLexer vmlex = new VMLexer(new ANTLRStringStream(asm));
        CommonTokenStream tokensVM = new CommonTokenStream(vmlex);
        VMParser parserVM = new VMParser(tokensVM);

        ExecuteVM vm;
		try {
			vm = new ExecuteVM(parserVM.createCode());
	        LogBuffer lb = new LogBuffer();
	        vm.setOutputBuffer(lb);
	        Utils.log(">>>>>>>>>>> Startin CPU <<<<<<<<<<<");
	        vm.cpu();		
			
	        return lb.getText().replaceFirst("\\n$", ""); // return output with last '\n' cancelled
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR";
		}        
	}

	public static <T> ArrayList<T> alist(T... values){
		return new ArrayList<T>(Arrays.asList(values));
	}
	
}
