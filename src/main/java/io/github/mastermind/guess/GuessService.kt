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
        correctNumber = 0
        correctPosition = 0
        guessNumber++

        evaluateGuess(readAndValidateGuess())
        println("Correct number $correctNumber; Correct position $correctPosition")

        return when {
            correctPosition == input.patternSize -> "You broke the code in ${guessNumber-1} guesses"
            guessNumber <= input.numberOfGuesses -> execute()
            else -> "You were unable to break the code in ${input.numberOfGuesses} guesses." +
                    " The code is: ${pattern.keys.stream().map { it.toString() }.collect(Collectors.joining(""))}"
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
        return guess.toCharArray().map { it.toString().toInt() }.toList()
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
