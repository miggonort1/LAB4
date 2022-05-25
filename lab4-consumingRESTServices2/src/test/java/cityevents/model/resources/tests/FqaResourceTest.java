package cityevents.model.resources.tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import cityevents.model.Fqas;
import cityevents.model.resources.FqasResource;

public class FqaResourceTest {

	static Fqas fqa1, fqa2, fqa3, fqa4;
	static FqasResource sr = new FqasResource();
	
	@BeforeClass
	public static void setup() throws Exception {
		// Test: Examples of fqas to do the tests added to the API
		fqa1 = sr.addFqa(new Fqas("Can I bring an Umbrella","Please no umbrellas, they block the view of others."));
		fqa2 = sr.addFqa(new Fqas("Can I bring my dog","Please no umbrellas, Leashed dogs are always welcome, but we would like for you to consider a few things before bringing your pet."));
		fqa3 = sr.addFqa(new Fqas("Can I smoke on the park","You are not allowed to smoke on public zones."));
		fqa4 = sr.addFqa(new Fqas("What sort of public transportation is available","You only have 2 options, using train and bus."));
	}

	@AfterClass
	public static void tearDown() throws Exception {
		// Test: Delete all the fqas created on the API
		sr.deleteFqa(fqa1.getId());
		sr.deleteFqa(fqa2.getId());
		sr.deleteFqa(fqa3.getId());
	}
	
	@Test
	public void testGetAll() {
		System.out.println("----- TEST: GET ALL FQAS -----");
		// Test: Receive all the existing fqas on the API
		Collection<Fqas> fqas = sr.getFqas();
		
		// Check that the collection received is not null
		assertNotNull("Error: The collection of fqas is null", fqas);
		
		// Show all the fqas
		fqas.stream().forEach(System.out::println);
	}

	@Test
	public void testGetFqa() {
		System.out.println("----- TEST: GET FQA -----");
		// Test: Get an existing fqa of the API
		Fqas f = sr.getFqa(fqa1.getId());
		// Test: Get a non existing fqa of the API
		//Fqas f = sr.getFqa("f300");
		
		// Check that the fqa received correspond to the fqa created
		assertEquals("Error: The id of the fqa do not match", fqa1.getId(), f.getId());
		assertEquals("Error: The question of the fqa do not match", fqa1.getQuestion(), f.getQuestion());
		assertEquals("Error: The answer of the fqa do not match", fqa1.getAnswer(), f.getAnswer());
		
		// Show the existing fqa
		System.out.println(f);
	}	

	@Test
	public void testAddFqa() {		
		System.out.println("----- TEST: ADD FQA -----");
		// Test: Add a new fqa
		String fQuestion = "What items are prohibited?";
		String fAnswer =  "The following are prohibited: tents, umbrellas, grills, portable generators, items...";
		Fqas f = sr.addFqa(new Fqas(fQuestion, fAnswer));
		
		// Test: Add an existing fqa (still add a new fqa)
		//String fQuestion = fqa1.getQuestion();
		//String fAnswer = fqa1.getAnswer();
		//Fqas f = sr.addFqa(fqa1);
		
		// Check if the fqa fields correspond to the fqa sent
		assertNotNull("Error: The fqa is null", f);
		assertEquals("Error: The fqa's question has not been setted correctly", fQuestion, f.getQuestion());
		assertEquals("Error: The fqa's answer has not been setted correctly", fAnswer, f.getAnswer());
		
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
		
		// Test: Update a non existing fqa
		//String fQuestion = "Updated question";
		//Fqas fUpdate = new Fqas("This fqa is not on the list", "");
		//fUpdate.setQuestion(fQuestion);
		//boolean success = sr.updateFqa(fUpdate);
		
		// Check if the fqa was updated with the content
		assertTrue("Error: Error when updating the fqa", success);
		Fqas f  = sr.getFqa(fqa1.getId());
		assertEquals("Error: The fqa´s question has not been updated correctly", fQuestion, f.getQuestion());

		// Show the updated fqa
		System.out.println(f);
		//sr.deleteFqa(fUpdate.getId());
	}

	@Test
	public void testDeleteFqa() {
		System.out.println("----- TEST: REMOVE FQA -----");
		// Test: Delete an existing fqa
		boolean success = sr.deleteFqa(fqa4.getId());
		
		// Test: Delete a non existing fqa
		//boolean success = sr.deleteFqa("f90");
		
		//Check if the fqa was removed
		assertTrue("Error: Error when deleting the fqa", success);
		Fqas f = sr.getFqa(fqa4.getId());
		//Fqas f = sr.getFqa("f90");
		assertNull("Error: The fqa has not been deleted correctly", f);
		
		// Show the removed fqa message
		if (f == null) {
			System.out.println("The fqa was removed ? " + success);
		}
	}

}
