/**
 * 
 */
package driver;

import java.text.SimpleDateFormat;
import java.util.Collections;

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
		for (Airport airport : airports) {
			 System.out.println(airport.toString());
		}

		// Try to get a list of airplanes
		Airplanes airplanes = ServerInterface.INSTANCE.getAirplanes(teamName);
		for (Airplane airplane : airplanes) {
			System.out.println(airplane.toString());
		}

		// Read inputs for departure airport and date
		Trip trip = InputReader.readTrip();
		System.out.println("---- Trip details ----");
		System.out.println(trip.toString());

		// Query the server for those inputs
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
		Flights departingFlights = ServerInterface.INSTANCE.getDepartingFlights(teamName, trip.getDepartingAirportCode(), sdf.format(trip.getDepartureDate()));
		if (departingFlights != null) {
			System.out.println("---- Flights matching your search ----");
			Flights connections = ServerInterface.INSTANCE.findConnections(teamName, trip.getDepartingAirportCode(), trip.getArrivalAirportCode(), sdf.format(trip.getDepartureDate()), departingFlights);
			if (connections != null) {
				System.out.println("---- ... Flight ----");
				for (int i = 0; i < connections.size(); i++) {
					System.out.println(i + ". Flight from " + connections.get(i).getDeparture().getAirportCode() + " to " + connections.get(i).getArrival().getAirportCode());
					System.out.println("\t Flight number: " + connections.get(i).getNumber());
					System.out.println("\t Duration: " + connections.get(i).getFlightDuration());
				}
				System.out.println("--------");

				System.out.println("Select the flight you would like to book: ");

			} else {
				System.out.println("No connecting flights to " + trip.getArrivalAirportCode() + " found for date " + trip.getDepartureDate() + ".");
			}
		} else {
			System.out.println("No flights to " + trip.getArrivalAirportCode() + " found for date " + trip.getDepartureDate() + ".");
		}
	}
}
