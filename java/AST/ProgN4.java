package AST;

import Errors.*;

public class ProgN4 implements Prog {

    public String ident;
    public Body body;

    public ProgN4(String ident, Body body) {

        this.ident = ident;
        this.body = body;

    }
}
