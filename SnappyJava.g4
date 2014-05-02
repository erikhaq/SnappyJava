grammar SnappyJava;

program     : mainClass (classDecl)*;
mainClass   : CLASS ID LBRACE PUBLIC STATIC VOID ID LPAREN STRING LBRACK RBRACK ID RPAREN LBRACE (varDecl)* (stmt)* RBRACE RBRACE;
classDecl   : CLASS ID (extend)? LBRACE (varDecl)* (methodDecl)* RBRACE;
varDecl     : type ID ENDL;
methodDecl  : PUBLIC type ID LPAREN formalList RPAREN LBRACE (varDecl)* (stmt)* RETURN expr ENDL RBRACE;

formalList
  : type ID (formalRest)*
  |
  ;

formalRest
  : ',' type ID
  ;

type
  : INT LBRACK RBRACK
  | BOOLEAN
  | INT
  | ID
  ;

stmt
  : LBRACE (stmt)* RBRACE                 # Body
  | IF LPAREN expr RPAREN stmt ELSE stmt  # If
  | WHILE LPAREN expr RPAREN stmt         # While
  | SOUT LPAREN expr RPAREN ENDL          # Sout
  | ID '=' expr ENDL                      # Assign
  | ID LBRACK expr RBRACK '=' expr ENDL   # ArrayAssign
  ; 

expr
  : LPAREN expr RPAREN                    # ParenExp
  | expr LBRACK expr RBRACK               # ArrayExp
  | expr '.' ID LPAREN exprList RPAREN    # CallExp
  | expr '.' LENGTH                       # LengthExp
  | '!' expr                              # NotExp
  | NEW INT LBRACK expr RBRACK            # NewIntArrayExp
  | NEW ID LPAREN RPAREN                  # NewIdExp
  | expr MUL expr                         # MultiOp
  | expr (ADD|SUB) expr                   # AddSubOp
  | expr (LT|LTE) expr                    # LTComp
  | expr (GT|GTE) expr                    # GTComp
  | expr CEQ expr                         # CEQComp
  | expr CNE expr                         # CNEComp
  | expr AND expr                         # AndComp
  | NUM                                   # NumExp
  | boolLiterals                          # BoolExp
  | ID                                    # IdExp
  | THIS                                  # ThisExp
  ;

op
  : '&&'
  | '<='
  | '>='
  | '<'
  | '>'
  | '*'
  | '+'
  | '-'
  ;

exprList
  : expr (exprRest)*
  |
  ;

exprRest
  : COMMA expr
  ;

boolLiterals
  : (TRUE|FALSE)
  ;

extend
  : 'extends' ID
  ;

// Keywords
BOOLEAN     : 'boolean';
STRING      : 'String';
INT         : 'int';
IF          : 'if';
ELSE        : 'else';
WHILE       : 'while';
RETURN      : 'return';
CLASS       : 'class';
VOID        : 'void';
STATIC      : 'static';
PUBLIC      : 'public';
SOUT        : 'System.out.println';
THIS        : 'this';
NEW         : 'new';
LENGTH      : 'length';
TRUE        : 'true';
FALSE       : 'false';
// MAIN        : 'main';
// MAIN        : PUBLIC WS+ STATIC WS+ VOID WS+ 'main';

// Separators
LPAREN      : '(';
RPAREN      : ')';
LBRACK      : '[';
RBRACK      : ']';
LBRACE      : '{';
RBRACE      : '}';
ENDL        : ';';
COMMA       : ',';

// Operators
AND         : '&&';
LT          : '<' ;
LTE         : '<=';
GT          : '>' ;
GTE         : '>=';
ADD         : '+' ;
SUB         : '-' ;
MUL         : '*' ;
CEQ         : '==';
CNE         : '!=';

// Misc
ID          : LETTER (LETTER|DIGIT)*;
NUM         : [0]|[1-9]DIGIT*;
WS          : [\t\n\r' '\f]+ -> skip;
COMMENT     : '/*' .*? '*/' -> skip;
LINE_COMMENT: '//' ~[\r\n]* -> skip;

// Helper tokens
fragment LETTER
  : [a-zA-Z_];
fragment DIGIT
  : [0-9];

