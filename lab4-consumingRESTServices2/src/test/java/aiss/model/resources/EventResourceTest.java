package aiss.model.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import aiss.model.resources.EventResource;
import aiss.model.Event;
import aiss.model.Fqas;


public class EventResourceTest {
	
	static Event event1, event2, event3, event4;
	static Fqas fqa;
	static EventResource sr = new EventResource();
	
	@BeforeClass
	public static void setup() throws Exception {
		
		// Test event 1
		event1 = sr.addEvent(new Event("Test id","Test name","Test description","Test organizer", "Test category", "Test location", "Test date", "Test price"));
		
		
	}

	@AfterClass
	public static void tearDown() throws Exception {
		sr.deleteEvent(event1.getId());
		sr.deleteEvent(event2.getId());
	}
	
	@Test
	public void testGetAll() {
		Collection<Event> events = sr.getEvents();
		
		assertNotNull("The collection of events is null", events);
		
		// Show result
		System.out.println("Listing all events:");
		int i=1;
		for (Event e : events) {
			System.out.println("Event " + i++ + " : " + e.getName() + " (ID=" + e.getId() + ")");
		}
	}

	@Test
	public void testGetEvent() {
		Event e = sr.getEvent(event1.getId());
		
		assertEquals("The id of the events do not match", event1.getId(), e.getId());
		assertEquals("The name of the events do not match", event1.getName(), e.getName());
		
		// Show result
		System.out.println("Event id: " +  e.getId());
		System.out.println("Event name: " +  e.getName());

	}

	@Test
	public void testAddEvent() {
		String eventName = "Add event test name";
		String eventDescription = "Add event test description";
		String eventOrganizer = "Add event test organizer";
		String eventCategory = "Add event test category";
		String eventLocation = "Add event test location";
		String eventDate = "Add event test date";
		String eventPrice = "Add event test price";
		
		
		event4 = sr.addEvent(new Event(eventName,eventDescription, eventOrganizer, eventCategory, eventLocation, eventDate, eventPrice));
		
		assertNotNull("Error when adding the city", event4);
		assertEquals("The city's name has not been setted correctly", eventName, event4.getName());
		assertEquals("The city's description has not been setted correctly", eventDescription, event4.getDescription());
	}

	@Test
	public void testUpdateCity() {
		String eventName = "Updated event name";

		// Update city
		event1.setName(eventName);

		boolean success = sr.updateEvent(event1);
		
		assertTrue("Error when updating the event", success);
		
		Event cl  = sr.getEvent(event1.getId());
		assertEquals("The event's name has not been updated correctly", eventName, cl.getName());

	}

	@Test
	public void testDeleteEvent() {
		boolean success = sr.deleteEvent(event3.getId());
		assertTrue("Error when deleting the event", success);
		
		Event cl = sr.getEvent(event2.getId());
		assertNull("The event has not been deleted correctly", cl);
	}
	
	@Test
	public void testAddFqaToEvent() {
		if(fqa!=null) {
			boolean success = sr.addFqaToEvent(event3.getId(), fqa.getId());
			assertTrue("Error when adding the fqa to the event", success);
		}
	}
	
	@Test
	public void testDeleteFqaFromEvent() {
		if(fqa!=null) {
			boolean success = sr.deleteFqaFromEvent(event3.getId(), fqa.getId());
			assertTrue("Error when deleting the fqa from the event", success);
		}		
	}

}
