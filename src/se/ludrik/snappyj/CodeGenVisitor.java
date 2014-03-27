package se.ludrik.snappyj;

import java.io.IOException;
import org.antlr.v4.runtime.misc.NotNull;
import se.ludrik.snappyj.antlr.*;
import se.ludrik.snappyj.objects.*;
import se.ludrik.snappyj.antlr.*;
import java.io.BufferedWriter;
import java.io.FileWriter;


/**
 * JVM Code generator class
 */
public class CodeGenVisitor extends SnappyJavaBaseVisitor {
  BufferedWriter jasminWriter;
  SymbolTable symbolTable;
  SnappyClass currentClass;
  SnappyMethod currentMethod;
  private int labelcount = 0;
  public String filePath;
  public CodeGenVisitor(SymbolTable symbolTable, String filePath) {
    this.symbolTable = symbolTable;
    this.filePath = filePath;
  }

  public void newClass(String className) {
    try {
      if(jasminWriter != null) {
        jasminWriter.close();
      }
      jasminWriter = new BufferedWriter(new FileWriter("./" + className + ".s"));
      jasminWriter.write(";\n; Output created by SnappyJava (mailto: snappy@java.se)\n;\n\n");
      jasminWriter.write(".source\t" + filePath + "\n");
      jasminWriter.write(".class\t" + className + "\n");
      jasminWriter.write(".super\tjava/lang/Object" + "\n\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void closeWriter() {
    try {
      if(jasminWriter != null) {
        jasminWriter.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  private String getLabel() {
    String l = "Label" + labelcount;
    labelcount++;
    return l;
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
  public Object visitMainClass(@NotNull SnappyJavaParser.MainClassContext ctx) {
    String mainId = ctx.ID().get(0).getText();
    currentClass = symbolTable.classes.get(mainId);
    currentMethod = currentClass.methods.get("main");
    newClass(mainId);
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
  public Object visitClassDecl(@NotNull SnappyJavaParser.ClassDeclContext ctx) {
    String classId = ctx.ID().getText();
    currentClass = symbolTable.classes.get(classId);
    newClass(classId);

    // visit the fields
    for (SnappyJavaParser.VarDeclContext v : ctx.varDecl()) {
      v.accept(this);
    }

    String constructor = JasminUtils.getConstuctorString();
    try {
      jasminWriter.write(constructor);
    } catch (IOException e) {
      e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    }

    /** Declare all methods */
    for(SnappyJavaParser.MethodDeclContext m : ctx.methodDecl()) {
      m.accept(this);
    }
    currentClass = null;

    return null;
  }

  @Override
  public Object visitVarDecl(@NotNull SnappyJavaParser.VarDeclContext ctx) {
    String varName = ctx.ID().getText();
    SnappyVariable var;
    if(currentMethod == null) {
      var = currentClass.fields.get(varName);
      /** write the text: .field <identifier><type> */
      String fieldString = JasminUtils.getFieldString(varName, var.type);
      try {
        jasminWriter.write(fieldString);

      } catch (IOException e) {
        System.err.println("could not write to file\n");
      }

    }


    return null;
  }

  @Override
  public Object visitMethodDecl(@NotNull SnappyJavaParser.MethodDeclContext ctx) {
    currentMethod = currentClass.methods.get(ctx.ID().getText());
    String methodString = JasminUtils.getOpeningMethodDeclaration(currentMethod.id);
    try {
      jasminWriter.write(methodString);
      /** Write all paramters */
      for(SnappyVariable v : currentMethod.parameters.values()) {
        jasminWriter.write(JasminUtils.getJasminType(v.type));
      }
      jasminWriter.write(JasminUtils.getCloseingMethodDeclaration(currentMethod.returnType));
      jasminWriter.write(JasminUtils.getMethodLimits(10, currentMethod.LOCAL_NUM));

      for(SnappyJavaParser.StmtContext stmt : ctx.stmt()) {
        stmt.accept(this);
      }
      /**  Write the return statement */
      ctx.expr().accept(this);
      jasminWriter.write(JasminUtils.getReturnString(currentMethod.returnType));


    } catch (IOException e) {
      e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    }finally {
      return null;
    }
  }

  @Override
  public Object visitType(@NotNull SnappyJavaParser.TypeContext ctx) {
    return super.visitType(
        ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

 /** ---------------------------------- Statements -------------------------------------------------------------- */

 @Override
  public Object visitBody(@NotNull SnappyJavaParser.BodyContext ctx) {
    for(SnappyJavaParser.StmtContext stmt: ctx.stmt()) {
      stmt.accept(this);
    }
   return null;
  }

  @Override
  public Object visitIf(@NotNull SnappyJavaParser.IfContext ctx) {
    ctx.expr().accept(this);
    String doneLabel = getLabel();
    String elseLabe = getLabel();
    /** PRINT ifeq <LABEL>> HERE*/
    try {
      jasminWriter.write("ifeq " + elseLabe + "\n");
      ctx.stmt(0).accept(this);
      jasminWriter.write("goto " + doneLabel + " \n");
      jasminWriter.write(elseLabe + ":\n");

      /** Visit the else statement*/
      ctx.stmt(1).accept(this);
      jasminWriter.write(doneLabel + ":\n");

    } catch (IOException e) {
      e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    } finally {
      return null;
    }

  }

  @Override
  public Object visitWhile(@NotNull SnappyJavaParser.WhileContext ctx) {

    try {
      /** Print the loopLabel here */
      String loopLabel = getLabel();
      String doneLabel = getLabel();
      jasminWriter.write(loopLabel + ":\n");

      /** visit the while expression*/
      ctx.expr().accept(this);
      /** print ifeq doneLabel*/
      jasminWriter.write("ifeq " + doneLabel + "\n");
      /** Visit the while statement*/
      ctx.stmt().accept(this);
      /** print Goto looplabel */
      jasminWriter.write("goto " + loopLabel + "\n");
      /** print doneLabel */
      jasminWriter.write(doneLabel + ":\n");


    } catch (IOException e) {
      e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    } finally {
      return null;
    }

  }

  @Override
  public Object visitSout(@NotNull SnappyJavaParser.SoutContext ctx) {
    try {
      TypeCheckVisitor typechecker = new TypeCheckVisitor(symbolTable);
      typechecker.currentClass = currentClass;
      typechecker.currentMethod = currentMethod;
      SnappyType exprType = ctx.expr().accept(typechecker);
      /** Get the printstream object*/
      jasminWriter.write("getstatic java/lang/System/out Ljava/io/PrintStream;\n");
      /** Put the sout statement on the stack*/
      ctx.expr().accept(this);
      /** put the tostring method on the stack*/
      if(exprType.equals(SnappyType.BOOL_TYPE)){
        jasminWriter.write("invokestatic java/lang/Boolean/toString(Z)Ljava/lang/String;\n");
      } else if(exprType.equals(SnappyType.INT_TYPE)) {
        jasminWriter.write("invokestatic java/lang/Integer/toString(I)Ljava/lang/String;\n");
      } else {
        jasminWriter.write("invokevirtual java/lang/Object/toString()Ljava/lang/String;\n");
      }

      /** invoke println method on the string on the stack*/
      jasminWriter.write("invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V\n");


    } catch (IOException e) {
      e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    } finally {
      return null;
    }


  }

  @Override
  public Object visitAssign(@NotNull SnappyJavaParser.AssignContext ctx) {

    try {
      /** put right hand side on the stack */
      ctx.expr().accept(this);
      /** get store string from left hand side depending on type */
      SnappyVariable var = getVariable(ctx.ID().getText());

    } catch (IOException e) {
      e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    } finally {
      return null;
    }
  }

  @Override
  public Object visitArrayAssign(@NotNull SnappyJavaParser.ArrayAssignContext ctx) {
    return super.visitArrayAssign(ctx);    //To change body of overridden methods use File | Settings | File Templates.
  }

  /**------------------------------------------ Expressions ---------------------------------------------------*/


  @Override
  public Object visitParenExp(@NotNull SnappyJavaParser.ParenExpContext ctx) {
    return super.visitParenExp(
        ctx);    //To change body of overridden methods use File | Settings | File Templates.
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
    return super.visitAddSubOp(
        ctx);    //To change body of overridden methods use File | Settings | File Templates.
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
    return super.visitIdExp(
        ctx);    //To change body of overridden methods use File | Settings | File Templates.
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
