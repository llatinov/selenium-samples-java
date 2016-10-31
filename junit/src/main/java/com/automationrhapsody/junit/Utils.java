package com.automationrhapsody.junit;

import java.util.Random;

public final class Utils {

    private static final Random RAND = new Random();

    private Utils() {
        // Utilities class
    }
    
    public static int randomDistance(int distance) {
        return RAND.nextInt(distance + distance) - distance;
    }
}
