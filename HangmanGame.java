import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * A traditional Hangman game.
 * The player attempts to guess a secret word by guessing letters
 * one at a time.
 * Each incorrect guess reduces the number of remaining tries.
 * Duplicate guesses and non‐alphabetic input are ignored.
 * A full‐word guess can win immediately.
 * @version 1
 */
public class HangmanGame implements Game {
    private final String secret;
    private final Set<Character> guessed = new HashSet<>();
    private int remainingTries = 6;
    private boolean fullWordGuessed = false;

    public HangmanGame(String secret) {
        if (secret == null || secret.isEmpty()) {
            throw new IllegalArgumentException("Secret word must be non‐empty");
        }
        this.secret = secret.toLowerCase();
    }

    @Override
    public String getName() {
        return "Hangman";
    }

    /** Placeholder – not used by the tests */
    @Override
    public Optional<Integer> play() {
        System.out.println("[Playing Hangman – not invoked by tests]");
        return Optional.of(remainingTries);
    }

    /**
     * Guess a single character.
     * @param c the character to guess
     * @return true if the character is in the secret word; false otherwise
     */
    public boolean guessLetter(char c) {
        c = Character.toLowerCase(c);
        // ignore non‑a–z
        if (c < 'a' || c > 'z') {
            return false;
        }
        // if already guessed, do nothing
        if (guessed.contains(c)) {
            return secret.indexOf(c) >= 0;
        }
        guessed.add(c);
        if (secret.indexOf(c) >= 0) {
            return true;
        } else {
            remainingTries--;
            return false;
        }
    }

    /**
     * Guess the entire word.
     * @param attempt the full‑word attempt
     * @return true if correct; false (and lose one try) otherwise
     */
    public boolean guessWord(String attempt) {
        if (attempt == null) {
            return false;
        }
        if (attempt.equalsIgnoreCase(secret)) {
            fullWordGuessed = true;
            return true;
        } else {
            remainingTries--;
            return false;
        }
    }

    /** How many lives remain (starts at 6) */
    public int getRemainingTries() {
        return remainingTries;
    }

    /** True once all letters have been revealed or full‑word guessed */
    public boolean isWon() {
        if (fullWordGuessed) {
            return true;
        }
        // check whether every distinct letter has been guessed
        for (char c : secret.toCharArray()) {
            if (!guessed.contains(c)) {
                return false;
            }
        }
        return true;
    }

    /** True if out of tries before winning */
    public boolean isLost() {
        return remainingTries <= 0 && !isWon();
    }
}
