package AST;

import Errors.*;

public class StatementList_ReglaProd2 implements StatementList {

    public Statement statement;
    public StatementList StList;

    public StatementList_ReglaProd2(Statement statement, StatementList StList) {
        this.statement = statement;
        this.StList = StList;
    }

    public void computeType() throws CompilerExc {
        statement.computeType();
        StList.computeType();
    }
}
