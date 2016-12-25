package com.abstractx1.androidqa.mydiary.simulators;

import com.abstractx1.androidqa.AlertDialogSimulator;

/**
 * Created by tfisher on 25/12/2016.
 */

public class ConfirmationDialogSimulator extends AlertDialogSimulator {
    public ConfirmationDialogSimulator() throws InterruptedException {
        super();
    }

    public void clickYes() {
        clickButton1();
    }

    public void clickNo() { clickButton2(); }
}
