import Lexer.Yylex;

public class LexerMain {

  public static void main(String args[]) throws Exception {
    Yylex lex= new Yylex(System.in);

    while(lex.next_token()!=null) {}
  }
}
