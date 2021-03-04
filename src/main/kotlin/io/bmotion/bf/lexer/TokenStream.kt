package io.bmotion.bf.lexer

class TokenStream(private val tokens: List<Token>) {

    private var index: Int = -1

    // TODO: add error event
    fun peek(): Token = if (hasNext()) tokens[index+1] else throw Exception("End of token stream")
    fun take(): Token = peek().also { index++ }
    fun hasNext(): Boolean = index+1 < tokens.size

    override fun toString(): String {
        return "TokenStream$tokens"
    }
}