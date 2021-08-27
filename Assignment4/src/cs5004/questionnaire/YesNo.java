package cs5004.questionnaire;

/**
 * This class represents a yes/no question, extended from the General Question abstract class,
 * which has a string prompt and a boolean required question.
 */
public class YesNo extends GeneralQuestion {

  public YesNo(String prompt, boolean isRequired) throws IllegalArgumentException {
    super(prompt, isRequired);
  }

  /**
   * Accepts answer for YesNo. This can't be an empty string, only yes or no.
   * @param input a string.
   * @throws IllegalArgumentException if anything other than yes or no.
   */
  @Override
  public void answer(String input) throws IllegalArgumentException {
    if (input == null || input.equals("") || !(input.equalsIgnoreCase("yes")
            || (input.equalsIgnoreCase("no")))) {
      throw new IllegalArgumentException("Need to enter yes or no only.");
    }
    this.answer = input;
  }

  /**
   * Copies the YesNo object.
   * @return a copy of the question.
   */
  @Override
  public Question copy() {
    Question copiedYesNo = new Likert(this.prompt, this.isRequired);
    if (!(this.answer.equals(""))) {
      copiedYesNo.answer(this.getAnswer());
    }
    return copiedYesNo;
  }
}
