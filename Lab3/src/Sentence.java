/**
 * This interface contains all operations that all sentences support.
 */
public interface Sentence {

  /**
   * Calculates the number of words in a sentence.
   * @return an int the number of words.
   */
  int getNumberOfWords();

  /**
   * Determines the longest word in a sentence.
   * @return a string the longest word.
   */
  String longestWord();

  /**
   * Duplicates a given sentence.
   * @return the same sentence duplicated.
   */
  Sentence clone();

  /**
   * Merges 2 sentences into a single sentence with the original punctuation
   * preserved.
   * @param other other sentence this sentence is merged with.
   * @return this sentence merged with the other sentence.
   */
  Sentence merge(Sentence other);

  /**
   * A string representation of the sentence object using the stringHelper.
   * @return a string of the sentence.
   */
  String toString();

  /**
   * Called in the toString method as a helper to determine toString for the rest of the sentence.
   * @param  acc acc the head of the sentence that was passed in.
   * @return the string representation with the punctuation mark for the rest of the sentence.
   */
  String toStringHelper(String acc);
}
