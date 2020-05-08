package flight;

import airport.Airport;
import dao.IFlight;

import java.util.Date;

public class Departure implements IFlight {
    String airportCode;
    Date time;
    String strTime;

    public Departure(String airport, Date time, String strTime) {
        this.airportCode = airport;
        this.time = time;
        this.strTime = strTime;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public Date getDate() {
        return time;
    }

    public String getStringDate() {
        return strTime;
    }

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
