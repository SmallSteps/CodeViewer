package org.hanuna.lexer

import org.hanuna.text.TextPosition

data class Token(val tokenType: TokenType, val text: String, val position: TextPosition)

interface TokenType

interface Lexer {
    fun getTokens(input: CharSequence): Sequence<Token>
}
