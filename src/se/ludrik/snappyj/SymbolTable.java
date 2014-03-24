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

}
