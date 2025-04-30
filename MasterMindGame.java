import java.util.Optional;
import java.util.Random;

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

    private static final char[] VALID_CHARACTER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    private static final int CODE_LENGTH = 4;

    public String getName() { return "MasterMind"; }
    public Optional<Integer> play() {
        System.out.println("[Playing MasterMind - Placeholder]");
        return Optional.empty();
    }

    public String generateCode() {
        Random r = new Random(System.nanoTime());
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < CODE_LENGTH; i++) {
            out.append(VALID_CHARACTER[r.nextInt(10)]);
        }
        return out.toString();
    }
}
