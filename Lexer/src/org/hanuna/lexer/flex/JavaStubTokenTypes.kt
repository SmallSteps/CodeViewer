package org.hanuna.lexer.flex

import org.hanuna.lexer.TokenType

enum class JavaStubTokenTypes : TokenType {
    // declaration
    IMPORT,
    PACKAGE,
    INTERFACE,
    CLASS,
    EXTENDS,
    IMPLEMENTS,
    THROWS,

    // modificators
    ABSTRACT,
    FINAL,
    DEFAULT,
    NATIVE,
    STATIC,
    STRICTFP,
    VOLATILE,
    TRANSIENT,
    SYNCHRONIZED,


    PUBLIC,
    PRIVATE,
    PROTECTED,

    // primitives
    BOOLEAN,
    BYTE,
    CHAR,
    DOUBLE,
    FLOAT,
    INT,
    LONG,
    VOID,
    SHORT,

    // Some tokens inside function or property body
    OTHER,

    EOF,

    IDENTIFIER,

    LPAREN, // (
    RPAREN, // )
    LBRACE, // {
    RBRACE, // }
    LBRACK, // [
    RBRACK, // ]
    SEMICOLON, // ;
    COMMA, // ,
    DOT, // .
    GT, // >
    LT, // <
    COLON, // :


    STRING_LITERAL,
    NUMBER,
    CHARACTER_LITERAL

}