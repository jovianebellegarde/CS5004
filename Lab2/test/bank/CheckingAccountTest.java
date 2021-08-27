package bank;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the Checking Account class.
 */
public class CheckingAccountTest {
  CheckingAccount checking101;
  CheckingAccount checking100;
  CheckingAccount checking99;
  CheckingAccount checking50;
  CheckingAccount checking01;

  private final static double DELTA = 0.01;
  private final static double CHECKING_101 = 101.00;
  private final static double CHECKING_100 = 100.00;
  private final static double CHECKING_99 = 99.25;
  private final static double CHECKING_50 = 50.45;
  private final static double CHECKING_01 = 0.01;

  @Before
  public void setUp() {
    this.checking101 = new CheckingAccount(101.00);
    this.checking100 = new CheckingAccount(100.00);
    this.checking99 = new CheckingAccount(99.25);
    this.checking50 = new CheckingAccount(50.45);
    this.checking01 = new CheckingAccount(0.01);
  }

  @Test (expected = IllegalArgumentException.class)
  public void zeroOrNegativeStarterAmount() {
    CheckingAccount checking0 = new CheckingAccount(0.00);
    IllegalArgumentException exception = new IllegalArgumentException("Can't open account "
            + "with a balance of 0 or less.");
    assertEquals(exception, checking0);

    CheckingAccount checkingNegative10 = new CheckingAccount(-10.45);
    assertEquals(exception, checkingNegative10);
  }

  @Test
  public void getMinimumBalanceTest() {
    double oneHundred = 100.00;
    assertEquals(oneHundred, this.checking101.getMinimumBalanceOf100(), DELTA);
    assertEquals(oneHundred, this.checking100.getMinimumBalanceOf100(), DELTA);
    assertEquals(oneHundred, this.checking99.getMinimumBalanceOf100(), DELTA);
    assertEquals(oneHundred, this.checking50.getMinimumBalanceOf100(), DELTA);
    assertEquals(oneHundred, this.checking01.getMinimumBalanceOf100(), DELTA);
  }

  //deposit
  @Test
  public void depositAndGetBalanceTest() {
    double twentyFive = 25.25;
    this.checking101.deposit(twentyFive);
    this.checking100.deposit(twentyFive);
    this.checking99.deposit(twentyFive);
    this.checking50.deposit(twentyFive);
    this.checking01.deposit(twentyFive);

    assertEquals(twentyFive + CHECKING_101, this.checking101.getBalance(), DELTA);
    assertEquals(twentyFive + CHECKING_100, this.checking100.getBalance(), DELTA);
    assertEquals(twentyFive + CHECKING_99, this.checking99.getBalance(), DELTA);
    assertEquals(twentyFive + CHECKING_50, this.checking50.getBalance(), DELTA);
    assertEquals(twentyFive + CHECKING_01, this.checking01.getBalance(), DELTA);
  }

  @Test
  public void depositZeroTest() {
    this.checking101.deposit(0);
    this.checking100.deposit(0);
    this.checking99.deposit(0);
    this.checking50.deposit(0);
    this.checking01.deposit(0);

    assertEquals(CHECKING_101, this.checking101.getBalance(), DELTA);
    assertEquals(CHECKING_100, this.checking100.getBalance(), DELTA);
    assertEquals(CHECKING_99, this.checking99.getBalance(), DELTA);
    assertEquals(CHECKING_50, this.checking50.getBalance(), DELTA);
    assertEquals(CHECKING_01, this.checking01.getBalance(), DELTA);
  }

  @Test (expected = IllegalArgumentException.class)
  public void depositLessThanZeroTest() {
    double negativeOne = -1.00;
    this.checking101.deposit(negativeOne);
    this.checking100.deposit(negativeOne);
    this.checking99.deposit(negativeOne);
    this.checking50.deposit(negativeOne);
    this.checking01.deposit(negativeOne);

    IllegalArgumentException exception = new IllegalArgumentException("Can't deposit an "
            + "amount less than 0.");
    assertEquals(exception, this.checking101.getBalance());
    assertEquals(exception, this.checking100.getBalance());
    assertEquals(exception, this.checking99.getBalance());
    assertEquals(exception, this.checking50.getBalance());
    assertEquals(exception, this.checking01.getBalance());
  }

  //withdraw
  @Test
  public void withdrawTest() {
    // withdraw regular
    double five = 5.00;
    assertTrue(this.checking101.withdraw(five));
    assertTrue(this.checking100.withdraw(five));
    assertTrue(this.checking99.withdraw(five));
    assertTrue(this.checking50.withdraw(five));
    assertFalse(this.checking01.withdraw(five));

    // checking amount after withdrawal
    assertEquals(CHECKING_101 - five, this.checking101.getBalance(), DELTA);
    assertEquals(CHECKING_100 - five, this.checking100.getBalance(), DELTA);
    assertEquals(CHECKING_99 - five, this.checking99.getBalance(), DELTA);
    assertEquals(CHECKING_50 - five, this.checking50.getBalance(), DELTA);
    assertEquals(CHECKING_01, this.checking01.getBalance(), DELTA);
  }

  @Test
  public void withdrawZeroTest() {
    double zero = 0.00;
    assertTrue(this.checking101.withdraw(zero));
    assertTrue(this.checking100.withdraw(zero));
    assertTrue(this.checking99.withdraw(zero));
    assertTrue(this.checking50.withdraw(zero));
    assertTrue(this.checking01.withdraw(zero));

    // checking amount after withdrawal
    assertEquals(CHECKING_101 - zero, this.checking101.getBalance(), DELTA);
    assertEquals(CHECKING_100 - zero, this.checking100.getBalance(), DELTA);
    assertEquals(CHECKING_99 - zero, this.checking99.getBalance(), DELTA);
    assertEquals(CHECKING_50 - zero, this.checking50.getBalance(), DELTA);
    assertEquals(CHECKING_01 - zero, this.checking01.getBalance(), DELTA);
  }

  @Test
  public void withdrawAllTest() {
    double zero = 0.00;
    assertTrue(this.checking101.withdraw(CHECKING_101));
    assertTrue(this.checking100.withdraw(CHECKING_100));
    assertTrue(this.checking99.withdraw(CHECKING_99));
    assertTrue(this.checking50.withdraw(CHECKING_50));
    assertTrue(this.checking01.withdraw(CHECKING_01));

    assertEquals(zero, this.checking101.getBalance(), DELTA);
    assertEquals(zero, this.checking100.getBalance(), DELTA);
    assertEquals(zero, this.checking99.getBalance(), DELTA);
    assertEquals(zero, this.checking50.getBalance(), DELTA);
    assertEquals(zero, this.checking01.getBalance(), DELTA);
  }

  @Test
  public void withdrawLessThanAmount() {
    double twoHundred = 200.00;
    assertFalse(this.checking101.withdraw(twoHundred));
    assertFalse(this.checking100.withdraw(twoHundred));
    assertFalse(this.checking99.withdraw(twoHundred));
    assertFalse(this.checking50.withdraw(twoHundred));
    assertFalse(this.checking01.withdraw(twoHundred));

    assertEquals(CHECKING_101, this.checking101.getBalance(), DELTA);
    assertEquals(CHECKING_100, this.checking100.getBalance(), DELTA);
    assertEquals(CHECKING_99, this.checking99.getBalance(), DELTA);
    assertEquals(CHECKING_50, this.checking50.getBalance(), DELTA);
    assertEquals(CHECKING_01, this.checking01.getBalance(), DELTA);
  }

  @Test
  public void withdrawNegativeAmount() {
    double negativeTen = -10.00;
    assertFalse(this.checking101.withdraw(negativeTen));
    assertFalse(this.checking100.withdraw(negativeTen));
    assertFalse(this.checking99.withdraw(negativeTen));
    assertFalse(this.checking50.withdraw(negativeTen));
    assertFalse(this.checking01.withdraw(negativeTen));

    assertEquals(CHECKING_101, this.checking101.getBalance(), DELTA);
    assertEquals(CHECKING_100, this.checking100.getBalance(), DELTA);
    assertEquals(CHECKING_99, this.checking99.getBalance(), DELTA);
    assertEquals(CHECKING_50, this.checking50.getBalance(), DELTA);
    assertEquals(CHECKING_01, this.checking01.getBalance(), DELTA);
  }

  @Test
  public void performMonthlyMaintenanceTest() {
    double fee = 5.00;
    this.checking101.performMonthlyMaintenance();
    this.checking100.performMonthlyMaintenance();
    this.checking99.performMonthlyMaintenance();
    this.checking50.performMonthlyMaintenance();
    this.checking01.performMonthlyMaintenance();

    assertEquals(CHECKING_101, this.checking101.getBalance(), DELTA);
    assertEquals(CHECKING_100, this.checking100.getBalance(), DELTA);
    assertEquals(CHECKING_99 - fee , this.checking99.getBalance(), DELTA);
    assertEquals(CHECKING_50 - fee, this.checking50.getBalance(), DELTA);
    assertEquals(CHECKING_01 - fee, this.checking01.getBalance(), DELTA);
  }

  @Test
  public void toStringTest() {
    assertEquals("$101.00", this.checking101.toString());
    assertEquals("$100.00", this.checking100.toString());
    assertEquals("$99.25", this.checking99.toString());
    assertEquals("$50.45", this.checking50.toString());
    assertEquals("$0.01", this.checking01.toString());

    this.checking100.withdraw(5);
    assertEquals("$95.00", this.checking100.toString());
  }
}