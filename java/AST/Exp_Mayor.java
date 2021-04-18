package AST;

import Errors.*;

public class Exp_Mayor implements Exp {

    public Exp expresion1, expresion2;

    public Exp_Mayor(Exp expresion1, Exp expresion2) {
        this.expresion1 = expresion1;
        this.expresion2 = expresion2;
    }
}
