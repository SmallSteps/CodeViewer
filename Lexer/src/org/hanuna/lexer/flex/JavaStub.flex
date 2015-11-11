package org.hanuna.lexer.generated;


import org.hanuna.lexer.Token;
import org.hanuna.lexer.TokenType;
import org.hanuna.lexer.flex.support.*;
import static org.hanuna.lexer.flex.JavaStubTokenTypes.*;
import org.jetbrains.annotations.NotNull;

%%

%public
%class JavaStubLexer
%extends JFlexAdapter

%unicode

%line
%column

%function next_token
%type Token

%apiprivate

%{
  StringBuilder string = new StringBuilder();

  private Token token(TokenType type) {
    return createToken(type, yyline+1, yycolumn+1, yytext());
  }

  private Token token(TokenType type, Object value) {
    return createToken(type, yyline+1, yycolumn+1, yytext(), value);
  }

  @NotNull
  @Override
  protected Token nextToken() throws java.io.IOException {
    return next_token();
  }

  /**
   * assumes correct representation of a long value for
   * specified radix in scanner buffer from <code>start</code>
   * to <code>end</code>
   */
  private long parseLong(int start, int end, int radix) {
    long result = 0;
    long digit;

    for (int i = start; i < end; i++) {
      digit  = Character.digit(yycharat(i),radix);
      result*= radix;
      result+= digit;
    }

    return result;
  }
%}

/* main character classes */
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]

WhiteSpace = {LineTerminator} | [ \t\f]

/* comments */
Comment = {TraditionalComment} | {EndOfLineComment} |
          {DocumentationComment}

TraditionalComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/*" "*"+ [^/*] ~"*/"

/* identifiers */
Identifier = [:jletter:][:jletterdigit:]*

/* integer literals */
DecIntegerLiteral = 0 | [1-9][0-9]*
DecLongLiteral    = {DecIntegerLiteral} [lL]

HexIntegerLiteral = 0 [xX] 0* {HexDigit} {1,8}
HexLongLiteral    = 0 [xX] 0* {HexDigit} {1,16} [lL]
HexDigit          = [0-9a-fA-F]

OctIntegerLiteral = 0+ [1-3]? {OctDigit} {1,15}
OctLongLiteral    = 0+ 1? {OctDigit} {1,21} [lL]
OctDigit          = [0-7]

/* floating point literals */
FloatLiteral  = ({FLit1}|{FLit2}|{FLit3}) {Exponent}? [fF]
DoubleLiteral = ({FLit1}|{FLit2}|{FLit3}) {Exponent}?

FLit1    = [0-9]+ \. [0-9]*
FLit2    = \. [0-9]+
FLit3    = [0-9]+
Exponent = [eE] [+-]? [0-9]+

/* string and character literals */
StringCharacter = [^\r\n\"\\]
SingleCharacter = [^\r\n\'\\]

%state STRING, CHARLITERAL

%%

<YYINITIAL> {

  /* keywords */
  "package"                      { return token(PACKAGE); }

  "import"                       { return token(IMPORT); }

  "interface"                    { return token(INTERFACE); }
  "class"                        { return token(CLASS); }
  "extends"                      { return token(EXTENDS); }
  "implements"                   { return token(IMPLEMENTS); }

  "throws"                       { return token(THROWS); }

  "abstract"                     { return token(ABSTRACT); }
  "final"                        { return token(FINAL); }
  "default"                      { return token(DEFAULT); }
  "native"                       { return token(NATIVE); }
  "synchronized"                 { return token(SYNCHRONIZED); }
  "transient"                    { return token(TRANSIENT); }
  "static"                       { return token(STATIC); }
  "volatile"                     { return token(VOLATILE); }
  "strictfp"                     { return token(STRICTFP); }

  "public"                       { return token(PUBLIC); }
  "private"                      { return token(PRIVATE); }
  "protected"                    { return token(PROTECTED); }

  "void"                         { return token(VOID); }
  "boolean"                      { return token(BOOLEAN); }
  "byte"                         { return token(BYTE); }
  "char"                         { return token(CHAR); }
  "double"                       { return token(DOUBLE); }
  "float"                        { return token(FLOAT); }
  "int"                          { return token(INT); }
  "long"                         { return token(LONG); }
  "short"                        { return token(SHORT); }

  "break"                        { return token(OTHER); }
  "case"                         { return token(OTHER); }
  "catch"                        { return token(OTHER); }
  "const"                        { return token(OTHER); }
  "continue"                     { return token(OTHER); }
  "do"                           { return token(OTHER); }
  "else"                         { return token(OTHER); }
  "finally"                      { return token(OTHER); }
  "for"                          { return token(OTHER); }
  "instanceof"                   { return token(OTHER); }
  "new"                          { return token(OTHER); }
  "goto"                         { return token(OTHER); }
  "if"                           { return token(OTHER); }
  "super"                        { return token(OTHER); }
  "switch"                       { return token(OTHER); }

  "return"                       { return token(OTHER); }
  "while"                        { return token(OTHER); }
  "this"                         { return token(OTHER); }
  "throw"                        { return token(OTHER); }
  "try"                          { return token(OTHER); }

  /* boolean literals */
  "true"                         { return token(OTHER); }
  "false"                        { return token(OTHER); }

  /* null literal */
  "null"                         { return token(OTHER); }


  /* separators */
  "("                            { return token(LPAREN); }
  ")"                            { return token(RPAREN); }
  "{"                            { return token(LBRACE); }
  "}"                            { return token(RBRACE); }
  "["                            { return token(LBRACK); }
  "]"                            { return token(RBRACK); }
  ";"                            { return token(SEMICOLON); }
  ","                            { return token(COMMA); }
  "."                            { return token(DOT); }

  /* operators */
  ">"                            { return token(GT); }
  "<"                            { return token(LT); }
  ":"                            { return token(COLON); }

  "="                            { return token(OTHER); }
  "!"                            { return token(OTHER); }
  "~"                            { return token(OTHER); }
  "?"                            { return token(OTHER); }
  "=="                           { return token(OTHER); }
  "<="                           { return token(OTHER); }
  ">="                           { return token(OTHER); }
  "!="                           { return token(OTHER); }
  "&&"                           { return token(OTHER); }
  "||"                           { return token(OTHER); }
  "++"                           { return token(OTHER); }
  "--"                           { return token(OTHER); }
  "+"                            { return token(OTHER); }
  "-"                            { return token(OTHER); }
  "*"                            { return token(OTHER); }
  "/"                            { return token(OTHER); }
  "&"                            { return token(OTHER); }
  "|"                            { return token(OTHER); }
  "^"                            { return token(OTHER); }
  "%"                            { return token(OTHER); }
  "<<"                           { return token(OTHER); }
  ">>"                           { return token(OTHER); }
  ">>>"                          { return token(OTHER); }
  "+="                           { return token(OTHER); }
  "-="                           { return token(OTHER); }
  "*="                           { return token(OTHER); }
  "/="                           { return token(OTHER); }
  "&="                           { return token(OTHER); }
  "|="                           { return token(OTHER); }
  "^="                           { return token(OTHER); }
  "%="                           { return token(OTHER); }
  "<<="                          { return token(OTHER); }
  ">>="                          { return token(OTHER); }
  ">>>="                         { return token(OTHER); }

  /* string literal */
  \"                             { yybegin(STRING); string.setLength(0); }

  /* character literal */
  \'                             { yybegin(CHARLITERAL); }

  /* numeric literals */

  /* This is matched together with the minus, because the number is too big to
     be represented by a positive integer. */
  "-2147483648"                  { return token(NUMBER, new Integer(Integer.MIN_VALUE)); }

  {DecIntegerLiteral}            { return token(NUMBER, new Integer(yytext())); }
  {DecLongLiteral}               { return token(NUMBER, new Long(yytext().substring(0,yylength()-1))); }

  {HexIntegerLiteral}            { return token(NUMBER, new Integer((int) parseLong(2, yylength(), 16))); }
  {HexLongLiteral}               { return token(NUMBER, new Long(parseLong(2, yylength()-1, 16))); }

  {OctIntegerLiteral}            { return token(NUMBER, new Integer((int) parseLong(0, yylength(), 8))); }
  {OctLongLiteral}               { return token(NUMBER, new Long(parseLong(0, yylength()-1, 8))); }

  {FloatLiteral}                 { return token(NUMBER, new Float(yytext().substring(0,yylength()-1))); }
  {DoubleLiteral}                { return token(NUMBER, new Double(yytext())); }
  {DoubleLiteral}[dD]            { return token(NUMBER, new Double(yytext().substring(0,yylength()-1))); }

  /* comments */
  {Comment}                      { /* ignore */ } // todo

  /* whitespace */
  {WhiteSpace}                   { /* ignore */ } // todo

  /* identifiers */
  {Identifier}                   { return token(IDENTIFIER, yytext()); }
}

<STRING> {
  \"                             { yybegin(YYINITIAL); return token(STRING_LITERAL, string.toString()); }

  {StringCharacter}+             { string.append( yytext() ); }

  /* escape sequences */
  "\\b"                          { string.append( '\b' ); }
  "\\t"                          { string.append( '\t' ); }
  "\\n"                          { string.append( '\n' ); }
  "\\f"                          { string.append( '\f' ); }
  "\\r"                          { string.append( '\r' ); }
  "\\\""                         { string.append( '\"' ); }
  "\\'"                          { string.append( '\'' ); }
  "\\\\"                         { string.append( '\\' ); }
  \\[0-3]?{OctDigit}?{OctDigit}  { char val = (char) Integer.parseInt(yytext().substring(1),8);
                        				   string.append( val ); }

  /* error cases */
  \\.                            { throw new RuntimeException("Illegal escape sequence \""+yytext()+"\""); }
  {LineTerminator}               { throw new RuntimeException("Unterminated string at end of line"); }
}

<CHARLITERAL> {
  {SingleCharacter}\'            { yybegin(YYINITIAL); return token(CHARACTER_LITERAL, yytext().charAt(0)); }

  /* escape sequences */
  "\\b"\'                        { yybegin(YYINITIAL); return token(CHARACTER_LITERAL, '\b');}
  "\\t"\'                        { yybegin(YYINITIAL); return token(CHARACTER_LITERAL, '\t');}
  "\\n"\'                        { yybegin(YYINITIAL); return token(CHARACTER_LITERAL, '\n');}
  "\\f"\'                        { yybegin(YYINITIAL); return token(CHARACTER_LITERAL, '\f');}
  "\\r"\'                        { yybegin(YYINITIAL); return token(CHARACTER_LITERAL, '\r');}
  "\\\""\'                       { yybegin(YYINITIAL); return token(CHARACTER_LITERAL, '\"');}
  "\\'"\'                        { yybegin(YYINITIAL); return token(CHARACTER_LITERAL, '\'');}
  "\\\\"\'                       { yybegin(YYINITIAL); return token(CHARACTER_LITERAL, '\\'); }
  \\[0-3]?{OctDigit}?{OctDigit}\' { yybegin(YYINITIAL);
			                              int val = Integer.parseInt(yytext().substring(1,yylength()-1),8);
			                            return token(CHARACTER_LITERAL, (char)val); }

  /* error cases */
  \\.                            { throw new RuntimeException("Illegal escape sequence \""+yytext()+"\""); }
  {LineTerminator}               { throw new RuntimeException("Unterminated character literal at end of line"); }
}

/* error fallback */
.|\n                             { throw new RuntimeException("Illegal character \""+yytext()+
                                                              "\" at line "+yyline+", column "+yycolumn); }
<<EOF>>                          { return token(EOF); }