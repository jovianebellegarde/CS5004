/**
 * This class represents an employee which has a name, hours worked, and a pay rate.
 */
public class Employee {
  private final String name;
  private double hoursWorked;
  private final double payRate;

  /**
   * Constructs an Employee object and initializes it to the given name and pay rate.
   * @param name the name of this employee.
   * @param payRate their hourly pay.
   */
  public Employee(String name, double payRate) {
    this.name = name;
    this.payRate = payRate;
    this.hoursWorked = 0;
  }

  /**
   * Gets the name of this employee.
   * @return the name of this employee.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Gets the pay rate of this employee.
   * @return the pay rate of this employee.
   */
  public double getPayRate() {
    return this.payRate;
  }

  /**
   * Get the hours worked of this employee.
   * @return a double of the hours worked of this employee.
   */
  public double getHoursWorked() {
    return this.hoursWorked;
  }

  /**
   * Adds the hours worked to the initial hours of zero.
   * @param addedHours a double of the number of hours the employee is adding.
   * @return a double of the total hours the employee worked.
   */
  public double addHoursWorked(double addedHours) throws IllegalArgumentException {
    if (addedHours < 0) {
      throw new IllegalArgumentException("Work hours can't be negative.");
    }
    return this.hoursWorked += addedHours;
  }

  /**
   * Resets the hours that the employee worked to 0.
   * @return a double 0 hours to reset the hours worked back to 0.
   */
  public double resetHoursWorked() {
    return this.hoursWorked;
  }

  /**
   * Returns a new PayCheck object with the employee's name, pay rate, and hours worked.
   * @return a new PayCheck object.
   */
  public PayCheck getWeeklyCheck() {
    return new PayCheck(this.name, this.payRate, this.hoursWorked);
  }

  /**
   * Makes a string representation of this employee.
   * @return a formatted string.
   */
  public String toString() {
    return this.name;
  }
}
