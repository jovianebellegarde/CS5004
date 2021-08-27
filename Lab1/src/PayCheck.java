/**
 * This class represents a paycheck which has a name and the total pay.
 */
public class PayCheck {
  private final String name;
  private final double totalPay;

  /**
   * Constructs a PayCheck object and initializes it to the given name, pay rate, and hours worked.
   * @param name the name for this paycheck.
   * @param payRate the hourly rate for this paycheck.
   * @param hoursWorked the hours worked for this paycheck.
   */
  public PayCheck(String name, double payRate, double hoursWorked) {
    double forty = 40;
    double overtime = 1.5;
    this.name = name;

    // calculating overtime
    if (hoursWorked <= forty) {
      this.totalPay = payRate * hoursWorked;
    } else {
      double difference = hoursWorked - forty;
      this.totalPay = forty * payRate + difference * overtime;
    }
  }

  /**
   * Gets the name for this paycheck.
   * @return a string name for this paycheck.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Gets the total pay for this paycheck.
   * @return double of the total pay for this paycheck.
   */
  public double getTotalPay() {
    return this.totalPay;
  }

  /**
   * Makes a string representation of this paycheck.
   * @return a formatted string.
   */
  public String toString() {
    return String.format("$%.2f", this.getTotalPay());
  }
}

