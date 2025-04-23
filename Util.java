import java.util.concurrent.atomic.AtomicInteger;

public class Util
{
    private static AtomicInteger ID_COUNTER;

    static
    {
        ID_COUNTER = new AtomicInteger(1000);
    }

    public static int getNewID()
    {
        return ID_COUNTER.getAndIncrement();
    }
}
