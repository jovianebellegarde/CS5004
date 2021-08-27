/**
 * This class represents a Bishop piece, extended from the abstract piece class,
 * which has a row, col, and color.
 */
public class Bishop extends AbstractPiece {

  public Bishop(int row, int col, Color color) throws IllegalArgumentException {
    super(row, col, color);
  }

  /**
   * Determines if this bishop piece can move. It can only move in a diagonal.
   * @param row row position of this bishop piece.
   * @param col col position of this bishop piece.
   * @return true if this bishop piece can move, false otherwise.
   */
  @Override
  public boolean canMove(int row, int col) {
    return super.canMove(row, col) && this.canMoveDiagonally(row, col);
  }
}
