package AST;

import Errors.*;
import Compiler.Type;
public class Statement_ReglaProd4 implements Statement {

    public Exp expresion;
    public StatementList statementList1;
    public StatementList statementList2;

    public Statement_ReglaProd4(Exp expresion, StatementList statementList1, StatementList statementList2) {
        this.expresion = expresion;
        this.statementList1 = statementList1;
        this.statementList2 = statementList2;
    }
}