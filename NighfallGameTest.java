import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

public class NighfallGameTest
{
    @Test
    public void testGameNameCorrectness()
    {
        Game nf = new NightfallGame();
        String output = nf.getName();
        assertEquals(output, "Nightfall");
    }

    /*
     * What tests to write
     * 
     * In looking at the tests for Jotto, those tests are really testing the
     * supporting methods The only supporting methods present are getName() and
     * private print utilities.
     * 
     * Room Description Tests
     * Setup. Create 4 Rooms which will be lettered A-D, each will have the
     * number of description lines defined at the start of each test.
     * 1. A-1: Test that the first description line contains an identified word.
     * 2. B-1: Test that the first description line does not contain an
     *      identified word.
     * 3. C-1: Test that setting the state of the Room to value greater than 1
     *      throws an exception (as this should not be possible with actions 
     *      later even if the debug state force method can).
     * 4. D-2: Test that the second description line contains an identified
     *      word.
     * 
     * 
     * Room Connection Tests
     * Setup. Create 5 Rooms lettered V-Z, each of which will have some
     * connections between them. These will mirror the five room setup I sent
     * a couple weeks back. For simplicity, descriptions will be "Room "
     * followed by the Room's letter.
     * 
     * Doorways will then connect Rooms as follows:
     * V-W, V-Y, W-X, W-Z, X-Y, X-Z, Y-Z
     * 
     * 
     * 
     */
}
