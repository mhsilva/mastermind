package io.github.mastermind

import io.github.mastermind.guess.GuessService
import io.github.mastermind.input.createInput
import io.github.mastermind.pattern.createPattern

fun main() {

    val input = createInput()
    val guessService = GuessService(input, createPattern(input.patternSize))
    println("******** ${guessService.execute()}")
}
