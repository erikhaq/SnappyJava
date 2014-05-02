package se.ludrik.snappyj;

import java.util.HashMap;
import java.util.Map;
import se.ludrik.snappyj.objects.*;

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
    classes.put(classId, mainClass);
    return mainClass;
  }

  public SnappyClass addClass(String classId) {
    SnappyClass newClass = new SnappyClass(classId);
    classes.put(classId, newClass);
    return newClass;
  }

  public SnappyClass addClass(String classId, SnappyClass extendedClass) {
    SnappyClass newClass = new SnappyClass(classId, extendedClass);

    /*  This is apparently not necessary with jasmin,
        but might be for other backends.
    SnappyClass extendedClass = classes.get(extendedClass);
    // Copy all inherited fields & methods from superclass
    if(extendedClass != null) {
      for(SnappyVariable field : extendedClass.fields.values()) {
        newClass.fields.put(field.id, field);
      }
      for(SnappyMethod method : extendedClass.methods.values()) {
        newClass.methods.put(method.id, method);
      }
    }
    */

    classes.put(classId, newClass);
    return newClass;
  }

  public SnappyClass getClass(String classId) {
    return classes.get(classId);
  }

  public SnappyType getExtendedType(String classId) {
    SnappyType type = null;
    SnappyClass subClass = getClass(classId);
    if(subClass != null && subClass.extendedClass != null) {
      type = new SnappyType(subClass.extendedClass.id);
    }
    return type;
  }

}
