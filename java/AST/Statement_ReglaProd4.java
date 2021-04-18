package AST;

import Errors.*;

public class Statement_ReglaProd4 implements Statement {

    public Exp expresion;
    public StatementList statement1;
    public StatementList statement2;

    public Statement_ReglaProd4(Exp expresion, StatementList statement1, StatementList statement2) {
        this.expresion = expresion;
        this.statement1 = statement1;
        this.statement2 = statement2;
    }
}