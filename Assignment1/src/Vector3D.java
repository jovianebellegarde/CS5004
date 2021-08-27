/**
 * This class represents a Vector3D which are made up of 3 components x, y, and z.
 */
public class Vector3D {
  private final double x;
  private final double y;
  private final double z;

  /**
   * Constructs a Vector3D object and initializes it to the given x, y, and z components.
   * @param x a double the x component.
   * @param y a double the y component.
   * @param z a double the z component.
   */
  public Vector3D(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  /**
   * Gets the x component of this vector.
   * @return a double the x component.
   */
  public double getX() {
    return this.x;
  }

  /**
   * Gets the y component of this vector.
   * @return a double the y component.
   */
  public double getY() {
    return this.y;
  }

  /**
   * Gets the z component of this vector.
   * @return a double the z component.
   */
  public double getZ() {
    return this.z;
  }

  /**
   * Makes a string representation of this vector.
   * @return a formatted string.
   */
  public String toString() {
    return String.format("(%.2f,%.2f,%.2f)", this.x, this.y, this.z);
  }

  /**
   * Calculates the magnitude of this vector.
   * @return a double the magnitude.
   */
  public double getMagnitude() {
    return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
  }

  /**
   * Calculates normalizing this vector by dividing x, y, and z by the vector's magnitude.
   * @return Vector3D object with normalized components.
   * @throws IllegalStateException if trying to divide components by 0.
   */
  public Vector3D normalize() throws IllegalStateException {
    if (this.getMagnitude() == 0) {
      throw new IllegalStateException("Can't divide by 0.");
    }
    double normalizedX = this.x / this.getMagnitude();
    double normalizedY = this.y / this.getMagnitude();
    double normalizedZ = this.z / this.getMagnitude();
    return new Vector3D(normalizedX, normalizedY, normalizedZ);
  }

  /**
   * Calculates addition of this vector and another vector by adding the respective
   * components of the two vectors.
   * @param anotherVector another Vector3D object.
   * @return Vector3D object with added components.
   */
  public Vector3D add(Vector3D anotherVector) {
    double addedX = this.x + anotherVector.getX();
    double addedY = this.y + anotherVector.getY();
    double addedZ = this.z + anotherVector.getZ();
    return new Vector3D(addedX, addedY, addedZ);
  }

  /**
   * Calculates multiplying the components of this vector by a constant.
   * @param constant a double used to multiply this vector's components by.
   * @return Vector3D object with multiplied components.
   */
  public Vector3D multiply(double constant) {
    double multipliedX = this.x * constant;
    double multipliedY = this.y * constant;
    double multipliedZ = this.z * constant;
    return new Vector3D(multipliedX, multipliedY, multipliedZ);
  }

  /**
   * Calculates the dot product of this vector and another vector.
   * @param anotherVector another Vector3D object.
   * @return Vector3D object with dotted components.
   */
  public double dotProduct(Vector3D anotherVector) {
    return this.x * anotherVector.getX() + this.y * anotherVector.getY()
            + this.z * anotherVector.getZ();
  }

  /**
   * Calculates the angle between this vector and another vector in degrees.
   * @param anotherVector another Vector3D object.
   * @return double angle in degrees.
   * @throws IllegalStateException if either magnitude is 0.
   */
  public double angleBetween(Vector3D anotherVector) throws IllegalStateException {
    if (this.getMagnitude() == 0 || anotherVector.getMagnitude() == 0) {
      throw new IllegalStateException("Can't divide by 0.");
    }
    double dottedResults = this.dotProduct(anotherVector);
    double magnitudes = this.getMagnitude() * anotherVector.getMagnitude();
    double fraction = dottedResults / magnitudes;
    return Math.toDegrees(Math.acos(fraction));
  }

}
