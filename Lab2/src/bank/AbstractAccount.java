package bank;

/**
 * This abstract class implements methods from the IAccount interface that any bank account should
 * be able to do.
 */
public abstract class AbstractAccount implements IAccount {

  protected double initialAmount;

  /**
   * Constructs an AbstractAccount object and initializes it to the given starter amount.
   * @param initialAmount the amount used to open this bank account.
   * @throws IllegalArgumentException if starter amount is less than 0.01.
   */
  public AbstractAccount(double initialAmount) throws IllegalArgumentException {
    double oneCent = 0.01;
    if (initialAmount < oneCent) {
      throw new IllegalArgumentException("Can't open account with a balance of 0 or less.");
    }
    this.initialAmount = initialAmount;
  }

  @Override
  public void deposit(double amount) throws IllegalArgumentException {
    if (amount < 0) {
      throw new IllegalArgumentException("Can't deposit an amount less than 0.");
    } else {
      this.initialAmount += amount;
    }
  }

  @Override
  public boolean withdraw(double amount) {
    boolean result;
    if (this.getBalance() < amount || amount < 0) {
      result = false;
    } else {
      this.initialAmount -= amount;
      result = true;
    }
    return result;
  }

  @Override
  public double getBalance() {
    return this.initialAmount;
  }

  /**
   * Makes a string representation of this bank account.
   * @return a formatted string.
   */
  @Override
  public String toString() {
    return String.format("$%.2f", this.getBalance());
  }
}
