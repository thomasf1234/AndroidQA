package com.abstractx1.androidqa;

import android.support.test.uiautomator.By;
import android.support.test.uiautomator.BySelector;

/**
 * Created by tfisher on 25/12/2016.
 */

public abstract class BaseActivitySimulator extends BaseSimulator {
    public BaseActivitySimulator() throws InterruptedException {
        super();
        load();
    }

    protected BySelector ById(String id) {
        return By.res(getPackageName() + ":id/" + id);
    }

    protected abstract String getPackageName();

    protected abstract String getActivityName();

    protected void load() throws InterruptedException {
        this.rootObject = QA.getInstance().waitForObject(ById(getActivityName()));
    }

    protected void reload() throws InterruptedException {
        load();
    }
}
