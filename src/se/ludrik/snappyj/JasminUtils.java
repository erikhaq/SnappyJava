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

  public static int labelcount = 0;

  public static String getLabel() {
    String l = "Label" + JasminUtils.labelcount;
    JasminUtils.labelcount++;
    return l;
  }


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
  public static String getClosingMethodDeclaration(SnappyType returnType) {
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
    return sb.toString();
  }

  public static String getStoreString(SnappyVariable var, String className) {
    StringBuilder sb = new StringBuilder();
    if(var.isField) {
      /** return the string putfield Classname/fieldname fieldType */
      sb.append(getPutfieldString(var, className));
    }
    else {
      sb.append("\t");
      /** if type is int or bool do an istore else we store an object reference*/
      if(var.type.equals(SnappyType.INT_TYPE) || var.type.equals(SnappyType.BOOL_TYPE)) sb.append("istore ");
      else sb.append("astore ");
      sb.append(var.variableNumber);
      sb.append("\n");
    }
    return sb.toString();
  }

  public static String getLoadIntString(String intLit) {
    //TODO check if int can be loaded with more optimized method than ldc
    StringBuilder sb = new StringBuilder();
    sb.append("\tldc ");
    sb.append(Integer.parseInt(intLit));
    sb.append("\n");
    return sb.toString();
  }

  public static String getAddSubString(String operator) {
    return operator.equals("+") ? "\tiadd\n" : "\tisub\n";
  }

  public static String getLoadString(SnappyVariable var, String className) {
    StringBuilder sb = new StringBuilder();
    sb.append("\t");

    return sb.toString();
  }

  public static String getGetfieldString(SnappyVariable var, String classname) {
    StringBuilder sb = new StringBuilder();
    sb.append("\t");
    /**create the string getfield <classname>/<fieldname> <fieldtype>*/
    sb.append("getfield ");
    sb.append(classname);
    sb.append("/");
    sb.append(var.id);
    sb.append(" ");
    sb.append(getJasminType(var.type));
    sb.append("\n");

    return sb.toString();
  }

  public static String getPutfieldString(SnappyVariable var, String className) {
    StringBuilder sb = new StringBuilder();
    sb.append("\t");

    /** return the string putfield <className>/<fieldname> <fieldType> */
    sb.append("putfield ");
    sb.append(className);
    sb.append("/");
    sb.append(var.id);
    sb.append(" ");
    sb.append(getJasminType(var.type));

    return sb.toString();
  }


  public static String getInvokevirtualString(SnappyMethod method, String className) {
    StringBuilder sb = new StringBuilder();
    sb.append("\t");
    sb.append("invokevirtual ");
    sb.append(className);
    sb.append("/");
    sb.append(method.id);
    sb.append("(");
    for(SnappyVariable param : method.parameters.values()) {
      sb.append(getJasminType(param.type));
    }
    sb.append(")");
    sb.append(getJasminType(method.returnType));
    sb.append("\n");

    return sb.toString();
  }


  public static String getComparatorString(String comparator) {
    String label1 = getLabel(), label2 = getLabel();
    StringBuilder sb = new StringBuilder();
    if(comparator.equals("<")) {
      sb.append("\tif_icmplt ");
    } else if(comparator.equals("<=")) {
      sb.append("\tif_icmple ");
    } else if(comparator.equals(">")) {
      sb.append("\tif_icmpgt ");
    } else { //comparator.equals(">=")
      sb.append("\tif_icmpge ");
    }
    sb.append(label2);
    sb.append("\n");
    sb.append("\ticonst_0\n");
    sb.append("\tgoto ");
    sb.append(label1);
    sb.append("\n\t");
    sb.append(label2);
    sb.append(":\n");
    sb.append("\ticonst_1\n\t");
    sb.append(label1);
    sb.append(":\n");
    return sb.toString();
  }

}
