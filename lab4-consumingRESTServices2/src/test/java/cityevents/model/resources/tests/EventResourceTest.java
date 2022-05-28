package cityevents.model.resources.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.restlet.resource.ResourceException;

import cityevents.model.Event;
import cityevents.model.Fqas;
import cityevents.model.resources.EventResource;
import cityevents.model.resources.FqasResource;


public class EventResourceTest {
	
	static Event event1, event2, event3, event4, event5;
	static Fqas fqa;
	static EventResource er = new EventResource();
	static FqasResource fr = new FqasResource();
	
	@BeforeClass
	public static void setup() throws Exception {
		// Test: Examples of events to do the tests added to the API
		event1 = er.addEvent(new Event("Champions League","It is the most prestigious official international football tournament at club level","Uefa", "Sport", "París", "28/05/2022", "70-690 euros"));
		event2 = er.addEvent(new Event("Manuel Carrasco concert","Manuel Carrasco's tour concert","San Fernando city hall", "Music", "San Fernando", "27/05/2022", "40-110 euros"));
		event3 = er.addEvent(new Event("Puro Latino Fest","The pioneer festival in offering Latin rhythms and making reggaeton one of its main attractions","Seville city hall", "Music", "Cartuja Stadium", "01/07/2022", "50-139 euros"));
		event4 = er.addEvent(new Event("Seville Tour","Tour throughout seville visiting emblematic sites","Seville city hall", "Turism", "Seville", "05/07/2022", "15 euros"));
		event5 = er.addEvent(new Event("Madrid Tour","Tour throughout Madrid visiting emblematic sites","Madrid city hall", "Turism", "Madrid", "03/02/2023", "20 euros"));
		fqa = fr.addFqa(new Fqas("This is a test fqa","You can introduce a fqa here"));
	}

	@AfterClass
	public static void tearDown() throws Exception {
		// Test: Delete all the events created on the API
		er.deleteEvent(event1.getId());
		er.deleteEvent(event2.getId());
		er.deleteEvent(event3.getId());
		er.deleteEvent(event4.getId());
		fr.deleteFqa(fqa.getId());
	}
	
	@Test
	public void testGetAll() {
		System.out.println("----- TEST: GET ALL EVENTS -----");
		// Test: Receive all the existing events on the API
		Collection<Event> events = er.getEvents();
		
		// Check that the collection received is not null
		assertNotNull("The collection of events is null", events);
		
		// Show all the events
		events.stream().forEach(System.out::println);
	}

	@Test
	public void testGetEvent() {
		System.out.println("----- TEST: GET EVENT -----");
		// Test: Get an existing event of the API
		Event e = er.getEvent(event1.getId());
		
		// Check that the event received correspond to the event created
		assertEquals("The id of the events do not match", event1.getId(), e.getId());
		assertEquals("The name of the events do not match", event1.getName(), e.getName());
		assertEquals("The description of the events do not match", event1.getDescription(), e.getDescription());
		assertEquals("The organizer of the events do not match", event1.getOrganizer(), e.getOrganizer());
		assertEquals("The category of the events do not match", event1.getCategory(), e.getCategory());
		assertEquals("The location of the events do not match", event1.getLocation(), e.getLocation());
		assertEquals("The date of the events do not match", event1.getDate(), e.getDate());
		assertEquals("The price of the events do not match", event1.getPrice(), e.getPrice());
		assertEquals("The fqa of the events do not match", event1.getFqas(), e.getFqas());
		
		// Show the existing fqa
		System.out.println(e);

	}

	@Test (expected = ResourceException.class)
	public void testGetNonExistingEvent() {
		System.out.println("----- TEST: GET NON EXISTING EVENT -----");
		// Test: Get a non existing event of the API
		er.getEvent("f300");
	}
	
	@Test
	public void testAddEvent() {
		System.out.println("----- TEST: ADD EVENT -----");
		// Test: Post a new event
		String eName = "Dani Martín concert";
		String eDescription = "Festival Starlite";
		String eOrganizer = "test";
		String eCategory = "test";
		String eLocation = "test";
		String eDate = "test";
		String ePrice = "test";
		Event e = er.addEvent(new Event(eName,eDescription, eOrganizer, eCategory, eLocation, eDate, ePrice));
		
		// Check if the event fields correspond to the event sent
		assertNotNull("The event is null", e);
		assertEquals("The event's name has not been setted correctly", eName, e.getName());
		assertEquals("The event's description has not been setted correctly", eDescription, e.getDescription());
	
		// Show the existing event
		System.out.println(e);
		er.deleteEvent(e.getId());
	}
		
	@Test
	public void testUpdateEvent() {
		System.out.println("----- TEST: UPDATE EVENT -----");
		// Test: Update an existing event
		String eName = "Champions League Final";
		event1.setName(eName);
		boolean success = er.updateEvent(event1);
		
		// Check if the event was updated with the content
		assertTrue("Error when updating the event", success);
		Event e  = er.getEvent(event1.getId());
		assertEquals("The event´s name has not been updated correctly", eName, e.getName());

		// Show the updated event
		System.out.println(e);
	}
	
	@Test (expected = ResourceException.class)
	public void testUpdateNonExistingEvent() {
		System.out.println("----- TEST: UPDATE NON EXISTING EVENT -----");
		// Test: Update a non existing event
		String eName = "Champions League Final";
		Event eUpdate = new Event("This event is not on the list", "");
		eUpdate.setName(eName);
		er.updateEvent(eUpdate);
	}

	@Test
	public void testDeleteEvent() {
		System.out.println("----- TEST: REMOVE EVENT -----");
		boolean success = er.deleteEvent(event5.getId());
		
		//Check if the event was removed
		assertTrue("Error when deleting the event", success);
	}
	
	@Test (expected = ResourceException.class)
	public void testDeleteNonExistingEvent() {
		System.out.println("----- TEST: REMOVE NON EXISTING EVENT -----");
		// Test: Delete a non existing event
		er.deleteEvent("e90");
	}
	
	
	@Test
	public void testAddFqaToEvent() {
		System.out.println("----- TEST: ADD A FPA TO AN EVENT -----");
		boolean success = er.addFqaToEvent(event3.getId(), fqa.getId());
		
		// Check if the fqa was added to the event
		assertTrue("Error when adding the fqa to the event", success);
		
		// Show the fqa on the event
		System.out.println(event3);
	}
	
	@Test
	public void testDeleteFqaFromEvent() {
		System.out.println("----- TEST: REMOVE A FPA FROM AN EVENT -----");
		boolean success = er.deleteFqaFromEvent(event3.getId(), fqa.getId());
		
		// Check if the fqa was removed to the event
		assertTrue("Error when deleting the fqa from the event", success);	
		
		// Show the fqa on the event
		System.out.println(event3);
	}

}
