package flight.comparators;

import flight.Flight;

import java.util.Comparator;
import java.util.Date;

public class SortByArrivalTime implements Comparator<Flight> {
    @Override
    public int compare(Flight flight1, Flight flight2) {
        Date date1 = flight1.getArrival().getDate();
        Date date2 = flight2.getArrival().getDate();
        return date1.compareTo(date2);
    }
}
