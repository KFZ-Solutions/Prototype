package tests;

import org.junit.Test;
import utils.InputReader;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class InputReaderTest {
    @Test
    public void testReadTrip() {
        String data = "BOS\r\nSFO\r\n2020-05-10\r\nN\r\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        InputReader.readTrip(new ArrayList<>() {{
            add("BOS");
            add("SFO");
        }});
        assertTrue(true); // if we reach this statement, the test has passed
    }

    @Test
    public void testReadFlightSelectionOrOptions() {

    }
}
