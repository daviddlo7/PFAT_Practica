package AST;

import Errors.*;
import Compiler.*;

public class Exp_Identificador implements Exp {

    public String ident;

    public Exp_Identificador(String ident) {
        this.ident = ident;
    }

    public Integer computeType() throws VarNoDefException {
        return SymbolTable.getType(ident);
    }
}