package easytrip.model.repository;

import java.util.Collection;

import easytrip.model.Flight;
import easytrip.model.Passenger;

public interface FlightRepository {

	//Passengers: All the operations applied for Passenger type
	public Collection<Passenger> getAllPassengers();
	public Passenger getPassenger(String passengerId);
	public void addPassenger(Passenger p);
	public void updatePassenger(Passenger p);
	public void deletePassenger(String passengerId);
	
	//Flights: All the operations applied for Flight type
	public Collection<Flight> getAllFlights();
	public Flight getFlight(String flightId);
	public void addFlight(Flight f);
	public void updateFlight(Flight f);
	public void deleteFlight(String flightId);
	
	public Collection<Passenger> getAllPassengersFromAFlight(String flightId);
	public void addPassengerToAFlight(String flightId, String passengerId);
	public void removePassengerFromAFlight(String flightId, String passengerId); 

}
