package AST;

import Errors.*;

public class Statement_ReglaProd1 implements Statement {

    public String ident;
    public Exp expresion;

    public Statement_ReglaProd1(String ident, Exp expresion) {
        this.ident = ident;
        this.expresion = expresion;
    }
}
