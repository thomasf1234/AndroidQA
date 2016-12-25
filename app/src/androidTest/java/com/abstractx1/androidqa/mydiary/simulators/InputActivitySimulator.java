package com.abstractx1.androidqa.mydiary.simulators;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject2;

import com.abstractx1.androidqa.BaseActivitySimulator;
import com.abstractx1.androidqa.PermissionDialogSimulator;
import com.abstractx1.androidqa.QA;

import static org.junit.Assert.assertEquals;

/**
 * Created by tfisher on 25/12/2016.
 */

public class InputActivitySimulator extends BaseActivitySimulator {
    public InputActivitySimulator() throws InterruptedException {
        super();
    }

    public void clickRecordButton() {
        getRootObject().findObject(ById("recordButton")).click();
    }
    public ConfirmationDialogSimulator clickClearRecordingButton() throws InterruptedException {
        getRootObject().findObject(ById("clearRecordingButton")).click();
        return new ConfirmationDialogSimulator();
    }

    public ConfirmationDialogSimulator clickStopButton() throws InterruptedException {
        clickRecordButton();
        return new ConfirmationDialogSimulator();
    }

    public String getDisplayTime() {
        return getRootObject().findObject(ById("recordingDurationTextView")).getText();
    }

    public void inputAnswer(String string) {
        UiObject2 answerEditText = getRootObject().findObject(ById("answerEditText"));
        answerEditText.click();
        answerEditText.setText(string);
    }

    public TitleActivitySimulator navigateUp() throws InterruptedException {
        QA.getInstance().getDevice().findObject(By.descContains("Navigate up")).click();
        return new TitleActivitySimulator();
    }

    public void makeRecording() throws InterruptedException {
        clickRecordButton();
        if (QA.getInstance().isDeviceAndroid6AndLater()) {
            PermissionDialogSimulator permissionDialog = new PermissionDialogSimulator();
            permissionDialog.clickAllow();
            reload();
        }
        QA.getInstance().wait(2000);

        assertEquals(false, getDisplayTime().equals("0:00"));

        ConfirmationDialogSimulator confirmationDialogSimulator = clickStopButton();
        confirmationDialogSimulator.clickYes();
        reload();

        assertEquals(false, getDisplayTime().equals("0:00"));
    }

    public void clearRecording() throws InterruptedException {
        ConfirmationDialogSimulator confirmationDialogSimulator = clickClearRecordingButton();
        confirmationDialogSimulator.clickYes();
        reload();
    }

    @Override
    protected String getPackageName() {
        return "com.abstractx1.mydiary";
    }

    @Override
    protected String getActivityName() {
        return "activity_input";
    }
}
