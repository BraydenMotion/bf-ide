package io.bmotion.bf.parser

import io.bmotion.bf.lexer.*

interface Parser {
    fun parse(tokenStream: TokenStream): Instruction
}

object ListParser: Parser {
    override fun parse(tokenStream: TokenStream): Instruction {

        if (!tokenStream.hasNext())
            return ListInstruction(listOf(), 0, 0, "")

        val instructions = mutableListOf<Instruction>()
        val firstToken = tokenStream.peek()

        while (tokenStream.hasNext()) {
            val instruction = when(tokenStream.peek()) {
                is OpenDiamondToken, is CloseDiamondToken -> PointerParser.parse(tokenStream)
                is PlusToken, is MinusToken -> MemoryParser.parse(tokenStream)
                is PeriodToken, is CommaToken -> SerialParser.parse(tokenStream)
                is OpenSquareToken -> LoopParser.parse(tokenStream)
                else -> break
            }

            instructions.add(instruction)
        }

        return ListInstruction(instructions, firstToken.line, firstToken.pos, "")
    }
}

object PointerParser: Parser {
    override fun parse(tokenStream: TokenStream): Instruction {
        return when (val token = tokenStream.take()) {
            is OpenDiamondToken -> PointerInstruction(Operation.FIRST, token.line, token.pos, token.text)
            is CloseDiamondToken -> PointerInstruction(Operation.SECOND, token.line, token.pos, token.text)
            else -> NoOpInstruction(token.line, token.pos, token.text)
        }
    }
}

object MemoryParser: Parser {
    override fun parse(tokenStream: TokenStream): Instruction {
        return when (val token = tokenStream.take()) {
            is PlusToken -> MemoryInstruction(Operation.FIRST, token.line, token.pos, token.text)
            is MinusToken -> MemoryInstruction(Operation.SECOND, token.line, token.pos, token.text)
            else -> NoOpInstruction(token.line, token.pos, token.text)
        }
    }
}

object SerialParser: Parser {
    override fun parse(tokenStream: TokenStream): Instruction {
        return when (val token = tokenStream.take()) {
            is PeriodToken -> SerialInstruction(Operation.FIRST, token.line, token.pos, token.text)
            is CommaToken -> SerialInstruction(Operation.SECOND, token.line, token.pos, token.text)
            else -> NoOpInstruction(token.line, token.pos, token.text)
        }
    }
}

object LoopParser: Parser {
    override fun parse(tokenStream: TokenStream): Instruction {
        val firstToken = tokenStream.peek()

        if (firstToken !is OpenSquareToken)
            throw Exception("Expected [ but found ${firstToken.text} at [${firstToken.line}:${firstToken.pos}]")

        val list = ListParser.parse(tokenStream.also { it.take() }) as ListInstruction
        val token = tokenStream.take()

        if (token !is CloseSquareToken)
            throw Exception("Expected ] but found ${token.text} at [${token.line}:${token.pos}]")

        return LoopInstruction(list, firstToken.line, firstToken.pos, "")
    }
}