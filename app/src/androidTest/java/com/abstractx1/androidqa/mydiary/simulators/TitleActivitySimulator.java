package com.abstractx1.androidqa.mydiary.simulators;

import android.support.test.uiautomator.UiObject2;

import com.abstractx1.androidqa.BaseActivitySimulator;

/**
 * Created by tfisher on 25/12/2016.
 */

//page object
public class TitleActivitySimulator extends BaseActivitySimulator {
    public TitleActivitySimulator() throws InterruptedException {
        super();
    }

    public InputActivitySimulator clickQuestion(int number) throws InterruptedException {
        getQuestion(number).click();
        return new InputActivitySimulator();
    }

    public void clickCameraMenuOption() {

    }

    public void clickSubmitAnswersMenuOption() {
        getActionBar().findObject(ById("sendButtonMenuOption")).click();
    }

    public static void clickSettingsMenuOption() {

    }

    public void clickHelpMenuOption() {
        getActionBar().findObject(ById("contactResearcherMenuOption")).click();
    }

    public UiObject2 getAnswerPreview(int number) {
        return getQuestion(number).findObject(ById("questionsListViewElementAnswerTextView"));
    }

    public UiObject2 getCheckbox(int number) {
        return getQuestion(number).findObject(ById("hasAnswerCheckBox"));
    }

    public UiObject2 getActionBar() {
        return getRootObject().findObject(ById("action_bar"));
    }

    public UiObject2 getQuestion(int number) {
        return getQuestionListView().getChildren().get(number-1);
    }

    public UiObject2 getQuestionListView() {
        return getRootObject().findObject(ById("questionsListView"));
    }

    @Override
    protected String getPackageName() {
        return "com.abstractx1.mydiary";
    }

    @Override
    protected String getActivityName() {
        return "activity_title";
    }
}
