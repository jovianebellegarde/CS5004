import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit Test class for WordNode, EmptyNode, and PunctuationNode classes.
 */
public class SentenceTest {
  private Sentence string1;
  private Sentence string2;
  private Sentence string3;

  @Before
  public void setUp() {
    this.string1 = new WordNode("I",
            new WordNode("miss",
                    new WordNode("Final",
                            new WordNode("Fantasy",
                                    new WordNode("XIV",
                                            new PunctuationNode(".",
                                                    new EmptyNode()))))));

    this.string2 = new WordNode("Why",
            new WordNode("are",
                    new WordNode("the",
                            new WordNode("tanks",
                                    new WordNode("not",
                                            new WordNode("tanking",
                                                    new PunctuationNode("?",
                                                            new EmptyNode())))))));

    this.string3 = new WordNode("Alliance",
            new WordNode("C",
                    new WordNode("is",
                            new WordNode("the",
                                    new WordNode("meme",
                                            new WordNode("party",
                                                    new PunctuationNode("!",
                                                            new EmptyNode())))))));

  }

  @Test
  public void getNumberOfWordsTest() {
    assertEquals(5, this.string1.getNumberOfWords());
    assertEquals(6, this.string2.getNumberOfWords());
    assertEquals(6, this.string3.getNumberOfWords());
  }

  @Test
  public void getLongestWordTest() {
    assertEquals("Fantasy", this.string1.longestWord());
    assertEquals("tanking", this.string2.longestWord());
    assertEquals("Alliance", this.string3.longestWord());
  }

  @Test
  public void toStringTest() {
    assertEquals("I miss Final Fantasy XIV.", this.string1.toString());
    assertEquals("Why are the tanks not tanking?", this.string2.toString());
    assertEquals("Alliance C is the meme party!", this.string3.toString());
  }

  @Test
  public void cloneTest() {
    Sentence sentence1Cloned = this.string1.clone();
    Sentence sentence2Cloned = this.string2.clone();
    Sentence sentence3Cloned = this.string3.clone();

    assertEquals("I miss Final Fantasy XIV.", sentence1Cloned.toString());
    assertEquals("Why are the tanks not tanking?", sentence2Cloned.toString());
    assertEquals("Alliance C is the meme party!", sentence3Cloned.toString());
  }

  @Test
  public void mergeTest() {
    Sentence newSentence = this.string1.merge(this.string2);
    assertEquals("I miss Final Fantasy XIV. Why are the tanks not tanking?",
            newSentence.toString());

    Sentence newSentence2 = this.string2.merge(this.string3);
    assertEquals("Why are the tanks not tanking? Alliance C is the meme party!",
            newSentence2.toString());

  }
}