package se.ludrik.snappyj.objects;

/**
 * Created by erikhaq on 2014-03-24.
 */
public class SnappyVariable {
  public SnappyType type;
  public String id;
  public int variableNumber = 0;
  public boolean isField = false;

  public SnappyVariable(String variableId, String type) {
    id = variableId;
    this.type = new SnappyType(type);
  }

  @Override
  public String toString() {
    return type.toString() + " " + id;
  }
}
