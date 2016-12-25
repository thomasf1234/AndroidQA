package com.abstractx1.androidqa;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject2;

/**
 * Created by tfisher on 25/12/2016.
 */

public class PermissionDialogSimulator extends BaseSimulator {
    public PermissionDialogSimulator() throws InterruptedException {
        this.rootObject = QA.getInstance().waitForObject(By.res("com.android.packageinstaller:id/dialog_container"));
    }

    public void clickAllow() {
        getButton("Allow").click();
    }

    public void clickDeny() {
        getButton("Deny").click();
    }

    private UiObject2 getButton(String text) {
        return getRootObject().findObject(By.clazz("android.widget.Button").text(text));
    }
}
