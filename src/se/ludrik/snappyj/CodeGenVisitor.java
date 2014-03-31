package se.ludrik.snappyj;

import java.io.File;
import java.io.IOException;
import org.antlr.v4.runtime.misc.NotNull;
import se.ludrik.snappyj.antlr.*;
import se.ludrik.snappyj.objects.*;
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
  public String filePath;
  public CodeGenVisitor(SymbolTable symbolTable, String filePath) {
    this.symbolTable = symbolTable;
    this.filePath = filePath;

  }

  public void pushVariableToStack(String varName) {
    SnappyVariable var = getVariable(varName);

    try {
      if (var.isField) {
        /** load field variable onto stack */
        jasminWriter.write("\taload_0\n");
        jasminWriter.write(JasminUtils.getGetfieldString(var, currentClass.id));
      } else {
        if(var.type.equals(SnappyType.INT_TYPE) || var.type.equals(SnappyType.BOOL_TYPE)) {
          jasminWriter.write("\tiload " + var.variableNumber + "\n");
        } else {
          jasminWriter.write("\taload " + var.variableNumber + "\n");
        }
      }
    } catch (IOException e) {
      e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    }

  }
  public void newClass(String className) {
    try {
      if(jasminWriter != null) {
        jasminWriter.close();
      }

      StringBuilder sb = new StringBuilder();
      sb.append("./jasmin/");


      File f = new File(filePath);
      String fileName = f.getName();
      System.out.println(fileName);
      String[] s = fileName.split("\\.");
      sb.append(s[0]);
      f = new File(sb.toString());
      f.mkdirs();
      sb.append("/");
      sb.append(className);
      sb.append(".s");
      //jasminWriter = new BufferedWriter(new FileWriter("./jasmin/" + className + ".s"));
      jasminWriter = new BufferedWriter(new FileWriter(sb.toString()));
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

    //Declare main method
    try {
      jasminWriter.write(".method public static main([Ljava/lang/String;)V\n");
      jasminWriter.write(JasminUtils.getMethodLimits(32, currentMethod.LOCAL_NUM));

      for (SnappyJavaParser.VarDeclContext v : ctx.varDecl()) {
        v.accept(this);
      }
      for(SnappyJavaParser.StmtContext stmt : ctx.stmt()) {
        stmt.accept(this);
      }

      jasminWriter.write("\treturn\n.end method\n");
      currentMethod = null;
      currentClass = null;

    } catch (IOException e) {
      e.printStackTrace();
    }


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
      jasminWriter.write(JasminUtils.getClosingMethodDeclaration(currentMethod.returnType));
      jasminWriter.write(JasminUtils.getMethodLimits(32, currentMethod.LOCAL_NUM)); //TODO: real stack size calc

      for(SnappyJavaParser.StmtContext stmt : ctx.stmt()) {
        stmt.accept(this);
      }
      /**  Write the return statement */
      ctx.expr().accept(this);
      jasminWriter.write(JasminUtils.getReturnString(currentMethod.returnType));
      jasminWriter.write(".end method\n");

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
    System.out.println("If expr: " + ctx.expr().getText());
    String doneLabel = JasminUtils.getLabel();
    String elseLabe = JasminUtils.getLabel();
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
      String loopLabel = JasminUtils.getLabel();
      String doneLabel = JasminUtils.getLabel();
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
      jasminWriter.write("\tgetstatic java/lang/System/out Ljava/io/PrintStream;\n");
      /** Put the sout statement on the stack*/
      ctx.expr().accept(this);
      /** put the tostring method on the stack*/
      if(exprType.equals(SnappyType.BOOL_TYPE)){
        jasminWriter.write("\tinvokestatic java/lang/Boolean/toString(Z)Ljava/lang/String;\n");
      } else if(exprType.equals(SnappyType.INT_TYPE)) {
        jasminWriter.write("\tinvokestatic java/lang/Integer/toString(I)Ljava/lang/String;\n");
      } else {
        jasminWriter.write("\tinvokevirtual java/lang/Object/toString()Ljava/lang/String;\n");
      }

      /** invoke println method on the string on the stack*/
      jasminWriter.write("\tinvokevirtual java/io/PrintStream/println(Ljava/lang/String;)V\n");


    } catch (IOException e) {
      e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    } finally {
      return null;
    }


  }

  @Override
  public Object visitAssign(@NotNull SnappyJavaParser.AssignContext ctx) {


    /** get the left hand side variable*/
    SnappyVariable var = getVariable(ctx.ID().getText());
    try {
      if(var.isField) {
        /** load 'this' onto the stack*/
        jasminWriter.write("\taload 0\n");
      }
      /** put right hand side on the stack */
      ctx.expr().accept(this);
      /** get store string from left hand side depending on type */

      jasminWriter.write(JasminUtils.getStoreString(var, currentClass.id));
    } catch (IOException e) {
      e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    } finally {
      return null;
    }

  }

  @Override
  public Object visitArrayAssign(@NotNull SnappyJavaParser.ArrayAssignContext ctx) {
    /** write load instruction for array*/

    SnappyVariable var = getVariable(ctx.ID().getText());
    try {
      /** push the left hand side variable onto the stack */
      pushVariableToStack(ctx.ID().getText());
      /** load index onto stack */
      ctx.expr(0).accept(this);
      /** load right hand side onto stack */
      ctx.expr(1).accept(this);
      /** store into loaded array */
      jasminWriter.write("\tiastore \n");
    } catch (IOException e) {
      e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    }finally {
      return null;
    }
  }

  /**------------------------------------------ Expressions ---------------------------------------------------*/


  @Override
  public Object visitParenExp(@NotNull SnappyJavaParser.ParenExpContext ctx) {
    ctx.expr().accept(this);
    return null;
  }

  @Override
  public Object visitArrayExp(@NotNull SnappyJavaParser.ArrayExpContext ctx) {
    SnappyVariable var = getVariable(ctx.expr(0).getText());
    System.out.println("visit array access: " + ctx.getText());
    try {
      /** put array reference on stack */
      ctx.expr(0).accept(this);
      /*jasminWriter.write("\taload " + var.variableNumber + "\n");
      if(var.isField) {
        /** put field reference on stack
        jasminWriter.write(JasminUtils.getGetfieldString(var, currentClass.id));
      }*/
      /** put index value on stack */
      ctx.expr(1).accept(this);
      /** load value from array */
      jasminWriter.write("\tiaload\n");
    } catch (IOException e) {
      e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    } finally {
      return null;
    }
  }

  @Override
  public Object visitCallExp(@NotNull SnappyJavaParser.CallExpContext ctx) {
    TypeCheckVisitor typeCheckVisitor = new TypeCheckVisitor(symbolTable);
    typeCheckVisitor.currentClass = currentClass;
    typeCheckVisitor.currentMethod = currentMethod;
    System.out.println("visit method call: " + ctx.getText());
    SnappyType leftType = ctx.expr().accept(typeCheckVisitor);
    try {
      /** push left hand expression on the stack(the caller object) */
      ctx.expr().accept(this);
      /** push method arguments on the stack */
      ctx.exprList().accept(this);

      /** get the method that this epression wants to call */
      SnappyMethod m = symbolTable.classes.get(leftType.type).methods.get(ctx.ID().getText());
      jasminWriter.write(JasminUtils.getInvokevirtualString(m, leftType.type));
      System.out.println("writing method call: " + JasminUtils.getInvokevirtualString(m, leftType.type));

    } catch (IOException e) {
      e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    } finally {
      return null;
    }
  }

  @Override
  public Object visitLengthExp(@NotNull SnappyJavaParser.LengthExpContext ctx) {


    try {
      /** put the array reference on the stack */
      ctx.expr().accept(this);
      jasminWriter.write("\tarraylength\n");
    } catch (IOException e) {
      e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    } finally {
      return null;
    }

  }

  @Override
  public Object visitNotExp(@NotNull SnappyJavaParser.NotExpContext ctx) {
    try {
      jasminWriter.write("\ticonst_1\n"); // write 1 on the stack
      ctx.expr().accept(this);            // write boolean 1 or 0 on stack
      jasminWriter.write("\tisub\n");     // subtract 0 or 1 from 1 and push on stack


    } catch (IOException e) {
      e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    } finally {
      return null;
    }

  }

  @Override
  public Object visitNewIntArrayExp(@NotNull SnappyJavaParser.NewIntArrayExpContext ctx) {
    try {
      ctx.expr().accept(this);
      jasminWriter.write("\tnewarray int\n");
    } catch (IOException e) {
      e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    } finally {
      return null;
    }
  }

  @Override
  public Object visitNewIdExp(@NotNull SnappyJavaParser.NewIdExpContext ctx) {
    try {
      String className = ctx.ID().getText();
      jasminWriter.write("\tnew " + className + "\n"); // create a new object reference
      jasminWriter.write("\tdup\n");                            // duplicate so we can invoke the constructor
      jasminWriter.write("\tinvokespecial " + className + "/<init>()V\n");
    } catch (IOException e) {
      e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    } finally {
      return null;
    }
  }

  @Override
  public Object visitMultiOp(@NotNull SnappyJavaParser.MultiOpContext ctx) {
    try {
      visitChildren(ctx);
      jasminWriter.write("\timul\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
  @Override
  public Object visitAddSubOp(@NotNull SnappyJavaParser.AddSubOpContext ctx) {
    try {
      visitChildren(ctx);
      String operator = ctx.getChild(1).getText(); //maybe not best way to check +/-?
      jasminWriter.write(JasminUtils.getAddSubString(operator));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public void visitComp(String comparator) {
    try {
      jasminWriter.write(JasminUtils.getComparatorString(comparator));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Object visitLTComp(@NotNull SnappyJavaParser.LTCompContext ctx) {
    System.out.println("visit Less than: " + ctx.getText());
    visitChildren(ctx);
    visitComp(ctx.getChild(1).getText());
    return null;
  }

  @Override
  public Object visitGTComp(@NotNull SnappyJavaParser.GTCompContext ctx) {
    visitChildren(ctx);
    visitComp(ctx.getChild(1).getText());
    return null;
  }

  @Override
  public Object visitAndComp(@NotNull SnappyJavaParser.AndCompContext ctx) {
    System.out.println("visit AND: " + ctx.getText());
    try {
      visitChildren(ctx);
      jasminWriter.write("\tiand\n");
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      return null;
    }
  }

  @Override
  public Object visitNumExp(@NotNull SnappyJavaParser.NumExpContext ctx) {
    String intLit = ctx.NUM().getText();
    try {
      jasminWriter.write(JasminUtils.getLoadIntString(intLit));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public Object visitBoolExp(@NotNull SnappyJavaParser.BoolExpContext ctx) {

    ctx.boolLiterals().accept(this);
    return null;
  }

  @Override
  public Object visitIdExp(@NotNull SnappyJavaParser.IdExpContext ctx) {

      pushVariableToStack(ctx.ID().getText());
      return null;
  }

  @Override
  public Object visitThisExp(@NotNull SnappyJavaParser.ThisExpContext ctx) {
    try {
      jasminWriter.write("\taload_0\n");
    } catch (IOException e) {
      e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    } finally {
      return null;
    }


  }
 /** --------------------------------------------------------- boolliterals-----------------------------------------------*/

  @Override
  public Object visitBoolLiterals(@NotNull SnappyJavaParser.BoolLiteralsContext ctx) {
    try {
      if(ctx.TRUE() != null) {
        jasminWriter.write("\ticonst_1\n");
      } else {
        jasminWriter.write("\ticonst_0\n");
      }
    } catch (IOException e) {
      e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    } finally {
      return null;
    }

  }
}
