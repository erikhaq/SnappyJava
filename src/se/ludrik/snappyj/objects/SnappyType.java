package se.ludrik.snappyj.objects;

/**
 * Created by erikhaq on 2014-03-24.
 */
public class SnappyType {
  public String type;

  public SnappyType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return type;
  }
}
