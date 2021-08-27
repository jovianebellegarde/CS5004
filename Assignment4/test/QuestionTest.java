import org.junit.Before;
import org.junit.Test;

import cs5004.questionnaire.Likert;
import cs5004.questionnaire.Question;
import cs5004.questionnaire.ShortAnswer;
import cs5004.questionnaire.YesNo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * A JUnit test class for th the Question interface.
 */
public class QuestionTest {
  private Question yesNo1;
  private Question yesNo2;
  private Question shortAnswer1;
  private Question likert1;

  @Before
  public void setUp() {
    this.yesNo1 = new YesNo("Question 1", true);
    this.yesNo2 = new YesNo("Question 2", true);
    this.shortAnswer1 = new ShortAnswer("Short Answer 1", false);
    this.likert1 = new Likert("Likert1", true);
  }

  @Test
  public void getPromptTest() {
    assertEquals("Question 1", this.yesNo1.getPrompt());
    assertEquals("Question 2", this.yesNo2.getPrompt());
    assertEquals("Short Answer 1", this.shortAnswer1.getPrompt());
    assertEquals("Likert1", this.likert1.getPrompt());
  }

  @Test
  public void getIsRequiredTest() {
    assertTrue(this.yesNo1.isRequired());
    assertTrue(this.yesNo2.isRequired());
    assertFalse(this.shortAnswer1.isRequired());
    assertTrue(this.likert1.isRequired());
  }

  @Test (expected = IllegalArgumentException.class)
  public void inValid() {
    this.yesNo1.answer("hello");
    assertEquals("Need to answer yes or no only.", this.yesNo1.getAnswer());
  }

  @Test
  public void getAnswerTest() {
    this.likert1.answer("Disagree");
    assertEquals("Disagree", this.likert1.getAnswer());
  }

  @Test (expected = IllegalArgumentException.class)
  public void getAnswerInvalidTest() {
    this.yesNo1.answer(null);
    assertEquals("Need to answer yes or no only.", this.yesNo1.getAnswer());
    assertEquals("need to answer yes or no only.", this.yesNo1.copy().toString());

    this.shortAnswer1.answer(null);
    assertEquals("Need a valid short answer string.", this.shortAnswer1.getAnswer());

    this.shortAnswer1.answer("");
    assertEquals("Need a valid short answer string.", this.shortAnswer1.getPrompt());
    assertEquals("Need a valid short answer string.", this.shortAnswer1.copy().toString());

    String over280 = "We are going to write a long paragraph and see if this is going " +
            "to count as an Illegal Argument Exception. It totally should throw the " +
            "exception. Trying to reach the two hundred and eighty character count. Looks like " +
            "I will have to keep typing to break this threshold for this assignment.";
    this.shortAnswer1.answer(over280);

    assertEquals("We are going to write a long paragraph and see if this is going " +
            "to count as an Illegal Argument Exception. It totally should throw the exception. " +
            "Trying to reach the two hundred and eighty character count. Looks like I will " +
            "have to keep typing to break this threshold for this assignment.",
            this.shortAnswer1.getAnswer());

    this.likert1.answer("disagreee");
    assertEquals("Answer not in likert.", this.likert1.getAnswer());

    this.likert1.answer("");
    assertEquals("Answer not in likert.", this.likert1.getAnswer());
  }

  @Test
  public void copyTest() {
    assertNotSame(this.yesNo1, this.yesNo1.copy());
    assertNotSame(this.yesNo2, this.yesNo2.copy());
    assertNotSame(this.shortAnswer1, this.shortAnswer1.copy());
    assertNotSame(this.likert1, this.likert1.copy());
  }
}