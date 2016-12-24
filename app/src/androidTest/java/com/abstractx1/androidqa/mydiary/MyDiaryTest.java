package com.abstractx1.androidqa.mydiary;

import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject2;

import com.abstractx1.androidqa.BaseTest;

import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

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
        getDevice().pressBack();

        getDevice().findObject(By.desc("Navigate up")).click();
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
}