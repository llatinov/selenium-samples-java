package com.automationrhapsody.designpatterns;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class WebDriverFactory {
    public WebDriver createInstance(Browsers browser) {
        if (Browsers.CHROME == browser) {
            Properties props = System.getProperties();
            props.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
            return new ChromeDriver();
        } else if (Browsers.IE == browser) {
            Properties props = System.getProperties();
            props.setProperty("webdriver.ie.driver", "drivers/IEDriverServer.exe");
            return new InternetExplorerDriver();
        } else {
            Properties props = System.getProperties();
            props.setProperty("webdriver.gecko.driver", "drivers/geckodriver64.exe");
            return new FirefoxDriver();
        }
    }
}
