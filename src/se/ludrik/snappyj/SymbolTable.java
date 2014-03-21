package se.ludrik.snappyj;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Symbol table class.
 *
 */
public class SymbolTable {

  public SnappyClass mainClass = null;
  public Map<String, SnappyClass> classes;

  public SymbolTable() {
    classes = new HashMap<String, SnappyClass>();
  }

  public SnappyClass addMainClass(String classId, String mainMethodParamId) {
    mainClass = new SnappyClass(classId);
    SnappyVariable param = new SnappyVariable(mainMethodParamId, "String[]");
    mainClass.addMethod("void", "main");
    mainClass.methods.get("main").addParameter(param);
    return mainClass;
  }

  public SnappyClass addClass(String classId) {
    SnappyClass newClass = new SnappyClass(classId);
    classes.put(classId, newClass);
    return newClass;
  }

  // Class class
  protected class SnappyClass {
    public String id;
    public Map<String, SnappyVariable> fields;
    public Map<String, SnappyMethod> methods;

    public SnappyClass(String classId) {
      fields = new HashMap<String, SnappyVariable>();
      methods = new HashMap<String, SnappyMethod>();
      id = classId;
    }

    public void addField(String id, String type) {
      SnappyVariable variable = new SnappyVariable(id, type);
      fields.put(variable.id, variable);
    }

    public SnappyMethod addMethod(String returnType, String id) {
      SnappyMethod method = new SnappyMethod(id);
      method.setReturnType(returnType);
      methods.put(method.id, method);
      return method;
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("Class " + id + "\nFields: (");
      for(SnappyVariable field : fields.values()) {
        sb.append(field);
        sb.append(", ");
      }
      sb = fields.size() > 0 ? sb.replace(sb.length()-2, sb.length(), ")") : sb.append(" )");
      for(SnappyMethod method : methods.values()) {
        sb.append(method);
        sb.append("\n");
      }
      return sb.toString();
    }
  }

  // Variable class
  protected class SnappyVariable {
    public SnappyType type;
    public String id;

    public SnappyVariable(String variableId, String type) {
      id = variableId;
      this.type = new SnappyType(type);
    }

    @Override
    public String toString() {
      return type.toString() + " " + id;
    }
  }

  // Type class
  protected class SnappyType {
    public String type;

    public SnappyType(String type) {
      this.type = type;
    }

    @Override
    public String toString() {
      return type;
    }
  }

  // Method class
  protected class SnappyMethod {
    public String id;
    public Map<String, SnappyVariable> parameters;
    public Map<String, SnappyVariable> variables;
    public SnappyType returnType;
    
    public SnappyMethod(String methodId) {
      parameters = new HashMap<String, SnappyVariable>();
      variables = new HashMap<String, SnappyVariable>();
      id = methodId;
    }

    public void addParameter(SnappyVariable param) {
      parameters.put(param.id, param);
    }

    public void addVariable(SnappyVariable variable) {
      variables.put(variable.id, variable);
    }

    public void setReturnType(String returnType) {
      this.returnType = new SnappyType(returnType);
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("\nMethod " + id + "\nParameters: (");
      for(SnappyVariable param : parameters.values()) {
        sb.append(param);
        sb.append(", ");
      }
      sb = parameters.size() > 0 ? sb.replace(sb.length()-2, sb.length(), ")") : sb.append(" )");
      sb.append("\nVariables: (");
      for(SnappyVariable variable : variables.values()) {
        sb.append(variable);
        sb.append(", ");
      }
      sb = variables.size() > 0 ? sb.replace(sb.length()-2, sb.length(), ")") : sb.append(" )");
      sb.append("\nReturn type: " + returnType);
      return sb.toString();
    }

  }
}
