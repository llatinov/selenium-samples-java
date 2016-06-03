package com.automationrhapsody.junit.tests;

import com.automationrhapsody.junit.rules.PrintSequenceRule;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

public class ExecutionSequenceRulesTest {

    @ClassRule
    public static PrintSequenceRule classRule = new PrintSequenceRule();
    @Rule
    public PrintSequenceRule testRule = new PrintSequenceRule();

    public ExecutionSequenceRulesTest() {
        System.out.println("TestClass constructor");
    }

    @BeforeClass
    public static void oneTimeSetUp() {
        System.out.println("@BeforeClass");
    }

    @Before
    public void setUp() {
        System.out.println("@Before");
    }

    @Test
    public void test3() {
        System.out.println("test3 body");
    }

    @Test
    public void test1() {
        System.out.println("test1 body");
    }

    @Test
    public void test2() {
        System.out.println("test2 body");
    }

    @After
    public void tearDown() {
        System.out.println("@After");
    }

    @AfterClass
    public static void oneTimeTearDown() {
        System.out.println("@AfterClass");
    }
}
