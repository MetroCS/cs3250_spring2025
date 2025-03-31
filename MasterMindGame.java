import java.util.Optional;

/**
 * A code-breaking game where the app selects a sequence of symbols, and
 * the player tries to guess the sequence within a fixed number of attempts.
 *
 * Feedback for each guess indicates how many values are correct and in
 * the correct position and how many are correct but in the wrong position.
 *
 * Consider using arrays or strings to store and compare codes.
 * @version 1
 */
class MasterMindGame implements Game {
    public String getName() { return "MasterMind"; }
    public Optional<Integer> play() {
        System.out.println("[Playing MasterMind - Placeholder]");
        return Optional.empty();
    }
}
