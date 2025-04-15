import java.util.Optional;
/**
 * User Story:#14
 * As a player, I want to see a short description of the Tic-Tac-Toe game before it start 
 * so that I understand the rules and goals.
 */
public class TicTacToeGame implements Game {
public String getName(){
  return "Tic-Tac_toe";
}
public Optional<Integer> play(){
   printGameDescription();
   startGame();
return Optional.empty();
}

 private void printGameDescription() {
      System.out.println("Welcome to Tic-Tac-Toe!");
      System.out.println("Objective: Be the first to get 3 of your marks in a row.");
      System.out.println("How to play: Take turns placing x or 0 on the 3x3 grid.\n.");
}

private void startGame() {
     System.out.println("Game board would appear here...");
     System.out.println(" [ Normal game logic would follow]");
  }
}  

