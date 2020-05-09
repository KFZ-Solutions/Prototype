package flight;

import airplane.Airplane;
import seat.SeatClass;

public class Flight {
    /**
     * Flight attributes
     */
    /** airplane */
    Airplane airplane;

    /**
     * Flight attributes
     */
    /** flightDuration */
    int flightDuration;

    /**
     * Flight attributes
     */
    /** number */
    int number;

    /**
     * Flight attributes
     */
    /** departure */
    Departure departure;

    /**
     * Flight attributes
     */
    /** arrival */
    Arrival arrival;

    /**
     * Flight attributes
     */
    /** seating */
    SeatClass seating;

    /**
     * Initializing constructor.
     *
     * @param airplane Airplane of flight
     * @param flightDuration Flight duration
     * @param number Flight number
     * @param departure Departure information of the flight
     * @param arrival Arrival information of the flight
     * @param seating Seating information of the flight
     */
    public Flight(Airplane airplane, int flightDuration, int number, Departure departure, Arrival arrival, SeatClass seating) {
        this.airplane = airplane;
        this.flightDuration = flightDuration;
        this.number = number;
        this.departure = departure;
        this.arrival = arrival;
        this.seating = seating;
    }

    /**
     * get the airplane information
     *
     * @return airplane of the flight
     */
    public Airplane getAirplane() {
        return airplane;
    }

    /**
     * get the flight duration
     *
     * @return flight duration
     */
    public int getFlightDuration() {
        return flightDuration;
    }

    /**
     * get the flight number
     *
     * @return flight number
     */
    public int getNumber() {
        return number;
    }

    /**
     * get the departure information of the flight
     *
     * @return departure of the flight
     */
    public Departure getDeparture() {
        return departure;
    }

    /**
     * get the arrival information of the flight
     *
     * @return arrival of the flight
     */
    public Arrival getArrival() {
        return arrival;
    }

    /**
     * get the seating information
     *
     * @return flight seating information
     */
    public SeatClass getSeating() {
        return seating;
    }

    /**
     * set the departure information
     *
     * @param depature The departure information
     */
    public void setDepature(Departure depature) { this.departure = depature; }

    /**
     * set the arrival information
     *
     * @param arrival The departure information
     */
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
