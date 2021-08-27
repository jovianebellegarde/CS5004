/**
 * This interface contains all operations that polynomials support.
 */
public interface Polynomial {

  /**
   * Recursively calls itself and traverses the polynomial to add the term with the given
   * coefficient and power.
   * @param coefficient an int.
   * @param power an int.
   * @throws IllegalArgumentException if the power is negative.
   */
  void addTerm(int coefficient, int power) throws IllegalArgumentException;

  /**
   * Recursively calls itself and traverses the polynomial to remove the term with the given power.
   * @param power an int.
   * @throws IllegalArgumentException if the power is negative.
   */
  void removeTerm(int power) throws IllegalArgumentException;

  /**
   * Recursively calls itself and traverses the polynomial to get the degree.
   * @return an int.
   */
  int getDegree();

  /**
   * Recursively calls itself and traverses the polynomial to find the coefficient
   * of the given power.
   * @param power an int.
   * @return an int.
   */
  int getCoefficient(int power) throws IllegalArgumentException;

  /**
   * Recursively calls itself and traverses the polynomial to evaluate the polynomial term by term.
   * @param decimalNumber a double.
   * @return a double result.
   */
  double evaluate(double decimalNumber);

  /**
   * Recursively calls itself and traverses the polynomial to add another polynomial to this one.
   * @param other Polynomial.
   * @return a Polynomial.
   * @throws IllegalArgumentException if the other polynomial is not of type polynomial.
   */
  Polynomial add(Polynomial other) throws IllegalArgumentException;
}
