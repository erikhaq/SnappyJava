package se.ludrik.snappyj;

import org.antlr.v4.runtime.misc.NotNull;
import se.ludrik.snappyj.objects.*;
import se.ludrik.snappyj.SnappyJavaParser.*;

public class TypeCheckVisitor extends SnappyJavaBaseVisitor<SnappyType>{
  SnappyClass currentClass;
  SnappyMethod currentMethod;
  SymbolTable symbolTable;

  public TypeCheckVisitor(SymbolTable table) {
    symbolTable = table;

  }
  public boolean isValidType(String type) {
    if(SnappyType.reservedTypes.contains(type)) return true;
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
    return new SnappyType(mainId);
  }

  @Override
  public SnappyType visitClassDecl(@NotNull SnappyJavaParser.ClassDeclContext ctx) {
    String classId = ctx.ID().getText();
    currentClass = symbolTable.classes.get(classId);

    for (SnappyJavaParser.VarDeclContext v : ctx.varDecl()) {
      v.accept(this);
    }
    for(SnappyJavaParser.MethodDeclContext m : ctx.methodDecl()) {
      m.accept(this);
    }
    currentClass = null;
    return new SnappyType(classId);


  }

  @Override
  public SnappyType visitMethodDecl(@NotNull SnappyJavaParser.MethodDeclContext ctx) {
    String methodId = ctx.ID().getText();
    currentMethod = currentClass.methods.get(methodId);
    SnappyType returnType = currentMethod.returnType;

    if(!isValidType(returnType.toString())) {
      ErrorHandler.missingClassSymbol(ctx.type().ID().getSymbol(), currentClass.id);

    }


    //Visit formalList
    ctx.formalList().accept(this);
    //vist varDeclarations
    for(SnappyJavaParser.VarDeclContext v : ctx.varDecl()) {
      v.accept(this);
    }
    // visit statmenets
    for(SnappyJavaParser.StmtContext stmt : ctx.stmt()) {
      stmt.accept(this);
    }
    //visit return expression
    SnappyType returnExpr = ctx.expr().accept(this);
    if(!returnType.equals(returnExpr)) {
      // the return expression is not same as return type
     ErrorHandler.incompatibleTypes(ctx.type().ID().getSymbol(), returnType.toString(), returnExpr.toString());
    }
    //check return expression is same as return type
    currentMethod = null;
    return returnType;
  }


  @Override
  public SnappyType visitVarDecl(@NotNull SnappyJavaParser.VarDeclContext ctx) {
    String varName = ctx.ID().getText();
    SnappyType varType = null;
    if(currentClass != null) {
      if(currentMethod != null) {
        // declaration is a variable
        varType = currentMethod.variables.get(varName).type;
      } else {
        // declaration is a field
        varType = currentClass.fields.get(varName).type;
      }

      if(!isValidType(varType.toString())) {
        // send error message here for no such symbol
        ErrorHandler.missingClassSymbol(ctx.type().ID().getSymbol(), currentClass.id);
      }
    }
    return varType;
    
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
    String paramType = ctx.type().ID().getText();

    if(!isValidType(paramType)) {
      ErrorHandler.missingClassSymbol(ctx.type().ID().getSymbol(), currentClass.id);
    }

    for(FormalRestContext formalRestContext : ctx.formalRest()) {
      formalRestContext.accept(this);
    }

    return null;
  }

  @Override
  public SnappyType visitFormalRest(@NotNull FormalRestContext ctx) {
    String paramType = ctx.type().ID().getText();
    if(!isValidType(paramType)) {
      ErrorHandler.missingClassSymbol(ctx.type().ID().getSymbol(), currentClass.id);
    }
    return null;
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
