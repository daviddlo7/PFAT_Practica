package AST;

import Errors.*;

public class ProgN1 implements Prog {

	public String ident;
	public IdentList identList;
	public LDecl LDecl;
	public Body body;

	public ProgN1(String ident, IdentList identList, LDecl LDecl, Body body) {
		this.ident = ident;
		this.identList = identList;
		this.LDecl = LDecl;
		this.body = body;

	}

	public void computeAH1() throws CompilerExc {
		//identlist son los parameters que nos pasan por args, siempre serán int
		System.out.println("Entro en compuiteAH1");
		identList.computeAH1(0);//es 0 porque es int, siempre es int
		LDecl.computeAH1();
	}
	public void computeType() throws CompilerExc{
		System.out.println("Entro en compuiteType");
		body.computeType();
	}
}