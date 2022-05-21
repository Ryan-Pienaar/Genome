package trie;
import java.util.ArrayList;
/**
 * This class is a modified version of the 
 * state class used in the CS144 project of the
 * student Ryan Pienaar (24688207). I have full 
 * rights to use this code as I am the original author
 * of it and the author of the current CS214 project. 
 * As to my knowledge I am allowed to use this
 * code for this project. The state class has been
 * modified to not use the original edge class.
 * Author: Ryan Pienaar (24688207)
 */
public class State {
    private final String stateString;
    private boolean isEnd;
    private boolean visited; //For DFS purposes
    public Character stateChar;
    private ArrayList<State> childStates = new ArrayList<>();

    /** Constructor method. Creates a new State.
     * @param accept If the State is at an accept state or not.
     * @param stateChar the character added to the parent state string.
     * @param stateString the string of the state.
     */
    public State(boolean accept, Character stateChar, String stateString) {
        this.stateChar = stateChar;
        this.stateString = stateString;
        isEnd = accept;
    }

    /** Fetches the current child states of the current state.
     * @return Returns an array list containing all the outgoing Edges.
     */
    public ArrayList<State> getChildStates() {
        return childStates; 
    }

    /** The character added to get to the current state string.
     * @return returns the state character.
     */
    public char getStateChar() {
        return stateChar; 
    }

    /** Fethes the number of child states of the current state.
     * @return Returns an int value of the amount of outgoing states.
     */
    public int getNumbOfOutgoingEdges() {
        return childStates.size();
    }

    /** Checks if a State is in a accept state.
     * @return Returns a boolean value if the word is in a accept state.
     */
    public boolean isEnd() {
        return isEnd; 
    }

    /** Fetches the string contained in the state.
     * @return Returns a string containing the word that the State represents.
     */
    public String stateString() {
        return stateString; 
    }

    /** Adds a link between two States.
     * @param s The state from which a link is to be added.
     */
    public void addLink(State s) {
        childStates.add(s); 
    }
}
