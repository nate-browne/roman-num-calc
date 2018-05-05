# Roman Numeral Calculator

### Introduction

This simple program turns your string of Roman numerals into a decimal and
hexadecimal number. There is a C++ version, a Java version, and a Python
version. The included `Makefile` allows for simple compilation of all of the
files included.

### Compilation

The provided `Makefile` includes targets for creating the executable as well as
a target to create Javadoc documentation. Both can be done simultaneously by
typing `make all_java` or just `make`. The target for only compiling the calculator
is `make calc_java`

The python file is executable by default and can be run with either `python2
RNC.py` or `./RNC.py`. It uses Python2 by default. If your python2 is in a
different location, change the first line from the provided one to instead say:
```
#!/usr/bin/env python2
```
The C++ file can also be compiled with the provided Makefile, just with a
different target. To compile this version, use `make calc_cpp`.

### Other

Shoutout to Aidan Bohley for providing test cases to verify correctness of this
project. Pull requests are encouraged, but be sure to conform to the [Google
Java style guide](https://google.github.io/styleguide/javaguide.html).

Contact me at natebrowne@outlook.com with any suggestions/bug fixes.
