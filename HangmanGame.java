import java.util.Optional;

/**
 * A traditional Hangman game.
 * The player attempts to guess a secret word by guessing letters one at a time.
 * Each incorrect guess reduces the number of remaining tries.
 *
 * Implement visual feedback (e.g., ASCII scaffold).
 * Handle duplicate guesses and win/loss conditions.
 */
class HangmanGame implements Game {
    public String getName() { return "Hangman"; }
    public Optional<Integer> play() {
        System.out.println("[Playing Hangman - Placeholder]");
        return Optional.empty();
    }
}
