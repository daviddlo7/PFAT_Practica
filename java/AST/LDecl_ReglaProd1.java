package AST;

import Errors.*;

public class LDecl_ReglaProd1 implements LDecl {

    public final Decl decl;

    public LDecl_ReglaProd1(Decl decl) {
        this.decl = decl;
    }

    public void computeAH1() throws DoubleDefException{
        decl.computeAH1();
    }
}