package cs5004.tictactoe;

/**
 * The enum class for TicTacToe.
 */
public enum Player {
  X("X"),
  O("O");

  String letter;

  Player(String letter) {
    this.letter = letter;
  }
}
