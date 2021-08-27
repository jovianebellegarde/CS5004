package cs5004.marblesolitaire.model;

/**
 * An enum class that represents the board positions.
 */
public enum Piece {
  MARBLE, EMPTY, INVALID;

  /**
   * String representation of the enum.
   * @return a string.
   */
  @Override
  public String toString() {
    switch (this) {
      case MARBLE:
        return "O";
      case EMPTY:
        return "_";
      default:
        return " ";
    }
  }
}
