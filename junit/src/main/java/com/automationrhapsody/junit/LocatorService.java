package com.automationrhapsody.junit;

public class LocatorService {

    public Point locate(Point point) {
        return new Point(point.getX(), point.getY());
    }
}
