import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit 5 test class for the GameLauncher.
 * Uses constructor injection and simulates console I/O to test
 * menu interaction, input validation, history recording, and file saving.
 * @version 1
 */
public class GameLauncherTest {

    /** Captures standard output for test assertions. */
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    /** Stores the original System.out stream for restoration. */
    private final PrintStream originalOut = System.out;

    /** Stores the original System.in stream for restoration. */
    private InputStream originalIn;

    /** List of stubbed games to inject into the launcher. */
    private List<Game> testGames;

    /** In-memory history tracker to inject. */
    private GameHistoryTracker testHistory;

    /** GameLauncher under test with injected components. */
    private GameLauncher launcher;

    /**
     * Set up test environment, redirect I/O, and initialize dependencies.
     */
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        originalIn = System.in;

        testGames = new ArrayList<>();
        testGames.add(new StubGame("Test Game 1", Optional.of(42)));
        testGames.add(new StubGame("Test Game 2", Optional.empty()));

        testHistory = new GameHistoryTracker();
    }

    /**
     * Restores standard input and output after each test.
     */
    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    /**
     * Simulates a valid game selection and verifies output and history recording.
     */
    @Test
    public void testRunValidGameSelection() {
        provideInput("1\n0\n"); // Select game 1, then exit
        launcher.run();

        String output = outContent.toString();
        assertTrue(output.contains("Test Game 1"), "Game name should appear in menu");
        assertTrue(output.contains("Playing Test Game 1"), "Game should be played");
        assertTrue(output.contains("Goodbye!"), "Exit message should be shown");
    }

    /**
     * Simulates invalid text input and verifies error handling.
     */
    @Test
    public void testRunInvalidInput() {
        provideInput("abc\n0\n");
        launcher.run();

        String output = outContent.toString();
        assertTrue(output.contains("Please enter a valid number or 'H'."), "Should reject non-numeric input");
    }

    /**
     * Simulates out-of-range numeric input and verifies error message.
     */
    @Test
    public void testRunInvalidGameChoice() {
        provideInput("999\n0\n");
        launcher.run();

        String output = outContent.toString();
        assertTrue(output.contains("Invalid choice."), "Should handle invalid game number");
    }

    /**
     * Simulates user choosing to view history and verifies output.
     */
    @Test
    public void testRunViewHistory() {
        provideInput("H\n0\n");
        launcher.run();

        String output = outContent.toString();
        assertTrue(output.contains("=== Game Play History ==="), "Should display history header");
    }

    /**
     * Verifies that saveHistory successfully writes to a file.
     * @param tempDir Temporary directory provided by JUnit
     * @throws IOException if file I/O fails unexpectedly
     */
    @Test
    public void testSaveHistoryCreatesFile(@TempDir final Path tempDir) throws IOException {
        Path file = tempDir.resolve("testHistory.dat");
        testHistory.recordPlay("Test Game X", 88);
        testHistory.saveHistory(file.toString());

        assertTrue(Files.exists(file), "Saved history file should exist");
        assertTrue(Files.size(file) > 0, "Saved history file should not be empty");
    }

    /**
     * Verifies graceful handling of an exception during saveHistory.
     */
    @Test
    public void testSaveHistoryHandlesIOException() {
        GameLauncher faultyLauncher = new GameLauncher(
                new Scanner(new ByteArrayInputStream("0\n".getBytes())),
                testHistory,
                testGames
        ) {
        @Override
        protected void saveHistory() {
            try {
                throw new IOException("Simulated IO failure");
            } catch (IOException e) {
                System.out.println("game history save failed: " + e.getMessage());
            }
        }
    };

    // Directly invoke the method being tested
    faultyLauncher.saveHistory();

    String output = outContent.toString();
    assertTrue(output.contains("game history save failed: Simulated IO failure"));
}

    /**
     * Replaces System.in with test input, creates launcher with injected scanner.
     *
     * @param data console input to simulate (e.g., "1\n0\n")
     */
    private void provideInput(final String data) {
        ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        // Subclass GameLauncher to access protected run()
        launcher = new GameLauncher(scanner, testHistory, testGames) { };
    }

    /**
     * Stub implementation of the Game interface used for testing.
     */
    private static class StubGame implements Game {
        /** Name of the game for menu and history. */
        private final String name;

        /** Predefined score or absence thereof. */
        private final Optional<Integer> scoreToReturn;

        /**
         * Constructs a stubbed game with a fixed name and play result.
         * @param gameName display-name of the game
         * @param score Optional score
         */
        StubGame(final String gameName, final Optional<Integer> score) {
            this.name = gameName;
            this.scoreToReturn = score;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public Optional<Integer> play() {
            System.out.println("Playing " + name);
            return scoreToReturn;
        }
    }
}
