package com.safetynet.safetynetalerts.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetynetalerts.service.PersonByFoyerService;

@SpringBootTest
class PersonByFoyerServiceTest {

	private PersonByFoyerService personByFoyerService = new PersonByFoyerService("John", "Boyd", "841-874-6512", 16);

	@Test
	void toStringTest() {
		assertEquals(
				"PersonByFoyerService(firstName=John, lastName_medications_allergies=Boyd, phone=841-874-6512, age=16)",
				personByFoyerService.toString());
	}

	@Test
	void hashCodeTest() {
		assertEquals(434242860, personByFoyerService.hashCode());
	}

	@Test
	void equalsTest() {
		assertTrue(new PersonByFoyerService("John", "Boyd", "841-874-6512", 16).equals(personByFoyerService));
	}

	@Test
	void equalsTestSameObjet() {
		assertTrue(personByFoyerService.equals(personByFoyerService));
	}

	@Test
	void equalsTestNullObjet() {
		PersonByFoyerService personByFoyerServiceNull = null;
		assertFalse(personByFoyerService.equals(personByFoyerServiceNull));
	}

	@Test
	void equalsTestDifferentObjet() {
		Object objetDifferent = new Object();
		assertFalse(personByFoyerService.equals(objetDifferent));
	}

	@Test
	void setFirstNameTest() {
		personByFoyerService.setFirstName("Johnn");
		assertEquals("Johnn", personByFoyerService.getFirstName());
	}

	@Test
	void setLastNameTest() {
		personByFoyerService.setLastName_medications_allergies("Boyduuu");
		assertEquals("Boyduuu", personByFoyerService.getLastName_medications_allergies());
	}

	@Test
	void setPhoneTest() {
		personByFoyerService.setPhone("02558999");
		assertEquals("02558999", personByFoyerService.getPhone());
	}

	@Test
	void setAgeTest() {
		personByFoyerService.setAge(3);
		assertEquals(3, personByFoyerService.getAge());
	}

}
