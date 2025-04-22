import java.util.Optional;

/**
 * A game that simulates a memory matching challenge.
 * Cards (represented by characters or numbers) are arranged in a grid.
 * The player flips two cards at a time and tries to find matching pairs.
 * <br />
 * The game ends when all pairs have been matched.
 * The score can be the number of turns that player took.
 * <pre>
 * Manages board state and the display of revealed vs. hidden tiles.
 * </pre>
 * @version 2.1
 */
class MemoryMatchGame implements Game {
    public String getName() {
      return "Memory Match";
    }
    public Optional<Integer> play() {
    System.out.println("Welcome, you are now playing Memory Match");
    System.out.println("Flip items to match pairs");
    System.out.println("If items don't match flip over, go again");
    System.out.println("When all items are matched you win");
    System.out.println("Have fun!!!");
        return Optional.empty();
    }
}

