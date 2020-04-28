package utils;

import trip.Trip;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class InputReader {

    public static Trip readTrip() {
        // Loop until the user provides valid input

        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter the departure airport: ");
        String departingAirport = scanner.nextLine().replaceAll("\\s+","");
        // TODO: Implement input validation for airport code
        boolean b = InputUtils.validateAirportInput(departingAirport);

        System.out.println("Please enter the arrival airport: ");
        String arrivalAirport = scanner.nextLine().replaceAll("\\s+","");
        // TODO: Implement input validation for airport code
        InputUtils.validateAirportInput(arrivalAirport);

        System.out.print("Please enter departure date: ");
        String departureDate = scanner.nextLine().replaceAll("\\s+","");
        // TODO: Implement date validation

        System.out.println("Is this a round-trip? Enter 'Y' or 'N': ");
        String roundTrip = scanner.nextLine().replaceAll("\\s+","");
        System.out.println("Roundtrip: " + roundTrip);
        // TODO: Check if input given is equal to 'Y' or 'N'

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (roundTrip.equals("Y")) {
            System.out.println("Please enter the returning date: ");
            String returningDate = scanner.nextLine();
            // TODO: Implement date validation

            Trip trip = null;
            try {
                trip = new Trip(sdf.parse(departureDate), sdf.parse(returningDate), departingAirport, arrivalAirport, false);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return trip;
        } else {
            Trip trip = null;
            try {
                trip = new Trip(sdf.parse(departureDate), null, departingAirport, arrivalAirport, true);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return trip;
        }
    }

}
