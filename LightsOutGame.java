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
	System.out.println("Welcome to Lights Out!");
	System.out.println("Lights Out includes a grid with tiles that have an 'on' or 'off' function.");
	System.out.println("Selecting a tile will change its state. It can also change the state of its neighboring tiles. Turning them 'off' or 'on' as well.");
	System.out.println("The goal is to light the entire grid in as few turns as possible. Good luck!");
        return Optional.empty();
    }
}
