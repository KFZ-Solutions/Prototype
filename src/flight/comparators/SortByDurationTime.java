package flight.comparators;

import flight.Flight;

import java.util.Comparator;

public class SortByDurationTime implements Comparator<Flight> {
    @Override
    public int compare(Flight flight1, Flight flight2) {
        Integer flightDuration1 = flight1.getFlightDuration();
        Integer flightDuration2 = flight2.getFlightDuration();
        return flightDuration1.compareTo(flightDuration2);
    }
}
