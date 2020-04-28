package trip;

import flight.Flight;

import java.util.Arrays;
import java.util.Date;

public class Trip {
    Date departureDate;
    Date returnDate;
    String departingAirportCode;
    String returningAirportCode;
    Flight[] connectingFlights;
    boolean oneWay;

    public Trip(Date departureDate, Date returnDate, String departingAirportCode, String returningAirportCode, boolean oneWay) {
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.departingAirportCode = departingAirportCode;
        this.returningAirportCode = returningAirportCode;
        this.oneWay = oneWay;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public String getDepartingAirportCode() {
        return departingAirportCode;
    }

    public String getReturningAirportCode() {
        return returningAirportCode;
    }

    public Flight[] getConnectingFlights() {
        return connectingFlights;
    }

    public boolean isOneWay() {
        return oneWay;
    }

    @Override
    public String toString() {
        String trip = "Trip from " + departingAirportCode + " to " + returningAirportCode;
        if (!oneWay) {
            trip += " (returning).";
        }
        trip += "\n";
        trip += "Date departing: " + departureDate;
        if (!oneWay) {
            trip += "\n";
            trip += "Date returning: " + returnDate;
        }
        return trip;
    }
}
