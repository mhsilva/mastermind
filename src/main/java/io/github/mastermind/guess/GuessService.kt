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
            correctPosition == input.patternSize.value -> "You broke the code in ${guessNumber - 1} guesses"
            guessNumber <= input.numberOfGuesses.value -> execute()
            else -> "You were unable to break the code in ${input.numberOfGuesses} guesses." +
                    " The code is: ${pattern.keys.stream().map { it.toString() }.collect(Collectors.joining(""))}"
        }
    }

    private fun evaluateGuess(formattedGuess: List<Int>) {
        for (elementIndex in formattedGuess.indices) {
            val element = formattedGuess[elementIndex]
            val indexOfElementInPattern = pattern[element] ?: continue
            this.correctNumber++
            if (elementIndex == indexOfElementInPattern)
                this.correctPosition++
        }
    }

    private fun readAndValidateGuess(): List<Int> {
        println("Make your guess: ")
        return when (val guess = validateGuess((readLine()))) {
            is Guess.Error -> handleGuessInputError(guess)
            is Guess.Success -> transformGuess(guess)
        }
    }

    private fun handleGuessInputError(guess: Guess.Error): List<Int> {
        println(guess.message)
        return readAndValidateGuess()
    }

    private fun validateGuess(inputGuess: String?): Guess =
        when {
            inputGuess == null -> Guess.Error("Guess cannot be null or empty")
            inputGuess.length != this.input.patternSize.value -> Guess.Error("Invalid length for guess")
            inputGuess.contains("\\D") -> Guess.Error("Guess must contain only numbers")
            else -> Guess.Success(inputGuess)
        }

    private fun transformGuess(guess: Guess.Success): List<Int> =
        guess.value.toCharArray().map { it.toString().toInt() }

    sealed class Guess {
        data class Success(val value: String) : Guess()
        data class Error(val message: String) : Guess()
    }
}
