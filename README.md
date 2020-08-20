# Description
Master Mind is a game of code breaking invented in 1970 by Mordecai Meirowitz.

A “code maker” chooses a number pattern up to length six. Each number in the pattern can be an
integer from 1 through 6. A “code breaker” then tries to guess the pattern. Feedback is given
after each guess for the count of numbers that are correct and the count of numbers that are in
the correct position.

For example if the pattern is 1234 and the guess is 1552 the feedback
given would be: **“2 numbers correct, 1 number in correct position”**. More examples given
below.

The number of guesses is agreed to at the start of the game. The game continues until
the maximum number of guesses is made, or the “code breaker” guesses the pattern.

**Your assignment is to program the game of mastermind!**

The computer program will act as the “code maker” assigning a random number pattern.
Player (“code breaker”) will input guesses until game ends.
## Additional Requirements
1. Game should start by prompting player for pattern length and number of guesses.
    1. Pattern length is 3. Maximum number of guesses is 3.
1. The generated pattern shouldn’t contain repeated numbers.
1. Player will enter a guess, and appropriate feedback will be given regarding count of
numbers guessed correctly and count of numbers in correct position.
1. If guess input is not correct (invalid length, guess number out of range, etc) error
message should be returned to user.
    1. Error messages should be specific regarding what part of input which is incorrect.
1. “Code breaker” will continue to be prompted for input until:
    1. Maximum number of guesses has been entered OR
    1. Code breaker guesses correct number pattern.
1. When “code breaker” guesses correct number pattern message: “You broke the code in
X guesses!”
1. If “code breaker” cannot guess pattern in M max guesses message should be displayed:
“You were unable to break the code in M guesses. Code pattern is: P.”

## Examples:
* Pattern: 1234, Guess: 5555, Output: 0 correct, 0 correct position.
* Pattern: 1234, Guess: 4321, Output: 4 correct, 0 correct position.
* Pattern: 1234, Guess: 1324, Output: 4 correct, 2 correct position.
* Pattern: 1234, Guess: 1552, Output: 1 correct, 1 correct position.
* Pattern: 1234, Guess: 1334, Output: 3 correct, 3 correct position.
* Pattern: 1234, Guess: 1234, Output: 4 correct, 4 correct position.