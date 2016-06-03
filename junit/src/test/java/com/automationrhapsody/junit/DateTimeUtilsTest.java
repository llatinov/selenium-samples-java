package com.automationrhapsody.junit;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DateTimeUtilsTest {

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final Random RAND = new Random();

    @Test
    public void test1() {
        runTest();
    }

    @Test
    public void test2() {
        runTest();
    }

    @Test
    public void test3() {
        runTest();
    }

    @Test
    public void test4() {
        runTest();
    }

    @Test
    public void test5() {
        runTest();
    }

    @Test
    public void test6() {
        runTest();
    }

    @Test
    public void test7() {
        runTest();
    }

    @Test
    public void test8() {
        runTest();
    }

    @Test
    public void test9() {
        runTest();
    }

    @Test
    public void test10() {
        runTest();
    }

    private void runTest() {
        for (int i = 0; i < 1000; i++) {
            LocalDate localDate = LocalDate.of(2000 + RAND.nextInt(40), 1 + RAND.nextInt(11), 1 + RAND.nextInt(27));
            Date date = Date.from(localDate.atStartOfDay().toInstant(ZoneOffset.UTC));
            assertEquals(FORMAT.format(localDate), DateTimeUtils.toDateString(date));
        }
    }
}
