package com.abstractx1.androidqa.mydiary;

import android.support.test.runner.AndroidJUnit4;

import com.abstractx1.androidqa.BaseTest;
import com.abstractx1.androidqa.QA;
import com.abstractx1.androidqa.mydiary.simulators.InputActivitySimulator;
import com.abstractx1.androidqa.mydiary.simulators.TitleActivitySimulator;

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
        QA.getInstance().openApp(getPackageName());
        TitleActivitySimulator titleActivity = new TitleActivitySimulator();

        assertEquals(false, titleActivity.getCheckbox(1).isChecked());
        assertEquals(null, titleActivity.getAnswerPreview(1).getText());

        InputActivitySimulator inputActivityPage = titleActivity.clickQuestion(1);
        inputActivityPage.inputAnswer("I have typed in this answer!");
        titleActivity = inputActivityPage.navigateUp();

        assertEquals(true, titleActivity.getCheckbox(1).isChecked());
        assertEquals("I have typed in this answer!", titleActivity.getAnswerPreview(1).getText());
    }

    @org.junit.Test
    public void testAnswerRecordingQuestion() throws InterruptedException {
        QA.getInstance().openApp(getPackageName());
        TitleActivitySimulator titleActivity = new TitleActivitySimulator();

        assertEquals(false, titleActivity.getCheckbox(1).isChecked());
        assertEquals(null, titleActivity.getAnswerPreview(1).getText());

        InputActivitySimulator inputActivityPage = titleActivity.clickQuestion(1);
        assertEquals(true, inputActivityPage.getDisplayTime().equals("0:00"));

        inputActivityPage.makeRecording();
        titleActivity = inputActivityPage.navigateUp();

        //checkbox should be checked because we've answered the question with a recording
        assertEquals(true, titleActivity.getCheckbox(1).isChecked());
        assertEquals(null, titleActivity.getAnswerPreview(1).getText());
    }

    @org.junit.Test
    public void testClearRecordingQuestion() throws InterruptedException {
        QA.getInstance().openApp(getPackageName());
        TitleActivitySimulator titleActivity = new TitleActivitySimulator();

        assertEquals(false, titleActivity.getCheckbox(1).isChecked());
        assertEquals(null, titleActivity.getAnswerPreview(1).getText());

        InputActivitySimulator inputActivityPage = titleActivity.clickQuestion(1);
        assertEquals(true, inputActivityPage.getDisplayTime().equals("0:00"));

        inputActivityPage.makeRecording();
        inputActivityPage.clearRecording();
        assertEquals(true, inputActivityPage.getDisplayTime().equals("0:00"));

        titleActivity = inputActivityPage.navigateUp();

        //checkbox should be checked because we've answered the question with a recording
        assertEquals(false, titleActivity.getCheckbox(1).isChecked());
        assertEquals(null, titleActivity.getAnswerPreview(1).getText());
    }

    @Override
    protected String getPackageName() {
        return "com.abstractx1.mydiary";
    }
}