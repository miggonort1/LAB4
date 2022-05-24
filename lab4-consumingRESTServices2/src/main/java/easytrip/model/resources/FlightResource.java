package easytrip.model.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import easytrip.model.Flight;
import easytrip.model.Passenger;
import easytrip.model.repository.FlightRepository;
import easytrip.model.repository.MapFlightRepository;

@Path("/flights")
public class FlightResource {
	
	private static FlightResource _instance=null;
	FlightRepository repository;
	
	private FlightResource() {
		repository = MapFlightRepository.getInstance();
	}
	
	public static FlightResource getInstance() {
		if(_instance == null) {
			_instance = new FlightResource();
		}
		return _instance;
	}
	
	@GET
	@Produces("application/json")
	public Collection<Flight> getAllFlights(@QueryParam("order") String order, @QueryParam("isEmpty") Boolean isEmpty, 
			@QueryParam("origin") String origin, @QueryParam("destination") String destination, @QueryParam("airline") String airline,@QueryParam("price") Double price) {
		
		List<Flight> result = new ArrayList<>();
		
		for (Flight flight: repository.getAllFlights()) {
			if (origin == null || flight.getOrigin().toUpperCase().equals(origin.toUpperCase())) {
				if (destination == null || flight.getDestination().toUpperCase().equals(destination.toUpperCase())) {
					if (price == null || flight.getPrice().equals(price)) {
						if (airline == null || flight.getAirline().toUpperCase().equals(airline.toUpperCase())) {
							if (isEmpty == null
									|| (isEmpty && (flight.getPassengers() == null || flight.getPassengers().size() == 0))
									|| (!isEmpty && (flight.getPassengers() != null || flight.getPassengers().size() > 0))) {
								result.add(flight);
							}
						}
					} 
				} 
			} 
		}
		
		if (result.size() == 0) {
			throw new BadRequestException("Any of the given params does not fit with any of the existing flights");
		}
		
		if (order != null) {
			if (order.equals("origin")) {
				Collections.sort(result,Comparator.comparing(Flight::getOrigin));
			} else if (order.equals("-origin")) {
				Collections.sort(result, Comparator.comparing(Flight::getOrigin).reversed());
			} else if (order.equals("destination")) {
				Collections.sort(result, Comparator.comparing(Flight::getDestination));
			} else if (order.equals("-destination")) {
				Collections.sort(result, Comparator.comparing(Flight::getDestination).reversed());
			} else if (order.equals("originDate")) {
				Collections.sort(result, Comparator.comparing(Flight::getOriginDate));
			} else if (order.equals("-originDate")) {
				Collections.sort(result, Comparator.comparing(Flight::getOriginDate).reversed());
			} else if (order.equals("destinationDate")) {
				Collections.sort(result, Comparator.comparing(Flight::getDestinationDate));
			} else if (order.equals("-destinationDate")) {
				Collections.sort(result, Comparator.comparing(Flight::getDestinationDate).reversed());
			}  else if (order.equals("price")) {
				Collections.sort(result, Comparator.comparing(Flight::getPrice));
			} else if (order.equals("-price")) {
				Collections.sort(result, Comparator.comparing(Flight::getPrice).reversed());
			} else if (order.equals("airline")) {
				Collections.sort(result, Comparator.comparing(Flight::getAirline));
			} else if (order.equals("-airline")) {
				Collections.sort(result, Comparator.comparing(Flight::getAirline).reversed());
			} else if (order.equals("model")) {
				Collections.sort(result, Comparator.comparing(Flight::getModel));
			} else if (order.equals("-model")) {
				Collections.sort(result, Comparator.comparing(Flight::getModel).reversed());
			} else if (order.equals("numMaxPassengers")) {
				Collections.sort(result, Comparator.comparing(Flight::getNumMaxPassengers));
			} else if (order.equals("-numMaxPassengers")) {
				Collections.sort(result, Comparator.comparing(Flight::getNumMaxPassengers).reversed());
			} else {
				throw new BadRequestException("The order parameter must be 'origin' , '-origin' , 'destination' , '-destination' , 'originDate' , '-originDate', 'destinationDate', '-destinationDate', 'price', '-price', 'airline' , '-airline' , 'model' , '-model' , 'numMaxPassengers' or '-numMaxPassengers'.");
			}
		}
		return result;
	}
	
	@GET
	@Path("/{flightId}")
	@Produces("application/json")
	public Flight getFlight(@PathParam("flightId") String flightId) {	
		
		// Check if the flight exists on the repository
		if (!repository.getAllFlights().stream().anyMatch(x -> x.getId().equals(flightId))) {
			throw new NotFoundException("The flight with the id = " + flightId + " does not exist on the repository");
		}
		
		return repository.getFlight(flightId);
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addFlight(@Context UriInfo uriInfo, Flight flight) {
		
		// If the flight contains any wrong field it throws a 400 Error
		if (flight.getId() == null || "".equals(flight.getId()) || !flight.getId().startsWith("f") || !flight.getId().substring(1).chars().allMatch(Character::isDigit)) {
			throw new BadRequestException("The id of the flight must follow the pattern fX (Example: f10) and must not be null or empty");
		} else if (flight.getOrigin() == null || "".equals(flight.getOrigin()) || !flight.getOrigin().replace(" ", "").chars().allMatch(Character::isLetter)) {
			throw new BadRequestException("The origin of the flight must contains letters and must not be null or empty");
		} else if (flight.getDestination() == null || "".equals(flight.getDestination()) || !flight.getDestination().replace(" ", "").chars().allMatch(Character::isLetter)) {
			throw new BadRequestException("The destination of the flight must contains letters and must not be null or empty");
		} else if (flight.getOriginDate() == null || flight.getOriginDate() <= 0 || flight.getOriginDate() > 24) {
			throw new BadRequestException("The originDate of the flight is not valid");
		} else if (flight.getDestinationDate() == null || flight.getDestinationDate() <= 0 || flight.getDestinationDate() > 24) {
			throw new BadRequestException("The destinationDate of the flight is not valid");
		} else if (flight.getPrice() == null || flight.getPrice() <= 0) {
			throw new BadRequestException("The price of the flight must be positive");
		} else if (flight.getAirline() == null || "".equals(flight.getAirline()) || !flight.getDestination().replace(" ", "").chars().allMatch(Character::isLetter)) {
			throw new BadRequestException("The airline of the flight must contains letters and must not be null or empty");
		} else if (flight.getNumMaxPassengers() == null || flight.getNumMaxPassengers() < flight.getPassengers().size()) {
			throw new BadRequestException("The max of seats must be over the existing passengers number");
		}
		
		// Check if the flight exists on the repository
		if (repository.getAllFlights().stream().anyMatch(x -> x.getId().equals(flight.getId()))) {
			throw new NotFoundException("The flight to be added with the id = " + flight.getId() + " already exist on the repository");
		}
		
		repository.addFlight(flight);

		// Builds the response. Returns the flight that has just been added.	
		Flight f = repository.getFlight(flight.getId());
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "getFlight");
		URI uri = ub.build(f.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(f);
		return resp.build();
	}

	
	@PUT
	@Consumes("application/json")
	public Response updateFlight(Flight flight) {
		
		// If the flight contains any wrong field it throws a 400 Error
		if (flight.getId() == null || "".equals(flight.getId()) || !flight.getId().startsWith("f") || !flight.getId().substring(1).chars().allMatch(Character::isDigit)) {
			throw new BadRequestException("The id of the flight must follow the pattern fX (Example: f10) and must not be null or empty");
		} else if (flight.getOrigin() == null || "".equals(flight.getOrigin()) || !flight.getOrigin().replace(" ", "").chars().allMatch(Character::isLetter)) {
			throw new BadRequestException("The origin of the flight must contains letters and must not be null or empty");
		} else if (flight.getDestination() == null || "".equals(flight.getDestination()) || !flight.getDestination().replace(" ", "").chars().allMatch(Character::isLetter)) {
			throw new BadRequestException("The destination of the flight must contains letters and must not be null or empty");
		} else if (flight.getOriginDate() == null || flight.getOriginDate() <= 0 || flight.getOriginDate() > 24) {
			throw new BadRequestException("The originDate of the flight is not valid");
		} else if (flight.getDestinationDate() == null || flight.getDestinationDate() <= 0 || flight.getDestinationDate() > 24) {
			throw new BadRequestException("The destinationDate of the flight is not valid");
		} else if (flight.getPrice() == null || flight.getPrice() <= 0) {
			throw new BadRequestException("The price of the flight must be positive");
		} else if (flight.getAirline() == null || "".equals(flight.getAirline()) || !flight.getDestination().replace(" ", "").chars().allMatch(Character::isLetter)) {
			throw new BadRequestException("The airline of the flight must contains letters and must not be null or empty");
		} else if (flight.getNumMaxPassengers() == null || flight.getNumMaxPassengers() < flight.getPassengers().size()) {
			throw new BadRequestException("The max of seats must be over the existing passengers number");
		}
		
		// Check if the flight exists on the repository
		if (!repository.getAllFlights().stream().anyMatch(x -> x.getId().equals(flight.getId()))) {
			throw new NotFoundException("The flight to be updated with the id = " + flight.getId() + " does not exist on the repository");
		} else if (!(flight.getPassengers().size() == repository.getFlight(flight.getId()).getPassengers().size())) {
			throw new BadRequestException("The flight to be updated can not modify passengers field");
		}
		
		repository.updateFlight(flight);
		
		// Finally, it creates a 204 Success status
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("/{flightId}")
	public Response removeFlight(@PathParam("flightId") String flightId) {
		
		// Check if the flight exists on the repository
		if (!repository.getAllFlights().stream().anyMatch(x -> x.getId().equals(flightId))) {
			throw new NotFoundException("The flight to be removed with the id = " + flightId + " does not exist on the repository");
		}
			
		repository.deleteFlight(flightId);
		
		// Finally, it creates a 204 Success status
		return Response.noContent().build();
	}
	
	@GET
	@Path("/{flightId}/{passengerId}")
	@Produces("application/json")
	public Passenger getPassengerFromAFlight(@PathParam("flightId") String flightId, @PathParam("passengerId") String passengerId) {
		
		// Check if the flight exists on the repository
		if (!repository.getAllFlights().stream().anyMatch(x -> x.getId().equals(flightId))) {
			throw new NotFoundException("The flight with the id = " + flightId + " does not exist on the repository");
		}
		
		// Check if the passenger exists on the repository
		if (!repository.getAllPassengers().stream().anyMatch(x -> x.getId().equals(passengerId))) {
			throw new NotFoundException("The passenger with the id = " + passengerId + " does not exist on the repository");
		}
		
		//Check if the flight contains the passenger on the repository
		if (!repository.getFlight(flightId).getPassengers().contains(repository.getPassenger(passengerId))) {
			throw new NotFoundException("The passenger with the id = " + passengerId + " is not on the flight with id = " + flightId);
		}
		
		return repository.getFlight(flightId).getPassenger(passengerId);
	}
	
	@POST	
	@Path("/{flightId}/{passengerId}")
	@Consumes("text/plain")
	@Produces("application/json")
	public Response addPassengerToAFlight(@Context UriInfo uriInfo,@PathParam("flightId") String flightId, @PathParam("passengerId") String passengerId) {				
		
		// Check if the flight exists on the repository
		if (!repository.getAllFlights().stream().anyMatch(x -> x.getId().equals(flightId))) {
			throw new NotFoundException("The flight with the id = " + flightId + " does not exist on the repository");
		}
		
		// Check if the passenger exists on the repository
		if (!repository.getAllPassengers().stream().anyMatch(x -> x.getId().equals(passengerId))) {
			throw new NotFoundException("The passenger to be added with the id = " + passengerId + " does not exist on the repository");
		}
		
		// Check if the flight contains the passenger on the repository
		if (repository.getAllPassengersFromAFlight(flightId).stream().anyMatch(x -> x.getId().equals(passengerId))) {
			throw new NotFoundException("The passenger to be added with the id = " + passengerId + " is already on the flight with id = " + flightId);
		}
		
		// Check if the numMaxPassengers is equal to the list size
		if (repository.getFlight(flightId).getNumMaxPassengers() == repository.getFlight(flightId).getPassengers().size()) {
			throw new NotFoundException("The flight with the id = " + flightId + " do not have any available seat");
		}
		
		repository.addPassengerToAFlight(flightId, passengerId);		

		// Builds the response. Returns the flight that has just been added.
		Flight f = repository.getFlight(flightId);
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "getFlight");
		URI uri = ub.build(f.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(f);			
		return resp.build();
	}
	
	@DELETE
	@Path("/{flightId}/{passengerId}")
	public Response removePassengerFromAFlight(@PathParam("flightId") String flightId, @PathParam("passengerId") String passengerId) {
		
		// Check if the flight exists on the repository
		if (!repository.getAllFlights().stream().anyMatch(x -> x.getId().equals(flightId))) {
			throw new NotFoundException("The flight with the id = " + flightId + " does not exist on the repository");
		}
		
		// Check if the passenger exists on the repository
		if (!repository.getAllPassengers().stream().anyMatch(x -> x.getId().equals(passengerId))) {
			throw new NotFoundException("The passenger to be removed with the id = " + passengerId + " does not exist on the repository");
		}
		
		//Check if the flight contains the passenger on the repository
		if (!repository.getFlight(flightId).getPassengers().contains(repository.getPassenger(passengerId))) {
			throw new NotFoundException("The passenger to be removed with the id = " + passengerId + " is not on the flight with id = " + flightId);
		}
			
		repository.removePassengerFromAFlight(flightId, passengerId);	

		// Finally, it creates a 204 Success status
		return Response.noContent().build();
	}
}
