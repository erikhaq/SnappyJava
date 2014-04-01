package mjc;
import java.io.IOException;
import se.ludrik.snappyj.*;

/**
 * Run from SnappyJava directory with:
 * java -cp build/jar/mjc.jar:lib/antlr-4.2-complete.jar mjc.JVMMain [filename] [flags]
 */
public class JVMMain {

  public static boolean generateJvmCode = true;
  public static boolean printErrors = true;

  public static void main(String[] args) {
    String filePath;
    if(args.length > 0) {
      filePath = args[0];
      if(args.length == 2) {
        if(args[1].equals("-m")) {
          printErrors = false;
        } else if(args[1].equals("-S")) {
          generateJvmCode = true;
        }
      }
    } else {
      //System.err.println("Please specify a filename");
      System.err.println("Using default file.");
      filePath = "test-files/test2.java";
      //System.exit(1);
      //return;
    }

    try {
      Main snappyJava = new Main(filePath);
      snappyJava.init();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
