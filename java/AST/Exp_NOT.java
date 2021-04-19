package AST;

import Errors.*;
import Compiler.Type;
public class Exp_NOT implements Exp {

    public Exp expresion;

    public Exp_NOT(Exp expresion) {
        this.expresion = expresion;
    }

    public Integer computeType() throws CompilerExc {
        Integer t1=expresion.computeType();

        if(t1.equals(Type.bool)) return Type.bool;

        throw new TypException("NOT");
    }
}
