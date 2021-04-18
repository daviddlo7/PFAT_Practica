package AST;

import Errors.*;

public class Statement_ReglaProd5 implements Statement {

    public StatementList statement;
    public Exp expresion;

    public Statement_ReglaProd5(StatementList statement, Exp expresion) {
        this.statement = statement;
        this.expresion = expresion;
    }
}