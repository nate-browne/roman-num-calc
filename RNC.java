/*
 * Name: Nate Browne
 * Date: 20 September 2017
 * File: RNC.java
 * Version: 1.0
 * This program translates strings of Roman numerals into the decimal
 * equivalent.
 */

import java.util.*;

public class RNC {

  /* Instance variables and constants declaration */

  private static Deque<String> stack1; // Stack used for storing user input

  // HashMap used to store the values of the Roman Numerals
  private static HashMap<String, Integer> numerals;

  // Array of numerals used in grabInput method
  private static String[] numeralList = {"I", "V", "X", "L", "C", "D", "M"};

  /**
   * This method grabs the user input and stores it in stack1 for use in the
   * calculation method.
   */
  private static void grabInput() {

    // Used to grab input in this method
    Scanner input = new Scanner(System.in);

    String userInput = null;

    // Used to figure out if user input is a valid Roman numeral
    boolean foundInArray = false;

    System.out.print("Type in a string of Roman Numerals ");
    System.out.print("(I, V, X, L, C, D, M). ");
    System.out.print("\nThen, type EOF when finished to have it calculated in");
    System.out.print(" decimal and hexadecimal. \nPress enter between each ");
    System.out.println("character for correct parsing.");


    // Start loop for grabbing values
    while(input.hasNext()) {

      // Grab first line of input
      userInput = input.next().toUpperCase();

      // Iterate through the array checking if the input is valid
      for(int i = 0; i < numeralList.length; i++) {

        if(userInput.equals(numeralList[i])) {

          foundInArray = true;
        }
      }

      // Add the verified string into the stack
      if(!foundInArray) {

        System.out.println("Invalid input for user string " + userInput);
      } else {

        stack1.push(userInput);
        System.err.println("You added " + stack1.peek());
      }

      // Reset the boolean and string for the next set of input
      foundInArray = false;
      userInput = null;
    }

    // Close the scanner to finish reading input
    input.close();
  }

  /**
   * This method takes the user input from the grabInput method and calculates
   * it into decimal.
   * @return the integer value of the roman numeral strings entered in.
   */
  private static int calculate() {

    // Return value of the method
    int answer = 0;

    // Turn the stack into an array for printing
    String[] inputArray = stack1.toArray(new String[0]);

    // Repeat the input back to the user
    System.out.print("\nThe Roman Numeral you entered to calculate is: ");
    for(int i = inputArray.length - 1; i >= 0; i--) {

      System.out.print(inputArray[i]);
    }

    System.out.println();

    return answer;
  }

  /**
   * This is the main method of the program. It calls grabInput and calculate to
   * correctly turn Roman numerals to decimal and later translates the answer to
   * hexadecimal before looping.
   * @param args String array of command line arguments
   */
  public static void main(String[] args) {

    // Create the HashMap
    numerals = new HashMap<String, Integer>(7);

    // Add the correct values into the HashMap
    numerals.put("I", 1);
    numerals.put("V", 5);
    numerals.put("X", 10);
    numerals.put("L", 50);
    numerals.put("C", 100);
    numerals.put("D", 500);
    numerals.put("M", 1000);

    // Create the stack used for input
    stack1 = new ArrayDeque<String>();

    // Grab the user input first
    grabInput();

    // Convert to Roman Numerals
    int answer = calculate();

    // Report the results
    System.out.print("The Roman numeral string you entered converted to");
    System.out.println(" decimal is " + answer);

    System.out.println("In hex, the value is " + Integer.toHexString(answer));
  }
}
