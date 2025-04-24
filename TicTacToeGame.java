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
 * @version 2
 */
public class TicTacToeGame implements Game {
/**
*Returns the name of the game.
*@return the game name.
*/
public String getName() {
  return "Tic-Tac-Toe";
}
/**
*Displays the game instructions and returns empty result.
*@return an empty Optional.
**/

public Optional<Integer> play() {

     System.out.println("Welcome to Tic-Tac-Toe!.");
     System.out.println("Objective: Be the first to get 3 of your "
                    + "marks in a row.");
     System.out.println("How to play: Take turns placing X or O "
                    + "on the 3x3 grid.");
     return Optional.empty();
  }
}
