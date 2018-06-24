/*
 * Author: Nate Browne
 * Version: 2.0
 * Date: 05 May 2018
 * File: RNCCalc.java
 * Main driver for the RNC program in Java. Uses the RNC class to convert input
 */

import java.util.*;

/**
 * This class contains the main driver of the Java version of the Roman Numeral
 * Calculator. Only contains the main method.
 */
public class RNCCalc {

  /**
   * This is the main method of the program. It calls grabInput and eval to
   * correctly turn Roman numerals to decimal and later translates the answer to
   * hexadecimal.
   *
   * @param args String array of command line arguments
   */
  public static void main(String[] args) {

    // String for the user's entered input
    String input;
    int result;

    // Create a RNC object
    RNC converter = new RNC();

    // Print welcome message
    System.out.println("\nWelcome to the Roman Numeral Calculator!");

    // Start infinite loop for program
    while(true) {

      try {

        // Grab the user string
        input = converter.grabInput();

        // If input is the empty string, the user entered an invalid string.
        // Restart the loop.
        if(input.equals("")) {

          System.out.print("\n");
          continue;
        }

        // Evaluate the user string
        result = converter.eval(input);

        // Report the results
        System.out.print("The Roman numeral string you entered converted to");
        System.out.println(" decimal is: " + result);

        String hexResult = Integer.toHexString(result).toUpperCase();

        System.out.println("In hex, the number is: 0x" + hexResult);
      } catch(NoSuchElementException e) {

        System.out.println("\n\nExiting...");
        System.exit(0);
      }
    }
  }
}
