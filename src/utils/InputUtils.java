package utils;

import java.util.ArrayList;

public class InputUtils {
    /**
     * This method will validate the user airport input by checking if it is included in available airports
     *
     * @param airport Input string of the user departure or arrival airport
     * @param availableAirports A list of available airports that will be compared to
     * @return Boolean value of if the airport input string is a valid airport
     */
    public static boolean validateAirportInput(String airport, ArrayList<String> availableAirports) {
        if (!availableAirports.contains(airport)) {
            System.out.print("Airport Does not exist. ");
            return false;
        }
        return true; // stub
    }

    /**
     * This method will validate the user date input matches the date string format yyyy-mm-dd or not
     *
     * @param dateInput Input string of the user's departure or arrival date
     * @return Boolean value of if the date input matches the date string format
     */
    public static boolean validateDateInput(String dateInput) {
        if (!dateInput.matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$")) {
            System.out.print("Date format does not match yyyy-mm-dd. ");
            return false;
        }
        return true;
    }

    /**
     * This method will validate the user Y or N input
     *
     * @param YOrN A string of user's input for Y or N.
     * @return Boolean value of if users Y or N input is Y or N
     */
    public static boolean YOrNInput(String YOrN) {
        if (!(YOrN.equals("Y") || YOrN.equals("N"))) {
            System.out.print("Input is not Y or N. ");
            return false;
        }
        return true;
    }
}
