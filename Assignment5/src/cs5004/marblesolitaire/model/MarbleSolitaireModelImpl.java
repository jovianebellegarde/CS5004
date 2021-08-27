package cs5004.marblesolitaire.model;

/**
 * This class represents the Marble Solitaire Game Implementation of the Marble Model interface.
 * There are 4 constructors with different parameters that are passed into them.
 */
public class MarbleSolitaireModelImpl implements MarbleSolitaireModel {
  private Piece[][] gameBoard;
  private final int arm;
  private int sRow;
  private int sCol;

  /**
   * Constructs a default marble solitaire board with an arm thickness of 3, and empty position
   * at (3,3).
   */
  public MarbleSolitaireModelImpl() {
    this(3, 3, 3);
    this.makeGameBoard(this.arm, this.sRow, this.sCol);
  }

  /**
   * Constructs a marble solitaire board with given sRow and sCol of empty spot.
   * @param sRow int row of empty spot.
   * @param sCol int column of empty spot.
   * @throws IllegalArgumentException if the empty spot is in an invalid position.
   */
  public MarbleSolitaireModelImpl(int sRow, int sCol) throws IllegalArgumentException {
    this(3, sRow, sCol);
    if (this.isSpotInvalid(this.arm, sRow, sCol)) {
      throw new IllegalArgumentException(String.format("Invalid empty cell position (%d,%d)",
              sRow, sCol));
    }
    this.makeGameBoard(this.arm, this.sRow, this.sCol);
  }

  /**
   * Constructs a marble solitaire board with the given arm thickness.
   * @param arm an int.
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number.
   */
  public MarbleSolitaireModelImpl(int arm) throws IllegalArgumentException {
    if (this.isArmInvalid(arm)) {
      throw new IllegalArgumentException("Invalid arm thickness.");
    }
    this.arm = arm;
    int boardLength = this.calculateBoardLength(this.arm);
    int centerSpot = (boardLength - 1) / 2;
    this.makeGameBoard(this.arm, centerSpot, centerSpot);
  }

  /**
   * Constructs a marble solitaire board with the given arm, sRow, and sCol.
   * @param arm int arm thickness.
   * @param sRow int row of empty spot.
   * @param sCol int column of empty spot.
   * @throws IllegalArgumentException if the arm thickness is negative or an even number,
   *         or the empty spot position is invalid.
   */
  public MarbleSolitaireModelImpl(int arm, int sRow, int sCol) throws IllegalArgumentException {
    if (this.isArmInvalid(arm) || this.isSpotInvalid(arm, sRow, sCol)) {
      throw new IllegalArgumentException("Invalid arm or empty spot.");
    }
    this.arm = arm;
    this.sRow = sRow;
    this.sCol = sCol;
    this.makeGameBoard(this.arm, this.sRow, this.sCol);
  }

  /**
   * Makes the game board when called in constructor with the given arm thickness, sRow, and sCol.
   * @param arm int arm thickness.
   * @param sRow int row of empty spot.
   * @param sCol int column of empty spot.
   */
  private void makeGameBoard(int arm, int sRow, int sCol) {
    int boardLength = calculateBoardLength(arm);
    int invalidSpots = calculateInvalidSpots(boardLength, arm);
    this.gameBoard = new Piece[boardLength][boardLength];

    for (int row = 0; row < boardLength; row++) {
      for (int column = 0; column < boardLength; column++) {
        boolean topRightInvalid = this.isTopRightInvalid(invalidSpots, boardLength, row, column);
        boolean bottomRightInvalid = this.isBottomRightInvalid(invalidSpots, boardLength,
                row, column);
        boolean topLeftInvalid = this.isTopLeftInvalid(invalidSpots, row, column);
        boolean bottomLeftInvalid = this.isBottomLeftInvalid(invalidSpots, boardLength,
                row, column);

        if (topRightInvalid || bottomRightInvalid || topLeftInvalid || bottomLeftInvalid) {
          this.gameBoard[row][column] = Piece.INVALID;
        } else if (row == sRow && column == sCol) {
          this.gameBoard[row][column] = Piece.EMPTY;
        } else {
          this.gameBoard[row][column] = Piece.MARBLE;
        }
      }
    }
  }

  /**
   * Checks if the arm thickness is valid.
   * @param arm an int.
   * @return a boolean true, false otherwise.
   */
  private boolean isArmInvalid(int arm) {
    return arm < 3 || arm % 2 == 0;
  }

  /**
   * Calculates the number of rows and columns.
   * @param arm an int.
   * @return an int.
   */
  private int calculateBoardLength(int arm) {
    return arm * 2 + arm - 2;
  }

  /**
   * Calculates the invalid spots of the board.
   * @param boardLength an int.
   * @param arm an int.
   * @return an int.
   */
  private int calculateInvalidSpots(int boardLength, int arm) {
    return (boardLength - arm) / 2;
  }

  /**
   * Checks if the empty spot is in the invalid position of the marble solitaire board.
   * @param arm an int.
   * @param row an int.
   * @param column an int.
   * @return boolean true if the empty spot is in the invalid spot, false otherwise.
   */
  private boolean isSpotInvalid(int arm, int row, int column) {
    int boardLength = this.calculateBoardLength(arm);
    int invalidSpots = this.calculateInvalidSpots(boardLength, arm);
    boolean isSpotInTopLeftInvalid = isTopLeftInvalid(invalidSpots, row, column);
    boolean isSpotInBottomLeftInvalid = isBottomLeftInvalid(invalidSpots, boardLength, row, column);
    boolean isSpotInTopRightInvalid = isTopRightInvalid(invalidSpots, boardLength, row, column);
    boolean isSpotInBottomRightInvalid = isBottomRightInvalid(invalidSpots, boardLength,
            row, column);

    return isSpotInTopLeftInvalid || isSpotInBottomLeftInvalid || isSpotInTopRightInvalid
            || isSpotInBottomRightInvalid;
  }

  /**
   * Checks if the empty spot is in the invalid top left spots.
   * @param invalidSpots an int.
   * @param sRow an int.
   * @param sCol an int.
   * @return true if in invalid spot, false otherwise.
   */
  private boolean isTopLeftInvalid(int invalidSpots, int sRow, int sCol) {
    for (int row = 0; row < invalidSpots; row++) {
      for (int column = 0; column < invalidSpots; column++) {
        if (row == sRow && column == sCol) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Checks if the empty spot is in the invalid bottom left spots.
   * @param invalidSpots an int.
   * @param sRow an int.
   * @param sCol an int.
   * @return true if in invalid spot, false otherwise.
   */
  private boolean isBottomLeftInvalid(int invalidSpots, int boardLength, int sRow, int sCol) {
    for (int row = boardLength - invalidSpots; row < boardLength; row++) {
      for (int column = 0; column < invalidSpots; column++) {
        if (row == sRow && column == sCol) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Checks if the empty spot is in the invalid top right spots.
   * @param invalidSpots an int.
   * @param sRow an int.
   * @param sCol an int.
   * @return true if in invalid spot, false otherwise.
   */
  private boolean isTopRightInvalid(int invalidSpots, int boardLength, int sRow, int sCol) {
    for (int row = 0; row < invalidSpots; row++) {
      for (int column = boardLength - invalidSpots; column < boardLength; column++) {
        if (row == sRow && column == sCol) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Checks if the empty spot is in the invalid bottom right spots.
   * @param invalidSpots an int.
   * @param sRow an int.
   * @param sCol an int.
   * @return true if in invalid spot, false otherwise.
   */
  private boolean isBottomRightInvalid(int invalidSpots, int boardLength, int sRow, int sCol) {
    for (int row = boardLength - invalidSpots; row < boardLength; row++) {
      for (int column = boardLength - invalidSpots; column < boardLength; column++) {
        if (row == sRow && column == sCol) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Move a single marble from a given position to another given position. A move is valid only if
   * the from and to positions are valid. Specific implementations may place additional constraints
   * on the validity of a move.
   *
   * @param fromRow the row number of the position to be moved from (starts at 0)
   * @param fromCol the column number of the position to be moved from (starts at 0)
   * @param toRow   the row number of the position to be moved to (starts at 0)
   * @param toCol   the column number of the position to be moved to (starts at 0)
   * @throws IllegalArgumentException if the move is not possible
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    int inBetweenRow = calculateSpotInBetween(fromRow, toRow);
    int inBetweenColumn = calculateSpotInBetween(fromCol, toCol);
    boolean validMove = isMoveValid(fromRow, fromCol, toRow, toCol);

    for (int row = 0; row < this.gameBoard.length; row++) {
      for (int column = 0; column < this.gameBoard.length; column++) {
        if (validMove) {
          this.gameBoard[fromRow][fromCol] = Piece.EMPTY;
          this.gameBoard[toRow][toCol] = Piece.MARBLE;
          this.gameBoard[inBetweenRow][inBetweenColumn] = Piece.EMPTY;
          break;
        }
      }
    }
    if (!validMove) {
      throw new IllegalArgumentException("This is not a valid move.");
    }
  }

  /**
   * Calculates the spot that is in between the from row and to row or from column and to column.
   * @param numberOne an int.
   * @param numberTwo an int.
   * @return int result of calculation.
   */
  private int calculateSpotInBetween(int numberOne, int numberTwo) {
    return (numberOne + numberTwo) / 2;
  }

  /**
   * Checks if the move is valid from the starting position and the ending position.
   * @param fromRow int row of from position.
   * @param fromCol int column of from position.
   * @param toRow int row of to position.
   * @param toCol int column of to position.
   * @return boolean true if valid, false otherwise.
   */
  private boolean isMoveValid(int fromRow, int fromCol, int toRow, int toCol) {
    boolean isFromSpotValid = isSpotInvalid(this.arm, fromRow, fromCol);
    boolean isToSpotValid = isSpotInvalid(this.arm, toRow, toCol);
    boolean marbleAtSpot = isMarbleAtSpot(fromRow, fromCol);
    boolean spotEmpty = isSpotEmpty(toRow, toCol);

    boolean areTheyTwoSpotsAway = fromRow == toRow && Math.abs(fromCol - toCol) == 2
            || Math.abs(fromRow - toRow) == 2 && fromCol == toCol;

    int inBetweenRow = calculateSpotInBetween(fromRow, toRow);
    int inBetweenColumn = calculateSpotInBetween(fromCol, toCol);
    boolean marbleInBetween = isMarbleAtSpot(inBetweenRow, inBetweenColumn);
    boolean equal = isSpotBetweenFromToSame(inBetweenRow, inBetweenColumn);

    return !isFromSpotValid && !isToSpotValid && marbleAtSpot && spotEmpty && areTheyTwoSpotsAway
            && marbleInBetween && equal;
  }

  /**
   * Checking if the spot has a marble.
   * @param specifiedRow int row of specified row.
   * @param specifiedColumn int column of specified column.
   * @return boolean true if there is a marble, false otherwise.
   */
  private boolean isMarbleAtSpot(int specifiedRow, int specifiedColumn) {
    for (int row = 0; row < this.gameBoard.length; row++) {
      for (int column = 0; column < this.gameBoard.length; column++) {
        if (row == specifiedRow && column == specifiedColumn
                && this.gameBoard[specifiedRow][specifiedColumn] == Piece.MARBLE) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Checking if the spot between the from row/col and to row/col is the same spot.
   * @param specifiedRow int row specified spot.
   * @param specifiedColumn int column of specified spot.
   * @return boolean true if they are equal, false otherwise.
   */
  private boolean isSpotBetweenFromToSame(int specifiedRow, int specifiedColumn) {
    for (int row = 0; row < this.gameBoard.length; row++) {
      for (int column = 0; column < this.gameBoard.length; column++) {
        if (row == specifiedRow && column == specifiedColumn) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Checks if the spot is empty.
   * @param specifiedRow int row of specified spot.
   * @param specifiedColumn int column of specified column.
   * @return boolean true if empty, false otherwise.
   */
  private boolean isSpotEmpty(int specifiedRow, int specifiedColumn) {
    for (int row = 0; row < this.gameBoard.length; row++) {
      for (int column = 0; column < this.gameBoard.length; column++) {
        if (row == specifiedRow && column == specifiedColumn
                && this.gameBoard[specifiedRow][specifiedColumn] == Piece.EMPTY) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Determine and return if the game is over or not. A game is over if no more moves can be made.
   *
   * @return true if the game is over, false otherwise
   */
  @Override
  public boolean isGameOver() {
    for (int row = 0; row < this.gameBoard.length; row++) {
      for (int column = 0; column < this.gameBoard.length; column++) {
        if (this.isMoveValid(row, column, row - 2, column)
                || this.isMoveValid(row, column, row, column - 2)
                || this.isMoveValid(row, column, row + 2, column)
                || this.isMoveValid(row, column, row, column + 2)) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Return a string that represents the current state of the board. The string should have one line
   * per row of the game board. Each slot on the game board is a single character (O, X or space for
   * a marble, empty and invalid position respectively). Slots in a row should be separated by a
   * space. Each row has no space before the first slot and after the last slot.
   *
   * @return the game state as a string
   */
  @Override
  public String getGameState() {
    StringBuilder innerLoop = new StringBuilder();
    StringBuilder outerLoop = new StringBuilder();

    int boardLength = this.calculateBoardLength(this.arm);

    for (int row = 0; row < boardLength; row++) {
      for (int column = 0; column < boardLength; column++) {
        innerLoop.append(this.gameBoard[row][column]).append(" ");
      }
      outerLoop.append(innerLoop.toString().stripTrailing()).append("\n");
      innerLoop = new StringBuilder();
    }
    return outerLoop.toString().stripTrailing();
  }

  /**
   * Return the number of marbles currently on the board.
   *
   * @return the number of marbles currently on the board
   */
  @Override
  public int getScore() {
    int count = 0;
    Piece[][] board = this.gameBoard;
    for (Piece[] pieces : board) {
      for (int column = 0; column < this.gameBoard.length; column++) {
        if (pieces[column] == Piece.MARBLE) {
          count++;
        }
      }
    }
    return count;
  }
}
