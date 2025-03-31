import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

/**
 * Tests for JottoGame.
 * @version 1
 */
public class JottoGameTest {

    @Test
    public void testCountMatchingLetters_withNoMatches() {
        JottoGame game = new JottoGame();
        int matches = gameTestHelper_countMatchingLetters(game, "BRICK", "ZOOEY");
        assertEquals(0, matches);
    }

    @Test
    public void testCountMatchingLetters_withSomeMatches() {
        JottoGame game = new JottoGame();
        int matches = gameTestHelper_countMatchingLetters(game, "BRICK", "CRANE");
        assertEquals(2, matches); // C and R
    }

    @Test
    public void testCountMatchingLetters_withAllMatches() {
        JottoGame game = new JottoGame();
        int matches = gameTestHelper_countMatchingLetters(game, "BRICK", "BRICK");
        assertEquals(5, matches);
    }

    /**
     * Reflection-based access since countMatchingLetters is private.
     * @param game the Jotto game under test
     * @param secret the secret word
     * @param guess the player's guess word
     * @return the value returned by the game given the secret and guess
     *          or -1 if the reflection failed
     */
    private int gameTestHelper_countMatchingLetters(final JottoGame game,
                                                    final String secret,
                                                    final String guess) {
        try {
            var method = JottoGame.class.getDeclaredMethod("countMatchingLetters",
                                                           String.class,
                                                           String.class);
            method.setAccessible(true);
            return (int) method.invoke(game, secret, guess);
        } catch (Exception e) {
            fail("Reflection failed: " + e.getMessage());
            return -1;
        }
    }
}
