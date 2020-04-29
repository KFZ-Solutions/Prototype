package dao;

import airplane.Airplane;
import airplane.Airplanes;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DaoAirplane {

    /**
     * <Airplanes>
     *     <Airplane Manufacturer="Airbus" Model="A310">
     *          <FirstClassSeats>24</FirstClassSeats>
     *          <CoachSeats>200</CoachSeats>
     *     </Airplane>
     *     <Airplane>
     *         ...
     *     </Airplane>
     * </Airplanes>
     */

    /**
     *
     * @param xmlAirplanes
     * @return
     * @throws NullPointerException
     */
    public static Airplanes addAll (String xmlAirplanes) throws NullPointerException {
        Airplanes airplanes = new Airplanes();

        // Load the XML string into a DOM tree for ease of processing
        // then iterate over all nodes adding each airport to our collection
        Document docAirports = XMLFactory.buildDomDoc (xmlAirplanes);
        NodeList nodesFlights = docAirports.getElementsByTagName("Airplane");
        for (int i = 0; i < nodesFlights.getLength(); i++) {
            Element elementAirplane = (Element) nodesFlights.item(i);
            Airplane airplane = buildAirplane(elementAirplane);

            airplanes.add(airplane);
        }

        return airplanes; // stub
    }

    static private Airplane buildAirplane(Node nodeFlight) {

        String manufacturer;
        String model;

        int firstClassSeats;
        int coachSeats;

        Element elementFlight = (Element) nodeFlight;
        manufacturer = elementFlight.getAttributeNode("Manufacturer").getValue();
        model = elementFlight.getAttributeNode("Model").getValue();

        String firstClassSeatsTxt = ((Element) nodeFlight).getElementsByTagName("FirstClassSeats").item(0).getTextContent();
        String coachSeatsTxt = ((Element) nodeFlight).getElementsByTagName("CoachSeats").item(0).getTextContent();

        firstClassSeats = Integer.parseInt(firstClassSeatsTxt);
        coachSeats = Integer.parseInt(coachSeatsTxt);

//        System.out.println("---- Parsed values ----");
//        System.out.println("Manufacturer: " + manufacturer);
//        System.out.println("Model: " + model);
//        System.out.println("First class seats: " + firstClassSeatsTxt);
//        System.out.println("Coach seats: " + coachSeatsTxt);
//        System.out.println("-----------------------");

        return new Airplane(manufacturer, model, firstClassSeats, coachSeats);
    }
}
