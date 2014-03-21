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

  @Override public Object visitBoolLiterals(@NotNull SnappyJavaParser.BoolLiteralsContext ctx) {
    return super.visitBoolLiterals(ctx);
  }

  @Override public Object visitOp(@NotNull SnappyJavaParser.OpContext ctx) {
    return super.visitOp(ctx);
  }

  @Override public Object visitVarDecl(@NotNull SnappyJavaParser.VarDeclContext ctx) {
    return super.visitVarDecl(ctx);
  }

  @Override public Object visitStmt(@NotNull SnappyJavaParser.StmtContext ctx) {
    return super.visitStmt(ctx);
  }

  @Override public Object visitExpr(@NotNull SnappyJavaParser.ExprContext ctx) {
    return super.visitExpr(ctx);
  }

  @Override public Object visitType(@NotNull SnappyJavaParser.TypeContext ctx) {
    return super.visitType(ctx);
  }

  @Override public Object visitClassDecl(@NotNull SnappyJavaParser.ClassDeclContext ctx) {
    currentClass = symbolTable.addClass(ctx.ID().getText());

    for(SnappyJavaParser.VarDeclContext v : ctx.varDecl()) {
      v.accept(this);
    }

    for(SnappyJavaParser.MethodDeclContext m : ctx.methodDecl()) {
      m.accept(this);
    }

    currentClass = null;
    return null;
    //return super.visitClassDecl(ctx);
  }

  @Override public Object visitFormalList(@NotNull SnappyJavaParser.FormalListContext ctx) {
    return super.visitFormalList(ctx);
  }

  @Override public Object visitExprRest(@NotNull SnappyJavaParser.ExprRestContext ctx) {
    return super.visitExprRest(ctx);
  }

  @Override public Object visitProgram(@NotNull SnappyJavaParser.ProgramContext ctx) {
    return super.visitProgram(ctx);
  }

  @Override public Object visitMainClass(@NotNull SnappyJavaParser.MainClassContext ctx) {
    currentClass = symbolTable.addMainClass(ctx.ID(0).getText(), ctx.ID(1).getText());
    System.out.println(currentClass);
    for (SnappyJavaParser.VarDeclContext v : ctx.varDecl()) {
      v.accept(this);
    }
    //return super.visitMainClass(ctx);
    currentClass = null;
    return null;
  }

  @Override public Object visitMethodDecl(@NotNull SnappyJavaParser.MethodDeclContext ctx) {
    String returnType = ctx.type().getText(), methodId = ctx.ID().getText();
    currentMethod = symbolTable.classes.get(currentClass.id).addMethod(returnType, methodId);
    //visit formallist here!!

    //visit vardeclr here!!
    currentMethod = null;
    return null;
    //return super.visitMethodDecl(ctx);
  }

  @Override public Object visitFormalRest(@NotNull SnappyJavaParser.FormalRestContext ctx) {
    return super.visitFormalRest(ctx);
  }

  @Override public Object visitExprList(@NotNull SnappyJavaParser.ExprListContext ctx) {
    return super.visitExprList(ctx);
  }
}
