import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * A JUnit test class for the Knight class.
 */
public class KnightTest {
  ChessPiece knightBlack;
  ChessPiece knightWhite;
  ChessPiece knightCanKill;
  ChessPiece knightCannotKill;
  ChessPiece knightInvalid;

  @Before
  public void setUp() {
    this.knightBlack = new Knight(7, 7, Color.BLACK);
    this.knightWhite = new Knight(0, 0, Color.WHITE);
    this.knightCanKill = new Knight(5, 6, Color.WHITE);
    this.knightCannotKill = new Knight(5, 6, Color.BLACK);
  }

  @Test(expected = IllegalArgumentException.class)
  public void pieceOutOfBoundsTest() {
    this.knightInvalid = new Knight(8, 8, Color.BLACK);
    assertEquals("This piece is placed out of bounds.", this.knightInvalid);
  }

  @Test
  public void getRowTest() {
    assertEquals(7, this.knightBlack.getRow());
  }

  @Test
  public void getColumnTest() {
    assertEquals(7, this.knightBlack.getColumn());
  }

  @Test
  public void getColorTest() {
    assertEquals(Color.BLACK, this.knightBlack.getColor());
  }

  @Test
  public void canMoveTest() {
    // down 2, left 1
    assertTrue(this.knightBlack.canMove(5, 6));

    // down 1, left 2
    assertTrue(this.knightBlack.canMove(6, 5));
  }

  @Test
  public void cannotMoveTest() {
    // out of bounds
    assertFalse(this.knightBlack.canMove(5, 8));

    assertFalse(this.knightBlack.canMove(0, 0));
  }

  @Test
  public void canKillTest() {
    assertTrue(this.knightBlack.canKill(this.knightCanKill));
  }

  @Test
  public void cannotKillTest() {
    assertFalse(this.knightBlack.canKill(this.knightCannotKill));
  }
}