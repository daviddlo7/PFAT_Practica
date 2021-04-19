package AST;

import Errors.*;

public interface Prog {
    // Esta es la interfaz del no terminal <Prog>
	public void computeAH1() throws CompilerExc;
	public void computeType() throws  CompilerExc;
}
