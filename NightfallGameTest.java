import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class NightfallGameTest {
    @Test
    public void testGameNameCorrectness() {
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
     * identified word.
     * 3. C-1: Test that setting the state of the Room to value greater than 1
     * throws an exception (as this should not be possible with actions
     * later even if the debug state force method can).
     * 4. D-2: Test that the second description line contains an identified
     * word.
     */

    private Room A = new Room("A", "The keyword is framerate."),
            B = new Room("B", "Rise so high, in mud you lie."),
            C = new Room("C", "All your base are belong to us"),
            D = new Room("D", new String[] {
                    "He was born in the ash among the Velothi, anon Chimer...",
                    "The ending of the words is ALMSIVI." });

    @Test
    public void testRoomAContainsKeyword() {
        assertTrue(A.getDescription().contains("framerate"));
    }

    @Test
    public void testRoomBContainsKeyword() {
        assertFalse(B.getDescription().contains("Daro"));
    }

    @Test
    public void testRoomCContainsKeyword() {
        try {
            C.setState(1);
            assertEquals(1, 0);
        } catch (IndexOutOfBoundsException ie) {
            assertEquals(1, 1);
        }
    }

    @Test
    public void testRoomDContainsKeyword() {
        D.setState(1);
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
     * 3-7. Room V is connected to W, W to X, X to Y, Y to Z, Z to W, U to V.
     * 8-12. Room Pairs are not connected: V-X, W-Y, X-U, Y-V, Z-X, U-Z
     */

    Room U = new Room("Room U", "Longer description of Room U"),
            V = new Room("Room V", "Longer description of Room V"),
            W = new Room("Room W", "Longer description of Room W"),
            X = new Room("Room X", "Longer description of Room X"),
            Y = new Room("Room Y", "Longer description of Room Y"),
            Z = new Room("Room Z", "Longer description of Room Z");

    Doorway one = new Doorway(V, W),
            two = new Doorway(V, Y),
            three = new Doorway(W, X),
            four = new Doorway(W, Z),
            five = new Doorway(X, Y),
            six = new Doorway(X, Z),
            seven = new Doorway(Y, Z),
            eight = new Doorway(U, V);

    @Test
    public void testUHasOneDoorway() {
        assertEquals(1, U.getDoorwayCount());
    }
    // * 3-7. Room V to W, W to X, X to Y, Y to Z, Z to W, U to V.

    @Test
    public void testV_to_WConnection() {
        assertEquals(V.getAdjacentRoom(one), W);
    }

    @Test
    public void testW_to_XConnection() {
        assertEquals(W.getAdjacentRoom(three), X);
    }

    @Test
    public void testX_to_YConnection() {
        assertEquals(X.getAdjacentRoom(five), Y);
    }

    @Test
    public void testY_to_ZConnection() {
        assertEquals(Y.getAdjacentRoom(seven), Z);
    }

    @Test
    public void testZ_to_WConnection() {
        assertEquals(Z.getAdjacentRoom(four), W);
    }

    @Test
    public void testU_to_VConnection() {
        assertEquals(U.getAdjacentRoom(eight), V);
    }

    // * 8-12. Room Pairs are not connected: V-X, W-Y, X-U, Y-V, Z-X, U-Z

    @Test
    public void testV_to_XNOTConnected() {
        assertNotEquals(V.getAdjacentRoom(0), X);
    }

    @Test
    public void testW_to_YNOTConnected() {
        assertNotEquals(W.getAdjacentRoom(0), Y);
    }

    @Test
    public void testX_to_UNOTConnected() {
        assertNotEquals(X.getAdjacentRoom(0), U);
    }

    @Test
    public void testY_to_VNOTConnected() {
        assertNotEquals(Y.getAdjacentRoom(0), V);
    }

    @Test
    public void testZ_to_XNOTConnected() {
        assertNotEquals(Z.getAdjacentRoom(0), X);
    }

    @Test
    public void testU_to_ZNOTConnected() {
        assertNotEquals(U.getAdjacentRoom(0), Z);
    }

    /*
     * Input parsing: If pursued
     * "1" - int 1
     * "7" - int 7
     * "one" - Integer.MIN_VALUE
     * "0" - Integer.MIN_VALUE
     * "nightfall" - Integer.MIN_VALUE
     * "2 this should pass" - 2
     * "3 4 5" - 3
     */

    @Test
    public void testCorrectUserInputSmallValueA() {
        assertEquals(1, NightfallGame.parseUserInput("1"));
    }

    @Test
    public void testCorrectUserInputSmallValueB() {
        assertEquals(7, NightfallGame.parseUserInput("7"));
    }

    @Test
    public void testCorrectUserInputLargeValue() {
        assertEquals(7100, NightfallGame.parseUserInput("7100"));
    }

    @Test
    public void testIncorrectUserInputNumberWord() {
        assertEquals(Integer.MIN_VALUE, NightfallGame.parseUserInput("one"));
    }

    @Test
    public void testIncorrectUserInput_0() {
        assertEquals(Integer.MIN_VALUE, NightfallGame.parseUserInput("0"));
    }

    @Test
    public void testIncorrectUserInput_Nightfall() {
        assertEquals(Integer.MIN_VALUE, NightfallGame.parseUserInput("nightfall"));
    }

    @Test
    public void testCorrectUserInputTokensA() {
        assertEquals(2, NightfallGame.parseUserInput("2 this should pass"));
    }

    @Test
    public void testCorrectUserInputTokensB() {
        assertEquals(3, NightfallGame.parseUserInput("3 4 5"));
    }
}
