package AST;

import Errors.*;
import Compiler.Type;
public class Exp_Denominador implements Exp {

    public Exp subexpresion;

    public Exp_Denominador(Exp subexpresion) {
        this.subexpresion = subexpresion;
    }

    public Integer computeType() throws CompilerExc {
        Integer t1=subexpresion.computeType();
        if(t1.equals(Type.rational)){
            return Type.entero;
        }
        throw new TypException("Denominador");
    }
}