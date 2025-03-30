import java.util.Optional;

/**
 * A number placement puzzle on a 9x9 grid.
 * The objective is to fill the grid with digits from 1 to 9 so that each column, row,
 * and 3x3 subgrid contains all digits without repetition.
 *
 * You can implement puzzle validation, a playable UI, and possibly generate puzzles.
 */
class SudokuGame implements Game {
    public String getName() { return "Sudoku"; }
    public Optional<Integer> play() {
        System.out.println("[Playing Sudoku - Placeholder]");
        return Optional.empty();
    }
}
