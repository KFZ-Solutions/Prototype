package utils;

import trip.Trip;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class InputReader {

    public static Trip readTrip(ArrayList<String> availableAirports) {
        // Loop until the user provides valid input

        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter the departure airport: ");
        String departingAirport = scanner.nextLine().replaceAll("\\s+","");
        while (!InputUtils.validateAirportInput(departingAirport, availableAirports)) {
            System.out.print("Invalid airport. Please re-enter the departure airport: ");
            departingAirport = scanner.nextLine().replaceAll("\\s+","");
        }

        System.out.println("Please enter the arrival airport: ");
        String arrivalAirport = scanner.nextLine().replaceAll("\\s+","");
        while (!InputUtils.validateAirportInput(arrivalAirport, availableAirports)) {
            System.out.print("Invalid airport. Please re-enter the arrival airport: ");
            arrivalAirport = scanner.nextLine().replaceAll("\\s+","");
        }

        System.out.print("Please enter departure date: ");
        String departureDate = scanner.nextLine().replaceAll("\\s+","");
        while (!InputUtils.validateDateInput(departureDate)) {
            System.out.print("Invalid depature date. Please re-enter the departure date: ");
            departureDate = scanner.nextLine().replaceAll("\\s+","");
        }

        System.out.println("Is this a round-trip? Enter 'Y' or 'N': ");
        String roundTrip = scanner.nextLine().replaceAll("\\s+","");
        while (!InputUtils.YOrNInput(roundTrip)) {
            System.out.print("Invalid round-trip answer. Please re-enter the Y or N: ");
            roundTrip = scanner.nextLine().replaceAll("\\s+","");
        }
        System.out.println("Roundtrip: " + roundTrip);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (roundTrip.equals("Y")) {
            System.out.println("Please enter the returning date: ");
            String returningDate = scanner.nextLine();
            while (!InputUtils.validateDateInput(returningDate)) {
                System.out.print("Invalid returning date. Please re-enter the returning date: ");
                returningDate = scanner.nextLine().replaceAll("\\s+","");
            }

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

    public static void readFlightSelection() {

    }

}
