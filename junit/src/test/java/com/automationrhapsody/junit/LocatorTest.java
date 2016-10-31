package com.automationrhapsody.junit;

import com.automationrhapsody.junit.utils.PointUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LocatorTest {

    private static final Point TEST_POINT = new Point(11, 11);

    @Mock
    private LocatorService locatorServiceMock;

    private Locator locatorUnderTest;

    @Before
    public void setUp() {
        when(locatorServiceMock.geoLocate(any(Point.class))).thenReturn(TEST_POINT);

        locatorUnderTest = new Locator(locatorServiceMock);
    }

    @Test
    public void testLocateWithServiceArgument() {
        locatorUnderTest.locate(1, 1);

        verify(locatorServiceMock, times(1)).geoLocate(argThat(new PointMatcher(new Point(1, 1))));

        verifyNoMoreInteractions(locatorServiceMock);
    }

    @Test
    public void testLocateWithServiceResult() {
        assertEquals(TEST_POINT, locatorUnderTest.locate(1, 1));
    }

    @Test
    public void testLocateLocalResult() {
        Point expected = new Point(1, 1);
        assertTrue(PointUtils.arePointsEqual(expected, locatorUnderTest.locate(-1, -1)));
    }

    private class PointMatcher extends ArgumentMatcher<Point> {

        private final Point expected;

        public PointMatcher(Point expected) {
            this.expected = expected;
        }

        @Override
        public boolean matches(Object obj) {
            if (!(obj instanceof Point)) {
                return false;
            }
            Point actual = (Point) obj;

            return PointUtils.arePointsEqual(actual, expected);
        }
    }
}
