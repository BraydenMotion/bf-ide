package io.bmotion.bf.lexer

import java.io.File

object Lexer {

    fun process(file: File): TokenStream = process(CharacterStream(file))
    fun process(content: String): TokenStream = process(CharacterStream(content))

    fun process(charStream: CharacterStream): TokenStream {

        val tokens = mutableListOf<Token>()

        while(charStream.canRead()) {
            when (val char = charStream.take()) {
                '<' -> tokens.add(OpenDiamondToken(charStream.line, charStream.position, "<"))
                '>' -> tokens.add(CloseDiamondToken(charStream.line, charStream.position, ">"))
                '+' -> tokens.add(PlusToken(charStream.line, charStream.position, "+"))
                '-' -> tokens.add(MinusToken(charStream.line, charStream.position, "-"))
                '.' -> tokens.add(PeriodToken(charStream.line, charStream.position, "."))
                ',' -> tokens.add(CommaToken(charStream.line, charStream.position, ","))
                '[' -> tokens.add(OpenSquareToken(charStream.line, charStream.position, "["))
                ']' -> tokens.add(CloseSquareToken(charStream.line, charStream.position, "]"))
                else -> tokens.add(CharacterToken(charStream.line, charStream.position, char.toString()))
            }
        }

        return TokenStream(tokens)
    }
}