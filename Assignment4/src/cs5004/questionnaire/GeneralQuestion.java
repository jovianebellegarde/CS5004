package cs5004.questionnaire;

/**
 * The General Question abstract class shows the common methods that the 3 concrete classes
 * implement.
 */
public abstract class GeneralQuestion implements Question {
  protected String prompt;
  protected boolean isRequired;
  protected String answer;

  /**
   * Constructs a general question and initializes it to the string prompt and boolean isRequired.
   * @param prompt a string.
   * @param isRequired a boolean.
   */
  public GeneralQuestion(String prompt, boolean isRequired) throws IllegalArgumentException {
    if (prompt == null) {
      throw new IllegalArgumentException("Can't have a null prompt.");
    }
    this.prompt = prompt;
    this.isRequired = isRequired;
    this.answer = "";
  }

  @Override
  public String getPrompt() {
    return this.prompt;
  }

  @Override
  public boolean isRequired() {
    return this.isRequired;
  }

  @Override
  public abstract void answer(String input) throws IllegalArgumentException;

  @Override
  public String getAnswer() {
    return this.answer;
  }

  @Override
  public String toString() {
    String stringAnswer = "";
    return "Question: ";
  }
}
