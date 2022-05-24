package easytrip.model.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

import easytrip.model.Gender;
import easytrip.model.Passenger;
import easytrip.model.repository.FlightRepository;
import easytrip.model.repository.MapFlightRepository;

@Path("/passengers")
public class PassengerResource {

	public static PassengerResource _instance = null;
	FlightRepository repository;
	
	private PassengerResource() {
		repository = MapFlightRepository.getInstance();
	}
	
	public static PassengerResource getInstance() {
		if(_instance == null) {
			_instance = new PassengerResource();
		}
		return _instance; 
	}
	
	@GET
	@Produces("application/json")
	public Collection<Passenger> getAllPassengers(@QueryParam("q") String q, @QueryParam("order") String order, @QueryParam("limit") Integer limit, @QueryParam("offset") Integer offset) {
		
		List<Passenger> result = new ArrayList<>(), passengers = repository.getAllPassengers().stream().collect(Collectors.toList());
		int start = offset == null ? 0: offset-1;
		int end = limit == null ? passengers.size(): start  + limit;
		
		if (q != null) {
			for (int i = start; i < end; i++) {
				if (passengers.get(i).getName().contains(q) 
						|| passengers.get(i).getSurname().contains(q)) {
					result.add(passengers.get(i));
				}		
			}
		} else {
			result = passengers;
		}

		
		if (result.size() == 0) {
			throw new BadRequestException(limit + " " + offset + "Any of the given params does not fit with any of the existing passengers");
		}
		
		if (order != null) {
			if (order.equals("name")) {
				Collections.sort(result, Comparator.comparing(Passenger::getName));
			} else if (order.equals("-name")) {
				Collections.sort(result, Comparator.comparing(Passenger::getName).reversed());
			} else if (order.equals("surname")) {
				Collections.sort(result, Comparator.comparing(Passenger::getSurname));
			} else if (order.equals("-surname")) {
				Collections.sort(result, Comparator.comparing(Passenger::getSurname).reversed());
			} else if (order.equals("age")) {
				Collections.sort(result, Comparator.comparing(Passenger::getAge));
			} else if (order.equals("-age")) {
				Collections.sort(result, Comparator.comparing(Passenger::getAge).reversed());
			} else {
				throw new BadRequestException("The order parameter must be 'name' , '-name' , 'surname' , '-surname' , 'age' or '-age'.");
			}
		}
		return result;
	}
	
	@GET
	@Path("/{passengerId}")
	@Produces("application/json")
	public Passenger getPassenger(@PathParam("passengerId") String passengerId) {
		
		// Check if the passenger exists on the repository
		if (!repository.getAllPassengers().stream().anyMatch(x -> x.getId().equals(passengerId))) {
			throw new NotFoundException("The passenger with the id = " + passengerId + " does not exist on the repository");
		}
		
		return repository.getPassenger(passengerId);
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addPassenger(@Context UriInfo uriInfo, Passenger passenger) {
		
		// If the passenger contains any wrong field it throws a 400 Error
		if (passenger.getId() == null || "".equals(passenger.getId()) || !passenger.getId().startsWith("p") || !passenger.getId().substring(1).chars().allMatch(Character::isDigit)) {
			throw new BadRequestException("The id of the passenger must follow the pattern pX (Example: p10) and must not be null or empty");
		} else if (passenger.getName() == null || "".equals(passenger.getName()) || !passenger.getName().replace(" ", "").chars().allMatch(Character::isLetter)) {
			throw new BadRequestException("The name of the passenger must contains letters and must not be null or empty");
		} else if (passenger.getSurname() == null || "".equals(passenger.getSurname()) || !passenger.getSurname().replace(" ", "").chars().allMatch(Character::isLetter)) {
			throw new BadRequestException("The surname of the passenger must contains letters and must not be null or empty");
		} else if (passenger.getGender() == null || !passenger.getGender().equals(Gender.MAN) || !passenger.getGender().equals(Gender.WOMAN) || !passenger.getGender().equals(Gender.OTHER)) {
			throw new BadRequestException("The gender of the passenger must be MAN, WOMAN or OTHER");
		} else if (passenger.getAge() == null || passenger.getAge() <= 0) {
			throw new BadRequestException("The age of the passenger must be positive");
		}
		
		// Check if the passenger exists on the repository
		if (repository.getAllPassengers().stream().anyMatch(x -> x.getId().equals(passenger.getId()))) {
			throw new BadRequestException("The passenger to be added with the id = " + passenger.getId() + " already exist on the repository");
		}
		
		repository.addPassenger(passenger);

		// Builds the response. Returns the passenger that has just been added.	
		Passenger p = repository.getPassenger(passenger.getId());
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "getPassenger");
		URI uri = ub.build(p.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(p);
		return resp.build();
	}
	
	
	@PUT
	@Consumes("application/json")
	public Response updatePassenger(Passenger passenger) {
		
		// If the passenger contains any wrong field it throws a 400 Error
		if (passenger.getId() == null || "".equals(passenger.getId()) || !passenger.getId().startsWith("p") || !passenger.getId().substring(1).chars().allMatch(Character::isDigit)) {
			throw new BadRequestException("The id of the passenger must follow the pattern pX (Example: p10) and must not be null or empty");
		} else if (passenger.getName() == null || "".equals(passenger.getName()) || !passenger.getName().replace(" ", "").chars().allMatch(Character::isLetter)) {
			throw new BadRequestException("The name of the passenger must contains letters and must not be null or empty");
		} else if (passenger.getSurname() == null || "".equals(passenger.getSurname()) || !passenger.getSurname().replace(" ", "").chars().allMatch(Character::isLetter)) {
			throw new BadRequestException("The surname of the passenger must contains letters and must not be null or empty");
		} else if (passenger.getGender() == null || !passenger.getGender().equals(Gender.MAN) || !passenger.getGender().equals(Gender.WOMAN) || !passenger.getGender().equals(Gender.OTHER)) {
			throw new BadRequestException("The gender of the passenger must be MAN, WOMAN or OTHER");
		} else if (passenger.getAge() <= 0) {
			throw new BadRequestException("The age of the passenger must be positive");
		}
		
		// Check if the passenger exists on the repository
		if (!repository.getAllPassengers().contains(passenger)) {
			throw new NotFoundException("The passenger to be updated with the id = " + passenger.getId() + " does not exist on the repository");
		}
		
		repository.updatePassenger(passenger);
		
		// Finally, it creates a 204 Success status
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("/{passengerId}")
	public Response removePassenger(@PathParam("passengerId") String passengerId) {
		
		// Check if the passenger exists on the repository
		if (!repository.getAllPassengers().stream().anyMatch(x -> x.getId().equals(passengerId))) {
			throw new NotFoundException("The passenger to be removed with the id = " + passengerId + " does not exist on the repository");
		}
		
		repository.deletePassenger(passengerId);
		
		// Finally, it creates a 204 Success status
		return Response.noContent().build();
	}
	
}
