/**
 * This class represents a polynomial Term node implemented from the PolynomialImpl class.
 */
class TermNode implements Node {
  private final int coefficient;
  private final int power;
  private Node rest;

  /**
   * Constructs a polynomial object with the given coefficient, power, and rest.
   * @param coefficient an int.
   * @param power an int.
   * @param rest the rest of this node.
   * @throws IllegalArgumentException if the given power is negative.
   */
  public TermNode(int coefficient, int power, Node rest) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException("Can't have a negative power.");
    }
    this.coefficient = coefficient;
    this.power = power;
    this.rest = rest;
  }

  /**
   * Returns a term to the polynomial with the given coeifficent and power.
   * @param coefficient an int.
   * @param power an int.
   * @return a term node.
   * @throws IllegalArgumentException if the given power is negative.
   */
  @Override
  public Node addTerm(int coefficient, int power) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException("Can't have a negative power.");
    }
    if (this.power < power) {
      return new TermNode(coefficient, power, this);
    }
    if (this.power == power) {
      if (this.coefficient + coefficient == 0) {
        return this.rest;
      }
      return new TermNode(this.coefficient + coefficient, this.power, this.rest);
    }
    this.rest = this.rest.addTerm(coefficient, power);
    return this;
  }

  /**
   * Returns the term to be removed from the polynomial with the given power.
   * @param power an int.
   * @return a node.
   * @throws IllegalArgumentException if the power is negative.
   */
  @Override
  public Node removeTerm(int power) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException("Can't have a negative power.");
    }
    if (this.power == power) {
      return this.rest;
    }
    this.rest = this.rest.removeTerm(power);
    return this;
  }

  /**
   * Gets the coefficient of this term with the given power.
   * @param power an int.
   * @return an int.
   * @throws IllegalArgumentException if the given power is negative.
   */
  @Override
  public int getCoefficient(int power) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException("Can't have a negative power.");
    }
    if (this.power == power) {
      return this.coefficient;
    }
    return this.rest.getCoefficient(power);
  }

  /**
   * Gets the int of this term coefficient.
   * @return an int.
   */
  public int getTermCoefficient() {
    return this.coefficient;
  }

  /**
   * Gets the power of this term.
   * @return an int.
   */
  @Override
  public int getPower() {
    return this.power;
  }

  /**
   * Gets the degree of this term.
   * @return an int.
   */
  public int getDegree() {
    return this.power;
  }

  /**
   * Gets the rest of this term.
   * @return the rest of this term node.
   */
  @Override
  public Node getRest() {
    return this.rest;
  }

  /**
   * Evaluates this term with the given decimal number.
   * @param decimalNumber a double.
   * @return a double result.
   */
  @Override
  public double evaluate(double decimalNumber) {
    return this.coefficient * Math.pow(decimalNumber, this.power)
            + this.rest.evaluate(decimalNumber);
  }

  /**
   * String representation of this node.
   * @return a string.
   */
  @Override
  public String toString() {
    // Checking the last term to see if we only have a coefficient or 1 term.
    if (this.power == 0) {
      if (this.rest instanceof EmptyNode) {
        return String.valueOf(this.coefficient);
      }
      return String.valueOf(this.coefficient);
    }
    // Adding the appropriate sign here.
    if (this.rest instanceof EmptyNode) {
      return this.coefficient + "x^" + this.power;
    } else if (this.rest.getCoefficient(this.rest.getPower()) < 0) {
      return this.coefficient + "x^" + this.power + " " + this.rest.toString();
    } else {
      return this.coefficient + "x^" + this.power + " +" + this.rest.toString();
    }
  }
}
