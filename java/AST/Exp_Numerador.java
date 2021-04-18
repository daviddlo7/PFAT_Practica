package AST;

import Errors.*;

public class Exp_Numerador implements Exp {

    public Exp subexpresion;

    public Exp_Numerador(Exp subexpresion) {
        this.subexpresion = subexpresion;
    }
}
