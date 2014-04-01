package se.ludrik.snappyj;

import java.io.FileInputStream;
import java.io.IOException;
import mjc.JVMMain;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import se.ludrik.snappyj.antlr.*;

/**
 * Main class
 */

public class Main {
  private FileInputStream fIn;
  private ANTLRInputStream input;
  private SnappyJavaLexer lexer;
  private CommonTokenStream tokens;
  private SnappyJavaParser parser;
  private ParseTree tree;
  private String filePath;

  public Main(String filePath) throws IOException {
    this.filePath = filePath;
  }

  public void init() throws IOException {
    fIn = new FileInputStream(filePath);
    input = new ANTLRInputStream(fIn);
    lexer = new SnappyJavaLexer(input);
    lexer.removeErrorListeners();
    lexer.addErrorListener(ErrorHandler.INSTANCE);
    tokens = new CommonTokenStream(lexer);
    parser = new SnappyJavaParser(tokens);
    parser.removeErrorListeners();
    parser.addErrorListener(ErrorHandler.INSTANCE);
    tree = parser.program();
    run();
  }

  private void run() {
    // Building symbol table
    SymbolTable symTable = buildSymboltable();

    // Typechecking
    typeCheck(symTable);

    // Stack count
    stackCount(symTable);

    // Generate JVM code
    if(JVMMain.generateJvmCode) {
      generateJVM(symTable);
    }

  }

  private SymbolTable buildSymboltable() {
    SymbolTable symTable = new SymbolTable();
    SymbolTableVisitor symbolTableVisitor = new SymbolTableVisitor(symTable);
    symbolTableVisitor.visit(tree);
    exitIfError();
    return symTable;
  }

  private void typeCheck(SymbolTable symTable) {
    TypeCheckVisitor typeCheckVisitor = new TypeCheckVisitor(symTable);
    typeCheckVisitor.visit(tree);
    exitIfError();
  }

  private void stackCount(SymbolTable symTable) {
    StackCountVisitor stackCountVisitor = new StackCountVisitor(symTable);
    stackCountVisitor.visit(tree);
  }

  private void generateJVM(SymbolTable symTable) {
    CodeGenVisitor codeGenVisitor = new CodeGenVisitor(symTable, filePath);
    codeGenVisitor.visit(tree);
    codeGenVisitor.closeWriter();
    exitIfError();

  }

  private void exitIfError() {
    if(ErrorHandler.getErrorDetected()) {
      System.exit(1);
    }
  }

}