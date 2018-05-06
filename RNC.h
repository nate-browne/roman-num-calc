/*-----------------------------------------------------------------------------

                                                        Author: Nate Browne
                                                        Date: 05 May 2018
                          Roman Numeral Calculator

File Name:       RNC.h
Description:     This header file defines the RNC class used in RNC.cpp
-----------------------------------------------------------------------------*/

#ifndef RNC_h
#define RNC_h

#include <string>
#include <unordered_map>

/*=============================================================================
/ class RNC
/
/ Description: Contains public functions and data for creating a decimal to RNC
/              converter in C++. Translation of the RNC class from RNC.java
/
/ Data Fields:
/    numerals: unordered_set (HashMap) of numeral values with strings for keys
/              and ints for values
/
/ Public Functions:
/
/    RNC(void): Public constructor
/   ~RNC(void): Public destructor
/   const std::string grabInput(): Grabs user input from command line
/   const int eval(const std::string &): Parses the user string and returns the
/   resulting converted number
/============================================================================*/
class RNC {

  std::unordered_map<char, int> *numerals;

public:

  RNC(void);
  ~RNC(void);
  const std::string grabInput(void);
  const int eval(const std::string &);
};

#endif
