package com.automationrhapsody.designpatterns;

import java.io.File;

import org.openqa.selenium.WebDriver;

class WebDriverFactory {

    private static final String WEB_DRIVER_FOLDER = "webdrivers";

    public static WebDriver createWebDriver() {
        Browser browser = Browser.fromString(System.getProperty("browser"));
        String arch = System.getProperty("os.arch").contains("64") ? "64" : "32";
        String os = System.getProperty("os.name").toLowerCase().contains("win") ? "win.exe" : "linux";
        String driverFileName = browser.getName() + "driver-" + arch + "-" + os;
        String driverFilePath = driversFolder(new File("").getAbsolutePath());
        System.setProperty("webdriver." + browser.getName() + ".driver", driverFilePath + driverFileName);
        return browser.getDriver();
    }

    private static String driversFolder(String path) {
        File file = new File(path);
        for (String item : file.list()) {
            if (WEB_DRIVER_FOLDER.equals(item)) {
                return file.getAbsolutePath() + "/" + WEB_DRIVER_FOLDER + "/";
            }
        }
        return driversFolder(file.getParent());
    }
}
