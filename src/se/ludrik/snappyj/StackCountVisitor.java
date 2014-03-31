package se.ludrik.snappyj;

import org.antlr.v4.runtime.misc.NotNull;
import se.ludrik.snappyj.antlr.SnappyJavaBaseVisitor;
import se.ludrik.snappyj.antlr.SnappyJavaParser;
import se.ludrik.snappyj.objects.*;

/**
 * Created with IntelliJ IDEA.
 * User: Megatron
 * Date: 2014-03-29
 * Time: 09:42
 * To change this template use File | Settings | File Templates.
 */
public class StackCountVisitor extends SnappyJavaBaseVisitor<Integer> {

  SymbolTable symbolTable;
  SnappyClass currentClass;
  SnappyMethod currentMethod;
  public StackCountVisitor(SymbolTable symbolTable) {
    this.symbolTable = symbolTable;
  }

  @Override
  public Integer visitMainClass(@NotNull SnappyJavaParser.MainClassContext ctx) {
    currentClass = symbolTable.classes.get(ctx.ID(0).getText());
    currentMethod = currentClass.methods.get("main");
    int stacksize = 0;
    for(SnappyJavaParser.StmtContext stmt : ctx.stmt()) {
     stacksize += stmt.accept(this);
    }
    currentMethod.setStackSize(stacksize);
    currentMethod = null;
    currentClass = null;
    return stacksize;
  }

  @Override
  public Integer visitClassDecl(@NotNull SnappyJavaParser.ClassDeclContext ctx) {
    currentClass = symbolTable.classes.get(ctx.ID().getText());
    for(SnappyJavaParser.MethodDeclContext method : ctx.methodDecl()) {
      method.accept(this);
    }
    currentClass = null;
    return 0;
  }

  @Override
  public Integer visitMethodDecl(@NotNull SnappyJavaParser.MethodDeclContext ctx) {
    currentMethod = currentClass.methods.get(ctx.ID().getText());
    int stackSize = 0;
    for(SnappyJavaParser.StmtContext stmt : ctx.stmt()) {
      stackSize += stmt.accept(this);
    }
    stackSize += ctx.expr().accept(this);
    currentMethod.setStackSize(stackSize);
    currentMethod = null;
    return 0;
  }

  @Override
  public Integer visitBody(@NotNull SnappyJavaParser.BodyContext ctx) {
    return 0;
  }
}
