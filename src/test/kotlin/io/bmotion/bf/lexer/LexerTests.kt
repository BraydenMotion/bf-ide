package io.bmotion.bf.lexer

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.types.shouldBeTypeOf

class LexerTests : StringSpec({
    "Analysis with empty stream returns empty stream" {
        Lexer.process("").hasNext().shouldBeFalse()
    }

    "Analyze each token" {
        val stream = Lexer.process("<>+-.,[]a")
        stream.take().shouldBeTypeOf<OpenDiamondToken>()
        stream.take().shouldBeTypeOf<CloseDiamondToken>()
        stream.take().shouldBeTypeOf<PlusToken>()
        stream.take().shouldBeTypeOf<MinusToken>()
        stream.take().shouldBeTypeOf<PeriodToken>()
        stream.take().shouldBeTypeOf<CommaToken>()
        stream.take().shouldBeTypeOf<OpenSquareToken>()
        stream.take().shouldBeTypeOf<CloseSquareToken>()
        stream.take().shouldBeTypeOf<CharacterToken>()
    }
})