package se.ludrik.snappyj;

import java.io.FileInputStream;
import java.io.IOException;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import se.ludrik.snappyj.objects.*;

/**
 * Main class
 */

/*
  TODO:
  TypeChecker:
    visitMethodDeclaration: check if returnType exists
    

 */

public class Main {
  private FileInputStream fIn;
  private ANTLRInputStream input;
  private SnappyJavaLexer lexer;
  private CommonTokenStream tokens;
  private SnappyJavaParser parser;
  private ParseTree tree;

  public Main() throws IOException { }

  public void init(String fileName) throws IOException {
    fIn = new FileInputStream(fileName);
    input = new ANTLRInputStream(fIn);
    lexer = new SnappyJavaLexer(input);
    tokens = new CommonTokenStream(lexer);
    parser = new SnappyJavaParser(tokens);
    tree = parser.program();
    run();
  }

  private void run() {
    SymbolTable symTable = new SymbolTable();
    SymbolTableVisitor symbolTableVisitor = new SymbolTableVisitor(symTable);
    symbolTableVisitor.visit(tree);

    System.out.println("------------------------------------------");

    TypeCheckVisitor typeCheckVisitor = new TypeCheckVisitor(symTable);
    typeCheckVisitor.visit(tree);
  }

}