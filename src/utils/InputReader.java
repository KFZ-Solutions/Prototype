package utils;

import trip.Trip;

import java.util.Date;
import java.util.Scanner;

public class InputReader {

    public static Trip readTrip() {
        // Loop until the user provides valid input

        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter the departure airport: ");
        String departingAirport = scanner.nextLine();
        // TODO: Implement input validation for airport code
        boolean b = InputUtils.validateAirportInput(departingAirport);

        System.out.println("Please enter the arrival airport: ");
        String arrivalAirport = scanner.nextLine();
        // TODO: Implement input validation for airport code
        InputUtils.validateAirportInput(arrivalAirport);

        System.out.print("Please enter departure date: ");
        String departureDate = scanner.nextLine();
        // TODO: Implement date validation

        System.out.println("Is this a round-trip? Enter 'Y' or 'N': ");
        String roundTrip = scanner.nextLine();
        // TODO: Check if input given is equal to 'Y' or 'N'

        // TODO: Replace with real values
        Trip trip = new Trip(new Date(), new Date(), true);
        return trip;
    }

}
