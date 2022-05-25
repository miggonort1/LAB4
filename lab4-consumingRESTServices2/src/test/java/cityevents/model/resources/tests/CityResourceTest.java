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

	static City city1, city2, city3, city4;
	static Event event;
	static CityResource cr = new CityResource();
	static EventResource er = new EventResource();
	
	@BeforeClass
	public static void setUp() throws Exception {
		
		city1 = cr.addCity(new City("Test city 1"));
		city2 = cr.addCity(new City("Test city 2"));
		city3 = cr.addCity(new City("Test city 3"));
		
	
		event = er.addEvent(new Event("Test id","Test name","Test description","Test organizer", "Test category", "Test location", "Test date", "Test price"));
		if(event!=null)
			cr.addEventToCity(city1.getId(), event.getId());
	}

	@AfterClass
	public static void tearDown() throws Exception {
		cr.deleteCity(city1.getId());
		cr.deleteCity(city3.getId());
		cr.deleteCity(city4.getId());
		if(event!=null)
			er.deleteEvent(event.getId());
	}

	@Test
	public void testGetCities() {
		Collection<City> cities = cr.getCities(); 
		
		assertNotNull("The collection of cities is null", cities);
		
		// Show result
		System.out.println("Listing all cities:");
		int i=1;
		for (City cl : cities) {
			System.out.println("City " + i++ + " : " + cl.getName() + " (ID=" + cl.getId() + ")");
		}
		
	}

	@Test
	public void testGetCity() {
		City c = cr.getCity(city1.getId());
		
		assertEquals("The id of the cities do not match", city1.getId(), c.getId());
		assertEquals("The name of the cities do not match", city1.getName(), c.getName());
		
		// Show result
		System.out.println("City id: " +  c.getId());
		System.out.println("City name: " +  c.getName());

	}

	@Test
	public void testAddCity() {
		String cityName = "Add city test title";
		String cityDescription = "Add city test description";
		
		city4 = cr.addCity(new City(cityName,cityDescription));
		
		assertNotNull("Error when adding the city", city4);
		assertEquals("The city's name has not been setted correctly", cityName, city4.getName());
		assertEquals("The city's description has not been setted correctly", cityDescription, city4.getDescription());
	}

	@Test
	public void testUpdateCity() {
		String cityName = "Updated city name";

		// Update city
		city1.setName(cityName);

		boolean success = cr.updateCity(city1);
		
		assertTrue("Error when updating the city", success);
		
		City cl  = cr.getCity(city1.getId());
		assertEquals("The city's name has not been updated correctly", cityName, cl.getName());

	}

	@Test
	public void testDeleteCity() {
		boolean success = cr.deleteCity(city2.getId());
		assertTrue("Error when deleting the city", success);
		
		City cl = cr.getCity(city2.getId());
		assertNull("The city has not been deleted correctly", cl);
	}

	@Test
	public void testAddEventToCity() {
		if(event!=null) {
			boolean success = cr.addEventToCity(city3.getId(), event.getId());
			assertTrue("Error when adding the event to the city", success);
		}
	}

	@Test
	public void testDeleteEventFromCity() {
		if(event!=null) {
			boolean success = cr.deleteEventFromCity(city3.getId(), event.getId());
			assertTrue("Error when deleting the event to the city", success);
		}		
	}

}
