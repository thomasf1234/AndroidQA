package com.abstractx1.androidqa;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.util.Log;

import java.io.File;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by tfisher on 25/12/2016.
 */
public class QA {
    private static QA ourInstance;
    private static final int DEFAULT_WAIT=5000;
    private UiDevice device;

    public static QA getInstance() {
        if(ourInstance == null) {
            ourInstance = new QA();
        }
        return ourInstance;
    }

    private QA() {
        this.device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        assertThat(device, notNullValue());
        device.pressHome();
    }

    public void openApp(String packageName) throws InterruptedException {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        waitForObject(By.pkg(packageName), DEFAULT_WAIT*2);
    }

    public UiObject2 waitForObject(BySelector selector) throws InterruptedException {
        return waitForObject(selector, DEFAULT_WAIT);
    }

    public UiObject2 waitForObject(BySelector selector, int timeout) throws InterruptedException {
        UiObject2 object = null;
        int delay = 200;
        long time = System.currentTimeMillis();
        while (object == null) {
            object = device.findObject(selector);
            Thread.sleep(delay);
            if (System.currentTimeMillis() - timeout > time) {
                fail("Timeout waiting for object: " + selector.toString());
            }
        }
        return object;
    }


    protected void takeScreenshot(String name) {
        Log.d("TEST", "takeScreenshot");
        String dir = String.format("%s/%s", Environment.getExternalStorageDirectory().getPath(), "test-screenshots");
        File theDir = new File(dir);
        if (!theDir.exists()) {
            theDir.mkdir();
        }
        device.takeScreenshot(new File(String.format("%s/%s", dir, name)));
    }

    public UiDevice getDevice() {
        return device;
    }

    public int getDeviceAPILevel() {
        return Integer.valueOf(android.os.Build.VERSION.SDK);
    }

    public boolean isDeviceAndroid6AndLater() {
        return getDeviceAPILevel() >= 23;
    }

    public void wait(int milliseconds) throws InterruptedException {
        Thread.sleep(milliseconds);
    }
}
