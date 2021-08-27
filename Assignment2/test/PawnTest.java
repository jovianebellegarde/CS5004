import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * A JUnit test class for the Pawn class.
 */
public class PawnTest {
  ChessPiece pawnWhite;
  ChessPiece pawnBlack;
  ChessPiece pawnCanKill;
  ChessPiece pawnCannotKill;
  ChessPiece pawnCannotKill2;
  ChessPiece pawnInvalid;

  @Before
  public void setUp() {
    this.pawnBlack = new Pawn(1, 1, Color.BLACK);
    this.pawnWhite = new Pawn(2, 2, Color.WHITE);
    this.pawnCanKill = new Pawn(3, 3, Color.BLACK);
    this.pawnCannotKill = new Pawn(3, 3, Color.WHITE);
    this.pawnCannotKill2 = new Pawn(5, 6, Color.BLACK);
  }

  @Test (expected = IllegalArgumentException.class)
  public void pieceOutOfBounds() {
    this.pawnInvalid = new Pawn(-1, 0, Color.BLACK);
    assertEquals("This piece is placed out of bounds.", this.pawnInvalid);

    this.pawnInvalid = new Pawn(8, 8, Color.WHITE);
    assertEquals("This piece is placed out of bounds.", this.pawnInvalid);
  }

  @Test
  public void getRowTest() {
    assertEquals(2, this.pawnWhite.getRow());
  }

  @Test
  public void getColumnTest() {
    assertEquals(2, this.pawnWhite.getColumn());
  }

  @Test
  public void getColorTest() {
    assertEquals(Color.WHITE, this.pawnWhite.getColor());
  }

  @Test
  public void canMoveTest() {
    // black moving down
    assertTrue(this.pawnBlack.canMove(0, 1));
  }

  @Test
  public void cannotMoveTest() {
    // black moving back
    assertFalse(this.pawnBlack.canMove(2, 1));

    // black moving 2, and out of bounds
    assertFalse(this.pawnBlack.canMove(-1, 1));

    // white moving up 2
    assertFalse(this.pawnWhite.canMove(4, 2));

    // white moving up 3
    assertFalse(this.pawnWhite.canMove(5, 2));

    // moving back
    assertFalse(this.pawnWhite.canMove(1, 2));
  }

  @Test
  public void canKillTest() {
    assertTrue(this.pawnWhite.canKill(this.pawnCanKill));
  }

  @Test
  public void cannotKillTest() {
    // same color
    assertFalse(this.pawnWhite.canKill(this.pawnCannotKill));

    // can't move there
    assertFalse(this.pawnWhite.canKill(this.pawnCannotKill2));
  }
}