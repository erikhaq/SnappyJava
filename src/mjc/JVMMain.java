package mjc;
import java.io.IOException;
import se.ludrik.snappyj.*;

/**
 * Run from SnappyJava directory with:
 * java -cp build/jar/mjc.jar:lib/antlr-4.2-complete.jar mjc.JVMMain test-files/file-name.java
 */
public class JVMMain {
  public static void main(String[] args) {
    String fileName;
    if(args.length != 1) {
      System.err.println("No file specified, using default test.java");
      fileName = "test.java";
    } else {
      fileName = args[0];
    }

    try {
      Main snappyJava = new Main();
      snappyJava.init(fileName);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
