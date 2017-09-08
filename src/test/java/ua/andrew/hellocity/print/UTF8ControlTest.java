package ua.andrew.hellocity.print;

import org.junit.Before;
import org.junit.Test;

import java.util.ResourceBundle;

import static org.junit.Assert.assertEquals;

public class UTF8ControlTest {

    private ResourceBundle bundle;

    @Before
    public void init() {
        bundle = ResourceBundle.getBundle("HelloCity", new UTF8Control());
    }

    @Test
    public void testUTF8Control() {
        assertEquals("Good morning didn't print correctly!",
                "Good morning",
                bundle.getString("morning"));
    }
}