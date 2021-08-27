package cs5004.tictactoe;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Class represents a TicTacToeModel with the given board.
 */
public class TicTacToeModel implements TicTacToe {
  private final Player[][] board;
  private int numOfMoves;

  /**
   * Constructs a TicTacToeModel object.
   */
  public TicTacToeModel() {
    this.board = new Player[3][3];
    this.numOfMoves = 0;
  }

  @Override
  public String toString() {
    // Using Java stream API to save code:
    return Arrays.stream(getBoard())
            .map(row -> " " + Arrays
                    .stream(row).map(p -> p == null ? " " : p.toString())
                    .collect(Collectors.joining(" | ")))
            .collect(Collectors.joining("\n-----------\n"));
  }

  /**
   * Get the current turn, i.e., the player who will mark on the next call to move().
   *
   * @return the {@link Player} whose turn it is
   */
  @Override
  public Player getTurn() {
    if (this.numOfMoves % 2 == 0) {
      return Player.X;
    }
    return Player.O;
  }

  /**
   * Return whether the game is over. The game is over when either the board is full, or one player
   * has won.
   *
   * @return true if the game is over, false otherwise
   */
  @Override
  public boolean isGameOver() {
    return this.getWinner() == Player.X || this.getWinner() == Player.O || this.numOfMoves == 9;
  }

  /**
   * Checks if there is a row win.
   * @param row an int.
   * @return a boolean true if a win, false otherwise.
   */
  private boolean winRow(int row) {
    for (int i = 1; i < this.board.length; i++) {
      if (this.board[row][i - 1] != this.board[row][i]) {
        return false;
      }
    }
    return true;
  }

  /**
   * Checks if there is a column win.
   * @param column an int.
   * @return a boolean true if a win, false otherwise.
   */
  private boolean winColumn(int column) {
    for (int i = 1; i < this.board.length; i++) {
      if (this.board[i - 1][column] != this.board[i][column]) {
        return false;
      }
    }
    return true;
  }

  /**
   * Checks if there is a right diagonal win.
   * @return a boolean true if a win, false otherwise.
   */
  private boolean winDiagonalRight() {
    for (int i = 1; i < this.board.length; i++) {
      if (this.board[this.board.length - i][i - 1] != this.board[this.board.length - 1 - i][i]) {
        return false;
      }
    }
    return true;
  }

  /**
   * Checks if there is a left diagonal win.
   * @return a boolean true if a win, false otherwise.
   */
  private boolean winDiagonalLeft() {
    for (int i = 1; i < this.board.length; i++) {
      if (this.board[i - 1][i - 1] != this.board[i][i]) {
        return false;
      }
    }
    return true;
  }

  /**
   * Execute a move in the position specified by the given row and column.
   *
   * @param r the row of the intended move
   * @param c the column of the intended move
   * @throws IllegalArgumentException if the space is occupied or the position is otherwise invalid
   * @throws IllegalStateException    if the game is over
   */
  @Override
  public void move(int r, int c) {
    if (this.isGameOver()) {
      throw new IllegalStateException("The game is over.");
    }
    if (r > 2 || c > 2 || r < 0 || c < 0) {
      throw new IllegalArgumentException("Can't move off the board.");
    }
    if (this.getMarkAt(r, c) == Player.X || getMarkAt(r, c) == Player.O) {
      throw new IllegalArgumentException("That spot is taken.");
    }
    this.board[r][c] = this.getTurn();
    this.numOfMoves++;
  }

  /**
   * Return the winner of the game, or {@code null} if there is no winner. If the game is not over,
   * returns {@code null}.
   *
   * @return the winner, or null if there is no winner
   */
  @Override
  public Player getWinner() {
    for (int i = 0; i < this.board.length; i++) {
      if (this.winRow(i)) {
        return this.getMarkAt(0, i);
      }
      if (this.winColumn(i)) {
        return this.getMarkAt(i, 0);
      }
      if (this.winDiagonalRight()) {
        return this.getMarkAt(0, 2);
      }
      if (this.winDiagonalLeft()) {
        return this.getMarkAt(0, 0);
      }
      return null;
    }
    return null;
  }

  /**
   * Return the current game state, as a 2D array of Player. A {@code null} value in the grid
   * indicates an empty position on the board.
   *
   * @return the current game board
   */
  @Override
  public Player[][] getBoard() {
    Player[][] boardCopy = new Player[3][3];
    for (int row = 0; row < boardCopy.length; row++) {
      for (int column = 0; column < boardCopy.length; column++) {
        boardCopy[row][column] = this.board[row][column];
      }
    }
    return boardCopy;
  }

  /**
   * Return the current {@link Player} mark at a given row and column, or {@code null} if the
   * position is empty.
   *
   * @param r the row
   * @param c the column
   * @return the player at the given position, or null if it's empty
   */
  @Override
  public Player getMarkAt(int r, int c) {
    if (r > 2 || c > 2 || r < 0 || c < 0) {
      throw new IllegalArgumentException("That's an invalid mark.");
    }
    return this.board[r][c];
  }
}
