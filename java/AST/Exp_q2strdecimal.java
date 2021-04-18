package AST;

import Errors.*;

public class Exp_q2strdecimal implements Exp {

    public Exp subexpresion;
    public int ctedecimal;

    public Exp_q2strdecimal(Exp subexpresion, int ctedecimal) {
        this.subexpresion = subexpresion;
        this.ctedecimal = ctedecimal;
    }
}
