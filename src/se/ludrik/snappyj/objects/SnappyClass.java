package se.ludrik.snappyj.objects;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by erikhaq on 2014-03-24.
 */
public class SnappyClass {
  public String id;
  public SnappyClass extendedClass;
  public Map<String, SnappyVariable> fields;
  public Map<String, SnappyMethod> methods;

  public SnappyClass(String classId) {
    fields = new HashMap<String, SnappyVariable>();
    methods = new HashMap<String, SnappyMethod>();
    id = classId;
  }

  public SnappyClass(String classId, SnappyClass extendedClass) {
    this(classId); // Calls default constructor
    this.extendedClass = extendedClass;
  }

  public void addField(String id, String type) {
    SnappyVariable variable = new SnappyVariable(id, type);
    variable.isField = true;
    fields.put(variable.id, variable);
  }

  public SnappyMethod addMethod(String returnType, String id) {
    SnappyMethod method = new SnappyMethod(id);
    method.setReturnType(returnType);
    methods.put(method.id, method);
    return method;
  }

  public SnappyVariable getVariable(String varName) {
    SnappyVariable v = fields.get(varName);
    if (v == null && extendedClass != null) {
      // Try recursively to find variable in superclasses
      v = extendedClass.getVariable(varName);
    }
    return v;
  }

  public SnappyMethod getMethod(String methodName) {
    SnappyMethod m = methods.get(methodName);
    if (m == null && extendedClass != null) {
      // Try recursively to find method in superclasses
      m = extendedClass.getMethod(methodName);
    }
    return m;
  }

  public boolean hasMethod(String methodName) {
    if (methods.containsKey(methodName)) {
      return true;
    } else if (extendedClass != null && extendedClass.hasMethod(methodName)) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Class " + id);
    if (extendedClass != null) {
      sb.append(" extends " + extendedClass);
    }
    sb.append("\nFields: (");
    for (SnappyVariable field : fields.values()) {
      sb.append(field);
      sb.append(", ");
    }
    sb = fields.size() > 0 ? sb.replace(sb.length() - 2, sb.length(), ")") : sb.append(" )");
    for (SnappyMethod method : methods.values()) {
      sb.append(method);
      sb.append("\n");
    }
    return sb.toString();
  }
}

