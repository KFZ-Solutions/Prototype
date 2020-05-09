package seat;

public class SeatClass {
    /**
     * SeatClass attributes
     */
    /** airplane */
    private String airplane;

    /**
     * SeatClass attributes
     */
    /** firstClassPrice */
    private double firstClassPrice;

    /**
     * SeatClass attributes
     */
    /** coachPrice */
    private double coachPrice;

    /**
     * SeatClass attributes
     */
    /** totalFirstClass */
    private int totalFirstClass;

    /**
     * SeatClass attributes
     */
    /** totalCoach */
    private int totalCoach;

    /**
     * Initializing constructor.
     *
     * @param airplane Airplane of seats
     * @param firstClassPrice First class seat price
     * @param coachPrice Coach seat price
     * @param totalFirstClass Total first class seat booked
     * @param totalCoach Total coach seat booked
     */
    public SeatClass(String airplane, double firstClassPrice, double coachPrice, int totalFirstClass, int totalCoach) {
        this.airplane = airplane;
        this.firstClassPrice = firstClassPrice;
        this.coachPrice = coachPrice;
        this.totalFirstClass = totalFirstClass;
        this.totalCoach = totalCoach;
    }

    /**
     * get the SeatClass airplane information
     *
     * @return the SeatClass airplane information
     */
    public String getAirplane() {
        return airplane;
    }

    /**
     * get the SeatClass airplane information
     *
     * @return the SeatClass airplane information
     */
    public double getFirstClassPrice() {
        return firstClassPrice;
    }

    /**
     * get the SeatClass coachPrice information
     *
     * @return the SeatClass coachPrice information
     */
    public double getCoachPrice() {
        return coachPrice;
    }

    /**
     * get the SeatClass totalFirstClass information
     *
     * @return the SeatClass totalFirstClass information
     */
    public int getTotalFirstClass() {
        return totalFirstClass;
    }

    /**
     * get the SeatClass totalCoach information
     *
     * @return the SeatClass totalCoach information
     */
    public int getTotalCoach() {
        return totalCoach;
    }
}
