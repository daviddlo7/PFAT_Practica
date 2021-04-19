package AST;

import Errors.*;
import Compiler.Type;
public class Exp_Igual implements Exp {

	public Exp expresion1, expresion2;

	public Exp_Igual(Exp expresion1, Exp expresion2) {
		this.expresion1 = expresion1;
		this.expresion2 = expresion2;
	}

	public Integer computeType() throws CompilerExc {
		Integer t1,t2;
		t1=expresion1.computeType();
		t2=expresion2.computeType();

		if(t1.equals(t2)){
			return Type.bool;
		}
		throw new TypException("IGUAL");
	}
}
