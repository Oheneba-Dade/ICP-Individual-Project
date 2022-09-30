import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Node {
	private Node parentNode;
	private String airportCode;
	private int stops;
	private String airlineCode;

	public Node(Node parentNode, String airportCode, int stops, String airlineCode) {
		this.parentNode = parentNode;
		this.airportCode = airportCode;
		this.stops = stops;
		this.airlineCode = airlineCode;
	}

	public Node getParentNode() {
		return parentNode;
	}

	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}

	public String getAirportCode() {
		return airportCode;
	}

	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}

	public int getStops() {
		return stops;
	}

	public void setStops(int stops) {
		this.stops = stops;
	}

	public String getAirlineCode() {
		return airlineCode;
	}

	public void setAirlineCode(String airlineCode) {
		this.airlineCode = airlineCode;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Node node = (Node) o;
		return stops == node.stops && Objects.equals(airportCode, node.airportCode) && Objects.equals(airlineCode, node.airlineCode);
	}

	@Override
	public int hashCode() {
		return Objects.hash(airportCode, stops, airlineCode);
	}

	/**
	 * This function returns an ArrayList of Strings that contains the path from the start node to the current node
	 *
	 * @return The solution path is being returned.
	 */
	public ArrayList<String> solutionPath() {
		ArrayList<String> airportCodes = new ArrayList<>();
		ArrayList<String> airlineCodes = new ArrayList<>();
		ArrayList<Integer> stops = new ArrayList<>();
		ArrayList<String> path = new ArrayList<>();
		Node tempNode = this;
		while (tempNode != null) {
			airportCodes.add(tempNode.getAirportCode());
			airlineCodes.add(tempNode.getAirlineCode());
			stops.add(tempNode.getStops());
			tempNode = tempNode.parentNode;
		}
		Collections.reverse(airportCodes);
		Collections.reverse(airlineCodes);
		for (int i = 0; i < airlineCodes.size() - 1; i++) {
			String statement = i + 1 + ". " + airlineCodes.get(i + 1) + " from " + airportCodes.get(i) + " to " + airportCodes.get(i + 1) + " " + stops.get(i) + " stops";

			path.add(statement);
		}
		return path;
	}


}
