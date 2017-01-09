package com.automationrhapsody.junit;

import com.automationrhapsody.junit.utils.PointUtils;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(DataProviderRunner.class)
public class LocatorDataProviderTest {

    private static final Point MOCKED_POINT = new Point(11, 11);

    private LocatorService locatorServiceMock = mock(LocatorService.class);

    private Locator locatorUnderTest;

    @DataProvider
    public static Object[] dataProvider() {
        return new Object[][] {
            {-1, -1, new Point(1, 1)},
            {-1, 0, new Point(1, 0)},
            {-1, 1, new Point(1, 1)},

            {0, -1, new Point(0, 1)},
            {0, 0, MOCKED_POINT},
            {0, 1, MOCKED_POINT},

            {1, -1, new Point(1, 1)},
            {1, 0, MOCKED_POINT},
            {1, 1, MOCKED_POINT}
        };
    }

    @Before
    public void setUp() {
        when(locatorServiceMock.geoLocate(any(Point.class))).thenReturn(MOCKED_POINT);

        locatorUnderTest = new Locator(locatorServiceMock);
    }

    @Test
    @UseDataProvider("dataProvider")
    public void testLocateResults(int x, int y, Point expected) {
        assertTrue(PointUtils.arePointsEqual(expected, locatorUnderTest.locate(x, y)));
    }
}
