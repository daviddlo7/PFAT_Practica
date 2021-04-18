package AST;

import Errors.*;

public class IdentList_ReglaProd2 implements IdentList {

  public String ident;
  public IdentList identList;

  public IdentList_ReglaProd2(String ident, IdentList identList) {
    this.ident = ident;
    this.identList = identList;
  }
}
