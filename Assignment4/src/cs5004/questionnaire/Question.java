package cs5004.questionnaire;

/**
 * This Question interface contains all operations that all types of questions should support.
 */
public interface Question {

  /**
   * Gets the string representation of the question itself.
   * @return a string prompt.
   */
  String getPrompt();

  /**
   * Determines if the question is required or optional.
   * @return boolean true if required, false otherwise.
   */
  boolean isRequired();

  /**
   * Allows input an answer as a String depending on the type of question.
   * @param input a string.
   */
  void answer(String input);

  /**
   * Gets the answer to the question or an empty string if there is no answer.
   * @return a string.
   */
  String getAnswer();

  /**
   * Copies the question and all of its data.
   * @return a question.
   */
  Question copy();
}
