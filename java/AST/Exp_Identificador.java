package AST;

import Errors.*;

public class Exp_Identificador implements Exp {

    public String ident;

    public Exp_Identificador(String ident) {
        this.ident = ident;
    }
}
