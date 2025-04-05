import java.util.Scanner;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Console Game Hub.
 * Provides an arcade-style launcher for multiple games,
 * with support for scoring and history tracking.
 *
 * @author ChatGPT (from engineered prompts)
 * @author Dr. Jody Paul
 * @version 2 (Refactored. Includes dependency injection for testability.)
 */
public class GameLauncher {
    /** Default history file name. */
    private static final String HISTORY_FILENAME = "history.dat";

    /** Console input. */
    private final Scanner scanner;

    /** Collection of known games. */
    private final List<Game> games;

    /** Game history tracker. */
    private final GameHistoryTracker historyTracker;

    /**
     * Default constructor, used in production.
     * Loads default games, scanner, and history tracker.
     */
    public GameLauncher() {
        this(new Scanner(System.in),
             GameHistoryTracker.loadHistory(HISTORY_FILENAME),
             registerGames());
    }

    /**
     * Constructor with injected testable components.
     *
     * @param inputScanner the console input
     * @param tracker the tracker to record and save history
     * @param gameList the list of games available to play
     */
    public GameLauncher(final Scanner inputScanner,
                        final GameHistoryTracker tracker,
                        final List<Game> gameList) {
        this.scanner = inputScanner;
        this.historyTracker = tracker;
        this.games = gameList;
    }

    /**
     * Main entry point of the application.
     * @param args command-line arguments (not used)
     */
    public static void main(final String[] args) {
        new GameLauncher().run();
    }

    /**
     * Registers all available games in the arcade.
     * @return list of all available games
     */
    private static List<Game> registerGames() {
        List<Game> games = new ArrayList<>();
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
        return games;
    }

    /**
     * Runs the game launcher loop.
     * Allows user to choose and play games, and to view history.
     */
    protected void run() {
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
    protected void saveHistory() {
        try {
            historyTracker.saveHistory(HISTORY_FILENAME);
        } catch (IOException e) {
            System.out.println("game history save failed: " + e.getMessage());
        }
    }
}
