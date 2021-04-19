package AST;

import Errors.*;
import Compiler.Type;
public class Exp_NDecimal implements Exp {

    public int ctedecimal;

    public Exp_NDecimal(int ctedecimal) {
        this.ctedecimal = ctedecimal;
    }

    public Integer computeType() throws CompilerExc {
        return Type.entero;
    }
}
