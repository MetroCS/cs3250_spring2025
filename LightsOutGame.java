import java.util.Optional;

/**
 * A logic puzzle where each cell on a grid can be either "on" or "off".
 * Pressing a cell toggles it and its immediate neighbors.
 * The goal is to turn all the lights off in as few moves as possible.
 *
 * You can model this using a grid of booleans and implement toggling logic.
 */
class LightsOutGame implements Game {
    public String getName() { return "Lights Out"; }
    public Optional<Integer> play() {
        System.out.println("[Playing Lights Out - Placeholder]");
        return Optional.empty();
    }
}
