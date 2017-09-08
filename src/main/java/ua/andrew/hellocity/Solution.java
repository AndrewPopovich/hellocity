package ua.andrew.hellocity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.andrew.hellocity.print.MessageWriter;
import ua.andrew.hellocity.print.OwnMessageWriter;
import ua.andrew.hellocity.time.OwnTimeIdentifier;
import ua.andrew.hellocity.time.TimeIdentifier;
import ua.andrew.hellocity.time.TimesOfDay;

import java.time.OffsetDateTime;

public class Solution {

    private static final Logger LOGGER = LoggerFactory.getLogger(Solution.class);

    private static final int COUNT_SECOND_SIX_OCLOCK = 21600;

    private static final int COUNT_SECOND_NINE_OCLOCK = 32400;

    private static final int COUNT_SECOND_NINETEEN_OCLOCK = 68400;

    private static final int COUNT_SECOND_TWENTY_THREE_OCLOCK = 82800;

    private static final String CITY_NOT_FOUND_MESSAGE = "Please, start program with right parameters!";

    private static final String PROPERTIES_NAME = "HelloCity";

    public static void main(String[] args) {
        Solution solution = new Solution();

        if (args.length == 0) {
            System.out.println(CITY_NOT_FOUND_MESSAGE);
            return;
        }

        if (args.length == 1) {
            solution.run(args[0], "");
        } else {
            solution.run(args[0], args[1]);
        }
    }

    private void run(String city, String zone) {
        LOGGER.debug("City and zone:" + city + zone);

        TimeIdentifier timeIdentifier = new OwnTimeIdentifier();
        MessageWriter messageWriter = new OwnMessageWriter(PROPERTIES_NAME);

        OffsetDateTime time = timeIdentifier.identifyTime(city, zone);
        int secondCount = (time.getHour() * 3600) + (time.getMinute() * 60) + time.getSecond();

        TimesOfDay timesOfDay = TimesOfDay.NIGHT;
        if (secondCount > COUNT_SECOND_SIX_OCLOCK && secondCount <= COUNT_SECOND_NINE_OCLOCK) {
            timesOfDay = TimesOfDay.MORNING;
        }
        if (secondCount > COUNT_SECOND_NINE_OCLOCK && secondCount <= COUNT_SECOND_NINETEEN_OCLOCK) {
            timesOfDay = TimesOfDay.DAY;
        }
        if (secondCount > COUNT_SECOND_NINETEEN_OCLOCK && secondCount <= COUNT_SECOND_TWENTY_THREE_OCLOCK) {
            timesOfDay = TimesOfDay.EVENING;
        }
        LOGGER.debug("TimesOfDay is " + timesOfDay.getTimeOfDay());

        messageWriter.print(city, timesOfDay);
    }
}