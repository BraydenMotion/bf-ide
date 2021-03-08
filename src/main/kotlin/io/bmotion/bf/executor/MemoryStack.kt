package io.bmotion.bf.executor

class MemoryStack {

    val memory = arrayOf<Int>()
    var index = 0

    fun shiftLeft() = with (index--) {
        if (index < 0) throw Exception("Pointer out of bounds: $this")
    }

    fun shiftRight() = index++
    fun increment() = memory[index]++
    fun decrement() = memory[index]--
    fun value() = memory[index]
}