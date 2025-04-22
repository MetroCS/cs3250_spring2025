import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;
/**
* A collection of tests for the GameUtils class.
*@Author Landry Vewenda
*@Author Efream Fsahaye
*/
public class GameUtilsTest {
        @Test
        void testValidNonEmptyListOfWords() {
              List<String> words = Arrays.asList("java", "python", "kotlin");
              String selectedWord = GameUtils.getRandomWord(words);
              assertTrue(words.contains(selectedWord));
        }
        @Test
        void testEmptyListOfWords() {
              List<String> words =  Collections.emptyList();
              String selectedWord = GameUtils.getRandomWord(words);
              assertNull(selectedWord);
        }
        @Test
        void testListWithNull() {
              String selectedWord = GameUtils.getRandomWord(null);
              assertNull(selectedWord);
        }
}
