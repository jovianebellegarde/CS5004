package cs5004.tictactoe;

/**
 * This enum represents the two players of the tic tac toe game, X and O.
 */
public enum Player {
  X("X"),
  O("O");

  String letter;

  Player(String letter) {
    this.letter = letter;
  }
}
