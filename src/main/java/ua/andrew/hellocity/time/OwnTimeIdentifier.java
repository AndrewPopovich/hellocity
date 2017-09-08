package ua.andrew.hellocity.time;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.NoSuchElementException;
import java.util.Set;

public class OwnTimeIdentifier implements TimeIdentifier {

    private static final Logger LOGGER = LoggerFactory.getLogger(OwnTimeIdentifier.class);

    private Set<String> allZones = ZoneId.getAvailableZoneIds();

    private ZoneId identifyZone(String city, String zone) {
        ZoneId result = null;

        if (zone.equals("")) {
            LOGGER.debug("Zone not fount!");

            result = findZoneByCity(city);
        } else {
            if (allZones.contains(zone)) {
                result = ZoneId.of(allZones.stream().filter(z -> z.equals(zone)).findFirst().get());

                LOGGER.debug("ZonedId is a: " + result.getId());
            } else {
                result = findZoneByCity(city);
            }
        }
        return result;
    }

    private ZoneId findZoneByCity(String city) {
        ZoneId result = ZoneId.of("UTC");
        String changeCity = city.replaceAll(" ", "_");

        try {
            result = ZoneId.of(allZones.stream().filter(z -> z.contains(changeCity)).findFirst().get());
        } catch (NoSuchElementException ex) {
            LOGGER.debug("Didn't found city in allZones! City name = " + city);
        }
        return result;
    }

    @Override
    public OffsetDateTime identifyTime(String city, String zone) {
        ZoneId zoneId = identifyZone(city, zone);

        return OffsetDateTime.now(zoneId);
    }
}