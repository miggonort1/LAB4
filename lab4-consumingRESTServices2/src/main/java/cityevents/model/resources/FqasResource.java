package cityevents.model.resources;

import java.util.Arrays;
import java.util.Collection;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import cityevents.model.Fqas;

public class FqasResource {
	
	private String uri = "https://city-events-api.ew.r.appspot.com/api/fqas";

	
	public Collection<Fqas> getFqas() {
		ClientResource cr = null;
		Fqas [] fqas = null;
		try {
			cr = new ClientResource(uri);
			fqas = cr.get(Fqas[].class);
		} catch (ResourceException re) {
			System.err.println("Error when retrieving all fqas: " + cr.getResponse().getStatus());
			throw re;
		}
		return Arrays.asList(fqas);
	}
	

	public Fqas getFqa(String fqaId) {
		ClientResource cr = null;
		Fqas list = null;
		try {
			cr = new ClientResource(uri + "/" + fqaId);
			list = cr.get(Fqas.class);
		} catch (ResourceException re) {
			System.err.println("Error when retrieving the fqa: " + cr.getResponse().getStatus());
		}
		return list;
	}
	

	public Fqas addFqa(Fqas fq) {
		ClientResource cr = null;
		Fqas resultFqa = null;
		try {
			cr = new ClientResource(uri);
			cr.setEntityBuffering(true);		// Needed for using RESTlet from JUnit tests
			resultFqa = cr.post(fq,Fqas.class);
			
		} catch (ResourceException re) {
			System.err.println("Error when adding the fqa: " + cr.getResponse().getStatus());
		}
		
		return resultFqa;

	}
	
	public boolean updateFqa(Fqas fq) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource(uri);
			cr.setEntityBuffering(true);		// Needed for using RESTlet from JUnit tests
			cr.put(fq);
			
			
		} catch (ResourceException re) {
			System.err.println("Error when updating the fqa: " + cr.getResponse().getStatus());
			success = false;
		}
		
		return success;
	}
	

	public boolean deleteFqa(String fqaId) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource(uri + "/" + fqaId);
			cr.setEntityBuffering(true);		// Needed for using RESTlet from JUnit tests
			cr.delete();
			
		} catch (ResourceException re) {
			System.err.println("Error when deleting the fqa: " + cr.getResponse().getStatus());
			success = false;
			throw re;
		}
		
		return success;
		
	}

}
