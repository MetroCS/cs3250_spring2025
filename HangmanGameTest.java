import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HangmanGameTest {
    private HangmanGame game;

    @BeforeEach
    void setUp() {
        // supply an empty InputStream since we won't call play()
        game = new HangmanGame(new ByteArrayInputStream(new byte[0]), "apple");
    }

    @Test
    void testWinningByLetters() {
        assertFalse(game.isWon());
        // guess each distinct letter in "apple"
        assertTrue(game.guessLetter('a'));
        assertTrue(game.guessLetter('p'));
        assertTrue(game.guessLetter('l'));
        assertTrue(game.guessLetter('e'));
        assertTrue(game.isWon(), "All letters guessed, should be won");
        assertFalse(game.isLost(), "Won games are not lost");
        // no wrong guesses, so remainingTries is still max (6)
        assertEquals(6, game.getRemainingTries());
    }

    @Test
    void testWinningByFullWord() {
        assertFalse(game.isWon());
        int before = game.getRemainingTries();
        assertTrue(game.guessWord("ApPlE"), "Full-word guess should be case-insensitive");
        assertTrue(game.isWon());
        assertEquals(before, game.getRemainingTries(), "Correct full-word guess should not cost a try");
    }

    @Test
    void testLosingAfterSixIncorrectGuesses() {
        // pick six letters that are not in "apple"
        char[] wrongs = { 'b','c','d','f','g','h' };
        for (int i = 0; i < wrongs.length; i++) {
            boolean result = game.guessLetter(wrongs[i]);
            assertFalse(result, "Guess " + wrongs[i] + " should be wrong");
            assertEquals(6 - (i + 1), game.getRemainingTries(),
                         "After " + (i+1) + " wrongs, tries should decrement");
        }
        assertTrue(game.isLost(), "After 6 wrong guesses, game must be lost");
        assertFalse(game.isWon(), "Lost games are not won");
        assertEquals(0, game.getRemainingTries());
    }

    @Test
    void testRepeatedLetterGuessesDoNotCostTries() {
        int start = game.getRemainingTries();
        assertFalse(game.guessLetter('z'), "First guess of 'z' is wrong");
        assertEquals(start - 1, game.getRemainingTries());
        assertFalse(game.guessLetter('z'), "Repeated guess of 'z' should be ignored");
        assertEquals(start - 1, game.getRemainingTries(),
                     "Remaining tries should not change on repeated guess");
    }

    @Test
    void testInvalidInputIsIgnored() {
        int start = game.getRemainingTries();
        assertFalse(game.guessLetter('*'), "Non-alphabetic guess should be ignored");
        assertEquals(start, game.getRemainingTries());
        assertFalse(game.guessWord("   "), "Blank full-word guess should be ignored");
        assertEquals(start, game.getRemainingTries());
    }

    @Test
    void testWrongFullWordCostsOneTry() {
        int start = game.getRemainingTries();
        assertFalse(game.guessWord("wrongword"), "Incorrect full-word guess should be wrong");
        assertEquals(start - 1, game.getRemainingTries(),
                     "Incorrect full-word guess should cost one try");
    }

    @Test
    void testCannotGuessAfterWinOrLoss() {
        // win by full-word
        assertTrue(game.guessWord("apple"));
        assertThrows(IllegalStateException.class, () -> game.guessLetter('x'));
        assertThrows(IllegalStateException.class, () -> game.guessWord("banana"));

        // reset to new game and lose
        game = new HangmanGame(new ByteArrayInputStream(new byte[0]), "apple");
        for (char c : new char[]{'b','c','d','f','g','h'}) {
            game.guessLetter(c);
        }
        assertTrue(game.isLost());
        assertThrows(IllegalStateException.class, () -> game.guessLetter('x'));
        assertThrows(IllegalStateException.class, () -> game.guessWord("apple"));
    }

    @Test
    void testGetMaskedWordProgress() {
        game.setSecretWord("dog");
        // none guessed
        assertEquals("_ _ _", game.getMaskedWord());
        game.guessLetter('d');
        assertEquals("d _ _", game.getMaskedWord());
        game.guessLetter('G');
        assertEquals("d _ g", game.getMaskedWord(), "Case-insensitive reveal");
    }
}
