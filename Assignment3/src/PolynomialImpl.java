import java.util.Objects;

/**
 * This class represents actions that all polynomials should support and implements the Polynomial
 * interface.
 */
class PolynomialImpl implements Polynomial {
  private Node head;

  /**
   * Constructs a polynomial object with the given polynomial string by parsing the string and
   * converting it into integers.
   * @param polynomial a string.
   */
  public PolynomialImpl(String polynomial) {
    // Checking that null object isn't passed.
    Objects.requireNonNull(polynomial);

    /* For matches method: https://stackoverflow.com/questions/14635391/java-function
    -to-return-if-string-contains-illegal-characters
     */
    if (polynomial.matches("[`?!$&()=.,{}<>@*%|/~_#]\\[\\]\"")) {
      throw new IllegalArgumentException("Illegal symbol in polynomial.");
    }
    if (polynomial.matches("")) {
      throw new NumberFormatException("Passed in empty string.");
    }

    // Splitting the polynomial string by spaces for each term.
    String[] terms = polynomial.split(" ");
    this.head = new EmptyNode();
    // For each term in polynomial split by x^.
    for (String term : terms) {
      /* CoefficientAndPower array contains only the coefficient and power of each term.
       * CoefficientAndPower[0] will store just the coefficient before the x^.
       * CoefficientAndPower[1] will store any power after the x^.
       */
      String[] coefficientAndPower = term.split("x\\^");
      int coefficient = Integer.parseInt(coefficientAndPower[0]);
      int power;
      if (coefficientAndPower.length != 1) {
        power = Integer.parseInt(coefficientAndPower[1]);
        if (power < 0) {
          throw new IllegalArgumentException("Can't have a negative power.");
        }
      } else {
        power = 0;
      }
      this.head = this.head.addTerm(coefficient, power);
    }
  }

  /**
   * Constructs an empty polynomial object.
   */
  public PolynomialImpl() {
    this.head = new EmptyNode();
  }

  @Override
  public void addTerm(int coefficient, int power) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException("Can't have a negative power.");
    }
    this.head = this.head.addTerm(coefficient, power);
  }

  @Override
  public void removeTerm(int power) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException("Can't have a negative power.");
    }
    this.head.removeTerm(power);
  }

  @Override
  public int getDegree() {
    return this.head.getDegree();
  }

  @Override
  public int getCoefficient(int power) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException("Can't have a negative power.");
    }
    return this.head.getCoefficient(power);
  }

  @Override
  public double evaluate(double decimalNumber) {
    return this.head.evaluate(decimalNumber);
  }

  @Override
  public Polynomial add(Polynomial other) throws IllegalArgumentException {
    if (!(other instanceof Polynomial)) {
      throw new IllegalArgumentException("Need to pass in a polynomial.");
    }
    PolynomialImpl poly = (PolynomialImpl) other;
    PolynomialImpl newPol = new PolynomialImpl();
    PolynomialImpl temp = new PolynomialImpl();
    if (poly.head instanceof TermNode) {
      newPol.head = this.head.addTerm(((TermNode) poly.head).getTermCoefficient(),
              poly.head.getDegree());
      temp.head = poly.head.getRest();
      newPol.add(temp);
    } else {
      newPol.head = this.head;
      return newPol;
    }
    return newPol;
  }

  @Override
  public String toString() {
    if (this.head instanceof EmptyNode) {
      return "0";
    }
    return this.head.toString();
  }
}
