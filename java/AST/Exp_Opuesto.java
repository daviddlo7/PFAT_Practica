package AST;

import Errors.*;
import Compiler.Type;
public class Exp_Opuesto implements Exp {

    public Exp expresion;

    public Exp_Opuesto(Exp expresion) {
        this.expresion = expresion;
    }

    public Integer computeType() throws CompilerExc {
        Integer t1;
        t1 = expresion.computeType();
        if(!t1.equals(Type.str))  return t1;
        throw new TypException("Opuesto");
    }
}
