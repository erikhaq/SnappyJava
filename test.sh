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

if [ "all" == "$1" ]
then

  # Testing valid test cases
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

  # Testing invalid test cases
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

else

  # Testing single file
  echo "Testing file $1"
  java -cp build/jar/mjc.jar:lib/antlr-4.2-complete.jar mjc.JVMMain "$1" "-$JAVA_FLAGS"
  echo "Exit status: ${?}"
  
fi



