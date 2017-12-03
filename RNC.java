/*
 * Name: Nate Browne
 * Date: 20 September 2017
 * File: RNC.java
 * Version: 1.0
 * Last Modified: 9 October 2017
 * This program translates strings of Roman numerals into the decimal
 * equivalent.
 */

import java.util.*;

public class RNC {

  /* Instance variables and constants declaration */

  // HashMap used to store the values of the Roman Numerals
  private static HashMap<Character, Integer> numerals;

  // Array of numerals used in grabInput method
  private static char[] numeralList = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};

  /**
   * This method grabs the user input and stores it in stack1 for use in the
   * calculation method.
   * @return the string grabbed by the user
   */
  private static String grabInput() throws NoSuchElementException {

    // Used to grab input in this method
    Scanner input = new Scanner(System.in);

    System.out.print("\n\nType in a string of Roman Numerals ");
    System.out.print("(I, V, X, L, C, D, M). ");
    System.out.print("\nThen, hit enter when finished for calculation in");
    System.out.print(" decimal and hexadecimal. Type EOF to end the program\n");

    // Grab the user string
    String toConvert = input.next().toUpperCase();

    return toConvert;
  }

  /**
   * This method takes the user input from the grabInput method and calculates
   * it into decimal.
   * @param numberToConvert the number the user has entered from grabInput
   * @return the integer value of the roman numeral strings entered in.
   */
  private static int eval(String numberToConvert) {

    // Return value of method
    int answer = 0;

    // Repeat the input back to the user
    System.out.println("\nThe Roman Numeral you entered to calculate is: " +
                      numberToConvert);

    // Add a new line for readability
    System.out.println();

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
   * hexadecimal before closing.
   * @param args String array of command line arguments
   */
  public static void main(String[] args) {

    // Create the HashMap
    numerals = new HashMap<Character, Integer>(numeralList.length);

    // String for the user
    String input;

    // Add the correct values into the HashMap
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

        // Grab the user input first
        String toConvert = grabInput();

        // Evaluate the user string
        int result = eval(toConvert);

        // Report the results
        System.out.print("The Roman numeral string you entered converted to");
        System.out.println(" decimal is: " + result);

        String hexResult = Integer.toHexString(result).toUpperCase();

        System.out.println("In hex, the number is: 0x" + hexResult);
      } catch(NoSuchElementException e) {

        System.out.println("Exiting...\n");
        System.exit(0);
      }
    }
  }
}
