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

    /** Specific test value. */
    public static final int TEST_VALUE = 24;

    /** Number of initializations to run. */
    public static final int NUM_INITS = 10;
    /** Increment for general parameter values. */
    public static final int STEP_SIZE_COARSE = 5;
    /** Ending value for regular-size paramter. */
    public static final int MAX_REG_PARAM = 50;
    /** Increment for large-size parameter values. */
    public static final int STEP_SIZE_FINE = 1;
    /** Starting value for large-size paramter. */
    public static final int START_LARGE_PARAM = 60;
    /** Ending value for large-size paramter. */
    public static final int MAX_LARGE_PARAM = 120;

    /** Instance of Timer for measuring performance. */
    private static Timer testTimer = new Timer();

    /**
     * Run Util.fibon through sequence of computations.
     * @param args ignored
     */
    public static void main(final String[] args) {
        single(TEST_VALUE);
        sequence();
        largeParameters();
    }

    /**
     * Show run of Util.fibon for a specific parameter.
     * @param parm the parameter to pass to Util.fibon
     */
    public static void single(final int parm) {
            testTimer.start();
            Util.fibon(parm);
            long duration = testTimer.stop();
            System.out.println("Util.fibon(" + parm + "): "
                       + duration / CONVERT_NANO_TO_MILLI + " milliseconds, "
                       + duration / CONVERT_NANO_TO_UNIT + " seconds");
    }

    /**
     * Show runs of Util.fibon through regular sequence of computations.
     */
    public static void sequence() {
        for (int i = 0; i <= MAX_REG_PARAM; i += STEP_SIZE_COARSE) {
            testTimer.start();
            Util.fibon(i);
            long duration = testTimer.stop();
            System.out.println("Util.fibon(" + i + "): "
                       + duration / CONVERT_NANO_TO_MILLI + " milliseconds, "
                       + duration / CONVERT_NANO_TO_UNIT + " seconds");
        }
    }

    /**
     * Show multiple runs of Util.fibon for larger parameters.
     */
    public static void largeParameters() {
        for (int i = START_LARGE_PARAM;
             i <= MAX_LARGE_PARAM;
             i += STEP_SIZE_COARSE) {
            testTimer.start();
            Util.fibon(i);
            long duration = testTimer.stop();
            System.out.println("Util.fibon(" + i + "): "
                       + duration / CONVERT_NANO_TO_MILLI + " milliseconds, "
                       + duration / CONVERT_NANO_TO_UNIT + " seconds");
        }
    }

}
