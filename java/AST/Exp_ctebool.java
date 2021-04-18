package AST;

import Errors.*;

public class Exp_ctebool implements Exp {

    public boolean ctebool;

    public Exp_ctebool(boolean ctebool) {
        this.ctebool = ctebool;
    }
}