import java.util.Optional;

/**
 * A simplified console version of the classic Snake game.
 * The player controls a "snake" that moves around a grid, collecting food
 *   and growing in length.
 * The game ends if the snake runs into itself or the edge of the grid.
 *
 * Simulate the game board with a 2D array and display it using text-based output.
 */
class SnakeGame implements Game {
    public String getName() { return "Snake"; }
    public Optional<Integer> play() {
        System.out.println("[Playing Snake - Placeholder]");
        return Optional.empty();
    }
}
