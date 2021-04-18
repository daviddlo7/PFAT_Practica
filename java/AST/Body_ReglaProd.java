package AST;

import Errors.*;

public class Body_ReglaProd implements Body { // No hace falta implementar la interfaz del no terminal Body

    public StatementList StList;

    public Body_ReglaProd(StatementList StList) {
        this.StList = StList;
    }

}
