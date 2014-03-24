package se.ludrik.snappyj;

import java.util.List;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.TerminalNode;
import se.ludrik.snappyj.SymbolTable.*;

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


  @Override public Object visitProgram(@NotNull SnappyJavaParser.ProgramContext ctx) {
    return super.visitProgram(ctx);
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

    //System.out.println(currentClass);

    currentClass = null;
    return null;
    //return super.visitClassDecl(ctx);
  }

  @Override public Object visitMethodDecl(@NotNull SnappyJavaParser.MethodDeclContext ctx) {
    String returnType = ctx.type().getText(), methodId = ctx.ID().getText();
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
