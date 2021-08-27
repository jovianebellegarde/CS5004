/**
 * This abstract class represents an AbstractPiece which has a row, col, and color.
 */
public abstract class AbstractPiece implements ChessPiece {
  protected int row;
  protected int col;
  protected Color color;

  /**
   * Constructs an Abstract Piece object and initializes it to the given row, col, and color.
   * @param  row int row position of this abstract piece.
   * @param  col int col position of this abstract piece.
   * @param  color color of this abstract piece.
   * @throws IllegalArgumentException if this abstract piece is given a position that is
   *          out of bounds of the chess board.
   */
  public AbstractPiece(int row, int col, Color color) throws IllegalArgumentException {
    boolean invalidPosition;
    invalidPosition = row > 7 || row < 0 || col > 7 || col < 0;
    if (invalidPosition) {
      throw new IllegalArgumentException("This piece is placed out of bounds.");
    } else {
      this.row = row;
      this.col = col;
      this.color = color;
    }
  }

  @Override
  public int getRow() {
    return this.row;
  }

  @Override
  public int getColumn() {
    return this.col;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public boolean canMove(int row, int col) {
    return row >= 0 && row <= 7 && col >= 0 && col <= 7;
  }

  /**
   * This method checks if the piece can move diagonally.
   * @param  row int row position of this abstract piece.
   * @param  col int col position of this abstract piece.
   * @return boolean true if this chess piece can move, false otherwise.
   */
  protected boolean canMoveDiagonally(int row, int col) {
    return Math.abs(this.row - row) == Math.abs(this.col - col);
  }

  /**
   * This method checks if the piece can move horizontally or vertically.
   * @param  row int row position of this abstract piece.
   * @param  col int col position of this abstract piece.
   * @return boolean true if this chess piece can move, false otherwise.
   */
  protected boolean canMoveHorizontallyOrVertically(int row, int col) {
    return (this.col == col || this.row == row);
  }

  @Override
  public boolean canKill(ChessPiece piece) {
    return (this.canMove(piece.getRow(), piece.getColumn()) && this.color != piece.getColor());
  }
}
