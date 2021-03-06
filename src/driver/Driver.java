/**
 * 
 */
package driver;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import airplane.Airplane;
import airplane.Airplanes;
import airport.Airport;
import airport.Airports;
import dao.ServerInterface;
import flight.Flight;
import flight.Flights;
import trip.Trip;
import utils.FlightUtils;
import utils.InputReader;

/**
 * @author blake
 * @since 2016-02-24
 * @version 1.0
 *
 */
public class Driver {

	/**
	 * Entry point for CS509 sample code driver
	 * 
	 * This driver will:
	 * 1. Select a departure airport and arrival airport for their desired travel
	 * 2. The user will be able to use the client software to
	 * reserve flights to travel one-way (from departure to destination)
	 * with potentially up to 2 layovers
	 * 
	 * @param args is the arguments passed to java vm in format of "CS509.sample teamName" where teamName is a valid team
	 */
	public static void main(String[] args) throws ParseException {
		if (args.length != 1) {
			System.err.println("usage: CS509.sample teamName");
			System.exit(-1);
			return;
		}
		
		String teamName = args[0];
		// Try to get a list of airports
		Airports airports = ServerInterface.INSTANCE.getAirports(teamName);
		Collections.sort(airports);
		ArrayList<String> availableAirports = new ArrayList<String>();
		for (Airport airport : airports) {
			 String airportString = airport.toString();
			 System.out.println(airportString);
			 availableAirports.add(airportString.substring(0, 3));
		}

		// Try to get a list of airplanes
		Airplanes airplanes = ServerInterface.INSTANCE.getAirplanes(teamName);
		ServerInterface.INSTANCE.setAirplanesAvailable(airplanes);
		for (Airplane airplane : airplanes) {
			System.out.println(airplane.toString());
		}

		// Read inputs for departure airport and date
		Trip trip = InputReader.readTrip(availableAirports);
		System.out.println("---- Trip details ----");
		System.out.println(trip.toString());

		// Timeless response
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			public void run() {
				System.out.println("Loading results! Please wait.");
			}
		}, 3000);

		// Query the server for those inputs
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
		Flights departingFlights = ServerInterface.INSTANCE.getDepartingFlights(teamName, trip.getDepartingAirportCode(), sdf.format(trip.getDepartureDate()));
		if (departingFlights != null) {
			System.out.println("---- Flights matching your search ----");
			Map<String, Map<Integer, List<Flight>>> mainFlightMap = ServerInterface.INSTANCE.findConnections(teamName, trip.getDepartingAirportCode(), trip.getArrivalAirportCode(), sdf.format(trip.getDepartureDate()), departingFlights);
			Map<Integer, List<Flight>> oneConnection = mainFlightMap.get("oneConnection");
			System.out.println("There are a total of " + oneConnection.size() + " direct flights.");
			Map<Integer, List<Flight>> twoConnections = mainFlightMap.get("twoConnections");
			System.out.println("There are a total of " + twoConnections.size() + " two-connection flights.");
			Map<Integer, List<Flight>> threeConnections = mainFlightMap.get("threeConnections");
			System.out.println("There are a total of " + threeConnections.size() + " three-connection flights.");
			// Loop through each map (of the 3) and output the key of each entry followed by the entry values.

			Map<Integer, List<Flight>> searchFlightsResult = new HashMap<>();
			int resultCount = 0;

			System.out.println("Direct flights to " + trip.getArrivalAirportCode() + ":");
			for (int i=1; i<=oneConnection.size(); i++) {
				List<Flight> flights = oneConnection.get(i);
				if (!FlightUtils.flightsHasAvailableSeat(flights)) {
					continue;
				}
				for (Flight flight : flights) {
					FlightUtils.convertFlightTime(flight, airports);
					System.out.println("\t" + flight.toString());
				}
				searchFlightsResult.put(++resultCount, flights);
				System.out.println("Flight " + resultCount + ":");
			}

			System.out.println("Two-connection flights to " + trip.getArrivalAirportCode() + ":");
			for (int i=1; i<=twoConnections.size(); i++) {
				List<Flight> flights = twoConnections.get(i);
				if (!FlightUtils.flightsHasAvailableSeat(flights)) {
					continue;
				}
				searchFlightsResult.put(++resultCount, flights);
				System.out.println("Flight " + resultCount + ":");
				for (Flight flight : flights) {
					FlightUtils.convertFlightTime(flight, airports);
					System.out.println("\t" + flight.toString());
				}
			}

			System.out.println("Three-connection flights to " + trip.getArrivalAirportCode() + ":");
			for (int i=1; i<=threeConnections.size(); i++) {
				List<Flight> flights = threeConnections.get(i);
				if (!FlightUtils.flightsHasAvailableSeat(flights)) {
					continue;
				}
				searchFlightsResult.put(++resultCount, flights);
				System.out.println("Flight " + resultCount + ":");
				for (Flight flight : flights) {
					FlightUtils.convertFlightTime(flight, airports);
					System.out.println("\t" + flight.toString());
				}
			}

			List<Flight> selectedFlights = InputReader.readFlightSelectionOrOptions(searchFlightsResult, oneConnection, twoConnections, threeConnections);
			System.out.println("Your selected Flight is:");
			System.out.println(selectedFlights.toString());

			// Seating
			NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
			for (Flight selected : selectedFlights) {
				System.out.print("["+ selected.getSeating().getTotalCoach() +"/" + selected.getAirplane().getCoachSeats() + " reserved] Coach seating ticket price for flight " + selected.getNumber() + ": ");
				System.out.print(currencyFormatter.format(selected.getSeating().getCoachPrice()));
				System.out.println();
				System.out.print("["+ selected.getSeating().getTotalFirstClass() +"/" + selected.getAirplane().getFirstClassSeats() + " reserved] First class ticket price for flight " + selected.getNumber() + ": ");
				System.out.print(currencyFormatter.format(selected.getSeating().getFirstClassPrice()));
				System.out.println();
			}

			System.out.println();
			boolean reservedSeat = false;
			for (Flight selected : selectedFlights) {
				System.out.println("Please select seating for flight " + selected.getNumber() + ": ");
				String seating = InputReader.readSeating();
				String selectedSeating = seating.toLowerCase();
				if (selectedSeating.equals("coach")) {
					if (selected.getSeating().getTotalCoach() < selected.getAirplane().getCoachSeats()) {
						reservedSeat = ServerInterface.INSTANCE.reserveSeat(teamName, selected, "Coach");
					} else {
						System.out.println("Not enough seats available. Do you want to reserve a first class seat instead ('Y' or 'N')? ");
						boolean yn = InputReader.readNY();
						if (yn) {
							// Book first class
							reservedSeat = ServerInterface.INSTANCE.reserveSeat(teamName, selected, "FirstClass");
						}
					}
				} else {
					if (selected.getSeating().getTotalFirstClass() < selected.getAirplane().getFirstClassSeats()) {
						reservedSeat = ServerInterface.INSTANCE.reserveSeat(teamName, selected, "FirstClass");
					} else {
						System.out.println("Not enough seats available. Do you want to reserve a coach seat instead ('Y' or 'N')? ");
						boolean yn = InputReader.readNY();
						if (yn) {
							reservedSeat = ServerInterface.INSTANCE.reserveSeat(teamName, selected, "Coach");
						}
					}
				}
			}
			if (reservedSeat) {
				System.out.println("Seat reserved!");
				System.out.println("Searching for return flight:");
				if (!trip.isOneWay()) {
					Date returnDate = trip.getReturnDate();
					Flights departingFlightsReturn = ServerInterface.INSTANCE.getDepartingFlights(teamName, trip.getArrivalAirportCode(), sdf.format(returnDate));
					if (departingFlightsReturn != null) {
						System.out.println("---- Return flights matching your search ----");
						Map<String, Map<Integer, List<Flight>>> mainReturnFlightMap = ServerInterface.INSTANCE.findConnections(teamName, trip.getArrivalAirportCode(), trip.getDepartingAirportCode(), sdf.format(returnDate), departingFlightsReturn);
						Map<Integer, List<Flight>> oneConnectionReturn = mainReturnFlightMap.get("oneConnection");
						System.out.println("There are a total of " + oneConnectionReturn.size() + " direct returning flights.");
						Map<Integer, List<Flight>> twoConnectionsReturn = mainReturnFlightMap.get("twoConnections");
						System.out.println("There are a total of " + twoConnectionsReturn.size() + " two-connection returning flights.");
						Map<Integer, List<Flight>> threeConnectionsReturn = mainReturnFlightMap.get("threeConnections");
						System.out.println("There are a total of " + threeConnectionsReturn.size() + " three-connection returning flights.");

						Map<Integer, List<Flight>> searchReturnFlightsResult = new HashMap<>();
						int returnResultCount = 0;

						System.out.println("Direct returning flights to " + trip.getDepartingAirportCode() + ":");
						for (int i=1; i<=oneConnectionReturn.size(); i++) {
							List<Flight> flights = oneConnectionReturn.get(i);
							if (!FlightUtils.flightsHasAvailableSeat(flights)) {
								continue;
							}
							searchReturnFlightsResult.put(++returnResultCount, flights);
							System.out.println("Flight " + returnResultCount + ":");
							for (Flight flight : flights) {
								FlightUtils.convertFlightTime(flight, airports);
								System.out.println("\t" + flight.toString());
							}
						}

						System.out.println("Two-connection returning flights to " + trip.getDepartingAirportCode() + ":");
						for (int i=1; i<=twoConnectionsReturn.size(); i++) {
							List<Flight> flights = twoConnectionsReturn.get(i);
							if (!FlightUtils.flightsHasAvailableSeat(flights)) {
								continue;
							}
							searchReturnFlightsResult.put(++returnResultCount, flights);
							System.out.println("Flight " + returnResultCount + ":");
							for (Flight flight : flights) {
								FlightUtils.convertFlightTime(flight, airports);
								System.out.println("\t" + flight.toString());
							}
						}

						System.out.println("Three-connection returning flights to " + trip.getDepartingAirportCode() + ":");
						for (int i=1; i<=threeConnectionsReturn.size(); i++) {
							List<Flight> flights = threeConnectionsReturn.get(i);
							if (!FlightUtils.flightsHasAvailableSeat(flights)) {
								continue;
							}
							searchReturnFlightsResult.put(++returnResultCount, flights);
							System.out.println("Flight " + returnResultCount + ":");
							for (Flight flight : flights) {
								FlightUtils.convertFlightTime(flight, airports);
								System.out.println("\t" + flight.toString());
							}
						}

						List<Flight> selectedReturnFlights = InputReader.readFlightSelectionOrOptions(searchReturnFlightsResult, oneConnectionReturn, twoConnectionsReturn, threeConnectionsReturn);
						System.out.println("Your selected returning Flight is:");
						System.out.println(selectedReturnFlights.toString());

						// Seating
						for (Flight selected : selectedReturnFlights) {
							System.out.print("["+ selected.getSeating().getTotalCoach() +"/" + selected.getAirplane().getCoachSeats() + " reserved] Coach seating ticket price for flight " + selected.getNumber() + ": ");
							System.out.print(currencyFormatter.format(selected.getSeating().getCoachPrice()));
							System.out.println();
							System.out.print("["+ selected.getSeating().getTotalFirstClass() +"/" + selected.getAirplane().getFirstClassSeats() + " reserved] First class ticket price for flight " + selected.getNumber() + ": ");
							System.out.print(currencyFormatter.format(selected.getSeating().getFirstClassPrice()));
							System.out.println();
						}

						System.out.println();
						boolean reservedReturningSeat = false;
						for (Flight selected : selectedReturnFlights) {
							System.out.println("Please select seating for returning flight " + selected.getNumber() + ": ");
							String seating = InputReader.readSeating();
							String selectedSeating = seating.toLowerCase();
							if (selectedSeating.equals("coach")) {
								if (selected.getSeating().getTotalCoach() < selected.getAirplane().getCoachSeats()) {
									reservedReturningSeat = ServerInterface.INSTANCE.reserveSeat(teamName, selected, "Coach");
								} else {
									System.out.println("Not enough seats available. Do you want to reserve a first class seat instead ('Y' or 'N')? ");
									boolean yn = InputReader.readNY();
									if (yn) {
										// Book first class
										reservedReturningSeat = ServerInterface.INSTANCE.reserveSeat(teamName, selected, "FirstClass");
									}
								}
							} else {
								if (selected.getSeating().getTotalFirstClass() < selected.getAirplane().getFirstClassSeats()) {
									reservedReturningSeat = ServerInterface.INSTANCE.reserveSeat(teamName, selected, "FirstClass");
								} else {
									System.out.println("Not enough seats available. Do you want to reserve a coach seat instead ('Y' or 'N')? ");
									boolean yn = InputReader.readNY();
									if (yn) {
										reservedReturningSeat = ServerInterface.INSTANCE.reserveSeat(teamName, selected, "Coach");
									}
 								}
							}
						}

						if (reservedReturningSeat) {
							System.out.println("Seat reserved!");
						}
					}
				}
			}

		} else {
			System.out.println("No flights to " + trip.getArrivalAirportCode() + " found for date " + trip.getDepartureDate() + ".");
		}
	}
}
