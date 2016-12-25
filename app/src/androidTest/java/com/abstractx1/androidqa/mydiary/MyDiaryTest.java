package com.abstractx1.androidqa.mydiary;

import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject2;

import com.abstractx1.androidqa.BaseTest;

import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MyDiaryTest extends BaseTest {

    @org.junit.Test
    public void testAnswerQuestion() throws InterruptedException {
        openApp();

        UiObject2 questionsListView = getDevice().findObject(ById("questionsListView"));
        UiObject2 questionOne = questionsListView.getChildren().get(0);
        UiObject2 checkBox = questionOne.findObject(ById("hasAnswerCheckBox"));
        UiObject2 answerPreview = questionOne.findObject(ById("questionsListViewElementAnswerTextView"));

        //checkbox is initially false
        assertEquals(false, checkBox.isChecked());
        //Answer preview is initially false
        assertEquals(null, answerPreview.getText());

        //click first question
        questionOne.click();
        waitForObject(ById("activity_input"));

        UiObject2 answerEditText = getDevice().findObject(ById("answerEditText"));
        answerEditText.click();
        answerEditText.setText("I have typed in this answer!");

        getDevice().findObject(By.descContains("Navigate up")).click();
        waitForObject(ById("activity_title"));

        questionsListView = getDevice().findObject(ById("questionsListView"));
        questionOne = questionsListView.getChildren().get(0);
        checkBox = questionOne.findObject(ById("hasAnswerCheckBox"));
        answerPreview = questionOne.findObject(ById("questionsListViewElementAnswerTextView"));

        //checkbox is initially false
        assertEquals(true, checkBox.isChecked());
        //Answer preview is initially false
        assertEquals("I have typed in this answer!", answerPreview.getText());
    }

    @org.junit.Test
    public void testAnswerRecordingQuestion() throws InterruptedException {
        //openSettings();
        //getDevice().findObject(By.textContains("App")).click();
        //waitForObject(By.res(Pattern.compile(".*action_bar.*", Pattern.DOTALL)).hasDescendant(By.textContains("App")));
        //getDevice().findObject(By.)

        openApp();

        UiObject2 questionsListView = getDevice().findObject(ById("questionsListView"));
        UiObject2 questionOne = questionsListView.getChildren().get(0);
        UiObject2 checkBox = questionOne.findObject(ById("hasAnswerCheckBox"));
        UiObject2 answerPreview = questionOne.findObject(ById("questionsListViewElementAnswerTextView"));

        //checkbox is initially false
        assertEquals(false, checkBox.isChecked());
        //Answer preview is initially false
        assertEquals(null, answerPreview.getText());

        //click first question
        questionOne.click();
        waitForObject(ById("activity_input"));

        UiObject2 recordButton = getDevice().findObject(ById("recordButton"));
        UiObject2 playButton = getDevice().findObject(ById("playButton"));
        UiObject2 clearButton = getDevice().findObject(ById("playButton"));
        UiObject2 recordingDurationTextView = getDevice().findObject(ById("recordingDurationTextView"));

        //initially only recordButton exists
        assertThat(recordButton, notNullValue());
        assertEquals(null, playButton);
        assertEquals(null, clearButton);
        assertEquals(true, recordingDurationTextView.getText().equals("0:00"));

        recordButton.click();

        if (minAndroid6()) {
            //should request for record audio permission
            UiObject2 allowPermissionButton = waitForObject(By.text("Allow"));
            allowPermissionButton.click();
        }

        Thread.sleep(2000);

        recordButton = getDevice().findObject(ById("recordButton"));
        playButton = getDevice().findObject(ById("playButton"));
        clearButton = getDevice().findObject(ById("playButton"));
        recordingDurationTextView = getDevice().findObject(ById("recordingDurationTextView"));

        assertEquals(null, playButton);
        assertEquals(null, clearButton);
        //timer should have updated
        assertEquals(false, recordingDurationTextView.getText().equals("0:00"));

        recordButton.click();

        //confirmation box should appear
        waitForObject(By.res("android:id/message").text("Do you want to save the current recording?"));
        getDevice().findObject(By.text("Yes")).click();

        recordButton = getDevice().findObject(ById("recordButton"));
        playButton = getDevice().findObject(ById("playButton"));
        clearButton = getDevice().findObject(ById("playButton"));

        assertEquals(null, recordButton);
        assertThat(playButton, notNullValue());
        assertThat(clearButton, notNullValue());

        getDevice().findObject(By.desc("Navigate up")).click();
        waitForObject(ById("activity_title"));

        questionsListView = getDevice().findObject(ById("questionsListView"));
        questionOne = questionsListView.getChildren().get(0);
        checkBox = questionOne.findObject(ById("hasAnswerCheckBox"));
        answerPreview = questionOne.findObject(ById("questionsListViewElementAnswerTextView"));

        //checkbox is now checked because we have recorded
        assertEquals(true, checkBox.isChecked());
        //Answer preview is empty because we have not typed anything
        assertEquals(null, answerPreview.getText());
    }
}