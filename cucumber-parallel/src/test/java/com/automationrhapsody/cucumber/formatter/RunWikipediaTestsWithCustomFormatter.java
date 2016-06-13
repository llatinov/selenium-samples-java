package com.automationrhapsody.cucumber.formatter;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {"com.automationrhapsody.cucumber.formatter.CustomFormatter:custom-formatter-output.txt"},
    features = {"classpath:com/automationrhapsody/cucumber/parallel/tests/wikipedia"},
    glue = {"com.automationrhapsody.cucumber.parallel.tests"},
    tags = {"~@ignored"}
)
public class RunWikipediaTestsWithCustomFormatter {
}
