package AST;

import Errors.*;

public class LDecl_ReglaProd2 implements LDecl {
    public Decl Decl;
    public LDecl Ldcl;

    public LDecl_ReglaProd2(Decl Decl, LDecl Ldcl) {
        this.Decl = Decl;
        this.Ldcl = Ldcl;
    }

    public void computeAH1() throws DoubleDefException{
        Decl.computeAH1();
        Ldcl.computeAH1();
    }
}