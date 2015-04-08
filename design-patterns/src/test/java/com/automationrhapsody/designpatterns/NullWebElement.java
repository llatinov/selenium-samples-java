package com.automationrhapsody.designpatterns;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class NullWebElement implements WebElement {

    private NullWebElement() {
    }

    private static NullWebElement instance;

    public static NullWebElement getNull() {
        if (instance == null) {
            instance = new NullWebElement();
        }
        return instance;
    }

    public static boolean isNull(WebElement element) {
        return getNull().equals(element);
    }

    // <editor-fold desc="Interface implementation">
    public void click() {
    }

    public void submit() {
    }

    public void sendKeys(CharSequence... charSequences) {
    }

    public void clear() {
    }

    public String getTagName() {
        return "";
    }

    public String getAttribute(String s) {
        return "";
    }

    public boolean isSelected() {
        return false;
    }

    public boolean isEnabled() {
        return false;
    }

    public String getText() {
        return "";
    }

    public List<WebElement> findElements(By by) {
        return new ArrayList<WebElement>();
    }

    public WebElement findElement(By by) {
        return NullWebElement.getNull();
    }

    public boolean isDisplayed() {
        return false;
    }

    public Point getLocation() {
        return new Point(0, 0);
    }

    public Dimension getSize() {
        return new Dimension(0, 0);
    }

    public String getCssValue(String s) {
        return "";
    }
    // </editor-fold>
}
