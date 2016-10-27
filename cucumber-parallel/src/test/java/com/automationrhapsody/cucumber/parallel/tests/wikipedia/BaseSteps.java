package com.automationrhapsody.cucumber.parallel.tests.wikipedia;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseSteps {

    protected WebDriver driver;

    protected void startWebDriver() {
        Properties props = System.getProperties();
        props.setProperty("webdriver.gecko.driver", "drivers/geckodriver64.exe");
        driver = new FirefoxDriver();
        driver.navigate().to("http://en.wikipedia.org");
    }

    protected void stopWebDriver() {
        driver.quit();
    }

    protected void wait(int timeOutInSeconds) {
        try {
            Thread.sleep(timeOutInSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
