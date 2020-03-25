package utils;

import java.util.Scanner;

public class InputReader {

    public static String[] readDepartureAirportAndDate() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter the departure airport: ");
        String departingAirport = scanner.nextLine();

        System.out.print("Please enter departure date: ");
        String departureDate = scanner.nextLine();

        return new String[] {
                departingAirport,
                departureDate
        };
    }

}
