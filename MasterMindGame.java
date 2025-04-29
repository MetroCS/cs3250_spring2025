import java.util.Optional;

/**
 * A code-breaking game where the app selects a sequence of symbols, and
 * the player tries to guess the sequence within a fixed number of attempts.
 *
 * Feedback for each guess indicates how many values are correct and in
 * the correct position and how many are correct but in the wrong position.
 *
 * Consider using arrays or strings to store and compare codes.
 * @version 2
 */
class MasterMindGame implements Game {
    @Override
    public String getName() {
    return "MasterMind";
    }
    @Override
 public Optional<Integer> play() {

    System.out.println("You are now playing Mastermind");
    System.out.println("The app selects a sequance of symbols");
    System.out.println("The player tries to guess the sequance");
    System.out.println("You have a fixed number of attepmts to guess");
    System.out.println("When you guess correctly you win, have fun!");

        return Optional.empty();
    }
}
