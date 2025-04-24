import java.util.Optional;

/**
 * A puzzle game in which the player uncovers cells on a grid.
 * Some cells contain mines. Others show the number of adjacent mines.
 * The player must avoid the mines and clear the safe spaces to win.
 * <pre>
 * Consider simulating the grid using a 2D array,
 * implementing recursive revealing, and providing flagging of cells.
 * </pre>
 * @version 1
 * @author - Chad Ninteman
 * @author - Jose Ocampo
 * @author - Toren Kochman
 */
class MineSweeperGame implements Game {
    public String getName() {
        return "MineSweeper";
    }
    public Optional<Integer> play() {
        System.out.println("[Playing MineSweeper - Placeholder]");
        System.out.println("Welcome to Minesweeper!");
    System.out.println("Uncover tiles to reveal numbers or mines.");
        System.out.println("Numbers tell how many of the "
            + "8 adjacent tiles are mines.");
    System.out.println("Choosing a tile that contains a mine "
            + "will end the game.");
        System.out.println("Uncover all safe tiles to win!");
        return Optional.empty();
    }
}
