package flight.comparators;

import flight.Flight;

import java.util.Comparator;
import java.util.Date;

public class SortByDepartureTime implements Comparator<Flight> {
    @Override
    public int compare(Flight flight1, Flight flight2) {
        Date date1 = flight1.getDeparture().getDate();
        Date date2 = flight2.getDeparture().getDate();
        return date1.compareTo(date2);
    }
}
