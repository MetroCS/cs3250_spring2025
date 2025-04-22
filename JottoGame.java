import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.Optional;

/**
 * A logic-based word guessing game where the player must deduce a secret
 *  5-letter word.
 * <pre>
 * In Jotto, after each guess, the player is told how many letters
 *   in their guess match the secret word, regardless of position.
 * For example, if the secret word is "BRICK" and the player guesses
 *   "CRANE", the feedback would be "2 letters match"
 *   (because of the R and C).
 *
 * Scoring Recommendation:
 * - You may give the player a fixed number of guesses (e.g., 10).
 * - A score could be calculated as (guesses remaining) or
 *     (max guesses - actual guesses used).
 * - You could also assign a bonus if they guess the word early or
 *     penalize for inefficient guessing.
 *
 * Feedback Advice:
 * - Ensure the feedback count does not double-count repeated letters.
 *   For example, if the secret word has only one 'C' and the guess has two
 *     count only one.
 *
 * Implementation Tips:
 * - Keep a set or multiset of letters in the secret word to track
 *     matches efficiently.
 * - Validate user input to be 5 letters and alphabetic only.
 * - Use consistent casing (e.g., convert all to uppercase).
 * - Use Optional<Integer> for scoring to integrate with the game
 *     history tracker.
 * </pre>
 * @version 1
 */
public class JottoGame implements Game {
    /** Hard-coded secret word. Replace with randomized word from list. */
    private static final String SECRET_WORD = "BRICK";
    /** Number of letters in a word. */
    private static final int WORD_LENGTH = 5;
    /** Maximum number of guesses allowed. */
    private static final int MAX_GUESSES = 10;
    /** Console input. */
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String getName() {
        return "Jotto";
    }

    @Override
    public Optional<Integer> play() {
        System.out.println("[Jotto] Guess a secret word of "
                           + WORD_LENGTH + " letters. "
                           + System.lineSeparator()
                           + "You will be shown how many letters matched."
                           + System.lineSeparator()
                           + "Ex: \"BRICK\" matches \"BUILD\" by two "
                           + "letters: \"B\" and \"I\". ");
        int attemptsLeft = MAX_GUESSES;

        while (attemptsLeft > 0) {
            System.out.print("Enter guess: ");
            String guess = scanner.nextLine().trim().toUpperCase();

            if (guess.length() != WORD_LENGTH || !guess.matches("[A-Z]+")) {
                System.out.println("Invalid input. "
                                   + "Please enter a "
                                   + WORD_LENGTH + "-letter word "
                                   + "with alphabetic characters only.");
                continue;
            }

            if (guess.equals(SECRET_WORD)) {
                System.out.println("Congratulations! You guessed the word.");
                return Optional.of(attemptsLeft);
            } else {
                int matches = countMatchingLetters(SECRET_WORD, guess);
                System.out.println("Letters in common: " + matches);
                attemptsLeft--;
            }
        }

        System.out.println("You ran out of guesses. "
                           + "The word was: " + SECRET_WORD);
        return Optional.of(0);
    }

    /**
     * Counts how many unique letters in the guess are also in the secret word.
     * Each letter is only counted once, even if repeated.
     *
     * @param secret the secret word
     * @param guess the player's guess
     * @return number of matching letters
     */
    private int countMatchingLetters(final String secret, final String guess) {
        Set<Character> secretLetters = new HashSet<>();
        for (char c : secret.toCharArray()) {
            secretLetters.add(c);
        }

        Set<Character> guessLetters = new HashSet<>();
        for (char c : guess.toCharArray()) {
            guessLetters.add(c);
        }

        guessLetters.retainAll(secretLetters);
        return guessLetters.size();
    }
}
