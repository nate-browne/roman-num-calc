# Roman Numeral Calculator

### Introduction

This simple program turns your string of Roman numerals into a decimal and
hexadecimal number. There is a C++ version as well as a Java version which are
available in as well as a Python version. The included `Makefile` allows for
simple compilation of all of the non Python files included.

### Compilation/Installation

The provided `Makefile` includes targets for creating the executable as well as
a target to create Javadoc documentation. Both can be done simultaneously by
typing `make all_java` or just `make`. The target for only compiling the calculator
is `make calc_java`

The C++ file can also be compiled with the provided Makefile, just with a
different target. To compile this version, use `make calc_cpp`.

The python executable can be run as normal from a command line, but a pip
installation is on its way (check back soon!)

### Other

Shoutout to Aidan Bohley for providing test cases to verify correctness of this
project. Pull requests are encouraged, but be sure to conform to the [Google
Java style guide](https://google.github.io/styleguide/javaguide.html). For the
C++ program, please conform to the [Google C++ style
guide](https://google.github.io/styleguide/cppguide.html).

Contact me at natebrowne@outlook.com with any suggestions/bug fixes.
