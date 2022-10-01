import java.io.*;
import java.util.*;

public class TestRoute {

    static BufferedReader routesReader;
    static BufferedReader airportsReader;

    /**
     * The method takes in a source and destination and performs a breadth-first search to return an
     * ArrayList of Strings that represent the path from the source to the destination
     *
     * @param source The source city and country
     * @param destination The destination city and country.
     * @return The method returns an ArrayList of Strings.
     */
    public static ArrayList<String> bfs(String source, String destination) {
        Queue<Node> frontier = new ArrayDeque<>();
        Set<String> exploredRoutes = new HashSet<>();
        ArrayList<String> airportCodes = Airport.cityAirportsMap.get(source);
        for (String airportCode : airportCodes) {
            frontier.add(new Node(null, airportCode, 0, null));
        }
        while (frontier.size() > 0) {
            Node node = frontier.remove();
            exploredRoutes.add(node.getAirportCode());
            ArrayList<Route> successorStates = Route.routesMap.get(node.getAirportCode());
            if (successorStates != null) {
                for (Route successorState : successorStates) {
                    Node child =
                            new Node(
                                    node,
                                    successorState.getDestinationAirportCode(),
                                    successorState.getStops(),
                                    successorState.getAirlineCode());
                    String destinationCityAndCountry =
                            Airport.airportCodeToCityAndCountryMap.get(child.getAirportCode());
                    if (!frontier.contains(child)
                            && !exploredRoutes.contains(child.getAirportCode())) {
                        if (destinationCityAndCountry != null) {
                            if (destinationCityAndCountry.equals(destination)) {
                                return child.solutionPath();
                            }
                        }
                        frontier.add(child);
                    }
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            routesReader = new BufferedReader(new FileReader("routes.csv"));
            airportsReader = new BufferedReader(new FileReader("airports.csv"));

            // reading airports file and populating the airports hashmap
            String airportLine = airportsReader.readLine();
            while (airportLine != null) {
                String[] airportInfo = airportLine.split(",");
                String airportCityAndCountry = airportInfo[2] + ", " + airportInfo[3];
                Airport.airportCodeToCityAndCountryMap.put(airportInfo[4], airportCityAndCountry);

                // Check if the key (city and country) already exists in the hashmap
                ArrayList<String> airportCodes = Airport.cityAirportsMap.get(airportCityAndCountry);
                if (airportCodes != null) {
                    airportCodes.add(airportInfo[4]);
                    Airport.cityAirportsMap.put(airportCityAndCountry, airportCodes);
                } else {
                    ArrayList<String> newAirportIDs = new ArrayList<>();
                    newAirportIDs.add(airportInfo[4]);
                    Airport.cityAirportsMap.put(airportCityAndCountry, newAirportIDs);
                }
                airportLine = airportsReader.readLine();
            }
            airportsReader.close();

            // reading routes file and populating the routes hashmap
            String routeLine = routesReader.readLine();
            while (routeLine != null) {
                String[] routeInfo = routeLine.split(",");
                Route route =
                        new Route(
                                routeInfo[0],
                                routeInfo[1],
                                routeInfo[4],
                                Integer.parseInt(routeInfo[7]));

                // Check if the key (sourceAirportCode) already exists in the hashmap
                ArrayList<Route> tempRoutesList = Route.routesMap.get(routeInfo[2]);
                if (tempRoutesList == null) {
                    ArrayList<Route> routesList = new ArrayList<>();
                    routesList.add(route);
                    Route.routesMap.put(routeInfo[2], routesList);
                } else {
                    tempRoutesList.add(route);
                    Route.routesMap.put(routeInfo[2], tempRoutesList);
                }
                routeLine = routesReader.readLine();
            }
            routesReader.close();

            // Testing out the application

            // use Stringbuilder to enable obtain filename to use for output filename
            StringBuilder inputFileName = new StringBuilder("sandane-bangkok.txt");
            BufferedReader inputFileReader =
                    new BufferedReader(new FileReader(String.valueOf(inputFileName)));
            StringBuilder outputFileName =
                    new StringBuilder(
                            inputFileName.delete(
                                    inputFileName.length() - 4, inputFileName.length()));
            outputFileName.append(".txt");

            File outputFile = new File(inputFileName + "_output.txt");
            PrintWriter pw = new PrintWriter(outputFile);
            String source = inputFileReader.readLine();
            String destination = inputFileReader.readLine();

            ArrayList<String> path = bfs(source, destination);
            System.out.println("Searching for flights...");
            int numberOfFlights = 0;
            if (path == null) {
                pw.write("Cannot find a series of flights to this location");

            } else {
                for (int i = 0; i < path.size() - 1; i++) {
                    pw.println(path.get(i));
                    numberOfFlights++;
                }
                pw.println("Total flights: " + numberOfFlights);
                String totalNumberOfStops = path.get(path.size() - 1);
                pw.println("Total additional stops: " + totalNumberOfStops);
                System.out.println("Flights found. File created.");
            }
            pw.close();
            inputFileReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
