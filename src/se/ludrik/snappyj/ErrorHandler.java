package se.ludrik.snappyj;

import org.antlr.v4.runtime.Token;


/**
 * Created by erikhaq on 2014-03-21.
 */
public class ErrorHandler {
  public ErrorHandler() {
  }

  public static void variableAlreadyDefinedInMethod(Token idToken, String methodName) {
    //Error:(40, 17) java: variable symTable is already defined in method init()
    System.err.printf("Error:(%d, %d) java: variable '%s' is already defined in method %s()\n",
        idToken.getLine(), idToken.getCharPositionInLine(), idToken.getText(), methodName);
  }

  public static void variableAlreadyDefinedInClass(Token idToken, String className) {
    //Error:(19, 27) java: variable lexer is already defined in class se.ludrik.snappyj.Main
    System.err.printf("Error:(%d, %d) java: variable '%s' is already defined in class %s\n",
        idToken.getLine(), idToken.getCharPositionInLine(), idToken.getText(), className);
  }

  public static void methodAlreadyDefined(Token idToken, String className) {
    //Error:(63, 15) java: method init() is already defined in class se.ludrik.snappyj.Main
    System.err.printf("Error:(%d, %d) java: method %s() is already defined in class %s\n",
        idToken.getLine(), idToken.getCharPositionInLine(), idToken.getText(), className);
  }

  public static void classAlreadyDefined(Token idToken) {
    //Error:(51, 8) java: duplicate class: se.ludrik.snappyj.Main
    System.err.printf("Error:(%d, %d) java: duplicate class: %s\n", idToken.getLine(),
        idToken.getCharPositionInLine(), idToken.getText());
  }

  public static void incompatibleTypes(Token idToken, String requiredType, String foundType) {
    //Error:(42, 12) java: incompatible types
    //required: boolean
    //found:    int
    System.err.printf(
        "Error:(%d, %d) java: incompatible types\n\trequired:\t%s\n\tfound:\t\t%s\n",
        idToken.getLine(), idToken.getCharPositionInLine(), requiredType, foundType);
  }

  public static void missingClassSymbol(Token typeToken, String location) {
    //Error:(43, 5) java: cannot find symbol
    //symbol:   class Hora
    //location: class se.ludrik.snappyj.Main
    System.err.printf(
        "Error:(%d, %d) java: cannot find symbol\n\tsymbol:\t\tclass %s\n\tlocation:\tclass %s\n",
        typeToken.getLine(), typeToken.getCharPositionInLine(), typeToken.getText(), location);
  }

  public static void missingVariableSymbol(Token idToken, String location) {
    //Error:(42, 5) java: cannot find symbol
    //symbol:   variable bajs
    //location: class se.ludrik.snappyj.Main
    System.err.printf(
        "Error:(%d, %d) java: cannot find symbol\n\tsymbol:\t\tvariable %s\n\tlocation:\t%s\n",
        idToken.getLine(), idToken.getCharPositionInLine(), idToken.getText(), location);
  }

  public static void notAStatement(Token exprToken) {
    //Error:(52, 13) java: not a statement
    System.err.printf("Error:(%d, %d) java: not a statement\n", exprToken.getLine(),
        exprToken.getCharPositionInLine());
  }

  private class Error {

    public Error() {

    }
  }


}
