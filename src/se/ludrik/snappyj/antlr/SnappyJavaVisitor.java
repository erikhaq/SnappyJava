// Generated from SnappyJava.g4 by ANTLR 4.2
package se.ludrik.snappyj.antlr;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SnappyJavaParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SnappyJavaVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SnappyJavaParser#CallExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallExp(@NotNull SnappyJavaParser.CallExpContext ctx);

	/**
	 * Visit a parse tree produced by {@link SnappyJavaParser#AndComp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndComp(@NotNull SnappyJavaParser.AndCompContext ctx);

	/**
	 * Visit a parse tree produced by {@link SnappyJavaParser#MultiOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiOp(@NotNull SnappyJavaParser.MultiOpContext ctx);

	/**
	 * Visit a parse tree produced by {@link SnappyJavaParser#ThisExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThisExp(@NotNull SnappyJavaParser.ThisExpContext ctx);

	/**
	 * Visit a parse tree produced by {@link SnappyJavaParser#boolLiterals}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolLiterals(@NotNull SnappyJavaParser.BoolLiteralsContext ctx);

	/**
	 * Visit a parse tree produced by {@link SnappyJavaParser#varDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDecl(@NotNull SnappyJavaParser.VarDeclContext ctx);

	/**
	 * Visit a parse tree produced by {@link SnappyJavaParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(@NotNull SnappyJavaParser.TypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link SnappyJavaParser#classDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDecl(@NotNull SnappyJavaParser.ClassDeclContext ctx);

	/**
	 * Visit a parse tree produced by {@link SnappyJavaParser#formalList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalList(@NotNull SnappyJavaParser.FormalListContext ctx);

	/**
	 * Visit a parse tree produced by {@link SnappyJavaParser#While}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile(@NotNull SnappyJavaParser.WhileContext ctx);

	/**
	 * Visit a parse tree produced by {@link SnappyJavaParser#exprRest}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprRest(@NotNull SnappyJavaParser.ExprRestContext ctx);

	/**
	 * Visit a parse tree produced by {@link SnappyJavaParser#mainClass}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMainClass(@NotNull SnappyJavaParser.MainClassContext ctx);

	/**
	 * Visit a parse tree produced by {@link SnappyJavaParser#BoolExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolExp(@NotNull SnappyJavaParser.BoolExpContext ctx);

	/**
	 * Visit a parse tree produced by {@link SnappyJavaParser#GTComp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGTComp(@NotNull SnappyJavaParser.GTCompContext ctx);

	/**
	 * Visit a parse tree produced by {@link SnappyJavaParser#methodDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodDecl(@NotNull SnappyJavaParser.MethodDeclContext ctx);

	/**
	 * Visit a parse tree produced by {@link SnappyJavaParser#formalRest}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalRest(@NotNull SnappyJavaParser.FormalRestContext ctx);

	/**
	 * Visit a parse tree produced by {@link SnappyJavaParser#ParenExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExp(@NotNull SnappyJavaParser.ParenExpContext ctx);

	/**
	 * Visit a parse tree produced by {@link SnappyJavaParser#NumExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumExp(@NotNull SnappyJavaParser.NumExpContext ctx);

	/**
	 * Visit a parse tree produced by {@link SnappyJavaParser#exprList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprList(@NotNull SnappyJavaParser.ExprListContext ctx);

	/**
	 * Visit a parse tree produced by {@link SnappyJavaParser#LTComp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLTComp(@NotNull SnappyJavaParser.LTCompContext ctx);

	/**
	 * Visit a parse tree produced by {@link SnappyJavaParser#Body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody(@NotNull SnappyJavaParser.BodyContext ctx);

	/**
	 * Visit a parse tree produced by {@link SnappyJavaParser#op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp(@NotNull SnappyJavaParser.OpContext ctx);

	/**
	 * Visit a parse tree produced by {@link SnappyJavaParser#Assign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(@NotNull SnappyJavaParser.AssignContext ctx);

	/**
	 * Visit a parse tree produced by {@link SnappyJavaParser#NotExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExp(@NotNull SnappyJavaParser.NotExpContext ctx);

	/**
	 * Visit a parse tree produced by {@link SnappyJavaParser#AddSubOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSubOp(@NotNull SnappyJavaParser.AddSubOpContext ctx);

	/**
	 * Visit a parse tree produced by {@link SnappyJavaParser#NewIntArrayExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewIntArrayExp(@NotNull SnappyJavaParser.NewIntArrayExpContext ctx);

	/**
	 * Visit a parse tree produced by {@link SnappyJavaParser#LengthExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLengthExp(@NotNull SnappyJavaParser.LengthExpContext ctx);

	/**
	 * Visit a parse tree produced by {@link SnappyJavaParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(@NotNull SnappyJavaParser.ProgramContext ctx);

	/**
	 * Visit a parse tree produced by {@link SnappyJavaParser#ArrayAssign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayAssign(@NotNull SnappyJavaParser.ArrayAssignContext ctx);

	/**
	 * Visit a parse tree produced by {@link SnappyJavaParser#ArrayExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayExp(@NotNull SnappyJavaParser.ArrayExpContext ctx);

	/**
	 * Visit a parse tree produced by {@link SnappyJavaParser#IdExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdExp(@NotNull SnappyJavaParser.IdExpContext ctx);

	/**
	 * Visit a parse tree produced by {@link SnappyJavaParser#Sout}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSout(@NotNull SnappyJavaParser.SoutContext ctx);

	/**
	 * Visit a parse tree produced by {@link SnappyJavaParser#If}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf(@NotNull SnappyJavaParser.IfContext ctx);

	/**
	 * Visit a parse tree produced by {@link SnappyJavaParser#NewIdExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewIdExp(@NotNull SnappyJavaParser.NewIdExpContext ctx);
}