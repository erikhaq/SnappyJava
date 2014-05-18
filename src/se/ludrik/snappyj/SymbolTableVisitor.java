package se.ludrik.snappyj;

import java.util.LinkedList;
import org.antlr.v4.runtime.misc.NotNull;
import se.ludrik.snappyj.antlr.*;
import se.ludrik.snappyj.objects.*;
import se.ludrik.snappyj.antlr.SnappyJavaParser.ClassDeclContext;
import se.ludrik.snappyj.antlr.SnappyJavaParser.ExtendContext;

/**
 * Symbol table visitor
 */
public class SymbolTableVisitor extends SnappyJavaBaseVisitor {
  private SymbolTable symbolTable;
  private SnappyClass currentClass = null;
  private SnappyMethod currentMethod = null;

  public SymbolTableVisitor(SymbolTable table) {
    symbolTable = table;
  }

  @Override public Object visitProgram(@NotNull SnappyJavaParser.ProgramContext ctx) {
    ctx.mainClass().accept(this);

    // All this is needed to make sure super classes are always added first to the symbol table

    // Linked list that will hold all ClassDeclContexts in the right order
    LinkedList<ClassDeclContext> classDeclContexts = new LinkedList<ClassDeclContext>();

    for (ClassDeclContext classCtx : ctx.classDecl()) {
      ExtendContext extendContext = classCtx.extend();
      if (extendContext != null) {
        // This class extends another class.
        // We want to make sure it is placed in the linked list so that,
        // firstly, it is put in front of any class that extends it, and
        // secondly, after the class that it extends (if present in list,
        // otherwise place last)
        String thisExtendsClassName =
            extendContext.ID().getText(); // This class' extended class name
        String thisClassName = classCtx.ID().getText();             // This class' name
        String
            currClassName;                                       // Current class name from iterating the linked list
        String
            currClassExtendsName;                                // Current class' extended class from iterating list
        ClassDeclContext
            currClassCtx;                              // Current classCtx from iterating list
        boolean added = false;
        for (int i = 0; i < classDeclContexts.size(); i++) {
          added = false;
          currClassCtx = classDeclContexts.get(i);
          currClassName = currClassCtx.ID().getText();
          if (currClassCtx.extend() != null) {
            // The class at position i in the linked list extends another class
            currClassExtendsName = currClassCtx.extend().ID().getText();
            if (currClassExtendsName.equals(thisClassName)) {
              // The class in the linked list extends the class we are currently
              // trying to add, so we add it in front of this class.
              classDeclContexts.add(i, classCtx);
              added = true;
              break;
            }
          } else {
            if (thisExtendsClassName.equals(currClassName)) {
              // The class we are trying to add extends this class in the linked list
              // so we add it after this class.
              classDeclContexts.add(i + 1, classCtx);
              added = true;
              break;
            }
          }
        }
        if (!added) {
          // The class we are trying to add extends a class not yet
          // present in the linked list so we add it last.
          classDeclContexts.add(classCtx);
        }
      } else {
        // This class does not extend any other class, just add it to front of list
        classDeclContexts.addFirst(classCtx);
      }
    }

    // Visits all contexts in the right order
    for (ClassDeclContext c : classDeclContexts) {
      c.accept(this);
    }

    return null;
  }

  @Override public Object visitMainClass(@NotNull SnappyJavaParser.MainClassContext ctx) {
    try {

      if (!ctx.ID(1).getText().equals("main")) {
        ErrorHandler.noMainMethod(ctx.ID(1).getSymbol());
      }

      currentClass = symbolTable.addMainClass(ctx.ID(0).getText(), ctx.ID(2).getText());
      currentMethod = currentClass.methods.get("main");
    } catch (NullPointerException e) {
    }

    for (SnappyJavaParser.VarDeclContext v : ctx.varDecl()) {
      v.accept(this);
    }

    currentClass = null;
    currentMethod = null;
    return null;
  }

  @Override public Object visitClassDecl(@NotNull ClassDeclContext ctx) {
    String className = ctx.ID().getText();

    if (symbolTable.classes.containsKey(className)) {
      ErrorHandler.classAlreadyDefined(ctx.ID().getSymbol());
      return null;
    }

    if (ctx.extend() != null) {
      SnappyClass extendedClass = symbolTable.getClass(ctx.extend().ID().getText());

      // "Perfect" check for cyclic inheritance :P
      if (extendedClass == null) {
        ErrorHandler.cyclicInheritance(ctx.ID().getSymbol(), className);
        return null;
      }

      currentClass = symbolTable.addClass(className, extendedClass);
    } else {
      currentClass = symbolTable.addClass(className);
    }

    for (SnappyJavaParser.VarDeclContext v : ctx.varDecl()) {
      v.accept(this);
    }

    for (SnappyJavaParser.MethodDeclContext m : ctx.methodDecl()) {
      m.accept(this);
    }

    currentClass = null;
    return null;
  }

  @Override public Object visitMethodDecl(@NotNull SnappyJavaParser.MethodDeclContext ctx) {
    String returnType = ctx.type().getText(), methodId = ctx.ID().getText();
    if (currentClass.methods.containsKey(methodId)) {
      ErrorHandler.methodAlreadyDefined(ctx.ID().getSymbol(), currentClass.id);
      return null;
    }
    currentMethod = currentClass.addMethod(returnType, methodId);
    // Visit formallist here
    ctx.formalList().accept(this);
    // Visit vardeclr here
    for (SnappyJavaParser.VarDeclContext v : ctx.varDecl()) {
      v.accept(this);
    }
    currentMethod = null;
    return null;
  }

  @Override public Object visitFormalList(@NotNull SnappyJavaParser.FormalListContext ctx) {
    if (ctx.getChildCount() == 0) {
      return null;
    }
    currentMethod.addParameter(ctx.type().getText(), ctx.ID().getText());
    for (SnappyJavaParser.FormalRestContext r : ctx.formalRest()) {
      r.accept(this);
    }
    return null;
  }

  @Override public Object visitFormalRest(@NotNull SnappyJavaParser.FormalRestContext ctx) {
    String paramName = ctx.ID().getText();
    // check if param variable already exists.
    if (currentMethod.parameters.containsKey(paramName)) {
      ErrorHandler.variableAlreadyDefinedInMethod(ctx.ID().getSymbol(), currentMethod.id);
      return null;
    }
    currentMethod.addParameter(ctx.type().getText(), ctx.ID().getText());
    return null;
  }

  @Override public Object visitVarDecl(@NotNull SnappyJavaParser.VarDeclContext ctx) {
    String varId = ctx.ID().getText(), varType = ctx.type().getText();

    if (currentMethod != null) {
      // this variable belongs to a method.
      if (currentMethod.variables.containsKey(varId) || currentMethod.parameters.containsKey(
          varId)) {

        ErrorHandler.variableAlreadyDefinedInMethod(ctx.ID().getSymbol(), currentMethod.id);
        return null;
      }
      currentMethod.addVariable(varType, varId);
    } else {
      // we are not in the scope of a method so this variable must be a field
      if (currentClass.fields.containsKey(varId)) {
        ErrorHandler.variableAlreadyDefinedInClass(ctx.ID().getSymbol(), currentClass.id);
        return null;
      }
      currentClass.addField(ctx.ID().getText(), ctx.type().getText());
    }
    return null;
  }
}

