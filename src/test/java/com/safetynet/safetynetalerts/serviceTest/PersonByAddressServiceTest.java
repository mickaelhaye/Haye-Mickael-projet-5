package com.safetynet.safetynetalerts.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetynetalerts.service.PersonByAddressService;

@SpringBootTest
class PersonByAddressServiceTest {

	private PersonByAddressService personByAddressService = new PersonByAddressService("John", "Boyd", "36999", 16,
			null, null);

	@Test
	void toStringTest() {
		assertEquals(
				"PersonByAddressService(firstName=John, lastName=Boyd, phone=36999, age=16, medications=null, allergies=null)",
				personByAddressService.toString());
	}

	@Test
	void hashCodeTest() {
		assertEquals(1314320086, personByAddressService.hashCode());
	}

	@Test
	void equalsTest() {
		assertTrue(new PersonByAddressService("John", "Boyd", "36999", 16, null, null).equals(personByAddressService));
	}

	@Test
	void equalsSameObjetTest() {
		assertTrue(personByAddressService.equals(personByAddressService));
	}

	@Test
	void equalsNullObjetTest() {
		PersonByAddressService personByAddressServiceNull = null;
		assertFalse(personByAddressService.equals(personByAddressServiceNull));
	}

	@Test
	void equalsDifferentObjetTest() {
		Object objetDifferent = new Object();
		assertFalse(personByAddressService.equals(objetDifferent));
	}

	@Test
	void setFirstNameTest() {
		personByAddressService.setFirstName("Johnn");
		assertEquals("Johnn", personByAddressService.getFirstName());
	}

	@Test
	void setLastNameTest() {
		personByAddressService.setLastName("Boyduuu");
		assertEquals("Boyduuu", personByAddressService.getLastName());
	}

	@Test
	void setPhoneTest() {
		personByAddressService.setPhone("02558999");
		assertEquals("02558999", personByAddressService.getPhone());
	}

	@Test
	void setAgeTest() {
		personByAddressService.setAge(3);
		assertEquals(3, personByAddressService.getAge());
	}

	@Test
	void setMedicationsTest() {
		personByAddressService.setMedications(null);
		assertNull(personByAddressService.getMedications());
	}

	@Test
	void setAllergiesTest() {
		personByAddressService.setAllergies(null);
		assertNull(personByAddressService.getAllergies());
	}

}
