import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Class where a Sentence represents a linked list and each word and punctuation is represented as a
 * node.
 */
public class SentenceImpl implements Sentence {
  private final List<Node> sentence;

  /** Constructor method for null. */
  public SentenceImpl() {
    this.sentence = new LinkedList<>();
  }

  /** Constructor method. */
  public SentenceImpl(Node node) {
    this.sentence = new LinkedList<>();
    this.sentence.add(node);
  }

  /** Method that adds a String to a linked list (Mutator method). */
  public void addString(Node string) {
    this.sentence.add(string);
  }

  /**
   * Method that counts the number of punctuation nodes in a Sentence linked list. This is a higher
   * order function (passes in a function as a parameter)
   *
   * @param predicate function that returns a boolean if instanceof PunctuationNode
   * @return PunctuationNode count
   */
  public long countPunctuation(Predicate<Node> predicate) {
    return this.sentence.stream()
            .filter(predicate)
            .count();
  }

  /**
   * Method that counts the number of words that have the letter 'z' in nodes.
   *
   * @param predicate function that returns a boolean if substring contains 'z'
   * @return word count of nodes with a 'z'
   */
  public long countZPredicate(Predicate<Node> predicate) {
    return this.sentence.stream()
            .filter(predicate)
            .count();
  }

  /** Method that translates English to PigLatin. */
  public void toPigLatin() {
    this.sentence.stream()
        .filter(n -> n instanceof WordNode)
        .forEach(u -> ((WordNode) u).pigLatin());
  }

  /**
   * Method that replicates node.
   *
   * @return node copy
   */
  public SentenceImpl clone() {
    SentenceImpl clone = new SentenceImpl();

    // Deep copy
    for (Node node : this.sentence) {
      if (node instanceof WordNode) {
        clone.addString(new WordNode(node.getContent()));
      }
      if (node instanceof PunctuationNode) {
        clone.addString(new PunctuationNode(node.getContent()));
      }
    }
    return clone;
  }

  /**
   * Merges two sentences into a single sentence. Punctuation is preserved. The merged list is
   * returned by this method, but originals are unchanged.
   *
   * @param other node
   * @return a new merged Sentence object
   */
  public SentenceImpl merge(SentenceImpl other) {
    SentenceImpl thisCopy = this.clone();

    for (Node node : other.sentence) {
      thisCopy.addString(node);
    }
    return thisCopy;
  }

  /**
   * Determines longest word in a sentence.
   *
   * @return longest word in the linked list
   */
  public String longestWord() {
    int temp = 0;
    String longest = "";
    if (this.sentence == null) {
      return "";
    }
    // Iterate through linked list, compare, update
    for (Node node : this.sentence) {
      if (node.getContent().length() > temp && node instanceof WordNode) {
        longest = node.getContent();
        temp = node.getContent().length();
      }
    }
    return longest;
  }

  /**
   * Returns the number of nodes in the linked list.
   *
   * @return node count
   */
  public long getNumberOfWords(Predicate<Node> predicate) {
    return this.sentence.stream()
            .filter(predicate)
            .count();
  }

  /** Textual representation of Sentence linked list. */
  public String toString() {
    StringBuilder temp = new StringBuilder();

    for (Node node : this.sentence) {
      if (!node.getContent().equals("") && node instanceof WordNode) {
        temp.append(node.getContent()).append(" ");
      }
      if (!node.getContent().equals("") && node instanceof PunctuationNode) {
        temp.append(node.getContent());
      }
    }
    return temp.toString();
  }
}
