#!/bin/sh
#exit on error
set -e

#run from app directory 
HELPER=~/AndroidStudioProjects/AndroidHelp/scripts/helper.sh
APK_OUTPUT_DIR=app/build/outputs/apk
TEST_APK_DEFAULT_PATH=$APK_OUTPUT_DIR/app-debug-androidTest.apk

$HELPER build_test
$HELPER deploy $TEST_APK_DEFAULT_PATH

METHODS_TO_TEST=(
  "MyDiaryTest#testAnswerQuestion"
  "MyDiaryTest#testAnswerRecordingQuestion"
  "MyDiaryTest#testClearRecordingQuestion"
)
  
for method in "${METHODS_TO_TEST[@]}"
do
  $HELPER test_method com.abstractx1.mydiary com.abstractx1.androidqa.mydiary.$method    
done

