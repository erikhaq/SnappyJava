package se.ludrik.snappyj;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.TerminalNode;
import se.ludrik.snappyj.SymbolTable.*;

public class TypeCheckVisitor extends SnappyJavaBaseVisitor{
  SnappyClass currentClass;
  SnappyMethod currentMethod;
  SymbolTable symbolTable;
  List<String> reservedTypes;
  public enum ReservedTypes {
    INT("int"),
    INT_ARRAY("int[]"),
    BOOLEAN("boolean")
    ;
    private final String text;
    private ReservedTypes(final String text) {
      this.text = text;
    }

    @Override
    public String toString() {
      return this.text;
    }
  }
  public TypeCheckVisitor(SymbolTable table) {
    symbolTable = table;

    reservedTypes = new ArrayList<String>();

    for(ReservedTypes r : ReservedTypes.values()) {
       reservedTypes.add(r.toString());
    }



  }
  public boolean checkType(String type) {
    if(reservedTypes.contains(type)) return true;
    if(symbolTable.classes.containsKey(type)) return true;
    return false;
  }

  @Override
  public Object visitBoolLiterals(@NotNull SnappyJavaParser.BoolLiteralsContext ctx) {
    return super.visitBoolLiterals(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public Object visitOp(@NotNull SnappyJavaParser.OpContext ctx) {
    return super.visitOp(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public Object visitVarDecl(@NotNull SnappyJavaParser.VarDeclContext ctx) {
    if(!checkType(ctx.type().getText())) System.err.println("No such type: " + ctx.type().getText());
    return null;
  }

  @Override
  public Object visitStmt(@NotNull SnappyJavaParser.StmtContext ctx) {
    return super.visitStmt(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public Object visitExpr(@NotNull SnappyJavaParser.ExprContext ctx) {
    return super.visitExpr(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public Object visitType(@NotNull SnappyJavaParser.TypeContext ctx) {
    return super.visitType(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public Object visitClassDecl(@NotNull SnappyJavaParser.ClassDeclContext ctx) {
    return super.visitClassDecl(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public Object visitFormalList(@NotNull SnappyJavaParser.FormalListContext ctx) {
    return super.visitFormalList(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public Object visitExprRest(@NotNull SnappyJavaParser.ExprRestContext ctx) {
    return super.visitExprRest(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public Object visitProgram(@NotNull SnappyJavaParser.ProgramContext ctx) {
    return super.visitProgram(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public Object visitMainClass(@NotNull SnappyJavaParser.MainClassContext ctx) {
    String mainId = ctx.ID().get(0).getText();
    currentClass = symbolTable.classes.get(mainId);
    currentMethod = currentClass.methods.get("main");
    for (SnappyJavaParser.VarDeclContext v : ctx.varDecl()) {
      v.accept(this);
    }
    for(SnappyJavaParser.StmtContext stmt : ctx.stmt()) {
      stmt.accept(this);
    }

    currentMethod = null;
    currentClass = null;
    return null;
  }

  @Override
  public Object visitMethodDecl(@NotNull SnappyJavaParser.MethodDeclContext ctx) {
    return super.visitMethodDecl(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public Object visitFormalRest(@NotNull SnappyJavaParser.FormalRestContext ctx) {
    return super.visitFormalRest(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public Object visitExprList(@NotNull SnappyJavaParser.ExprListContext ctx) {
    return super.visitExprList(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }
}
