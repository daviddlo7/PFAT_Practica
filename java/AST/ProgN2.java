package AST;

import Errors.*;

public class ProgN2 implements Prog {

	public String ident;
	public LDecl LDecl;
	public Body body;

	public ProgN2(String ident, LDecl LDecl, Body body) {

		this.ident = ident;
		this.LDecl = LDecl;
		this.body = body;

	}

	public void computeAH1() throws CompilerExc {
		System.out.println("Entro en compuiteAH1");
		LDecl.computeAH1();
	}
	public void computeType() throws CompilerExc{
		System.out.println("Entro en computeType");
		body.computeType();
	}
}
