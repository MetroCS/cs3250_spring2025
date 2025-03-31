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
 * @version 1
 */
class MemoryMatchGame implements Game {
    public String getName() { return "Memory Match"; }
    public Optional<Integer> play() {
        System.out.println("[Playing Memory Match - Placeholder]");
        return Optional.empty();
    }
}
