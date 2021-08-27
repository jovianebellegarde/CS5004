/**
 * This class represents a Queen piece, extended from the abstract piece class,
 * which has a row, col, and color.
 */
public class Queen extends AbstractPiece {

  public Queen(int row, int col, Color color) throws IllegalArgumentException {
    super(row, col, color);
  }

  /**
   * Determines if this queen piece can move. It can only move horizontally, vertically,
   * and diagonally.
   * @param row row position of this queen piece.
   * @param col col position of this queen piece.
   * @return true if this queen piece can move, false otherwise.
   */
  @Override
  public boolean canMove(int row, int col) {
    return super.canMove(row, col) && (this.canMoveHorizontallyOrVertically(row, col)
            || this.canMoveDiagonally(row, col));
  }
}
