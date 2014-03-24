package se.ludrik.snappyj.objects;

import java.util.*;

/**
 * Created by erikhaq on 2014-03-24.
 */
public class SnappyType {
  public String type;
  public static final List<String> reservedTypes = Arrays.asList("int", "int[]", "boolean");
  public SnappyType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return type;
  }
}
