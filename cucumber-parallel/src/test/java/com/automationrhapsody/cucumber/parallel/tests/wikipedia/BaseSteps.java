package com.automationrhapsody.cucumber.parallel.tests.wikipedia;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseSteps {

    protected WebDriver driver;

    protected void startWebDriver() {
        driver = new FirefoxDriver();
        driver.navigate().to("http://en.wikipedia.org");
    }

    protected void stopWebDriver() {
        driver.quit();
    }
}
