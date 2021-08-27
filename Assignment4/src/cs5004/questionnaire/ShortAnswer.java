package cs5004.questionnaire;

/**
 * This class represents a short answer question, extended from the General Question abstract class,
 * which has a string prompt and boolean required question.
 */
public class ShortAnswer extends GeneralQuestion {

  public ShortAnswer(String prompt, boolean isRequired) throws IllegalArgumentException {
    super(prompt, isRequired);
  }

  /**
   * Accepts the answer for ShortAnswer. This
   * @param input a string.
   */
  @Override
  public void answer(String input) throws IllegalArgumentException {
    if (input == null || input.equals("") || !(input.length() > 0 && input.length() <= 280)) {
      throw new IllegalArgumentException("Need a valid short answer.");
    }
    this.answer = input;
  }

  /**
   * Copies the question object.
   * @return a copy of the question.
   */
  @Override
  public Question copy() {
    Question copiedShortAnswer = new Likert(this.prompt, this.isRequired);
    if (!(this.answer.equals(""))) {
      copiedShortAnswer.answer(this.getAnswer());
    }
    return copiedShortAnswer;
  }
}
