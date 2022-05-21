import modes.BruteForceGenerator;
import modes.Checker;
import modes.OptimisedBFG;

import java.io.File;
/**
 * The Repeats class houses the different
 * methods to use each mode on a specific argument input. The
 * first argument variable (args[0]) specifies
 * which mode to be used in the main method. For Mode 1
 * (Checker), there are to be only a maximum
 * of two program arguments, the first argument being the
 * mode and the second the filepath to the
 * file containing the string to be checked for repetitions.
 * For Mode 2 (BruteForceGenerator), there are
 * to be a maximum of 4 program arguments. The first is
 * the mode, the second specifies the letters
 * to be used from the string "ACGT", the third is a
 * string which specifies the root letter, and
 * the fourth is the maximum length of the strings to be
 * generated.
 * Author: Ryan Pienaar (24688207)
 */
public class Repeats {
  private static final String ALPHABET = "ACGT";

  /**
   * Does the error handling for certain exceptions.
   *
   * @param errorCode Specifies which error to display
   */
  public static void errorHandle(int errorCode) {
    switch (errorCode) {
      case 1:
        System.err.println("ERROR: invalid number of arguments");
        break;
      case 2:
        System.err.println("ERROR: invalid argument type");
        break;
      case 3:
        System.err.println("ERROR: invalid mode");
        break;
      case 4:
        System.err.println("ERROR: invalid or missing file");
        break;
      default:
        break;
    }
    System.exit(1);
  }

  /**
   * Main handeling method for different modes to be chosen.
   *
   * @param args Contains all the program arguments
   */
  public static void main(String[] args) {
    if (args.length == 0) {
      errorHandle(1);
    } else {
      try {
        Integer.parseInt(args[0]);
      } catch (NumberFormatException e) {
        errorHandle(2);
      }
      int mode = Integer.parseInt(args[0]);

      if (mode > 3 || mode < 1) {
        errorHandle(3);
      }

      if (mode == 1) {
        if (args.length != 2) {
          errorHandle(1);
        } else {
          File inputFile = new File(args[1]);
          if (inputFile.exists()) {
            Checker mode1 = new Checker();
            mode1.addFile(inputFile);
            mode1.checkFile();
          } else {
            errorHandle(4);
          }
        }
      } else if (mode == 2) {
        if (args.length != 4) {
          errorHandle(1);
        } else if (!ALPHABET.contains(args[2])) {
          errorHandle(2);
        } else {
          int letters = Integer.parseInt(args[1]);
          String rootString = args[2];
          int maxLength = Integer.parseInt(args[3]);
          if (letters > 4 || letters < 0) {
            errorHandle(2);
          } else {
            BruteForceGenerator bfg = new
                    BruteForceGenerator(letters, rootString, maxLength);
            bfg.genSeq();
            bfg.printTrie();
          }
        }
      } else if (mode == 3) {
        if (args.length != 4) {
          errorHandle(1);

        } else if (!ALPHABET.contains(args[2])) {
          errorHandle(2);
        } else {
          double startTime = System.currentTimeMillis();
          double duration = (Double.parseDouble(args[3]) * 1000) / 100 * 70;
          double endTime = startTime + duration;
          String rootString = args[2];
          int letters = Integer.parseInt(args[1]);
          OptimisedBFG optimisedBFG = new
                  OptimisedBFG(endTime, rootString, letters);
          optimisedBFG.genSeq();
        }
      }
    }
  }
}
