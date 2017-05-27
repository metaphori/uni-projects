package it.unibo.exp.interfaces;

public interface ILexer {
	public IToken next() throws Exception ;
	public IToken getCurToken() ;
}
