import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * A JUnit test class for the Bishop class.
 */
public class BishopTest {
  ChessPiece bishopBlack;
  ChessPiece bishopWhite;
  ChessPiece bishopCanKill;
  ChessPiece bishopCannotKill;
  ChessPiece bishopCannotKill2;
  ChessPiece bishopInvalid;

  @Before
  public void setUp() {
    this.bishopBlack = new Bishop(6, 5, Color.BLACK);
    this.bishopWhite = new Bishop(1, 7, Color.WHITE);
    this.bishopCanKill = new Bishop(3, 2, Color.WHITE);
    this.bishopCannotKill = new Bishop(3, 2, Color.BLACK);
    this.bishopCannotKill2 = new Bishop(0, 0, Color.WHITE);
  }

  @Test(expected = IllegalArgumentException.class)
  public void pieceOutOfBoundsTest() {
    this.bishopInvalid = new Bishop(9, 9, Color.BLACK);
    assertEquals("This piece is placed out of bounds.", this.bishopInvalid);

    this.bishopInvalid = new Bishop(-1, -1, Color.WHITE);
    assertEquals("This piece is place out of bounds.", this.bishopInvalid);
  }

  @Test
  public void getRowTest() {
    assertEquals(6, this.bishopBlack.getRow());
  }

  @Test
  public void getColumnTest() {
    assertEquals(5, this.bishopBlack.getColumn());
  }

  @Test
  public void getColorTest() {
    assertEquals(Color.BLACK, this.bishopBlack.getColor());
  }

  @Test
  public void canMoveTest() {
    // northwest
    assertTrue(this.bishopBlack.canMove(7, 4));

    // northeast
    assertTrue(this.bishopBlack.canMove(7, 6));

    // southwest
    assertTrue(this.bishopBlack.canMove(5, 4));

    // southeast
    assertTrue(this.bishopBlack.canMove(6, 5));
  }

  @Test
  public void cannotMoveTest() {
    // impossible move
    assertFalse(this.bishopBlack.canMove(2, 3));

    // move horizontal
    assertFalse(this.bishopBlack.canMove(5, 5));

    // move vertical
    assertFalse(this.bishopBlack.canMove(6, 4));
  }

  @Test
  public void canKillTest() {
    assertTrue(this.bishopBlack.canKill(this.bishopCanKill));
  }

  @Test
  public void cannotKillTest() {
    // same color
    assertFalse(this.bishopBlack.canKill(this.bishopCannotKill));

    // cannot move there
    assertFalse(this.bishopBlack.canKill(this.bishopCannotKill2));
  }
}