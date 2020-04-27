package trip;

import flight.Flight;

import java.util.Date;

public class Trip {
    Date departing;
    Date arriving;
    Flight[] connectingFlights;
    boolean oneWay;

    public Trip(Date departing, Date arriving, boolean oneWay) {
        this.departing = departing;
        this.arriving = arriving;
        this.oneWay = oneWay;
    }

    public Trip(Date departing, Date arriving, Flight[] connectingFlights, boolean oneWay) {
        this.departing = departing;
        this.arriving = arriving;
        this.connectingFlights = connectingFlights;
        this.oneWay = oneWay;
    }

    public Date getDepartingTime() {
        return departing;
    }

    public Date getArrivingTime() {
        return arriving;
    }

    public Flight[] getConnectingFlights() {
        return connectingFlights;
    }

    public boolean isOneWay() {
        return oneWay;
    }
}
