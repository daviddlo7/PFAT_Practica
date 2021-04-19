package AST;

import Errors.*;

public class Exp_MAS implements Exp {

	public Exp expresion1, expresion2;

	public Exp_MAS(Exp expresion1, Exp expresion2) {
		this.expresion1 = expresion1;
		this.expresion2 = expresion2;
	}

	public Integer computeType() throws CompilerExc {
		Integer t1, t2;
		t1 = expresion1.computeType();
		t2 = expresion2.computeType();
		System.out.println("+");
		System.out.println(t1);
		System.out.println(t2);
		if ((t1.equals(Type.entero)) && (t2.equals(Type.entero))) {
			return Type.entero;
		}
		if ((t1.equals(Type.entero)) && (t2.equals(Type.rational))) {
			return Type.rational;
		}
		if ((t1.equals(Type.rational)) && (t2.equals(Type.entero))) {
			return Type.rational;
		}
		if ((t1.equals(Type.str)) && (t2.equals(Type.str))) {
			return Type.str;
		}

		throw new TypException("MAS");

	}
}
