package io.bmotion.bf.lexer

import java.io.File

class CharacterStream(private val content: String) {

    constructor(file: File) : this(file.readText())

    private var index: Int = -1
    var line: Int = 1
        private set
    var position: Int = 1
        private set

    // TODO: add event
    fun peek(): Char = if (canRead()) content[index + 1] else throw Exception()

    fun take(): Char = peek().also {
        index++
        position++

        if (it == '\n') {
            position = 1
            line++
        }
    }

    fun canRead(): Boolean = content.length > index + 1
}
