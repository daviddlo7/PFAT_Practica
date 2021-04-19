package AST;

import Errors.*;
import Compiler.Type;
public class Exp_Numerador implements Exp {

    public Exp subexpresion;

    public Exp_Numerador(Exp subexpresion) {
        this.subexpresion = subexpresion;
    }

    public Integer computeType() throws CompilerExc {
        Integer t1=subexpresion.computeType();
        if(t1.equals(Type.rational)){
            return Type.entero;
        }
        throw new TypException("Numerador");
    }
}
