import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
     */
    
    private Room A = new Room("A", "The keyword is framerate."),
            B = new Room("B", "Rise so high, in mud you lie."),
            C = new Room("C", "All your base are belong to us"),
            D = new Room("D", "The ending of the words is ALMSIVI.");
    

    @Test
    public void testRoomAContainsKeyword()
    {
        assertTrue(A.getDescription().contains("framerate"));
    }

    @Test
    public void testRoomBContainsKeyword()
    {
        assertTrue(B.getDescription().contains("mud"));
    }

    @Test
    public void testRoomCContainsKeyword()
    {
        assertTrue(C.getDescription().contains("belong"));
    }

    @Test
    public void testRoomDContainsKeyword()
    {
        assertTrue(D.getDescription().contains("ALMSIVI"));
    }
    
     /* 
     * Room Connection Tests
     * Setup. Create 5 Rooms lettered U-Z, each of which will have some
     * connections between them. These will mirror the five room setup I sent
     * a couple weeks back (with U tacked on to give a single doorway). For
     * simplicity, descriptions will be "Room " followed by the Room's letter.
     * 
     * Doorways will then connect Rooms as follows:
     * V-W, V-Y, W-X, W-Z, X-Y, X-Z, Y-Z, U-V
     * 
     * 1. U contains exactly 1 Doorway
     * 2. U has Doorway to V
     * 3-7. Room V is connected to W, W to X, X to Y, Y to Z, Z to W.
     * 8-12. Room Pairs are not connected: V-X, W-Y, X-U, Y-V, Z-X, U-Z
     * 
     * 
     * Input parsing: If pursued
     * "1" - int 1
     * "7" - int 7
     * "one" - Integer.MIN_VALUE
     * "0" - Integer.MIN_VALUE
     * "nightfall" - Integer.MIN_VALUE
     * 
     * 
     */
}
