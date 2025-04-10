import java.util.Random;
import java.util.List;
import java.util.Arrays;
public class GameUtils{
	public static String getRandomWord(List<String>words){
		
		if (words == null || words.isEmpty()){
			return null;
			}
		Random random = new Random();
		int randomIndex = random.nextInt(words.size());
		return words.get(randomIndex);

	}
	
	public static void main(String args[]){
		
	}
}

