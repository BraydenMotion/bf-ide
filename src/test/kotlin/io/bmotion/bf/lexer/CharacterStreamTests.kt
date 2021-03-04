package io.bmotion.bf.lexer

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CharacterStreamTests: StringSpec({
    "Peek with empty stream" {
        shouldThrow<Exception> {
            CharacterStream("").peek()
        }
    }

    "peek with populated stream" {
        CharacterStream("abcd").peek().shouldBe('a')
    }

    "Consecutive peeks on populated stream" {
        val stream = CharacterStream("ab")

        stream.peek()
        stream.take().shouldBe('a')
        stream.take().shouldBe('b')
    }

    "Check line number after \\n" {

        val stream = CharacterStream("\n")
        stream.take()
        stream.line.shouldBe(2)
        stream.position.shouldBe(1)
    }
})