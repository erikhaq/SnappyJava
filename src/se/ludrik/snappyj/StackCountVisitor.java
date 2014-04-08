package se.ludrik.snappyj;

import org.antlr.v4.runtime.misc.NotNull;
import se.ludrik.snappyj.antlr.SnappyJavaBaseVisitor;
import se.ludrik.snappyj.antlr.SnappyJavaParser;
import se.ludrik.snappyj.objects.*;

/**
 * Stack count visitor class
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
    int maxStackSize = 0;
    for(SnappyJavaParser.StmtContext stmt : ctx.stmt()) {
      maxStackSize = Math.max(maxStackSize, stmt.accept(this));
    }
    currentMethod.setStackSize(maxStackSize);
    currentMethod = null;
    currentClass = null;
    return maxStackSize;
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
    int maxStackSize = 0;
    for(SnappyJavaParser.StmtContext stmt : ctx.stmt()) {
      maxStackSize = Math.max(maxStackSize, stmt.accept(this));
    }
    maxStackSize = Math.max(maxStackSize, ctx.expr().accept(this));
    currentMethod.setStackSize(maxStackSize);
    currentMethod = null;
    return 0;
  }

  @Override
  public Integer visitBody(@NotNull SnappyJavaParser.BodyContext ctx) {
    int maxStackSize = 0;
    for(SnappyJavaParser.StmtContext stmt : ctx.stmt()) {
      maxStackSize = Math.max(maxStackSize, stmt.accept(this));
    }
    return maxStackSize;
  }

  @Override public Integer visitIf(@NotNull SnappyJavaParser.IfContext ctx) {
    int maxStackSize = 0;
    for(SnappyJavaParser.StmtContext stmt : ctx.stmt()) {
      maxStackSize = Math.max(maxStackSize, stmt.accept(this));
    }
    maxStackSize = Math.max(ctx.expr().accept(this), maxStackSize);
    return maxStackSize;
  }

  @Override public Integer visitWhile(@NotNull SnappyJavaParser.WhileContext ctx) {
    int maxStackSize = 0;
    maxStackSize = Math.max(maxStackSize, ctx.expr().accept(this));
    maxStackSize = Math.max(maxStackSize, ctx.stmt().accept(this));
    return maxStackSize;
  }

  @Override public Integer visitSout(@NotNull SnappyJavaParser.SoutContext ctx) {
    return ctx.expr().accept(this) + 1; //+1 for loading the printstream
  }

  @Override public Integer visitAssign(@NotNull SnappyJavaParser.AssignContext ctx) {
    int isField = 0;
    String varName = ctx.ID().getText();
    if(currentClass.fields.containsKey(varName)) isField = 1;
    return ctx.expr().accept(this) + isField;
  }

  @Override public Integer visitArrayAssign(@NotNull SnappyJavaParser.ArrayAssignContext ctx) {
    return Math.max(ctx.expr(0).accept(this), ctx.expr(1).accept(this) + 1) + 1; //+1 for array obj. ref.
  }

  @Override public Integer visitParenExp(@NotNull SnappyJavaParser.ParenExpContext ctx) {
    return ctx.expr().accept(this);
  }

  @Override public Integer visitArrayExp(@NotNull SnappyJavaParser.ArrayExpContext ctx) {
    return Math.max(ctx.expr(0).accept(this), ctx.expr(1).accept(this) + 1);
  }

  @Override public Integer visitCallExp(@NotNull SnappyJavaParser.CallExpContext ctx) {
    return Math.max(ctx.expr().accept(this), ctx.exprList().accept(this)+1);
  }

  @Override public Integer visitExprList(@NotNull SnappyJavaParser.ExprListContext ctx) {
    int maxStackSize = 0, paramCount = 1;

    if (ctx.expr() != null) {
      maxStackSize = ctx.expr().accept(this);
    }

    for(SnappyJavaParser.ExprRestContext exprRest : ctx.exprRest()) {
      maxStackSize = Math.max(maxStackSize, exprRest.expr().accept(this) + paramCount++);
    }
    return maxStackSize;
  }

  @Override public Integer visitLengthExp(@NotNull SnappyJavaParser.LengthExpContext ctx) {
    return ctx.expr().accept(this);
  }

  @Override public Integer visitNotExp(@NotNull SnappyJavaParser.NotExpContext ctx) {
    return ctx.expr().accept(this) + 1; //+1 for the '!'
  }

  @Override public Integer visitNewIntArrayExp(@NotNull SnappyJavaParser.NewIntArrayExpContext ctx) {
    return ctx.expr().accept(this);
  }

  @Override public Integer visitNewIdExp(@NotNull SnappyJavaParser.NewIdExpContext ctx) {
    return 2; // 1 for new-mnemonic and 1 for dup-mnemonic
  }

  @Override public Integer visitMultiOp(@NotNull SnappyJavaParser.MultiOpContext ctx) {
    return Math.max(ctx.expr(0).accept(this), ctx.expr(1).accept(this)+1); //+1 for right side
  }

  @Override public Integer visitAddSubOp(@NotNull SnappyJavaParser.AddSubOpContext ctx) {
    return Math.max(ctx.expr(0).accept(this), ctx.expr(1).accept(this) + 1); //+1 for right side
  }

  @Override public Integer visitLTComp(@NotNull SnappyJavaParser.LTCompContext ctx) {
    return Math.max(ctx.expr(0).accept(this), ctx.expr(1).accept(this) + 1); //+1 for right side
  }

  @Override public Integer visitGTComp(@NotNull SnappyJavaParser.GTCompContext ctx) {
    return Math.max(ctx.expr(0).accept(this), ctx.expr(1).accept(this) + 1); //+1 for right side
  }

  @Override public Integer visitAndComp(@NotNull SnappyJavaParser.AndCompContext ctx) {
    return Math.max(ctx.expr(0).accept(this), ctx.expr(1).accept(this) + 1); //+1 for right side
  }

  @Override public Integer visitCEQComp(@NotNull SnappyJavaParser.CEQCompContext ctx) {
    return Math.max(ctx.expr(0).accept(this), ctx.expr(1).accept(this) + 1); //+1 for right side
  }

  @Override public Integer visitCNEComp(@NotNull SnappyJavaParser.CNECompContext ctx) {
    return Math.max(ctx.expr(0).accept(this), ctx.expr(1).accept(this) + 1); //+1 for right side
  }

  @Override public Integer visitNumExp(@NotNull SnappyJavaParser.NumExpContext ctx) {
    return 1;
  }

  @Override public Integer visitBoolExp(@NotNull SnappyJavaParser.BoolExpContext ctx) {
    return 1;
  }

  @Override public Integer visitIdExp(@NotNull SnappyJavaParser.IdExpContext ctx) {
    return 1;
  }

  @Override public Integer visitThisExp(@NotNull SnappyJavaParser.ThisExpContext ctx) {
    return 1;
  }
}
