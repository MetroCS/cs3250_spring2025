import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
            for (int j = 0; j < 4; j++) {
                if (contains(VALID_CHARACTER, in[i].charAt(j))) {
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
        int count = 100;
        String[] codes = new String[count];
        for (int i = 0; i < count; i++) {
            codes[i] = mm.generateCode();
        }

        // Commented below after ensuring code equality check is correct
        printCodes(codes);
        assertEquals(0, checkMatches(codes));
    }

    private static void printCodes(final String[] in) {
        for (String i: in) {
            System.out.println(i);
        }
    }

    private static int checkMatches(final String[] in) {
        int out = 0;
        for (int i = 0; i < in.length - 1; i++) {
            for (int j = 1; j < in.length; j++) {
                //Does this comparison actually test code equality
                if (in[i].equals(in[j])) {
                    out++;
                }
            }
        }
        return out;
    }
}
