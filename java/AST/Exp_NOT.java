package AST;

import Errors.*;

public class Exp_NOT implements Exp {

    public Exp expresion;

    public Exp_NOT(Exp expresion) {
        this.expresion = expresion;
    }
}
