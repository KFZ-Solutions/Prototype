package tests;

import org.junit.Test;
import utils.InputUtils;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;



public class InputUtilsTest {

    @Test
    public void testValidateAirportInput() {
        // Test the validateAirportInput function
        // When the user input is in a list of string for valid airport, it will return true
        // otherwise will return false

        ArrayList<String> availableAirports = new ArrayList<String>(1);
        availableAirports.add("BOS");
        String validAirport = "BOS";
        String invalidAirport = "BOSTON";

        Boolean result1 = InputUtils.validateAirportInput(validAirport, availableAirports);
        assertEquals(Boolean.TRUE, result1);

        Boolean result2 = InputUtils.validateAirportInput(invalidAirport, availableAirports);
        assertEquals(Boolean.FALSE, result2);
    }

    @Test
    public void testValidateDateInput() {
        // Test the validateDateInput function
        // When the user input is for date is in format yyyy-mm-dd, it will return true
        // otherwise will return false

        String validDate0 = "2020-05-12";
        String invalidDate1 = "20200512";
        String invalidDate2 = "May-Second-Twentytuenty";
        String invalidDate3 = "";

        Boolean result1 = InputUtils.validateDateInput(validDate0);
        assertEquals(Boolean.TRUE, result1);

        Boolean result2 = InputUtils.validateDateInput(invalidDate1);
        assertEquals(Boolean.FALSE, result2);

        Boolean result3 = InputUtils.validateDateInput(invalidDate2);
        assertEquals(Boolean.FALSE, result3);

        Boolean result4 = InputUtils.validateDateInput(invalidDate3);
        assertEquals(Boolean.FALSE, result4);
    }

    @Test
    public void testYOrNInput() {
        // Test the YOrNInput function
        // When the user input is Y or N, it will return true
        // otherwise will return false

        String validString1 = "Y";
        String validString2 = "N";
        String invalidString1 = "yes";
        String invalidString2 = "no";
        String invalidString3 = "";

        Boolean result1 = InputUtils.YOrNInput(validString1);
        assertEquals(Boolean.TRUE, result1);

        Boolean result2 = InputUtils.YOrNInput(validString2);
        assertEquals(Boolean.TRUE, result2);

        Boolean result3 = InputUtils.YOrNInput(invalidString1);
        assertEquals(Boolean.FALSE, result3);

        Boolean result4 = InputUtils.YOrNInput(invalidString2);
        assertEquals(Boolean.FALSE, result4);

        Boolean result5 = InputUtils.YOrNInput(invalidString3);
        assertEquals(Boolean.FALSE, result5);
    }
}