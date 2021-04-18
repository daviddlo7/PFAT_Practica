package AST;

import Errors.*;

public class Exp_Opuesto implements Exp {

    public Exp expresion;

    public Exp_Opuesto(Exp expresion) {
        this.expresion = expresion;
    }
}
