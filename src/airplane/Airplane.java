package airplane;

import dao.ServerInterface;

public class Airplane {
    /**
     * Airplane attributes as defined by the CS509 server interface XML
     */
    /** Manufacture */
    String manufacturer;

    /**
     * Airplane attributes as defined by the CS509 server interface XML
     */
    /** Airplane model */
    String model;

    /**
     * Airplane attributes as defined by the CS509 server interface XML
     */
    /** Count of total first class seats */
    int firstClassSeats;

    /**
     * Airplane attributes as defined by the CS509 server interface XML
     */
    /** Count of total coach seats */
    int coachSeats;

    /**
     * Initializing constructor.
     *
     * @param airplane String of the airplane
     */
    public Airplane(String airplane) {
        Airplanes airplanesAvailable = ServerInterface.INSTANCE.getAirplanesAvailable();
        for (Airplane a : airplanesAvailable) {
            if (airplane.equalsIgnoreCase(a.model)) {
                this.manufacturer = a.getManufacturer();
                this.model = a.getModel();
                this.firstClassSeats = a.getFirstClassSeats();
                this.coachSeats = a.getCoachSeats();
            }
        }
    }

    /**
     * Initializing constructor.
     *
     * @param manufacturer String of the manufacturer
     * @param model String of the model
     * @param firstClassSeats Count of the firstClassSeats
     * @param coachSeats Count of the coachSeats
     */
    public Airplane(String manufacturer, String model, int firstClassSeats, int coachSeats) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.firstClassSeats = firstClassSeats;
        this.coachSeats = coachSeats;
    }

    /**
     * get the manufacturer name
     *
     * @return manufacturer name
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * get the model name
     *
     * @return model name
     */
    public String getModel() {
        return model;
    }

    /**
     * get the first class seats count
     *
     * @return first class seats count
     */
    public int getFirstClassSeats() {
        return firstClassSeats;
    }

    /**
     * get the coach seats count
     *
     * @return coach seats count
     */
    public int getCoachSeats() {
        return coachSeats;
    }

    @Override
    public String toString() {
        return "Airplane{" +
                "manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", firstClassSeats=" + firstClassSeats +
                ", coachSeats=" + coachSeats +
                '}';
    }
}
