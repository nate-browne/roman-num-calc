/*
 * Name: Nate Browne
 * Date: 20 September 2017
 * File: RNC.java
 * Version: 1.0
 * Last Modified: 4 October 2017
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
    System.out.print(" decimal and hexadecimal. \nAdd a space between each ");
    System.out.println("character for correct parsing.");


    // Start loop for grabbing values
    while(input.hasNext()) {

      // Grab input and convert it to upper case for comparison
      userInput = input.next().toUpperCase();

      // Iterate through the array checking if the input is a valid numeral
      for(int i = 0; i < numeralList.length; i++) {

        if(userInput.equals(numeralList[i])) {

          foundInArray = true;
        }
      }

      // Add the verified string into the stack
      if(!foundInArray) {

        System.out.println("Invalid input for user string " + userInput);
        System.out.print("Your other input has been saved; please retype your");
        System.out.print(" last entry here: ");
      } else {

        stack1.add(userInput);
      }

      // Reset the boolean and string for the next set of input
      foundInArray = false;
      userInput = null;
    }

    // Finally, close the scanner to finish parsing input
    input.close();
  }

  /**
   * This method takes the user input from the grabInput method and calculates
   * it into decimal.
   * @return the integer value of the roman numeral strings entered in.
   */
  private static int eval() {

    // Return value of the method, last entered number, and the next number
    int answer = 0, currentNum = 0, nextNum = 0, numAfter = 0, ogNextNum = 0;

    // Turn the stack into an array for printing back to the user
    String[] inputArray = stack1.toArray(new String[0]);

    // Repeat the input back to the user
    System.out.print("\nThe Roman Numeral you entered to calculate is: ");
    for(int i = 0; i < inputArray.length; i++) {

      System.out.print(inputArray[i]);
    }

    // Add a new line for readability
    System.out.println();

    // Begin calculation
    while(!stack1.isEmpty()) {

      // Grab the current number from the deque and find the decmial value
      String data = stack1.poll();
      currentNum = numerals.get(data);

      // Grab the upcoming number from the deque
      String next = stack1.poll();

      // Grab two numbers ahead from the deque
      String later = stack1.poll();

      /* Perform internal calculations on next and later variables */

      // If the next number isn't null, set two variables to the HashMap value
      // for the string entered
      if(next != null) {

        ogNextNum = nextNum = numerals.get(next);

        // If the 2nd next number isn't null, set numAfter to the HashMap value
        // for the string entered in
        if(later != null) {

          numAfter = numerals.get(later);

          // Subtract if two numbers down is larger than the next number
          if(numAfter > nextNum) {

            nextNum = numAfter - nextNum;

          // Add if less than or equal to
          } else {

            nextNum += numAfter;
          }
        }

        // If the original next number is larger than the current, subtract
        // those two numbers and add the difference to the running total
        if(ogNextNum > currentNum) {

          answer += nextNum - currentNum;

        // Add the two numbers and add the sum to the running total
        } else {

          answer += nextNum + currentNum;
        }

      // The last number has been polled, so add it to the running total
      } else {

        answer += currentNum;
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
    int result = eval();

    // Report the results
    System.out.print("The Roman numeral string you entered converted to");
    System.out.println(" decimal is: " + result);

    String hexResult = Integer.toHexString(result).toUpperCase();

    System.out.println("In hex, the number is: 0x" + hexResult);
  }
}
