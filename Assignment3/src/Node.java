/**
 * This interface contains all operations that polynomial nodes support.
 */
public interface Node {

  /**
   * Adds a term with the given coefficient and power and returns it back to the polynomial.
   * @param coefficient an int.
   * @param power an int.
   * @return the term to be added.
   * @throws IllegalArgumentException if the given power is negative.
   */
  Node addTerm(int coefficient, int power) throws IllegalArgumentException;

  /**
   * Finds and removes the term given the power.
   * @param power an int.
   * @return an int.
   * @throws IllegalArgumentException the term to be removed.
   */
  Node removeTerm(int power) throws IllegalArgumentException;

  /**
   * Gets the degree of this term.
   * @return an int.
   */
  int getDegree();

  /**
   * Gets the coefficient of this term with the given power.
   * @param power an int.
   * @return an int.
   * @throws IllegalArgumentException if the power is negative.
   */
  int getCoefficient(int power) throws IllegalArgumentException;

  /**
   * Gets the power of this term.
   * @return an int.
   */
  int getPower();

  /**
   * Gets the rest of this term.
   * @return the rest of the term.
   */
  Node getRest();

  /**
   * Evaluates this term with the given decimalNumber.
   * @param decimalNumber a double.
   * @return a double.
   */
  double evaluate(double decimalNumber);

  /**
   * String representation of this term.
   * @return a string.
   */
  String toString();
}
