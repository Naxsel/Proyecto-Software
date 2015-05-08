#!/bin/bash

echo "Intro para realizar add"
read i
git add .
echo "add realizado"
echo "Escribir comentario e Intro para realizar commit"
read coment
git commit -m "$coment"
echo "commit realizado"
echo "Intro para realizar pull"
read i
git pull origin master
echo "pull realizado"
echo "Intro para realizar add"
read i
git add .
echo "add realizado"
echo "Intro para realizar commit (mismo comentario)"
read i
git commit -m "$coment"
echo "commit realizado"
echo "Intro para realizar push"
read i
git push origin master
echo "push realizado"
read i
