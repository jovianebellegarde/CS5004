/**
 * This class represents a fibonacci counter which has an initial count and current count.
 */
public class FibonacciCounter {
  private int initialCount;
  private final double currentCount;

  /**
   * Constructs a FibonacciCounter object and initializes it to the given initial count.
   * @param initialCount the nth number of fibonacci sequence.
   * @throws IllegalArgumentException if the number passed in is less than 0.
   */
  public FibonacciCounter(int initialCount) throws IllegalArgumentException {
    if (initialCount < 0) {
      throw new IllegalArgumentException("Can't have a negative number.");
    }
    this.initialCount = initialCount;

    // Binet's formula
    double part1 = Math.pow(((1 + Math.sqrt(5)) / 2), this.initialCount);
    double part2 = Math.pow(((1 - Math.sqrt(5)) / 2), this.initialCount);
    this.currentCount = (1 / Math.sqrt(5)) * (part1 - part2);
  }

  /**
   * Gets the initial count of the fibonacci sequence.
   * @return the initial count an int.
   */
  public int getInitialCount() {
    return this.initialCount;
  }

  /**
   * Increments the initial count by 1.
   * @return a new fibonacci object with initial count increased by 1.
   */
  public FibonacciCounter increment() {
    this.initialCount++;
    return new FibonacciCounter(this.initialCount);
  }

  /**
   * Decrements the initial count by 1.
   * @return a a new fibonacci object with the initial count decreased by 1.
   */
  public FibonacciCounter decrement() {
    if (this.initialCount > 0) {
      this.initialCount--;
    }
    return new FibonacciCounter(this.initialCount);
  }

  /**
   * Gets the current count in the fibonacci sequence.
   * @return a the current count of the sequence, an int.
   */
  public int getCurrentCount() {
    return (int) this.currentCount;
  }
}
