/*
 * Author: Nate Browne
 * Date: 20 September 2017
 * File: RNC.java
 * Version: 2.0
 * Last Modified: 5 May 2018
 * This program translates strings of Roman numerals into the decimal
 * equivalent.
 */

import java.util.*;

public class RNC {

  /* Instance variables and constants declaration */

  // HashMap used to store the values of the Roman Numerals
  private HashMap<Character, Integer> numerals;

  // Number of available Roman Numerals
  private static final int NUM_CHARS = 7;

  /**
   * This method grabs the user input. It starts with parsing a string and
   * converting it all to upper case. Then, a boolean array is created with the
   * same length as the string entered in so that each boolean in the array is a
   * one-to-one correspondence with each character in the string. Each character
   * of the string is checked against the keys in the HashMap to verify that it
   * is a valid Roman Numeral, and if every character is valid, the string is
   * returned.
   *
   * @throws NoSuchElementException if the user types EOF. Used to end execution
   * @return the string grabbed by the user
   */
  public String grabInput() throws NoSuchElementException {

    // Create scanner used to parse input in this method
    Scanner input = new Scanner(System.in);

    System.out.print("*******************************************************");
    System.out.print("\nType in a string of Roman Numerals ");
    System.out.println("(I, V, X, L, C, D, M).");
    System.out.print("Then, hit enter when finished for calculation in");
    System.out.println(" decimal and hex. Type EOF to end the program.");
    System.out.print("Your Roman Numerals are: ");

    // Parse the user string, converting it all to upper case
    String toConvert = input.next().toUpperCase();

    // Create a boolean array of length the same size of the string
    boolean[] checkString = new boolean[toConvert.length()];

    //Initialize every boolean in the array to false to begin
    for(int initial = 0; initial < checkString.length; initial++) {

      checkString[initial] = false;
    }

    /* Check the string for legitimate characters */

    // Begin outer for loop going through the parsed string
    for(int ind = 0; ind < toConvert.length(); ind++) {

      // Grab the current character
      char checkLegit = toConvert.charAt(ind);

      // Make sure character is in the HashMap as a key value
      if(numerals.containsKey(checkLegit)) {

        checkString[ind] = true;
      }
    }

    // Check every boolean value to see if any invalid chars were entered
    for(int ind = 0; ind < checkString.length; ind++) {

      if(!checkString[ind]) {

        System.err.print("The character '" + toConvert.charAt(ind) + "' is invalid.");
        System.err.println(" Please try again.");

        return "";
      }
    }

    return toConvert;
  }

  /**
   * This method takes the user input from the grabInput method and calculates
   * it into decimal. The method begins by repeating the input back to the user
   * before starting the calculation algorithm. Two numbers are parsed at a
   * time, with basic comparisons between them according to the rules of Roman
   * Numerals to figure out what number was entered. This number is added to the
   * running total, which is returned.
   *
   * @param numberToConvert the number the user has entered from grabInput
   * @return the integer value of the Roman Numeral strings entered in.
   */
  public int eval(String numberToConvert) {

    // Return value of method and intermediate values
    int answer = 0, num1, num2;

    // Repeat the input back to the user
    System.out.println("\nThe Roman Numeral you entered to calculate is: " +
                       numberToConvert + "\n");

    // Begin calculation
    for(int ind = 0; ind < numberToConvert.length(); ind++) {

      // Grab first value
      num1 = numerals.get(numberToConvert.charAt(ind));

      // Grab second value, if it exists
      if(ind + 1 < numberToConvert.length()) {

        num2 = numerals.get(numberToConvert.charAt(ind + 1));

        // Compare values
        if(num1 >= num2) {

          // Value is greater than or equal to, so add it to the final result
          answer += num1;
        } else {

          // Value is less, so subtract the values before adding to final result
          answer += num2 - num1;
          ind++;
        }
      } else {

        // Last number has been parsed, so add it to the final result
        answer += num1;
        ind++;
      }
    }

    return answer;
  }

  /**
   * Constructor for the RNC object. Initializes the corresponding fields of the
   * RNC class for usage in the other functions
   */
  public RNC() {

    // Create the HashMap
    this.numerals = new HashMap<Character, Integer>(NUM_CHARS);

    // Populate the HashMap
    this.numerals.put('I', 1);
    this.numerals.put('V', 5);
    this.numerals.put('X', 10);
    this.numerals.put('L', 50);
    this.numerals.put('C', 100);
    this.numerals.put('D', 500);
    this.numerals.put('M', 1000);
  }
}
