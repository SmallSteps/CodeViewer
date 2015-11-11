package org.hanuna.lexer.tests

import org.hanuna.lexer.flex.support.JFlexAdapter
import org.hanuna.lexer.generated.JavaStubLexer
import java.io.Reader

class JavaStubLexerTest : AbstractLexerTest() {
    override val lexer: (Reader) -> JFlexAdapter
        get() = ::JavaStubLexer


}

fun main(args: Array<String>) {
    JavaStubLexerTest().doTest("Lexer/src/org/hanuna/lexer/generated/JavaStubLexer.java")
}