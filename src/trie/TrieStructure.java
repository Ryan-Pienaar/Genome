package trie;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/**
 * This class is a modified version of the trie structure class used in the CS144 project of the
 * student Ryan Pienaar (24688207). I have full rights to use this code as I am the original author
 * of it and the author of the current CS214 project. As to my knowledge I am allowed to use this
 * code for this project. The trie structure class has been modified to only make use of only of
 * states and not edges and states. Author: Ryan Pienaar (24688207)
 */
public class TrieStructure {

  private final State startState;
  private State current;
  private int size = 0;
  private Character rootString;

  /**
   * Constructor method.
   *
   * @param rootString The root string for the trie structure.
   */
  public TrieStructure(String rootString) {
    this.rootString = rootString.charAt(0);
    startState = new State(true, this.rootString, rootString);
    current = startState;
  }

  /**
   * Adds a new string to the structure.
   *
   * @param s The string to be added to the structure.
   */
  public void addNewString(String s) {
    current = startState;
    for (int i = 1; i < s.length(); i++) {
      char ch = s.charAt(i);
      int index = -1;
      List<State> children = current.getChildStates();
      for (int j = 0; j < children.size(); j++) {
        if (children.get(j).getStateChar() == ch) {
          index = j;
        }
      }
      if (index == -1) {
        State state = new State(true, ch, s.substring(0, i + 1));
        current.addLink(state);
      } else {
        current = children.get(index);
      }
      size++;
    }
  }

  /**
   * Returns all the strings in the structure
   *
   * @return Returns an array list containing all the strings in the structure.
   */
  public ArrayList<String> getAllStrings() {
    ArrayList<String> words = new ArrayList<>();
    Queue<State> queue = new LinkedList<State>();
    State checkState = startState;
    queue.add(checkState);
    while (queue.size() > 0) {
      checkState = queue.poll();
      if (checkState.isEnd()) {
        words.add(checkState.stateString());
      }
      for (int i = 0; i < checkState.getNumbOfOutgoingEdges(); i++) {
        queue.add(checkState.getChildStates().get(i));
      }
    }
    return words;
  }

  /**
   * Gives the amount of string elements present in the structure.
   *
   * @return Returns an int value of the number of strings in the structure.
   */
  public int getNumberOfElements() {
    if (getAllStrings().size() > 0) {
      return size;
    }
    return 0;
  }
}
