import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the FibonacciCounter class.
 */
public class FibonacciCounterTest {
  private FibonacciCounter zero;
  private FibonacciCounter three;
  private FibonacciCounter six;
  private FibonacciCounter seven;
  private static final double DELTA = 0.01;

  @Before
  public void setUp() {
    this.zero = new FibonacciCounter(0);
    this.three = new FibonacciCounter(3);
    this.six = new FibonacciCounter(6);
    this.seven = new FibonacciCounter(7);
  }

  @Test (expected = IllegalArgumentException.class)
  public void negativeParameterTest() {
    assertEquals("Can't have a negative number.",
            new FibonacciCounter(-4).toString());
  }

  @Test
  public void getInitialCountTest() {
    assertEquals(0, this.zero.getInitialCount());
    assertEquals(3, this.three.getInitialCount());
    assertEquals(6, this.six.getInitialCount());
    assertEquals(7, this.seven.getInitialCount());
  }

  @Test
  public void incrementTest() {
    assertEquals(1, this.zero.increment().getInitialCount(), DELTA);
    assertEquals(4, this.three.increment().getInitialCount(), DELTA);
    assertEquals(7, this.six.increment().getInitialCount(), DELTA);
    assertEquals(8, this.seven.increment().getInitialCount(), DELTA);
  }

  @Test
  public void decrementTest() {
    assertEquals(0, this.zero.decrement().getInitialCount());
    assertEquals(2, this.three.decrement().getInitialCount());
    assertEquals(5, this.six.decrement().getInitialCount());
    assertEquals(6, this.seven.decrement().getInitialCount());
  }

  @Test
  public void getCurrentCountTest() {
    assertEquals(0, this.zero.getCurrentCount(), DELTA);
    assertEquals(2, this.three.getCurrentCount(), DELTA);
    assertEquals(8, this.six.getCurrentCount(), DELTA);
    assertEquals(13, this.seven.getCurrentCount(), DELTA);
  }
}