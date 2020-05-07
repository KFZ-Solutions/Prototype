package airplane;

import dao.ServerInterface;

public class Airplane {
    String manufacturer;
    String model;
    int firstClassSeats;
    int coachSeats;

    public Airplane(String airplane) {
        // TODO: query the airplanes database and fill in the data
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

    public Airplane(String manufacturer, String model, int firstClassSeats, int coachSeats) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.firstClassSeats = firstClassSeats;
        this.coachSeats = coachSeats;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public int getFirstClassSeats() {
        return firstClassSeats;
    }

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
