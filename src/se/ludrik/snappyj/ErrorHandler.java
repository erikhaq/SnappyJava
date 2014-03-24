package se.ludrik.snappyj;

import org.antlr.v4.runtime.Token;

/**
 * Created by erikhaq on 2014-03-21.
 */
public class ErrorHandler {
  public ErrorHandler() {

  }
  public static void variableAlreadyDeclared(Token var) {
    System.err.println("Error:(" + var.getLine() + ", " + var.getCharPositionInLine() + ") java: variable '"+ var.getText() + "' already declared.");
  }
  private class Error {

    public Error() {

    }
  }


}
