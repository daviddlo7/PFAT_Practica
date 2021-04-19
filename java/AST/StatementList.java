package AST;

import Errors.*;

public interface StatementList {
	public void computeType() throws CompilerExc;
}