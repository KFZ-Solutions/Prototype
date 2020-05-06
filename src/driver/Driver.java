/**
 * 
 */
package driver;

import java.text.SimpleDateFormat;
import java.util.*;

import airplane.Airplane;
import airplane.Airplanes;
import airport.Airport;
import airport.Airports;
import dao.ServerInterface;
import flight.Departure;
import flight.Flight;
import flight.Flights;
import trip.Trip;
import utils.InputReader;

/**
 * @author blake
 * @since 2016-02-24
 * @version 1.0
 *
 */
public class Driver {

	/**
	 * 1. Select a departure airport and arrival airport for their desired travel (90% done)
	 * 2. The user will be able to use the client software to
	 * reserve flights to travel one-way (from departure to destination)
	 * with potentially up to 2 layovers
	 */

	/**
	 * Entry point for CS509 sample code driver
	 * 
	 * This driver will retrieve the list of airports from the CS509 server and print the list 
	 * to the console sorted by 3 character airport code
	 * 
	 * @param args is the arguments passed to java vm in format of "CS509.sample teamName" where teamName is a valid team
	 */
	public static void main(String[] args) {
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
		for (Airplane airplane : airplanes) {
			System.out.println(airplane.toString());
		}

		// Read inputs for departure airport and date
		Trip trip = InputReader.readTrip(availableAirports);
		System.out.println("---- Trip details ----");
		System.out.println(trip.toString());

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
			Integer resultCount = 0;

			System.out.println("DIRECT:");
			for (int i=1; i<=oneConnection.size(); i++) {
				List<Flight> flights = oneConnection.get(i);
				searchFlightsResult.put(++resultCount, flights);
				System.out.println("Flight " + i + ":");
				for (Flight flight : flights) {
					System.out.println("\t" + flight.toString());
				}
			}

			System.out.println("TWO:");
			for (int i=1; i<=twoConnections.size(); i++) {
				List<Flight> flights = twoConnections.get(i);
				searchFlightsResult.put(++resultCount, flights);
				System.out.println("Flight " + i + ":");
				for (Flight flight : flights) {
					System.out.println("\t" + flight.toString());
				}
			}

			System.out.println("THREE:");
			for (int i=1; i<=threeConnections.size(); i++) {
				List<Flight> flights = threeConnections.get(i);
				searchFlightsResult.put(++resultCount, flights);
				System.out.println("Flight " + i + ":");
				for (Flight flight : flights) {
					System.out.println("\t" + flight.toString());
				}
			}

			List<Flight> selectedFlights = InputReader.readFlightSelection(searchFlightsResult);
			System.out.println("Your selected Flight is:");
			System.out.println(selectedFlights.toString());

		} else {
			System.out.println("No flights to " + trip.getArrivalAirportCode() + " found for date " + trip.getDepartureDate() + ".");
		}
	}
}
