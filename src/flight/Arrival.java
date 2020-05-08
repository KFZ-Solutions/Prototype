package flight;

import airport.Airport;

import java.util.Date;

public class Arrival {
    String airportCode;
    Date time;
    String strTime;

    public Arrival(String airport, Date time, String strTime) {
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
        return "Arrival{" +
                "airportCode='" + airportCode + '\'' +
                ", time=" + strTime +
                '}';
    }
}
