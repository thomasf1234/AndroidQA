package com.abstractx1.androidqa;

import org.junit.Before;

import static org.junit.Assert.fail;

public abstract class BaseTest {
    protected String runsAgainstPackage;

    @Before
    public void before() {
        setRunsAgainstPackage();
        QA.getInstance();
    }

    protected void setRunsAgainstPackage() {
        RunsAgainstPackage runsAgainstPackageAnnotation = getClass().getAnnotation(RunsAgainstPackage.class);

        if (runsAgainstPackageAnnotation != null) {
            this.runsAgainstPackage = runsAgainstPackageAnnotation.value();
        } else {
            fail("RunsAgainstPackage not set for " + getClass().getName());
        }
    }

    protected String getRunsAgainstPackage() {
        return runsAgainstPackage;
    }
}