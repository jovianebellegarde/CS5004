package bank;

/**
 * This class represents a checking account, extended from the abstract bank account,
 * which has a starter amount.
 */
public class CheckingAccount extends AbstractAccount {

  private final double minimumBalanceOf100 = 100.00;

  /**
   * Constructs a CheckingAccount object and initializes it to the given starter amount.
   * @param initialAmount the amount used to open this bank account.
   */
  public CheckingAccount(double initialAmount) {
    super(initialAmount);
  }

  /**
   * Gets the minimum balance which is 100.
   * @return a double the minimum balance.
   */
  public double getMinimumBalanceOf100() {
    return this.minimumBalanceOf100;
  }

  /**
   * Checks to see if this checking account amount is less than 100 to charge the $5 fee.
   */
  @Override
  public void performMonthlyMaintenance() {
    double maintenanceFee = 5.00;
    if (super.getBalance() < this.minimumBalanceOf100) {
      super.initialAmount -= maintenanceFee;
    }
  }
}
