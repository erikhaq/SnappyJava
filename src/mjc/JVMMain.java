package mjc;
import java.io.IOException;
import se.ludrik.snappyj.*;

/**
 * Created by erikhaq on 2014-03-25.
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
