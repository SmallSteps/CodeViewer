package org.hanuna.lexer.flex.support

import org.apache.commons.io.input.CharSequenceReader
import org.hanuna.lexer.Lexer
import org.hanuna.lexer.Token
import org.hanuna.lexer.TokenType
import org.hanuna.text.TextPosition
import org.hanuna.util.check
import java.io.Reader


abstract class JFlexAdapter {
    @JvmOverloads
    protected fun createToken(type: TokenType, line: Int, column: Int, text: String, value: Any? = null): Token {
        // todo use value
        return Token(type, text, TextPosition(line, column))
    }

    @Throws(java.io.IOException::class)
    abstract fun nextToken(): Token

    object EOF : TokenType
}

fun createLexer(flexLexer: (Reader) -> JFlexAdapter): Lexer = object: Lexer {
    override fun getTokens(input: CharSequence): Sequence<Token> {
        val lexer = flexLexer(CharSequenceReader(input))

        return sequence { lexer.nextToken().check { it.tokenType != JFlexAdapter.EOF } }
    }

}

