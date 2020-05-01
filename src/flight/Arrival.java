package flight;

import airport.Airport;

import java.util.Date;

public class Arrival {
    String airportCode;
    Date time;

    public Arrival(String airport, Date time) {
        this.airportCode = airport;
        this.time = time;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public Date getDate() {
        return time;
    }

    @Override
    public String toString() {
        return "Arrival{" +
                "airportCode='" + airportCode + '\'' +
                ", time=" + time +
                '}';
    }
}
