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
    return ctx.expr().accept(this); //TODO maybe +1 for putting 'this' reference on stack
  }

  @Override public Integer visitArrayAssign(@NotNull SnappyJavaParser.ArrayAssignContext ctx) {
    return Math.max(ctx.expr(0).accept(this), ctx.expr(1).accept(this)) + 1; //+1 for array obj. ref.
    //todo if left hand size is max, we do +1. if right hand side is max or equal to left hand side
    // (todo) we do +2. (+1 for array ref, +1 for left hand side)
  }

  @Override public Integer visitParenExp(@NotNull SnappyJavaParser.ParenExpContext ctx) {
    return ctx.expr().accept(this);
  }

  @Override public Integer visitArrayExp(@NotNull SnappyJavaParser.ArrayExpContext ctx) {
    return Math.max(ctx.expr(0).accept(this), ctx.expr(1).accept(this));
    //todo if right hand side is max, return +1 since we also have the left hand side on stack
    // todo: should always clamp between max and min, min here is 2
  }

  @Override public Integer visitCallExp(@NotNull SnappyJavaParser.CallExpContext ctx) {
    return Math.max(ctx.expr().accept(this), ctx.exprList().accept(this));
    //todo +1 if right hand side is max or equal to left hand side. clamp to min of 2 stack size
  }

  @Override public Integer visitExprList(@NotNull SnappyJavaParser.ExprListContext ctx) {
    int maxStackSize = 0, paramCount = 1;

    if (ctx.expr() != null) {
      maxStackSize = ctx.expr().accept(this);
    }

    for(SnappyJavaParser.ExprRestContext exprRest : ctx.exprRest()) {
      maxStackSize = Math.max(maxStackSize, exprRest.expr().accept(this) + paramCount++);
    }
    return maxStackSize++; //TODO does this not return maxStackSize before it has been incremented? maybe ++maxStackSize
  }

  @Override public Integer visitExprRest(@NotNull SnappyJavaParser.ExprRestContext ctx) {
    return ctx.expr().accept(this);
    //TODO this is never used?
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

  @Override public Integer visitNumExp(@NotNull SnappyJavaParser.NumExpContext ctx) {
    return 1;
  }

  @Override public Integer visitBoolExp(@NotNull SnappyJavaParser.BoolExpContext ctx) {
    return 1;
  }

  @Override public Integer visitIdExp(@NotNull SnappyJavaParser.IdExpContext ctx) {
    return 0; //todo this is not right?
  }

  @Override public Integer visitThisExp(@NotNull SnappyJavaParser.ThisExpContext ctx) {
    return 0; //todo this is not right?
  }
}
