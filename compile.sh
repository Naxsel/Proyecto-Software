#!/bin/bash
rm -rf ./bin
mkdir ./bin 

#javac -d ./bin/servidor -cp ".:/lib/*.jar" ./src/servidor/servidorchat/*.java
# Util
javac -source 1.7 -target 1.7 -d ./bin ./src/util/*.java
echo "Compilado ./src/util"
# Cliente
javac -source 1.7 -target 1.7 -d ./bin -cp ./bin ./src/cliente/*.java
echo "Compilado ./src/cliente"
# Admin
javac -source 1.7 -target 1.7 -d ./bin -cp ./bin ./src/admin/*.java
echo "Compilado ./src/admin"
# Servidor
javac -source 1.7 -target 1.7 -d ./bin -cp "./lib/*.jar:./bin" ./src/servidor/*.java
echo "Compilado ./src/servidor"
