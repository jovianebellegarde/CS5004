import java.util.function.Predicate;

/**
 * Class where a Sentence represents a linked list and each word and punctuation is represented as a
 * node.
 */
public interface Sentence {

  /**
   * Determines longest word in a sentence.
   *
   * @return longest word in the linked list
   */
  String longestWord();

  /**
   * Returns the number of nodes in the linked list.
   *
   * @return node count
   */
  long getNumberOfWords(Predicate<Node> predicate);

  /** Method that adds a String to a linked list (Mutator method). */
  void addString(Node string);

  /**
   * Method that counts the number of punctuation nodes in a Sentence linked list. This is a higher
   * order function (passes in a function as a parameter)
   *
   * @param predicate function that returns a boolean if instanceof PunctuationNode
   * @return PunctuationNode count
   */
  long countPunctuation(Predicate<Node> predicate);

  /**
   * Method that counts the number of words that have the letter 'z' in nodes.
   *
   * @param predicate function that returns a boolean if substring contains 'z'
   * @return word count of nodes with a 'z'
   */
  long countZPredicate(Predicate<Node> predicate);

  /** Method that translates English to PigLatin. */
  void toPigLatin();

  /**
   * Method that replicates node.
   *
   * @return node copy
   */
  SentenceImpl clone() throws CloneNotSupportedException;

  /**
   * Merges two sentences into a single sentence. Punctuation is preserved. The merged list is
   * returned by this method, but originals are unchanged.
   *
   * @param other node
   * @return a new merged Sentence object
   */
  SentenceImpl merge(SentenceImpl other);

  /** Textual representation of Sentence linked list. */
  String toString();
}
