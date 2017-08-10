package com.automationrhapsody.cucumber.parallel.tests.wikipedia;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class WikipediaSteps extends BaseSteps {

    @Before
    public void before() {
        startWebDriver();
    }

    @After
    public void after() {
        stopWebDriver();
    }

    @Given("^Enter search term '(.*?)'$")
    public void searchFor(String searchTerm) {
        WebElement searchField = driver.findElement(By.id("searchInput"));
        searchField.sendKeys(searchTerm);
    }

    @When("^Do search$")
    public void clickSearchButton() {
        WebElement searchButton = driver.findElement(By.id("searchButton"));
        searchButton.click();
        wait(2);
    }

    @Then("^Multiple results are shown for '(.*?)'$")
    public void assertMultipleResults(String searchResults) {
        WebElement firstSearchResult = driver.findElement(By.cssSelector("div#mw-content-text.mw-content-ltr p"));
        assertEquals(searchResults, firstSearchResult.getText());
    }

    @Then("^Single result is shown for '(.*?)'$")
    public void assertSingleResult(String searchResults) {
        WebElement articleName = driver.findElement(By.id("firstHeading"));
        assertEquals(articleName.getText(), searchResults);
    }

    @Then("^This is (.*?)good article$")
    public void assertGoodArticle(String isNot) {
        boolean isGood = isNot != null && "".equals(isNot);
        try {
            driver.findElement(By.cssSelector("div#mw-indicator-good-star.mw-indicator a img"));
            assertTrue(isGood);
        } catch (NoSuchElementException enfe) {
            assertTrue(!isGood);
        }
    }

    @Then("^Current date is shown$")
    public void checkCurrentDate() {
        WebElement element = driver.findElement(By.cssSelector("div#mp-otd p b a"));

        String fullFormat = "%s";
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMMM d");
        String expected = String.format(fullFormat, LocalDate.now().format(dateFormat));

        assertEquals(expected, element.getText());
    }
}
