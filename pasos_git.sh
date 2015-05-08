#!/bin/bash

git add .
git commit -m "$1"
git pull origin master
git add .
git commit -m "$1"
git push origin master
