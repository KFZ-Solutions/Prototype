package flight;

import airport.Airport;

import java.util.Date;

public class Arrival {
    Airport airport;
    Date time;

    public Arrival(Airport airport, Date time) {
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
