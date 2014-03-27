package se.ludrik.snappyj;

import org.antlr.v4.runtime.misc.NotNull;
import se.ludrik.snappyj.antlr.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * Created with IntelliJ IDEA.
 * User: Megatron
 * Date: 2014-03-26
 * Time: 14:41
 * To change this template use File | Settings | File Templates.
 */
public class CodeGenVisitor extends SnappyJavaBaseVisitor {
  BufferedWriter jasminWriter;
  SymbolTable symbolTable;
  public CodeGenVisitor(SymbolTable symbolTable, BufferedWriter writer ) {
    this.symbolTable = symbolTable;
    jasminWriter = writer;
  }

  @Override
  public Object visitMainClass(@NotNull SnappyJavaParser.MainClassContext ctx) {
    return super.visitMainClass(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public Object visitClassDecl(@NotNull SnappyJavaParser.ClassDeclContext ctx) {
    return super.visitClassDecl(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public Object visitVarDecl(@NotNull SnappyJavaParser.VarDeclContext ctx) {
    return super.visitVarDecl(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public Object visitMethodDecl(@NotNull SnappyJavaParser.MethodDeclContext ctx) {
    return super.visitMethodDecl(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public Object visitFormalList(@NotNull SnappyJavaParser.FormalListContext ctx) {
    return super.visitFormalList(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public Object visitFormalRest(@NotNull SnappyJavaParser.FormalRestContext ctx) {
    return super.visitFormalRest(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public Object visitType(@NotNull SnappyJavaParser.TypeContext ctx) {
    return super.visitType(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }


 /** ---------------------------------- Statements -------------------------------------------------------------- */

 @Override
  public Object visitBody(@NotNull SnappyJavaParser.BodyContext ctx) {
    return super.visitBody(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public Object visitIf(@NotNull SnappyJavaParser.IfContext ctx) {
    return super.visitIf(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public Object visitWhile(@NotNull SnappyJavaParser.WhileContext ctx) {
    return super.visitWhile(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public Object visitSout(@NotNull SnappyJavaParser.SoutContext ctx) {
    return super.visitSout(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public Object visitAssign(@NotNull SnappyJavaParser.AssignContext ctx) {
    return super.visitAssign(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public Object visitArrayAssign(@NotNull SnappyJavaParser.ArrayAssignContext ctx) {
    return super.visitArrayAssign(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  /**------------------------------------------ Expressions ---------------------------------------------------*/


  @Override
  public Object visitParenExp(@NotNull SnappyJavaParser.ParenExpContext ctx) {
    return super.visitParenExp(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public Object visitArrayExp(@NotNull SnappyJavaParser.ArrayExpContext ctx) {
    return super.visitArrayExp(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public Object visitCallExp(@NotNull SnappyJavaParser.CallExpContext ctx) {
    return super.visitCallExp(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public Object visitLengthExp(@NotNull SnappyJavaParser.LengthExpContext ctx) {
    return super.visitLengthExp(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public Object visitNotExp(@NotNull SnappyJavaParser.NotExpContext ctx) {
    return super.visitNotExp(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public Object visitNewIntArrayExp(@NotNull SnappyJavaParser.NewIntArrayExpContext ctx) {
    return super.visitNewIntArrayExp(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public Object visitNewIdExp(@NotNull SnappyJavaParser.NewIdExpContext ctx) {
    return super.visitNewIdExp(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public Object visitMultiOp(@NotNull SnappyJavaParser.MultiOpContext ctx) {
    return super.visitMultiOp(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }
  @Override
  public Object visitAddSubOp(@NotNull SnappyJavaParser.AddSubOpContext ctx) {
    return super.visitAddSubOp(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public Object visitLTComp(@NotNull SnappyJavaParser.LTCompContext ctx) {
    return super.visitLTComp(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public Object visitGTComp(@NotNull SnappyJavaParser.GTCompContext ctx) {
    return super.visitGTComp(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public Object visitAndComp(@NotNull SnappyJavaParser.AndCompContext ctx) {
    return super.visitAndComp(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public Object visitNumExp(@NotNull SnappyJavaParser.NumExpContext ctx) {
    String intLit = ctx.NUM().getText();
    int i = Integer.parseInt(intLit);
    
    return null;
  }

  @Override
  public Object visitBoolExp(@NotNull SnappyJavaParser.BoolExpContext ctx) {
    return super.visitBoolExp(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public Object visitIdExp(@NotNull SnappyJavaParser.IdExpContext ctx) {
    return super.visitIdExp(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public Object visitThisExp(@NotNull SnappyJavaParser.ThisExpContext ctx) {
    return super.visitThisExp(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }
 /** --------------------------------------------------------- ExprList-----------------------------------------------*/
  @Override
  public Object visitExprList(@NotNull SnappyJavaParser.ExprListContext ctx) {
    return super.visitExprList(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public Object visitExprRest(@NotNull SnappyJavaParser.ExprRestContext ctx) {
    return super.visitExprRest(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public Object visitBoolLiterals(@NotNull SnappyJavaParser.BoolLiteralsContext ctx) {
    return super.visitBoolLiterals(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }
}
