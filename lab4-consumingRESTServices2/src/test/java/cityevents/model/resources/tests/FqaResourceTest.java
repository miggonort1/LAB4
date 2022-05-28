package cityevents.model.resources.tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.restlet.resource.ResourceException;

import cityevents.model.Fqas;
import cityevents.model.resources.FqasResource;

public class FqaResourceTest {

	static Fqas fqa1, fqa2, fqa3, fqa4, fqa5;
	static FqasResource sr = new FqasResource();
	
	@BeforeClass
	public static void setup() throws Exception {
		// Test: Examples of fqas to do the tests added to the API
		fqa1 = sr.addFqa(new Fqas("Can I bring an Umbrella","Please no umbrellas, they block the view of others."));
		fqa2 = sr.addFqa(new Fqas("Can I bring my dog","Please no umbrellas, Leashed dogs are always welcome, but we would like for you to consider a few things before bringing your pet."));
		fqa3 = sr.addFqa(new Fqas("Can I smoke on the park","You are not allowed to smoke on public zones."));
		fqa4 = sr.addFqa(new Fqas("What sort of public transportation is available","You only have 2 options, using train and bus."));
		fqa5 = sr.addFqa(new Fqas("Where I can find a cheap place?","Near the river you can find a small shop to buy cheap food"));
	}

	@AfterClass
	public static void tearDown() throws Exception {
		// Test: Delete all the fqas created on the API
		sr.deleteFqa(fqa1.getId());
		sr.deleteFqa(fqa2.getId());
		sr.deleteFqa(fqa3.getId());
		sr.deleteFqa(fqa4.getId());
	}
	
	@Test
	public void testGetAll() {
		System.out.println("----- TEST: GET ALL FQAS -----");
		// Test: Receive all the existing fqas on the API
		Collection<Fqas> fqas = sr.getFqas();
		
		// Check that the collection received is not null
		assertNotNull("The collection of fqas is null", fqas);
		
		// Show all the fqas
		fqas.stream().forEach(System.out::println);
	}

	@Test
	public void testGetFqa() {
		System.out.println("----- TEST: GET FQA -----");
		// Test: Get an existing fqa of the API
		Fqas f = sr.getFqa(fqa1.getId());
		
		// Check that the fqa received correspond to the fqa created
		assertEquals("The id of the fqa do not match", fqa1.getId(), f.getId());
		assertEquals("The question of the fqa do not match", fqa1.getQuestion(), f.getQuestion());
		assertEquals("The answer of the fqa do not match", fqa1.getAnswer(), f.getAnswer());
		
		// Show the existing fqa
		System.out.println(f);
	}	
	
	@Test (expected = ResourceException.class)
	public void testGetNonExistingFqa() {
		System.out.println("----- TEST: GET NON EXISTING FQA -----");
		// Test: Get a non existing fqa of the API
		sr.getFqa("f300");
	}

	@Test
	public void testAddFqa() {		
		System.out.println("----- TEST: ADD FQA -----");
		// Test: Post a new fqa
		String fQuestion = "What items are prohibited?";
		String fAnswer =  "The following are prohibited: tents, umbrellas, grills, portable generators, items...";
		Fqas f = sr.addFqa(new Fqas(fQuestion, fAnswer));
		
		// Check if the fqa fields correspond to the fqa sent
		assertNotNull("The fqa is null", f);
		assertEquals("The fqa's question has not been setted correctly", fQuestion, f.getQuestion());
		assertEquals("The fqa's answer has not been setted correctly", fAnswer, f.getAnswer());
		
		// Show the existing fqa
		System.out.println(f);
		sr.deleteFqa(f.getId());
	}

	@Test 
	public void testUpdateFqa() {
		System.out.println("----- TEST: UPDATE FQA -----");
		// Test: Update an existing fqa
		String fQuestion = "Updated question";
		fqa1.setQuestion(fQuestion); 
		boolean success = sr.updateFqa(fqa1);
		
		// Check if the fqa was updated with the content
		assertTrue("Error when updating the fqa", success);
		Fqas f  = sr.getFqa(fqa1.getId());
		assertEquals("The fqa´s question has not been updated correctly", fQuestion, f.getQuestion());

		// Show the updated fqa
		System.out.println(f);
	}
	
	@Test (expected = ResourceException.class)
	public void testUpdateNonExistingFqa() {
		System.out.println("----- TEST: UPDATE NON EXISTING FQA -----");
		// Test: Update a non existing fqa
		String fQuestion = "Updated question";
		Fqas fUpdate = new Fqas("This fqa is not on the list", "");
		fUpdate.setQuestion(fQuestion);
		sr.updateFqa(fUpdate);
	}

	@Test
	public void testDeleteFqa() {
		System.out.println("----- TEST: REMOVE FQA -----");
		// Test: Delete an existing fqa
		boolean success = sr.deleteFqa(fqa5.getId());
		
		//Check if the fqa was removed
		assertTrue("Error when deleting the fqa", success);
	}
	
	@Test (expected = ResourceException.class)
	public void testDeleteNonExistingFqa() {
		System.out.println("----- TEST: REMOVE NON EXISTING FQA -----");
		// Test: Delete a non existing fqa
		sr.deleteFqa("f90");
	}

}
