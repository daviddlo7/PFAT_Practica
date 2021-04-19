package AST;

import Errors.*;

public class Exp_ParteEntera implements Exp {

    public Exp subexpresion;

    public Exp_ParteEntera(Exp subexpresion) {
        this.subexpresion = subexpresion;
    }

    public Integer computeType() throws CompilerExc {
        Integer t1=subexpresion.computeType();
        if(t1.equals(Type.rational)){
            return Type.entero;
        }
        throw new TypException("ParteEntera");
    }
}
