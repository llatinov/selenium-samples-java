package com.automationrhapsody.junit.utils;

import com.automationrhapsody.junit.Point;

public final class PointUtils {

    private PointUtils() {
        // Utilities class
    }

    public static boolean arePointsEqual(Point p1, Point p2) {
        return p1.getX() == p2.getX()
            && p1.getY() == p2.getY();
    }
}
