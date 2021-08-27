/**
 * This class represents a PunctuationNode, implemented from the Sentence interface,
 * which has a head and rest of the string.
 */
public class PunctuationNode implements Sentence {
  private final String head;
  private final Sentence rest;

  /**
   * Constructs a PunctuationNode object and initializes it to the given head and rest.
   * @param head head of the string.
   * @param rest rest of the string.
   */
  public PunctuationNode(String head, Sentence rest) {
    this.head = head;
    this.rest = rest;
  }

  /**
   * Calculates the number of words in a sentence.
   * @return an int 0.
   */
  @Override
  public int getNumberOfWords() {
    return 0;
  }

  /**
   * Determines the longest word in the sentence.
   * @return the longestWord called on the rest of the sentence.
   */
  @Override
  public String longestWord() {
    return this.rest.longestWord();
  }

  /**
   * Clones a sentence by calling clone on the rest of the sentence.
   * @return a cloned sentence.
   */
  @Override
  public Sentence clone() {
    return new PunctuationNode(this.head, this.rest.clone());
  }

  /**
   * Merges 2 sentences by returning a new Punctuation node passing in the
   * head and calling merge on the rest of the sentence with the other sentence.
   * @param other other sentence this sentence is merged with.
   * @return a sentence merged.
   */
  @Override
  public Sentence merge(Sentence other) {
    return new PunctuationNode(this.head, this.rest.merge(other));
  }

  /**
   * A string representation of the sentence using the toStringHelper by calling the
   * toStringHelper on the rest of the sentence.
   * @return a string representation.
   */
  @Override
  public String toString() {
    return this.rest.toStringHelper(this.head);
  }

  /**
   * Called in the toString method and adds a space between the acc and the head.
   * @param  acc acc the head of the sentence that was passed in.
   * @return the toString method called on the rest.
   */
  @Override
  public String toStringHelper(String acc) {
    return this.rest.toStringHelper(acc + this.head);
  }
}
