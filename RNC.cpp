/*-----------------------------------------------------------------------------

                                                        Author: Nate Browne
                                                        Date: 05 May 2018
                          Roman Numeral Calculator

File Name:       RNC.cpp
Description:     This file implements the RNC class and creates a way to
                 interact with it
-----------------------------------------------------------------------------*/

#include <iostream>
#include <string>
#include <unordered_map>

#include "RNC.h"

/*-----------------------------------------------------------------------------
% Routine Name: RNC::RNC(void)
% File: RNC.cpp
%
% Description:  Constructor for the RNC object. Initializes the unordered_set
%
% Result:       A RNC object is allocated
% Return:       Void.
% Parameters descriptions:
%
% name              description
------------------ ------------------------------------------------------
% N/A
-----------------------------------------------------------------------------*/
RNC::RNC(void) {

  // Create the unordered map
  this->numerals = new std::unordered_map<char, int>();

  // Populate the unordered map
  (*numerals)['I'] = 1;
  (*numerals)['V'] = 5;
  (*numerals)['X'] = 10;
  (*numerals)['L'] = 50;
  (*numerals)['C'] = 100;
  (*numerals)['D'] = 500;
  (*numerals)['M'] = 1000;
}

/*-----------------------------------------------------------------------------
% Routine Name: RNC::~RNC
% File: RNC.cpp
%
% Description:  Destructor for the RNC object.
%
% Result:       The RNC object is deallocated
% Return:       Void.
% Parameters descriptions:
%
% name              description
------------------ ------------------------------------------------------
% N/A
-----------------------------------------------------------------------------*/
RNC::~RNC(void) {

  delete this->numerals;
}

/*-----------------------------------------------------------------------------
% Routine Name: const std::string RNC::grabInput(void)
% File: RNC.cpp
%
% Description:  This function grabs user input by parsing a given string. It
% then checks the string to verify that the entire string is valid before
% sending it on to the eval function.
%
% Result:       The user string is parsed and returned
% Return:       The parsed user string
% Parameters descriptions:
%
% name              description
------------------ ------------------------------------------------------
% N/A
-----------------------------------------------------------------------------*/
const std::string RNC::grabInput(void) {

  // Create the return value
  std::string result;

  // Create the buffer used to hold the input
  char buffer[BUFSIZ];

  // Int used to make sure entire string was valid
  int validCount = 0;

  // Print intro to user
  std::cout << "*******************************************************";
  std::cout << std::endl << "Type in a string of Roman Numerals ";
  std::cout << "(I, V, X, L, C, D, M)." << std::endl;
  std::cout << "Then, hit enter when finished for calculation in";
  std::cout << " decimal and hex. Type \"quit\" to end the program.\n";
  std::cout << "Your Roman Numerals are: ";

  // Read in the user's input
  std::cin >> buffer;

  // Create a C++ string from the C-style string
  result = std::string(buffer);

  // Check if user wants to quit
  if(result == "quit") {

    return result;
  }

  /* Check string for legitimate characters */

  // Create iterator for our string
  std::string::iterator it;

  // Iterate through the string
  for(it = result.begin(); it < result.end(); it++) {

    // Grab current character
    char check = *it;

    // Make sure character is in the unordered_set
    if(numerals->find(check) != numerals->end()) {

      validCount++;
    }
  }

  // Incorrect number of invalid characters == string shouldn't be parsed
  if(validCount != result.length()) {

    std::cerr << "The string has invalid characters. Please try again." <<
      std::endl;

    return "";
  }

  return result;
}

/*-----------------------------------------------------------------------------
% Routine Name: const int RNC::eval(std::string numToConvert)
% File: RNC.cpp
%
% Description:  This function takes the user input parsed from grabInput and
% translates it into its equivalent decimal version.
%
% Result:       The string is turned into its decimal translation
% Return:       The parsed string in integer form
% Parameters descriptions:
%
% name              description
------------------ ------------------------------------------------------
% numToConvert      the parsed string from grabInput
-----------------------------------------------------------------------------*/
const int RNC::eval(const std::string & numToConvert) {

  // Return value, intermediate nums
  int answer = 0, num1, num2;

  // Iterator for the unordered_set
  std::unordered_map<char, int>::iterator it;

  // Iterator for the string
  std::string::const_iterator str = numToConvert.begin();

  // Print the input back to the user
  std::cout << "The Roman Numeral you entered to calculate is: " << numToConvert
    << std::endl;

  // Begin calculation
  for( ; str < numToConvert.end(); str++) {

    // Grab first value
    it = numerals->find(*str);
    num1 = it->second;

    // Grab second value, if it exists
    if(str + 1 < numToConvert.end()) {

      it = numerals->find(*(str + 1));
      num2 = it->second;

      // Compare values
      if(num1 >= num2) {

        answer += num1;
      } else {

        answer += num2 - num1;
        str++;
      }
    } else {

      answer += num1;
      str++;
    }
  }

  return answer;
}
