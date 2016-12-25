package com.abstractx1.androidqa;

import android.os.Bundle;
import android.support.test.InstrumentationRegistry;

import org.junit.Before;

import static org.junit.Assert.fail;

public abstract class BaseTest {
    @Before
    public void before() {
        QA.getInstance();
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