package cityevents.model.resources.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import cityevents.model.Event;
import cityevents.model.Fqas;
import cityevents.model.resources.EventResource;
import cityevents.model.resources.FqasResource;


public class EventResourceTest {
	
	static Event event1, event2, event3, event4;
	static Fqas fqa;
	static EventResource er = new EventResource();
	static FqasResource fr = new FqasResource();
	
	@BeforeClass
	public static void setup() throws Exception {
		
		// Test event 1
		event1 = er.addEvent(new Event("e9","Champions League","It is the most prestigious official international football tournament at club level","Uefa", "Sport", "París", "28/05/2022", "70-690 euros"));
		event2 = er.addEvent(new Event("e10","Manuel Carrasco concert","Manuel Carrasco's tour concert","San Fernando city hall", "Music", "San Fernando", "27/05/2022", "40-110 euros"));
		event3 = er.addEvent(new Event("e11","Puro Latino Fest","The pioneer festival in offering Latin rhythms and making reggaeton one of its main attractions","Seville city hall", "Music", "Cartuja Stadium", "01/07/2022", "50-139 euros"));
		event4 = er.addEvent(new Event("e12","Seville Tour","Tour throughout seville visiting emblematic sites","Seville city hall", "Turism", "Seville", "05/07/2022", "15 euros"));
		
	}

	@AfterClass
	public static void tearDown() throws Exception {
		er.deleteEvent(event1.getId());
		er.deleteEvent(event2.getId());
		er.deleteEvent(event3.getId());
		er.deleteEvent(event4.getId());
	}
	
	@Test
	public void testGetAll() {
		Collection<Event> events = er.getEvents();
		
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
		Event e = er.getEvent(event1.getId());
		
		assertEquals("The id of the events do not match", event1.getId(), e.getId());
		assertEquals("The name of the events do not match", event1.getName(), e.getName());
		
		// Show result
		System.out.println("Event id: " +  e.getId());
		System.out.println("Event name: " +  e.getName());

	}

	@Test
	public void testAddEvent() {
		String eventName = "Dani Martín concert";
		String eventDescription = "Festival Starlite";
			
		event4 = er.addEvent(new Event(eventName,eventDescription));
			
		assertNotNull("Error when adding the city", event4);
		assertEquals("The city's name has not been setted correctly", eventName, event4.getName());
		assertEquals("The city's description has not been setted correctly", eventDescription, event4.getDescription());
	}
		
	@Test
	public void testUpdateCity() {
		String eventName = "Champions League Final";

		// Update city
		event1.setName(eventName);

		boolean success = er.updateEvent(event1);
		
		assertTrue("Error when updating the event", success);
		
		Event cl  = er.getEvent(event1.getId());
		assertEquals("The event's name has not been updated correctly", eventName, cl.getName());

	}

	@Test
	public void testDeleteEvent() {
		boolean success = er.deleteEvent(event3.getId());
		assertTrue("Error when deleting the event", success);
		
		Event cl = er.getEvent(event3.getId());
		assertNull("The event has not been deleted correctly", cl);
	}
	
	@Test
	public void testAddFqaToEvent() {
		if(fqa!=null) {
			boolean success = er.addFqaToEvent(event3.getId(), fqa.getId());
			assertTrue("Error when adding the fqa to the event", success);
		}
	}
	
	@Test
	public void testDeleteFqaFromEvent() {
		if(fqa!=null) {
			boolean success = er.deleteFqaFromEvent(event3.getId(), fqa.getId());
			assertTrue("Error when deleting the fqa from the event", success);
		}		
	}

}
