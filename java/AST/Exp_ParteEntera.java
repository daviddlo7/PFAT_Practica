package AST;

import Errors.*;

public class Exp_ParteEntera implements Exp {

    public Exp subexpresion;

    public Exp_ParteEntera(Exp subexpresion) {
        this.subexpresion = subexpresion;
    }
}
