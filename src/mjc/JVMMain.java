package mjc;
import java.io.IOException;
import se.ludrik.snappyj.*;

/**
 * Run from SnappyJava directory with:
 * java -cp build/jar/mjc.jar:lib/antlr-4.2-complete.jar mjc.JVMMain [filename] [flags]
 */
public class JVMMain {

  public static boolean generateJvmCode = false;
  public static boolean printErrors = true;
  public static String outputDirectory = ".";

  public static void main(String[] args) {

    //printArgs(args);

    String filePath;
    if(args.length > 0) {
      filePath = args[0];
      //if(readOption("-m", args) > 0) printErrors = false;
      if(readOption("-S", args) > 0) generateJvmCode = true;
      int oIndex = readOption("-o", args);
      if(oIndex > 0) outputDirectory = readStringArg(oIndex, args);
    } else {
      //System.err.println("Please specify a filename");
      System.err.println("Using default file.");
      filePath = "testcases/DotaTest.java";
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

  public static String readStringArg(int index, String[] args) {
    if(index < args.length - 1 && index >= 0) {
      return args[index+1];
    } else {
      return ".";
    }
  }

  public static int readOption(String option, String[] args) {
    for (int i = 0; i < args.length; i++) {
      if(args[i].equals(option)) {
        return i;
      }
    }
    return -1;
  }

  public static void printArgs(String[] args) {
    System.err.print("Arguments:");
    for (int i = 0; i < args.length; i++) {
      System.err.print(" " + args[i]);
    }
    System.err.println();
  }
}
