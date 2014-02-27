grammar tjelvar;

program
  : mainClass classDeclaration*
  ;

mainClass
  : 'class' Id '{' 'public static void main' '(' STRING LEFTBRACK RIGHTBRACK Id '{' varDeclaration* statement* '}'
  ;

classDeclaration
  : 'class' Id '{' varDeclaration* methodDeclaration* '}'
  ;

varDeclaration
  : type Id
  ;

methodDeclaration
  : 'public' type Id '(' formalList ')' '{' varDeclaration* statement* 'return' expression ';' '}'
  ;

formalList
  : type Id formalRest*
  |
  ;

formalRest
  : ',' type Id
  ;

type
  : 'int[]'
  | 'boolean'
  | 'int'
  | Id
  ;

statement
  : braceStatement
  | 'if' parenExpression statement 'else' statement
  | 'while' parenExpression statement
  | 'System.out.println' parenExpression
  ;

braceStatement
  : '{' statement* '}'
  ;

expression
  :  expression operator expression
  | expression '[' expression ']'
  |  expression '.length'
  |  expression '.' Id '(' expressionList ')'
  |  IntLiteral
  | 'true'
  | 'false'
  | Id
  |  'this'
  | 'new int [' expression ']'
  | 'new ' Id '()'
  | '!' expression
  | parenExpression
  |  LongLiteral
  ;

expressionList
  :  expression expressionRest*
  ;

expressionRest
  : ',' expression
  ;

parenExpression
  : '(' expression ')'
  ;

operator
  :  '&&'
  | '<'
  |  '+'
  |  '-'
  |  '*'
  ;

Id
  :  Letter LetterOrDigit*
  ;

fragment
Letter
  :  [a-zA-Z$_]
  ;

fragment
LetterOrDigit
  :  [a-zA-Z0-9$_]
  ;

LongLiteral
  :  '0'[lL]
  |  NonZeroDigit Digit* [lL]
  ;

IntLiteral
  :  NonZeroDigit Digit*
  ;

fragment
Digit
  :  '0'
  |  NonZeroDigit
  ;

fragment
NonZeroDigit
  : [1-9]
  ;


//Keywords

TRUE          : 'true';
FALSE         :  'false';  
THIS          :  'this';
NEW           : 'new';
INT           : 'int';
STRING        : 'String';


//Separators
LEFTPAREN     : '(';
RIGHTPAREN    : ')';
LEFTBRACE     : '{';
RIGHTBRACE    : '}';
LEFTBRACK     : '[';
RIGHTBRACK    : ']';
SEMI          : ';';
COMMA         : ',';
DOT           : '.';


//Operator
