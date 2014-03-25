package mjc;
import java.io.IOException;
import se.ludrik.snappyj.*;

/**
 * Run from SnappyJava directory with:
 * java -cp build/jar/mjc.jar:lib/antlr-4.2-complete.jar mjc.JVMMain test-files/file-name.java
 */
public class JVMMain {
  public static void main(String[] args) {
    if(args.length != 1) {
      System.err.println("Error: incorrect number of parameters");
      System.exit(1);
    }

    try {
      new Main(args[0]);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
