package AST;

import Errors.*;
import Compiler.Type;
public class Exp_AND implements Exp {

    public Exp expresion1, expresion2;

    public Exp_AND(Exp expresion1, Exp expresion2) {
        this.expresion1 = expresion1;
        this.expresion2 = expresion2;
    }

    public Integer computeType() throws CompilerExc {
        Integer t1=expresion1.computeType();
        Integer t2=expresion2.computeType();

        if((t1.equals(Type.bool)) && (t2.equals(Type.bool))) return Type.bool;

        throw new TypException("AND");
    }
}
