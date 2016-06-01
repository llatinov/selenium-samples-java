package com.automationrhapsody.junit.tests;

import com.automationrhapsody.junit.rules.PrintSequenceRule;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PrintSequenceTest {

    @ClassRule
    public static PrintSequenceRule classRule = new PrintSequenceRule();
    @Rule
    public PrintSequenceRule testRule = new PrintSequenceRule();

    @BeforeClass
    public static void beforeClass() {
        System.out.println("@BeforeClass");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("@AfterClass");
    }

    @Before
    public void beforeTest() {
        System.out.println("@Before");
    }

    @After
    public void afterTest() {
        System.out.println("@After");
    }

    @Test
    public void test1() {
        assertTrue(true);
    }

    @Test
    public void test2() {
        assertTrue(true);
    }
}
