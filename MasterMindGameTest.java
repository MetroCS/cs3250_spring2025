import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;

public class MasterMindGameTest {

    private MasterMindGame mm;
    private static final char[] VALID_CHARACTER =
    {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};


    @BeforeEach
    public void setUp() {
        mm = new MasterMindGame();
    }

    @Test
    public void testCodeLengthCorrectness() {
        assertEquals(4, mm.generateCode().length());
    }

    @Test
    public void testCodeContentCorrectness() {
        int count = 100;
        String[] codes = new String[count];
        for (int i = 0; i < count; i++) {
            codes[i] = mm.generateCode();
        }

        assertEquals(0, checkIncorrectCharacters(codes));
    }

    private static int checkIncorrectCharacters(final String[] in) {
        int out = 0;
        for (int i = 0; i < in.length; i++) {
            for (int j = 0; j < in[i].length(); j++) {
                if (!contains(VALID_CHARACTER, in[i].charAt(j))) {
                    out++;
                }
            }
        }
        return out;
    }

    private static boolean contains(final char[] bank, final char key) {
        for (char b: bank) {
            if (b == key) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void testCodeRepetition() {
        /*
         * I was curious as to the odds of an overlap, and before spending ages
         * rerunning "ant test" to no avail, I checked via Wolfram Alpha.
         * It appears that 10,000 choose 100 (100 codes being checked, and 
         * values from 0000 to 9999) is on the order of 6 * 10^241. Code
         * collisions are very unlikely given that many possible unique
         * combinations.
         */
        int count = 100;
        HashSet<String> codes = new HashSet<>(count);
        for (int i = 0; i < count; i++) {
            codes.add(mm.generateCode());
        }

        // Commented below after ensuring code equality check is correct
        // printCodes(codes);
        assertEquals(count, codes.size());
    }

    private static void printCodes(final HashSet<String> in) {
        for (String i: in) {
            System.out.println(i);
        }
    }
}
