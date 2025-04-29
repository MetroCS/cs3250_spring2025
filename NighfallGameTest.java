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
     * In looking at the tests for Jotto, those tests are really testing the supporting methods
     * The only supporting methods present are getName() and private print utilities.
     * 
     * Is it worth to build in a Room supplier method for testing and file reading.
     * 
     */
}
