
all:
	make calc
	make javadoc

calc: RNC.class
	echo 'java RNC' > RNCalculator
	chmod ug+x RNCalculator

RNC.class: RNC.java
	javac RNC.java

javadoc: RNC.java
	javadoc RNC.java
	mkdir Javadoc\ Stuff
	mv *.html Javadoc\ Stuff
	mv package-list Javadoc\ Stuff
	mv script.js Javadoc\ Stuff
	mv stylesheet.css Javadoc\ Stuff

clean:
	rm -rf RNCalculator *.class
	rm -rf Javadoc\ Stuff

new:
	make clean
	make calc
