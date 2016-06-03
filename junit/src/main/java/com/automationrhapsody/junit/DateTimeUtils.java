package com.automationrhapsody.junit;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeUtils {

    private static final ThreadLocal<SimpleDateFormat> DATE_FORMAT_SAFE = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        }
    };
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    public static String toDateString(Date date) {
        return toDateStringThreadLocal(date);
    }

    private static String toDateStringUnsafe(Date date) {
        return DATE_FORMAT.format(date);
    }

    private static synchronized String toDateStringSynchronized(Date date) {
        return DATE_FORMAT.format(date);
    }

    private static String toDateStringNewObject(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(date);
    }

    private static String toDateStringThreadLocal(Date date) {
        return DATE_FORMAT_SAFE.get().format(date);
    }
}
