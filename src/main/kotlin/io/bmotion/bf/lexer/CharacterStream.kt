package io.bmotion.bf.lexer

import java.io.File

const val NULL = (-1).toChar()

class CharacterStream(private val content: String) {

    constructor(file: File) : this(file.readText())

    private var index: Int = -1
    var line: Int = 1
        private set
    var position: Int = 0
        private set

    fun peek(): Char {
        return if (!canRead())
            NULL
        else
            content[index+1]
    }

    fun take(): Char = peek().also {

        when {
            it != NULL -> {
                index++
                position++
            }
            it == '\n' -> {
                position = 1
                line++
            }
        }
    }

    fun canRead(): Boolean = content.length > index+1
}
