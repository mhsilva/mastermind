package io.github.mastermind.guess

import io.github.mastermind.input.Input
import java.util.stream.Collectors

class GuessService(private val input: Input, private val pattern: Map<Int, Int>) {

    private var correctNumber = 0
    private var correctPosition = 0

    companion object {
        var guessNumber = 1
    }

    fun execute(): String {

        guessNumber++

        val guess = readAndValidateGuess()
        evaluateGuess(guess)

        return when {
            correctPosition == input.patternSize -> "You broke the code in ${input.numberOfGuesses}"
            guessNumber <= input.numberOfGuesses -> execute()
            else -> "You were unable to break the code in ${input.numberOfGuesses} guesses"
        }
    }

    private fun evaluateGuess(guess: List<Int>) {
        for (i in 0 until guess.size) {
            val element = guess[i]
            val indexOfElementInPattern = pattern[element]

            if (indexOfElementInPattern != null) {
                this.correctNumber++
                if (i == indexOfElementInPattern)
                    this.correctPosition++
            }
        }
    }

    private fun readAndValidateGuess(): List<Int> {

        println("Make your guess: ")
        val guess = readLine()
        try {
            validateGuess(guess)
        } catch (e: RuntimeException) {
            println(e.message)
            return readAndValidateGuess()
        }
        return transformGuess(guess!!)
    }

    private fun transformGuess(guess: String): List<Int> {
        return guess.toList().stream().map { it.toInt() }.collect(Collectors.toList())
    }

    private fun validateGuess(guess: String?): String {
        when {
            guess == null -> throw RuntimeException("Guess cannot be null or empty")
            guess.length != this.input.patternSize -> throw RuntimeException("Invalid length for guess")
            guess.contains("\\D") -> throw RuntimeException("Guess must contain only numbers")
        }
        return guess!!
    }
}