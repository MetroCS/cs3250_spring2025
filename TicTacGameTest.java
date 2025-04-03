/**
 * User Story:#14
 * As a player, I want to see a short description of the Tic-Tac-Toe game before it start 
 * so that I understand the rules and goals.
 */
public class TicTacToe {
    public static void main(String[] args) {
        // Display game description at launch (Acceptance Criteria 1)
        printGameDescription();
        
        // Proceed with normal game flow (Acceptance Criteria 2)
        startGame();
    }
    
    /**
     * Prints a brief introduction to the game
     */
    private static void printGameDescription() {
        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println("Objective: Be the first to get 3 of your marks in a row");
        System.out.println("How to play: Take turns placing X or O on the 3x3 grid\n");
    }
    
    /**
     * Starts the main game logic
     */
    private static void startGame() {
        // Existing game implementation would go here
        System.out.println("Game board would appear here...");
        System.out.println("(Normal game logic would follow)");
    }
}

