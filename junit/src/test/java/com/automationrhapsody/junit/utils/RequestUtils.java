package com.automationrhapsody.junit.utils;

import java.util.LinkedList;
import java.util.Queue;

public class RequestUtils {

    private static final ThreadLocal<Queue<String>> MESSAGES = new ThreadLocal<Queue<String>>() {
        @Override
        protected Queue<String> initialValue() {
            return new LinkedList<>();
        }
    };

    public static String makeSomeRequest(String request) {
        getMessages().add(request);
        String response = "makeSomeRequestResponse";
        getMessages().add(response);
        return response;
    }

    public static String makeAnotherRequest(String request) {
        getMessages().add(request);
        String response = "makeAnotherRequestResponse";
        getMessages().add(response);
        return response;
    }

    public static void printMessages() {
        for (String message : getMessages()) {
            System.out.println(message);
        }
        clearMessages();
    }

    public static void clearMessages() {
        getMessages().clear();
    }

    private static Queue<String> getMessages() {
        return MESSAGES.get();
    }
}
