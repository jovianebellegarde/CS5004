/**
 * This class represents a Rook piece, extended from the abstract piece class,
 * which has a row, col, and color.
 */
public class Rook extends AbstractPiece {

  public Rook(int row, int col, Color color) throws IllegalArgumentException {
    super(row, col, color);
  }

  /**
   * Determines if this rook piece can move. It can only move horizontally or vertically.
   * @param row row position of this rook piece.
   * @param col col position of this rook piece.
   * @return true if this rook piece can move, false otherwise.
   */
  @Override
  public boolean canMove(int row, int col) {
    return super.canMove(row, col) && this.canMoveHorizontallyOrVertically(row, col);
  }
}
