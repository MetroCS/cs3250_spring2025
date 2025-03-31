import java.util.Optional;

/**
 * A vertical strategy game where the player plays against a computer AI.
 * The player and the computer take turns dropping colored discs into a
 * 7-column, 6-row grid.
 * The goal is to be the first to form a line of four discs
 * horizontally, vertically, or diagonally.
 * <pre>
 * Implement simple or advanced AI for the opponent.
 * Consider using a matrix for the board state.
 * </pre>
 * @version 1
 */
class ConnectFourGame implements Game {
    public String getName() { return "Connect Four"; }
    public Optional<Integer> play() {
        System.out.println("[Playing Connect Four - Placeholder]");
        return Optional.empty();
    }
}
