package se.ludrik.snappyj.antlr;

import se.ludrik.snappyj.objects.SnappyType;

/**
 * Created with IntelliJ IDEA.
 * User: Megatron
 * Date: 2014-03-27
 * Time: 12:13
 * To change this template use File | Settings | File Templates.
 */
public class JasminUtils {

  public static String getFieldString(String id, String type) {

    /** */
    String field = ".field " + id + " ";
    if(type.equals(SnappyType.INT_TYPE.type)) return field + "I\n";
    if(type.equals(SnappyType.INT_ARRAY_TYPE.type)) return field + "[I\n";
    if(type.equals(SnappyType.BOOL_TYPE.type)) return field + "Z\n";

    return field + type + ";\n";
  }
}
