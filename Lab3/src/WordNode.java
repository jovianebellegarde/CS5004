/**
 * This class represents a WordNode, implemented from the Sentence interface,
 * which has a head and rest of the string.
 */
public class WordNode implements Sentence {
  private final String head;
  private final Sentence rest;

  /**
   * Constructs a WordNode object and initializes it to the given head and rest.
   * @param head the head node of the sentence.
   * @param rest the rest of the sentence.
   */
  public WordNode(String head, Sentence rest) {
    this.head = head;
    this.rest = rest;
  }

  /**
   * Calculates the number of words in a sentence by passing in 1 each time and adding
   * it to the getNumberOfWords being called on the rest of the sentence.
   * @return an int the number of words in a sentence.
   */
  @Override
  public int getNumberOfWords() {
    return 1 + this.rest.getNumberOfWords();
  }

  /**
   * Determines the longest word in the sentence by comparing the head of the sentence
   * to the longestWord being called on the rest of the sentence.
   * @return a string the longest word.
   */
  @Override
  public String longestWord() {
    String longestWord = this.rest.longestWord();
    return this.head.length() > longestWord.length()
            ? this.head : this.rest.longestWord();
  }

  /**
   * Clones a sentence by calling clone on the rest of the sentence.
   * @return a cloned sentence.
   */
  @Override
  public Sentence clone() {
    return new WordNode(this.head, this.rest.clone());
  }

  /**
   * Merges 2 sentences into a single sentence with original punctuation by returning a new node
   * passing in head and calling merge on the rest of the sentence with the other sentence.
   * @param other other sentence this sentence is merged with.
   * @return a sentence merged.
   */
  @Override
  public Sentence merge(Sentence other) {
    return new WordNode(this.head, this.rest.merge(other));
  }

  /**
   * A string representation of the sentence using the toStringHelper on the rest and passing
   * the head through.
   * @return a string representation of this sentence.
   */
  @Override
  public String toString() {
    return this.rest.toStringHelper(this.head);
  }

  /**
   * Called in the toString method to add a space between the acc and the head.
   * @param  acc the head of the sentence that was passed in.
   * @return a string with a space in between the acc and the head.
   */
  @Override
  public String toStringHelper(String acc) {
    return this.rest.toStringHelper(acc + " " + this.head);
  }
}
