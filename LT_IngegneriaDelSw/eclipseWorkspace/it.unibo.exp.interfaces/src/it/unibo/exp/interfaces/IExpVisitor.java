package it.unibo.exp.interfaces;

 public interface IExpVisitor {	
	public  void visit( INumExp e ) throws Exception;
 	public  void visit( IOpExp e ) throws Exception;
}
