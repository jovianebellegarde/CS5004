/**
 * This class represents a polynomial Empty node polynomial implemented from the
 * PolynomialImpl class.
 */
class EmptyNode implements Node {

  /**
   * Returns a new node with the given coefficient and power for this empty node.
   * @param coefficient an int.
   * @param power an int.
   * @return a new term.
   * @throws IllegalArgumentException if the power is negative.
   */
  @Override
  public Node addTerm(int coefficient, int power) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException("Can't have a negative power.");
    }
    return new TermNode(coefficient, power, this);
  }

  /**
   * Returns this empty node when called with the given power.
   * @param power an int.
   * @return this empty node.
   * @throws IllegalArgumentException if the power is negative.
   */
  @Override
  public Node removeTerm(int power) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException("Can't have a negative power.");
    }
    return this;
  }

  /**
   * Returns 0 when called on this empty node.
   * @return an int.
   */
  @Override
  public int getDegree() {
    return 0;
  }

  /**
   * Returns 0 when called with the given power with this empty node.
   * @param power an int.
   * @return an int.
   * @throws IllegalArgumentException if the power is negative.
   */
  @Override
  public int getCoefficient(int power) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException("Can't have a negative power.");
    }
    return 0;
  }

  /**
   * Returns 0 when called on this empty node.
   * @return an int.
   */
  @Override
  public int getPower() {
    return 0;
  }

  /**
   * Returns this empty node when called on this empty node.
   * @return this empty node.
   */
  @Override
  public Node getRest() {
    return this;
  }

  /**
   * Returns 0 when called on this empty node.
   * @param decimalNumber a double.
   * @return a double.
   */
  @Override
  public double evaluate(double decimalNumber) {
    return 0;
  }

  /**
   * Returns an empty string when called on this empty node.
   * @return an empty string.
   */
  @Override
  public String toString() {
    return "";
  }
}
