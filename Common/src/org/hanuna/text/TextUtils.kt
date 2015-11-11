package org.hanuna.text

import java.io.File


data class TextPosition(val line: Int, val column: Int)


fun getTextFromFile(filename: String): CharSequence {
    return File(filename).readText()
}