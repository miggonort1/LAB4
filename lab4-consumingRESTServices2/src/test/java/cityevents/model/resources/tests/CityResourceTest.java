package cityevents.model.resources.tests;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.restlet.resource.ResourceException;

import cityevents.model.City;
import cityevents.model.Event;
import cityevents.model.resources.CityResource;
import cityevents.model.resources.EventResource;

public class CityResourceTest {

	static City city1, city2, city3, city4, city5;
	static Event event;
	static CityResource cr = new CityResource();
	static EventResource er = new EventResource();
	
	@BeforeClass
	public static void setUp() throws Exception {
		
		city1 = cr.addCity(new City("Madrid", "Capital of Spain and one of the great European cities"));
		city2 = cr.addCity(new City("London", "Capital of England, a beautiful city in winter"));
		city3 = cr.addCity(new City("Helsinki", "Capital of Finland, a very cold city"));
		city4 = cr.addCity(new City("Berlin", "Capital of Germany"));
		city5 = cr.addCity(new City("Paris", "Capital of France"));
		event = er.addEvent(new Event("Tour","Tour through the important places of the city","Helsinki", "Turism", "Helsinki", "03/02/2023", "40 euros"));
	}

	@AfterClass
	public static void tearDown() throws Exception {
		// Test: Delete all the cities created on the API
		cr.deleteCity(city1.getId());
		cr.deleteCity(city2.getId());
		cr.deleteCity(city3.getId());
		cr.deleteCity(city4.getId());
		er.deleteEvent(event.getId());
	}

	@Test
	public void testGetCities() {
		System.out.println("----- TEST: GET ALL CITIES -----");
		// Test: Receive all the existing cities on the API
		Collection<City> cities = cr.getCities(); 
		
		// Check that the collection received is not null
		assertNotNull("The collection of cities is null", cities);
		
		// Show all the events
		cities.stream().forEach(System.out::println);
	}

	@Test
	public void testGetCity() {
		System.out.println("----- TEST: GET CITY -----");
		// Test: Get an existing event of the API
		City c = cr.getCity(city1.getId());
		
		// Check that the event received correspond to the event created
		assertEquals("The id of the cities do not match", city1.getId(), c.getId());
		assertEquals("The name of the cities do not match", city1.getName(), c.getName());
		assertEquals("The description of the cities do not match", city1.getDescription(), c.getDescription());
		
		// Show result
		System.out.println(c);
	}
	
	@Test (expected = ResourceException.class)
	public void testGetNonExistingCity() {
		System.out.println("----- TEST: GET NON EXISTING CITY -----");
		// Test: Get a non existing city of the API
		cr.getCity("c300");
	}

	@Test
	public void testAddCity() {
		System.out.println("----- TEST: ADD CITY -----");
		// Test: Post a new event
		String cityName = "Rome";
		String cityDescription = "Capital of Italy";
		City c = cr.addCity(new City(cityName,cityDescription));
		
		// Check if the event fields correspond to the event sent
		assertNotNull("The city is null", c);
		assertEquals("The city's name has not been setted correctly", cityName, c.getName());
		assertEquals("The city's description has not been setted correctly", cityDescription, c.getDescription());
	
		// Show the existing city
		System.out.println(c);
		cr.deleteCity(c.getId());
	}

	@Test
	public void testUpdateCity() {
		System.out.println("----- TEST: UPDATE CITY -----");
		// Test: Update an existing city
		String cName = "Updated city name";
		city1.setName(cName);
		boolean success = cr.updateCity(city1);
		
		// Check if the event was updated with the content
		assertTrue("Error when updating the city", success);
		City c  = cr.getCity(city1.getId());
		assertEquals("The city's name has not been updated correctly", cName, c.getName());

		// Show the updated city
		System.out.println(c);
	}
	
	@Test (expected = ResourceException.class)
	public void testUpdateNonExistingCity() {
		System.out.println("----- TEST: UPDATE NON EXISTING CITY -----");
		// Test: Update a non existing city
		String cName = "Seul";
		City cUpdate = new City("This city is not on the list", "");
		cUpdate.setName(cName);
		cr.updateCity(cUpdate);
	}

	@Test
	public void testDeleteCity() {
		System.out.println("----- TEST: REMOVE CITY -----");
		boolean success = cr.deleteCity(city5.getId());
		
		//Check if the event was removed
		assertTrue("The city has not been deleted correctly", success);
	}
	
	@Test (expected = ResourceException.class)
	public void testDeleteNonExistingCity() {
		System.out.println("----- TEST: REMOVE NON EXISTING CITY -----");
		// Test: Delete a non existing city
		cr.deleteCity("c90");
	}

	@Test
	public void testAddEventToCity() {
		System.out.println("----- TEST: ADD AN EVENT TO A CITY -----");
		boolean success = cr.addEventToCity(city3.getId(), event.getId());
		
		// Check if the event was added to the city
		assertTrue("Error when adding the event to the city", success);
		
		// Show the event on the city
		System.out.println(city3);
	}

	@Test
	public void testDeleteEventFromCity() {
		System.out.println("----- TEST: REMOVE AN EVENT FROM AN CITY -----");
		System.out.println(er.getEvent(event.getId()));
		System.out.println(cr.getCity(city3.getId()));
		boolean success = cr.deleteEventFromCity(city3.getId(), event.getId());
		
		// Check if the event was removed from the city
		assertTrue("Error when deleting the event to the city", success);	
		
		// Show the event on the city
		System.out.println(city3);
	}

}
