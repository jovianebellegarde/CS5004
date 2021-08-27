import java.util.Locale;

/** Class that represents a word node/string for a Sentence linked list. */
public class WordNode implements Node {
  private String word;

  /**
   * Constructor method.
   *
   * @param word string representing a node
   */
  public WordNode(String word) {
    this.word = word.toUpperCase(Locale.ROOT);
  }

  /**
   * Returns the number of nodes in the linked list.
   *
   * @return node count
   */
  @Override
  public String getContent() {
    return this.word;
  }

  /**
   * Method that returns a boolean if word node begins with a vowel.
   *
   * @return boolean if word node begins with a vowel
   */
  public boolean isFirstVowel() {
    char first = word.charAt(0);
    return first == 'A' || first == 'E' || first == 'I' || first == 'O' || first == 'U';
  }

  /** Method that translates English to PigLatin (Mutator). */
  public String pigLatin() {
    if (this.isFirstVowel()) {
      this.word = this.word + "WAY";
    } else {
      String consonant = String.valueOf(this.word.charAt(0));
      this.word = this.word.substring(1) + consonant + "AY";
    }
    return this.word;
  }
}
