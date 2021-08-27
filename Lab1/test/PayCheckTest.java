import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the PayCheck class.
 */
public class PayCheckTest {
  private PayCheck tifa;
  private PayCheck cloud;
  private PayCheck cid;
  private static final double DELTA = 0.01;

  @Before
  public void setUp() {
    this.tifa = new PayCheck("Tifa", 75.25, 43.50);
    this.cloud = new PayCheck("Cloud", 100.06, 35.45);
    this.cid = new PayCheck("Cid", 95.00, 50);
  }

  @Test
  public void getNameTest() {
    assertEquals("Tifa", this.tifa.getName());
    assertEquals("Cloud", this.cloud.getName());
    assertEquals("Cid", this.cid.getName());
  }

  @Test
  public void getTotalPayTest() {
    assertEquals(3015.25, this.tifa.getTotalPay(), DELTA);
    assertEquals(3547.13, this.cloud.getTotalPay(), DELTA);
    assertEquals(3815.00, this.cid.getTotalPay(), DELTA);
  }

  @Test
  public void toStringTest() {
    assertEquals("$3015.25", this.tifa.toString());
    assertEquals("$3547.13", this.cloud.toString());
    assertEquals("$3815.00", this.cid.toString());
  }
}