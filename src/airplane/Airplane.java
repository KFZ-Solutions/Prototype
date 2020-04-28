package airplane;

public class Airplane {
    String manufacturer;
    String model;
    int firstClassSeats;
    int coachSeats;

    public Airplane(String airplane) {
        // TODO: query the airplanes database and fill in the data
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
