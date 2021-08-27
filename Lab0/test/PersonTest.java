

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * A JUnit test class for the Person class.
 */
public class PersonTest {

  private Person john;

  /**
   * This is the setup for the constructor.
   */
  @Before
  public void setUp() {

    john = new Person("John", "Doe", 1945);
  }

  /**
   * This tests the getFirstName method for the first name.
   */
  @Test
  public void testFirst() {
    assertEquals("John", john.getFirstName());

  }

  /**
   * This tests the getLastName method for the last name.
   */
  @Test
  public void testSecond() {
    assertEquals("Doe", john.getLastName());
  }

  /**
   * This tests the the getYearOfBirth method for the year of birth.
   */
  @Test
  public void testYearOfBirth() {
    assertEquals(1945, john.getYearOfBirth());
  }

}
