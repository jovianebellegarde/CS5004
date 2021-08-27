package cs5004.questionnaire;

/**
 * This class represents a QuestionnaireElement that goes into each slot in the ArrayList in the
 * Impl. Each QuestionnaireElement has an identifier and question q.
 */
public class QuestionnaireElement implements Question {
  private final String identifier;
  private final Question q;

  /**
   * Constructs a QuestionnaireElement object with the given identifier and question q.
   * @param identifier a string.
   * @param q a question object.
   * @throws IllegalArgumentException if the length of the identifier is 0.
   */
  public QuestionnaireElement(String identifier, Question q) throws IllegalArgumentException {
    if (identifier.length() == 0) {
      throw new IllegalArgumentException("Can't have an empty identifier.");
    }
    this.identifier = identifier;
    this.q = q;
  }

  public String getIdentifier() {
    return this.identifier;
  }

  public Question getQ() {
    return this.q;
  }

  @Override
  public String getPrompt() {
    return this.q.getPrompt();
  }

  @Override
  public boolean isRequired() {
    return this.q.isRequired();
  }

  @Override
  public void answer(String input) {
    this.q.answer(input);
  }

  @Override
  public String getAnswer() {
    return this.q.getAnswer();
  }

  @Override
  public Question copy() {
    return new QuestionnaireElement(this.identifier, this.q);
  }

  @Override
  public String toString() {
    return null;
  }

}
