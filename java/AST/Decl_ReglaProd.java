package AST;

import Errors.*;

public class Decl_ReglaProd implements Decl { // No hace falta implementar la interfaz del no terminal Decl

    public Integer type;
    public IdentList identList;

    public Decl_ReglaProd(Integer type, IdentList identList) {
        this.type = type;
        this.identList = identList;
    }
}