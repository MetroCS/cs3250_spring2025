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
 * @version 2.1 (Refactored. Includes dependency injection for testability.)
 */
public class GameLauncher {
    /** Default history file name. */
    private static final String HISTORY_FILENAME = "history.dat";

    /** Name of the history file. */
    private String historyFileName;

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
             registerGames(),
             HISTORY_FILENAME);
    }

    /**
     * Constructor with injected testable components.
     *
     * @param inputScanner the console input
     * @param tracker the tracker to record and save history
     * @param gameList the list of games available to play
     * @param fileName the name of the history tracker file
     */
    public GameLauncher(final Scanner inputScanner,
                        final GameHistoryTracker tracker,
                        final List<Game> gameList,
                        final String fileName) {
        this.scanner = inputScanner;
        this.historyTracker = tracker;
        this.games = gameList;
        this.historyFileName = fileName;
    }

    /**
     * Main entry point of the application.
     * @param args command-line arguments (not used)
     */
    public static void main(final String[] args) {
        GameLauncher launcher = new GameLauncher();
        launcher.run();
        launcher.saveHistory();
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
            for (int i = 0; i < this.games.size(); i++) {
                System.out.printf("%d. %s\n",
                                  i + 1,
                                  this.games.get(i).getName());
            }
            System.out.println("0. Exit");
            System.out.println("H. View Game History");
            System.out.println("C. Clear Game History");
            System.out.print("Choose a game: ");

            String input = this.scanner.nextLine().trim();
            if (input.equalsIgnoreCase("H")) {
                this.historyTracker.displayHistory();
                continue;
            } else if (input.equalsIgnoreCase("C")) {
                confirmClearHistory();
                continue;
            }

          try {
                int choice = Integer.parseInt(input);
                if (choice == 0) {
                    running = false;
                    System.out.println("Goodbye!");
                } else if (choice > 0 && choice <= this.games.size()) {
                    Game game = this.games.get(choice - 1);
                    Optional<Integer> score = game.play();
                    this.historyTracker.recordPlay(game.getName(),
                                                   score.orElse(null));
                } else {
                    System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println(
                    "Please enter a valid number or Letters H or C.");
            }
        }
    }


    /**
     * Clears the history after user confirmation.
     *
     */
    private void confirmClearHistory() {
        System.out.println("Are you sure you want to clear history?");
        System.out.println("type CLEAR to confirm");
        System.out.println("or type anything else to cancel.");

        String input = this.scanner.nextLine().trim();
        if (input.equalsIgnoreCase("clear")) {
             System.out.println("Clearing History.\n");
             historyTracker.clearHistory("history.dat");
         }

    }

    /**
     * Saves the history of games played.
     */
    protected void saveHistory() {
        try {
            this.historyTracker.saveHistory(this.historyFileName);
        } catch (IOException e) {
            System.out.println("game history save failed: " + e.getMessage());
        }
    }
}
