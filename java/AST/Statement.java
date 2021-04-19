package AST;

import Errors.*;

public interface Statement {
	public void computeType() throws CompilerExc;
}
