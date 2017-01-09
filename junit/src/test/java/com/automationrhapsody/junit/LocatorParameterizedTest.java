package com.automationrhapsody.junit;

import com.automationrhapsody.junit.utils.PointUtils;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class LocatorParameterizedTest {

    private static final Point MOCKED_POINT = new Point(11, 11);

    private LocatorService locatorServiceMock = mock(LocatorService.class);

    private Locator locatorUnderTest;

    @Parameterized.Parameters(name = "{index}: Test with X={0}, Y={1}, result is: {2}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
            {-1, -1, new Point(1, 1)},
            {-1, 0, new Point(1, 0)},
            {-1, 1, new Point(1, 1)},

            {0, -1, new Point(0, 1)},
            {0, 0, MOCKED_POINT},
            {0, 1, MOCKED_POINT},

            {1, -1, new Point(1, 1)},
            {1, 0, MOCKED_POINT},
            {1, 1, MOCKED_POINT}
        });
    }

    private final int x;
    private final int y;
    private final Point expected;

    public LocatorParameterizedTest(int x, int y, Point expected) {
        this.x = x;
        this.y = y;
        this.expected = expected;
    }

    @Before
    public void setUp() {
        when(locatorServiceMock.geoLocate(any(Point.class))).thenReturn(MOCKED_POINT);

        locatorUnderTest = new Locator(locatorServiceMock);
    }

    @Test
    public void testLocateResults() {
        assertTrue(PointUtils.arePointsEqual(expected, locatorUnderTest.locate(x, y)));
    }
}
