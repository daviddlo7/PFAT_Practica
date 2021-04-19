package AST;

import Errors.*;
import Compiler.*;

public class Statement_ReglaProd1 implements Statement {

	public String ident;
	public Exp expresion;
	public Statement_ReglaProd1(String ident, Exp expresion) {
		this.ident = ident;
		this.expresion = expresion;
	}

	public void computeType() throws CompilerExc {
		Integer t1, t2;
		t1 = SymbolTable.getType(ident);
		t2 = expresion.computeType();

		if (!t1.equals(t2)) throw new TypException("Statement_ReglaProd1: No coinciden los tipos");


	}
}
