package io.github.mastermind.input

fun createInput(): Input {
    return Input(
        readAndValidate(InputType.NumberOfGuesses()) as InputType.NumberOfGuesses,
        readAndValidate(InputType.PatternSize()) as InputType.PatternSize
    )
}

private fun readAndValidate(inputType: InputType): InputType {
    println("Type the ${inputType.label}: ")
    val value = readLine()?.toIntOrNull()
    return when (val result = validation(value, inputType)) {
        is InputValidationResult.Error -> handleError(result.message, inputType)
        is InputValidationResult.Success -> handleSuccess(inputType, value)
    }
}

fun handleSuccess(inputType: InputType, value: Int?): InputType {
    value ?: error("Value cannot be empty at this point!!!")
    return when (inputType) {
        is InputType.PatternSize -> InputType.PatternSize(value)
        is InputType.NumberOfGuesses -> InputType.NumberOfGuesses(value)
    }
}

fun handleError(message: String, inputType: InputType): InputType {
    println(message)
    return readAndValidate(inputType)
}

private fun validation(value: Int?, inputType: InputType) =
    when {
        value == null -> InputValidationResult.Error("${inputType.label.capitalize()} cannot be null")
        inputType is InputType.PatternSize && (value > 6 || value == 0) -> InputValidationResult.Error("Invalid input for ${inputType.label}")
        else -> InputValidationResult.Success
    }

sealed class InputValidationResult {
    object Success : InputValidationResult()
    data class Error(val message: String) : InputValidationResult()
}