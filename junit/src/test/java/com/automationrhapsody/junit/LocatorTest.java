package com.automationrhapsody.junit;

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
    private Locator locatorUnderTest;

    @Mock
    private LocatorService locatorServiceMock;

    @Before
    public void setUp() {
        when(locatorServiceMock.locate(any(Point.class))).thenReturn(TEST_POINT);

        locatorUnderTest = new Locator(locatorServiceMock);
    }

    @Test
    public void testLocateWithServiceArgument() {
        locatorUnderTest.locate(1, 1);

        verify(locatorServiceMock, times(1)).locate(argThat(new PointMatcher(new Point(1, 1))));

        verifyNoMoreInteractions(locatorServiceMock);
    }

    @Test
    public void testLocateWithServiceResult() {
        assertEquals(TEST_POINT, locatorUnderTest.locate(1, 1));
    }

    @Test
    public void testLocateLocalResult() {
        Point expected = new Point(1, 1);
        assertTrue(arePointsEqual(expected, locatorUnderTest.locate(-1, -1)));
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

            return arePointsEqual(actual, expected);
        }
    }

    private boolean arePointsEqual(Point p1, Point p2) {
        return p1.getX() == p2.getX()
            && p1.getY() == p2.getY();
    }
}
