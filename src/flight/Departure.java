package flight;

import airport.Airport;
import dao.IFlight;

import java.util.Date;

public class Departure implements IFlight {
    Airport airport;
    Date time;

    public Departure(Airport airport, Date time) {
        this.airport = airport;
        this.time = time;
    }

    public Airport getAirport() {
        return airport;
    }

    public Date getDate() {
        return time;
    }
}
