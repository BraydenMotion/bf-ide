package io.bmotion.bf.executor

import io.bmotion.bf.parser.Operation
import io.bmotion.bf.parser.PointerInstruction
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PointerExecutorTests: StringSpec({
    "Execute < at index 0" {
        val instruction = PointerInstruction(Operation.FIRST, 1, 1, "<")

        shouldThrow<Exception> { PointerExecutor.execute(instruction, MemoryStack()) }
    }

    "Execute > at index 0" {
        val instruction = PointerInstruction(Operation.SECOND, 1, 1, ">")
        val memory = PointerExecutor.execute(instruction, MemoryStack())

        memory.index.shouldBe(1)
    }
})