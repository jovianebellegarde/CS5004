import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * A JUnit test class for the Queen class.
 */
public class QueenTest {
  ChessPiece queenBlack;
  ChessPiece queenWhite;
  ChessPiece queenCanKill;
  ChessPiece queenCannotKill;
  ChessPiece queenInvalid;

  @Before
  public void setUp() {
    this.queenBlack = new Queen(6, 6, Color.BLACK);
    this.queenWhite = new Queen(1, 1, Color.WHITE);
    this.queenCanKill = new Queen(7, 7, Color.WHITE);
    this.queenCannotKill = new Queen(7, 7, Color.BLACK);
  }

  @Test (expected = IllegalArgumentException.class)
  public void pieceOutOfBounds() {
    this.queenInvalid = new Queen(8, 8, Color.BLACK);
    assertEquals("This piece is placed out of bounds.", this.queenInvalid);
  }

  @Test
  public void canMoveTest() {
    // northeast
    assertTrue(this.queenBlack.canMove(7, 7));

    // northwest
    assertTrue(this.queenBlack.canMove(5, 7));

    // southeast
    assertTrue(this.queenBlack.canMove(7, 5));

    // southwest
    assertTrue(this.queenBlack.canMove(0, 0));

    // north
    assertTrue(this.queenBlack.canMove(7, 6));

    // south
    assertTrue(this.queenBlack.canMove(0, 6));

    // east
    assertTrue(this.queenBlack.canMove(6, 7));

    // west
    assertTrue(this.queenBlack.canMove(6, 0));
  }

  @Test
  public void cannotMoveTest() {
    assertFalse(this.queenBlack.canMove(3, 4));
  }

  @Test
  public void canKillTest() {
    assertTrue(this.queenBlack.canKill(this.queenCanKill));
  }

  @Test
  public void cannotKillTest() {
    assertFalse(this.queenBlack.canKill(this.queenCannotKill));
  }
}