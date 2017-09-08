package ua.andrew.hellocity.print;

import org.junit.Before;
import org.junit.Test;
import ua.andrew.hellocity.time.TimesOfDay;

import static org.junit.Assert.assertEquals;

public class OwnMessageWriterTest {

    private MessageWriter messageWriter;

    @Before
    public void init() {
        messageWriter = new OwnMessageWriter("HelloCity");
    }

    @Test
    public void testPrintMorning() {
        assertEquals("Good morning didn't print correctly!",
                "Доброе утро, TestCity!",
                messageWriter.print("TestCity", TimesOfDay.MORNING));
    }

    @Test
    public void testPrintDay() {
        assertEquals("Good afternoon didn't print correctly!",
                "Добрый день, TestCity!",
                messageWriter.print("TestCity", TimesOfDay.DAY));
    }

    @Test
    public void testPrintEvening() {
        assertEquals("Good evening didn't print correctly!",
                "Добрый вечер, TestCity!",
                messageWriter.print("TestCity", TimesOfDay.EVENING));
    }

    @Test
    public void testPrintNight() {
        assertEquals("Good night didn't print correctly!",
                "Доброй ночи, TestCity!",
                messageWriter.print("TestCity", TimesOfDay.NIGHT));
    }

    @Test
    public void testPrintNullCity() {
        assertEquals("Good morning with null city didn't print correctly!",
                "Доброе утро, null!",
                messageWriter.print(null, TimesOfDay.MORNING));
    }

    @Test(expected = NullPointerException.class)
    public void testPrintNullTimesOfDay() {
        messageWriter.print("TestCity", null);
    }
}