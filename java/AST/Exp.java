package AST;

import Errors.*;

public interface Exp {
	public Integer computeType() throws CompilerExc;
}
