package bank;

/**
 * This interface contains all operations that all types of bank accounts should support.
 */
public interface IAccount {

  /**
   * Takes in a single double representing the amount deposited into the account.
   * If the amount specified is negative, an IllegalArgumentException is thrown.
   */
  void deposit(double amount);

  /**
   * Takes in a single double representing the amount to withdraw from the account.
   * If the amount specified is greater than the balance available or the amount is less than 0,
   * this operation fails and returns false.
   * @param amount a double amount to withdraw from bank account.
   * @return a boolean true or false if amount can be withdrawn from account.
   */
  boolean withdraw(double amount);

  /**
   * Gets the current account balance.
   * @return a double of the current account balance.
   */
  double getBalance();

  /**
   * Charges any fees and then reset transaction counters to zero.
   */
  void performMonthlyMaintenance();
}
