package flight;

import airplane.Airplane;
import seat.SeatClass;

import java.util.Date;

public class Flight {
    Airplane airplane;
    Date flightTime;
    int number;
    Departure departure;
    Arrival arrival;
    SeatClass seating;

    public Flight(Airplane airplane, Date flightTime, int number, Departure departure, Arrival arrival, SeatClass seating) {
        this.airplane = airplane;
        this.flightTime = flightTime;
        this.number = number;
        this.departure = departure;
        this.arrival = arrival;
        this.seating = seating;
    }
}
