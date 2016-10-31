package com.automationrhapsody.junit;

public class LocatorService {

    public Point geoLocate(Point point) {
        return new Point(point.getX(), point.getY());
    }

    public Point generatePointWithinDistance(Point point, int distance) {
        return new Point(point.getX() + Utils.randomDistance(distance), point.getY() + Utils.randomDistance(distance));
    }
}
