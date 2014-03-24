grammar SnappyJava;

program : mainClass (classDecl)* ;

mainClass 	: CLASS ID LBRACE PUBLIC STATIC VOID 'main' LPAREN STRING LBRACK RBRACK ID RPAREN LBRACE (varDecl)* (stmt)* RBRACE RBRACE;
classDecl 	: CLASS ID LBRACE (varDecl)* (methodDecl)* RBRACE	; 
varDecl		: type ID ENDL;
methodDecl 	: PUBLIC type ID LPAREN formalList RPAREN LBRACE (varDecl)* (stmt)* RETURN expr ENDL RBRACE	;

formalList : type ID (formalRest)* 
	|;

formalRest : ',' type ID ;

type: INT LBRACK RBRACK
	| BOOLEAN
	| INT
	| ID
	;

stmt: LBRACE (stmt)* RBRACE                 # Body
	| IF LPAREN expr RPAREN stmt ELSE stmt  # If
	| WHILE LPAREN expr RPAREN stmt         # While
	| SOUT LPAREN expr RPAREN ENDL          # Sout
	| ID '=' expr ENDL                      # Assign
	| ID LBRACK expr RBRACK '=' expr ENDL   # ArrayAssign
	;	

expr: expr op expr 							# BinaryOp
	| expr LBRACK expr RBRACK				# ArrayExp
	| expr '.' LENGTH 						# LengthExp
	| expr '.' ID LPAREN exprList RPAREN	# CallExp
	| NUM 									# NumExp
	| boolLiterals 							# BoolExp
	| ID									# IdExp
	| THIS 									# ThisExp
	| NEW INT LBRACK expr RBRACK 			# NewIntExp
	| NEW ID LPAREN RPAREN 					# NewIdExp
	| '!' expr 								# NotExp
	| LPAREN expr RPAREN 					# ParenExp
	;

op : 	'&&'
	| 	'<'
	| 	'>'
	| 	'*'
	| 	'+'
	| 	'-'
	;

exprList : expr (exprRest)*
	|;

exprRest : COMMA expr ;

boolLiterals : (TRUE|FALSE) ;

BOOLEAN : 'boolean'	;
STRING	: 'String'	;
INT		: 'int'		;
IF		: 'if'		;
ELSE	: 'else'	;
WHILE	: 'while'	;
RETURN	: 'return'	;
CLASS	: 'class'	;
VOID	: 'void'	;
STATIC	: 'static'	;
PUBLIC	: 'public'	;
SOUT	: 'System.out.println';
THIS	: 'this'	;
NEW		: 'new'		;
LENGTH	: 'length'	;
EPSILON	: ''		;

TRUE 	: 'true'	;
FALSE 	: 'false'	;
//BoolLiterals : TRUE|FALSE;

// separators
LPAREN	: '('	;
RPAREN	: ')'	;
LBRACK	: '['	;
RBRACK	: ']'	;
LBRACE	: '{'	;
RBRACE	: '}'	;
ENDL	: ';'	;
COMMA	: ','	;

AND : '&&'	;
LT  : '<'	;
GT	: '>'	;
ADD	: '+'	;
SUB	: '-'	;
MUL : '*'	;

ID	: LETTER (LETTER|DIGIT)* ;
NUM : [0]|[1-9]DIGIT* ;
WS  : [\t\n\r' ']+ -> skip ;
COMMENT : '/*' .*? '*/' -> skip ;
LINE_COMMENT : '//' ~[\r\n]* -> skip ;
/*
	Helper tokens
*/
fragment LETTER : [a-zA-Z_] ;
fragment DIGIT : [0-9] ;
