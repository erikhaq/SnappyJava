package se.ludrik.snappyj;

import java.util.Locale;
import org.antlr.v4.runtime.Token;

/**
 * Created by erikhaq on 2014-03-21.
 */
public class ErrorHandler {
  public ErrorHandler() {

  }

  public static void variableAlreadyDefinedInMethod(Token token, String methodName) {
    //Error:(40, 17) java: variable symTable is already defined in method init()
    System.err.printf("Error:(%d, %d) java: variable '%s' is already defined in method %s()\n", token.getLine(), token.getCharPositionInLine(), token.getText(), methodName);
  }

  public static void variableAlreadyDefinedInClass(Token token, String className) {
    //Error:(19, 27) java: variable lexer is already defined in class se.ludrik.snappyj.Main
    System.err.printf("Error:(%d, %d) java: variable '%s' is already defined in class %s\n", token.getLine(), token.getCharPositionInLine(), token.getText(), className);
  }

  public static void classAlreadyDefined(Token token) {
    //Error:(51, 8) java: duplicate class: se.ludrik.snappyj.Main
    System.err.printf("Error:(%d, %d) java: duplicate class: %s\n", token.getLine(),
        token.getCharPositionInLine(), token.getText());
  }

  public static void incompatibleTypes(Token token, String requiredType, String foundType) {
    //Error:(42, 12) java: incompatible types
    //required: boolean
    //found:    int
    System.err.printf("Error:(%d, %d) java: incompatible types\n\t\t\t\t\trequired:\t%s\n\t\t\t\t\t\t found:\t%s\n", token.getLine(), token.getCharPositionInLine(), requiredType, foundType);
  }

  private class Error {

    public Error() {

    }
  }


}
