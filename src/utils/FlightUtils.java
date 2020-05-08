package utils;

import airport.Airport;
import airport.Airports;
import flight.Arrival;
import flight.Departure;
import flight.Flight;
import utils.converter.TimezoneMapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class FlightUtils {
    /**
     * Check if flights has available seats
     */
    public static boolean flightsHasAvailableSeat(List<Flight> flights) {
        for (Flight flight : flights) {
            if (flight.getSeating().getTotalCoach() >= flight.getAirplane().getCoachSeats() && flight.getSeating().getTotalFirstClass() >= flight.getAirplane().getFirstClassSeats()) {
                return false;
            }
        }
        return true;
    }
    /**
     * Convert flight time base on airport timezone
     */
    public static void convertFlightTime(Flight flight, Airports airports) throws ParseException {
        Departure depature = flight.getDeparture();
        Arrival arrival = flight.getArrival();
        Airport depatureAirport = null;
        Airport arrivalAirport = null;

        for (Airport airport : airports) {
            if (airport.mCode.equals(depature.getAirportCode())) {
                depatureAirport = airport;
            }
            if (airport.mCode.equals(arrival.getAirportCode())) {
                arrivalAirport = airport;
            }
        }

        TimeZone depatureTimeZone = TimeZone.getTimeZone(TimezoneMapper.latLngToTimezoneString(depatureAirport.mLatitude, depatureAirport.mLongitude));
        TimeZone arrivalTimeZone = TimeZone.getTimeZone(TimezoneMapper.latLngToTimezoneString(arrivalAirport.mLatitude, arrivalAirport.mLongitude));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm z");

        Date depatureDate = sdf.parse(depature.getStringDate());
        sdf.setTimeZone(depatureTimeZone);
        depature.setStrTime(sdf.format(depatureDate));

        sdf.setTimeZone(arrivalTimeZone);
        Date arrivalDate = sdf.parse(arrival.getStringDate());
        arrival.setStrTime(sdf.format(arrivalDate));

        flight.setDepature(depature);
        flight.setArrival(arrival);

        return;
    }
    /**
     * Calculates total duration of flight
     */
    public int calcFlightDuration() {
        return 0; // stub
    }
}
