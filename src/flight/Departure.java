package flight;

import airport.Airport;
import dao.IFlight;

import java.util.Date;

public class Departure implements IFlight {
    String airportCode;
    Date time;

    public Departure(String airport, Date time) {
        this.airportCode = airport;
        this.time = time;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public Date getDate() {
        return time;
    }
}
