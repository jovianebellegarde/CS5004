package cs5004.tictactoe;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * This class represents the workhorse model of the TicTacToe game and all of it's methods.
 */
public class TicTacToeModel implements TicTacToe {
  // add your implementation here
  private final Player[][] board;
  private int numberMoves;


  /**
   * Constructs a tic tac toe player and board.
   */
  public TicTacToeModel() {
    this.board = new Player[3][3];
    this.numberMoves = 0;
  }

  @Override
  public String toString() {
    // Using Java stream API to save code:
    return Arrays.stream(getBoard())
            .map(row -> " " + Arrays.stream(row)
                    .map(p -> p == null ? " " : p.toString())
                    .collect(Collectors.joining(" | ")))
            .collect(Collectors.joining("\n-----------\n"));
    // This is the equivalent code as above, but using iteration, and still using
    // the helpful built-in String.join method.
  }

  private boolean winVertical() {
    // starting at middle of the board
    for (int i = 1; i < this.board.length; i++) {
      if (this.board[i - 1][i] != this.board[i][i]) {
        return false;
      }
    }
    return true;
  }

  private boolean winHorizontal() {
    for (int i = 1; i < this.board.length; i++) {
      if (this.board[i][i - 1] != this.board[i][i]) {
        return false;
      }
    }
    return true;
  }

  private boolean diagonal1() {
    for (int i = 1; i < this.board.length; i++) {
      if (this.board[this.board.length - i][i - 1]
              != this.board[this.board.length - 1 - i][i]) {
        return false;
      }
    }
    return true;
  }

  private boolean diagonal2() {
    for (int i = 1; i < this.board.length; i++) {
      if (this.board[i - 1][i - 1] != this.board[i][i]) {
        return false;
      }
    }
    return true;
  }

  @Override
  public void move(int r, int c) throws IllegalArgumentException, IllegalStateException {
    if (r > 2 || r < 0 || c > 2 | c < 0) {
      throw new IllegalArgumentException("Position can't be outside the board.");
    }
    if (this.board[r][c] != null) {
      throw new IllegalArgumentException("There is already an x or o there.");
    }
    if (this.isGameOver()) {
      throw new IllegalStateException("Game is already over.");
    }
    if (getMarkAt(r, c) == Player.X || getMarkAt(r, c) == Player.O) {
      throw new IllegalArgumentException("Space already marked.");
    }
    this.board[r][c] = getTurn();
    this.numberMoves++;
    // this.currentPlayer = this.currentPlayer == Player.X ? Player.O : Player.X;
  }

  @Override
  public Player getTurn() {
    Player player;

    if (this.numberMoves % 2 == 0) {
      player = Player.X;
    } else {
      player = Player.O;
    }
    return player;
  }

  @Override
  public boolean isGameOver() {
    if (getWinner() == Player.X) {
      return true;
    } else if (getWinner() == Player.O) {
      return true;
    } else return this.numberMoves == 9;
  }

  @Override
  public Player getWinner() {
    for (int i = 0; i < this.board.length; i++) {
      if (diagonal1()) {
        return getMarkAt(0, 2);
      } else if (diagonal2()) {
        return getMarkAt(0, 0);
      } else if (winHorizontal()) {
        return getMarkAt(0, i);
      } else if (winVertical()) {
        return getMarkAt(i, 0);
      } else {
        return null;
      }
    }
    return null;
  }
  /**
   * Checking to see if there are 3 X/O's in a row whether in a row, column, or diagonally.
   * @return boolean true if 3 X/O's, false otherwise.
   */
  /*
  private boolean isThreeInARow() {
    // diagonal, horizontal, vertical separate methods
    // try a version without so many loops
    boolean result = false;
    for (int row = 0; row < this.boardSize; row++) {
      for (int column = 0; column < this.boardSize; column++) {

        // checking if matches in all the rows
        if (this.board[row][0] == this.board[row][1] && this.board[row][1] == this.board[row][2]) {
          result = this.board[row][0] != null;

          // checking if matches in all the columns
        } else if (this.board[0][column] == this.board[1][column]
                && this.board[1][column] == this.board[2][column]) {
          result = this.board[0][column] != null;

          // takes care of diagonals
        } else if (this.board[0][0] == this.board[1][1] && this.board[1][1] == this.board[2][2]
                || this.board[0][2] == this.board[1][1] && this.board[1][1] == this.board[2][0]) {
          result = this.board[row][column] != null;

        } else {
          result = false;
        }
      }
    }
    return result;
  }
   */

  /**
   * Method to check if the board is full.
   * @return true if full, false otherwise.
   */
  /*
  private boolean isBoardFull() {
    for (int row = 0; row < this.boardSize; row++) {
      for (int column = 0; column < this.boardSize; column++) {
        if (this.board[row][column] == null) {
          return false;
        }
      }
    }
    return true;
  }

   */

  /*


  @Override
  public Player getWinner() {
    if (this.isThreeInARow()) {
      return this.getTurn();
    }
    return null;
  }

   */

  @Override
  public Player[][] getBoard() {
    Player[][] temporaryBoard = new Player[3][3];
    for (int row = 0; row < temporaryBoard.length; row++) {
      for (int column = 0; column < temporaryBoard.length; column++) {
        temporaryBoard[row][column] = this.board[row][column];
      }
    }
    return temporaryBoard;
  }

  @Override
  public Player getMarkAt(int r, int c) throws IllegalArgumentException {
    if (r > 2 || r < 0 || c > 2 | c < 0) {
      throw new IllegalArgumentException("Position can't be outside the board.");
    }
    return this.board[r][c];
  }
}
