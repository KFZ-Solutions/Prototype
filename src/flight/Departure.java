package flight;

import airport.Airport;
import dao.IFlight;

import java.util.Date;

public class Departure implements IFlight {
    /**
     * Departure attributes
     */
    /** airportCode */
    String airportCode;

    /**
     * Departure attributes which is a Date format
     */
    /** time */
    Date time;

    /**
     * Departure attributes which is a string of the time rather than the time (Date)
     */
    /** strTime */
    String strTime;

    /**
     * Initializing constructor.
     *
     * @param airport Airport of departure
     * @param time Departure date in Date format
     * @param strTime Departure date in string format, normally is time string converted to local timezone
     */
    public Departure(String airport, Date time, String strTime) {
        this.airportCode = airport;
        this.time = time;
        this.strTime = strTime;
    }

    /**
     * get the departure airportCode
     *
     * @return the departure airportCode
     */
    public String getAirportCode() {
        return airportCode;
    }

    /**
     * get the departure time in Date format
     *
     * @return the departure time
     */
    public Date getDate() {
        return time;
    }

    /**
     * get the departure time in String format
     *
     * @return the departure strTime
     */
    public String getStringDate() {
        return strTime;
    }

    /**
     * set the departure strTime
     *
     * @param strTime The departure string time
     */
    public void setStrTime(String strTime) {
        this.strTime = strTime;
    }

    @Override
    public String toString() {
        return "Departure{" +
                "airportCode='" + airportCode + '\'' +
                ", time=" + strTime +
                '}';
    }
}
