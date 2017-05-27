package lpemc.rc.vm;

import org.antlr.runtime.*;

public class TestVM {
    public static void main(String[] args) throws Exception {
      
        ANTLRFileStream input = new ANTLRFileStream(args[0]);
        VMLexer lexer = new VMLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        VMParser parser = new VMParser(tokens);
        
        ExecuteVM vm = new ExecuteVM(parser.createCode());
        vm.cpu();  
  
    }
}
