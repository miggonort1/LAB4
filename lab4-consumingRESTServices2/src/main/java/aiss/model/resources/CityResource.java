package aiss.model.resources;

import java.util.Arrays;
import java.util.Collection;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import aiss.model.City;

public class CityResource {
	
	private String uri = "https://city-events-api.ew.r.appspot.com/api/cities";

	
	public Collection<City> getCities() {
		ClientResource cr = null;
		City [] cities = null;
		try {
			cr = new ClientResource(uri);
			cities = cr.get(City[].class);
			
		} catch (ResourceException re) {
			System.err.println("Error when retrieving all cities: " + cr.getResponse().getStatus());
			throw re;
		}
		
		return Arrays.asList(cities);
	}
	

	public City getCity(String cityId) {
		
		ClientResource cr = null;
		City list = null;
		try {
			cr = new ClientResource(uri + "/" + cityId);
			list = cr.get(City.class);
			
		} catch (ResourceException re) {
			System.err.println("Error when retrieving the city: " + cr.getResponse().getStatus());
		}
		
		return list;

	}
	
	//Get total price from all events of a city(cityId)
	

	public City addCity(City c) {
		
		ClientResource cr = null;
		City resultCity = null;
		try {
			cr = new ClientResource(uri);
			cr.setEntityBuffering(true);		
			resultCity = cr.post(c,City.class);
			
		} catch (ResourceException re) {
			System.err.println("Error when adding the city: " + cr.getResponse().getStatus());
		}
		
		return resultCity;
	}
	
	public boolean addEventToCity(String cityId, String eventId) {
		ClientResource cr = null;
		boolean exito = true;
		try {
			cr = new ClientResource(uri+"/"+cityId+"/"+eventId);
			cr.setEntityBuffering(true);
			cr.post(" ");
		} catch (ResourceException e) {
			System.err.println("Error al intentar añadir este evento a la ciudad"+cr.getResponse().getStatus());
			exito=false;
			throw e;
		}
		return exito;
	}
	
	public boolean updateCity(City city) {
		
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource(uri);
			cr.setEntityBuffering(true);		
			cr.put(city);
			
			
		} catch (ResourceException re) {
			System.err.println("Error when updating the city: " + cr.getResponse().getStatus());
			success = false;
		}
		
		return success;
	}
	

	public boolean deleteCity(String cityId) {
		ClientResource cr = null;
		boolean success = true;
		try {
			cr = new ClientResource(uri + "/" + cityId);
			cr.setEntityBuffering(true);	
			cr.delete();
			
		} catch (ResourceException re) {
			System.err.println("Error when deleting the city: " + cr.getResponse().getStatus());
			success = false;
			throw re;
		}
		
		return success;
		
	}
	
	public boolean deleteEventFromCity(String eventId, String cityId) {
		ClientResource cr = null;
		boolean exito = true;
		try {
			cr = new ClientResource(uri+"/"+cityId+"/"+eventId);
			cr.setEntityBuffering(true);
			cr.delete();
		} catch (ResourceException e){
			System.err.println("Error al intentar eliminar este evento de la ciudad"+cr.getResponse().getStatus());
			exito = false;
			throw e;
		}
		return exito;
	}
	
	
		
}
