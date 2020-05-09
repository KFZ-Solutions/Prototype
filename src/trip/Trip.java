package trip;

import flight.Flight;

import java.util.Date;

public class Trip {
    /**
     * Trip attributes
     */
    /** departureDate */
    Date departureDate;

    /**
     * Trip attributes
     */
    /** returnDate */
    Date returnDate;

    /**
     * Trip attributes
     */
    /** departingAirportCode */
    String departingAirportCode;

    /**
     * Trip attributes
     */
    /** arrivalAirportCode */
    String arrivalAirportCode;

    /**
     * Trip attributes
     */
    /** connectingFlights */
    Flight[] connectingFlights;

    /**
     * Trip attributes
     */
    /** oneWay */
    boolean oneWay;

    /**
     * Initializing constructor.
     *
     * @param departureDate The date of the departure time
     * @param returnDate The date of the return time
     * @param departingAirportCode The airport code of the departure airport
     * @param arrivalAirportCode The airport code of the arrival airport
     * @param oneWay If the trip is a one way trip or not, ie. round trip
     */
    public Trip(Date departureDate, Date returnDate, String departingAirportCode, String arrivalAirportCode, boolean oneWay) {
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.departingAirportCode = departingAirportCode;
        this.arrivalAirportCode = arrivalAirportCode;
        this.oneWay = oneWay;
    }

    /**
     * get the Trip departure date
     *
     * @return the Trip departure date
     */
    public Date getDepartureDate() {
        return departureDate;
    }

    /**
     * get the Trip returning date
     *
     * @return the Trip returning date
     */
    public Date getReturnDate() {
        return returnDate;
    }

    /**
     * get the Trip departure airport code
     *
     * @return the Trip departure airport code
     */
    public String getDepartingAirportCode() {
        return departingAirportCode;
    }

    /**
     * get the Trip arrival airport code
     *
     * @return the Trip arrival airport code
     */
    public String getArrivalAirportCode() {
        return arrivalAirportCode;
    }

    /**
     * get the Trip connecting flights
     *
     * @return the Trip connecting flights
     */
    public Flight[] getConnectingFlights() {
        return connectingFlights;
    }

    /**
     * get the Trip isOneWay or not information
     *
     * @return the Trip isOneWay or not value
     */
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
