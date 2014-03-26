package se.ludrik.snappyj;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * Created with IntelliJ IDEA.
 * User: Megatron
 * Date: 2014-03-26
 * Time: 14:41
 * To change this template use File | Settings | File Templates.
 */
public class CodeGenVisitor extends SnappyJavaBaseVisitor{
  BufferedWriter jasminWriter;
  SymbolTable symbolTable;
  CodeGenVisitor(SymbolTable symbolTable, String filename ) {
    this.symbolTable = symbolTable;
    File f = new File(filename);
    if(!f.exists()) {

    }
    //jasminWriter = new BufferedWriter(new FileWriter(new File(filename)));
  }
}
