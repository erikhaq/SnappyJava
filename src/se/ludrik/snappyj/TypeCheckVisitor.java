package se.ludrik.snappyj;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sun.xml.internal.bind.v2.TODO;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.TerminalNode;
import se.ludrik.snappyj.SymbolTable.*;

public class TypeCheckVisitor extends SnappyJavaBaseVisitor<SnappyType>{
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
  public SnappyType visitMainClass(@NotNull SnappyJavaParser.MainClassContext ctx) {
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
    return new SnappyType("mainId");
  }

  @Override
  public SnappyType visitClassDecl(@NotNull SnappyJavaParser.ClassDeclContext ctx) {
    return super.visitClassDecl(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public SnappyType visitMethodDecl(@NotNull SnappyJavaParser.MethodDeclContext ctx) {
    return super.visitMethodDecl(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }


  @Override
  public SnappyType visitVarDecl(@NotNull SnappyJavaParser.VarDeclContext ctx) {
    if(!checkType(ctx.type().getText())) {
      // send error message here for no such symbol
      //TODO
    }
    return null;
  }

  @Override
  public SnappyType visitCallExp(@NotNull SnappyJavaParser.CallExpContext ctx) {

    return super.visitCallExp(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public SnappyType visitThisExp(@NotNull SnappyJavaParser.ThisExpContext ctx) {
    return super.visitThisExp(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public SnappyType visitBoolLiterals(@NotNull SnappyJavaParser.BoolLiteralsContext ctx) {
    return super.visitBoolLiterals(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public SnappyType visitType(@NotNull SnappyJavaParser.TypeContext ctx) {
    return super.visitType(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public SnappyType visitFormalList(@NotNull SnappyJavaParser.FormalListContext ctx) {
    return super.visitFormalList(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public SnappyType visitWhile(@NotNull SnappyJavaParser.WhileContext ctx) {
    return super.visitWhile(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public SnappyType visitExprRest(@NotNull SnappyJavaParser.ExprRestContext ctx) {
    return super.visitExprRest(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public SnappyType visitBoolExp(@NotNull SnappyJavaParser.BoolExpContext ctx) {
    return super.visitBoolExp(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public SnappyType visitFormalRest(@NotNull SnappyJavaParser.FormalRestContext ctx) {
    return super.visitFormalRest(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public SnappyType visitParenExp(@NotNull SnappyJavaParser.ParenExpContext ctx) {
    return super.visitParenExp(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public SnappyType visitNumExp(@NotNull SnappyJavaParser.NumExpContext ctx) {
    return super.visitNumExp(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public SnappyType visitBinaryOp(@NotNull SnappyJavaParser.BinaryOpContext ctx) {
    SnappyJavaParser.ExprContext left = ctx.expr(0);
    SnappyJavaParser.ExprContext right = ctx.expr(1);
    
    return super.visitBinaryOp(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public SnappyType visitExprList(@NotNull SnappyJavaParser.ExprListContext ctx) {
    return super.visitExprList(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public SnappyType visitBody(@NotNull SnappyJavaParser.BodyContext ctx) {
    return super.visitBody(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public SnappyType visitNewIntExp(@NotNull SnappyJavaParser.NewIntExpContext ctx) {
    return super.visitNewIntExp(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public SnappyType visitOp(@NotNull SnappyJavaParser.OpContext ctx) {
    return super.visitOp(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public SnappyType visitAssign(@NotNull SnappyJavaParser.AssignContext ctx) {
    return super.visitAssign(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public SnappyType visitNotExp(@NotNull SnappyJavaParser.NotExpContext ctx) {
    return super.visitNotExp(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public SnappyType visitLengthExp(@NotNull SnappyJavaParser.LengthExpContext ctx) {
    return super.visitLengthExp(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public SnappyType visitProgram(@NotNull SnappyJavaParser.ProgramContext ctx) {
    return super.visitProgram(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public SnappyType visitArrayAssign(@NotNull SnappyJavaParser.ArrayAssignContext ctx) {
    return super.visitArrayAssign(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public SnappyType visitIdExp(@NotNull SnappyJavaParser.IdExpContext ctx) {
    return super.visitIdExp(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public SnappyType visitArrayExp(@NotNull SnappyJavaParser.ArrayExpContext ctx) {
    return super.visitArrayExp(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public SnappyType visitSout(@NotNull SnappyJavaParser.SoutContext ctx) {
    return super.visitSout(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public SnappyType visitIf(@NotNull SnappyJavaParser.IfContext ctx) {
    return super.visitIf(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public SnappyType visitNewIdExp(@NotNull SnappyJavaParser.NewIdExpContext ctx) {
    return super.visitNewIdExp(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }
}
