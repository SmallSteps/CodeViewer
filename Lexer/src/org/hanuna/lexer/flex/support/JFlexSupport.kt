package org.hanuna.lexer.flex.support

import org.hanuna.lexer.Lexer
import org.hanuna.lexer.Token
import org.hanuna.lexer.TokenType
import org.hanuna.text.TextPosition


abstract class JFlexAdapter: Lexer {
    @JvmOverloads
    protected fun createToken(type: TokenType, line: Int, column: Int, text: String, value: Any? = null): Token {
        // todo use value
        return Token(type, text, TextPosition(line, column))
    }

    @Throws(java.io.IOException::class)
    protected abstract fun next_token(): Token

}