import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Collections;
/**
* generating test for selecting a random word from an internal list
*@Author Landry Vewenda
*@Author Efream Fsahaye

*/


public class HangmanGameTest{
	public static String getRandomWord(List<String> words){
		
		if (words == null || words.isEmpty()){
			return null;
		}
		Random random = new Random();
		int randomIndex = random.nextInt(words.size());
		return words.get(randomIndex);
	}
	@Test
	void ValidNonEmptyListOfWords(){
		List<String> words = Arrays.asList("java", "python", "kotlin");
		String selectedWord = getRandomWord(words);
		assertTrue(words.contains(selectedWord));
		
		
	}
	@Test
	void EmptyListOfWords() {
		List <String> words =  Collections.emptyList();
		String selectedWord = getRandomWord(words);
		assertNull(selectedWord);
	}
	@Test
	void ListWithNull() {
		String selectedWord = getRandomWord(null);
		assertNull(selectedWord);
		
	}
}

