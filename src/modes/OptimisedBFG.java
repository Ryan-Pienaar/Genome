package modes;
import trie.TrieStructure;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
/**
 * The OptimisedBFG class is used for Mode 3 operations.
 * It also uses a method in the
 * Checker (Mode 1) class to check if the generated string
 * contains any repetitions it is to be discarded.
 * a Trie structure is used to contain all the generated
 * strings containing no repetitions.
 * After all the necessary strings has been generated,
 * indicated by the maxSeqLen variable, it prints the longest
 * generated string to a text file
 * ending in "*_opt.txt" in order.
 * Author: Ryan Pienaar (24688207)
 */
public class OptimisedBFG {
  private double endTime;
  private String root;
  private int alphabet;
  private TrieStructure trie;
  private String currentLongest = "";

  /**
   * Constructor method.
   * @param endTime the time the program should run for.
   * @param root the root string to be used in generating the strings.
   * @param alphabet the letters to be used in generating the strings.
   */
  public OptimisedBFG(double endTime, String root, int alphabet) {
    this.endTime = endTime;
    this.root = root;
    this.alphabet = alphabet;
    this.trie = new TrieStructure(root);
  }

  /**
   * Prints the longest generated string
   * to a file ending in *_opt.txt
   * @param string the string to be printed
   */
  public void print(String string) {
    int i = 1;
    while (new File("../out/out" + i + "_opt.txt").exists()) {
      i++;
    }
    File file = new File("../out/out" + i + "_opt.txt");
    try (FileWriter wr = new FileWriter(file)) {
        wr.write(string.length() + " - " + string + "\n");
    } catch (IOException e) {
      System.err.println("ERROR: invalid or missing file");
    }
  }

  /**
   * Fetches the current strings in the trie structure.
   * @param stringLength specified string length
   * of the strings to be returned.
   * @return returns an array list containing the fetched strings.
   */
  public ArrayList<String> fetchStrings(int stringLength) {
    ArrayList<String> inputList = trie.getAllStrings();
    ArrayList<String> aux = new ArrayList<>();
    if (stringLength == 0) {
      return inputList;
    } else {
      for (int i = 0; i < inputList.size(); i++) {
        String tempString = inputList.get(i);
        if (tempString.length() == stringLength) {
          aux.add(tempString);
        }
      }
    }
    return aux;
  }

  /**
   * Adds a string to the trie structure.
   * @param string the string to be added to the trie structure.
   */
  public void addKey(String string) {
    trie.addNewString(string);
  }

  /**
   * Generates the strings with no repetitions and
   * adds the generated strings to the trie structure.
   */
  public void genSeq() {
    Checker ch = new Checker();
    int currentLen = 1;
    int additions = 1;
    while (additions != 0 && System.currentTimeMillis() < endTime - 10) {
        ArrayList<String> aux = fetchStrings(currentLen);
      currentLongest = aux.get(aux.size() - 1);
      additions = 0;

      for (int i = 0; i < aux.size(); i++) {
        String temp = aux.get(i);
        if (ch.checkString(temp + "A") == 0 && alphabet > 0) {
          addKey(temp + "A");
          additions++;
        }
        if (ch.checkString(temp + "C") == 0 && alphabet > 1) {
          addKey(temp + "C");
          additions++;
        }
        if (ch.checkString(temp + "G") == 0 && alphabet > 2) {
          addKey(temp + "G");
          additions++;
        }
        if (ch.checkString(temp + "T") == 0 && alphabet > 3) {
          addKey(temp + "T");
          additions++;
        }
      }
      currentLen++;
    }
    print(currentLongest);
  }
}
