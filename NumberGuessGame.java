import java.util.Optional;

/**
 * A simple guessing game where the computer selects a number in a
 * given range (for example, 1 to 100),
 * and the player must guess the number in as few attempts as possible.
 * Feedback is given after each guess (e.g., "Too high", "Too low").
 * <pre>
 * The score can be calculated based on number of attempts or time taken.
 * </pre>
 * @version 1
 */
class NumberGuessGame implements Game {
    /**
    *gets the string name.
    *@return Number Guess.
    */
    public String getName() {
        return "Number Guess";
    }
    /**
    *Starts the game and prints game instructions.
    *@return an empty optional instance to represent the absence of a score.
    */
    public Optional<Integer> play() {
        System.out.println("[Playing Number Guessing Game]");
        System.out.println("This is a simple game where you"
                           + " try and guess the number I pick.");
        System.out.println("If you are too high or too low I will let"
                           + " you know. Try to do it in the fewest attempts!");
        return Optional.empty();
    }
}
