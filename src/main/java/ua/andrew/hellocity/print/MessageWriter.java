package ua.andrew.hellocity.print;

import ua.andrew.hellocity.time.TimesOfDay;

public interface MessageWriter {
    String print(String city, TimesOfDay timesOfDay);
}