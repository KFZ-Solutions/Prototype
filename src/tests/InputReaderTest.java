package tests;

import airplane.Airplane;
import flight.Arrival;
import flight.Departure;
import flight.Flight;
import org.junit.Test;
import seat.SeatClass;
import utils.InputReader;

import java.io.ByteArrayInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertEquals;
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
        Map<Integer, List<Flight>> testSearchMap = new HashMap<>();
        Map<Integer, List<Flight>> testOneConnectionMap = new HashMap<>();
        Map<Integer, List<Flight>> testTwoConnectionsMap = new HashMap<>();
        Map<Integer, List<Flight>> testThreeConnectionsMap = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm");
        try {
            Flight testFlightOneConnectionDirect = new Flight(
                    new Airplane("Boeing", "717", 24, 82),
                    52,
                    3778,
                    new Departure("BOS", sdf.parse("2020 May 09 22:26 EDT"), "2020 May 09 22:26 EDT"),
                    new Arrival("PHL", sdf.parse("2020 May 09 23:18 EDT"), "2020 May 09 23:18 EDT"),
                    new SeatClass("717", 162.65, 47.6, 24, 61)
            );
            List<Flight> testFlightListOneConnectionDirect = new ArrayList<>();
            testFlightListOneConnectionDirect.add(testFlightOneConnectionDirect);
            testOneConnectionMap.put(1, testFlightListOneConnectionDirect);
            Flight testFlightTwoConnections1 = new Flight(
                    new Airplane("Airbus", "A380", 128, 427),
                    98,
                    3775,
                    new Departure("BOS", sdf.parse("2020 May 09 20:31 EDT"), "2020 May 09 20:31 EDT"),
                    new Arrival("RDU", sdf.parse("2020 May 09 22:09 EDT"), "2020 May 09 22:09 EDT"),
                    new SeatClass("A380", 58.1, 17.42, 43, 403)
            );
            Flight testFlightTwoConnections2 = new Flight(
                    new Airplane("Boeing", "717", 24, 82),
                    48,
                    36933,
                    new Departure("RDU", sdf.parse("2020 May 09 23:41 EDT"), "2020 May 09 23:41 EDT"),
                    new Arrival("PHL", sdf.parse("2020 May 10 00:29 EDT"), "2020 May 10 00:29 EDT"),
                    new SeatClass("A380", 58.1, 17.42, 43, 403)
            );
            List<Flight> testFlightListTwoConnections = new ArrayList<>();
            testFlightListTwoConnections.add(testFlightTwoConnections1);
            testFlightListTwoConnections.add(testFlightTwoConnections2);
            testTwoConnectionsMap.put(1, new ArrayList<>() {{
                add(testFlightTwoConnections1);
                add(testFlightTwoConnections2);
            }});
            Flight testFlightThreeConnections1 = new Flight(
                    new Airplane("Boeing", "717", 24, 82),
                    171,
                    3788,
                    new Departure("BOS", sdf.parse("2020 May 10 04:03 EDT"), "2020 May 10 04:03 EDT"),
                    new Arrival("MCO", sdf.parse("2020 May 10 06:54 EDT"), "2020 May 10 06:54 EDT"),
                    new SeatClass("717", 540.63, 158.23, 13, 30)
            );
            Flight testFlightThreeConnections2 = new Flight(
                    new Airplane("Airbus", "A320", 12, 124),
                    50,
                    32219,
                    new Departure("MCO", sdf.parse("2020 May 10 08:40 EDT"), "2020 May 10 08:40 EDT"),
                    new Arrival("ATL", sdf.parse("2020 May 10 09:30 EDT"), "2020 May 10 09:30 EDT"),
                    new SeatClass("A320", 317.44, 30.72, 4, 20)
            );
            Flight testFlightThreeConnections3 = new Flight(
                    new Airplane("Boeing", "757", 32, 170),
                    91,
                    31,
                    new Departure("ATL", sdf.parse("2020 May 10 12:28 EDT"), "2020 May 10 12:28 EDT"),
                    new Arrival("PHL", sdf.parse("2020 May 10 13:59 EDT"), "2020 May 10 13:59 EDT"),
                    new SeatClass("757", 214.49, 40.37, 32, 110)
            );
            List<Flight> testFlightListThreeConnections = new ArrayList<>();
            testFlightListThreeConnections.add(testFlightThreeConnections1);
            testFlightListThreeConnections.add(testFlightThreeConnections2);
            testFlightListThreeConnections.add(testFlightThreeConnections3);
            testThreeConnectionsMap.put(1, testFlightListThreeConnections);
            // Add to test search map
            testSearchMap.put(1, testFlightListOneConnectionDirect);
            testSearchMap.put(2, testFlightListTwoConnections);
            testSearchMap.put(3, testFlightListThreeConnections);
            List<Flight> flights = InputReader.readFlightSelectionOrOptions(
                    testSearchMap,
                    testOneConnectionMap,
                    testTwoConnectionsMap,
                    testThreeConnectionsMap
            );

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
