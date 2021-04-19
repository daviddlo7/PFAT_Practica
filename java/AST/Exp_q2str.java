package AST;

import Errors.*;
import Compiler.Type;
public class Exp_q2str implements Exp {

    public Exp subexpresion;

    public Exp_q2str(Exp subexpresion) {
        this.subexpresion = subexpresion;
    }

    public Integer computeType() throws CompilerExc {
        Integer t1=subexpresion.computeType();
        if(t1.equals(Type.rational)){
            return Type.str;
        }
        throw new TypException("q2str");
    }
}
