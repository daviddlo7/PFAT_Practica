package AST;

import Errors.*;

public class Exp_String implements Exp {

	public String string;

	public Exp_String(String string) {
		this.string = string;
	}

	public Integer computeType() throws CompilerExc {
		return Type.str;
	}
}
