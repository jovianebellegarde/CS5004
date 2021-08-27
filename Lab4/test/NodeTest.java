import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.function.Predicate;
import org.junit.Before;
import org.junit.Test;

/** JUnit test for SentenceImpl linked list. */
public class NodeTest {
  private final SentenceImpl nullSentence = new SentenceImpl();
  private SentenceImpl sentence;
  private SentenceImpl sentencesFullOfPunctuation;
  private SentenceImpl pigLatin;
  private SentenceImpl punctuationOnly;

  /** Setup for testing. */
  @Before
  public void setUp() {
    this.sentence = new SentenceImpl(new WordNode("Hello"));
    this.sentence.addString(new WordNode("Zebras"));
    this.sentence.addString(new WordNode("are"));
    this.sentence.addString(new WordNode("zebras"));

    this.sentencesFullOfPunctuation = new SentenceImpl(new WordNode("Hello"));
    this.sentencesFullOfPunctuation.addString(new PunctuationNode("!"));
    this.sentencesFullOfPunctuation.addString(new WordNode("I"));
    this.sentencesFullOfPunctuation.addString(new PunctuationNode("."));
    this.sentencesFullOfPunctuation.addString(new WordNode("love"));
    this.sentencesFullOfPunctuation.addString(new PunctuationNode("."));
    this.sentencesFullOfPunctuation.addString(new WordNode("doggies"));
    this.sentencesFullOfPunctuation.addString(new PunctuationNode("!"));

    this.pigLatin = new SentenceImpl(new WordNode("MAKING"));
    this.pigLatin.addString(new WordNode("A"));
    this.pigLatin.addString(new WordNode("PIG"));
    this.pigLatin.addString(new WordNode("DEAL"));
    this.pigLatin.addString(new WordNode("ABOUT"));
    this.pigLatin.addString(new WordNode("PIG"));
    this.pigLatin.addString(new WordNode("LATIN"));

    this.punctuationOnly = new SentenceImpl(new PunctuationNode("!"));
    this.punctuationOnly.addString(new PunctuationNode(";"));
  }

  /** Test constructor method. */
  @Test
  public void testConstructor() {
    // Test non-null
    assertNotNull(this.sentence);
    assertNotNull(this.sentencesFullOfPunctuation);
    assertNotNull(this.pigLatin);
    assertNotNull(this.punctuationOnly);

    // Test null
    assertEquals("", this.nullSentence.toString());
  }

  /** Test getNumberOfWords method. */
  @Test
  public void testLongestWord() {
    // Test empty
    assertEquals("", nullSentence.longestWord());

    // Test not null
    assertEquals("ZEBRAS", sentence.longestWord());
    assertEquals("DOGGIES", sentencesFullOfPunctuation.longestWord());
    assertEquals("", punctuationOnly.longestWord());
    assertEquals("MAKING", pigLatin.longestWord());
  }

  /** Test filter for getNumberOfWords count. */
  @Test
  public void testGetWordNodeCount() {

    // Generalized method to instantiate
    Predicate<Node> predicate = n -> n instanceof WordNode;

    // Overriding the Predicate class method to instantiate
    Predicate<Node> newPredicate =
        new Predicate<Node>() {
          @Override
          public boolean test(Node node) {
            return node instanceof PunctuationNode;
          }
        };

    assertEquals(4, this.sentencesFullOfPunctuation.getNumberOfWords(predicate));
    assertEquals(4, this.sentencesFullOfPunctuation.countPunctuation(newPredicate));
    assertEquals(7, this.pigLatin.getNumberOfWords(predicate));
    assertEquals(0, this.pigLatin.countPunctuation(newPredicate));
  }

  /** Test filter for words that contain the letter 'z'. */
  @Test
  public void testAddString() {

    // Test from null
    this.nullSentence.addString(new WordNode("mouse"));
    assertEquals("MOUSE ", this.nullSentence.toString());

    // Test from non-null
    this.sentence.addString(new WordNode("keyboard"));
    assertEquals("HELLO ZEBRAS ARE ZEBRAS KEYBOARD ", this.sentence.toString());
  }

  /** Test filter for words that contain the letter 'z'. */
  @Test
  public void testFilterZ() {

    // Generalized method to instantiate
    Predicate<Node> predicate = n -> n.getContent().contains("Z");

    // Overriding the Predicate class method to instantiate
    Predicate<Node> newPredicate =
        new Predicate<Node>() {
          @Override
          public boolean test(Node node) {
            return node.getContent().contains("Z");
          }
        };

    // Contains 'z'
    assertEquals(2, this.sentence.countZPredicate(predicate));
    assertEquals(2, this.sentence.countZPredicate(newPredicate));

    // Does not contain 'z'
    assertEquals(0, this.sentencesFullOfPunctuation.countZPredicate(predicate));
    assertEquals(0, this.pigLatin.countZPredicate(predicate));
    assertEquals(0, this.punctuationOnly.countZPredicate(predicate));
  }

  /** Test filter for PunctuationNode count. */
  @Test
  public void testFilterPunctuation() {

    // Generalized method to instantiate
    Predicate<Node> predicate = n -> n instanceof PunctuationNode;

    // Overriding the Predicate class method to instantiate
    Predicate<Node> newPredicate =
        new Predicate<Node>() {
          @Override
          public boolean test(Node node) {
            return node instanceof PunctuationNode;
          }
        };

    // Contains PunctuationNodes
    assertEquals(4, this.sentencesFullOfPunctuation.countPunctuation(predicate));
    assertEquals(2, this.punctuationOnly.countPunctuation(newPredicate));

    // Does not contain PunctuationNodes
    assertEquals(0, this.sentence.countPunctuation(newPredicate));
    assertEquals(0, this.pigLatin.countPunctuation(predicate));
  }

  /** Test pigLatin() translator. */
  @Test
  public void testPigLatin() {
    this.pigLatin.toPigLatin();
    assertEquals("AKINGMAY AWAY IGPAY EALDAY ABOUTWAY IGPAY ATINLAY ", this.pigLatin.toString());
  }

  /** Test clone() method. */
  @Test
  public void testClone() {
    Sentence copy = sentence.clone();
    assertEquals("HELLO ZEBRAS ARE ZEBRAS ", copy.toString());
  }

  /** Test merge(SentenceImpl other) method. */
  @Test
  public void testMerge() {
    // Check merge with null sentence
    assertEquals("!;", this.punctuationOnly.merge(nullSentence).toString());

    // Check merge with non-null
    assertEquals(
        "HELLO ZEBRAS ARE ZEBRAS HELLO !I .LOVE .DOGGIES !",
        this.sentence.merge(sentencesFullOfPunctuation).toString());
  }

  /** Test toString() method. */
  @Test
  public void testToString() {
    assertEquals("MAKING A PIG DEAL ABOUT PIG LATIN ", this.pigLatin.toString());
    assertEquals("HELLO ZEBRAS ARE ZEBRAS ", this.sentence.toString());
    assertEquals("HELLO !I .LOVE .DOGGIES !", this.sentencesFullOfPunctuation.toString());
    assertEquals("!;", this.punctuationOnly.toString());
  }
}
