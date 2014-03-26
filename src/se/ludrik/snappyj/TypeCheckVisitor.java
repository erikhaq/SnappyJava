package se.ludrik.snappyj;

import org.antlr.v4.runtime.misc.NotNull;
import se.ludrik.snappyj.objects.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import se.ludrik.snappyj.antlr.*;
import se.ludrik.snappyj.antlr.SnappyJavaParser.*;

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
    String type = ctx.getText();
    if(!isValidType(type)) {
      //ERROR: No such type
      System.err.printf("Error:(%d, %d) java: no such type exists: %s\n",
              ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine(), type);

    }
    return new SnappyType(type);
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
    ctx.expr().accept(this);
    return null;
  }

  @Override
  public SnappyType visitAssign(@NotNull SnappyJavaParser.AssignContext ctx) {
    SnappyVariable left = getVariable(ctx.ID().getText());
    SnappyType rightExp = ctx.expr().accept(this);

    if(left == null) {
      ErrorHandler.missingVariableSymbol(ctx.ID().getSymbol(), currentMethod.id);
    } else if(!left.type.equals(rightExp)) {
      ErrorHandler.incompatibleTypes(ctx.expr().getStart(), left.type.toString(), rightExp.type );
    }
    return null;

  }

  @Override
  public SnappyType visitArrayAssign(@NotNull SnappyJavaParser.ArrayAssignContext ctx) {
    SnappyVariable id = getVariable(ctx.ID().getText());
    SnappyType innerExp = ctx.expr(0).accept(this);
    SnappyType assingExp = ctx.expr(1).accept(this);
    if(id == null) {
      ErrorHandler.missingVariableSymbol(ctx.ID().getSymbol(), currentMethod.id);
    } else if(!id.type.equals(SnappyType.INT_ARRAY_TYPE)) {
      // the left hand side identifier is not of type INT_ARRAY!
      ErrorHandler.incompatibleTypes(ctx.ID().getSymbol(), SnappyType.INT_ARRAY_TYPE.type, id.type.toString());
    }
    // check that inner expression is of type INT_TYPE
    if(!innerExp.equals(SnappyType.INT_TYPE)) {
      ErrorHandler.incompatibleTypes(ctx.expr(0).getStart(), SnappyType.INT_TYPE.type, innerExp.type);
    }
    // check that the right hand side is of type INT_ARRAY
    if(!assingExp.equals(SnappyType.INT_ARRAY_TYPE)) {
      ErrorHandler.incompatibleTypes(ctx.expr(1).getStart(), SnappyType.INT_ARRAY_TYPE.type, assingExp.type);
    }
    return null;
  }
 /* ------------------------------------- Expressions ---------------------------------------------- */
  public void doOpCheck(ExprContext leftCtx, ExprContext rightCtx, SnappyType opType) {
    SnappyType leftType = leftCtx.accept(this);
    SnappyType rightType = rightCtx.accept(this);
    if(!leftType.equals(opType)) {
      ErrorHandler.incompatibleTypes(leftCtx.getStart(),opType.type, leftType.type );
    }
    if(!rightType.equals(opType)) {
      ErrorHandler.incompatibleTypes(rightCtx.getStart(),opType.type, rightType.type );
    }
  }

  @Override
  public SnappyType visitMultiOp(@NotNull MultiOpContext ctx) {


    doOpCheck(ctx.expr(0), ctx.expr(1), SnappyType.INT_TYPE);

    return SnappyType.INT_TYPE;
  }

  @Override
  public SnappyType visitAddSubOp(@NotNull AddSubOpContext ctx) {

    doOpCheck(ctx.expr(0), ctx.expr(1), SnappyType.INT_TYPE);

    return SnappyType.INT_TYPE;
  }

  @Override
  public SnappyType visitLTComp(@NotNull LTCompContext ctx) {

    doOpCheck(ctx.expr(0), ctx.expr(1), SnappyType.INT_TYPE);

    return SnappyType.BOOL_TYPE;
  }

  @Override
  public SnappyType visitGTComp(@NotNull GTCompContext ctx) {

    doOpCheck(ctx.expr(0), ctx.expr(1), SnappyType.INT_TYPE);

    return SnappyType.BOOL_TYPE;
  }

  @Override
  public SnappyType visitAndComp(@NotNull AndCompContext ctx) {
    SnappyType opType = SnappyType.BOOL_TYPE;
    doOpCheck(ctx.expr(0), ctx.expr(1), opType);

    return opType;
  }

  @Override
  public SnappyType visitArrayExp(@NotNull SnappyJavaParser.ArrayExpContext ctx) {
    SnappyType returnType = SnappyType.INT_TYPE;
    // get type of the left expression
    SnappyType leftType = ctx.expr(0).accept(this);
    SnappyType innerType = ctx.expr(1).accept(this);

    if(!leftType.equals(SnappyType.INT_ARRAY_TYPE)) {
      // ERROR, Left type must be of INT_ARRAY
      System.err.println("Left type must be of type int[]");
      ErrorHandler.incompatibleTypes(ctx.expr(0).getStart(), SnappyType.INT_ARRAY_TYPE.type, leftType.type);
    }
    if(!innerType.equals(SnappyType.INT_TYPE)){
      // ERROR, Inner must be of INT type
      System.err.println("inner type must be of type int");
      ErrorHandler.incompatibleTypes(ctx.expr(1).getStart(), SnappyType.INT_TYPE.type, innerType.type);
    }
    return returnType;
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
  public SnappyType visitCallExp(@NotNull SnappyJavaParser.CallExpContext ctx) {
    SnappyType returnType = new SnappyType("Not defined");
    SnappyType classType = ctx.expr().accept(this);
    ArrayList<String> requiredParamTypes = new ArrayList<String>();
    ArrayList<String> foundParamTypes = new ArrayList<String>();
    boolean foundError = false;
    if(classType == null) {
      ErrorHandler.missingClassSymbol(ctx.expr().getStart(), currentMethod.id);
    } else if(symbolTable.classes.containsKey(classType.type)) {
      SnappyClass leftClass = symbolTable.classes.get(classType.type);
      String methodName = ctx.ID().getText();
      if(!leftClass.methods.containsKey(methodName)) {

        ErrorHandler.noSuchMethod(ctx.ID().getSymbol(), classType.type);
      } else {

        // check that parameters in exprlist are of same type as declared in method methodName
        SnappyMethod method = leftClass.methods.get(methodName);
        returnType = method.returnType;
        // First check that number of parameters defined in method is same as inserted in current method call
        // Also don't enter block if there are no parameters to check.

        if(method.parameters.size() == ctx.exprList().getChildCount() && method.parameters.size() > 0) {
          // Get all declared parameters from method
          ArrayList<SnappyVariable> declaredParams = new ArrayList<SnappyVariable>(method.parameters.values());
          // get first input type and first declared type
          SnappyType currInputType = ctx.exprList().expr().accept(this);
          SnappyType currDeclType = declaredParams.get(0).type;
          requiredParamTypes.add(currDeclType.type);
          foundParamTypes.add(currInputType.type);
          // check that they are of the same type
          if(!currInputType.equals(currDeclType)) {
            // invalid method parameters
            foundError = true;
          }
          // Get the rest of the input parameters from exprRest
          List<ExprRestContext> inputParams = ctx.exprList().exprRest();

          // loop through all input parameters and check that they are of same type as defined for current method.
          for(int i = 0; i < inputParams.size(); i++) {
            currInputType = inputParams.get(i).accept(this);
            currDeclType = declaredParams.get(i+1).type;
            requiredParamTypes.add(currDeclType.type);
            foundParamTypes.add(currInputType.type);
            if(!currInputType.equals(currDeclType)) {
              // invalid method parameters
              foundError = true;

            }
          }

          if(foundError) {
            ErrorHandler.invalidMethodParams(ctx.ID().getSymbol(), classType.type,requiredParamTypes.toString(), foundParamTypes.toString());
          }
        } else {
          if(method.parameters.size() != ctx.exprList().getChildCount()) {
           ErrorHandler.invalidMethodParamSize(ctx.ID().getSymbol(), classType.type);
          }
        }
      }
    }
    return returnType;
  }


  @Override
  public SnappyType visitNumExp(@NotNull SnappyJavaParser.NumExpContext ctx) {
    try {
      Integer.parseInt(ctx.NUM().getText());
    } catch (NumberFormatException e) {
      ErrorHandler.notAnumber(ctx.getStart());
    } finally {
      return SnappyType.INT_TYPE;
    }

  }

  @Override
  public SnappyType visitBoolExp(@NotNull SnappyJavaParser.BoolExpContext ctx) {
    return SnappyType.BOOL_TYPE;
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
  public SnappyType visitThisExp(@NotNull SnappyJavaParser.ThisExpContext ctx) {
    if(currentClass.id.equals(symbolTable.mainClass.id)) {
      ErrorHandler.cantCallThisFromStaticContext(ctx.getStart());
    }
    return new SnappyType(currentClass.id);
  }

  @Override
  public SnappyType visitNewIntArrayExp(@NotNull NewIntArrayExpContext ctx) {
    SnappyType exprType = ctx.expr().accept(this);
    if(!exprType.equals(SnappyType.INT_TYPE)) {
      ErrorHandler.incompatibleTypes(ctx.expr().getStart(), SnappyType.INT_TYPE.type, exprType.type);
    }
    return SnappyType.INT_ARRAY_TYPE;
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


  @Override
  public SnappyType visitNotExp(@NotNull SnappyJavaParser.NotExpContext ctx) {
    SnappyType exprType = ctx.expr().accept(this);
    if(!exprType.equals(SnappyType.BOOL_TYPE)) {
      ErrorHandler.incompatibleTypes(ctx.expr().getStart(), SnappyType.BOOL_TYPE.type, exprType.type );
    }
    return SnappyType.BOOL_TYPE;
  }


  @Override
  public SnappyType visitParenExp(@NotNull SnappyJavaParser.ParenExpContext ctx) {
    return ctx.expr().accept(this);
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
  public SnappyType visitExprRest(@NotNull SnappyJavaParser.ExprRestContext ctx) {
    return ctx.expr().accept(this);
  }

}
