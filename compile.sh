#!/bin/bash
mkdir ./bin

#javac -d ./bin/servidor -cp ".:/lib/*.jar" ./src/servidor/servidorchat/*.java
# Util
javac -d ./bin ./src/util/*.java
echo "Compilado ./src/util"
# Cliente
javac -d ./bin -cp ./bin ./src/cliente/*.java
echo "Compilado ./src/cliente"
# Admin
javac -d ./bin -cp ./bin ./src/admin/*.java
echo "Compilado ./src/admin"
# Servidor
javac -d ./bin -cp ./bin ./src/servidor/*.java
echo "Compilado ./src/servidor"
