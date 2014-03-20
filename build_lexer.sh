#!/bin/bash

if [ -z "$1" ]
  then
    echo "No file name argument supplied"
    echo "run ./build_lexer.sh [filename]"
    exit 2
fi

FILENAME=${1%%.*}
echo "Generating Java files..."
java -jar /usr/local/lib/antlr-4.2-complete.jar -package se.ludrik.snappyj -o ./src/se/ludrik/snappyj/ -no-listener -visitor $FILENAME.g4


echo "Compiling Java files..."
javac $FILENAME*.java
