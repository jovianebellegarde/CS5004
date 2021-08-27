import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * A JUnit test class for the Rook class.
 */
public class RookTest {
  ChessPiece rookBlack;
  ChessPiece rookWhite;
  ChessPiece rookCanKill;
  ChessPiece rookCannotKill;
  ChessPiece rookInvalid;

  @Before
  public void setUp() {
    this.rookBlack = new Rook(3, 3, Color.BLACK);
    this.rookWhite = new Rook(2, 3, Color.BLACK);
    this.rookCanKill = new Rook(3, 4, Color.WHITE);
    this.rookCannotKill = new Rook(3, 4, Color.BLACK);
  }

  @Test (expected = IllegalArgumentException.class)
  public void pieceOutOfBounds() {
    this.rookInvalid = new Rook(-2, -2, Color.WHITE);
    assertEquals("This piece is placed out of bounds.", this.rookInvalid);
  }

  @Test
  public void getRowTest() {
    assertEquals(3, this.rookBlack.getRow());
  }

  @Test
  public void getColumnTest() {
    assertEquals(3, this.rookBlack.getColumn());
  }

  @Test
  public void getColorTest() {
    assertEquals(Color.BLACK, this.rookBlack.getColor());
  }

  @Test
  public void canMoveTest() {
    // left
    assertTrue(this.rookBlack.canMove(1, 3));

    // right
    assertTrue(this.rookBlack.canMove(3, 7));

    // up
    assertTrue(this.rookBlack.canMove(6, 3));

    // down
    assertTrue(this.rookBlack.canMove(0, 3));
  }

  @Test
  public void cannotMoveTest() {
    // diagonal
    assertFalse(this.rookBlack.canMove(2, 2));

    // impossible move
    assertFalse(this.rookBlack.canMove(7, 5));

    // move to out of bounds
    assertFalse(this.rookBlack.canMove(3, 8));
    assertFalse(this.rookBlack.canMove(-2, 3));
  }

  @Test
  public void canKillTest() {
    assertTrue(this.rookBlack.canKill(this.rookCanKill));
  }

  @Test
  public void cannotKillTest() {
    assertFalse(this.rookBlack.canKill(this.rookCannotKill));
  }
}