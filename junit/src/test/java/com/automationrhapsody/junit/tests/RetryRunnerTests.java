package com.automationrhapsody.junit.tests;

import com.automationrhapsody.junit.runners.RetryRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(RetryRunner.class)
public class RetryRunnerTests {

    private int retryAttempts = 0;

    @Test
    public void testRetrySuccessFirstTime() {
        assertTestRetryAttempts(0);
    }

    @Test
    public void testRetrySuccessSecondTime() {
        assertTestRetryAttempts(1);
    }

    @Test
    public void testRetrySuccessThirdTime() {
        assertTestRetryAttempts(2);
    }

    @Test
    public void testRetryFailureFourthTime() {
        assertTestRetryAttempts(3);
    }

    private void assertTestRetryAttempts(int assertTrueAttempt) {
        if (retryAttempts++ < assertTrueAttempt) {
            assertTrue(false);
        } else {
            assertTrue(true);
        }
    }
}
