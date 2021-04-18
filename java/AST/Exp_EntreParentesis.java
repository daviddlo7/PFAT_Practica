package AST;

import Errors.*;

public class Exp_EntreParentesis implements Exp {

    public Exp expresion;

    public Exp_EntreParentesis(Exp expresion) {
        this.expresion = expresion;
    }
}
