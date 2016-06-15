package com.automationrhapsody.junit;

public class LocatorService {

    public Point geoLocate(Point point) {
        return new Point(point.getX(), point.getY());
    }
}
