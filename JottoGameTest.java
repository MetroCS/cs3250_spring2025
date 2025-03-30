import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

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

    // Reflection-based access since countMatchingLetters is private
    private int gameTestHelper_countMatchingLetters(JottoGame game,
						    String secret,
						    String guess) {
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
