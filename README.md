# [Brainfuck](https://en.wikipedia.org/wiki/Brainfuck)

## Summary

This project is a prerequisite to developing my own programming language. I chose brainfuck because of it's simplicity
as it won't take much time to build a compiler and editor. The overall structure is over-complicated for what the
language is, but this is to build a base for more complex languages.

## Features

### Compiler

- [x] Lexer
- [x] parser
- [ ] Event system
- [ ] Executor

## Language Details

Brainfuck consists of 8 instructions that affect an array of integers.

### Instructions

| Token |                              Instruction                              |
|:-----:|:---------------------------------------------------------------------:|
|   \<   |                     Shift the pointer to the left                     |
|   \>   |                     Shift the pointer to the left                     |
|   +   |              Increment the value in memory at the pointer             |
|   -   |              Decrement the value in memory at the pointer             |
|   .   |         Output the integer at the pointer as a UTF-8 character        |
|   ,   |      Accept an integer input and write it to the current pointer      |
|   [   | Run instructions until ] if pointer is greater that 0, else skip to ] |
|   ]   |                         Jump to start of loop                         |

### Example

```bf
++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++.
```