package test.java;

import org.junit.BeforeClass;
import org.junit.Test;

import main.java.CountryLocator;

import static org.junit.Assert.*;

public class CountryLocatorTest {
    private static CountryLocator locator;

    @BeforeClass
    public static void setUp() {
        locator = new CountryLocator();
    }

    @Test
    public void testUSCoordinates() {
        String countryCode = locator.getCountryCode(37.7749, -122.4194);
        assertEquals("USA", countryCode);
    }

    @Test
    public void testCACoordinates() {
        String countryCode = locator.getCountryCode(45.4215, -75.6972);
        assertEquals("CAN", countryCode);
    }

    @Test
    public void testINCoordinates() {
        String countryCode = locator.getCountryCode(35.8617, 104.1954);
        assertEquals("CHN", countryCode);
    }

    @Test
    public void testAUCoordinates() {
        String countryCode = locator.getCountryCode(-33.8688, 151.2093);
        assertEquals("AUS", countryCode);
    }

    @Test
    public void testBRCoordinates() {
        String countryCode = locator.getCountryCode(-23.5505, -46.6333);
        assertEquals("BRA", countryCode);
    }

    @Test
    public void testUnknownCoordinates() {
        String countryCode = locator.getCountryCode(0.0, 0.0);
        assertNull(countryCode);
    }
}
