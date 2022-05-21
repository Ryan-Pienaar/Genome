package modes;
import trie.TrieStructure;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * The BruteForceGenerator class is used for
 * Mode 2 operations. It also uses a method in the Checker
 * (Mode 1) class to check if the generated string
 * contains any repetitions it is to be discarded.
 * a Trie structure is used to contain all the
 * generated strings containing no repetitions. After all
 * the necessary strings has been generated,
 * indicated by the maxSeqLen variable, it prints the
 * entire tree to a text file ending
 * in "*_bf.txt" in order.
 * Author: Ryan Pienaar (24688207)
 */
public class BruteForceGenerator {
  private final String rootString;
  private final int maxSeqLen;
  private final int letters;
  private TrieStructure trie;
  // private List<String> longestString

  /**
   * Constructor method.
   *
   * @param letters the letters to be
   * used in generating the strings.
   * @param rootString the root
   * string to be used in generating the strings.
   * @param maxSeqLen the maximum
   * length of the strings to be generated.
   */
  public BruteForceGenerator(int letters, String rootString, int maxSeqLen) {
    this.letters = letters;
    this.rootString = rootString;
    this.maxSeqLen = maxSeqLen;
    this.trie = new TrieStructure(rootString);
  }

  /** Prints the trie tree elements to
   * a text file ending in "*_bf.txt". */
  public void printTrie() {
    int i = 1;
    while (new File("../out/gen" + i + "_bf.txt").exists()) {
      i++;
    }
    List<String> actualList = fetchStrings(0);
    File file = new File("../out/gen" + i + "_bf.txt");
    try (FileWriter wr = new FileWriter(file)) {
      for (String s : actualList) {
        wr.write(s.length() + " - " + s + "\n");
      }
    } catch (IOException e) {
      System.err.println("ERROR: invalid or missing file");
    }
  }

  /**
   * Fetches the current strings in the trie structure.
   *
   * @param stringLength specified string length
   * of the strings to be returned.
   * @return returns an array list
   * containing the fetched strings.
   */
  public List<String> fetchStrings(int stringLength) {
    List<String> inputList = trie.getAllStrings();
    List<String> aux = new ArrayList<>();
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
   *
   * @param string the string to be added
   *               to the trie structure.
   */
  public void addKey(String string) {
    trie.addNewString(string);
  }

  /**
   * Generates the strings with no repetitions
   * and adds the generated strings to the trie structure.
   */
  public void genSeq() {
    Checker ch = new Checker();
    int currentLen = 1;

    if (maxSeqLen != 0) {
      while (currentLen != maxSeqLen) {
        List<String> aux = fetchStrings(currentLen);
        for (int i = 0; i < aux.size(); i++) {
          String temp = aux.get(i);
          if (ch.checkString(temp + "A") == 0 && letters > 0) {
            addKey(temp + "A");
          }
          if (ch.checkString(temp + "C") == 0 && letters > 1) {
            addKey(temp + "C");
          }
          if (ch.checkString(temp + "G") == 0 && letters > 2) {
            addKey(temp + "G");
          }
          if (ch.checkString(temp + "T") == 0 && letters > 3) {
            addKey(temp + "T");
          }
        }
        currentLen++;
      }
    } else {
      int additions = 1;
      while (additions != 0) {
        additions = 0;
        List<String> aux = fetchStrings(currentLen);
        for (int i = 0; i < aux.size(); i++) {
          String temp = aux.get(i);
          if (ch.checkString(temp + "A") == 0 && letters > 0) {
            addKey(temp + "A");
            additions++;
          }
          if (ch.checkString(temp + "C") == 0 && letters > 1) {
            addKey(temp + "C");
            additions++;
          }
          if (ch.checkString(temp + "G") == 0 && letters > 2) {
            addKey(temp + "G");
            additions++;
          }
          if (ch.checkString(temp + "T") == 0 && letters > 3) {
            addKey(temp + "T");
            additions++;
          }
        }
        currentLen++;
      }
    }
  }
}
