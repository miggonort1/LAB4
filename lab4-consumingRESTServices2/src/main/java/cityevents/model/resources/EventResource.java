package cityevents.model.resources;

import java.util.Arrays;
import java.util.Collection;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import cityevents.model.Event;

public class EventResource {
	
		
	private String uri = "https://city-events-api.ew.r.appspot.com/api/events";

	
	public Collection<Event> getEvents() {
		ClientResource cr = null;
		Event [] events = null;
		try {
			cr = new ClientResource(uri);
			events = cr.get(Event[].class);
			
		} catch (ResourceException re) {
			System.err.println("Error when retrieving all events: " + cr.getResponse().getStatus());
			throw re;
		}
		return Arrays.asList(events);
	}
	

	public Event getEvent(String eventId) {
		ClientResource cr = null;
		Event list = null;
		try {
			cr = new ClientResource(uri + "/" + eventId);
			list = cr.get(Event.class);
			
		} catch (ResourceException re) {
			System.err.println("Error when retrieving the event: " + cr.getResponse().getStatus());
		}
		
		return list;
	}
	

	public Event addEvent(Event ev) {
		ClientResource cr = null;
		Event resultEvent = null;
		try {
			cr = new ClientResource(uri);
			cr.setEntityBuffering(true);		// Needed for using RESTlet from JUnit tests
			resultEvent = cr.post(ev,Event.class);
			
		} catch (ResourceException re) {
			System.err.println("Error when adding the event: " + cr.getResponse().getStatus());
		}
		
		return resultEvent;

	}
	
	public boolean updateEvent(Event ev) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource(uri);
			cr.setEntityBuffering(true);		// Needed for using RESTlet from JUnit tests
			cr.put(ev);
			
			
		} catch (ResourceException re) {
			System.err.println("Error when updating the event: " + cr.getResponse().getStatus());
			success = false;
		}
		
		return success;
	}
	

	public boolean deleteEvent(String eventId) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource(uri + "/" + eventId);
			cr.setEntityBuffering(true);		// Needed for using RESTlet from JUnit tests
			cr.delete();
			
		} catch (ResourceException re) {
			System.err.println("Error when deleting the event: " + cr.getResponse().getStatus());
			success = false;
			throw re;
		}
		
		return success;
		
	}
	
	
	public boolean addFqaToEvent(String eventID, String fqaID) {
		ClientResource cr = null;
		boolean exito = true;
		try {
			cr = new ClientResource(uri+"/"+eventID+"/"+fqaID);
			cr.setEntityBuffering(true);
			cr.post(" ");
		} catch (ResourceException e) {
			System.err.println("Error a intentar añadir la fqa al evento: "+cr.getResponse().getStatus());
			exito=false;
			throw e;
		}
		return exito;
	}
	
	public boolean deleteFqaFromEvent(String eventID, String fqaID) {
		ClientResource cr = null;
		boolean exito = true;
		try {
			cr = new ClientResource(uri+"/"+eventID+"/"+fqaID);
			cr.setEntityBuffering(true);
			cr.delete();
		} catch (ResourceException e) {
			System.err.println("Error a intentar eliminar la fqa al evento: "+cr.getResponse().getStatus());
			exito=false;
			throw e;
		}
		return exito;
	}
}