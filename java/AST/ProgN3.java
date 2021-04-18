package AST;

import Errors.*;

public class ProgN3 implements Prog {

  public String ident;
  public IdentList identList;
  public Body body;

  public ProgN3(String ident, IdentList identList, Body body) {

    this.ident = ident;
    this.identList = identList;
    this.body = body;

  }

}
