package io.github.mastermind.pattern

import io.github.mastermind.input.InputType

fun createPattern(inputType: InputType.PatternSize): Map<Int, Int> {
    return inputType.value.let {
        // Create a list of 1 to 6, shuffle and get the first N numbers
        // Group the first N numbers into a Map where the value is the index.
        val pattern = (1..6)
            .shuffled()
            .take(it)
        pattern.map { element -> element to pattern.indexOf(element) }.toMap()
    }
}
