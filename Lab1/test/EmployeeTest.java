import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the Employee class.
 */
public class EmployeeTest {
  private Employee tifa;
  private Employee cloud;
  private Employee cid;
  private static final double DELTA = 0.01;

  @Before
  public void setUp() {
    this.tifa = new Employee("Tifa", 75.25);
    this.cloud = new Employee("Cloud", 100.06);
    this.cid = new Employee("Cid", 95.00);
  }

  @Test
  public void getNameTest() {
    assertEquals("Tifa", this.tifa.getName());
    assertEquals("Cloud", this.cloud.getName());
    assertEquals("Cid", this.cid.getName());
  }

  @Test
  public void getPayRateTest() {
    assertEquals(75.25, this.tifa.getPayRate(), DELTA);
    assertEquals(100.06, this.cloud.getPayRate(), DELTA);
    assertEquals(95.00, this.cid.getPayRate(), DELTA);
  }

  @Test
  public void getHoursWorkedTest() {
    assertEquals(0, this.tifa.getHoursWorked(), DELTA);
    assertEquals(0, this.cloud.getHoursWorked(), DELTA);
    assertEquals(0, this.cid.getHoursWorked(), DELTA);
  }

  @Test
  public void addHoursWorkedTest() {
    assertEquals(35.00, this.tifa.addHoursWorked(35.00), DELTA);
    assertEquals(45.35, this.cloud.addHoursWorked(45.35), DELTA);
    assertEquals(55.17, this.cid.addHoursWorked(55.17), DELTA);
  }

  @Test (expected = IllegalArgumentException.class)
  public void addHoursWorkedExceptionTest() {
    assertEquals(-30, this.tifa.addHoursWorked(-30), DELTA);
  }

  @Test
  public void resetHoursWorkedTest() {
    assertEquals(0, this.tifa.resetHoursWorked(), DELTA);
    assertEquals(0, this.cloud.resetHoursWorked(), DELTA);
    assertEquals(0, this.cid.resetHoursWorked(), DELTA);
  }

  @Test
  public void getWeeklyCheckTest() {
    // names in PayCheck object
    assertEquals("Tifa", this.tifa.getWeeklyCheck().getName());
    assertEquals("Cloud", this.cloud.getWeeklyCheck().getName());
    assertEquals("Cid", this.cid.getWeeklyCheck().getName());

    // pay rates in PayCheck object
    assertEquals(0, this.tifa.getWeeklyCheck().getTotalPay(), DELTA);
    assertEquals(0, this.cloud.getWeeklyCheck().getTotalPay(), DELTA);
    assertEquals(0, this.cid.getWeeklyCheck().getTotalPay(), DELTA);
  }

  @Test
  public void toStringTest() {
    assertEquals("Tifa", this.tifa.toString());
    assertEquals("Cloud", this.cloud.toString());
    assertEquals("Cid", this.cid.toString());
  }
}