/*-----------------------------------------------------------------------------

                                                        Author: Nate Browne
                                                        Date: 05 May 2018
                          Roman Numeral Calculator

File Name:       main.cpp
Description:     Translation of RNCCalc.java into C++.
-----------------------------------------------------------------------------*/

#include <iostream> // cout, endl
#include <cstdio> // printf
#include <cstdlib> // EXIT_SUCCESS

#include "RNC.h"

/*-----------------------------------------------------------------------------
% Routine Name: main
% File: main.cpp
%
% Description:  Creates an RNC converter instance and uses it to convert
% user-entered strings of Roman Numerals into decimal values
%
% Result:       User-entered strings are converted
% Return:       EXIT_SUCCESS upon program termination
% Parameters descriptions:
%
% name              description
------------------ ------------------------------------------------------
% argc              number of command line arguments
% argv              array of command line arguments, passed in as C-style
%                   strings
-----------------------------------------------------------------------------*/
int main(int argc, char *argv[]) {

  std::string input; // String used to parse user input
  int result; // Return value from eval

  // Create a new RNC object
  RNC converter = RNC();

  // Print welcome message
  std::cout << "Welcome to the Roman Numeral Calculator!" << std::endl;

  // Start infinite loop of program
  while(true) {

    // Grab user input
    input = converter.grabInput();

    // If we get the input "quit", end the program
    if(input == "quit") {

      break;

    // If the input is the empty string, repeat the loop
    } else if(input == "") {

      std::cout << "" << std::endl;
      continue;
    }

    // Evaluate the string
    result = converter.eval(input);

    // Report results
    std::cout << "The Roman Numeral string you entered to convert to decimal ";
    std::cout << " is: " << result << std::endl;

    // Print hex result
    printf("In hex, the number is %#X\n", result);
  }

  std::cout << "Exiting..." << std::endl;
  return EXIT_SUCCESS;
}
