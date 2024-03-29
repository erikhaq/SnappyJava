package se.ludrik.snappyj;

import mjc.JVMMain;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.misc.Nullable;

/**
 * Error handler class
 */
public class ErrorHandler extends BaseErrorListener {
  public static ErrorHandler INSTANCE = new ErrorHandler();
  private static boolean errorDetected;

  public ErrorHandler() {
    errorDetected = false;
  }

  private static void setErrorDetected() {
    if (!errorDetected) errorDetected = true;
  }

  public static boolean getErrorDetected() {
    return errorDetected;
  }

  public static void cantCallThisFromStaticContext(Token thisToken) {
    if (JVMMain.printErrors) {
      System.err.printf(
          "Error:(%d, %d) java: error: non-static variable this cannot be referenced from a static context \n",
          thisToken.getLine(), thisToken.getCharPositionInLine());
    }
    setErrorDetected();
  }

  public static void variableAlreadyDefinedInMethod(Token idToken, String methodName) {
    //Error:(40, 17) java: variable symTable is already defined in method init()
    if (JVMMain.printErrors) {
      System.err.printf("Error:(%d, %d) java: variable '%s' is already defined in method %s()\n",
          idToken.getLine(), idToken.getCharPositionInLine(), idToken.getText(), methodName);
    }
    setErrorDetected();
  }

  public static void variableAlreadyDefinedInClass(Token idToken, String className) {
    //Error:(19, 27) java: variable lexer is already defined in class se.ludrik.snappyj.Main
    if (JVMMain.printErrors) {
      System.err.printf("Error:(%d, %d) java: variable '%s' is already defined in class %s\n",
          idToken.getLine(), idToken.getCharPositionInLine(), idToken.getText(), className);
    }
    setErrorDetected();
  }

  public static void methodAlreadyDefined(Token idToken, String className) {
    //Error:(63, 15) java: method init() is already defined in class se.ludrik.snappyj.Main
    if (JVMMain.printErrors) {
      System.err.printf("Error:(%d, %d) java: method %s() is already defined in class %s\n",
          idToken.getLine(), idToken.getCharPositionInLine(), idToken.getText(), className);
    }
    setErrorDetected();
  }

  public static void noSuchMethod(Token methodIdToken, String className) {
    if (JVMMain.printErrors) {
      System.err.printf("Error:(%d, %d) java: no such method %s() defined in class %s\n",
          methodIdToken.getLine(), methodIdToken.getCharPositionInLine(), methodIdToken.getText(),
          className);
    }
    setErrorDetected();
  }

  public static void invalidMethodParams(Token methodIdToken, String className, String requiredType,
      String foundType) {
    //Error(73, 14)java: method count in class se.ludrik.snappyj.Main.Lek cannot be applied to given types;
    //required: int
    //found: boolean
    if (JVMMain.printErrors) {
      System.err.printf(
          "Error:(%d, %d) java: method %s in class %s cannot be applied to given types\n\trequired:\t%s\n\tfound:\t\t%s\n",
          methodIdToken.getLine(), methodIdToken.getCharPositionInLine(), methodIdToken.getText(),
          className, requiredType, foundType);
    }
    setErrorDetected();
  }

  public static void invalidMethodParamSize(Token methodIdToken, String className) {
    if (JVMMain.printErrors) {
      System.err.printf(
          "Error:(%d, %d) java: number of input parameters of method %s in class %s does not match number declared\n",
          methodIdToken.getLine(), methodIdToken.getCharPositionInLine(), methodIdToken.getText(),
          className);
    }
    setErrorDetected();
  }

  public static void classAlreadyDefined(Token idToken) {
    //Error:(51, 8) java: duplicate class: se.ludrik.snappyj.Main
    if (JVMMain.printErrors) {
      System.err.printf("Error:(%d, %d) java: duplicate class: %s\n", idToken.getLine(),
          idToken.getCharPositionInLine(), idToken.getText());
    }
    setErrorDetected();
  }

  public static void notAnumber(Token idToken) {
    if (JVMMain.printErrors) {
      System.err.printf("Error:(%d, %d) java: %s could not be parsed to int.\n", idToken.getLine(),
          idToken.getCharPositionInLine(), idToken.getText());
    }
    setErrorDetected();
  }

  public static void incompatibleTypes(Token idToken, String requiredType, String foundType) {
    //Error:(42, 12) java: incompatible types
    //required: boolean
    //found:    int
    if (JVMMain.printErrors) {
      System.err.printf(
          "Error:(%d, %d) java: incompatible types\n\trequired:\t%s\n\tfound:\t\t%s\n",
          idToken.getLine(), idToken.getCharPositionInLine(), requiredType, foundType);
    }
    setErrorDetected();
  }

  public static void missingClassSymbol(Token typeToken, String location) {
    //Error:(43, 5) java: cannot find symbol
    //symbol:   class Test
    //location: class se.ludrik.snappyj.Main
    if (JVMMain.printErrors) {
      System.err.printf(
          "Error:(%d, %d) java: cannot find symbol\n\tsymbol:\t\tclass %s\n\tlocation:\tclass %s\n",
          typeToken.getLine(), typeToken.getCharPositionInLine(), typeToken.getText(), location);
    }
    setErrorDetected();
  }

  public static void missingVariableSymbol(Token idToken, String location) {
    //Error:(42, 5) java: cannot find symbol
    //symbol:   variable testVar
    //location: class se.ludrik.snappyj.Main
    if (JVMMain.printErrors) {
      System.err.printf(
          "Error:(%d, %d) java: cannot find symbol\n\tsymbol:\t\tvariable %s\n\tlocation:\t%s\n",
          idToken.getLine(), idToken.getCharPositionInLine(), idToken.getText(), location);
    }
    setErrorDetected();
  }

  public static void notAStatement(Token exprToken) {
    //Error:(52, 13) java: not a statement
    if (JVMMain.printErrors) {
      System.err.printf("Error:(%d, %d) java: not a statement\n", exprToken.getLine(),
          exprToken.getCharPositionInLine());
    }
    setErrorDetected();
  }

  public static void cantCreateMultiDimensional(Token exprToken, String text, String location) {
    if (JVMMain.printErrors) {
      System.err.printf(
          "Error:(%d, %d) java: cannot create multidimensional array\n\tsymbol:\t\t %s\n\tlocation:\t%s\n",
          exprToken.getLine(), exprToken.getCharPositionInLine(), text, location);
    }
    setErrorDetected();
  }

  public static void noMainMethod(Token tkn) {
    System.err.printf("Error:(%d, %d) java: Main-method not named 'main'.", tkn.getLine(),
        tkn.getCharPositionInLine());
    setErrorDetected();
  }

  public static void cyclicInheritance(Token classIdToken, String classId) {
    // Error:(211, 1) java: cyclic inheritance involving se.ludrik.snappyj.TestCircle
    System.err.printf("Error:(%d, %d) java: cyclic inheritance involving %s\n",
        classIdToken.getLine(), classIdToken.getCharPositionInLine(), classId);
  }

  @Override
  public void syntaxError(@NotNull Recognizer<?, ?> recognizer, @Nullable Object offendingSymbol,
      int line, int charPositionInLine, @NotNull String msg, @Nullable RecognitionException e) {
    if (JVMMain.printErrors) System.err.printf("line %d:%d: %s\n", line, charPositionInLine, msg);
    setErrorDetected();
  }
}
