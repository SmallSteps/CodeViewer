package org.hanuna.lexer.flex

import org.hanuna.lexer.TokenType

enum class JavaStubTokenTypes : TokenType {
    // declaration
    IMPORT,
    CLASS,
    EXTENDS,
    IMPLEMENTS,

    // modificators
    ABSTRACT,
    FINAL,
    DEFAULT,

    // primitives
    BOOLEAN,
    BYTE,
    CHAR,
    DOUBLE,
    FLOAT,
    INT,

    // Some tokens inside function or property body
    OTHER,
}