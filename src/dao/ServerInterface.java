/**
 * 
 */
package dao;

import airplane.Airplane;
import airplane.Airplanes;
import airport.Airports;
import flight.Arrival;
import flight.Departure;
import flight.Flight;
import flight.Flights;
import trip.Trip;
import utils.QueryFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.*;


/**
 * This class provides an interface to the CS509 server. It provides sample methods to perform
 * HTTP GET and HTTP POSTS
 *   
 * @author blake
 * @version 1.1
 * @since 2016-02-24
 *
 */
public enum ServerInterface {
	INSTANCE;
	
	/** 
	 * mUrlBase is the Universal Resource Locator (web address) of the CS509 reservation server
	 */
	private final String mUrlBase = "http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem";

	/**
	 * Return a collection of all the airports from server
	 * 
	 * Retrieve the list of airports available to the specified teamName via HTTPGet of the server
	 * 
	 * @param teamName identifies the name of the team requesting the collection of airports
	 * @return collection of Airports from server or null if error.
	 */
	public Airports getAirports (String teamName) {

		URL url;
		HttpURLConnection connection;
		BufferedReader reader;
		String line;
		StringBuffer result = new StringBuffer();
		
		String xmlAirports;
		Airports airports;

		try {
			/**
			 * Create an HTTP connection to the server for a GET 
			 * QueryFactory provides the parameter annotations for the HTTP GET query string
			 */
			url = new URL(mUrlBase + QueryFactory.getAirports(teamName));
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", teamName);

			/**
			 * If response code of SUCCESS read the XML string returned
			 * line by line to build the full return string
			 */
			int responseCode = connection.getResponseCode();
			if (responseCode >= HttpURLConnection.HTTP_OK) {
				InputStream inputStream = connection.getInputStream();
				String encoding = connection.getContentEncoding();
				encoding = (encoding == null ? "UTF-8" : encoding);

				reader = new BufferedReader(new InputStreamReader(inputStream));
				while ((line = reader.readLine()) != null) {
					result.append(line);
				}
				reader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		xmlAirports = result.toString();
		airports = DaoAirport.addAll(xmlAirports);
		return airports;
		
	}


	/**
	 * This method will get the details of airplanes which includes available seating
	 */
	public Airplanes getAirplanes(String teamName) {
		URL url;
		HttpURLConnection connection;
		BufferedReader reader;
		String line;
		StringBuffer result = new StringBuffer();

		String xmlAirplanes;
		Airplanes airports;

		try {
			/**
			 * Create an HTTP connection to the server for a GET
			 * QueryFactory provides the parameter annotations for the HTTP GET query string
			 */
			url = new URL(mUrlBase + QueryFactory.getAirplanes(teamName));
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", teamName);

			/**
			 * If response code of SUCCESS read the XML string returned
			 * line by line to build the full return string
			 */
			int responseCode = connection.getResponseCode();
			if (responseCode >= HttpURLConnection.HTTP_OK) {
				InputStream inputStream = connection.getInputStream();
				String encoding = connection.getContentEncoding();
				encoding = (encoding == null ? "UTF-8" : encoding);

				reader = new BufferedReader(new InputStreamReader(inputStream));
				while ((line = reader.readLine()) != null) {
					result.append(line);
				}
				reader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		xmlAirplanes = result.toString();
		airports = DaoAirplane.addAll(xmlAirplanes);
		return airports;
	}

	public Flights getDepartingFlights (String teamName, String airportCode, String day) {
		URL url;
		HttpURLConnection connection;
		BufferedReader reader;
		String line;
		StringBuffer result = new StringBuffer();

		String xmlFlights;
		Flights flights = null;

		try {
			/**
			 * Create an HTTP connection to the server for a GET
			 * QueryFactory provides the parameter annotations for the HTTP GET query string
			 */
			url = new URL(mUrlBase + QueryFactory.getDepartingFlights(teamName, airportCode, day));
//			System.out.println("---- Getting departing flights for: " + airportCode + " ----");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", teamName);

			/**
			 * If response code of SUCCESS read the XML string returned
			 * line by line to build the full return string
			 */
			int responseCode = connection.getResponseCode();
			if (responseCode >= HttpURLConnection.HTTP_OK) {
				InputStream inputStream = connection.getInputStream();
				String encoding = connection.getContentEncoding();
				encoding = (encoding == null ? "UTF-8" : encoding);

				reader = new BufferedReader(new InputStreamReader(inputStream));
				while ((line = reader.readLine()) != null) {
					result.append(line);
				}
				reader.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		xmlFlights = result.toString();
		try {
			flights = DaoFlight.addAll(xmlFlights);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return flights;
	}

	/**
	 * This method will take departure and arrival and find the connecting flights between them
	 * @param teamName
	 * @param departureAirportCode
	 * @param arrivalAirportCode
	 */
	public void findConnections(String teamName, String departureAirportCode, String arrivalAirportCode, String departureDate, Flights firstLevelDepartingFlights) {
		// .get(arrivalAirportCode) -> flight
		Map<String, List<Flight>> firstConnectionMap = new HashMap<>();
		Map<String, List<Flight>> secondConnectionMap = new HashMap<>();
		Map<String, List<Flight>> thirdConnectionMap = new HashMap<>();

		for (Flight firstLevelDepartingFlight : firstLevelDepartingFlights) {
			if (firstConnectionMap.get(firstLevelDepartingFlight.getArrival().getAirportCode()) == null) {
				// Instantiate a new list and add the current flight
				List<Flight> tmp = new ArrayList<>();
				tmp.add(firstLevelDepartingFlight);
				firstConnectionMap.put(firstLevelDepartingFlight.getArrival().getAirportCode(), tmp);
				// Add 2nd connecting flight
				Flights secondLevelDepartingFlights = ServerInterface.INSTANCE.getDepartingFlights(teamName, firstLevelDepartingFlight.getArrival().getAirportCode(), departureDate);
				if (secondLevelDepartingFlights != null) {
					for (Flight secondLevelDepartingFlight : secondLevelDepartingFlights) {
						List<Flight> tmpSecondLevelFlights = secondConnectionMap.get(secondLevelDepartingFlight.getArrival().getAirportCode());
						if (tmpSecondLevelFlights == null) {
							tmpSecondLevelFlights = new ArrayList<>();
							tmpSecondLevelFlights.add(secondLevelDepartingFlight);
						} else {
							boolean exists = false;
							for (Flight flight : tmpSecondLevelFlights) {
								if (flight.getNumber() == secondLevelDepartingFlight.getNumber()) {
									exists = true;
									break;
								}
							}
							if (!exists) {
								tmpSecondLevelFlights.add(secondLevelDepartingFlight);
							}
						}
						secondConnectionMap.put(secondLevelDepartingFlight.getArrival().getAirportCode(), tmpSecondLevelFlights);
						// Add 3rd connecting flight
						List<Flight> thirdLevelDepartingFlights = ServerInterface.INSTANCE.getDepartingFlights(teamName, secondLevelDepartingFlight.getArrival().getAirportCode(), departureDate);
						if (thirdLevelDepartingFlights != null) {
							for (Flight thirdLevelDepartingFlight : thirdLevelDepartingFlights) {
								List<Flight> tmpThirdLevelFlights = thirdConnectionMap.get(thirdLevelDepartingFlight.getArrival().getAirportCode());
								if (tmpThirdLevelFlights == null) {
									tmpThirdLevelFlights = new ArrayList<>();
									tmpThirdLevelFlights.add(thirdLevelDepartingFlight);
								} else {
									boolean exists = false;
									for (Flight flight : tmpThirdLevelFlights) {
										if (flight.getNumber() == thirdLevelDepartingFlight.getNumber()) {
											exists = true;
											break;
										}
									}
									if (!exists) {
										tmpThirdLevelFlights.add(thirdLevelDepartingFlight);
									}
								}
								thirdConnectionMap.put(thirdLevelDepartingFlight.getArrival().getAirportCode(), tmpThirdLevelFlights);
							}
						}
					}
				}
			} else {
				// Check the existing list for the current flight
				boolean exists = false;
				List<Flight> tmpFlights = firstConnectionMap.get(firstLevelDepartingFlight.getArrival().getAirportCode());
				for (Flight flight : tmpFlights) {
					// Avoid storing duplicate flights
					if (flight.getNumber() == firstLevelDepartingFlight.getNumber()) {
						exists = true;
						break;
					}
				}
				if (!exists) {
					tmpFlights.add(firstLevelDepartingFlight);
				}
				firstConnectionMap.put(firstLevelDepartingFlight.getArrival().getAirportCode(), tmpFlights);
			}
		}

		List<Flight> oneConnectionFlights = firstConnectionMap.get(arrivalAirportCode);
		List<Flight> twoConnectionFlights = secondConnectionMap.get(arrivalAirportCode);
		List<Flight> threeConnectionFlights = thirdConnectionMap.get(arrivalAirportCode);
		System.out.println(oneConnectionFlights);
		System.out.println(twoConnectionFlights);
		System.out.println(threeConnectionFlights);
		System.out.println("Second connection flights:");
		if (twoConnectionFlights != null) {
			for (Flight flight : twoConnectionFlights) {
				List<Flight> flights = firstConnectionMap.get(flight.getArrival().getAirportCode());
				if (flights != null) {
					for (Flight conn : flights) {
						System.out.println("From: " + conn.getDeparture().getAirportCode() + " departs on " + conn.getDeparture().toString() + " arrives at " + conn.getArrival().toString());
					}
				}
			}
		}
		System.out.println("Third connection flights:");
		if (twoConnectionFlights != null) {
			for (Flight flight : twoConnectionFlights) {
				List<Flight> secondFlights = secondConnectionMap.get(flight.getArrival().getAirportCode());
				if (secondFlights != null) {
					for (Flight connSecond : secondFlights) {
						String airportCode = connSecond.getDeparture().getAirportCode();
						List<Flight> firstFlights = firstConnectionMap.get(airportCode);
						System.out.println("From: " + connSecond + " which may come from: ");
						if (firstFlights != null) {
							for (Flight connFirst : firstFlights) {
								System.out.println("\t " + connFirst.getDeparture().getAirportCode() + " departs on " + connFirst.getDeparture().toString() + " arrives at " + connFirst.getArrival().toString());
							}
						}
					}
				}
			}
		}

//		// Get departing flights for departureAirportCode
//		List<Flights> flightsList = new ArrayList<>();
//		Flights flights = new Flights();
//		for (Flight firstFlight : firstLevelDepartingFlights) {
//			String airportCode = firstFlight.getArrival().getAirportCode();
//			if (arrivalAirportCode.equals(airportCode)) {
//				// We found the connection!
//				flights.add(firstFlight);
//				System.out.println("Connection found from " + departureAirportCode + " to " + airportCode);
//			}
//		}
//		flightsList.add(flights);
//		flights = new Flights();
//		// Check departing flights for each of the airports returned
//		Flights secondLevelConnectionFlights = null;
//		for (Flight firstFlight : firstLevelDepartingFlights) {
//			String check = firstFlight.getArrival().getAirportCode();
//			secondLevelConnectionFlights = ServerInterface.INSTANCE.getDepartingFlights(teamName, check, departureDate);
//			for (Flight secondFlight : Objects.requireNonNull(secondLevelConnectionFlights)) {
//				String airportCode = secondFlight.getArrival().getAirportCode();
//				if (airportCode.equals(arrivalAirportCode)) {
//					// We found the connection!
//					flights.add(firstFlight);
//					flights.add(secondFlight);
//					System.out.println("Connection found: " + airportCode);
//				}
//			}
//		}
//		flightsList.add(flights);
//		flights = new Flights();
//		// Check departing flights for each of the airports returned from our search for second level flights
//		for (Flight secondFlight : Objects.requireNonNull(secondLevelConnectionFlights)) {
//			String check = secondFlight.getArrival().getAirportCode();
//			Flights thirdLevelConnectionFlights = ServerInterface.INSTANCE.getDepartingFlights(teamName, check, departureDate);
//			for (Flight thirdFlight : Objects.requireNonNull(thirdLevelConnectionFlights)) {
//				String airportCode = thirdFlight.getArrival().getAirportCode();
//				if (airportCode.equals(arrivalAirportCode)) {
//					// We found the connection!
////					flights.add(firstFlight);
//					flights.add(secondFlight);
//					flights.add(thirdFlight);
//				}
//			}
//		}
//		return flightsList;
	}

	/**
	 * This method will reserve a seat based on the booking object provided
	 * @param teamName
	 * @param booking
	 */
	public void reserveSeat(String teamName, Trip booking) {

	}
	
	/**
	 * Lock the database for updating by the specified team. The operation will fail if the lock is held by another team.
	 * 
	 * @post database locked
	 * 
	 * @param teamName is the name of team requesting server lock
	 * @return true if the server was locked successfully, else false
	 */
	public boolean lock (String teamName) {
		URL url;
		HttpURLConnection connection;

		try {
			url = new URL(mUrlBase);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("User-Agent", teamName);
			connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			
			String params = QueryFactory.lock(teamName);
			
			connection.setDoOutput(true);
			DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
			writer.writeBytes(params);
			writer.flush();
			writer.close();
			
			int responseCode = connection.getResponseCode();
			System.out.println("\nSending 'POST' to lock database");
			System.out.println(("\nResponse Code : " + responseCode));
			
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			StringBuffer response = new StringBuffer();
			
			while ((line = in.readLine()) != null) {
				response.append(line);
			}
			in.close();
			
			System.out.println(response.toString());
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Unlock the database previous locked by specified team. The operation will succeed if the server lock is held by the specified
	 * team or if the server is not currently locked. If the lock is held be another team, the operation will fail.
	 * 
	 * The server interface to unlock the server interface uses HTTP POST protocol
	 * 
	 * @post database unlocked if specified teamName was previously holding lock
	 * 
	 * @param teamName is the name of the team holding the lock
	 * @return true if the server was successfully unlocked.
	 */
	public boolean unlock (String teamName) {
		URL url;
		HttpURLConnection connection;
		
		try {
			url = new URL(mUrlBase);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			
			String params = QueryFactory.unlock(teamName);
			
			connection.setDoOutput(true);
			connection.setDoInput(true);
			
			DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
			writer.writeBytes(params);
			writer.flush();
			writer.close();
		    
			int responseCode = connection.getResponseCode();
			System.out.println("\nSending 'POST' to unlock database");
			System.out.println(("\nResponse Code : " + responseCode));

			if (responseCode >= HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line;
				StringBuffer response = new StringBuffer();

				while ((line = in.readLine()) != null) {
					response.append(line);
				}
				in.close();

				System.out.println(response.toString());
			}
		}
		catch (IOException ex) {
			ex.printStackTrace();
			return false;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}
}
