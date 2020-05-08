package tests;

import airplane.Airplane;
import airport.Airport;
import airport.Airports;
import flight.Arrival;
import flight.Departure;
import flight.Flight;
import flight.Flights;
import org.junit.Test;
import seat.SeatClass;
import utils.FlightUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class FlightUtilsTest {

    @Test
    public void testFlightsHasAvailableSeat() {
        // Test the flightsHasAvailableSeat function
        // When the flight has coach seat or first class seat, it will return true
        // otherwise will return false
        ArrayList<Flight> flightsHasSeat = new ArrayList<Flight>(1);
        ArrayList<Flight> flightsHasNOSeat = new ArrayList<Flight>(1);

        // set up test airplane
        Airplane airplane = new Airplane("Boeing", "737", 10, 50);

        // set up test departure and arrival
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Date depatureDate = new Date();
        String strDepatureDate = dateFormat.format(depatureDate);
        Date arrivalDate = new Date();
        String strArrivalDate = dateFormat.format(arrivalDate);
        Departure departure = new Departure("BOS", depatureDate, strDepatureDate);
        Arrival arrival = new Arrival("SFO", arrivalDate, strArrivalDate);

        // set up test SeatClass
        SeatClass seating1 = new SeatClass("737", 100, 50, 5, 10);
        SeatClass seating2 = new SeatClass("737", 80, 40, 10, 50);

        // set up test flights
        Flight flightHasSeat = new Flight(airplane, 600, 89757, departure, arrival, seating1);
        Flight flightHasNOSeat = new Flight(airplane, 900, 89758, departure, arrival, seating2);
        flightsHasSeat.add(flightHasSeat);
        flightsHasNOSeat.add(flightHasNOSeat);

        Boolean result1 = FlightUtils.flightsHasAvailableSeat(flightsHasSeat);
        assertEquals(Boolean.TRUE, result1);

        Boolean result2 = FlightUtils.flightsHasAvailableSeat(flightsHasNOSeat);
        assertEquals(Boolean.FALSE, result2);
    }

    @Test
    public void testConvertFlightTime() throws ParseException {
        // Test the convertFlightTime function
        // Convert the flight time to the timezone of departure and arrival airport
        Airplane airplane = new Airplane("Boeing", "737", 10, 50);

        // set up test departure and arrival
        DateFormat dateFormat = new SimpleDateFormat("yyyy MMM dd HH:mm z");
        Date depatureDate = new Date();
        String strDepatureDate = dateFormat.format(depatureDate);
        Date arrivalDate = new Date();
        String strArrivalDate = dateFormat.format(arrivalDate);
        Departure departure = new Departure("BOS", depatureDate, strDepatureDate);
        Arrival arrival = new Arrival("SFO", arrivalDate, strArrivalDate);

        // set up test SeatClass
        SeatClass seating = new SeatClass("737", 100, 50, 5, 10);

        // set up test flights
        Flight flightToConvert = new Flight(airplane, 600, 89757, departure, arrival, seating);

        // set up test airports
        Airport depatureAirport = new Airport("Logan International", "BOS", "42.366", "-71.010");
        Airport arrivalAirport = new Airport("San Francisco International", "SFO", "37.622", "-122.379");
        Airports airports = new Airports();
        airports.add(depatureAirport);
        airports.add(arrivalAirport);

        // convert the time to local timezone for the flight
        FlightUtils.convertFlightTime(flightToConvert, airports);

        // for BOS timezone
        assertThat(flightToConvert.toString(), containsString("GET"));
        // for SFO timezone
        assertThat(flightToConvert.toString(), containsString("EDT"));
    }
}