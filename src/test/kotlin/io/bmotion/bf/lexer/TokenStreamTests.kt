package io.bmotion.bf.lexer

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.types.shouldBeTypeOf

class TokenStreamTests: StringSpec({
    "Peek with empty stream" {
        shouldThrow<Exception> {
            TokenStream(emptyList()).peek()
        }
    }

    "peek with populated stream" {
        TokenStream(listOf(PeriodToken(1,0,"."))).peek().shouldBeTypeOf<PeriodToken>()
    }

    "Consecutive peeks on populated stream" {
        val stream = TokenStream(listOf(PeriodToken(1, 0, "."),CommaToken(1, 1, ",")))

        stream.peek()
        stream.take().shouldBeTypeOf<PeriodToken>()
        stream.take().shouldBeTypeOf<CommaToken>()
    }
})