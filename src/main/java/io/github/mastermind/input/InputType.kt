package io.github.mastermind.input

sealed class InputType(val label: String) {
    data class NumberOfGuesses(val value: Int = 0): InputType("number of guesses")
    data class PatternSize(val value: Int = 0): InputType("pattern size")
}

data class Input(val numberOfGuesses: InputType.NumberOfGuesses, val patternSize: InputType.PatternSize)