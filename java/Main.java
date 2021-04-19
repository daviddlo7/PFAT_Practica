import AST.Prog;
import Parser.*;
import Lexer.*;
import java.io.*;

public class Main
{
  public static void main(String[] args) throws Exception{
    java.io.BufferedReader in;
    Yylex sc;
    parser p;
    java_cup.runtime.Symbol sroot;

    boolean error=false;

    //El primer parametro es el nombre del fichero con el programa
    if (args.length < 1) {
      System.out.println(
        "Uso: java Main <nombre_fichero>!");
      error=true;
    }

    //An�lisis l�xico y sint�ctico

    if (!error) {  
	try {
	    in = new java.io.BufferedReader(new java.io.FileReader(args[0]));
	    sc = new Yylex(in);
	    p = new parser(sc);
	    sroot = p.parse();
		Object nodoArbol=sroot.value;
		Prog progArbol=(Prog)nodoArbol;
		progArbol.computeAH1();
		progArbol.computeType();
	    System.out.println("Analisis lexico, sintactico y semantico correctos");
	} catch(IOException e) {
	    System.out.println("Error abriendo fichero: " + args[0]);
	    error= true;
	}
    }
  }
}