package utils;

import flight.Flight;

import java.util.List;

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
     * Calculates total duration of flight
     */
    public int calcFlightDuration() {
        return 0; // stub
    }
}
