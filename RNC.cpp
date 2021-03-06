/*-----------------------------------------------------------------------------

                                                        Author: Nate Browne
                                                        Date: 05 May 2018
                          Roman Numeral Calculator

File Name:       RNC.cpp
Description:     This file implements the RNC class and creates a way to
                 interact with it
-----------------------------------------------------------------------------*/

#include <iostream>
#include <cstdio>
#include <cstring>
#include <string>
#include <cctype>

#include "RNC.h"

/*-----------------------------------------------------------------------------
% Routine Name: RNC::RNC(void)
% File: RNC.cpp
%
% Description:  Constructor for the RNC object. Initializes the unordered_map
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

  // Return value
  std::string result;

  // Buffer used to hold the input, char to check, pointers for fgets and strstr
  char buffer[BUFSIZ] = {0}, check, *retVal, *newline;

  // index for converting string to upper case, location of newline
  int ind = 0, loc;

  // Int used to make sure entire string was valid
  unsigned int validCount = 0;

  // Print intro to user
  std::cout << "*******************************************************";
  std::cout << std::endl << "Type in a string of Roman Numerals ";
  std::cout << "(I, V, X, L, C, D, M)." << std::endl;
  std::cout << "Then, hit enter when finished for calculation in";
  std::cout << " decimal and hex. Type \"quit\" to end the program.\n";
  std::cout << "Your Roman Numerals are: ";

  // Read in the user's input
  retVal = fgets(buffer, BUFSIZ, stdin);

  // Check for EOF
  if(!retVal) {

    std::cout << std::endl;
    return "QUIT";
  }

  // Find the \n and replace it with a null
  newline = strstr(buffer, "\n");
  loc = newline - buffer;
  buffer[loc] = '\0';

  // Convert entire string to upper case
  while(buffer[ind]) {

    buffer[ind] = toupper(buffer[ind]);
    ind++;
  }

  // Create a C++ style string
  result = std::string(buffer);

  // Check if user wants to quit
  if(result == "QUIT") {

    return result;
  }

  /* Check string for legitimate characters */

  // Create iterator for our string
  std::string::iterator it;

  // Iterate through the string
  for(it = result.begin(); it < result.end(); it++) {

    // Grab current character
    check = *it;

    // Make sure character is in the unordered_map
    if(this->numerals->find(check) != this->numerals->end()) {

      validCount++;
    }
  }

  // Incorrect number of invalid characters == string shouldn't be parsed
  if(validCount != result.length()) {

    std::cerr << "The string has invalid characters. Please try again." <<
      std::endl;

    return "invalid";
  }

  return result;
}

/*-----------------------------------------------------------------------------
% Routine Name: const int RNC::eval(std::string & numToConvert)
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
    it = this->numerals->find(*str);
    num1 = it->second;

    // Grab second value, if it exists
    if(str + 1 < numToConvert.end()) {

      it = this->numerals->find(*(str + 1));
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
