/**
 * This class represents an EmptyNode which is the end of a sentence or an "empty sentence".
 */
public class EmptyNode implements Sentence {

  /**
   * Gets the number of words for empty sentence.
   * @return an int 0 number of words.
   */
  @Override
  public int getNumberOfWords() {
    return 0;
  }

  /**
   * Determines the longest word in an empty sentence.
   * @return an empty string.
   */
  @Override
  public String longestWord() {
    return "";
  }

  /**
   * Clones an empty sentence.
   * @return an empty node.
   */
  @Override
  public Sentence clone() {
    return new EmptyNode();
  }

  /**
   * Merges this empty sentence with another sentence.
   * @param other other sentence this sentence is merged with.
   * @return the other sentence.
   */
  @Override
  public Sentence merge(Sentence other) {
    return other;
  }

  /**
   * A string representation of this empty sentence.
   * @return an empty string.
   */
  @Override
  public String toString() {
    return "";
  }

  /**
   * Checks to see if the length of the accumulator is 0 to return an empty string or return
   * the correct punctuation mark.
   * @param acc length of the rest of this sentence.
   * @return an empty string or correct ending of sentence.
   */
  @Override
  public String toStringHelper(String acc) {

    if (acc.length() == 0) {
      return "";
    }

    char lastCharacter = acc.charAt(acc.length() - 1);
    String symbol = Character.toString(lastCharacter);

    if (symbol.equals(".") || symbol.equals("?") || symbol.equals("!")) {
      return acc;
    } else {
      return acc + " " + ".";
    }
  }
}
