package trip;

import airport.Airport;

public class Layover {
    /**
     * Layover attributes
     */
    /** time */
    int time;

    /**
     * Layover attributes
     */
    /** airport */
    Airport airport;

    /**
     * Initializing constructor.
     *
     * @param time layover time
     * @param airport Airport of the layover
     */
    public Layover(int time, Airport airport) {
        this.time = time;
        this.airport = airport;
    }

    /**
     * get the layover time
     *
     * @return the layover time
     */
    public int getTime() {
        return time;
    }

    /**
     * get the layover airport
     *
     * @return the layover airport
     */
    public Airport getAirport() {
        return airport;
    }
}
