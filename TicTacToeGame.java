import java.util.Optional;

/**
 * A classic 3x3 two-player game adapted for single-player mode against the computer.
 * The player and the computer take turns marking Xs and Os on a grid.
 * The first to align three in a row (horizontally, vertically, or diagonally) wins.
 *
 * You can implement a basic AI using heuristics or an unbeatable strategy using
 * the Minimax algorithm.
 */
class TicTacToeGame implements Game {
    public String getName() { return "Tic-Tac-Toe"; }
    public Optional<Integer> play() {
        System.out.println("[Playing Tic-Tac-Toe - Placeholder]");
        return Optional.empty();
    }
}
