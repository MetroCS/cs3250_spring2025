import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.IOException;
import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Track history and stats of games played.
 * @version 1
 */
class GameHistoryTracker implements Serializable {
    private static final long serialVersionUID = 1L;
    /** Collection of play stats for each game. */
    private final HashMap<String, GameStats> statsMap = new HashMap<>();

    /**
     * Records a play session for a game.
     * @param gameName the name of the game played
     * @param score optional numeric score (nullable)
     */
    public void recordPlay(final String gameName, final Integer score) {
        GameStats stats = statsMap.getOrDefault(gameName, new GameStats());
        stats.timesPlayed++;
        if (score != null) {
            stats.totalScore += score;
            stats.scores.add(score);
        }
        statsMap.put(gameName, stats);
    }

    /**
     * Displays a summary of play history and scores.
     */
    public void displayHistory() {
        System.out.println("\n=== Game Play History ===");
        if (statsMap.isEmpty()) {
            System.out.println("No games played yet.");
            return;
        }
        for (Map.Entry<String, GameStats> entry : statsMap.entrySet()) {
            String game = entry.getKey();
            GameStats stats = entry.getValue();
            System.out.printf("%s - Played: %d", game, stats.timesPlayed);
            if (!stats.scores.isEmpty()) {
                double avg = stats.totalScore / (double) stats.scores.size();
                System.out.printf(", Avg Score: %.2f", avg);
            }
            System.out.println();
        }
    }

    /**
     * Saves the game history to a file.
     * @param filename the name of the file to save to
     * @throws IOException if an I/O error occurs
     */
    public void saveHistory(final String filename) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(this);
        }
    }

    /**
     * Loads the game history from a file.
     * @param filename the name of the file to load from
     * @return a GameHistoryTracker instance
     */
    public static GameHistoryTracker loadHistory(final String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (GameHistoryTracker) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(
                "No previous history found or failed to load. Starting fresh.");
            return new GameHistoryTracker();
        }
    }

    /**
     * Inner class to track stats for a single game.
     */
    private static class GameStats implements Serializable {
        private static final long serialVersionUID = 1L;
        int timesPlayed = 0;
        int totalScore = 0;
        ArrayList<Integer> scores = new ArrayList<>();
    }
}
