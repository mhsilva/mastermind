package io.github.mastermind.input

import java.lang.RuntimeException

class InputFactory {

    companion object {
        fun createInput(): Input {

            return Input(
                readAndValidate(InputFactoryEnum.NUMBER_OF_GUESSES),
                readAndValidate(InputFactoryEnum.PATTERN_SIZE)
            )
        }

        private fun readAndValidate(inputFactoryEnum: InputFactoryEnum): Int {

            println("Type the ${inputFactoryEnum.value}: ")
            val value: Int? = readLine()?.toIntOrNull()
            try {
                validation(value, inputFactoryEnum)
            } catch (e: RuntimeException) {
                println(e.message)
                return readAndValidate(inputFactoryEnum)
            }

            return value!!
        }

        private fun validation(value: Int?, inputFactoryEnum: InputFactoryEnum) {
            if (value == null)
                throw RuntimeException("${inputFactoryEnum.value.capitalize()} cannot be null")
            else if (inputFactoryEnum == InputFactoryEnum.PATTERN_SIZE && (value > 6 || value == 0))
                throw RuntimeException("Invalid input for ${inputFactoryEnum.value}")
        }
    }
}