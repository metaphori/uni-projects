package lpemc.rc.minifun;

import org.antlr.runtime.*;
import java.io.*;
import java.util.regex.Pattern;

import lpemc.rc.minifun.ast.BoolNode;
import lpemc.rc.minifun.ast.BoolTypeNode;
import lpemc.rc.minifun.ast.Node;
import lpemc.rc.vm.ExecuteVM;
import lpemc.rc.vm.VMLexer;
import lpemc.rc.vm.VMParser;

public class TestMiniFun {
 
  public static void main(String[] args) throws IOException {
        MiniFunLexer lex = new MiniFunLexer(new ANTLRFileStream(args[0]));
        CommonTokenStream tokens = new CommonTokenStream(lex);
 
        MiniFunParser parser = new MiniFunParser(tokens);
 
        try {
        	Utils.LEVEL = Utils.DEBUG_LEVEL;
        	Node root = parser.prog();
            System.out.println(root.getStringRepr());
            
            Utils.log("TYPE-CHECK: " +  root.typeCheck());
            
            String asm = root.genCode();
            
            FileWriter fstream = new FileWriter(args[0]+".asm");
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(asm);
            out.close();

            VMLexer vmlex = new VMLexer(new ANTLRFileStream(args[0]+".asm"));
            CommonTokenStream tokensVM = new CommonTokenStream(vmlex);
            VMParser parserVM = new VMParser(tokensVM);

            ExecuteVM vm = new ExecuteVM(parserVM.createCode());
            Utils.log(">>>>>>>>>>> Startin CPU <<<<<<<<<<<");
            vm.cpu();
            
        } catch (Exception e)  {
            e.printStackTrace();
        }    
  }
  
}