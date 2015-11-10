package org.hanuna.lexer

interface Token {
    val tokenType: TokenType
    val text: String
}

interface TokenType {

}

interface Lexer {
    fun getTokens(input: CharSequence): Sequence<Token>
}
