package AST;

import Errors.*;

public class Exp_q2str implements Exp {

    public Exp subexpresion;

    public Exp_q2str(Exp subexpresion) {
        this.subexpresion = subexpresion;
    }
}
