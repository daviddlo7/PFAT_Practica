package AST;

import Errors.*;
import Compiler.*;

public class IdentList_ReglaProd2 implements IdentList {

	public String ident;
	public IdentList identList;
	private Integer ah1;

	public IdentList_ReglaProd2(String ident, IdentList identList) {
		this.ident = ident;
		this.identList = identList;
	}

	public void computeAH1(Integer type) throws DoubleDefException {
		ah1=type;
		SymbolTable.newEntry(ident,type);
		identList.computeAH1(type);
	}

	public Integer getAh1() {
		return ah1;
	}

}
