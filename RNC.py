#!/usr/bin/env python2
# Author: Nate Browne
# Version: 1.0
# Date: 05 May 2018
# File: RNC.py
# This file is a translation of the RNC.java file into python for use in other
# Python projects. Maintains the same OOP approach as the original Java file.

"""
Creates a RNC class used in the file main.py. Contains only a dict with key
value pairs of strings and integers, respectively
Attributes:
    numerals: dict of key value pairs for strings and ints
"""
class RNC(object):

    """
    Constructor. Initializes the dict object
    Param: unused: unused integer value not used at all
    """
    def __init__(self):

        # Initialize the dict
        self.numerals = {}

        # Populate the dict
        self.numerals['I'] = 1
        self.numerals['V'] = 5
        self.numerals['X'] = 10
        self.numerals['L'] = 50
        self.numerals['C'] = 100
        self.numerals['D'] = 500
        self.numerals['M'] = 1000

    """
    Function used to parse the user input verify that it is a valid Roman
    Numeral
    Return: The parsed string to use
    Exceptions Thrown: EOFError when user types ^D
    """
    def grab_input(self):

        # Counter to verify that the string was valid
        counter = 0

        # Print out message to user
        print "*" * 55
        print "\nType in a string of Roman Numerals (I, V, X, L, C, D, M)."
        print "Then, hit enter when finished for calculation in decimal and hex."
        print "Type EOF or 'quit' to end the program."

        try:

            # Parse user input
            usr_str = raw_input("Your Roman Numerals are: ").upper()

            # See if user wants to end the program
            if usr_str == 'QUIT':

                raise EOFError

            # Validate each character in the string
            for char in usr_str:

                if char in self.numerals.keys():

                    counter += 1

            # Make sure entire string was valid
            if counter != len(usr_str):

                print "The string you entered has invalid characters. Try again."
                return ""

            return usr_str
        except(EOFError, KeyboardInterrupt):

            raise EOFError

    """
    Function used to evaluate the given string as Roman Numerals
    Param: usr_str: string to parse
    Return: Integer value corresponding to the string
    """
    def eval(self, usr_str):

        # Return value
        answer = 0

        # Loop index
        ind = 0

        # Print the string back to the user
        print "The Roman Numeral you entered to calulate is: %s" % usr_str

        # Begin calculation
        while True:

            if ind >= len(usr_str):

                break
            else:

                # Grab first number in string
                num1 = self.numerals.get(usr_str[ind])

                # Grab second value, if it exists
                if ind + 1 < len(usr_str):

                    num2 = self.numerals.get(usr_str[ind + 1])

                    # Compare values
                    if num1 >= num2:

                        answer += num1
                    else:

                        answer += num2 - num1
                        ind += 1
                else:

                    answer += num1
                    ind += 1

                ind += 1

        return answer
