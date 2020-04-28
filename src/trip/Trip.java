package trip;

import flight.Flight;

import java.util.Date;

public class Trip {
    Date departureDate;
    Date returnDate;
    String departingAirportCode;
    String arrivalAirportCode;
    Flight[] connectingFlights;
    boolean oneWay;

    public Trip(Date departureDate, Date returnDate, String departingAirportCode, String arrivalAirportCode, boolean oneWay) {
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.departingAirportCode = departingAirportCode;
        this.arrivalAirportCode = arrivalAirportCode;
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

    public String getArrivalAirportCode() {
        return arrivalAirportCode;
    }

    public Flight[] getConnectingFlights() {
        return connectingFlights;
    }

    public boolean isOneWay() {
        return oneWay;
    }

    @Override
    public String toString() {
        String trip = "Trip from " + departingAirportCode + " to " + arrivalAirportCode;
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
