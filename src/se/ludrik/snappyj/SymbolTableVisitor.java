package se.ludrik.snappyj;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.TerminalNode;
import se.ludrik.snappyj.objects.*;

/**
 * Symbol table visitor
 */
public class SymbolTableVisitor extends SnappyJavaBaseVisitor {
  private SymbolTable symbolTable;
  private SnappyClass currentClass = null;
  private SnappyMethod currentMethod = null;

  public SymbolTableVisitor() {
    symbolTable = new SymbolTable();
  }
  public SymbolTableVisitor(SymbolTable table) {
    symbolTable = table;

  }

  @Override public Object visitMainClass(@NotNull SnappyJavaParser.MainClassContext ctx) {
    currentClass = symbolTable.addMainClass(ctx.ID(0).getText(), ctx.ID(1).getText());
    currentMethod = currentClass.methods.get("main");

    for (SnappyJavaParser.VarDeclContext v : ctx.varDecl()) {
      v.accept(this);
    }

    currentClass = null;
    currentMethod = null;
    return null;
  }

  @Override public Object visitClassDecl(@NotNull SnappyJavaParser.ClassDeclContext ctx) {
    String className = ctx.ID().getText();

    if(symbolTable.classes.containsKey(className)) {
      ErrorHandler.classAlreadyDefined(ctx.ID().getSymbol());
      return null;
    }

    currentClass = symbolTable.addClass(className);

    for(SnappyJavaParser.VarDeclContext v : ctx.varDecl()) {
      v.accept(this);
    }

    for(SnappyJavaParser.MethodDeclContext m : ctx.methodDecl()) {
      m.accept(this);
    }

    currentClass = null;
    return null;
  }

  @Override public Object visitMethodDecl(@NotNull SnappyJavaParser.MethodDeclContext ctx) {
    String returnType = ctx.type().getText(), methodId = ctx.ID().getText();
    if(currentClass.methods.containsKey(methodId)) {
      ErrorHandler.methodAlreadyDefined(ctx.ID().getSymbol(), currentClass.id);
      return null;
    }
    currentMethod = currentClass.addMethod(returnType, methodId);
    //visit formallist here!!
    ctx.formalList().accept(this);
    //visit vardeclr here!!
    for(SnappyJavaParser.VarDeclContext v : ctx.varDecl()) {
      v.accept(this);
    }
    currentMethod = null;
    return null;
    //return super.visitMethodDecl(ctx);
  }


  @Override public Object visitFormalList(@NotNull SnappyJavaParser.FormalListContext ctx) {

    currentMethod.addParameter(ctx.type().getText(), ctx.ID().getText());
    for(SnappyJavaParser.FormalRestContext r : ctx.formalRest()) {
      r.accept(this);
    }
    return null;
  }
  @Override public Object visitFormalRest(@NotNull SnappyJavaParser.FormalRestContext ctx) {
    String paramName = ctx.ID().getText();
    // check if param variable already exists.
    if(currentMethod.parameters.containsKey(paramName)) {
      ErrorHandler.variableAlreadyDefinedInMethod(ctx.ID().getSymbol(), currentMethod.id);
      return null;
    }
    currentMethod.addParameter(ctx.type().getText(), ctx.ID().getText());
    return null;
  }
  @Override public Object visitVarDecl(@NotNull SnappyJavaParser.VarDeclContext ctx) {

    if(currentClass != null) {

      if(currentMethod != null){
        // this variable belongs to a method.
        if(currentMethod.variables.containsKey(ctx.ID().getText())) {
          ErrorHandler.variableAlreadyDefinedInMethod(ctx.ID().getSymbol(), currentMethod.id);
          return null;
        }
        currentMethod.addVariable(ctx.type().getText(), ctx.ID().getText());

      } else {
        // we are not in the scope of a method so this variable must be a field
        if(currentClass.fields.containsKey(ctx.ID().getText())) {
          ErrorHandler.variableAlreadyDefinedInClass(ctx.ID().getSymbol(), currentClass.id);
          return null;
        }
        currentClass.addField(ctx.ID().getText(), ctx.type().getText());

      }
    }
    return null;
  }

}
