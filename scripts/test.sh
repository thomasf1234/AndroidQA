#!/bin/sh
#exit on error
set -e

#run from app directory 
HELPER=~/NetBeansProjects/AndroidHelp/scripts/helper.sh

$HELPER deploy_testpkg

METHODS_TO_TEST=(
  "MyDiaryTest#testAnswerQuestion"
  "MyDiaryTest#testRecording"
  "MyDiaryTest#testAnswerAndRecording"
  "MyDiaryTest#testClearRecording"
  "MyDiaryTest#testRerecording"
)
  
for method in "${METHODS_TO_TEST[@]}"
do
  $HELPER test_method com.abstractx1.mydiary com.abstractx1.androidqa.mydiary.$method    
done

