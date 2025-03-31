import java.util.Optional;

/**
 * Interface representing a generic game in the arcade.
 * @version 1
 */
interface Game {
    /**
     * Returns the name of the game.
     * @return game name
     */
    String getName();

    /**
     * Starts and runs the game.
     *
     * This method returns an Optional<Integer> to represent the score
     *   achieved by the player.
     * <pre>
     * Games that track a numeric score return Optional.of(score).
     * Games that do not use scoring return Optional.empty().
     *
     * This approach avoids the ambiguity of using 0 to mean "no score",
     * since 0 can be a valid score in many games.
     * </pre>
     * @return Optional containing score if applicable, or
     *         Optional.empty() if not
     */
    Optional<Integer> play();
}
