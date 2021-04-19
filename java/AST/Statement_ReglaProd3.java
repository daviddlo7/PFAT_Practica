package AST;

import Errors.*;
import Compiler.Type;

public class Statement_ReglaProd3 implements Statement {

	public Exp expresion;
	public StatementList statementList;

	public Statement_ReglaProd3(Exp expresion, StatementList statementList) {
		this.expresion = expresion;
		this.statementList = statementList;
	}

	public void computeType() throws CompilerExc {
		Integer t1;
		t1 = expresion.computeType();
		statementList.computeType();

		if (!t1.equals(Type.bool)) throw new TypException("Statement_ReglaProd3: la expresion del if no es bool");

	}
}
