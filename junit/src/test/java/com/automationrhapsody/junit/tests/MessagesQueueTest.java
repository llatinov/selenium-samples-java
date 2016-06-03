package com.automationrhapsody.junit.tests;

import com.automationrhapsody.junit.rules.MessagesQueueRule;
import com.automationrhapsody.junit.utils.RequestUtils;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MessagesQueueTest {

    @Rule
    public MessagesQueueRule rule = new MessagesQueueRule();

    @Test
    public void test1() {
        String result1 = RequestUtils.makeSomeRequest("test1request1");
        String result2 = RequestUtils.makeAnotherRequest(result1);
        String actualResult = RequestUtils.makeAnotherRequest(result2);
        assertEquals("makeAnotherRequestResponse", actualResult);
    }

    @Test
    public void test2() {
        RequestUtils.makeSomeRequest("test2request1");
        RequestUtils.makeAnotherRequest("test2request2");
        RequestUtils.makeSomeRequest("test2request3");
    }

    @Test
    public void test3() {
        RequestUtils.makeSomeRequest("test3request1");
        RequestUtils.makeAnotherRequest("test3request2");
        RequestUtils.makeAnotherRequest("test3request3");
    }

    @Test
    public void test4() {
        RequestUtils.makeSomeRequest("test4request1");
        RequestUtils.makeAnotherRequest("test4request2");
        RequestUtils.makeSomeRequest("test4request3");
    }
}
