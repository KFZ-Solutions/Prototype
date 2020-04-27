package dao;

import flight.Arrival;
import flight.Departure;
import flight.Flight;
import flight.Flights;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import seat.SeatClass;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.Date;

public class DaoFlight {
    /**
     *     <Flights>
     *         <Flight Airplane="A320" FlightTime="167" Number="3774">
     *             <Departure>
     *                 <Code>BOS</Code>
     *                 <Time>2020 May 10 00:12 GMT</Time>
     *             </Departure>
     *             <Arrival>
     *                 <Code>MSP</Code>
     *                 <Time>2020 May 10 02:59 GMT</Time>
     *             </Arrival>
     *             <Seating>
     *                 <FirstClass Price="$1,053.50">8</FirstClass>
     *                 <Coach Price="$101.95">100</Coach>
     *             </Seating>
     *         </Flight>
     *         <Flight Airplane="A380" FlightTime="98" Number="3775"><Departure><Code>BOS</Code><Time>2020 May 10 00:31 GMT</Time></Departure><Arrival><Code>RDU</Code><Time>2020 May 10 02:09 GMT</Time></Arrival><Seating><FirstClass Price="$58.10">43</FirstClass><Coach Price="$17.42">403</Coach></Seating></Flight><Flight Airplane="767" FlightTime="97" Number="3776"><Departure><Code>BOS</Code><Time>2020 May 10 01:23 GMT</Time></Departure><Arrival><Code>CVG</Code><Time>2020 May 10 03:00 GMT</Time></Arrival><Seating><FirstClass Price="$70.33">63</FirstClass><Coach Price="$36.57">105</Coach></Seating></Flight><Flight Airplane="717" FlightTime="76" Number="3777"><Departure><Code>BOS</Code><Time>2020 May 10 02:10 GMT</Time></Departure><Arrival><Code>PIT</Code><Time>2020 May 10 03:26 GMT</Time></Arrival><Seating><FirstClass Price="$240.97">21</FirstClass><Coach Price="$70.53">52</Coach></Seating></Flight><Flight Airplane="717" FlightTime="52" Number="3778"><Departure><Code>BOS</Code><Time>2020 May 10 02:26 GMT</Time></Departure><Arrival><Code>PHL</Code><Time>2020 May 10 03:18 GMT</Time></Arrival><Seating><FirstClass Price="$162.65">20</FirstClass><Coach Price="$47.60">57</Coach></Seating></Flight><Flight Airplane="777" FlightTime="86" Number="3779"><Departure><Code>BOS</Code><Time>2020 May 10 03:18 GMT</Time></Departure><Arrival><Code>DTW</Code><Time>2020 May 10 04:44 GMT</Time></Arrival><Seating><FirstClass Price="$82.31">35</FirstClass><Coach Price="$16.26">130</Coach></Seating></Flight><Flight Airplane="A310" FlightTime="187" Number="3780"><Departure><Code>BOS</Code><Time>2020 May 10 03:38 GMT</Time></Departure><Arrival><Code>MEM</Code><Time>2020 May 10 06:45 GMT</Time></Arrival><Seating><FirstClass Price="$590.41">20</FirstClass><Coach Price="$70.85">67</Coach></Seating></Flight><Flight Airplane="A380" FlightTime="34" Number="3781"><Departure><Code>BOS</Code><Time>2020 May 10 04:23 GMT</Time></Departure><Arrival><Code>PHL</Code><Time>2020 May 10 04:57 GMT</Time></Arrival><Seating><FirstClass Price="$20.08">4</FirstClass><Coach Price="$6.02">37</Coach></Seating></Flight><Flight Airplane="717" FlightTime="206" Number="3782"><Departure><Code>BOS</Code><Time>2020 May 10 04:59 GMT</Time></Departure><Arrival><Code>MEM</Code><Time>2020 May 10 08:25 GMT</Time></Arrival><Seating><FirstClass Price="$649.09">16</FirstClass><Coach Price="$189.98">14</Coach></Seating></Flight><Flight Airplane="737" FlightTime="68" Number="3783"><Departure><Code>BOS</Code><Time>2020 May 10 06:09 GMT</Time></Departure><Arrival><Code>CLE</Code><Time>2020 May 10 07:17 GMT</Time></Arrival><Seating><FirstClass Price="$183.22">7</FirstClass><Coach Price="$51.30">62</Coach></Seating></Flight><Flight Airplane="A340" FlightTime="193" Number="3784"><Departure><Code>BOS</Code><Time>2020 May 10 06:14 GMT</Time></Departure><Arrival><Code>TPA</Code><Time>2020 May 10 09:27 GMT</Time></Arrival><Seating><FirstClass Price="$456.10">8</FirstClass><Coach Price="$54.46">89</Coach></Seating></Flight><Flight Airplane="777" FlightTime="28" Number="3785"><Departure><Code>BOS</Code><Time>2020 May 10 06:33 GMT</Time></Departure><Arrival><Code>LGA</Code><Time>2020 May 10 07:01 GMT</Time></Arrival><Seating><FirstClass Price="$26.93">66</FirstClass><Coach Price="$5.32">91</Coach></Seating></Flight><Flight Airplane="A310" FlightTime="154" Number="3786"><Departure><Code>BOS</Code><Time>2020 May 10 07:05 GMT</Time></Departure><Arrival><Code>MSP</Code><Time>2020 May 10 09:39 GMT</Time></Arrival><Seating><FirstClass Price="$485.77">0</FirstClass><Coach Price="$58.29">68</Coach></Seating></Flight><Flight Airplane="A340" FlightTime="108" Number="3787"><Departure><Code>BOS</Code><Time>2020 May 10 07:32 GMT</Time></Departure><Arrival><Code>CMH</Code><Time>2020 May 10 09:20 GMT</Time></Arrival><Seating><FirstClass Price="$256.69">13</FirstClass><Coach Price="$30.65">137</Coach></Seating></Flight><Flight Airplane="717" FlightTime="171" Number="3788"><Departure><Code>BOS</Code><Time>2020 May 10 08:03 GMT</Time></Departure><Arrival><Code>MCO</Code><Time>2020 May 10 10:54 GMT</Time></Arrival><Seating><FirstClass Price="$540.63">13</FirstClass><Coach Price="$158.23">30</Coach></Seating></Flight><Flight Airplane="717" FlightTime="148" Number="3789"><Departure><Code>BOS</Code><Time>2020 May 10 08:19 GMT</Time></Departure><Arrival><Code>ATL</Code><Time>2020 May 10 10:47 GMT</Time></Arrival><Seating><FirstClass Price="$467.24">6</FirstClass><Coach Price="$136.75">9</Coach></Seating></Flight><Flight Airplane="747" FlightTime="140" Number="3790"><Departure><Code>BOS</Code><Time>2020 May 10 08:25 GMT</Time></Departure><Arrival><Code>MSP</Code><Time>2020 May 10 10:45 GMT</Time></Arrival><Seating><FirstClass Price="$85.78">111</FirstClass><Coach Price="$26.59">170</Coach></Seating></Flight><Flight Airplane="A320" FlightTime="184" Number="3791"><Departure><Code>BOS</Code><Time>2020 May 10 09:19 GMT</Time></Departure><Arrival><Code>MSP</Code><Time>2020 May 10 12:23 GMT</Time></Arrival><Seating><FirstClass Price="$1,163.18">1</FirstClass><Coach Price="$112.57">5</Coach></Seating></Flight><Flight Airplane="757" FlightTime="122" Number="3792"><Departure><Code>BOS</Code><Time>2020 May 10 10:00 GMT</Time></Departure><Arrival><Code>ATL</Code><Time>2020 May 10 12:02 GMT</Time></Arrival><Seating><FirstClass Price="$288.63">1</FirstClass><Coach Price="$54.33">73</Coach></Seating></Flight><Flight Airplane="747" FlightTime="131" Number="3793"><Departure><Code>BOS</Code><Time>2020 May 10 10:09 GMT</Time></Departure><Arrival><Code>MDW</Code><Time>2020 May 10 12:20 GMT</Time></Arrival><Seating><FirstClass Price="$80.22">80</FirstClass><Coach Price="$24.87">229</Coach></Seating></Flight><Flight Airplane="757" FlightTime="176" Number="3794"><Departure><Code>BOS</Code><Time>2020 May 10 10:25 GMT</Time></Departure><Arrival><Code>BNA</Code><Time>2020 May 10 13:21 GMT</Time></Arrival><Seating><FirstClass Price="$417.64">27</FirstClass><Coach Price="$78.62">108</Coach></Seating></Flight><Flight Airplane="A310" FlightTime="128" Number="3795"><Departure><Code>BOS</Code><Time>2020 May 10 11:06 GMT</Time></Departure><Arrival><Code>ORD</Code><Time>2020 May 10 13:14 GMT</Time></Arrival><Seating><FirstClass Price="$404.98">11</FirstClass><Coach Price="$48.60">144</Coach></Seating></Flight><Flight Airplane="767" FlightTime="169" Number="3796"><Departure><Code>BOS</Code><Time>2020 May 10 11:16 GMT</Time></Departure><Arrival><Code>ATL</Code><Time>2020 May 10 14:05 GMT</Time></Arrival><Seating><FirstClass Price="$122.91">87</FirstClass><Coach Price="$63.91">1</Coach></Seating></Flight><Flight Airplane="A380" FlightTime="61" Number="3797"><Departure><Code>BOS</Code><Time>2020 May 10 11:26 GMT</Time></Departure><Arrival><Code>DCA</Code><Time>2020 May 10 12:27 GMT</Time></Arrival><Seating><FirstClass Price="$35.87">36</FirstClass><Coach Price="$10.75">181</Coach></Seating></Flight><Flight Airplane="A340" FlightTime="54" Number="3798"><Departure><Code>BOS</Code><Time>2020 May 10 11:46 GMT</Time></Departure><Arrival><Code>PHL</Code><Time>2020 May 10 12:40 GMT</Time></Arrival><Seating><FirstClass Price="$128.70">25</FirstClass><Coach Price="$15.37">47</Coach></Seating></Flight><Flight Airplane="717" FlightTime="63" Number="3799"><Departure><Code>BOS</Code><Time>2020 May 10 12:57 GMT</Time></Departure><Arrival><Code>BWI</Code><Time>2020 May 10 14:00 GMT</Time></Arrival><Seating><FirstClass Price="$200.22">4</FirstClass><Coach Price="$58.60">9</Coach></Seating></Flight><Flight Airplane="777" FlightTime="144" Number="3800"><Departure><Code>BOS</Code><Time>2020 May 10 14:13 GMT</Time></Departure><Arrival><Code>TPA</Code><Time>2020 May 10 16:37 GMT</Time></Arrival><Seating><FirstClass Price="$137.72">10</FirstClass><Coach Price="$27.20">250</Coach></Seating></Flight><Flight Airplane="747" FlightTime="114" Number="3801"><Departure><Code>BOS</Code><Time>2020 May 10 14:23 GMT</Time></Departure><Arrival><Code>BNA</Code><Time>2020 May 10 16:17 GMT</Time></Arrival><Seating><FirstClass Price="$69.68">100</FirstClass><Coach Price="$21.60">80</Coach></Seating></Flight><Flight Airplane="757" FlightTime="109" Number="3802"><Departure><Code>BOS</Code><Time>2020 May 10 15:14 GMT</Time></Departure><Arrival><Code>CVG</Code><Time>2020 May 10 17:03 GMT</Time></Arrival><Seating><FirstClass Price="$257.29">15</FirstClass><Coach Price="$48.43">35</Coach></Seating></Flight><Flight Airplane="767" FlightTime="114" Number="3803"><Departure><Code>BOS</Code><Time>2020 May 10 15:40 GMT</Time></Departure><Arrival><Code>ORD</Code><Time>2020 May 10 17:34 GMT</Time></Arrival><Seating><FirstClass Price="$82.80">99</FirstClass><Coach Price="$43.06">46</Coach></Seating></Flight><Flight Airplane="747" FlightTime="121" Number="3804"><Departure><Code>BOS</Code><Time>2020 May 10 16:08 GMT</Time></Departure><Arrival><Code>CMH</Code><Time>2020 May 10 18:09 GMT</Time></Arrival><Seating><FirstClass Price="$74.00">38</FirstClass><Coach Price="$22.94">376</Coach></Seating></Flight><Flight Airplane="737" FlightTime="120" Number="3805"><Departure><Code>BOS</Code><Time>2020 May 10 16:35 GMT</Time></Departure><Arrival><Code>CMH</Code><Time>2020 May 10 18:35 GMT</Time></Arrival><Seating><FirstClass Price="$323.61">28</FirstClass><Coach Price="$90.61">81</Coach></Seating></Flight><Flight Airplane="747" FlightTime="29" Number="3806"><Departure><Code>BOS</Code><Time>2020 May 10 17:16 GMT</Time></Departure><Arrival><Code>LGA</Code><Time>2020 May 10 17:45 GMT</Time></Arrival><Seating><FirstClass Price="$17.94">49</FirstClass><Coach Price="$5.56">333</Coach></Seating></Flight><Flight Airplane="747" FlightTime="142" Number="3807"><Departure><Code>BOS</Code><Time>2020 May 10 17:57 GMT</Time></Departure><Arrival><Code>MSP</Code><Time>2020 May 10 20:19 GMT</Time></Arrival><Seating><FirstClass Price="$86.86">17</FirstClass><Coach Price="$26.93">177</Coach></Seating></Flight><Flight Airplane="777" FlightTime="90" Number="3808"><Departure><Code>BOS</Code><Time>2020 May 10 18:25 GMT</Time></Departure><Arrival><Code>RDU</Code><Time>2020 May 10 19:55 GMT</Time></Arrival><Seating><FirstClass Price="$86.72">55</FirstClass><Coach Price="$17.13">371</Coach></Seating></Flight><Flight Airplane="737" FlightTime="59" Number="3809"><Departure><Code>BOS</Code><Time>2020 May 10 18:30 GMT</Time></Departure><Arrival><Code>IAD</Code><Time>2020 May 10 19:29 GMT</Time></Arrival><Seating><FirstClass Price="$159.98">12</FirstClass><Coach Price="$44.80">59</Coach></Seating></Flight><Flight Airplane="A310" FlightTime="57" Number="3810"><Departure><Code>BOS</Code><Time>2020 May 10 19:21 GMT</Time></Departure><Arrival><Code>BWI</Code><Time>2020 May 10 20:18 GMT</Time></Arrival><Seating><FirstClass Price="$180.09">12</FirstClass><Coach Price="$21.61">153</Coach></Seating></Flight><Flight Airplane="777" FlightTime="102" Number="3811"><Departure><Code>BOS</Code><Time>2020 May 10 20:08 GMT</Time></Departure><Arrival><Code>DTW</Code><Time>2020 May 10 21:50 GMT</Time></Arrival><Seating><FirstClass Price="$98.11">53</FirstClass><Coach Price="$19.38">76</Coach></Seating></Flight><Flight Airplane="777" FlightTime="174" Number="3812"><Departure><Code>BOS</Code><Time>2020 May 10 20:25 GMT</Time></Departure><Arrival><Code>MEM</Code><Time>2020 May 10 23:19 GMT</Time></Arrival><Seating><FirstClass Price="$166.59">5</FirstClass><Coach Price="$32.90">15</Coach></Seating></Flight><Flight Airplane="A330" FlightTime="71" Number="3813"><Departure><Code>BOS</Code><Time>2020 May 10 20:33 GMT</Time></Departure><Arrival><Code>DCA</Code><Time>2020 May 10 21:44 GMT</Time></Arrival><Seating><FirstClass Price="$127.36">40</FirstClass><Coach Price="$14.26">108</Coach></Seating></Flight><Flight Airplane="777" FlightTime="39" Number="3814"><Departure><Code>BOS</Code><Time>2020 May 10 21:35 GMT</Time></Departure><Arrival><Code>PHL</Code><Time>2020 May 10 22:14 GMT</Time></Arrival><Seating><FirstClass Price="$37.12">63</FirstClass><Coach Price="$7.33">266</Coach></Seating></Flight><Flight Airplane="A330" FlightTime="114" Number="3815"><Departure><Code>BOS</Code><Time>2020 May 10 21:40 GMT</Time></Departure><Arrival><Code>RDU</Code><Time>2020 May 10 23:34 GMT</Time></Arrival><Seating><FirstClass Price="$205.03">10</FirstClass><Coach Price="$22.96">174</Coach></Seating></Flight><Flight Airplane="A320" FlightTime="154" Number="3816"><Departure><Code>BOS</Code><Time>2020 May 10 22:12 GMT</Time></Departure><Arrival><Code>FLL</Code><Time>2020 May 11 00:46 GMT</Time></Arrival><Seating><FirstClass Price="$969.53">1</FirstClass><Coach Price="$93.83">15</Coach></Seating></Flight><Flight Airplane="737" FlightTime="116" Number="3817"><Departure><Code>BOS</Code><Time>2020 May 10 22:31 GMT</Time></Departure><Arrival><Code>DTW</Code><Time>2020 May 11 00:27 GMT</Time></Arrival><Seating><FirstClass Price="$313.21">22</FirstClass><Coach Price="$87.70">93</Coach></Seating></Flight><Flight Airplane="A340" FlightTime="103" Number="3818"><Departure><Code>BOS</Code><Time>2020 May 10 22:39 GMT</Time></Departure><Arrival><Code>DTW</Code><Time>2020 May 11 00:22 GMT</Time></Arrival><Seating><FirstClass Price="$244.19">32</FirstClass><Coach Price="$29.16">259</Coach></Seating></Flight><Flight Airplane="777" FlightTime="215" Number="3819"><Departure><Code>BOS</Code><Time>2020 May 10 22:55 GMT</Time></Departure><Arrival><Code>MEM</Code><Time>2020 May 11 02:30 GMT</Time></Arrival><Seating><FirstClass Price="$205.87">6</FirstClass><Coach Price="$40.66">390</Coach></Seating></Flight><Flight Airplane="717" FlightTime="78" Number="3820"><Departure><Code>BOS</Code><Time>2020 May 10 23:10 GMT</Time></Departure><Arrival><Code>DCA</Code><Time>2020 May 11 00:28 GMT</Time></Arrival><Seating><FirstClass Price="$246.11">24</FirstClass><Coach Price="$72.03">10</Coach></Seating></Flight><Flight Airplane="A330" FlightTime="115" Number="3821"><Departure><Code>BOS</Code><Time>2020 May 10 23:25 GMT</Time></Departure><Arrival><Code>BNA</Code><Time>2020 May 11 01:20 GMT</Time></Arrival><Seating><FirstClass Price="$206.54">31</FirstClass><Coach Price="$23.13">38</Coach></Seating></Flight><Flight Airplane="A380" FlightTime="38" Number="3822"><Departure><Code>BOS</Code><Time>2020 May 10 23:43 GMT</Time></Departure><Arrival><Code>PHL</Code><Time>2020 May 11 00:21 GMT</Time></Arrival><Seating><FirstClass Price="$22.65">21</FirstClass><Coach Price="$6.79">258</Coach></Seating></Flight>
     *     </Flights>
     */

    public static Flights addAll (String xmlFlights) throws NullPointerException {
        Flights flights = new Flights();

        // Load the XML string into a DOM tree for ease of processing
        // then iterate over all nodes adding each airport to our collection
        Document docAirports = buildDomDoc (xmlFlights);
        NodeList nodesFlights = docAirports.getElementsByTagName("Flight");
        for (int i = 0; i < nodesFlights.getLength(); i++) {
            Element elementFlight = (Element) nodesFlights.item(i);
            Flight flight = buildFlight (elementFlight);

            flights.add(flight);
        }

        return flights;
    }

    static private Flight buildFlight (Node nodeFlight) {
//        Airplane airplane;
        String airplane; // need to replace with Airplane

        int flightDuration;
        int number;

        String departureCode;
        String departureDate;
        String arrivalCode;
        String arrivalDate;

        Departure departure;
        Arrival arrival;
        SeatClass seating;

        Element elementFlight = (Element) nodeFlight;
        airplane = elementFlight.getAttributeNode("Airplane").getValue();
        flightDuration = Integer.parseInt(elementFlight.getAttributeNode("FlightTime").getValue());
        number = Integer.parseInt(elementFlight.getAttributeNode("Number").getValue());

        departureCode = ((Element) nodeFlight).getElementsByTagName("Departure").item(0).getFirstChild().getTextContent();
        departureDate = ((Element) nodeFlight).getElementsByTagName("Departure").item(0).getChildNodes().item(1).getTextContent();

        arrivalCode = ((Element) nodeFlight).getElementsByTagName("Arrival").item(0).getFirstChild().getTextContent();
        arrivalDate = ((Element) nodeFlight).getElementsByTagName("Arrival").item(0).getChildNodes().item(1).getTextContent();

        System.out.println("---- Parsed values ----");
        System.out.println("Airplane: " + airplane);
        System.out.println("FlightTime: " + flightDuration);
        System.out.println("Number: " + number);
        System.out.println("Departure code: " + departureCode);
        System.out.println("Departure date: " + departureDate);
        System.out.println("Arrival code: " + arrivalCode);
        System.out.println("Arrival date: " + arrivalDate);
        System.out.println("-----------------------");



        return new Flight(); // stub
    }

    static private Date parseDateGMT(String original) {
        return new Date(); // stub
    }

    static private Document buildDomDoc (String xmlString) {
        /**
         * load the xml string into a DOM document and return the Document
         */
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            InputSource inputSource = new InputSource();
            inputSource.setCharacterStream(new StringReader(xmlString));

            return docBuilder.parse(inputSource);
        }
        catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Retrieve character data from an element if it exists
     *
     * @param e is the DOM Element to retrieve character data from
     * @return the character data as String [possibly empty String]
     */
    private static String getCharacterDataFromElement (Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "";
    }
}
