package cs5004.questionnaire;

/**
 * This class represents a likert question, extended from the General Question abstract class,
 * which has a string prompt and boolean required question.
 */
public class Likert extends GeneralQuestion {

  public Likert(String prompt, boolean isRequired) throws IllegalArgumentException {
    super(prompt, isRequired);
  }

  /**
   * Accepts answer for Likert. This can't be an empty string, only the exact string values to enum.
   * @param input a string.
   * @throws IllegalArgumentException if anything other than the enum values.
   */
  @Override
  public void answer(String input) throws IllegalArgumentException {
    if (input == null || input.equals("")) {
      throw new IllegalArgumentException("Can't have null or empty likert.");
    }

    if (LikertResponseOption.STRONGLY_AGREE.getText().equalsIgnoreCase(input)) {
      this.answer = input;
    } else if (LikertResponseOption.AGREE.getText().equalsIgnoreCase(input)) {
      this.answer = input;
    } else if (LikertResponseOption.NEUTRAL.getText().equalsIgnoreCase(input)) {
      this.answer = input;
    } else if (LikertResponseOption.DISAGREE.getText().equalsIgnoreCase(input)) {
      this.answer = input;
    } else if (LikertResponseOption.STRONGLY_DISAGREE.getText().equalsIgnoreCase(input)) {
      this.answer = input;
    } else {
      throw new IllegalArgumentException("Likert response not found.");
    }
  }

  /**
   * Copies the Likert object.
   * @return a copy of the question.
   */
  @Override
  public Question copy() {
    Question copiedLikert = new Likert(this.prompt, this.isRequired);
    if (!(this.answer.equals(""))) {
      copiedLikert.answer(this.getAnswer());
    }
    return copiedLikert;
  }
}
