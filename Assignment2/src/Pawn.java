/**
 * This class represents a Pawn piece, extended from the abstract piece class,
 * which has a row, col, and color.
 */
public class Pawn extends AbstractPiece {

  public Pawn(int row, int col, Color color) throws IllegalArgumentException {
    super(row, col, color);
  }

  /**
   * Determines if this pawn can move. It can only move forward and not backwards one place at a
   * time in its own column.
   *
   * @param row row position of this pawn.
   * @param col col position of this pawn.
   * @return a boolean true if this pawn piece can move, false otherwise.
   */
  @Override
  public boolean canMove(int row, int col) {
    boolean result;
    if (super.canMove(row, col) && this.color == Color.BLACK) {
      result = this.row - 1 == row && this.col == col;
    } else if (super.canMove(row, col) && this.color == Color.WHITE) {
      result = this.row + 1 == row && this.col == col;
    } else {
      result = false;
    }
    return result;
  }

  /**
   * Determines if this pawn can kill another piece based on the color and position
   * of the other chess piece.
   * @param piece another Chess piece.
   * @return boolean true if this piece can kill, false otherwise.
   */
  @Override
  public boolean canKill(ChessPiece piece) {
    boolean result;
    if (this.color == piece.getColor()) {
      result = false;
    } else if (this.color == Color.BLACK) {
      result = this.row - 1 == piece.getRow() && this.col + 1 == piece.getColumn()
              || this.row - 1 == piece.getRow() && this.col - 1 == piece.getColumn();
    } else {
      result = this.row + 1 == piece.getRow() && this.col + 1 == piece.getColumn()
              || this.row + 1 == piece.getRow() && this.col - 1 == piece.getColumn();
    }
    return result;
  }
}
