package com.automationrhapsody.junit;

public class Locator {

    private LocatorService locatorService;

    public Locator(LocatorService locatorService) {
        this.locatorService = locatorService;
    }

    public Point locate(int x, int y) {
        if (x < 0 && y < 0) {
            return new Point(-x, -y);
        } else {
            return locatorService.locate(new Point(x, y));
        }
    }
}
