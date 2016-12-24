package com.abstractx1.androidqa;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.util.Log;

import org.hamcrest.CoreMatchers;
import org.junit.Before;

import java.io.File;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public abstract class BaseTest {
    private UiDevice device;

    @Before
    public void before() {
        // Initialize UiDevice instance
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        assertThat(device, notNullValue());

        // Start from the home screen
        device.pressHome();
    }

    protected void openApp() throws InterruptedException {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        waitForObject(By.pkg(getPackageName()), 10000);
    }

    protected UiObject2 waitForObject(BySelector selector) throws InterruptedException {
        return waitForObject(selector, 5000);
    }

    protected UiObject2 waitForObject(BySelector selector, int timeout) throws InterruptedException {
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

    protected UiDevice getDevice() {
        return device;
    }

    protected BySelector ById(String id) {
        return By.res(getPackageName() + ":id/" + id);
    }

    protected String getPackageName() {
        Bundle extras = InstrumentationRegistry.getArguments();

        if ( extras != null && extras.containsKey("packageToTest")) {
            return extras.getString("packageToTest");
        } else {
            fail("No packageToTest found");
        }

        return null;
    }

}
