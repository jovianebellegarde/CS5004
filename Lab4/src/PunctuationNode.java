/** Class that represents a punctuation string/node for a Sentence linked list. */
public class PunctuationNode implements Node {
  private final String punctuation;

  /**
   * Constructor method.
   *
   * @param punctuation string representing a node
   * @throws IllegalArgumentException if it is not punctuation
   */
  public PunctuationNode(String punctuation) throws IllegalArgumentException {
    if (punctuation.length() == 1) {
      this.punctuation = punctuation;
    } else {
      throw new IllegalArgumentException("Punctuation only");
    }
  }

  /**
   * Returns the number of nodes in the linked list.
   *
   * @return node count
   */
  @Override
  public String getContent() {
    return this.punctuation;
  }
}
