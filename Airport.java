import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Airport {
	private String airportID;
	private String name;
	private String city;
	private String country;
	private String IATACode;
	private String ICAOCode;

	// HashMap with Key -> IATA Code and Value -> City+Country
	static HashMap<String, String> airportCodeToCityAndCountryMap = new HashMap<>();

	// HashMap with Key -> City+Country and Value -> A list of all IATACodes present in that City+Country
	static HashMap<String, ArrayList<String>> cityAirportsMap = new HashMap<>();

	public Airport(String airportID, String name, String city, String country, String IATACode, String ICAOCode) {
		this.airportID = airportID;
		this.name = name;
		this.city = city;
		this.country = country;
		this.IATACode = IATACode;
		this.ICAOCode = ICAOCode;
	}

	public String getAirportID() {
		return airportID;
	}

	public void setAirportID(String airportID) {
		this.airportID = airportID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getIATACode() {
		return IATACode;
	}

	public void setIATACode(String IATACode) {
		this.IATACode = IATACode;
	}

	public String getICAOCode() {
		return ICAOCode;
	}

	public void setICAOCode(String ICAOCode) {
		this.ICAOCode = ICAOCode;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Airport airport = (Airport) o;
		return Objects.equals(airportID, airport.airportID) && Objects.equals(name, airport.name) && Objects.equals(city, airport.city) && Objects.equals(country, airport.country) && Objects.equals(IATACode, airport.IATACode) && Objects.equals(ICAOCode, airport.ICAOCode);
	}

	@Override
	public int hashCode() {
		return Objects.hash(airportID, name, city, country, IATACode, ICAOCode);
	}

	@Override
	public String toString() {
		return "Airport{" + "airportID='" + airportID + '\'' + ", name='" + name + '\'' + ", city='" + city + '\'' + ", country='" + country + '\'' + ", IATACode='" + IATACode + '\'' + ", ICAOCode='" + ICAOCode + '\'' + '}';
	}
}
