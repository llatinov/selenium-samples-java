package com.automationrhapsody.junit.rules;

import org.junit.AssumptionViolatedException;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class PrintSequenceRule extends TestWatcher {

    protected void succeeded(Description description) {
        System.out.println("Succeeded: " + description.getDisplayName());
    }

    protected void failed(Throwable e, Description description) {
        System.out.println("Failed: " + description.getDisplayName());
    }

    protected void skipped(AssumptionViolatedException e, Description description) {
        System.out.println("Skipped: " + description.getDisplayName());
    }

    protected void starting(Description description) {
        System.out.println("Starting: " + description.getDisplayName());
    }

    protected void finished(Description description) {
        System.out.println("Finished: " + description.getDisplayName());
    }
}
