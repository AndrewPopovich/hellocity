package ua.andrew.hellocity.print;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.andrew.hellocity.time.TimesOfDay;

import java.util.ResourceBundle;

public class OwnMessageWriter implements MessageWriter {

    private static final Logger LOGGER = LoggerFactory.getLogger(OwnMessageWriter.class);

    private ResourceBundle bundle;

    public OwnMessageWriter(String propertiesName) {
        bundle = ResourceBundle.getBundle(propertiesName, new UTF8Control());

        LOGGER.debug("Bundle: " + bundle.getBaseBundleName());
    }

    @Override
    public String print(String city, TimesOfDay timesOfDay) {
        String result = "";

        switch (timesOfDay) {
            case MORNING:
                result = printInConsole(city, timesOfDay);
                break;
            case DAY:
                result = printInConsole(city, timesOfDay);
                break;
            case EVENING:
                result = printInConsole(city, timesOfDay);
                break;
            case NIGHT:
                result = printInConsole(city, timesOfDay);
                break;
        }
        return result;
    }

    private String printInConsole(String city, TimesOfDay timesOfDay) {
        String result = bundle.getString(timesOfDay.getTimeOfDay()) + ", " + city + "!";

        System.out.println(result);

        return result;
    }
}