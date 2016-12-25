package com.abstractx1.androidqa;

import android.support.test.uiautomator.UiObject2;

/**
 * Created by tfisher on 25/12/2016.
 */

public abstract class BaseSimulator {
    protected UiObject2 rootObject;

    protected UiObject2 getRootObject() {
        return rootObject;
    }
}
