package com.automationrhapsody.cucumber.formatter;

import java.util.List;

import gherkin.formatter.Formatter;
import gherkin.formatter.NiceAppendable;
import gherkin.formatter.Reporter;
import gherkin.formatter.model.Background;
import gherkin.formatter.model.BasicStatement;
import gherkin.formatter.model.Examples;
import gherkin.formatter.model.Feature;
import gherkin.formatter.model.Match;
import gherkin.formatter.model.Result;
import gherkin.formatter.model.Scenario;
import gherkin.formatter.model.ScenarioOutline;
import gherkin.formatter.model.Step;
import gherkin.formatter.model.TagStatement;

public class CustomFormatter implements Reporter, Formatter {

    private NiceAppendable output;

    public CustomFormatter(Appendable appendable) {
        output = new NiceAppendable(appendable);
        output.println("CustomFormatter()");
        System.out.println("CustomFormatter(): " + output.toString());
    }

    @Override
    public void syntaxError(String s, String s1, List<String> list, String s2, Integer integer) {
        String out = String.format("syntaxError(): s=%s, s1=%s, list=%s, s2=%s, integer=%s", s, s1, list, s2, integer);
        System.out.println(out);
    }

    @Override
    public void uri(String s) {
        System.out.println("uri(): " + s);
    }

    @Override
    public void feature(Feature feature) {
        System.out.println("feature(): " + printTagStatement(feature));
    }

    @Override
    public void scenarioOutline(ScenarioOutline scenarioOutline) {
        System.out.println("scenarioOutline(): " + printTagStatement(scenarioOutline));
    }

    @Override
    public void examples(Examples examples) {
        String out = String.format("rows=%s", examples.getRows());
        System.out.println("examples(): " + printTagStatement(examples) + "; " + out);
    }

    @Override
    public void startOfScenarioLifeCycle(Scenario scenario) {
        System.out.println("startOfScenarioLifeCycle(): " + printTagStatement(scenario));
    }

    @Override
    public void background(Background background) {
        System.out.println("background(): " + printBasicStatement(background));
    }

    @Override
    public void scenario(Scenario scenario) {
        System.out.println("scenario(): " + printTagStatement(scenario));
    }

    @Override
    public void step(Step step) {
        String out = String.format("doc_string=%s, rows=%s", step.getDocString(), step.getRows());
        System.out.println("step(): " + printBasicStatement(step) + "; " + out);
    }

    @Override
    public void endOfScenarioLifeCycle(Scenario scenario) {
        System.out.println("endOfScenarioLifeCycle(): " + printTagStatement(scenario));
    }

    @Override
    public void done() {
        System.out.println("done()");
    }

    @Override
    public void close() {
        output.close();
        System.out.println("close()");
    }

    @Override
    public void eof() {
        System.out.println("eof()");
    }

    @Override
    public void before(Match match, Result result) {
        System.out.println("before(): " + printMatch(match) + "; " + printResult(result));
    }

    @Override
    public void result(Result result) {
        output.println(printResult(result));
        System.out.println("result(): " + printResult(result));
    }

    @Override
    public void after(Match match, Result result) {
        System.out.println("after(): " + printMatch(match) + "; " + printResult(result));
    }

    @Override
    public void match(Match match) {
        System.out.println("match(): " + printMatch(match));
    }

    @Override
    public void embedding(String s, byte[] bytes) {
        System.out.println("embedding(): s=");
    }

    @Override
    public void write(String s) {
        System.out.println("write(): " + s);
    }

    private String printBasicStatement(BasicStatement basic) {
        return String.format("keyword=%s, name=%s, line=%s", basic.getKeyword(), basic.getName(), basic.getLine());
    }

    private String printTagStatement(TagStatement tag) {
        return String.format("tags=%s, %s", tag.getTags(), printBasicStatement(tag));
    }

    private String printMatch(Match match) {
        return String.format("arguments=%s, location=%s", match.getArguments(), match.getLocation());
    }

    private String printResult(Result result) {
        return String.format("status=%s, duration=%s, error_message=%s",
            result.getStatus(), result.getDuration(), result.getErrorMessage());
    }
}
