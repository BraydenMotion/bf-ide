package io.bmotion.bf.lexer

interface Token {
    val line: Int
    val pos: Int
    val text: String
}

class OpenDiamondToken(override val line: Int, override val pos: Int, override val text: String) : Token
class CloseDiamondToken(override val line: Int, override val pos: Int, override val text: String) : Token
class PlusToken(override val line: Int, override val pos: Int, override val text: String) : Token
class MinusToken(override val line: Int, override val pos: Int, override val text: String) : Token
class PeriodToken(override val line: Int, override val pos: Int, override val text: String) : Token
class CommaToken(override val line: Int, override val pos: Int, override val text: String) : Token
class OpenSquareToken(override val line: Int, override val pos: Int, override val text: String) : Token
class CloseSquareToken(override val line: Int, override val pos: Int, override val text: String) : Token
class CharacterToken(override val line: Int, override val pos: Int, override val text: String) : Token
