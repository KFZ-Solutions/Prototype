package flight.comparators;

import flight.Flight;

import java.util.Comparator;

class SortByPrice implements Comparator<Flight> {
    @Override
    public int compare(Flight flight1, Flight flight2) {
        Double coachPrice1 = flight1.getSeating().getCoachPrice();
        Double coachPrice2 = flight2.getSeating().getCoachPrice();
        return coachPrice1.compareTo(coachPrice2);
    }
}
