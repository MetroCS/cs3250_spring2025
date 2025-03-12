/**
 * Report timing of Util.fibon method.
 *
 * @author  Dr. Jody Paul
 * @version 20250312.0
 */
public class FibonTimer {
    /** Conversion factor from nanoseconds to milliseconds. */
    public static final double CONVERT_NANO_TO_MILLI = 1000000;
    /** Conversion factor from nanoseconds to seconds. */
    public static final double CONVERT_NANO_TO_UNIT = 1000000000;

    /** Increment for parameter values. */
    public static final int STEP_SIZE = 5;
    /** Ending value for paramter. */
    public static final int MAX_PARAM = 50;

    /**
     * Run Util.fibon through sequence of computations.
     * @param args ignored
     */
    public static void main(final String[] args) {
        Timer testTimer = new Timer();

        for (int i = 0; i <= MAX_PARAM; i += STEP_SIZE) {
            testTimer.start();
            Util.fibon(i);
            long duration = testTimer.stop();
            System.out.println("Util.fibon(" + i + "): "
                       + duration / CONVERT_NANO_TO_MILLI + " milliseconds, "
                       + duration / CONVERT_NANO_TO_UNIT + " seconds");
        }
    }
}
