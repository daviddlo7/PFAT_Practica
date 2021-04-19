package AST;

import Errors.*;
import Compiler.Type;
public class Statement_ReglaProd5 implements Statement {

    public StatementList statementList;
    public Exp expresion;

    public Statement_ReglaProd5(StatementList statementList, Exp expresion) {
        this.statementList = statementList;
        this.expresion = expresion;
    }


    public void computeType() throws CompilerExc {
        Integer t1;
        t1 = expresion.computeType();
        statementList.computeType();

        if (!t1.equals(Type.bool)) {
            throw new TypException("Statement_ReglaProd5: la expresion del repeat no es bool");
        }


    }
}