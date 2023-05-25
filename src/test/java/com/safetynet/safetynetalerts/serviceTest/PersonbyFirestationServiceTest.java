package com.safetynet.safetynetalerts.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetynetalerts.service.PersonbyFirestationService;

@SpringBootTest
class PersonbyFirestationServiceTest {

	private PersonbyFirestationService personbyFirestationService = new PersonbyFirestationService("John", "Boyd",
			"1509 Culver St", "841-874-6512");

	@Test
	void toStringTest() {
		assertEquals(
				"PersonbyFirestationService(firstName=John, lastName=Boyd, address=1509 Culver St, phone=841-874-6512)",
				personbyFirestationService.toString());
	}

	@Test
	void hashCodeTest() {
		assertEquals(1413191105, personbyFirestationService.hashCode());
	}

	@Test
	void equalsTest() {
		assertTrue(new PersonbyFirestationService("John", "Boyd", "1509 Culver St", "841-874-6512")
				.equals(personbyFirestationService));
	}

	@Test
	void equalsSameObjetTest() {
		assertTrue(personbyFirestationService.equals(personbyFirestationService));
	}

	@Test
	void equalsTestNullObjet() {
		PersonbyFirestationService personbyFirestationServiceNull = null;
		assertFalse(personbyFirestationService.equals(personbyFirestationServiceNull));
	}

	@Test
	void equalsDifferentObjetTest() {
		Object objetDifferent = new Object();
		assertFalse(personbyFirestationService.equals(objetDifferent));
	}

	@Test
	void setFirstNameTest() {
		personbyFirestationService.setFirstName("Johnn");
		assertEquals("Johnn", personbyFirestationService.getFirstName());
	}

	@Test
	void setLastNameTest() {
		personbyFirestationService.setLastName("Boyduuu");
		assertEquals("Boyduuu", personbyFirestationService.getLastName());
	}

	@Test
	void setAddressTest() {
		personbyFirestationService.setAddress("1510 Culv");
		assertEquals("1510 Culv", personbyFirestationService.getAddress());
	}

	@Test
	void setPhoneTest() {
		personbyFirestationService.setPhone("02558999");
		assertEquals("02558999", personbyFirestationService.getPhone());
	}

}
