import java.util.Optional;

/**
 * A word guessing game similar to Wordle.
 * The player has a limited number of attempts to guess a secret
 * 5-letter word.
 * After each guess, the game indicates whether the guess is correct.
 * <br />
 * The score is determined by how many attempts the player had remaining
 *   when they guessed the word correctly.
 * @version 1
 */
class WordGuessGame implements Game {

    /**
     * Returns the name of the game.
     * @return the name "Word Guess"
     */
    public String getName()
    {
        return "Word Guess";
    }

    public Optional<Integer> play() {
        System.out.println(
            "[Playing Word Guess - You will have a limited number of attempts"
            + " to guess a secret 5 letter word.]"
        );
        System.out.println(
            "After each guess, the game will indicate whether the guess is"
            + " correct."
        );
        System.out.println(
            "Your score is determined by the number of attempts remaining"
            + " after you guessed the word correctly!"
        );
        return Optional.empty();
    }
}
