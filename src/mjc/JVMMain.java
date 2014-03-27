package mjc;
import java.io.IOException;
import se.ludrik.snappyj.*;

/**
 * Run from SnappyJava directory with:
 * java -cp build/jar/mjc.jar:lib/antlr-4.2-complete.jar mjc.JVMMain [filename] [flags]
 */
public class JVMMain {

  public static boolean generateAssemblyCode = false;
  public static boolean printErrors = true;

  public static void main(String[] args) {
    String fileName;
    if(args.length > 0) {
      fileName = args[0];
      if(args.length == 2) {
        if(args[1].equals("-n")) {
          printErrors = false;
        } else if(args[1].equals("-S")) {
          generateAssemblyCode = true;
        }
      }
    } else {
      //System.err.println("Please specify a filename");
      System.err.println("Using default file.");
      fileName = "test.java";
      //System.exit(1);
      //return;
    }

    try {
      Main snappyJava = new Main(fileName);
      snappyJava.init();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
