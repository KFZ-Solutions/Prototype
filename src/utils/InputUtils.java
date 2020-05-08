package utils;

import java.util.ArrayList;

public class InputUtils {
    /**
     * This method will validate the user airport input
     * @return
     */
    public static boolean validateAirportInput(String airport, ArrayList<String> availableAirports) {
        if (!availableAirports.contains(airport)) {
            System.out.print("Airport Does not exist. ");
            return false;
        }
        return true; // stub
    }

    /**
     * This method will validate the user date input
     * @return
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
     * @return
     */
    public static boolean YOrNInput(String YOrN) {
        if (!(YOrN.equals("Y") || YOrN.equals("N"))) {
            System.out.print("Input is not Y or N. ");
            return false;
        }
        return true; // stub
    }
}
