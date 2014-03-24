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
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null || getClass() != other.getClass()) return false;

    SnappyType that = (SnappyType) other;

    if (!type.equals(that.type)) return false;

    return true;
  }

  @Override
  public int hashCode() {
    return type.hashCode();
  }

  @Override
  public String toString() {
    return type;
  }
}
