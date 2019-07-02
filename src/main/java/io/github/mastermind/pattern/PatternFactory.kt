package io.github.mastermind.pattern

import io.github.mastermind.input.Input
import java.util.stream.Collectors
import java.util.stream.IntStream

class PatternFactory {

    companion object {

        fun createPattern(input: Input): Map<Int, Int> {
            val patternSize = input.patternSize.toLong()

            // Create a list of 1 to 6, shuffle and get the first N numbers
            val pattern = (1..6)
                .shuffled()
                .stream()
                .limit(patternSize)
                .collect(Collectors.toList())

            // Group the first N numbers into a Map where the value is the index.
            return IntStream.range(0, pattern.size)
                .boxed()
                .collect(Collectors.toList())
                .map { pattern[it] to it }
                .toMap()
        }
    }
}
