/**
 * 
 */
package driver;

import java.util.Collections;

import airport.Airport;
import airport.Airports;
import dao.ServerInterface;
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
	 * 2.
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
			// System.out.println(airport.toString());
		}

		// Read inputs for departure airport and date
		Trip trip = InputReader.readTrip();
		System.out.println("---- Trip details ----");
		System.out.println(trip.toString());

		// Query the server for those inputs
//		ServerInterface.INSTANCE.getDepartingFlights(teamName, departingAirport, departingDate);
	}
}
