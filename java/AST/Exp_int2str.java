package AST;

import Errors.*;

public class Exp_int2str implements Exp {

    public Exp subexpresion;

    public Exp_int2str(Exp subexpresion) {
        this.subexpresion = subexpresion;
    }
}
