#!/bin/bash


if [ -z $1 ]
then
    echo "No arguments supplied."
    echo "usage: ./test [filename | all] [flags]"
    echo "optional flags: -n [no java output]"
    exit 2
fi

JAVA_FLAGS=""

if [ "-n" == "$2" ]
then
    JAVA_FLAGS="n"
fi

OK_FILES=./test-files/minijava_tests/no_inheritence/valid/*.java
NOT_SO_OK_FILES=./test-files/minijava_tests/no_inheritence/invalid/*.java 

for f in $OK_FILES
do
  echo "Testing file $f"
  java -cp build/jar/mjc.jar:lib/antlr-4.2-complete.jar mjc.JVMMain $f "-$JAVA_FLAGS"
  STATUS=${?}
  if [ $STATUS -ne 0 ]
    then
      echo "Error found in valid java file: $f"
  fi
done

for f in $NOT_SO_OK_FILES
do
  echo "Testing file $f"
  java -cp build/jar/mjc.jar:lib/antlr-4.2-complete.jar mjc.JVMMain $f "-$JAVA_FLAGS"
  STATUS=${?}
  if [ $STATUS -ne 1 ]
    then
      echo "No error found in invalid java file: $f"
  fi
done