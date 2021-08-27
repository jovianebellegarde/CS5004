package cs5004.questionnaire;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * This class represents the questionnaire with questions and identifiers.
 */
public class QuestionnaireImpl implements Questionnaire {
  private final List<QuestionnaireElement> questionnaireList;

  /**
   * Constructs a Questionnaire object with the given linked hashmap.
   */
  public QuestionnaireImpl() {
    this.questionnaireList = new LinkedList<>();
  }

  /**
   * Add a question to the questionnaire.
   *
   * @param identifier a name for the question <b>unique</b> within this questionnaire. Not null or
   *                   empty.
   * @param q          the {@link Question} to be added to the questionnaire
   */
  @Override
  public void addQuestion(String identifier, Question q) throws IllegalArgumentException {
    if (identifier == null || q == null || identifier.equals("")) {
      throw new IllegalArgumentException("Need to enter a valid identifier.");
    }

    this.questionnaireList
            .stream()
            .filter(each -> each.getIdentifier() == null)
            .forEachOrdered(each -> {
              throw new IllegalArgumentException("Can't have a null list.");
            });

    this.questionnaireList.add(new QuestionnaireElement(identifier, q));
  }

  /**
   * Remove the question with the given identifier from the questionnaire.
   *
   * @param identifier the identifier of the question to be removed.
   * @throws NoSuchElementException if there is no question with the given identifier.
   */
  @Override
  public void removeQuestion(String identifier) throws NoSuchElementException {
    if (identifier == null || identifier.equals("")) {
      throw new NoSuchElementException("Question DNE.");
    }

    this.questionnaireList
            .stream()
            .filter(each -> each.getIdentifier() == null)
            .forEachOrdered(each -> {
              throw new NoSuchElementException("Can't have a null list.");
            });

    this.questionnaireList.forEach(each -> {
      if (each.getIdentifier().equalsIgnoreCase(identifier)) {
        this.questionnaireList
                .stream()
                .map(QuestionnaireElement::getIdentifier)
                .filter(eachIdentifier -> eachIdentifier.equalsIgnoreCase(identifier))
                .map(eachIdentifier -> identifier).forEachOrdered(this.questionnaireList::remove);
      } else {
        throw new NoSuchElementException();
      }
    });
  }

  /**
   * Get the question with the given number, based on the order in which it was added to the
   * questionnaire, or the sorted order if the {@code sort()} method is called. The first question
   * is 1, second 2, etc.
   *
   * @param num the number of the question, counting from 1
   * @return the question
   * @throws IndexOutOfBoundsException if there is no such question num
   */
  @Override
  public Question getQuestion(int num) throws IndexOutOfBoundsException {
    if (num < 1 || num > this.questionnaireList.size()) {
      throw new IndexOutOfBoundsException("This question does not exist.");
    }
    int questionNumber = num - 1;
    return this.questionnaireList.get(questionNumber);
  }

  /**
   * Get the question with the given identifier (question having been previously added to the
   * questionnaire).
   *
   * @param identifier the identifier of the question
   * @return the question
   * @throws NoSuchElementException if there is no question with the identifier
   */
  @Override
  public Question getQuestion(String identifier) throws NoSuchElementException {
    if (identifier == null || identifier.equals("")) {
      throw new NoSuchElementException("This question doesn't exit.");
    }
    for (QuestionnaireElement each : this.questionnaireList) {
      if (each.getIdentifier().equals(identifier)) {
        return each.getQ();
      }
    }
    throw new NoSuchElementException("This question does not exist.");
  }

  /**
   * Return a list of all required questions in the questionnaire.
   *
   * @return the required questions.
   */
  @Override
  public List<Question> getRequiredQuestions() {
    List<Question> requiredQuestions;

    requiredQuestions = this.questionnaireList
            .stream()
            .filter(QuestionnaireElement::isRequired)
            .collect(Collectors.toList());

    return requiredQuestions;
  }

  /**
   * Return a list of all optional questions in the questionnaire.
   *
   * @return the optional questions.
   */
  @Override
  public List<Question> getOptionalQuestions() {
    List<Question> optionalQuestions;

    optionalQuestions = this.questionnaireList
            .stream()
            .filter(n -> !(n.isRequired()))
            .collect(Collectors.toList());

    return optionalQuestions;
  }

  /**
   * Report if all required questions have some non-empty response.
   *
   * @return true if all required questions have responses, false otherwise.
   */
  @Override
  public boolean isComplete() {
    return this.questionnaireList
            .stream()
            .noneMatch(each -> each.getAnswer().isEmpty() && each.isRequired());
  }

  /**
   * Return a list of just the responses to all the questions in the questionnaire.
   *
   * @return the responses
   */
  @Override
  public List<String> getResponses() {
    return this.questionnaireList
            .stream()
            .map(QuestionnaireElement::getAnswer)
            .collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   * Produce a new questionnaire containing just the questions where the given predicate returns
   * true. The returned questionnaire is completely independent of this questionnaire. That is, the
   * questions in the returned questionnaire are <b>copies</b> of the original questions.
   *
   * @param pq the predicate
   * @return the new questionnaire
   */
  @Override
  public Questionnaire filter(Predicate<Question> pq) throws IllegalArgumentException {
    if (pq == null) {
      throw new IllegalArgumentException("Can't have a null predicate.");
    }

    Questionnaire filteredQuestions = new QuestionnaireImpl();

    this.questionnaireList
            .stream()
            .filter(pq)
            .forEach(u -> filteredQuestions.addQuestion(u.getIdentifier(), u.getQ()));
    return filteredQuestions;
  }

  /**
   * Sort the questions according to the given comparator. Return values from {@code
   * getQuestion(int)} should reflect the new sorted order following sort.
   *
   * @param comp a comparator for Question
   */
  @Override
  public void sort(Comparator<Question> comp) throws IllegalArgumentException {
    if (comp == null) {
      throw new IllegalArgumentException("Can't have a null comp.");
    }
    this.questionnaireList
            .sort((question1, question2) -> comp.compare(question1.getQ(), question2.getQ()));
  }

  /**
   * Produce a single summary value based on the given folding function and seed value.
   *
   * @param bf   the folding function
   * @param seed the seed value
   * @return the summary value
   */
  @Override
  public <R> R fold(BiFunction<Question, R, R> bf, R seed) throws IllegalArgumentException {
    if (bf == null || seed == null) {
      throw new IllegalArgumentException("Can't have a null bf or seed.");
    }
    R accumulator = seed;
    for (QuestionnaireElement each : this.questionnaireList) {
      accumulator = bf.apply(each.getQ(), accumulator);
    }
    return accumulator;
  }

  @Override
  public String toString() {
    return "";
  }
}
