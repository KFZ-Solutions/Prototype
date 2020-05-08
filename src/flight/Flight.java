package flight;

import airplane.Airplane;
import seat.SeatClass;

public class Flight {
    Airplane airplane;
    int flightDuration;
    int number;
    Departure departure;
    Arrival arrival;
    SeatClass seating;

    public Flight(Airplane airplane, int flightDuration, int number, Departure departure, Arrival arrival, SeatClass seating) {
        this.airplane = airplane;
        this.flightDuration = flightDuration;
        this.number = number;
        this.departure = departure;
        this.arrival = arrival;
        this.seating = seating;
    }

    /**
     * Sorting:
     *  - Departure time
     *  - Arrival time
     *  - Duration
     *  - Price
     */


    public Airplane getAirplane() {
        return airplane;
    }

    public int getFlightDuration() {
        return flightDuration;
    }

    public int getNumber() {
        return number;
    }

    public Departure getDeparture() {
        return departure;
    }

    public Arrival getArrival() {
        return arrival;
    }

    public SeatClass getSeating() {
        return seating;
    }

    public void setDepature(Departure depature) { this.departure = depature; }

    public void setArrival(Arrival arrival) { this.arrival = arrival; }

    @Override
    public String toString() {
        return "Flight {" +
                "number=" + number +
                ", departure=" + departure.toString() +
                ", arrival=" + arrival.toString() +
                '}';
    }
}
