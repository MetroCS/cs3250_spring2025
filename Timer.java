/**
 * A timer for performance investigations.
 * @author Dr. Jody Paul
 * @version 20250312.0
 */
public class Timer {
    /** State: system time at start. */
    private static long startTimeNano = 0L;
    /** State: system time at end. */
    private static long endTimeNano = 0L;
    /** Computation: duration between start and end. */
    private static long durationNano = 0L;

    /**
     * Initialize and Start the Timer.
     * @return the system time at start
     */
    public final long start() {
        return startTimeNano = System.nanoTime();
    }

    /**
     * Return current elapsed time.
     * @return difference between current time and start time
     */
    public final long elapsed() {
        return System.nanoTime() - startTimeNano;
    }

    /**
     * Return duration between start and end time.
     * @return difference between end time and start time
     */
    public final long duration() {
        if (durationNano == 0) {
	    durationNano = endTimeNano - startTimeNano;
	}
        return durationNano;
    }

    /**
     * Stop timer and compute duration.
     * @return duration between start and end time
     */
    public final long stop() {
        endTimeNano = System.nanoTime();
        return durationNano = endTimeNano - startTimeNano;
    }
}
