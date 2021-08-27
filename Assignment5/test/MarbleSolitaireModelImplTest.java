import org.junit.Before;
import org.junit.Test;

import cs5004.marblesolitaire.model.MarbleSolitaireModel;
import cs5004.marblesolitaire.model.MarbleSolitaireModelImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * A JUnit test class for the MarbleSolitaireImpl class.
 */
public class MarbleSolitaireModelImplTest {
  private MarbleSolitaireModel boardOne;
  private MarbleSolitaireModel boardTwo;
  private MarbleSolitaireModel boardThree;
  private MarbleSolitaireModel boardFour;

  @Before
  public void setUp() {
    this.boardOne = new MarbleSolitaireModelImpl();
    this.boardTwo = new MarbleSolitaireModelImpl(0, 3);
    this.boardThree = new MarbleSolitaireModelImpl(5);
    this.boardFour = new MarbleSolitaireModelImpl(5, 0, 6);
  }

  @Test
  public void validConstructorOneTest() {
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", this.boardOne.getGameState());
  }

  @Test
  public void validConstructorTwoTest() {
    assertEquals("    O _ O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", this.boardTwo.getGameState());
  }

  @Test
  public void validConstructorThreeTest() {
    assertEquals("        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O _ O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O", this.boardThree.getGameState());
  }

  @Test
  public void validConstructorFourTest() {
    assertEquals("        O O _ O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O", this.boardFour.getGameState());
  }

  @Test (expected = IllegalArgumentException.class)
  public void emptyInvalidSpotTest() {
    MarbleSolitaireModel invalidConstructorFour = new MarbleSolitaireModelImpl(0, 4, 4);
    assertEquals("Invalid arm, or sRow, or sCol.", invalidConstructorFour.toString());

    MarbleSolitaireModel invalidConstructorFour2 = new MarbleSolitaireModelImpl(3, 0, 1);
    assertEquals("Invalid arm, or sRow, or sCol.", invalidConstructorFour2.toString());

    MarbleSolitaireModel invalidConstructorFour3 = new MarbleSolitaireModelImpl(3, 1, 6);
    assertEquals("Invalid arm, or sRow, or sCol.", invalidConstructorFour3.toString());

    MarbleSolitaireModel validConstructorFour = new MarbleSolitaireModelImpl(5, 0, 0);
    assertEquals("Invalid arm, or sRow, or SCol.", validConstructorFour.toString());
  }

  @Test (expected = IllegalArgumentException.class)
  public void invalidBoardTest() {
    MarbleSolitaireModel invalidEmptyBoardTwo = new MarbleSolitaireModelImpl(0, 0);
    assertEquals(String.format("Invalid empty cell position (%d,%d)", 0, 0),
            invalidEmptyBoardTwo.getGameState());

    MarbleSolitaireModel invalidArmBoardThree = new MarbleSolitaireModelImpl(6);
    assertEquals("Invalid arm thickness.", invalidArmBoardThree.getGameState());

    MarbleSolitaireModel invalidEmptyBoardFour = new MarbleSolitaireModelImpl(0,12, 12);
    assertEquals("Invalid arm or empty spot.", invalidEmptyBoardFour.getGameState());

    MarbleSolitaireModel invalidArmBoardFour = new MarbleSolitaireModelImpl(0, 4, 6);
    assertEquals("Need a valid arm thickness.", invalidArmBoardFour.getGameState());
  }

  @Test
  public void moveTest() {
    this.boardOne.move(1, 3, 3, 3);
    assertEquals("    O O O\n"
            + "    O _ O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", this.boardOne.getGameState());
    assertEquals(31, this.boardOne.getScore());

    this.boardOne.move(2, 1, 2, 3);
    assertEquals("    O O O\n"
            + "    O _ O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O", this.boardOne.getGameState());
    assertEquals(30, this.boardOne.getScore());

    this.boardThree.move(8, 6, 6, 6);
    assertEquals("        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O _ O O O O O O\n"
            + "O O O O O O _ O O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O", this.boardThree.getGameState());

    this.boardThree.move(10, 6, 8, 6);
    assertEquals("        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O _ O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "        O O _ O O\n"
            + "        O O _ O O\n"
            + "        O O O O O\n"
            + "        O O O O O", this.boardThree.getGameState());
  }


  @Test (expected = IllegalArgumentException.class)
  public void invalidMoveTest() {
    // 2 spots up
    this.boardOne.move(2, 3, 0, 3);
    assertEquals("This is not a valid move.", this.boardOne.getGameState());

    // 1 spot down
    this.boardOne.move(2, 3, 3, 3);
    assertEquals("This is not a valid move.", this.boardOne.getGameState());

    // 3 spots down
    this.boardOne.move(2, 3, 3, 5);
    assertEquals("This is not a valid move.", this.boardOne.getGameState());

    // marble to marble 2 spots right
    this.boardOne.move(2, 3, 2, 5);
    assertEquals("This is not a valid move.", this.boardOne.getGameState());

    // marble to marble 2 spots left
    this.boardOne.move(2, 3, 1, 3);
    assertEquals("This is not a valid move.", this.boardOne.getGameState());

    // marble 2 down empty in between
    this.boardOne.move(2, 3, 4, 3);
    assertEquals("This is not a valid move.", this.boardOne.getGameState());

    // trying to move up left diagonal to empty spot
    this.boardThree.move(8, 9, 6, 6);
    assertEquals("This is not a valid move.", this.boardThree.getGameState());

    // move to the left 3 spots
    this.boardThree.move(6, 10, 6, 6);
    assertEquals("This is not a valid move.", this.boardThree.getGameState());
  }

  @Test
  public void isGameOverTest() {
    assertFalse(this.boardOne.isGameOver());
    assertFalse(this.boardTwo.isGameOver());
    assertFalse(this.boardThree.isGameOver());
    assertFalse(this.boardFour.isGameOver());
  }

  @Test
  public void getScoreTest() {
    assertEquals(32, this.boardOne.getScore());
    assertEquals(32, this.boardTwo.getScore());
    assertEquals(104, this.boardThree.getScore());
    assertEquals(104, this.boardFour.getScore());
  }

}