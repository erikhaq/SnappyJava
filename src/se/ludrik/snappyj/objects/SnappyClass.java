package se.ludrik.snappyj.objects;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by erikhaq on 2014-03-24.
 */
public class SnappyClass {
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

