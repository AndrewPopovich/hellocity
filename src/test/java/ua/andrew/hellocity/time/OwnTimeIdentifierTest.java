package ua.andrew.hellocity.time;

import org.junit.Before;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

public class OwnTimeIdentifierTest {

    private TimeIdentifier timeIdentifier;

    private DateTimeFormatter format;

    @Before
    public void init() {
        timeIdentifier = new OwnTimeIdentifier();

        format = DateTimeFormatter.ofPattern("HH:mm");
    }

    @Test
    public void testIdentifyTime() {
        assertEquals("Returned time is wrong!",
                OffsetDateTime.now(ZoneId.of("Europe/Kiev")).format(format),
                timeIdentifier.identifyTime("Kiev", "Europe/Kiev").format(format));
    }

    @Test
    public void testIdentifyTimeWrongCity() {
        assertEquals("Time for wrong city not found! Must be GWT!",
                OffsetDateTime.now(ZoneId.of("UTC")).format(format),
                timeIdentifier.identifyTime("Dnipro", "").format(format));
    }

    @Test
    public void testIdentifyTimeOnlyCity() {
        assertEquals("Time for city not found!",
                OffsetDateTime.now(ZoneId.of("Europe/Kiev")).format(format),
                timeIdentifier.identifyTime("Kiev", "").format(format));
    }

    @Test(expected = NullPointerException.class)
    public void testIdentifyTimeWithNullZone() {
        timeIdentifier.identifyTime("Kiev", null);
    }

    @Test
    public void testIdentifyTimeWrongZone() {
        assertEquals("Time for wrong zone not found! Must identify zone by city!",
                OffsetDateTime.now(ZoneId.of("Europe/Kiev")).format(format),
                timeIdentifier.identifyTime("Kiev", "Unknown/Zone").format(format));
    }

    @Test
    public void testIdentifyTimeWrongCityZndZone() {
        assertEquals("Time for wrong city and zone Must return GWT!",
                OffsetDateTime.now(ZoneId.of("UTC")).format(format),
                timeIdentifier.identifyTime("Unknown City", "Unknown/Zone").format(format));
    }
}