package se.ludrik.snappyj;

import se.ludrik.snappyj.objects.*;

/**
 * Created with IntelliJ IDEA.
 * User: Megatron
 * Date: 2014-03-27
 * Time: 12:13
 * To change this template use File | Settings | File Templates.
 */
public class JasminUtils {


  public static String getFieldString(String id, SnappyType type) {
    /** */
    StringBuilder sb = new StringBuilder();
    sb.append(".field ");
    sb.append(id);
    sb.append(" ");
    sb.append(getJasminType(type));
    sb.append("\n");

    return sb.toString();
  }

  public static String getConstuctorString() {
    StringBuilder sb = new StringBuilder();
    sb.append(".method <init>()V \n");
    sb.append("\t.limit stack 1 \n");
    sb.append("\t.limit locals 1 \n");
    sb.append("\taload_0 \n");
    sb.append("\tinvokenonvirtual java/lang/Object/<init>()V \n");
    sb.append("\treturn \n");
    sb.append(".end method\n");

    return sb.toString();
  }
  public static String getOpeningMethodDeclaration(String methodId) {
    StringBuilder sb = new StringBuilder();
    sb.append(".method public ");
    sb.append(methodId);
    sb.append("(");

    return sb.toString();
  }
  public static String getCloseingMethodDeclaration(SnappyType returnType) {
    StringBuilder sb = new StringBuilder();
    sb.append(")");
    sb.append(getJasminType(returnType));
    sb.append("\n");
    return sb.toString();
  }

  public static String getMethodLimits(int stackLimit, int localLimit) {
    StringBuilder sb = new StringBuilder();
    sb.append("\t.limit stack ");
    sb.append(stackLimit);
    sb.append("\n");

    sb.append("\t.limit locals ");
    sb.append(localLimit);
    sb.append("\n");

    return sb.toString();

  }
  public static String getJasminType(SnappyType type) {
    StringBuilder sb = new StringBuilder();
    if(type.equals(SnappyType.INT_TYPE)) sb.append("I");
    else if(type.equals(SnappyType.INT_ARRAY_TYPE)) sb.append("[I");
    else if(type.equals(SnappyType.BOOL_TYPE)) sb.append("I");
    else  {
      sb.append("L");
      sb.append(type.type);
      sb.append(";");
    }
    return sb.toString();
  }

  public static String getReturnString(SnappyType returnType) {
    StringBuilder sb = new StringBuilder();
    sb.append("\t");
    if(returnType.equals(SnappyType.INT_TYPE) || returnType.equals(SnappyType.BOOL_TYPE))sb.append("ireturn ");
    else sb.append("areturn ");
    sb.append("\n");

    return null;
  }

  public static String getStoreString(SnappyVariable var, String className) {
    StringBuilder sb = new StringBuilder();

    if(var.isField) {
      sb.append("aload_0\n");
      sb.append("getfield ");
      sb.append(className);
      sb.append("/");
      sb.append(var.id);
      sb.append(" ");
      sb.append(getJasminType(var.type));
    }
    else {
      
    }


  }


}
