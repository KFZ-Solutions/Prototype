package seat;

public class SeatClass {

    private String airplane;
    private double firstClassPrice;
    private double coachPrice;
    private int totalFirstClass;
    private int totalCoach;

    public SeatClass(String airplane, double firstClassPrice, double coachPrice, int totalFirstClass, int totalCoach) {
        this.airplane = airplane;
        this.firstClassPrice = firstClassPrice;
        this.coachPrice = coachPrice;
        this.totalFirstClass = totalFirstClass;
        this.totalCoach = totalCoach;
    }

    public String getAirplane() {
        return airplane;
    }

    public double getFirstClassPrice() {
        return firstClassPrice;
    }

    public double getCoachPrice() {
        return coachPrice;
    }

    public int getTotalFirstClass() {
        return totalFirstClass;
    }

    public int getTotalCoach() {
        return totalCoach;
    }
}
