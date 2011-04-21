%%

%byaccj

%line

%{
  private Parser yyparser;

  private String string; /* used to build up string literals */

  public Yylex(java.io.Reader r, Parser yyparser) {
    this(r);
    this.yyparser = yyparser;
  }

  /* track the line numbers for debugging */
  public int getLine() { return yyline; }
%}

comment = "/*" [^*] ~"*/" | "/*" "*"+ "/" | "//" .* {NL}
FLT     = [0-9]+"."[0-9]+
INT     = [0-9]+
ID      = [A-Za-z][A-Za-z0-9_]*
NL      = \n | \r | \r\n
ARC     = "->"
EQ      = "=="
NEQ     = "!="
LTE     = "<="
GTE     = ">="

/* error lexemes */
BAD_ID  = ~{ID}

%state STRING
%state SQSTRING

%%

<YYINITIAL>{

/* strip whitespace and comments */
{NL}      |
[ \t]+    |
{comment}   { }

/* reserved words - more to follow */
int         { return Parser.INT_T; }
float       { return Parser.FLT_T; }
string      { return Parser.STR_T; }
Node        { return Parser.NODE_T; }
Arc         { return Parser.ARC_T; }
Graph       { return Parser.GRAPH; }
List        { return Parser.LIST_T; }
while       { return Parser.WHILE; }
if          { return Parser.IF; }
of          { return Parser.OF; }
use         { return Parser.USE; }
print       { return Parser.PRINT; }

/* arc connector */
{ARC}       { return Parser.ARC; }

/* equality and comparison operators */
{EQ}        { return Parser.EQ; }
{NEQ}       { return Parser.NEQ; }
{LTE}       { return Parser.LTE; }
{GTE}       { return Parser.GTE; }

/* single-character operators */
"@" |
":" |
"." |
"," |
";" |
"<" |
">" |
"=" |
"+" | 
"-" | 
"*" | 
"/" | 
"%" | 
"{" |
"}" |
"[" |
"]" |
"(" | 
")"         { return (int) yycharat(0); }

/* string literal */

\"          { yybegin(STRING);
              string = ""; }

\'          { yybegin(SQSTRING); 
              string = ""; }

/* float */
{FLT}       { yyparser.yylval = new ParserVal(Double.parseDouble(yytext()));
              return Parser.FLT; }

/* int */
{INT}       { yyparser.yylval = new ParserVal(Integer.parseInt(yytext()));
              return Parser.INT; }

/* identifier */
{ID}        { yyparser.yylval = new ParserVal(yytext());
              return Parser.ID; }

} /* End state YYINITIAL */

<STRING> {

/* Closing double quote */

\"          { yybegin(YYINITIAL); 
              yyparser.yylval = new ParserVal(string);
              return Parser.STR; }

/* Escaped characters */

"\\\""      { string += "\""; }
"\\n"       { string += "\n"; }
"\\t"       { string += "\t"; }
"\\r"       { string += "\r"; }
"\\f"       { string += "\f"; }
"\\b"       { string += "\b"; }
"\\\\"      { string += "\\"; }

/* All other characters */

[^\"\\]+    { string += yytext(); }

} /* End state STRING */

<SQSTRING> {

/* Closing single quote */

\'          { yybegin(YYINITIAL);
              yyparser.yylval = new ParserVal(string);
              return Parser.STR; }

/* Escaped single quote */

"\\'"       { string += "'"; }

/* All other characters */

[^\'\\]+    { string += yytext(); }

} /* End state SQSTRING */