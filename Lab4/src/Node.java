/**
 * This interface represents all operations to be supported by WordNodes, PunctuationNodes, and
 * EmptyNodes to create a Node linked list.
 */
public interface Node {

  /**
   * Returns the number of nodes in the linked list.
   *
   * @return node count
   */
  String getContent();

  // get the content from every node, giving us a string represntation
}
