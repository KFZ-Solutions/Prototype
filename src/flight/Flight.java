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
}
