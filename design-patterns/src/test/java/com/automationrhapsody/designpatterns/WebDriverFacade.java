package com.automationrhapsody.designpatterns;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverFacade {
    private WebDriver webDriver = null;
    private long waitForElement = 5;

    public WebDriverFacade(Browsers browser) {
        WebDriverFactory factory = new WebDriverFactory();
        webDriver = factory.createInstance(browser);
    }

    public void start(String url) {
        webDriver.get(url);
    }

    public void stop() {
        webDriver.quit();
    }

    public Object ExecuteJavaScript(String script) {
        return ((JavascriptExecutor) webDriver).executeScript("return " + script);
    }

    public WebElement findElement(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, waitForElement);
            return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception ex) {
            return NullWebElement.getNull();
        }
    }

    public List<WebElement> findElements(By by) {
        WebDriverWait wait = new WebDriverWait(webDriver, waitForElement);
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }
}