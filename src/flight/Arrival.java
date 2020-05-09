package flight;

import airport.Airport;

import java.util.Date;

public class Arrival {
    /**
     * Arrival attributes
     */
    /** airportCode */
    String airportCode;

    /**
     * Arrival attributes which is a Date format
     */
    /** time */
    Date time;

    /**
     * Arrival attributes which is a string of the time rather than the time (Date)
     */
    /** strTime */
    String strTime;

    /**
     * Initializing constructor.
     *
     * @param airport Airport of arrival
     * @param time Arrival date in Date format
     * @param strTime Arrival date in string format, normally is time string converted to local timezone
     */
    public Arrival(String airport, Date time, String strTime) {
        this.airportCode = airport;
        this.time = time;
        this.strTime = strTime;
    }

    /**
     * get the arrival airportCode
     *
     * @return the arrival airportCode
     */
    public String getAirportCode() {
        return airportCode;
    }

    /**
     * get the arrival time in Date format
     *
     * @return the arrival time
     */
    public Date getDate() {
        return time;
    }

    /**
     * get the arrival time in String format
     *
     * @return the arrival strTime
     */
    public String getStringDate() {
        return strTime;
    }

    /**
     * set the arrival strTime
     *
     * @param strTime The arrival string time
     */
    public void setStrTime(String strTime) {
        this.strTime = strTime;
    }

    @Override
    public String toString() {
        return "Arrival{" +
                "airportCode='" + airportCode + '\'' +
                ", time=" + strTime +
                '}';
    }
}
