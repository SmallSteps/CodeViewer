package org.hanuna.lexer.tests

import org.hanuna.lexer.flex.support.JFlexAdapter
import org.hanuna.lexer.flex.support.createLexer
import org.hanuna.text.getTextFromFile
import java.io.Reader

abstract class AbstractLexerTest {
    abstract val lexer: (Reader) -> JFlexAdapter

    fun doTest(filename: String) {
        val myLexer = createLexer(lexer)

        myLexer.getTokens(getTextFromFile(filename)).forEach {
            println(it)
        }
    }
}