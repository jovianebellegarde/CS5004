/**
 * This interface contains all operations that all types of bank accounts should support.
 */
public interface ChessPiece {

  /**
   * Get the row position of this chess piece.
   * @return int position of this chess piece.
   */
  int getRow();

  /**
   * Get the column position of this chess piece.
   * @return int position of this chess piece.
   */
  int getColumn();

  /**
   * Get color of this chess piece from enum.
   * @return color of this chess piece.
   */
  Color getColor();

  /**
   * Determine if this chess piece can move to a given cell.
   * @param row int row position of this chess piece.
   * @param col int column position of this chess piece.
   * @return boolean true if this chess piece can move, false otherwise.
   */
  boolean canMove(int row, int col);

  /**
   * Determine if this chess piece can kill a provided piece starting from where it currently is.
   * @param piece another Chess Piece.
   * @return boolean true if can kill another chess piece, false otherwise.
   */
  boolean canKill(ChessPiece piece);
}
