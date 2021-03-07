package io.bmotion.bf.parser

interface Instruction {
    val line: Int
    val pos: Int
    val value: String
}

class PointerInstruction(val direction: Operation, override val line: Int, override val pos: Int, override val value: String) : Instruction
class MemoryInstruction(val operation: Operation, override val line: Int, override val pos: Int, override val value: String) : Instruction
class SerialInstruction(val port: Operation, override val line: Int, override val pos: Int, override val value: String) : Instruction
class LoopInstruction(val list: ListInstruction, override val line: Int, override val pos: Int, override val value: String) : Instruction
class ListInstruction(val instructions: List<Instruction>, override val line: Int, override val pos: Int, override val value: String) : Instruction
class NoOpInstruction(override val line: Int, override val pos: Int, override val value: String) : Instruction

enum class Operation {
    FIRST, SECOND
}