// Generated from SnappyJava.g4 by ANTLR 4.2
package se.ludrik.snappyj.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SnappyJavaParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__3=1, T__2=2, T__1=3, T__0=4, BOOLEAN=5, STRING=6, INT=7, IF=8, ELSE=9, 
		WHILE=10, RETURN=11, CLASS=12, VOID=13, STATIC=14, PUBLIC=15, SOUT=16, 
		THIS=17, NEW=18, LENGTH=19, TRUE=20, FALSE=21, LPAREN=22, RPAREN=23, LBRACK=24, 
		RBRACK=25, LBRACE=26, RBRACE=27, ENDL=28, COMMA=29, AND=30, LT=31, LTE=32, 
		GT=33, GTE=34, ADD=35, SUB=36, MUL=37, ID=38, NUM=39, WS=40, COMMENT=41, 
		LINE_COMMENT=42;
	public static final String[] tokenNames = {
		"<INVALID>", "'.'", "'='", "'main'", "'!'", "'boolean'", "'String'", "'int'", 
		"'if'", "'else'", "'while'", "'return'", "'class'", "'void'", "'static'", 
		"'public'", "'System.out.println'", "'this'", "'new'", "'length'", "'true'", 
		"'false'", "'('", "')'", "'['", "']'", "'{'", "'}'", "';'", "','", "'&&'", 
		"'<'", "'<='", "'>'", "'>='", "'+'", "'-'", "'*'", "ID", "NUM", "WS", 
		"COMMENT", "LINE_COMMENT"
	};
	public static final int
		RULE_program = 0, RULE_mainClass = 1, RULE_classDecl = 2, RULE_varDecl = 3, 
		RULE_methodDecl = 4, RULE_formalList = 5, RULE_formalRest = 6, RULE_type = 7, 
		RULE_stmt = 8, RULE_expr = 9, RULE_op = 10, RULE_exprList = 11, RULE_exprRest = 12, 
		RULE_boolLiterals = 13;
	public static final String[] ruleNames = {
		"program", "mainClass", "classDecl", "varDecl", "methodDecl", "formalList", 
		"formalRest", "type", "stmt", "expr", "op", "exprList", "exprRest", "boolLiterals"
	};

	@Override
	public String getGrammarFileName() { return "SnappyJava.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SnappyJavaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public MainClassContext mainClass() {
			return getRuleContext(MainClassContext.class,0);
		}
		public ClassDeclContext classDecl(int i) {
			return getRuleContext(ClassDeclContext.class,i);
		}
		public List<ClassDeclContext> classDecl() {
			return getRuleContexts(ClassDeclContext.class);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SnappyJavaVisitor ) return ((SnappyJavaVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28); mainClass();
			setState(32);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CLASS) {
				{
				{
				setState(29); classDecl();
				}
				}
				setState(34);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MainClassContext extends ParserRuleContext {
		public TerminalNode RBRACE(int i) {
			return getToken(SnappyJavaParser.RBRACE, i);
		}
		public List<TerminalNode> LBRACE() { return getTokens(SnappyJavaParser.LBRACE); }
		public TerminalNode RBRACK() { return getToken(SnappyJavaParser.RBRACK, 0); }
		public List<TerminalNode> ID() { return getTokens(SnappyJavaParser.ID); }
		public TerminalNode VOID() { return getToken(SnappyJavaParser.VOID, 0); }
		public VarDeclContext varDecl(int i) {
			return getRuleContext(VarDeclContext.class,i);
		}
		public List<TerminalNode> RBRACE() { return getTokens(SnappyJavaParser.RBRACE); }
		public TerminalNode LPAREN() { return getToken(SnappyJavaParser.LPAREN, 0); }
		public TerminalNode CLASS() { return getToken(SnappyJavaParser.CLASS, 0); }
		public TerminalNode LBRACK() { return getToken(SnappyJavaParser.LBRACK, 0); }
		public TerminalNode PUBLIC() { return getToken(SnappyJavaParser.PUBLIC, 0); }
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public List<VarDeclContext> varDecl() {
			return getRuleContexts(VarDeclContext.class);
		}
		public TerminalNode RPAREN() { return getToken(SnappyJavaParser.RPAREN, 0); }
		public TerminalNode STATIC() { return getToken(SnappyJavaParser.STATIC, 0); }
		public TerminalNode LBRACE(int i) {
			return getToken(SnappyJavaParser.LBRACE, i);
		}
		public TerminalNode STRING() { return getToken(SnappyJavaParser.STRING, 0); }
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public TerminalNode ID(int i) {
			return getToken(SnappyJavaParser.ID, i);
		}
		public MainClassContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mainClass; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SnappyJavaVisitor ) return ((SnappyJavaVisitor<? extends T>)visitor).visitMainClass(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MainClassContext mainClass() throws RecognitionException {
		MainClassContext _localctx = new MainClassContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_mainClass);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(35); match(CLASS);
			setState(36); match(ID);
			setState(37); match(LBRACE);
			setState(38); match(PUBLIC);
			setState(39); match(STATIC);
			setState(40); match(VOID);
			setState(41); match(3);
			setState(42); match(LPAREN);
			setState(43); match(STRING);
			setState(44); match(LBRACK);
			setState(45); match(RBRACK);
			setState(46); match(ID);
			setState(47); match(RPAREN);
			setState(48); match(LBRACE);
			setState(52);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(49); varDecl();
					}
					} 
				}
				setState(54);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			setState(58);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << WHILE) | (1L << SOUT) | (1L << LBRACE) | (1L << ID))) != 0)) {
				{
				{
				setState(55); stmt();
				}
				}
				setState(60);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(61); match(RBRACE);
			setState(62); match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassDeclContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(SnappyJavaParser.LBRACE, 0); }
		public MethodDeclContext methodDecl(int i) {
			return getRuleContext(MethodDeclContext.class,i);
		}
		public List<VarDeclContext> varDecl() {
			return getRuleContexts(VarDeclContext.class);
		}
		public TerminalNode ID() { return getToken(SnappyJavaParser.ID, 0); }
		public VarDeclContext varDecl(int i) {
			return getRuleContext(VarDeclContext.class,i);
		}
		public TerminalNode RBRACE() { return getToken(SnappyJavaParser.RBRACE, 0); }
		public List<MethodDeclContext> methodDecl() {
			return getRuleContexts(MethodDeclContext.class);
		}
		public TerminalNode CLASS() { return getToken(SnappyJavaParser.CLASS, 0); }
		public ClassDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SnappyJavaVisitor ) return ((SnappyJavaVisitor<? extends T>)visitor).visitClassDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassDeclContext classDecl() throws RecognitionException {
		ClassDeclContext _localctx = new ClassDeclContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_classDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64); match(CLASS);
			setState(65); match(ID);
			setState(66); match(LBRACE);
			setState(70);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << INT) | (1L << ID))) != 0)) {
				{
				{
				setState(67); varDecl();
				}
				}
				setState(72);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(76);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PUBLIC) {
				{
				{
				setState(73); methodDecl();
				}
				}
				setState(78);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(79); match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDeclContext extends ParserRuleContext {
		public TerminalNode ENDL() { return getToken(SnappyJavaParser.ENDL, 0); }
		public TerminalNode ID() { return getToken(SnappyJavaParser.ID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public VarDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SnappyJavaVisitor ) return ((SnappyJavaVisitor<? extends T>)visitor).visitVarDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDeclContext varDecl() throws RecognitionException {
		VarDeclContext _localctx = new VarDeclContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_varDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81); type();
			setState(82); match(ID);
			setState(83); match(ENDL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodDeclContext extends ParserRuleContext {
		public TerminalNode PUBLIC() { return getToken(SnappyJavaParser.PUBLIC, 0); }
		public TerminalNode LBRACE() { return getToken(SnappyJavaParser.LBRACE, 0); }
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public FormalListContext formalList() {
			return getRuleContext(FormalListContext.class,0);
		}
		public TerminalNode ENDL() { return getToken(SnappyJavaParser.ENDL, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<VarDeclContext> varDecl() {
			return getRuleContexts(VarDeclContext.class);
		}
		public TerminalNode ID() { return getToken(SnappyJavaParser.ID, 0); }
		public TerminalNode RPAREN() { return getToken(SnappyJavaParser.RPAREN, 0); }
		public TerminalNode RETURN() { return getToken(SnappyJavaParser.RETURN, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public VarDeclContext varDecl(int i) {
			return getRuleContext(VarDeclContext.class,i);
		}
		public TerminalNode RBRACE() { return getToken(SnappyJavaParser.RBRACE, 0); }
		public TerminalNode LPAREN() { return getToken(SnappyJavaParser.LPAREN, 0); }
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public MethodDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SnappyJavaVisitor ) return ((SnappyJavaVisitor<? extends T>)visitor).visitMethodDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodDeclContext methodDecl() throws RecognitionException {
		MethodDeclContext _localctx = new MethodDeclContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_methodDecl);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(85); match(PUBLIC);
			setState(86); type();
			setState(87); match(ID);
			setState(88); match(LPAREN);
			setState(89); formalList();
			setState(90); match(RPAREN);
			setState(91); match(LBRACE);
			setState(95);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(92); varDecl();
					}
					} 
				}
				setState(97);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			setState(101);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << WHILE) | (1L << SOUT) | (1L << LBRACE) | (1L << ID))) != 0)) {
				{
				{
				setState(98); stmt();
				}
				}
				setState(103);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(104); match(RETURN);
			setState(105); expr(0);
			setState(106); match(ENDL);
			setState(107); match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormalListContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SnappyJavaParser.ID, 0); }
		public List<FormalRestContext> formalRest() {
			return getRuleContexts(FormalRestContext.class);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public FormalRestContext formalRest(int i) {
			return getRuleContext(FormalRestContext.class,i);
		}
		public FormalListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SnappyJavaVisitor ) return ((SnappyJavaVisitor<? extends T>)visitor).visitFormalList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormalListContext formalList() throws RecognitionException {
		FormalListContext _localctx = new FormalListContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_formalList);
		int _la;
		try {
			setState(118);
			switch (_input.LA(1)) {
			case BOOLEAN:
			case INT:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(109); type();
				setState(110); match(ID);
				setState(114);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(111); formalRest();
					}
					}
					setState(116);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case RPAREN:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormalRestContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SnappyJavaParser.ID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public FormalRestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalRest; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SnappyJavaVisitor ) return ((SnappyJavaVisitor<? extends T>)visitor).visitFormalRest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormalRestContext formalRest() throws RecognitionException {
		FormalRestContext _localctx = new FormalRestContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_formalRest);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120); match(COMMA);
			setState(121); type();
			setState(122); match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode RBRACK() { return getToken(SnappyJavaParser.RBRACK, 0); }
		public TerminalNode BOOLEAN() { return getToken(SnappyJavaParser.BOOLEAN, 0); }
		public TerminalNode INT() { return getToken(SnappyJavaParser.INT, 0); }
		public TerminalNode ID() { return getToken(SnappyJavaParser.ID, 0); }
		public TerminalNode LBRACK() { return getToken(SnappyJavaParser.LBRACK, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SnappyJavaVisitor ) return ((SnappyJavaVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_type);
		try {
			setState(130);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(124); match(INT);
				setState(125); match(LBRACK);
				setState(126); match(RBRACK);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(127); match(BOOLEAN);
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(128); match(INT);
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(129); match(ID);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmtContext extends ParserRuleContext {
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
	 
		public StmtContext() { }
		public void copyFrom(StmtContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BodyContext extends StmtContext {
		public TerminalNode LBRACE() { return getToken(SnappyJavaParser.LBRACE, 0); }
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public TerminalNode RBRACE() { return getToken(SnappyJavaParser.RBRACE, 0); }
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public BodyContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SnappyJavaVisitor ) return ((SnappyJavaVisitor<? extends T>)visitor).visitBody(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignContext extends StmtContext {
		public TerminalNode ENDL() { return getToken(SnappyJavaParser.ENDL, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode ID() { return getToken(SnappyJavaParser.ID, 0); }
		public AssignContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SnappyJavaVisitor ) return ((SnappyJavaVisitor<? extends T>)visitor).visitAssign(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WhileContext extends StmtContext {
		public TerminalNode WHILE() { return getToken(SnappyJavaParser.WHILE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(SnappyJavaParser.RPAREN, 0); }
		public TerminalNode LPAREN() { return getToken(SnappyJavaParser.LPAREN, 0); }
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public WhileContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SnappyJavaVisitor ) return ((SnappyJavaVisitor<? extends T>)visitor).visitWhile(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayAssignContext extends StmtContext {
		public TerminalNode RBRACK() { return getToken(SnappyJavaParser.RBRACK, 0); }
		public TerminalNode ENDL() { return getToken(SnappyJavaParser.ENDL, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode ID() { return getToken(SnappyJavaParser.ID, 0); }
		public TerminalNode LBRACK() { return getToken(SnappyJavaParser.LBRACK, 0); }
		public ArrayAssignContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SnappyJavaVisitor ) return ((SnappyJavaVisitor<? extends T>)visitor).visitArrayAssign(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SoutContext extends StmtContext {
		public TerminalNode ENDL() { return getToken(SnappyJavaParser.ENDL, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(SnappyJavaParser.RPAREN, 0); }
		public TerminalNode SOUT() { return getToken(SnappyJavaParser.SOUT, 0); }
		public TerminalNode LPAREN() { return getToken(SnappyJavaParser.LPAREN, 0); }
		public SoutContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SnappyJavaVisitor ) return ((SnappyJavaVisitor<? extends T>)visitor).visitSout(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfContext extends StmtContext {
		public TerminalNode IF() { return getToken(SnappyJavaParser.IF, 0); }
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(SnappyJavaParser.ELSE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(SnappyJavaParser.RPAREN, 0); }
		public TerminalNode LPAREN() { return getToken(SnappyJavaParser.LPAREN, 0); }
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public IfContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SnappyJavaVisitor ) return ((SnappyJavaVisitor<? extends T>)visitor).visitIf(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_stmt);
		int _la;
		try {
			setState(173);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				_localctx = new BodyContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(132); match(LBRACE);
				setState(136);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << WHILE) | (1L << SOUT) | (1L << LBRACE) | (1L << ID))) != 0)) {
					{
					{
					setState(133); stmt();
					}
					}
					setState(138);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(139); match(RBRACE);
				}
				break;

			case 2:
				_localctx = new IfContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(140); match(IF);
				setState(141); match(LPAREN);
				setState(142); expr(0);
				setState(143); match(RPAREN);
				setState(144); stmt();
				setState(145); match(ELSE);
				setState(146); stmt();
				}
				break;

			case 3:
				_localctx = new WhileContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(148); match(WHILE);
				setState(149); match(LPAREN);
				setState(150); expr(0);
				setState(151); match(RPAREN);
				setState(152); stmt();
				}
				break;

			case 4:
				_localctx = new SoutContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(154); match(SOUT);
				setState(155); match(LPAREN);
				setState(156); expr(0);
				setState(157); match(RPAREN);
				setState(158); match(ENDL);
				}
				break;

			case 5:
				_localctx = new AssignContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(160); match(ID);
				setState(161); match(2);
				setState(162); expr(0);
				setState(163); match(ENDL);
				}
				break;

			case 6:
				_localctx = new ArrayAssignContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(165); match(ID);
				setState(166); match(LBRACK);
				setState(167); expr(0);
				setState(168); match(RBRACK);
				setState(169); match(2);
				setState(170); expr(0);
				setState(171); match(ENDL);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class CallExpContext extends ExprContext {
		public ExprListContext exprList() {
			return getRuleContext(ExprListContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode ID() { return getToken(SnappyJavaParser.ID, 0); }
		public TerminalNode RPAREN() { return getToken(SnappyJavaParser.RPAREN, 0); }
		public TerminalNode LPAREN() { return getToken(SnappyJavaParser.LPAREN, 0); }
		public CallExpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SnappyJavaVisitor ) return ((SnappyJavaVisitor<? extends T>)visitor).visitCallExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LTCompContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public TerminalNode LT() { return getToken(SnappyJavaParser.LT, 0); }
		public TerminalNode LTE() { return getToken(SnappyJavaParser.LTE, 0); }
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public LTCompContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SnappyJavaVisitor ) return ((SnappyJavaVisitor<? extends T>)visitor).visitLTComp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AndCompContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public TerminalNode AND() { return getToken(SnappyJavaParser.AND, 0); }
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public AndCompContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SnappyJavaVisitor ) return ((SnappyJavaVisitor<? extends T>)visitor).visitAndComp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MultiOpContext extends ExprContext {
		public TerminalNode MUL() { return getToken(SnappyJavaParser.MUL, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public MultiOpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SnappyJavaVisitor ) return ((SnappyJavaVisitor<? extends T>)visitor).visitMultiOp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ThisExpContext extends ExprContext {
		public TerminalNode THIS() { return getToken(SnappyJavaParser.THIS, 0); }
		public ThisExpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SnappyJavaVisitor ) return ((SnappyJavaVisitor<? extends T>)visitor).visitThisExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NotExpContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NotExpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SnappyJavaVisitor ) return ((SnappyJavaVisitor<? extends T>)visitor).visitNotExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AddSubOpContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode SUB() { return getToken(SnappyJavaParser.SUB, 0); }
		public TerminalNode ADD() { return getToken(SnappyJavaParser.ADD, 0); }
		public AddSubOpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SnappyJavaVisitor ) return ((SnappyJavaVisitor<? extends T>)visitor).visitAddSubOp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NewIntArrayExpContext extends ExprContext {
		public TerminalNode RBRACK() { return getToken(SnappyJavaParser.RBRACK, 0); }
		public TerminalNode NEW() { return getToken(SnappyJavaParser.NEW, 0); }
		public TerminalNode INT() { return getToken(SnappyJavaParser.INT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode LBRACK() { return getToken(SnappyJavaParser.LBRACK, 0); }
		public NewIntArrayExpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SnappyJavaVisitor ) return ((SnappyJavaVisitor<? extends T>)visitor).visitNewIntArrayExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LengthExpContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode LENGTH() { return getToken(SnappyJavaParser.LENGTH, 0); }
		public LengthExpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SnappyJavaVisitor ) return ((SnappyJavaVisitor<? extends T>)visitor).visitLengthExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BoolExpContext extends ExprContext {
		public BoolLiteralsContext boolLiterals() {
			return getRuleContext(BoolLiteralsContext.class,0);
		}
		public BoolExpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SnappyJavaVisitor ) return ((SnappyJavaVisitor<? extends T>)visitor).visitBoolExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class GTCompContext extends ExprContext {
		public TerminalNode GTE() { return getToken(SnappyJavaParser.GTE, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode GT() { return getToken(SnappyJavaParser.GT, 0); }
		public GTCompContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SnappyJavaVisitor ) return ((SnappyJavaVisitor<? extends T>)visitor).visitGTComp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IdExpContext extends ExprContext {
		public TerminalNode ID() { return getToken(SnappyJavaParser.ID, 0); }
		public IdExpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SnappyJavaVisitor ) return ((SnappyJavaVisitor<? extends T>)visitor).visitIdExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayExpContext extends ExprContext {
		public TerminalNode RBRACK() { return getToken(SnappyJavaParser.RBRACK, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode LBRACK() { return getToken(SnappyJavaParser.LBRACK, 0); }
		public ArrayExpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SnappyJavaVisitor ) return ((SnappyJavaVisitor<? extends T>)visitor).visitArrayExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenExpContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(SnappyJavaParser.RPAREN, 0); }
		public TerminalNode LPAREN() { return getToken(SnappyJavaParser.LPAREN, 0); }
		public ParenExpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SnappyJavaVisitor ) return ((SnappyJavaVisitor<? extends T>)visitor).visitParenExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NumExpContext extends ExprContext {
		public TerminalNode NUM() { return getToken(SnappyJavaParser.NUM, 0); }
		public NumExpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SnappyJavaVisitor ) return ((SnappyJavaVisitor<? extends T>)visitor).visitNumExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NewIdExpContext extends ExprContext {
		public TerminalNode NEW() { return getToken(SnappyJavaParser.NEW, 0); }
		public TerminalNode ID() { return getToken(SnappyJavaParser.ID, 0); }
		public TerminalNode RPAREN() { return getToken(SnappyJavaParser.RPAREN, 0); }
		public TerminalNode LPAREN() { return getToken(SnappyJavaParser.LPAREN, 0); }
		public NewIdExpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SnappyJavaVisitor ) return ((SnappyJavaVisitor<? extends T>)visitor).visitNewIdExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(196);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				_localctx = new NotExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(176); match(4);
				setState(177); expr(12);
				}
				break;

			case 2:
				{
				_localctx = new ParenExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(178); match(LPAREN);
				setState(179); expr(0);
				setState(180); match(RPAREN);
				}
				break;

			case 3:
				{
				_localctx = new NewIntArrayExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(182); match(NEW);
				setState(183); match(INT);
				setState(184); match(LBRACK);
				setState(185); expr(0);
				setState(186); match(RBRACK);
				}
				break;

			case 4:
				{
				_localctx = new NewIdExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(188); match(NEW);
				setState(189); match(ID);
				setState(190); match(LPAREN);
				setState(191); match(RPAREN);
				}
				break;

			case 5:
				{
				_localctx = new NumExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(192); match(NUM);
				}
				break;

			case 6:
				{
				_localctx = new BoolExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(193); boolLiterals();
				}
				break;

			case 7:
				{
				_localctx = new IdExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(194); match(ID);
				}
				break;

			case 8:
				{
				_localctx = new ThisExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(195); match(THIS);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(230);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(228);
					switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
					case 1:
						{
						_localctx = new MultiOpContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(198);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(199); match(MUL);
						setState(200); expr(10);
						}
						break;

					case 2:
						{
						_localctx = new AddSubOpContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(201);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(202);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==SUB) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(203); expr(9);
						}
						break;

					case 3:
						{
						_localctx = new LTCompContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(204);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(205);
						_la = _input.LA(1);
						if ( !(_la==LT || _la==LTE) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(206); expr(8);
						}
						break;

					case 4:
						{
						_localctx = new GTCompContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(207);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(208);
						_la = _input.LA(1);
						if ( !(_la==GT || _la==GTE) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(209); expr(7);
						}
						break;

					case 5:
						{
						_localctx = new AndCompContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(210);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(211); match(AND);
						setState(212); expr(6);
						}
						break;

					case 6:
						{
						_localctx = new ArrayExpContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(213);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(214); match(LBRACK);
						setState(215); expr(0);
						setState(216); match(RBRACK);
						}
						break;

					case 7:
						{
						_localctx = new CallExpContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(218);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(219); match(1);
						setState(220); match(ID);
						setState(221); match(LPAREN);
						setState(222); exprList();
						setState(223); match(RPAREN);
						}
						break;

					case 8:
						{
						_localctx = new LengthExpContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(225);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(226); match(1);
						setState(227); match(LENGTH);
						}
						break;
					}
					} 
				}
				setState(232);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class OpContext extends ParserRuleContext {
		public OpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SnappyJavaVisitor ) return ((SnappyJavaVisitor<? extends T>)visitor).visitOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OpContext op() throws RecognitionException {
		OpContext _localctx = new OpContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_op);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AND) | (1L << LT) | (1L << LTE) | (1L << GT) | (1L << GTE) | (1L << ADD) | (1L << SUB) | (1L << MUL))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprListContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<ExprRestContext> exprRest() {
			return getRuleContexts(ExprRestContext.class);
		}
		public ExprRestContext exprRest(int i) {
			return getRuleContext(ExprRestContext.class,i);
		}
		public ExprListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprList; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SnappyJavaVisitor ) return ((SnappyJavaVisitor<? extends T>)visitor).visitExprList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprListContext exprList() throws RecognitionException {
		ExprListContext _localctx = new ExprListContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_exprList);
		int _la;
		try {
			setState(243);
			switch (_input.LA(1)) {
			case 4:
			case THIS:
			case NEW:
			case TRUE:
			case FALSE:
			case LPAREN:
			case ID:
			case NUM:
				enterOuterAlt(_localctx, 1);
				{
				setState(235); expr(0);
				setState(239);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(236); exprRest();
					}
					}
					setState(241);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case RPAREN:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprRestContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(SnappyJavaParser.COMMA, 0); }
		public ExprRestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprRest; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SnappyJavaVisitor ) return ((SnappyJavaVisitor<? extends T>)visitor).visitExprRest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprRestContext exprRest() throws RecognitionException {
		ExprRestContext _localctx = new ExprRestContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_exprRest);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245); match(COMMA);
			setState(246); expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoolLiteralsContext extends ParserRuleContext {
		public TerminalNode TRUE() { return getToken(SnappyJavaParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(SnappyJavaParser.FALSE, 0); }
		public BoolLiteralsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolLiterals; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SnappyJavaVisitor ) return ((SnappyJavaVisitor<? extends T>)visitor).visitBoolLiterals(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolLiteralsContext boolLiterals() throws RecognitionException {
		BoolLiteralsContext _localctx = new BoolLiteralsContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_boolLiterals);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(248);
			_la = _input.LA(1);
			if ( !(_la==TRUE || _la==FALSE) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 9: return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return precpred(_ctx, 9);

		case 1: return precpred(_ctx, 8);

		case 2: return precpred(_ctx, 7);

		case 3: return precpred(_ctx, 6);

		case 4: return precpred(_ctx, 5);

		case 5: return precpred(_ctx, 15);

		case 6: return precpred(_ctx, 14);

		case 7: return precpred(_ctx, 13);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3,\u00fd\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\7\2!\n\2\f\2\16\2$\13"+
		"\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3\65"+
		"\n\3\f\3\16\38\13\3\3\3\7\3;\n\3\f\3\16\3>\13\3\3\3\3\3\3\3\3\4\3\4\3"+
		"\4\3\4\7\4G\n\4\f\4\16\4J\13\4\3\4\7\4M\n\4\f\4\16\4P\13\4\3\4\3\4\3\5"+
		"\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6`\n\6\f\6\16\6c\13\6\3"+
		"\6\7\6f\n\6\f\6\16\6i\13\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\7\7s\n\7\f"+
		"\7\16\7v\13\7\3\7\5\7y\n\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\5\t"+
		"\u0085\n\t\3\n\3\n\7\n\u0089\n\n\f\n\16\n\u008c\13\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u00b0\n\n\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\5\13\u00c7\n\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u00e7\n\13"+
		"\f\13\16\13\u00ea\13\13\3\f\3\f\3\r\3\r\7\r\u00f0\n\r\f\r\16\r\u00f3\13"+
		"\r\3\r\5\r\u00f6\n\r\3\16\3\16\3\16\3\17\3\17\3\17\2\3\24\20\2\4\6\b\n"+
		"\f\16\20\22\24\26\30\32\34\2\7\3\2%&\3\2!\"\3\2#$\3\2 \'\3\2\26\27\u0111"+
		"\2\36\3\2\2\2\4%\3\2\2\2\6B\3\2\2\2\bS\3\2\2\2\nW\3\2\2\2\fx\3\2\2\2\16"+
		"z\3\2\2\2\20\u0084\3\2\2\2\22\u00af\3\2\2\2\24\u00c6\3\2\2\2\26\u00eb"+
		"\3\2\2\2\30\u00f5\3\2\2\2\32\u00f7\3\2\2\2\34\u00fa\3\2\2\2\36\"\5\4\3"+
		"\2\37!\5\6\4\2 \37\3\2\2\2!$\3\2\2\2\" \3\2\2\2\"#\3\2\2\2#\3\3\2\2\2"+
		"$\"\3\2\2\2%&\7\16\2\2&\'\7(\2\2\'(\7\34\2\2()\7\21\2\2)*\7\20\2\2*+\7"+
		"\17\2\2+,\7\5\2\2,-\7\30\2\2-.\7\b\2\2./\7\32\2\2/\60\7\33\2\2\60\61\7"+
		"(\2\2\61\62\7\31\2\2\62\66\7\34\2\2\63\65\5\b\5\2\64\63\3\2\2\2\658\3"+
		"\2\2\2\66\64\3\2\2\2\66\67\3\2\2\2\67<\3\2\2\28\66\3\2\2\29;\5\22\n\2"+
		":9\3\2\2\2;>\3\2\2\2<:\3\2\2\2<=\3\2\2\2=?\3\2\2\2><\3\2\2\2?@\7\35\2"+
		"\2@A\7\35\2\2A\5\3\2\2\2BC\7\16\2\2CD\7(\2\2DH\7\34\2\2EG\5\b\5\2FE\3"+
		"\2\2\2GJ\3\2\2\2HF\3\2\2\2HI\3\2\2\2IN\3\2\2\2JH\3\2\2\2KM\5\n\6\2LK\3"+
		"\2\2\2MP\3\2\2\2NL\3\2\2\2NO\3\2\2\2OQ\3\2\2\2PN\3\2\2\2QR\7\35\2\2R\7"+
		"\3\2\2\2ST\5\20\t\2TU\7(\2\2UV\7\36\2\2V\t\3\2\2\2WX\7\21\2\2XY\5\20\t"+
		"\2YZ\7(\2\2Z[\7\30\2\2[\\\5\f\7\2\\]\7\31\2\2]a\7\34\2\2^`\5\b\5\2_^\3"+
		"\2\2\2`c\3\2\2\2a_\3\2\2\2ab\3\2\2\2bg\3\2\2\2ca\3\2\2\2df\5\22\n\2ed"+
		"\3\2\2\2fi\3\2\2\2ge\3\2\2\2gh\3\2\2\2hj\3\2\2\2ig\3\2\2\2jk\7\r\2\2k"+
		"l\5\24\13\2lm\7\36\2\2mn\7\35\2\2n\13\3\2\2\2op\5\20\t\2pt\7(\2\2qs\5"+
		"\16\b\2rq\3\2\2\2sv\3\2\2\2tr\3\2\2\2tu\3\2\2\2uy\3\2\2\2vt\3\2\2\2wy"+
		"\3\2\2\2xo\3\2\2\2xw\3\2\2\2y\r\3\2\2\2z{\7\37\2\2{|\5\20\t\2|}\7(\2\2"+
		"}\17\3\2\2\2~\177\7\t\2\2\177\u0080\7\32\2\2\u0080\u0085\7\33\2\2\u0081"+
		"\u0085\7\7\2\2\u0082\u0085\7\t\2\2\u0083\u0085\7(\2\2\u0084~\3\2\2\2\u0084"+
		"\u0081\3\2\2\2\u0084\u0082\3\2\2\2\u0084\u0083\3\2\2\2\u0085\21\3\2\2"+
		"\2\u0086\u008a\7\34\2\2\u0087\u0089\5\22\n\2\u0088\u0087\3\2\2\2\u0089"+
		"\u008c\3\2\2\2\u008a\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u008d\3\2"+
		"\2\2\u008c\u008a\3\2\2\2\u008d\u00b0\7\35\2\2\u008e\u008f\7\n\2\2\u008f"+
		"\u0090\7\30\2\2\u0090\u0091\5\24\13\2\u0091\u0092\7\31\2\2\u0092\u0093"+
		"\5\22\n\2\u0093\u0094\7\13\2\2\u0094\u0095\5\22\n\2\u0095\u00b0\3\2\2"+
		"\2\u0096\u0097\7\f\2\2\u0097\u0098\7\30\2\2\u0098\u0099\5\24\13\2\u0099"+
		"\u009a\7\31\2\2\u009a\u009b\5\22\n\2\u009b\u00b0\3\2\2\2\u009c\u009d\7"+
		"\22\2\2\u009d\u009e\7\30\2\2\u009e\u009f\5\24\13\2\u009f\u00a0\7\31\2"+
		"\2\u00a0\u00a1\7\36\2\2\u00a1\u00b0\3\2\2\2\u00a2\u00a3\7(\2\2\u00a3\u00a4"+
		"\7\4\2\2\u00a4\u00a5\5\24\13\2\u00a5\u00a6\7\36\2\2\u00a6\u00b0\3\2\2"+
		"\2\u00a7\u00a8\7(\2\2\u00a8\u00a9\7\32\2\2\u00a9\u00aa\5\24\13\2\u00aa"+
		"\u00ab\7\33\2\2\u00ab\u00ac\7\4\2\2\u00ac\u00ad\5\24\13\2\u00ad\u00ae"+
		"\7\36\2\2\u00ae\u00b0\3\2\2\2\u00af\u0086\3\2\2\2\u00af\u008e\3\2\2\2"+
		"\u00af\u0096\3\2\2\2\u00af\u009c\3\2\2\2\u00af\u00a2\3\2\2\2\u00af\u00a7"+
		"\3\2\2\2\u00b0\23\3\2\2\2\u00b1\u00b2\b\13\1\2\u00b2\u00b3\7\6\2\2\u00b3"+
		"\u00c7\5\24\13\16\u00b4\u00b5\7\30\2\2\u00b5\u00b6\5\24\13\2\u00b6\u00b7"+
		"\7\31\2\2\u00b7\u00c7\3\2\2\2\u00b8\u00b9\7\24\2\2\u00b9\u00ba\7\t\2\2"+
		"\u00ba\u00bb\7\32\2\2\u00bb\u00bc\5\24\13\2\u00bc\u00bd\7\33\2\2\u00bd"+
		"\u00c7\3\2\2\2\u00be\u00bf\7\24\2\2\u00bf\u00c0\7(\2\2\u00c0\u00c1\7\30"+
		"\2\2\u00c1\u00c7\7\31\2\2\u00c2\u00c7\7)\2\2\u00c3\u00c7\5\34\17\2\u00c4"+
		"\u00c7\7(\2\2\u00c5\u00c7\7\23\2\2\u00c6\u00b1\3\2\2\2\u00c6\u00b4\3\2"+
		"\2\2\u00c6\u00b8\3\2\2\2\u00c6\u00be\3\2\2\2\u00c6\u00c2\3\2\2\2\u00c6"+
		"\u00c3\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c6\u00c5\3\2\2\2\u00c7\u00e8\3\2"+
		"\2\2\u00c8\u00c9\f\13\2\2\u00c9\u00ca\7\'\2\2\u00ca\u00e7\5\24\13\f\u00cb"+
		"\u00cc\f\n\2\2\u00cc\u00cd\t\2\2\2\u00cd\u00e7\5\24\13\13\u00ce\u00cf"+
		"\f\t\2\2\u00cf\u00d0\t\3\2\2\u00d0\u00e7\5\24\13\n\u00d1\u00d2\f\b\2\2"+
		"\u00d2\u00d3\t\4\2\2\u00d3\u00e7\5\24\13\t\u00d4\u00d5\f\7\2\2\u00d5\u00d6"+
		"\7 \2\2\u00d6\u00e7\5\24\13\b\u00d7\u00d8\f\21\2\2\u00d8\u00d9\7\32\2"+
		"\2\u00d9\u00da\5\24\13\2\u00da\u00db\7\33\2\2\u00db\u00e7\3\2\2\2\u00dc"+
		"\u00dd\f\20\2\2\u00dd\u00de\7\3\2\2\u00de\u00df\7(\2\2\u00df\u00e0\7\30"+
		"\2\2\u00e0\u00e1\5\30\r\2\u00e1\u00e2\7\31\2\2\u00e2\u00e7\3\2\2\2\u00e3"+
		"\u00e4\f\17\2\2\u00e4\u00e5\7\3\2\2\u00e5\u00e7\7\25\2\2\u00e6\u00c8\3"+
		"\2\2\2\u00e6\u00cb\3\2\2\2\u00e6\u00ce\3\2\2\2\u00e6\u00d1\3\2\2\2\u00e6"+
		"\u00d4\3\2\2\2\u00e6\u00d7\3\2\2\2\u00e6\u00dc\3\2\2\2\u00e6\u00e3\3\2"+
		"\2\2\u00e7\u00ea\3\2\2\2\u00e8\u00e6\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9"+
		"\25\3\2\2\2\u00ea\u00e8\3\2\2\2\u00eb\u00ec\t\5\2\2\u00ec\27\3\2\2\2\u00ed"+
		"\u00f1\5\24\13\2\u00ee\u00f0\5\32\16\2\u00ef\u00ee\3\2\2\2\u00f0\u00f3"+
		"\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2\u00f6\3\2\2\2\u00f3"+
		"\u00f1\3\2\2\2\u00f4\u00f6\3\2\2\2\u00f5\u00ed\3\2\2\2\u00f5\u00f4\3\2"+
		"\2\2\u00f6\31\3\2\2\2\u00f7\u00f8\7\37\2\2\u00f8\u00f9\5\24\13\2\u00f9"+
		"\33\3\2\2\2\u00fa\u00fb\t\6\2\2\u00fb\35\3\2\2\2\23\"\66<HNagtx\u0084"+
		"\u008a\u00af\u00c6\u00e6\u00e8\u00f1\u00f5";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}