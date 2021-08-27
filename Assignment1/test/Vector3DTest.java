import org.junit.Before;
import org.junit.Test;

import static org. junit.Assert.assertEquals;

/**
 * A JUnit test class for the Vector3D class.
 */
public class Vector3DTest {
  Vector3D vectorZero;
  Vector3D vectorNegative;
  Vector3D vectorPositive;
  Vector3D vectorMixed;
  Vector3D vector3Digits;
  Vector3D vectorPlus;
  Vector3D vectorMinus;
  Vector3D vectorLarge;
  Vector3D vectorSmall;

  private final static double DELTA = 0.01;

  @Before
  public void setUp() {
    this.vectorZero = new Vector3D(0.00, 0.00, 0.00);
    this.vectorNegative = new Vector3D(-2.50, -4.50, -6.50);
    this.vectorPositive = new Vector3D(5.00, 10.00, 15.00);
    this.vectorMixed = new Vector3D(5.00, -3.54, 6.45);
    this.vector3Digits = new Vector3D(1.234, 5.678, 9.101);
    this.vectorPlus = new Vector3D(5.12, 7.15, 9.24);
    this.vectorMinus = new Vector3D(-5.12, -7.15, -9.24);
    this.vectorLarge = new Vector3D(60.00, 70.00, 80.00);
    this.vectorSmall = new Vector3D(10.00, 15.35, 20.00);
  }

  @Test
  public void gettersTest() {
    // Testing getX
    assertEquals(0.00, this.vectorZero.getX(), DELTA);
    assertEquals(-2.50, this.vectorNegative.getX(), DELTA);
    assertEquals(5.00, this.vectorPositive.getX(), DELTA);
    assertEquals(5.00, this.vectorMixed.getX(), DELTA);
    assertEquals(1.23, this.vector3Digits.getX(), DELTA);

    // Testing getY
    assertEquals(0.00, this.vectorZero.getY(), DELTA);
    assertEquals(-4.50, this.vectorNegative.getY(), DELTA);
    assertEquals(10.00, this.vectorPositive.getY(), DELTA);
    assertEquals(-3.54, this.vectorMixed.getY(), DELTA);
    assertEquals(5.67, this.vector3Digits.getY(), DELTA);

    // Testing getZ
    assertEquals(0.00, this.vectorZero.getZ(), DELTA);
    assertEquals(-6.50, this.vectorNegative.getZ(), DELTA);
    assertEquals(15.00, this.vectorPositive.getZ(), DELTA);
    assertEquals(6.45, this.vectorMixed.getZ(), DELTA);
    assertEquals(9.10, this.vector3Digits.getZ(), DELTA);

  }

  @Test
  public void toStringTest() {
    assertEquals("(0.00,0.00,0.00)", this.vectorZero.toString());
    assertEquals("(-2.50,-4.50,-6.50)", this.vectorNegative.toString());
    assertEquals("(5.00,10.00,15.00)", this.vectorPositive.toString());
    assertEquals("(5.00,-3.54,6.45)", this.vectorMixed.toString());
    assertEquals("(1.23,5.68,9.10)", this.vector3Digits.toString());
  }

  @Test
  public void getMagnitudeTest() {
    assertEquals(0.00, this.vectorZero.getMagnitude(), DELTA);
    assertEquals(8.29, this.vectorNegative.getMagnitude(), DELTA);
    assertEquals(18.71, this.vectorPositive.getMagnitude(), DELTA);
    assertEquals(8.90, this.vectorMixed.getMagnitude(), DELTA);
    assertEquals(10.80, this.vector3Digits.getMagnitude(), DELTA);
  }

  @Test
  public void normalizeTest() {
    // Negative
    assertEquals("(-0.30,-0.54,-0.78)", this.vectorNegative.normalize().toString());

    // Positive
    assertEquals("(0.27,0.53,0.80)", this.vectorPositive.normalize().toString());

    // Mixed
    assertEquals("(0.56,-0.40,0.73)", this.vectorMixed.normalize().toString());

    // 3 digits
    assertEquals("(0.11,0.53,0.84)", this.vector3Digits.normalize().toString());
  }

  @Test (expected = IllegalStateException.class)
  public void normalizeZero() {
    assertEquals("Can't divide by 0.", this.vectorZero.normalize().toString());
  }

  @Test
  public void addingTwoVectors() {
    // Negative and 0
    assertEquals("(-2.50,-4.50,-6.50)", this.vectorNegative.add(this.vectorZero).toString());

    // Mixed and 3 digits
    assertEquals("(6.23,2.14,15.55)", this.vectorMixed.add(this.vector3Digits).toString());

    // Cancel out
    assertEquals("(0.00,0.00,0.00)", vectorPlus.add(vectorMinus).toString());
  }


  @Test
  public void multiplyVectorByConstant() {
    double two = 2.00;
    double zero = 0.00;
    // 0 Vector by 2
    assertEquals("(0.00,0.00,0.00)", this.vectorZero.multiply(two).toString());

    // Nonzero Vector by 0
    assertEquals("(0.00,0.00,0.00)", this.vectorPositive.multiply(zero).toString());

    // Large Vector by 2
    assertEquals("(120.00,140.00,160.00)", this.vectorLarge.multiply(two).toString());
  }

  @Test
  public void dotProductTest() {
    // Zero and 3 digits
    assertEquals(0.00, this.vectorZero.dotProduct(this.vector3Digits), DELTA);

    // Positive and negative
    assertEquals(-155.00, this.vectorPositive.dotProduct(this.vectorNegative), DELTA);

    // Small and large
    assertEquals(3274.50, this.vectorLarge.dotProduct(this.vectorSmall), DELTA);

    // Plus and minus
    assertEquals(-162.71, this.vectorPlus.dotProduct(this.vectorMinus), DELTA);
  }

  @Test
  public void angleBetweenTest() {
    // Positive and negative
    assertEquals(177.74, this.vectorPositive.angleBetween(this.vectorNegative), DELTA);

    // Large and Small
    assertEquals(8.48, this.vectorLarge.angleBetween(this.vectorSmall), DELTA);

    // Plus and minus
    assertEquals(180.00, this.vectorPlus.angleBetween(this.vectorMinus), DELTA);
  }

  @Test (expected = IllegalStateException.class)
  public void angleBetweenExceptionTest() {
    IllegalStateException exception = new IllegalStateException("Can't divide by 0.");

    // Zero and minus
    assertEquals(exception, this.vectorZero.angleBetween(this.vectorMinus));

    // Mixed and zero vector
    assertEquals(exception, this.vectorMixed.angleBetween(this.vectorZero));
  }

}