package trip;

import airport.Airport;

public class Layover {
    int time;
    Airport airport;

    public Layover(int time, Airport airport) {
        this.time = time;
        this.airport = airport;
    }

    public int getTime() {
        return time;
    }

    public Airport getAirport() {
        return airport;
    }
}
