package com.automationrhapsody.junit;

public class Locator {

    private final LocatorService locatorService;

    public Locator(LocatorService locatorService) {
        this.locatorService = locatorService;
    }

    public Point locate(int x, int y) {
        if (x < 0 || y < 0) {
            return new Point(Math.abs(x), Math.abs(y));
        } else {
            return locatorService.geoLocate(new Point(x, y));
        }
    }
}
