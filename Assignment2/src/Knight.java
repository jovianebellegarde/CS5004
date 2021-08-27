/**
 * This class represents a Knight piece, extended from the abstract piece class,
 * which has row, col, and color.
 */
public class Knight extends AbstractPiece {

  public Knight(int row, int col, Color color) throws IllegalArgumentException {
    super(row, col, color);
  }

  /**
   * Determines if this knight piece can move. It can only move in an L-shaped
   * pattern.
   * @param row row position of this knight piece.
   * @param col col position of this knight piece.
   * @return true if this knight piece can move, false otherwise.
   */
  @Override
  public boolean canMove(int row, int col) {
    return super.canMove(row, col) && ((Math.abs(this.row - row) == 1
            && Math.abs(this.col - col) == 2) || (Math.abs(this.row - row) == 2
            && Math.abs(this.col - col) == 1));
  }
}
