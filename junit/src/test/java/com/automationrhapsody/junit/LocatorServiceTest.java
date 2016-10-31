package com.automationrhapsody.junit;

import com.automationrhapsody.junit.utils.PointUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Utils.class})
public class LocatorServiceTest {

    private LocatorService locatorServiceUnderTest;

    @Before
    public void setUp() {
        locatorServiceUnderTest = new LocatorService();
    }

    @Test
    public void testGeoLocate() {
        Point input = new Point(11, 11);

        assertTrue(PointUtils.arePointsEqual(input, locatorServiceUnderTest.geoLocate(input)));
    }

    @Test
    public void testGeneratePointWithinDistance() {
        int distance = 111;
        PowerMockito.mockStatic(Utils.class);
        when(Utils.randomDistance(anyInt())).thenReturn(distance);

        Point input = new Point(11, 11);
        Point expected = new Point(input.getX() + distance, input.getY() + distance);

        assertTrue(PointUtils.arePointsEqual(expected, locatorServiceUnderTest.generatePointWithinDistance(input, 1)));
    }
}
