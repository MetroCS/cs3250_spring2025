import java.util.Optional;

/**
 * A traditional Hangman game.
 * The player attempts to guess a secret word by guessing letters
 * one at a time.
 * Each incorrect guess reduces the number of remaining tries.
 * <pre>
 * Implements visual feedback (e.g., ASCII scaffold).
 * Handles duplicate guesses and win/loss conditions.
 * </pre>
 * @version 1
 */
class HangmanGame implements Game {
    public String getName() {
    return "Hangman";
}
    public Optional<Integer> play() {
      System.out.println("Welcome to Hangman!");
      System.out.println("Guess the hidden word one letter at a time.");
      System.out.println("Each wrong guess reduces your number of tries.");
        return Optional.empty();
    }
}
