import java.util.Optional;

/**
 * A classic 3x3 two-player game adapted for single-player mode
 * against the computer.
 * The player and the computer take turns marking Xs and Os on a grid.
 * The winner is the first to align three in a row (horizontally,
 * vertically, or diagonally).
 * <pre>
 * Implementation may be a basic AI using heuristics or
 * an unbeatable strategy using the Minimax algorithm.
 * </pre>
 * @version 1
 */
class TicTacToeGame implements Game {
    public String getName() { return "Tic-Tac-Toe"; }
    public Optional<Integer> play() {
        System.out.println("[Playing Tic-Tac-Toe - Placeholder]");
        return Optional.empty();
    }
}
