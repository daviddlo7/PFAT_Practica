package AST;

import Errors.*;

public class Exp_Denominador implements Exp {

    public Exp subexpresion;

    public Exp_Denominador(Exp subexpresion) {
        this.subexpresion = subexpresion;
    }
}