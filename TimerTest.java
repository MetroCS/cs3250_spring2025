import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * Tests for class Timer
 *
 * @author  Dr. Jody Paul
 * @version 20250312.0
 */
public class TimerTest {
    /** Test assignment statement. */
    @Test
    public void assignmentTest() {
        Timer testTimer = new Timer();
        int variable;
        testTimer.start();
        variable = 245126;
        long duration = testTimer.stop();
        assertTrue(duration > 0L);
    }
}
