package bank;

/**
 * This class represents a savings account, extended from the abstract bank account,
 * which has a starter amount.
 */
public class SavingsAccount extends AbstractAccount {

  private int numberOfWithdrawals;

  /**
   * Constructs a SavingsAccount object and initializes it to the given starter amount. It also
   * sets numberOfWithdrawals to 0 as the account is opened.
   * @param initialAmount the amount used to open this bank account.
   */
  public SavingsAccount(double initialAmount) {
    super(initialAmount);
    this.numberOfWithdrawals = 0;
  }

  /**
   * Takes in a single double representing the amount to withdraw from the account. If the amount
   * specified is greater than the balance available or the amount is less than 0, this operation
   * fails and returns false. Incrementing the numberOfWithdrawals by 1 for maintenance fees.
   * @param amount a double amount to withdraw from bank account.
   * @return a boolean true or false if amount can be withdrawn from account.
   */
  @Override
  public boolean withdraw(double amount) {
    this.numberOfWithdrawals++;
    return super.withdraw(amount);
  }

  /**
   * Gets the number of total withdrawals for this savings account.
   * @return an int the number of withdrawals.
   */
  public int getNumberOfWithdrawals() {
    return this.numberOfWithdrawals;
  }

  /**
   * Checks to see if this savings account had more than 6 withdrawals.
   * Sets numberOfWithdrawals to 0 to reset.
   */
  @Override
  public void performMonthlyMaintenance() {
    int sixWithdrawals = 6;
    int fee = 14;
    if (this.numberOfWithdrawals > sixWithdrawals) {
      super.initialAmount -= fee;
    }
    this.numberOfWithdrawals = 0;
  }
}
