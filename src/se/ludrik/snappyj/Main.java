package se.ludrik.snappyj;

import java.io.FileInputStream;
import java.io.IOException;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

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

  public Main() throws IOException {
    init();
  }

  public static void main(String[] args) {
    try {
      new Main();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void init() throws IOException {
    fIn = new FileInputStream("test.java");
    input = new ANTLRInputStream(fIn);
    lexer = new SnappyJavaLexer(input);
    tokens = new CommonTokenStream(lexer);
    parser = new SnappyJavaParser(tokens);
    tree = parser.program();
    SymbolTable symTable = new SymbolTable();
    SymbolTableVisitor symbolTableVisitor = new SymbolTableVisitor(symTable);
    symbolTableVisitor.visit(tree);
    //
    //for(c : symTable.classes.entrySet()) {
    //
    //}
  }

}