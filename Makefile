
all:
	make calc

calc: RNC.class
	echo 'java RNC' > RNCalculator
	chmod ug+x RNCalculator

RNC.class: RNC.java
	javac RNC.java

clean:
	rm -rf RNCalculator *.class

new:
	make clean
	make calc
