package com.automationrhapsody.junit.rules;

import org.junit.AssumptionViolatedException;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class PrintSequenceRule extends TestWatcher {

    public PrintSequenceRule() {
        System.out.println("RuleClass constructor");
        sleep();
    }

    protected void succeeded(Description description) {
        printDescription("succeeded()", description);
    }

    protected void failed(Throwable e, Description description) {
        printDescription("failed()", description);
    }

    protected void skipped(AssumptionViolatedException e, Description description) {
        printDescription("skipped()", description);
    }

    protected void starting(Description description) {
        printDescription("starting()", description);
    }

    protected void finished(Description description) {
        printDescription("finished()", description);
    }

    private void printDescription(String methodName, Description description) {
        String name = description.getMethodName();
        if (name == null) {
            System.out.println(methodName + " of TestClass");
        } else {
            System.out.println(methodName + " of TestMethod " + name + "()");
        }
        sleep();
    }

    private void sleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
