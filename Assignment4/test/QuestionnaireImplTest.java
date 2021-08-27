import org.junit.Before;
import org.junit.Test;

import cs5004.questionnaire.Likert;
import cs5004.questionnaire.Question;
import cs5004.questionnaire.QuestionnaireImpl;
import cs5004.questionnaire.ShortAnswer;
import cs5004.questionnaire.YesNo;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for QuestionnaireImplTest class.
 */
public class QuestionnaireImplTest {

  private QuestionnaireImpl questionnaire;
  private Question shortAnswer1;
  private Question yesNo1;
  private Question likert1;

  @Before
  public void setUp() {
    this.questionnaire = new QuestionnaireImpl();
    this.yesNo1 = new YesNo("Huh prompt", true);
    this.likert1 = new Likert("Yup prompt", false);
    this.shortAnswer1 = new ShortAnswer("Nice prompt", true);
  }

  @Test
  public void addQuestionTest() {
    this.questionnaire.addQuestion("firstYesNo", this.yesNo1);
    this.questionnaire.addQuestion("secondLikert", this.likert1);
    this.questionnaire.addQuestion("thirdShortAnswer", this.shortAnswer1);
    assertEquals("huh prompt", this.questionnaire.getQuestion(1).toString());
  }

  @Test
  public void toStringTest() {
    assertEquals("Huh promopt", this.yesNo1.toString());
  }

}