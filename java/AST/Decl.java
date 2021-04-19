package AST;

import Errors.*;

public interface Decl {
    // Esta es la interfaz del no terminal <Decl>
	public void computeAH1() throws DoubleDefException;
}
