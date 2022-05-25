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
		
		// Test fqa 1
		fqa1 = sr.addFqa(new Fqas("id","question","answer"));
		
		
	}

	@AfterClass
	public static void tearDown() throws Exception {
		sr.deleteFqa(fqa1.getId());
		sr.deleteFqa(fqa2.getId());
	}
	
	@Test
	public void testGetAll() {
		Collection<Fqas> fqa = sr.getFqas();
		
		assertNotNull("The collection of fqas is null", fqa);
		
		// Show result
		System.out.println("Listing all fqas:");
		int i=1;
		for (Fqas f : fqa) {
			System.out.println("Event " + i++ + " : " + f.getQuestion() +f.getAnswer()+" (ID=" + f.getId() + ")");
		}
	}

	@Test
	public void testGetFqa() {
		Fqas f = sr.getFqa(fqa1.getId());
		
		assertEquals("The id of the fqas do not match", fqa1.getId(), f.getId());
		assertEquals("The name of the fqas do not match", fqa1.getQuestion(), f.getQuestion());
		
		// Show result
		System.out.println("Fqa id: " +  f.getId());
		System.out.println("Fqa question: " +  f.getQuestion());

	}

	@Test
	public void testAddFqa() {
		String fqaQuestion = "Add fqa test question";
		String fqaAnswer = "Add fqa test answer";
		
		
		fqa4 = sr.addFqa(new Fqas(fqaQuestion,fqaAnswer));
		
		assertNotNull("Error when adding the fqa", fqa4);
		assertEquals("The fqa's question has not been setted correctly", fqaQuestion, fqa4.getQuestion());
		assertEquals("The fqa's answer has not been setted correctly", fqaAnswer, fqa4.getAnswer());
	}

	@Test
	public void testUpdateFqa() {
		String fqaQuestion = "Updated question";

		// Update city
		fqa1.setQuestion(fqaQuestion); 

		boolean success = sr.updateFqa(fqa1);
		
		assertTrue("Error when updating the fqa", success);
		
		Fqas f  = sr.getFqa(fqa1.getId());
		assertEquals("The fqa´s quesrtion has not been updated correctly", fqaQuestion, f.getQuestion());

	}

	@Test
	public void testDeleteFqa() {
		boolean success = sr.deleteFqa(fqa3.getId());
		assertTrue("Error when deleting the fqa", success);
		
		Fqas f = sr.getFqa(fqa3.getId());
		assertNull("The fqa has not been deleted correctly", f);
	}

}
