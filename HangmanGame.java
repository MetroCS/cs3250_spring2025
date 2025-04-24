import java.io.InputStream;
import java.util.*;

/**
 * A conventional Hangman game playable from the command line.
 * Implements:
 *  - 6 incorrect guesses → complete hangman (head, body, arms, legs)
 *  - Guess single letters or full word (costs 1 try on wrong)
 *  - Repeated or non-alphabetic guesses are ignored
 *  - Case-insensitive; words drawn from a small hardcoded list
 *  - play() returns Optional.of(score) where score = remaining tries on win, or 0 on loss
 */
public class HangmanGame implements Game {
    //─── config ────────────────────────────────────────────────────────────────
    private static final int MAX_TRIES = /* TODO: number of allowed wrong guesses */;
    private static final List<String> WORDS = Arrays.asList(
        /* TODO: your default word list */
    );
    private static final String[] HANGMAN_STATES = {
        /* TODO: ASCII art stages from 0..MAX_TRIES */
    };

    //─── instance state ────────────────────────────────────────────────────────
    private String secret;
    private final Set<Character> guessedLetters = new LinkedHashSet<>();
    private int remainingTries;
    private boolean fullWordGuessed;
    private final Scanner scanner;

    //─── constructors ────────────────────────────────────────────────────────────

    /** Default—uses System.in and picks a random word */
    public HangmanGame() {
        this(System.in, /* pick random from WORDS */);
    }

    /** For testing/injection; uses given InputStream & secret */
    public HangmanGame(InputStream in, String secret) {
        this.scanner = new Scanner(in);
        // TODO: validate and set secret
        // TODO: initialize remainingTries and fullWordGuessed
    }

    //─── Game interface ─────────────────────────────────────────────────────────

    /** The menu name for this game */
    @Override
    public String getName() {
        return "Hangman";
    }

    /**
     * Main loop: draw, prompt for guess, update state, repeat.
     * @return remainingTries if win, or 0 if loss
     */
    @Override
    public Optional<Integer> play() {
        // TODO: while not won/lost:
        //   - print HANGMAN_STATES[MAX_TRIES - remainingTries]
        //   - print getMaskedWord()
        //   - print guessedLetters
        //   - read line, decide letter vs word
        //   - call guessLetter(...) or guessWord(...)
        //   - print feedback
        // TODO: after loop, print final message and return score
        return Optional.empty();
    }

    //─── core logic ─────────────────────────────────────────────────────────────

    /** Attempt one letter; return true if correct, false otherwise */
    public boolean guessLetter(char ch) {
        // TODO: ensureActive()
        // TODO: normalize, ignore non-alpha & repeats
        // TODO: add to guessedLetters, adjust remainingTries
        return false;
    }

    /** Attempt full word; return true if correct, false otherwise */
    public boolean guessWord(String attempt) {
        // TODO: ensureActive()
        // TODO: trim/null check
        // TODO: if match → fullWordGuessed, else remainingTries--
        return false;
    }

    //─── state queries ──────────────────────────────────────────────────────────

    /** True once all letters revealed or full-word guessed */
    public boolean isWon() {
        // TODO: return true if fullWordGuessed or every letter in secret is guessed
        return false;
    }

    /** True if out of tries and not won */
    public boolean isLost() {
        // TODO: return remainingTries <= 0 && !isWon()
        return false;
    }

    /** Masked view, e.g. "_ p p _ e" */
    public String getMaskedWord() {
        // TODO: build string with '_' or revealed letter
        return "";
    }

    //─── test helpers ───────────────────────────────────────────────────────────

    /** For tests: letters guessed so far */
    Set<Character> getGuessedLetters() {
        // TODO
        return null;
    }

    /** For tests: how many tries remain */
    int getRemainingTries() {
        // TODO
        return 0;
    }

    /** For tests: set a custom secret word */
    void setSecretWord(String newSecret) {
        // TODO: validate alphabetic & non-empty, set secret
    }

    //─── internals ───────────────────────────────────────────────────────────────

    /** Throws if game already won or lost */
    private void ensureActive() {
        // TODO
    }
}
