package io.github.mastermind

import io.github.mastermind.guess.GuessService
import io.github.mastermind.input.InputFactory
import io.github.mastermind.pattern.PatternFactory

fun main() {

    val input = InputFactory.createInput()
    val pattern: Map<Int, Int> = PatternFactory.createPattern(input)

    val guessService = GuessService(input, pattern)
    println("******** ${guessService.execute()}")

}
