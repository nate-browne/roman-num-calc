#!/usr/bin/env python2.7
# Author: Nate Browne
# Version: 1.0
# Date: 05 May 2018
# File: main.py
# This file is a translation of the RNCCalc.java file into python. Maintains the
# same OOP approach as the original Java file.

import RNC as cl

"""
Main method. Calls eval and getInput repeatedly in a loop until user wants to
stop calculating values
"""
def main():

    # Create RNC object
    converter = cl.RNC()

    # Print welcome message
    print "\nWelcome to the Roman Numeral Calculator!"

    try:

        while True:

            # Grab the user's string
            usr_in = converter.grab_input()

            # Validate the string
            if usr_in == "":
                print
                continue

            # Convert the string
            result = converter.eval(usr_in)

            # Report results
            print "The Roman Numeral you entered converted to decimal is: %d" % result

            # Report results in hexadecimal
            print "In hex, the number is %s" % hex(result).upper()

    except(EOFError):

        print "\nExiting..."

# Standard boilerplate to run the main function
if __name__ == '__main__':
    main()
