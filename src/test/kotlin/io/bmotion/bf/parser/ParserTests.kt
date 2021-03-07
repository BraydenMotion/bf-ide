package io.bmotion.bf.parser

import io.bmotion.bf.lexer.Lexer
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf

class PointerParserTests: StringSpec({
    "Parse <" {
        val tokens = Lexer.process("<")
        val instruction = PointerParser.parse(tokens)

        instruction.shouldBeTypeOf<PointerInstruction>()
        instruction.value.shouldBe("<")
        instruction.direction.shouldBe(Operation.FIRST)
    }

    "Parse >" {
        val tokens = Lexer.process(">")
        val instruction = PointerParser.parse(tokens)

        instruction.shouldBeTypeOf<PointerInstruction>()
        instruction.value.shouldBe(">")
        instruction.direction.shouldBe(Operation.SECOND)
    }

    "Parse non-pointer" {
        val tokens = Lexer.process("a")
        val instruction = PointerParser.parse(tokens)

        instruction.shouldBeTypeOf<NoOpInstruction>()
        instruction.value.shouldBe("a")
    }
})

class MemoryParserTests: StringSpec({
    "Parse +" {
        val tokens = Lexer.process("+")
        val instruction = MemoryParser.parse(tokens)

        instruction.shouldBeTypeOf<MemoryInstruction>()
        instruction.value.shouldBe("+")
        instruction.operation.shouldBe(Operation.FIRST)
    }

    "Parse -" {
        val tokens = Lexer.process("-")
        val instruction = MemoryParser.parse(tokens)

        instruction.shouldBeTypeOf<MemoryInstruction>()
        instruction.value.shouldBe("-")
        instruction.operation.shouldBe(Operation.SECOND)
    }

    "Parse non-memory" {
        val tokens = Lexer.process("a")
        val instruction = MemoryParser.parse(tokens)

        instruction.shouldBeTypeOf<NoOpInstruction>()
        instruction.value.shouldBe("a")
    }
})

class SerialParserTests: StringSpec({
    "Parse ." {
        val tokens = Lexer.process(".")
        val instruction = SerialParser.parse(tokens)

        instruction.shouldBeTypeOf<SerialInstruction>()
        instruction.value.shouldBe(".")
        instruction.port.shouldBe(Operation.FIRST)
    }

    "Parse ," {
        val tokens = Lexer.process(",")
        val instruction = SerialParser.parse(tokens)

        instruction.shouldBeTypeOf<SerialInstruction>()
        instruction.value.shouldBe(",")
        instruction.port.shouldBe(Operation.SECOND)
    }

    "Parse non-serial" {
        val tokens = Lexer.process("a")
        val instruction = SerialParser.parse(tokens)

        instruction.shouldBeTypeOf<NoOpInstruction>()
        instruction.value.shouldBe("a")
    }
})

class ListParserTests: StringSpec({
    "Parse single instruction" {
        val tokens = Lexer.process("+")
        val instruction = ListParser.parse(tokens)

        instruction.shouldBeTypeOf<ListInstruction>()
        instruction.instructions.size.shouldBe(1)
        instruction.instructions[0].shouldBeTypeOf<MemoryInstruction>()
    }

    "Parse multiple instructions" {
        val tokens = Lexer.process("<>+-.,")
        val instruction = ListParser.parse(tokens)

        instruction.shouldBeTypeOf<ListInstruction>()
        instruction.instructions.size.shouldBe(6)
    }

    "Parse instructions with loop" {
        val tokens = Lexer.process("<>+-[].,")
        val instruction = ListParser.parse(tokens)

        instruction.shouldBeTypeOf<ListInstruction>()
        instruction.instructions.size.shouldBe(7)
    }

    "Parse empty stream" {
        val tokens = Lexer.process("")
        val instruction = ListParser.parse(tokens)

        instruction.shouldBeTypeOf<ListInstruction>()
        instruction.instructions.shouldBeEmpty()
    }
})

class LoopParserTests: StringSpec({
    "Parse empty loop" {
        val tokens = Lexer.process("[]")
        val instruction = LoopParser.parse(tokens)

        instruction.shouldBeTypeOf<LoopInstruction>()
        instruction.list.instructions.shouldBeEmpty()
    }

    "Parse closing bracket" {
        val tokens = Lexer.process("]")
        shouldThrow<Exception> {
            LoopParser.parse(tokens)
        }
    }

    "Parse opening bracket" {
        val tokens = Lexer.process("[")
        shouldThrow<Exception> {
            LoopParser.parse(tokens)
        }
    }

    "Parse populated loop" {
        val tokens = Lexer.process("[+>++>+++>++++,.]")
        val instruction = LoopParser.parse(tokens)

        instruction.shouldBeTypeOf<LoopInstruction>()
        instruction.list.instructions.size.shouldBe(15)
    }

    "Parse nested loop" {
        val tokens = Lexer.process("[+>[+++]-]")
        val instruction = LoopParser.parse(tokens)

        instruction.shouldBeTypeOf<LoopInstruction>()
        instruction.list.instructions.size.shouldBe(4)
    }
})
