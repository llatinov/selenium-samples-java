package com.automationrhapsody.junit.rules;

import com.automationrhapsody.junit.utils.RequestUtils;

import org.junit.AssumptionViolatedException;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class MessagesQueueRule extends TestWatcher {

    protected void succeeded(Description description) {
        System.out.println(Thread.currentThread().getId());
        RequestUtils.printMessages();
    }

    protected void failed(Throwable e, Description description) {
        RequestUtils.printMessages();
    }

    protected void skipped(AssumptionViolatedException e, Description description) {
        RequestUtils.printMessages();
    }
}
