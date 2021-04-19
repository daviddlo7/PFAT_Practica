package AST;

import Errors.*;
import Compiler.Type;
public class Exp_Menor implements Exp {

    public Exp expresion1, expresion2;

    public Exp_Menor(Exp expresion1, Exp expresion2) {
        this.expresion1 = expresion1;
        this.expresion2 = expresion2;
    }
}
