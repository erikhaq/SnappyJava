package se.ludrik.snappyj;

import org.antlr.v4.runtime.misc.NotNull;
import se.ludrik.snappyj.objects.*;
import se.ludrik.snappyj.SnappyJavaParser.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collection;

public class TypeCheckVisitor extends SnappyJavaBaseVisitor<SnappyType>{
  SnappyClass currentClass;
  SnappyMethod currentMethod;
  SymbolTable symbolTable;
  List<String> boolOperators = Arrays.asList("&&", "<",">", "<=", ">=");
  List<String> intOperators = Arrays.asList("*", "+", "-");
  public TypeCheckVisitor(SymbolTable table) {
    symbolTable = table;

  }
  public boolean isValidType(String type) {
    if(SnappyType.reservedTypes.contains(type)) return true;
    if(symbolTable.classes.containsKey(type)) return true;
    return false;
  }
  public SnappyVariable getVariable(String var) {
    // Try to get var from fields. If it does not exist it will return null
    SnappyVariable v = currentClass.fields.get(var);
    if(currentMethod != null) {
      // check if variable is bound harder for method
      if(currentMethod.parameters.containsKey(var)) {
        v = currentMethod.parameters.get(var);
      } else if(currentMethod.variables.containsKey(var)) {
        v = currentMethod.variables.get(var);
      }
    }

    return v;
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
  public SnappyType visitVarDecl(@NotNull SnappyJavaParser.VarDeclContext ctx) {
    String varName = ctx.ID().getText();
    SnappyType varType = null;
    if(currentMethod != null) {
      // declaration is a variable
      varType = currentMethod.variables.get(varName).type;
    } else {
      // declaration is a field
      varType = currentClass.fields.get(varName).type;
    }

    if(!isValidType(varType.toString())) {
      // send error message here for no such symbol
      ErrorHandler.missingClassSymbol(ctx.type().getStart(), currentClass.id);
    }

    return varType;

  }


  @Override
  public SnappyType visitMethodDecl(@NotNull SnappyJavaParser.MethodDeclContext ctx) {
    String methodId = ctx.ID().getText();
    currentMethod = currentClass.methods.get(methodId);
    SnappyType returnType = currentMethod.returnType;

    if(!isValidType(returnType.toString())) {
      ErrorHandler.missingClassSymbol(ctx.type().getStart(), currentClass.id);

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
      ErrorHandler.incompatibleTypes(ctx.type().getStart(), returnType.toString(), returnExpr.toString());
    }
    //check return expression is same as return type
    currentMethod = null;
    return returnType;
  }

  @Override
  public SnappyType visitFormalList(@NotNull SnappyJavaParser.FormalListContext ctx) {
    if(ctx.getChildCount() > 0) {
      String paramType = ctx.type().getText();

      if(!isValidType(paramType)) {
        ErrorHandler.missingClassSymbol(ctx.type().ID().getSymbol(), currentClass.id);
      }

      for(FormalRestContext formalRestContext : ctx.formalRest()) {
        formalRestContext.accept(this);
      }
    }

    return null;
  }

  @Override
  public SnappyType visitFormalRest(@NotNull FormalRestContext ctx) {
    String paramType = ctx.type().getText();
    if(!isValidType(paramType)) {
      ErrorHandler.missingClassSymbol(ctx.type().ID().getSymbol(), currentClass.id);
    }
    return null;
  }
  @Override
  public SnappyType visitType(@NotNull SnappyJavaParser.TypeContext ctx) {
    return super.visitType(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  /*-------------------------------------- Statements ----------------------------------------------*/

  @Override
  public SnappyType visitBody(@NotNull SnappyJavaParser.BodyContext ctx) {
    for(StmtContext stmt : ctx.stmt()) {
      stmt.accept(this);
    }
    return null;
  }
  @Override
  public SnappyType visitIf(@NotNull SnappyJavaParser.IfContext ctx) {
    SnappyType exprType = ctx.expr().accept(this);
    if(!exprType.equals(SnappyType.BOOL_TYPE)) {
      ErrorHandler.incompatibleTypes(ctx.expr().getStart(), SnappyType.BOOL_TYPE.type, exprType.type);
    }
    for(StmtContext stmt : ctx.stmt()) {
      stmt.accept(this);
    }

    return null;
  }
  @Override
  public SnappyType visitWhile(@NotNull SnappyJavaParser.WhileContext ctx) {
    SnappyType exprType = ctx.expr().accept(this);
    if(!exprType.equals(SnappyType.BOOL_TYPE)) {
      ErrorHandler.incompatibleTypes(ctx.expr().getStart(), SnappyType.BOOL_TYPE.type, exprType.type);
    }
    ctx.stmt().accept(this);
    return null;
  }
  @Override
  public SnappyType visitSout(@NotNull SnappyJavaParser.SoutContext ctx) {
    return super.visitSout(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  @Override
  public SnappyType visitAssign(@NotNull SnappyJavaParser.AssignContext ctx) {
    SnappyVariable left = getVariable(ctx.ID().getText());
    SnappyType rightExp = ctx.expr().accept(this);
    SnappyType returnType = rightExp;
    if(left == null) {
      ErrorHandler.missingVariableSymbol(ctx.ID().getSymbol(), currentMethod.id);
    } else if(!left.equals(rightExp)) {

      ErrorHandler.incompatibleTypes(ctx.expr().getStart(), left.type.toString(), rightExp.type );
    } else {
      returnType = left.type;
    }
    return returnType;

  }

  @Override
  public SnappyType visitArrayAssign(@NotNull SnappyJavaParser.ArrayAssignContext ctx) {
    SnappyVariable id = getVariable(ctx.ID().getText());
    SnappyType innerExp = ctx.expr(0).accept(this);
    SnappyType assingExp = ctx.expr(1).accept(this);
    if(id == null) {
      ErrorHandler.missingVariableSymbol(ctx.ID().getSymbol(), currentMethod.id);
    }
    if(!innerExp.equals(SnappyType.INT_TYPE)) {
      ErrorHandler.incompatibleTypes(ctx.expr(0).getStart(), SnappyType.INT_TYPE.type, innerExp.type);
    }
    if(!assingExp.equals(SnappyType.INT_ARRAY_TYPE)) {
      ErrorHandler.incompatibleTypes(ctx.expr(1).getStart(), SnappyType.INT_ARRAY_TYPE.type, assingExp.type);
    }
    return SnappyType.INT_ARRAY_TYPE;
  }
 /* ------------------------------------- Expressions ---------------------------------------------- */
  @Override
  public SnappyType visitCallExp(@NotNull SnappyJavaParser.CallExpContext ctx) {
    SnappyType returnType = null;
    SnappyType classType = ctx.expr().accept(this);
    if(classType == null) {
      //TODO: ERROR, LEFTHA
    } else if(symbolTable.classes.containsKey(classType.type)) {
        SnappyClass leftClass = symbolTable.classes.get(classType.type);
      String methodName = ctx.ID().getText();
      if(!leftClass.methods.containsKey(methodName)) {
        //TODO ERROR, NO SUCH METHOD IN CLASS LeftClass
      } else {
        //SnappyClass tmpClass = currentClass;
        SnappyMethod tmpMethod = currentMethod;
        currentMethod = leftClass.methods.get(methodName);
        //currentClass = leftClass;
        returnType = leftClass.methods.get(methodName).returnType;
        ctx.exprList().accept(this);
        currentMethod = tmpMethod;
        //currentClass = tmpClass;
      }

    } else {
      //TODO Incorrect left expression
    }
    return returnType;
  }

  @Override
  public SnappyType visitBinaryOp(@NotNull SnappyJavaParser.BinaryOpContext ctx) {
    SnappyJavaParser.ExprContext left = ctx.expr(0);
    SnappyJavaParser.ExprContext right = ctx.expr(1);

    // get left and right expression type
    SnappyType leftType = left.accept(this);
    SnappyType rightType = right.accept(this);

    // get type of operator
    SnappyType returnType = ctx.op().accept(this);

    // check that left and right hand side are of same type as operator
    if(!leftType.equals(returnType) || !rightType.equals(returnType)) {
      //TODO Throw error, not correct types
    }
    return returnType;
  }




  @Override
  public SnappyType visitBoolExp(@NotNull SnappyJavaParser.BoolExpContext ctx) {
    return SnappyType.BOOL_TYPE;
  }

  @Override
  public SnappyType visitThisExp(@NotNull SnappyJavaParser.ThisExpContext ctx) {
    return new SnappyType(currentClass.id);
  }

  @Override
  public SnappyType visitParenExp(@NotNull SnappyJavaParser.ParenExpContext ctx) {
    return ctx.expr().accept(this);
  }

  @Override
  public SnappyType visitNumExp(@NotNull SnappyJavaParser.NumExpContext ctx) {
    return SnappyType.INT_TYPE;
  }

  @Override
  public SnappyType visitNotExp(@NotNull SnappyJavaParser.NotExpContext ctx) {
    SnappyType exprType = ctx.expr().accept(this);
    if(!exprType.equals(SnappyType.BOOL_TYPE)) {
      ErrorHandler.incompatibleTypes(ctx.expr().getStart(), SnappyType.BOOL_TYPE.type, exprType.type );
    }
    return SnappyType.BOOL_TYPE;
  }

  @Override
  public SnappyType visitNewIntExp(@NotNull SnappyJavaParser.NewIntExpContext ctx) {
    SnappyType exprType = ctx.expr().accept(this);
    if(exprType.equals(SnappyType.INT_TYPE)) {
      ErrorHandler.incompatibleTypes(ctx.expr().getStart(), SnappyType.INT_TYPE.type, exprType.type);
    }
    return SnappyType.INT_ARRAY_TYPE;
  }



  @Override
  public SnappyType visitLengthExp(@NotNull SnappyJavaParser.LengthExpContext ctx) {
    SnappyType exprType = ctx.expr().accept(this);
    if(!exprType.equals(SnappyType.INT_ARRAY_TYPE)) {
      ErrorHandler.notAStatement(ctx.getStart());
    }
    return SnappyType.INT_TYPE;
  }



  @Override
  public SnappyType visitIdExp(@NotNull SnappyJavaParser.IdExpContext ctx) {
    String varName = ctx.ID().getText();
    SnappyType returnType = new SnappyType(varName);
    SnappyVariable var = getVariable(varName);
    if(var == null) {
      ErrorHandler.missingVariableSymbol(ctx.ID().getSymbol(), currentMethod.id);
    } else {
      returnType = var.type;
    }
    return returnType;
  }

  @Override
  public SnappyType visitArrayExp(@NotNull SnappyJavaParser.ArrayExpContext ctx) {
    SnappyType returnType = SnappyType.INT_TYPE;
    // get type of the left expression
    SnappyType leftType = ctx.expr(0).accept(this);
    SnappyType innerType = ctx.expr(1).accept(this);

    if(!leftType.equals(SnappyType.INT_ARRAY_TYPE)) {
      //TODO ERROR, Left type must be of INT_ARRAY
    }
    if(!innerType.equals(SnappyType.INT_TYPE)){
      //TODO ERROR, Inner must be of INT type
    }
    return returnType;
  }

  @Override
  public SnappyType visitNewIdExp(@NotNull SnappyJavaParser.NewIdExpContext ctx) {
    String className = ctx.ID().getText();
    SnappyType returnType = new SnappyType(className);
    if(!symbolTable.classes.containsKey(className)) {
      ErrorHandler.missingClassSymbol(ctx.ID().getSymbol(), currentMethod.id);
    }
    return returnType;
  }
  /* ------------------------------------------------ OPERATORS -------------------------------------------- */
  @Override
  public SnappyType visitOp(@NotNull SnappyJavaParser.OpContext ctx) {
    SnappyType returnType = null;
    String operator = ctx.getText();
    if(boolOperators.contains(operator)) {
      returnType = SnappyType.BOOL_TYPE;
    } else if(intOperators.contains(operator)) {
      returnType = SnappyType.INT_TYPE;
    }
    return returnType;
  }

  /* ---------------------------------------- Expression List ----------------------------------------------*/
  @Override
  public SnappyType visitExprList(@NotNull SnappyJavaParser.ExprListContext ctx) {

    if(ctx.getChildCount() > 0) {
      // there are expressions to evaluate against method parameters
      ArrayList<SnappyVariable> allVariables = new ArrayList<SnappyVariable>(currentMethod.parameters.values());
      SnappyVariable var = allVariables.get(0);
      SnappyType currType = ctx.expr().accept(this);
      if(!var.type.equals(currType)) {
        ErrorHandler.incompatibleTypes(ctx.expr().getStart(), var.type.toString(), currType.type.toString());
      }
      if(allVariables.size()-1 != ctx.exprRest().size()){
        // Check if method call has as many parameters as declared
        //TODO THROW ERROR THAT METHOD CALL DOES NOT MATCH.
      } else {
        for(int i = 0; i < ctx.exprRest().size(); i++) {
          var = allVariables.get(i+1);
          currType = ctx.exprRest(i).accept(this);
          if(!var.type.equals(currType)) {
            ErrorHandler.incompatibleTypes(ctx.exprRest(i).getStart(), var.type.toString(), currType.type.toString());
          }
        }

      }

    }
    return null;
  }
  @Override
  public SnappyType visitExprRest(@NotNull SnappyJavaParser.ExprRestContext ctx) {
    return ctx.expr().accept(this);
  }

}
