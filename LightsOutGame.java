import java.util.Optional;

/**
 * A logic puzzle where each cell on a grid can be either "on" or "off".
 * Selecting a cell toggles it and its immediate neighbors.
 * The goal is to turn all the lights off in as few moves as possible.
 * <pre>
 * Consider a model using a grid of booleans.
 * Implement toggling logic on that grid.
 * </pre>
 * @version 1
 */
class LightsOutGame implements Game {
    public String getName() { return "Lights Out"; }
    public Optional<Integer> play() {
        System.out.println("[Playing Lights Out - Placeholder]");
        return Optional.empty();
    }
}
