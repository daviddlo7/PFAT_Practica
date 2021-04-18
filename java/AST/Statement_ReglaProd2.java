package AST;

import Errors.*;

public class Statement_ReglaProd2 implements Statement {

  public Exp expresion;

  public Statement_ReglaProd2(Exp expresion) {
    this.expresion = expresion;
  }

}
