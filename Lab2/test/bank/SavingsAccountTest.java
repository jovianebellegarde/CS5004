package bank;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * A JUnit test class for the Savings Account class.
 */
public class SavingsAccountTest {
  SavingsAccount savings100;
  SavingsAccount savings01;
  private final static double DELTA = 0.01;
  private final static double SAVINGS_100 = 100.00;
  private final static double SAVINGS_01 = 0.01;

  @Before
  public void setUp() {
    this.savings100 = new SavingsAccount(100.00);
    this.savings01 = new SavingsAccount(0.01);
  }

  @Test (expected = IllegalArgumentException.class)
  public void zeroOrNegativeStarterAmount() {
    SavingsAccount savings0 = new SavingsAccount(0.00);
    IllegalArgumentException exception = new IllegalArgumentException("Can't open a "
            + "savings account with a balance of 0 or less.");
    assertEquals(exception, savings0);

    SavingsAccount savingsNegative10 = new SavingsAccount(-10.45);
    assertEquals(exception, savingsNegative10);
  }

  @Test
  public void depositTest() {
    double ten = 10;
    this.savings100.deposit(ten);
    this.savings01.deposit(ten);

    assertEquals(SAVINGS_100 + ten, this.savings100.getBalance(), DELTA);
    assertEquals(SAVINGS_01 + ten, this.savings01.getBalance(), DELTA);
  }

  @Test
  public void depositZeroTest() {
    double zero = 0.00;
    this.savings100.deposit(zero);
    this.savings01.deposit(zero);

    assertEquals(SAVINGS_100 + zero, this.savings100.getBalance(), DELTA);
    assertEquals(SAVINGS_01 + zero, this.savings01.getBalance(), DELTA);
  }

  @Test
  public void withdrawalAndNumWithdrawalTest() {
    double ten = 10;
    this.savings100.withdraw(ten);
    this.savings01.withdraw(ten);

    assertEquals(SAVINGS_100 - ten, this.savings100.getBalance(), DELTA);
    assertEquals(SAVINGS_01, this.savings01.getBalance(), DELTA);

    assertEquals(1, this.savings100.getNumberOfWithdrawals());
    assertEquals(1, this.savings01.getNumberOfWithdrawals());
  }

  @Test
  public void withdrawZeroTest() {
    double zero = 0.00;
    this.savings100.withdraw(zero);
    this.savings01.withdraw(zero);

    assertEquals(SAVINGS_100 - zero, this.savings100.getBalance(), DELTA);
    assertEquals(SAVINGS_01 - zero, this.savings01.getBalance(), DELTA);

    assertEquals(1, this.savings100.getNumberOfWithdrawals());
    assertEquals(1, this.savings01.getNumberOfWithdrawals());
  }

  @Test
  public void withdrawAll() {
    this.savings100.withdraw(SAVINGS_100);
    this.savings01.withdraw(SAVINGS_01);

    assertEquals(0.00, this.savings100.getBalance(), DELTA);
    assertEquals(0.00, this.savings01.getBalance(), DELTA);
  }

  @Test
  public void withdrawLessThanAmount() {
    double twoHundred = 200.00;
    assertFalse(this.savings100.withdraw(twoHundred));
    assertFalse(this.savings01.withdraw(twoHundred));

    assertEquals(SAVINGS_100, this.savings100.getBalance(), DELTA);
    assertEquals(SAVINGS_01, this.savings01.getBalance(), DELTA);
  }

  @Test
  public void withdrawNegativeAmount() {
    double negativeTen = -10.00;
    assertFalse(this.savings100.withdraw(negativeTen));
    assertFalse(this.savings01.withdraw(negativeTen));

    assertEquals(SAVINGS_100, this.savings100.getBalance(), DELTA);
    assertEquals(SAVINGS_01, this.savings01.getBalance(), DELTA);
  }

  @Test
  public void performMonthlyMaintenanceTest() {
    double fee = 14.00;
    double ten = 10.00;

    this.savings100.withdraw(1);
    this.savings100.withdraw(1);
    this.savings100.withdraw(1);
    this.savings100.withdraw(1);
    this.savings100.withdraw(1);
    this.savings100.withdraw(1);
    this.savings100.withdraw(1);

    assertEquals(7, this.savings100.getNumberOfWithdrawals(), DELTA);
    assertEquals(93, this.savings100.getBalance(), DELTA);

    this.savings100.performMonthlyMaintenance();
    assertEquals(79, this.savings100.getBalance(), DELTA);
  }

  @Test
  public void toStringTest() {
    assertEquals("$100.00", this.savings100.toString());
    assertEquals("$0.01", this.savings01.toString());
  }
}