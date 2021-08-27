import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * A JUnit test for PolynomialImpl class.
 */
public class PolynomialImplTest {
  private Polynomial poly1;
  private Polynomial poly2;
  private Polynomial poly3;
  private Polynomial poly4;
  private Polynomial poly1Coeff;
  private Polynomial polyMultipleTerms;
  private Polynomial polyOutOfOrder;
  private Polynomial polyNeg;
  private Polynomial polyEmpty;
  private Polynomial polyNull;
  private Polynomial polyMixed;

  @Before
  public void setUp() {
    this.poly1 = new PolynomialImpl("4x^3 +3x^2 +2x^1");
    this.poly2 = new PolynomialImpl("5x^4");
    this.poly3 = new PolynomialImpl("3");
    this.poly4 = new PolynomialImpl("-4x^1");
    this.poly1Coeff = new PolynomialImpl("5x^2 +1x^1");
    this.polyMultipleTerms = new PolynomialImpl("7x^3 +3x^2 +3x^2");
    this.polyNeg = new PolynomialImpl("-4x^3 -3x^2 -2x^1");
    this.polyOutOfOrder = new PolynomialImpl("4x^1 +5x^2");
    this.polyEmpty = new PolynomialImpl();
    this.polyNull = null;
    this.polyMixed = new PolynomialImpl("-4x^230 +2x^23 -5x^76");
  }

  @Test
  public void gettersTest() {
    assertEquals(4, this.poly1.getCoefficient(3));
    assertEquals(-2, this.polyNeg.getCoefficient(1));
    assertEquals(3, this.polyNeg.getDegree());
    assertEquals(3, this.poly1.getDegree());
    assertEquals(230, this.polyMixed.getDegree());
    assertEquals(2, this.polyMixed.getCoefficient(23));
    assertEquals(0, this.poly2.getCoefficient(23));
  }

  @Test (expected = IllegalArgumentException.class)
  public void inValidPowersTest() {
    assertEquals("Can't have a negative power", this.poly1.getCoefficient(-1));
    assertEquals("Can't have a negative power.", this.polyEmpty.getCoefficient(-3));
  }

  @Test (expected = IllegalArgumentException.class)
  public void negativePower() {
    assertEquals("Can't have a negative power.", new PolynomialImpl("5x^-2").toString());
  }

  @Test (expected = IllegalArgumentException.class)
  public void polyIllegalTest() {
    assertEquals("Illegal symbol in polynomial.",
            new PolynomialImpl("2@2 +5x^1").toString());
    assertEquals("Illegal symbol in polynomial.",
            new PolynomialImpl("4x^3 +7x^2 +9%1").toString());
    assertEquals("Illegal symbol in polynomial.",
            this.poly1.add(new PolynomialImpl("4$5")).toString());
  }

  @Test (expected = NumberFormatException.class)
  public void emptyStringTest() {
    assertEquals("Passed in an empty string", new PolynomialImpl("").toString());
  }

  @Test
  public void nullTest() {
    assertNull(this.polyNull);
    assertNotNull(this.poly1);
  }

  @Test
  public void addTermTest() {
    // Added same term
    this.poly2.addTerm(5, 4);
    assertEquals("10x^4", this.poly2.toString());

    // Added term to polynomial out of order
    this.polyOutOfOrder.addTerm(6, 3);
    assertEquals("6x^3 +5x^2 +4x^1", this.polyOutOfOrder.toString());
    this.polyMixed.addTerm(5, 1);
    assertEquals("-4x^230 -5x^76 +2x^23 +5x^1", this.polyMixed.toString());
  }

  @Test
  public void addPolynomialTest() {
    assertEquals("-4x^1 +3", this.poly3.add(this.poly4).toString());
    assertEquals("4x^3 +8x^2 +6x^1", this.poly1.add(this.polyOutOfOrder).toString());
    assertEquals("0", this.polyEmpty.add(this.polyEmpty).toString());
    assertEquals("4x^3 +8x^2 +6x^1", this.poly1.add(this.polyEmpty).toString());
  }

  @Test
  public void addAndRemoveEmptyPolynomialTest() {
    // empty with negative
    assertEquals("-4x^3 -3x^2 -2x^1", this.polyEmpty.add(this.polyNeg).toString());

    // empty with negative polynomial
    assertEquals("0", this.polyEmpty.add(this.polyEmpty).toString());

    // add term to empty polynomial
    this.polyEmpty.addTerm(7, 5);
    this.polyEmpty.addTerm(6, 4);
    assertEquals("7x^5 +6x^4", this.polyEmpty.toString());

    this.polyEmpty.addTerm(7, 5);
    assertEquals("14x^5 +6x^4", this.polyEmpty.toString());

    this.polyEmpty.removeTerm(5);
    assertEquals("6x^4", this.polyEmpty.toString());
    this.poly1.removeTerm(3);
    assertEquals("3x^2 +2x^1", this.poly1.toString());
  }

  @Test
  public void toStringTest() {
    assertEquals("4x^3 +3x^2 +2x^1", this.poly1.toString());
    assertEquals("5x^4", this.poly2.toString());
    assertEquals("3", this.poly3.toString());
    assertEquals("5x^4 +4x^3 +3x^2 +2x^1", this.poly1.add(this.poly2).toString());
    assertEquals("-4x^1", this.poly4.toString());
    assertEquals("0", this.polyEmpty.toString());
    assertEquals("-4x^3 -3x^2 -2x^1", this.polyNeg.toString());
    assertEquals("7x^3 +6x^2", this.polyMultipleTerms.toString());
    assertEquals("5x^2 +1x^1", this.poly1Coeff.toString());
  }

  @Test
  public void evaluateTest() {
    double result = 4 * Math.pow(2.5, 3) + 3 * Math.pow(2.5, 2) + 2 * Math.pow(2.5, 1);
    assertEquals(result, this.poly1.evaluate(2.5), 0.01);

    double result2 = -4 * Math.pow(0, 3) - 3 * Math.pow(0, 2) - 2 * Math.pow(0, 1);
    assertEquals(result2, this.polyNeg.evaluate(0), 0.01);

    double result3 = 4 * Math.pow(-1.5, 3) + 3 * Math.pow(-1.5, 2) + 2 * Math.pow(-1.5, 1);
    assertEquals(result3, this.poly1.evaluate(-1.5), 0.01);

    double result4 = 4 * Math.pow(-50.01, 3) + 3 * Math.pow(-50.01, 2) + 2 * Math.pow(-50.01, 1);
    assertEquals(result4, this.poly1.evaluate(-50.01), 0.01);
  }
}