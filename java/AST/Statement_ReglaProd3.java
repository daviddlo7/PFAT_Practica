package AST;

import Errors.*;

public class Statement_ReglaProd3 implements Statement {

    public Exp expresion;
    public StatementList statement;

    public Statement_ReglaProd3(Exp expresion, StatementList statement) {
        this.expresion = expresion;
        this.statement = statement;
    }
}
