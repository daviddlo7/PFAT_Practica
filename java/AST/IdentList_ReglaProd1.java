package AST;

import Errors.*;
import Compiler.*;

public class IdentList_ReglaProd1 implements IdentList {
	public String ident;
	private Integer ah1;

	public IdentList_ReglaProd1(String ident) {
		this.ident = ident;
	}

	public void computeAH1(Integer type) throws DoubleDefException{
		ah1 = type;   //no tiene hijos
		SymbolTable.newEntry(ident,type);
	}

	public Integer getAh1() {
		return ah1;
	}

}