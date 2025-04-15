import java.util.Random;
import java.util.List;
/**
*This class provides utility methods for handling random word selection.
*It contains a method to get a random word from a list of words.
*/
public final class GameUtils {
    /**
    *Prevent Instantiation.
    */
    private GameUtils() {
        throw new UnsupportedOperationException("class cannot be instantiated");
    }
    /**
    *Static instance used to generate random strings.
    */
    private static final Random RANDOM = new Random();
    /**
    *Returns a random word from given list.
    *
    *@param words A list of words from which the random word will be selected.
    *@return A random word from the list, or null if the list is null or empty.
    */
    public static String getRandomWord(final List<String> words) {
        if (words == null || words.isEmpty()) {
            return null;
            }
        int randomIndex = RANDOM.nextInt(words.size());
        return words.get(randomIndex);
     }
}
