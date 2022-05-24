package easytrip.model.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import easytrip.model.Flight;
import easytrip.model.Gender;
import easytrip.model.Passenger;

public class MapFlightRepository implements FlightRepository {

	//Attributes
	private static MapFlightRepository instance = null;
	public Map<String, Flight> flightMap;
	public Map<String, Passenger> passengerMap;
	
	public static MapFlightRepository getInstance() {
		if (instance == null) {
			instance = new MapFlightRepository();
			instance.init();
		}
		return instance;
	}
	
	public void init() {
		flightMap = new HashMap<String, Flight>();
		passengerMap = new HashMap<String, Passenger>();
		
		//Create passengers
		Passenger p1 = new Passenger("p1","Roberto", "García Martínez", Gender.WOMAN, 23);
		addPassenger(p1);
		Passenger p2 = new Passenger("p2","Lucía", "López Vladic", Gender.WOMAN, 33);
		addPassenger(p2);
		Passenger p3 = new Passenger("p3","Loren", "Hernández Quispe", Gender.OTHER, 40);
		addPassenger(p3);
		Passenger p4 = new Passenger("p4","Pepe", "Sánchez Diaz", Gender.MAN, 28);
		addPassenger(p4);
		Passenger p5 = new Passenger("p5","Alberto", "Moreno Romero", Gender.MAN, 33);
		addPassenger(p5);
		Passenger p6 = new Passenger("p6","María", "Giménez Ibáñez", Gender.WOMAN, 72);
		addPassenger(p6);
		Passenger p7 = new Passenger("p7","Miguel", "Ortega Castro", Gender.OTHER, 25);
		addPassenger(p7);
		Passenger p8 = new Passenger("p8","Juan", "Rubio Marín", Gender.MAN, 18);
		addPassenger(p8);
		Passenger p9 = new Passenger("p9","Pablo", "Iglesias Medina", Gender.MAN, 47);
		addPassenger(p9);
		Passenger p10 = new Passenger("p10","Paula", "Ortiz Zambrano", Gender.WOMAN, 52);
		addPassenger(p10);
		Passenger p11 = new Passenger("p11","Lucía", "Molina Blanzo", Gender.WOMAN, 36);
		addPassenger(p11);
		Passenger p12 = new Passenger("p12","Elena", "Aguilar Pascual", Gender.WOMAN, 66);
		addPassenger(p12);
		Passenger p13 = new Passenger("p13","María", "Duarte Herrera", Gender.WOMAN, 50);
		addPassenger(p13);
		Passenger p14 = new Passenger("p14","Paco", "Fuentes Peña", Gender.OTHER, 43);
		addPassenger(p14);
		Passenger p15 = new Passenger("p15","Gloria", "Nieto León", Gender.WOMAN, 33);
		addPassenger(p15);
		Passenger p16 = new Passenger("p16","Juan", "Casimiro Caleya", Gender.MAN, 32);
		addPassenger(p16);
		Passenger p17 = new Passenger("p17","Ana", "Muñoz Alonso", Gender.WOMAN, 22);
		addPassenger(p17);
		Passenger p18 = new Passenger("p18","Alicia", "González Álvarez", Gender.WOMAN, 27);
		addPassenger(p18);
		Passenger p19 = new Passenger("p19","Enrique", "Gutiérrez Navarro", Gender.MAN, 38);
		addPassenger(p19);
		Passenger p20 = new Passenger("p20","Fernando", "Torres Díaz", Gender.MAN, 22);
		addPassenger(p20);
		Passenger p21 = new Passenger("p21","Teresa", "Peña Campos", Gender.WOMAN, 19);
		addPassenger(p21);
		Passenger p22 = new Passenger("p22","Sergio", "Caballero Cabañas", Gender.MAN, 29);
		addPassenger(p22);
		Passenger p23 = new Passenger("p23","Celia", "Reyes Vega", Gender.WOMAN, 37);
		addPassenger(p23);
		Passenger p24 = new Passenger("p24","Francisco", "Zambrano Zambrano", Gender.MAN, 49);
		addPassenger(p24);
		Passenger p25 = new Passenger("p25","María", "Rico Ferrer", Gender.OTHER, 65);
		addPassenger(p25);
		Passenger p26 = new Passenger("p26","José Joaquín", "Conde Ortiz", Gender.MAN, 22);
		addPassenger(p26);
		Passenger p27 = new Passenger("p27","Luis", "Ramos Vázquez", Gender.MAN, 31);
		addPassenger(p27);
		Passenger p28 = new Passenger("p28","Sergio", "Serrano Alonso", Gender.MAN, 25);
		addPassenger(p28);
		Passenger p29 = new Passenger("p29","Daniela", "Suarez Delgado", Gender.WOMAN, 27);
		addPassenger(p29);
		Passenger p30 = new Passenger("p30","Jesús", "Ortiz Pecero", Gender.OTHER, 47);
		addPassenger(p30);
		
		//Create flights
		Flight f1 = new Flight("f1", "Madrid", "Barcelona", 1, 5, 130.2, "Iberia", "Airbus A330-300", 20);
		addFlight(f1);
		Flight f2 = new Flight("f2", "Sevilla", "Valencia", 2, 7, 250.12, "Vueling", "Airbus A320-214", 30);
		addFlight(f2);
		Flight f3 = new Flight("f3", "Madrid", "Lisboa", 12, 15, 350.5, "Plus Ultra", "Airbus A340-313", 30);
		addFlight(f3);
		Flight f4 = new Flight("f4", "Paris", "Roma", 23, 3, 125.6, "Air Europa", "Airbus A330-300", 25);
		addFlight(f4);
		Flight f5 = new Flight("f5", "Asturias", "Vigo", 1, 3, 420.1, "Iberia", "Airbus A330-300", 90);
		addFlight(f5);
		
		//Add passengers to flights
		addPassengerToAFlight(f1.getId(), p1.getId());
		addPassengerToAFlight(f1.getId(), p2.getId());
		addPassengerToAFlight(f1.getId(), p3.getId());
		addPassengerToAFlight(f1.getId(), p4.getId());
		addPassengerToAFlight(f1.getId(), p5.getId());
		addPassengerToAFlight(f1.getId(), p6.getId());
		addPassengerToAFlight(f1.getId(), p7.getId());
		addPassengerToAFlight(f1.getId(), p8.getId());
		addPassengerToAFlight(f1.getId(), p9.getId());
		addPassengerToAFlight(f1.getId(), p10.getId());
		addPassengerToAFlight(f1.getId(), p11.getId());
		addPassengerToAFlight(f1.getId(), p12.getId());
		addPassengerToAFlight(f2.getId(), p13.getId());
		addPassengerToAFlight(f2.getId(), p14.getId());
		addPassengerToAFlight(f2.getId(), p15.getId());
		addPassengerToAFlight(f2.getId(), p16.getId());
		addPassengerToAFlight(f2.getId(), p17.getId());
		addPassengerToAFlight(f2.getId(), p18.getId());
		addPassengerToAFlight(f2.getId(), p19.getId());
		addPassengerToAFlight(f3.getId(), p20.getId());
		addPassengerToAFlight(f3.getId(), p21.getId());
		addPassengerToAFlight(f3.getId(), p22.getId());
		addPassengerToAFlight(f3.getId(), p23.getId());
		addPassengerToAFlight(f3.getId(), p24.getId());
		addPassengerToAFlight(f3.getId(), p25.getId());
		addPassengerToAFlight(f4.getId(), p26.getId());
		addPassengerToAFlight(f4.getId(), p27.getId());
		addPassengerToAFlight(f4.getId(), p28.getId());
		addPassengerToAFlight(f5.getId(), p29.getId());
		addPassengerToAFlight(f5.getId(), p30.getId());
	}
	
	//Passengers: All the operations applied for Passenger type
	
	// Get all the passengers from the passengerMap
	@Override
	public Collection<Passenger> getAllPassengers() {
		return this.passengerMap.values();
	}

	// Get a specific passenger from the passengerMap
	@Override
	public Passenger getPassenger(String passengerId) {
		return this.passengerMap.get(passengerId);
	}

	// Add a specific passenger to the passengerMap
	@Override
	public void addPassenger(Passenger p) {
		passengerMap.put(p.getId(), p);
	}

	// Update the passenger on the passengerMap (the passenger is updated on the existing flights)
	@Override
	public void updatePassenger(Passenger p) {
		Passenger passenger = passengerMap.get(p.getId());
		passenger.setName(p.getName());
		passenger.setSurname(p.getSurname());
		passenger.setAge(p.getAge());
	}

	// Remove the passenger on the passengerMap (requires removing the passenger on the existing flights)
	@Override
	public void deletePassenger(String passengerId) {
		Passenger p = this.passengerMap.get(passengerId);
		passengerMap.remove(passengerId);
		
		// Remove existing passenger on the existing flights
		this.flightMap.entrySet().stream()
			.filter(x -> x.getValue().getPassengers().contains(p))
			.forEach(x -> x.getValue().deletePassenger(p.getId()));
	}

	//Flights: All the operations applied for Flight type
	
	// Get all the flights from the flightMap
	@Override
	public Collection<Flight> getAllFlights() {
		return this.flightMap.values();
	}

	// Get a specific flight from the flightMap
	@Override
	public Flight getFlight(String flightId) {
		return this.flightMap.get(flightId);
	}

	// Add a specific flight to the fligthMap
	@Override
	public void addFlight(Flight f) {
		flightMap.put(f.getId(), f);
	}

	// Update an existing flight on the flightMap
	@Override
	public void updateFlight(Flight f) {
		flightMap.replace(f.getId(), f);
	}

	// Remove an existing flight on the flightMap
	@Override
	public void deleteFlight(String flightId) {
		flightMap.remove(flightId);
	}

	// Get all the passenger from the specific flight on the flightMap
	@Override
	public Collection<Passenger> getAllPassengersFromAFlight(String flightId) {
		return flightMap.get(flightId).getPassengers();
	}

	// Add a specific passenger to the specific flight on the flightMap
	@Override
	public void addPassengerToAFlight(String flightId, String passengerId) {
		Flight flight = flightMap.get(flightId);
		Passenger passenger = passengerMap.get(passengerId);
		flight.addPassenger(passenger);
		flightMap.replace(flight.getId(), flight);
	}

	// Remove a specific passenger on the specific flight on the flightMap
	@Override
	public void removePassengerFromAFlight(String flightId, String passengerId) {
		Flight flight = flightMap.get(flightId);
		Passenger passenger = passengerMap.get(passengerId);
		flight.deletePassenger(passenger);
		flightMap.replace(flight.getId(), flight);
	}

}
