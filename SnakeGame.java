import java.util.Optional;

/**
 * A simplified console version of the classic Snake game.
 * The player controls a "snake" that moves around a grid, collecting food
 *   and growing in length.
 * The game ends if the snake runs into itself or the edge of the grid.
 * <pre>
 * Simulate the game board with a 2D array.
 * Display the game using text-based output.
 * </pre>
 * @version 1
 */
class SnakeGame implements Game {
    public String getName() { return "Snake"; }
    public Optional<Integer> play() {
	System.out.println("This is a simple console version of the Snake game.");
	System.out.println("The player controls a snake that moves around a grid,");
	System.out.println("collecting food and growing in length.");
	System.out.println("The game ends if the snake runs into itself");
	System.out.println("or the edge of the grid");
        return Optional.empty();
    }
}
