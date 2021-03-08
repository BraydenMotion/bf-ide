package io.bmotion.bf.executor

import io.bmotion.bf.parser.Instruction
import io.bmotion.bf.parser.Operation
import io.bmotion.bf.parser.PointerInstruction

interface Executor {
    fun execute(instruction: Instruction, memoryStack: MemoryStack): MemoryStack
}

object PointerExecutor: Executor {
    override fun execute(instruction: Instruction, memoryStack: MemoryStack): MemoryStack {
        if (instruction !is PointerInstruction)
            throw Exception("Expected pointer instruction")

        when (instruction.direction) {
            Operation.FIRST -> memoryStack.shiftLeft()
            Operation.SECOND -> memoryStack.shiftRight()
        }

        return memoryStack
    }
}