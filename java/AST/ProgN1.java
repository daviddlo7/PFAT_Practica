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

}