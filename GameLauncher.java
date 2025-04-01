import java.util.Scanner;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Console Game Hub.
 * @author ChatGPT from prompts engineered by Dr. Jody Paul
 * @author Dr. Jody Paul
 * @version 1
 */
public class GameLauncher {
    /** Console input. */
    private final Scanner scanner = new Scanner(System.in);
    /** Collection of known games. */
    private final List<Game> games = new ArrayList<>();
    /** Game history tracker. */
    private final GameHistoryTracker historyTracker =
        GameHistoryTracker.loadHistory("history.dat");

    /**
     * Main entry point of the application.
     * @param args command-line arguments (not used)
     */
    public static void main(final String[] args) {
        GameLauncher launcher = new GameLauncher();
        launcher.registerGames();
        launcher.run();
        launcher.saveHistory();
    }

    /**
     * Registers all available games in the arcade.
     */
    private void registerGames() {
        games.add(new TicTacToeGame());
        games.add(new WordGuessGame());
        games.add(new JottoGame());
        games.add(new HangmanGame());
        games.add(new SnakeGame());
        games.add(new ConnectFourGame());
        games.add(new MineSweeperGame());
        games.add(new SudokuGame());
        games.add(new NumberGuessGame());
        games.add(new MemoryMatchGame());
        games.add(new LightsOutGame());
        games.add(new MasterMindGame());
    }

    /**
     * Runs the game launcher loop to allow user to choose and play games.
     */
    private void run() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Console Arcade Hub ===");
            for (int i = 0; i < games.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, games.get(i).getName());
            }
            System.out.println("0. Exit");
            System.out.println("H. View Game History");
            System.out.print("Choose a game: ");

            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("H")) {
                historyTracker.displayHistory();
                continue;
            }

            try {
                int choice = Integer.parseInt(input);
                if (choice == 0) {
                    running = false;
                    System.out.println("Goodbye!");
                } else if (choice > 0 && choice <= games.size()) {
                    Game game = games.get(choice - 1);
                    Optional<Integer> score = game.play();
                    historyTracker.recordPlay(game.getName(),
                                              score.orElse(null));
                } else {
                    System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number or 'H'.");
            }
        }
    }

    /**
     * Saves the history of games played.
     */
    private void saveHistory() {
        try {
            historyTracker.saveHistory("history.dat");
        } catch (IOException e) {
            System.out.println("game history save failed: " + e.getMessage());
        }
    }
}
