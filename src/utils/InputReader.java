package utils;

import flight.Flight;
import flight.comparators.SortByArrivalTime;
import flight.comparators.SortByDepartureTime;
import flight.comparators.SortByPrice;
import seat.SeatClass;
import trip.Trip;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
        System.out.println("Round trip: " + roundTrip);

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

    public static List<Flight> readFlightSelectionOrOptions(Map<Integer, List<Flight>> searchFlightsResult, Map<Integer, List<Flight>> oneConnection, Map<Integer, List<Flight>> twoConnections, Map<Integer, List<Flight>> threeConnections) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the flights number you want [enter number or 'O' for options]: ");
        String input = scanner.nextLine().replaceAll("\\s+","");
        if (input.equalsIgnoreCase("O")) {
            System.out.println();
            System.out.println("Enter:");
            System.out.println("'SP' for sorting by price");
            System.out.println("'SDD' for sorting by departure date");
            System.out.println("'SAD' for sorting by arrival date");
            System.out.println("'SFD' for sorting by flight duration");
            System.out.println();
            input = scanner.nextLine().replaceAll("\\s+", "");
            if (input.equalsIgnoreCase("SP")) {
                // Sort the flight results by price
                // Combine all direct flights into one list
                List<Flight> oneConnectionList = new ArrayList<>();
                for (int i=1; i<=oneConnection.size(); i++) {
                    List<Flight> flights = oneConnection.get(i);
                    oneConnectionList.add(flights.get(0));
                }
                // Combine all two-connection flights into one list
                List<Flight> twoConnectionsList = new ArrayList<>();
                for (int i=1; i<=twoConnections.size(); i++) {
                    List<Flight> flights = twoConnections.get(i);
                    Flight flight1 = flights.get(0);
                    Flight flight2 = flights.get(1);
                    SeatClass flight1SeatClass = flight1.getSeating();
                    SeatClass flight2SeatClass = flight2.getSeating();
                    double totalCoachPrice = flight1SeatClass.getCoachPrice() + flight2SeatClass.getCoachPrice();
                    double totalFirstClassPrice = flight1SeatClass.getFirstClassPrice() + flight2SeatClass.getFirstClassPrice();
                    SeatClass flightSeatClass = new SeatClass(flight1SeatClass.getAirplane(), totalFirstClassPrice, totalCoachPrice, flight1SeatClass.getTotalFirstClass(), flight1SeatClass.getTotalCoach());
                    Flight flight = new Flight(
                            flight1.getAirplane(),
                flight1.getFlightDuration() + flight2.getFlightDuration(),
                    flight1.getNumber() + flight2.getNumber(),
                            flight1.getDeparture(),
                            flight2.getArrival(),
                            flightSeatClass
                    );
                    twoConnectionsList.add(flight);
                }
                // Combine all three-connection flights into one list
                List<Flight> threeConnectionsList = new ArrayList<>();
                for (int i=1; i<=threeConnections.size(); i++) {
                    List<Flight> flights = threeConnections.get(i);
                    Flight flight1 = flights.get(0);
                    Flight flight2 = flights.get(1);
                    Flight flight3 = flights.get(2);
                    SeatClass flight1SeatClass = flight1.getSeating();
                    SeatClass flight2SeatClass = flight2.getSeating();
                    SeatClass flight3SeatClass = flight3.getSeating();
                    double totalCoachPrice = flight1SeatClass.getCoachPrice() + flight2SeatClass.getCoachPrice() + flight3SeatClass.getCoachPrice();
                    double totalFirstClassPrice = flight1SeatClass.getFirstClassPrice() + flight2SeatClass.getFirstClassPrice() + flight3SeatClass.getFirstClassPrice();
                    SeatClass flightSeatClass = new SeatClass(flight1SeatClass.getAirplane(), totalFirstClassPrice, totalCoachPrice, flight1SeatClass.getTotalFirstClass(), flight1SeatClass.getTotalCoach());
                    Flight flight = new Flight(
                            flight1.getAirplane(),
                flight1.getFlightDuration() + flight2.getFlightDuration() + flight3.getFlightDuration(),
                    flight1.getNumber() + flight2.getNumber() + flight3.getNumber(),
                            flight1.getDeparture(),
                            flight3.getArrival(),
                            flightSeatClass
                    );
                    threeConnectionsList.add(flight);
                }
                // Combine all the flights together
                List<Flight> allConnectionFlights = new ArrayList<>();
                allConnectionFlights.addAll(oneConnectionList);
                allConnectionFlights.addAll(twoConnectionsList);
                allConnectionFlights.addAll(threeConnectionsList);
                Collections.sort(allConnectionFlights, new SortByPrice());

                for (Flight flight : allConnectionFlights) {
                    System.out.println("Flight: From(" + flight.getDeparture().getAirportCode() + "), To(" + flight.getArrival().getAirportCode() + "), Arrival(" + flight.getArrival().toString() + "), Departure(" + flight.getDeparture().toString() + "), TotalDuration(" + flight.getFlightDuration() + "), TotalCoachPrice(" + flight.getSeating().getCoachPrice() +"), TotalFirstClassPrice(" + flight.getSeating().getFirstClassPrice() + ")");
                }

                return InputReader.readFlightSelectionOrOptions(searchFlightsResult, oneConnection, twoConnections, threeConnections);
            } else if (input.equalsIgnoreCase("SDD")) {
                // Sort the flight results by departure date
                // Combine all direct flights into one list
                List<Flight> oneConnectionList = new ArrayList<>();
                for (int i=1; i<=oneConnection.size(); i++) {
                    List<Flight> flights = oneConnection.get(i);
                    oneConnectionList.add(flights.get(0));
                }
                // Combine all two-connection flights into one list
                List<Flight> twoConnectionsList = new ArrayList<>();
                for (int i=1; i<=twoConnections.size(); i++) {
                    List<Flight> flights = twoConnections.get(i);
                    Flight flight1 = flights.get(0);
                    Flight flight2 = flights.get(1);
                    SeatClass flight1SeatClass = flight1.getSeating();
                    SeatClass flight2SeatClass = flight2.getSeating();
                    double totalCoachPrice = flight1SeatClass.getCoachPrice() + flight2SeatClass.getCoachPrice();
                    double totalFirstClassPrice = flight1SeatClass.getFirstClassPrice() + flight2SeatClass.getFirstClassPrice();
                    SeatClass flightSeatClass = new SeatClass(flight1SeatClass.getAirplane(), totalFirstClassPrice, totalCoachPrice, flight1SeatClass.getTotalFirstClass(), flight1SeatClass.getTotalCoach());
                    Flight flight = new Flight(
                            flight1.getAirplane(),
                            flight1.getFlightDuration() + flight2.getFlightDuration(),
                            flight1.getNumber() + flight2.getNumber(),
                            flight1.getDeparture(),
                            flight2.getArrival(),
                            flightSeatClass
                    );
                    twoConnectionsList.add(flight);
                }
                // Combine all three-connection flights into one list
                List<Flight> threeConnectionsList = new ArrayList<>();
                for (int i=1; i<=threeConnections.size(); i++) {
                    List<Flight> flights = threeConnections.get(i);
                    Flight flight1 = flights.get(0);
                    Flight flight2 = flights.get(1);
                    Flight flight3 = flights.get(2);
                    SeatClass flight1SeatClass = flight1.getSeating();
                    SeatClass flight2SeatClass = flight2.getSeating();
                    SeatClass flight3SeatClass = flight3.getSeating();
                    double totalCoachPrice = flight1SeatClass.getCoachPrice() + flight2SeatClass.getCoachPrice() + flight3SeatClass.getCoachPrice();
                    double totalFirstClassPrice = flight1SeatClass.getFirstClassPrice() + flight2SeatClass.getFirstClassPrice() + flight3SeatClass.getFirstClassPrice();
                    SeatClass flightSeatClass = new SeatClass(flight1SeatClass.getAirplane(), totalFirstClassPrice, totalCoachPrice, flight1SeatClass.getTotalFirstClass(), flight1SeatClass.getTotalCoach());
                    Flight flight = new Flight(
                            flight1.getAirplane(),
                            flight1.getFlightDuration() + flight2.getFlightDuration() + flight3.getFlightDuration(),
                            flight1.getNumber() + flight2.getNumber() + flight3.getNumber(),
                            flight1.getDeparture(),
                            flight3.getArrival(),
                            flightSeatClass
                    );
                    threeConnectionsList.add(flight);
                }
                // Combine all the flights together
                List<Flight> allConnectionFlights = new ArrayList<>();
                allConnectionFlights.addAll(oneConnectionList);
                allConnectionFlights.addAll(twoConnectionsList);
                allConnectionFlights.addAll(threeConnectionsList);
                Collections.sort(allConnectionFlights, new SortByDepartureTime());

                for (Flight flight : allConnectionFlights) {
                    System.out.println("Flight: From(" + flight.getDeparture().getAirportCode() + "), To(" + flight.getArrival().getAirportCode() + "), Arrival(" + flight.getArrival().toString() + "), Departure(" + flight.getDeparture().toString() + "), TotalDuration(" + flight.getFlightDuration() + "), TotalCoachPrice(" + flight.getSeating().getCoachPrice() +"), TotalFirstClassPrice(" + flight.getSeating().getFirstClassPrice() + ")");
                }

                return InputReader.readFlightSelectionOrOptions(searchFlightsResult, oneConnection, twoConnections, threeConnections);
            } else if (input.equalsIgnoreCase("SAD")) {
                // Sort the flight results by arrival date
                // Combine all direct flights into one list
                List<Flight> oneConnectionList = new ArrayList<>();
                for (int i=1; i<=oneConnection.size(); i++) {
                    List<Flight> flights = oneConnection.get(i);
                    oneConnectionList.add(flights.get(0));
                }
                // Combine all two-connection flights into one list
                List<Flight> twoConnectionsList = new ArrayList<>();
                for (int i=1; i<=twoConnections.size(); i++) {
                    List<Flight> flights = twoConnections.get(i);
                    Flight flight1 = flights.get(0);
                    Flight flight2 = flights.get(1);
                    SeatClass flight1SeatClass = flight1.getSeating();
                    SeatClass flight2SeatClass = flight2.getSeating();
                    double totalCoachPrice = flight1SeatClass.getCoachPrice() + flight2SeatClass.getCoachPrice();
                    double totalFirstClassPrice = flight1SeatClass.getFirstClassPrice() + flight2SeatClass.getFirstClassPrice();
                    SeatClass flightSeatClass = new SeatClass(flight1SeatClass.getAirplane(), totalFirstClassPrice, totalCoachPrice, flight1SeatClass.getTotalFirstClass(), flight1SeatClass.getTotalCoach());
                    Flight flight = new Flight(
                            flight1.getAirplane(),
                            flight1.getFlightDuration() + flight2.getFlightDuration(),
                            flight1.getNumber() + flight2.getNumber(),
                            flight1.getDeparture(),
                            flight2.getArrival(),
                            flightSeatClass
                    );
                    twoConnectionsList.add(flight);
                }
                // Combine all three-connection flights into one list
                List<Flight> threeConnectionsList = new ArrayList<>();
                for (int i=1; i<=threeConnections.size(); i++) {
                    List<Flight> flights = threeConnections.get(i);
                    Flight flight1 = flights.get(0);
                    Flight flight2 = flights.get(1);
                    Flight flight3 = flights.get(2);
                    SeatClass flight1SeatClass = flight1.getSeating();
                    SeatClass flight2SeatClass = flight2.getSeating();
                    SeatClass flight3SeatClass = flight3.getSeating();
                    double totalCoachPrice = flight1SeatClass.getCoachPrice() + flight2SeatClass.getCoachPrice() + flight3SeatClass.getCoachPrice();
                    double totalFirstClassPrice = flight1SeatClass.getFirstClassPrice() + flight2SeatClass.getFirstClassPrice() + flight3SeatClass.getFirstClassPrice();
                    SeatClass flightSeatClass = new SeatClass(flight1SeatClass.getAirplane(), totalFirstClassPrice, totalCoachPrice, flight1SeatClass.getTotalFirstClass(), flight1SeatClass.getTotalCoach());
                    Flight flight = new Flight(
                            flight1.getAirplane(),
                            flight1.getFlightDuration() + flight2.getFlightDuration() + flight3.getFlightDuration(),
                            flight1.getNumber() + flight2.getNumber() + flight3.getNumber(),
                            flight1.getDeparture(),
                            flight3.getArrival(),
                            flightSeatClass
                    );
                    threeConnectionsList.add(flight);
                }
                // Combine all the flights together
                List<Flight> allConnectionFlights = new ArrayList<>();
                allConnectionFlights.addAll(oneConnectionList);
                allConnectionFlights.addAll(twoConnectionsList);
                allConnectionFlights.addAll(threeConnectionsList);
                Collections.sort(allConnectionFlights, new SortByArrivalTime());

                for (Flight flight : allConnectionFlights) {
                    System.out.println("Flight: From(" + flight.getDeparture().getAirportCode() + "), To(" + flight.getArrival().getAirportCode() + "), Arrival(" + flight.getArrival().toString() + "), Departure(" + flight.getDeparture().toString() + "), TotalDuration(" + flight.getFlightDuration() + "), TotalCoachPrice(" + flight.getSeating().getCoachPrice() +"), TotalFirstClassPrice(" + flight.getSeating().getFirstClassPrice() + ")");
                }

                return InputReader.readFlightSelectionOrOptions(searchFlightsResult, oneConnection, twoConnections, threeConnections);
            } else if (input.equalsIgnoreCase("SFD")) {
                // Sort the flight results by flight duration
                // Combine all direct flights into one list
                List<Flight> oneConnectionList = new ArrayList<>();
                for (int i=1; i<=oneConnection.size(); i++) {
                    List<Flight> flights = oneConnection.get(i);
                    oneConnectionList.add(flights.get(0));
                }
                // Combine all two-connection flights into one list
                List<Flight> twoConnectionsList = new ArrayList<>();
                for (int i=1; i<=twoConnections.size(); i++) {
                    List<Flight> flights = twoConnections.get(i);
                    Flight flight1 = flights.get(0);
                    Flight flight2 = flights.get(1);
                    SeatClass flight1SeatClass = flight1.getSeating();
                    SeatClass flight2SeatClass = flight2.getSeating();
                    double totalCoachPrice = flight1SeatClass.getCoachPrice() + flight2SeatClass.getCoachPrice();
                    double totalFirstClassPrice = flight1SeatClass.getFirstClassPrice() + flight2SeatClass.getFirstClassPrice();
                    SeatClass flightSeatClass = new SeatClass(flight1SeatClass.getAirplane(), totalFirstClassPrice, totalCoachPrice, flight1SeatClass.getTotalFirstClass(), flight1SeatClass.getTotalCoach());
                    Flight flight = new Flight(
                            flight1.getAirplane(),
                            flight1.getFlightDuration() + flight2.getFlightDuration(),
                            flight1.getNumber() + flight2.getNumber(),
                            flight1.getDeparture(),
                            flight2.getArrival(),
                            flightSeatClass
                    );
                    twoConnectionsList.add(flight);
                }
                // Combine all three-connection flights into one list
                List<Flight> threeConnectionsList = new ArrayList<>();
                for (int i=1; i<=threeConnections.size(); i++) {
                    List<Flight> flights = threeConnections.get(i);
                    Flight flight1 = flights.get(0);
                    Flight flight2 = flights.get(1);
                    Flight flight3 = flights.get(2);
                    SeatClass flight1SeatClass = flight1.getSeating();
                    SeatClass flight2SeatClass = flight2.getSeating();
                    SeatClass flight3SeatClass = flight3.getSeating();
                    double totalCoachPrice = flight1SeatClass.getCoachPrice() + flight2SeatClass.getCoachPrice() + flight3SeatClass.getCoachPrice();
                    double totalFirstClassPrice = flight1SeatClass.getFirstClassPrice() + flight2SeatClass.getFirstClassPrice() + flight3SeatClass.getFirstClassPrice();
                    SeatClass flightSeatClass = new SeatClass(flight1SeatClass.getAirplane(), totalFirstClassPrice, totalCoachPrice, flight1SeatClass.getTotalFirstClass(), flight1SeatClass.getTotalCoach());
                    Flight flight = new Flight(
                            flight1.getAirplane(),
                            flight1.getFlightDuration() + flight2.getFlightDuration() + flight3.getFlightDuration(),
                            flight1.getNumber() + flight2.getNumber() + flight3.getNumber(),
                            flight1.getDeparture(),
                            flight3.getArrival(),
                            flightSeatClass
                    );
                    threeConnectionsList.add(flight);
                }
                // Combine all the flights together
                List<Flight> allConnectionFlights = new ArrayList<>();
                allConnectionFlights.addAll(oneConnectionList);
                allConnectionFlights.addAll(twoConnectionsList);
                allConnectionFlights.addAll(threeConnectionsList);
                Collections.sort(allConnectionFlights, new SortByPrice());

                for (Flight flight : allConnectionFlights) {
                    System.out.println("Flight: From(" + flight.getDeparture().getAirportCode() + "), To(" + flight.getArrival().getAirportCode() + "), Arrival(" + flight.getArrival().toString() + "), Departure(" + flight.getDeparture().toString() + "), TotalDuration(" + flight.getFlightDuration() + "), TotalCoachPrice(" + flight.getSeating().getCoachPrice() +"), TotalFirstClassPrice(" + flight.getSeating().getFirstClassPrice() + ")");
                }

                return InputReader.readFlightSelectionOrOptions(searchFlightsResult, oneConnection, twoConnections, threeConnections);
            } else {
                System.out.println("Invalid option.");
                return InputReader.readFlightSelectionOrOptions(searchFlightsResult, oneConnection, twoConnections, threeConnections);
            }
        } else {
            System.out.println("You picked flights number: " + input);
            List<Flight> selectedFlights = searchFlightsResult.get(Integer.parseInt(input));
            return selectedFlights;
        }
    }

    public static String readSeating() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static boolean readNY() {
        Scanner scanner = new Scanner(System.in);
        String ny = scanner.nextLine();
        if (ny.equalsIgnoreCase("Y")) {
            return true;
        } else {
            return false;
        }
    }
}
