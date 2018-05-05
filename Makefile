
# Target for compiling all executables
all:
	@echo "Compiling all files..."
	make all_java
	make calc_cpp
	@echo "Complete!"

# Compile and create everything associated with the Java exe + Javadoc
all_java:
	make calc_java
	make javadoc

# Create the Java executable
calc_java: RNCCalc.class
	@echo "Creating Java executable..."
	@echo 'java RNCCalc' > RNCalcJava
	chmod ug+x RNCalcJava
	@echo ""
	@echo "Compilation Successful!"

# Use Valgrind on the C++ target
valgrind_calc: calc_cpp
	valgrind --leak-check=full --read-var-info=yes --show-reachable=yes \
		./RNCalcC++

# Compile the C++ executable
calc_cpp: RNC.o
	@echo "Linking all object modules ..."
	g++ -Wall -pedantic -g -std=c++11 -o RNCalcC++ main.cpp RNC.o
	rm *.o
	@echo ""
	@echo "Compilation Successful!"

# Object file creation for C++ executable
RNC.o: RNC.cpp RNC.h
	@echo "Compiling C++ files..."
	g++ -Wall -pedantic -g -c -std=c++11 RNC.cpp

# Compile the Java files for the executable
RNCCalc.class: RNCCalc.java RNC.java
	@echo "Compiling Java files..."
	javac -g RNCCalc.java

# Create Javadoc documentation
javadoc: RNC.java
	@echo "Creating Javadoc files..."
	javadoc RNC.java
	mkdir Javadoc\ Stuff
	mv *.html Javadoc\ Stuff
	mv package-list Javadoc\ Stuff
	mv script.js Javadoc\ Stuff
	mv stylesheet.css Javadoc\ Stuff
	@echo ""
	@echo "Javadoc files created. Available in Javadoc Stuff directory."

# Safely clean up the project directory
clean:
	@echo "Cleaning up project directory..."
	rm -rf RNCalcJava RNCalcC++ *.class Javadoc\ Stuff *.dSYM
	@echo "Clean."

# Recompile everything
new:
	make clean
	make all
