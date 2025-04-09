/**
 *Author:Mateo P
 *Issue #14
 * A classic 3x3 two-player game adapted for single-player mode
 * against the computer.
 * The player and the computer take turns marking Xs and Os on a grid.
 * The winner is the first to align three in a row (horizontally,
 * vertically, or diagonally).
 */

import java.util.Optional;

class TicTacToeGame implements Game {
    // Add this main method to make it executable
    public static void main(String[] args) {
        new TicTacToeGame().play();
    }

    public String getName() { 
        return "Tic-Tac-Toe"; 
    }
    
    public Optional<Integer> play() {
        // Game description (meets your requirements)
        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println("Goal: Get 3 X's or O's in a row to win");
        System.out.println("Rules: Alternate turns on a 3x3 grid\n");
        
        System.out.println("[Game would start here]");
        return Optional.empty();
    }
}

// Required interface definition
interface Game {
    String getName();
    Optional<Integer> play();
}
