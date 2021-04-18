package Errors;

public class LexerException extends CompilerExc {

  private String msg;

  public LexerException(String s) {
    msg = s;
  }

  public String toString() {
    return msg;
  }
}
