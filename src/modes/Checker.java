package modes;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
/**
 * The Checker class is used for Mode 1 operations. It takes an input file and reads the string to a
 * string array by splitting it up in the letters it consists of. The string array is then checked,
 * using two indexers, for any repetitions and if found it saves the starting indices of the found
 * repetitions and prints them to an output file ending in "*_chk.txt". Author: Ryan Pienaar
 * (24688207)
 */
public class Checker {
  public String[] wordArray;
  public String fileName;
  private final String alph = "ACGT";

  /**
   * Adds the file containing the string to be checked and reads the contents of the file.
   *
   * @param inputFile the file to be read.
   */
  public void addFile(File inputFile) {
    readFile(inputFile);
    fileName = getFileName(inputFile);
  }

  /**
   * Reads the contents of the file and writes it to an array where each index contains one letter
   * from the string.
   *
   * @param inputFile the file containing the string to be checked.
   */
  public void readFile(File inputFile) {
    try {
      BufferedReader br = new BufferedReader(new FileReader(inputFile));
      String tempString = br.readLine();
      String[] tempArray = new String[tempString.length()];
      for (int i = 0; i < tempArray.length; i++) {
        tempArray[i] = Character.toString(tempString.charAt(i));
      }
      for (int i = 0; i < tempString.length(); i++) {
        if (!alph.contains(tempString.substring(i, i + 1))) {
          System.err.println("ERROR: invalid alphabet symbol");
          System.exit(1);
        }
      }
      wordArray = tempArray;
    } catch (IOException e) {
      System.err.println("ERROR: invalid or missing file");
    }
  }

  /**
   * Retrieves the file name of the file before the extension identifier.
   *
   * @param inputFile the file to get the name from.
   * @return returns the file name.
   */
  public String getFileName(File inputFile) {
    String fileName;
    String tempFileName = inputFile.getName();
    if (tempFileName.contains(".")) {
      fileName = tempFileName.substring(0, tempFileName.indexOf("."));
    } else {
      fileName = tempFileName;
    }
    return fileName;
  }

  /**
   * Checks the array containing the characters of the string for any repetitions and outputs it to
   * a specifically named text file ending with "*_chk.txt".
   */
  public void checkFile() {
    int indexerA = 1;
    int indexerB = 0;
    int disp = 0;
    int count = 0;
    try (FileWriter stringWriter = new FileWriter("../out/" + fileName + "_chk.txt")) {
      while (indexerA < wordArray.length) {
        while (indexerB < indexerA) {
          if (wordArray[indexerB + disp].equals(wordArray[indexerA + disp])) {
            StringBuilder sb = new StringBuilder();
            while (indexerA + disp < wordArray.length
                && wordArray[indexerB + disp].equals(wordArray[indexerA + disp])) {
              sb.append(wordArray[indexerB + disp]);
              disp++;
              if (sb.length() > 1
                  && sb.substring(0, 1).equals(sb.substring(sb.length() - 1, sb.length()))) {
                stringWriter.write(sb + " " + indexerB + " " + indexerA + "\n");
                count++;
              }
            }
          }
          indexerB++;
          disp = 0;
        }
        indexerA++;
        indexerB = 0;
      }
      if (count == 0) {
        stringWriter.write("None");
      }
    } catch (IOException e) {
      System.err.println("ERROR: invalid or missing file");
    }
  }

  /**
   * This method has been specially made for the BruteForceGenerator class to check if a string
   * contains any repetitions.
   *
   * @param input the string to be checked for any repetitions.
   * @return returns 1 at the moment a repetition is found. Returns 0 if no repetitions are found.
   */
  public int checkString(String input) {
    int indexerA = 1;
    int indexerB = 0;
    int disp = 0;
    String[] arr = new String[input.length()];
    for (int i = 0; i < input.length(); i++) {
      arr[i] = String.valueOf(input.charAt(i));
    }
    while (indexerA < input.length()) {
      while (indexerB < indexerA) {
        if (arr[indexerB + disp].equals(arr[indexerA + disp])) {
          StringBuilder sb = new StringBuilder();
          while (indexerA + disp < arr.length
              && arr[indexerB + disp].equals(arr[indexerA + disp])) {
            sb.append(arr[indexerB + disp]);
            disp++;
            if (sb.length() > 1
                && sb.substring(0, 1).equals(sb.substring(sb.length() - 1, sb.length()))) {
              return 1;
            }
          }
        }
        indexerB++;
        disp = 0;
      }
      indexerA++;
      indexerB = 0;
    }
    return 0;
  }
}
