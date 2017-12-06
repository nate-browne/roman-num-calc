/*
 * Name: Nate Browne
 * Date: 20 September 2017
 * File: RNC.java
 * Version: 1.0
 * Last Modified: 2 December 2017
 * This program translates strings of Roman numerals into the decimal
 * equivalent.
 */

import java.util.*;

public class RNC {

  /* Instance variables and constants declaration */

  // HashMap used to store the values of the Roman Numerals
  private static HashMap<Character, Integer> numerals;

  // Array of valid Roman Numerals used in grabInput method
  private static char[] numeralList = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};

  /**
   * This method grabs the user input. It starts with parsing a string and
   * converting it all to upper case. Then, a boolean array is created with the
   * same length as the string entered in so that each boolean in the array is a
   * one-to-one correspondence with each character in the string. Each character
   * of the string is checked against the numeralList array to make sure it is a
   * valid Roman Numeral, and if every character is valid, the string is
   * returned.
   *
   * @throws NoSuchElementException
   * @return the string grabbed by the user
   */
  private static String grabInput() throws NoSuchElementException {

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
    for(int i = 0; i < toConvert.length(); i++) {

      // Grab the current character
      char checkLegit = toConvert.charAt(i);

      // Run current char against array of valid characters
      for(int j = 0; j < numeralList.length; j++) {

        // If the character is valid, set the corresponding boolean to true
        if(checkLegit == numeralList[j]) {

          checkString[i] = true;
        }
      }
    }

    // Check every boolean value to see if any invalid chars were entered
    for(int last = 0; last < checkString.length; last++) {

      if(!checkString[last]) {

        System.out.print("You entered an invalid character in your string. ");
        System.out.println("Please try again.");

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
  private static int eval(String numberToConvert) {

    // Return value of method
    int answer = 0;

    // Repeat the input back to the user
    System.out.println("\nThe Roman Numeral you entered to calculate is: " +
                       numberToConvert + "\n");

    // Begin calculation
    for(int i = 0; i < numberToConvert.length(); i++) {

      // Grab first value
      int num1 = numerals.get(numberToConvert.charAt(i));

      // Grab second value, if it exists
      if(i + 1 < numberToConvert.length()) {

        int num2 = numerals.get(numberToConvert.charAt(i + 1));

        // Compare values
        if(num1 >= num2) {

          // Value is greater than or equal to, so add it to the final result
          answer += num1;
        } else {

          // Value is less, so subtract the values before adding to final result
          answer += num2 - num1;
          i++;
        }
      } else {

        // Last number has been parsed, so add it to the final result
        answer += num1;
        i++;
      }
    }

    return answer;
  }

  /**
   * This is the main method of the program. It calls grabInput and eval to
   * correctly turn Roman numerals to decimal and later translates the answer to
   * hexadecimal.
   *
   * @param args String array of command line arguments
   */
  public static void main(String[] args) {

    // Create the HashMap
    numerals = new HashMap<Character, Integer>(numeralList.length);

    // String for the user's entered input
    String input;

    // Populate the HashMap
    numerals.put('I', 1);
    numerals.put('V', 5);
    numerals.put('X', 10);
    numerals.put('L', 50);
    numerals.put('C', 100);
    numerals.put('D', 500);
    numerals.put('M', 1000);

    // Print welcome message
    System.out.println("\nWelcome to the Roman Numeral Calculator!");

    // Start infinite loop for program
    while(true) {

      try {

        // Grab the user string
        input = grabInput();

        // If input is the empty string, the user entered an invalid string.
        // Restart the loop.
        if(input.equals("")) {

          continue;
        }

        // Evaluate the user string
        int result = eval(input);

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
