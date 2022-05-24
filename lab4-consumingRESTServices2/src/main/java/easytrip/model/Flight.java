package easytrip.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Flight implements Comparable<Flight>{

	// Attributes (Private)
	private String id;
	private String origin;
	private String destination;
	private Integer originDate;
	private Integer destinationDate;
	private Double price;
	private String airline;
	private String model;
	private Integer numMaxPassengers;
	private List<Passenger> passengers;
	
	public Flight() {}
	
	// Builder C1: Receive all the parameters and create a Flight object
	public Flight(String id, String origin, String destination, Integer originDate, Integer destinationDate, Double price, String airline, String model, Integer numMaxPassengers) {
		this.id = id;
		this.origin = origin;
		this.destination = destination;
		this.originDate = originDate;
		this.destinationDate = destinationDate;
		this.price = price;
		this.airline = airline;
		this.model = model;
		this.numMaxPassengers = numMaxPassengers;
		this.passengers = new ArrayList<>();
	}
	
	// Getters - Setters: Methods for all the attributes
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public Integer getOriginDate() {
		return originDate;
	}

	public void setOriginDate(Integer originDate) {
		this.originDate = originDate;
	}

	public Integer getDestinationDate() {
		return destinationDate;
	}

	public void setDestinationDate(Integer destinationDate) {
		this.destinationDate = destinationDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	

	public Integer getNumMaxPassengers() {
		return numMaxPassengers;
	}

	public void setNumMaxPassengers(Integer numMaxPassengers) {
		this.numMaxPassengers = numMaxPassengers;
	}

	// Getters - Setters: Methods for the list attribute
	public List<Passenger> getPassengers() {
		return this.passengers;
	}
	
	public Passenger getPassenger(String id) {
		return this.passengers.stream()
				.filter(p -> p.getId().equals(id))
				.findAny()
				.get();
	}
	
	public void addPassenger(Passenger p) {
		if (!this.passengers.contains(p)) {
			this.passengers.add(p);
		} else if (this.passengers == null) {
			this.passengers = new ArrayList<Passenger>();
			this.passengers.add(p);
		}
	}
	
	public void deletePassenger(Passenger p) {
		if (this.passengers.contains(p)) {
			this.passengers.remove(p);
		}
	}
	
	public void deletePassenger(String passengerId) {
		Passenger p = getPassenger(passengerId);
		if (this.passengers.contains(p)) {
			this.passengers.remove(p);
		}
			
	}

	// Equality criteria: All the flights are different from each others depending on the id
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flight other = (Flight) obj;
		return Objects.equals(id, other.id);
	}

	// Sorting criteria: All the flights are sorted depending on the id
	@Override
	public int compareTo(Flight f) {
		Integer a = Integer.parseInt(this.id.substring(1));
		Integer b = Integer.parseInt(f.getId().substring(1));
		return a.compareTo(b);
	}

	// String format: Each flight is represented with all the values
	@Override
	public String toString() {
		String list = this.passengers.stream()
				.map(Passenger::toString)
				.collect(Collectors.joining("\n - "));
		return String.format("%s, %s, %s, %d, %d, %f, %s, %s, %d :\n%s", id, origin, destination, originDate, destinationDate, price, airline, model, numMaxPassengers, list);
	}
	
}
