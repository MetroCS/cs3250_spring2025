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
        System.out.println("The tiles have an 'on' or 'off' function.");
        System.out.println("Selecting a tile will change its state.");
        System.out.println("Tiles also change the state of neighboring tiles.");
        System.out.println("Turning them 'off' or 'on' as well.");
        System.out.println("Grid must be lit in as few turns as possible.");
        System.out.println("Good luck!");
        return Optional.empty();
    }
}
