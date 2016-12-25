package com.abstractx1.androidqa;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject2;

/**
 * Created by tfisher on 25/12/2016.
 */

public class AlertDialogSimulator extends BaseSimulator {
    public AlertDialogSimulator() throws InterruptedException {
        this.rootObject = QA.getInstance().waitForObject(By.res("android:id/parentPanel"));
    }

    public String getTitle() { return getRootObject().findObject(By.res("android:id/alertTitle")).getText(); }

    public String getMessage() { return getRootObject().findObject(By.res("android:id/message")).getText(); }

    protected void clickButton1() { getButton("button1").click(); }

    protected void clickButton2() {
        getButton("button2").click();
    }

    private UiObject2 getButton(String resourceID) {
        return getRootObject().findObject(By.clazz("android.widget.Button").res("android:id/" + resourceID));
    }
}
