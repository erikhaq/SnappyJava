package se.ludrik.snappyj.objects;

import java.util.*;

/**
 * Created by erikhaq on 2014-03-24.
 */
public class SnappyMethod {
  public String id;
  public Map<String, SnappyVariable> parameters;
  public Map<String, SnappyVariable> variables;
  public SnappyType returnType;
  public int numOfLocals = 1;
  private int stackSize = 1;

  public SnappyMethod(String methodId) {
    parameters = new LinkedHashMap<String, SnappyVariable>();

    variables = new HashMap<String, SnappyVariable>();
    id = methodId;
  }

  public void addParameter(SnappyVariable param) {
    parameters.put(param.id, param);
    param.variableNumber = numOfLocals;
    numOfLocals++;
  }

  public void addParameter(String type, String id) {
    SnappyVariable param = new SnappyVariable(id, type);
    param.variableNumber = numOfLocals;
    numOfLocals++;
    parameters.put(id, param);
  }

  public void addVariable(String type, String id) {
    SnappyVariable var = new SnappyVariable(id, type);
    var.variableNumber = numOfLocals;
    numOfLocals++;
    variables.put(id, var);
  }

  public void setReturnType(String returnType) {
    this.returnType = new SnappyType(returnType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("\nMethod " + id + "\nParameters: (");
    for (SnappyVariable param : parameters.values()) {
      sb.append(param);
      sb.append(", ");
    }
    sb = parameters.size() > 0 ? sb.replace(sb.length() - 2, sb.length(), ")") : sb.append(" )");
    sb.append("\nVariables: (");
    for (SnappyVariable variable : variables.values()) {
      sb.append(variable);
      sb.append(", ");
    }
    sb = variables.size() > 0 ? sb.replace(sb.length() - 2, sb.length(), ")") : sb.append(" )");
    sb.append("\nReturn type: " + returnType);
    return sb.toString();
  }

  public int getStackSize() {
    return stackSize;
  }

  public void setStackSize(int stackSize) {
    this.stackSize = stackSize;
  }
}
