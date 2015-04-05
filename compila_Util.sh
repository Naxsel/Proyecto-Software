if [ ! -d bin ]
then
	mkdir bin
fi
javac -d ./bin -cp ./bin ./src/Util/*.java
