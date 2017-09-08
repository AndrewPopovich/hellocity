package ua.andrew.hellocity.time;

import java.time.OffsetDateTime;

public interface TimeIdentifier {
    OffsetDateTime identifyTime(String city, String zone);
}