package com.safetynet.safetynetalerts.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetynetalerts.model.PersonByAddressModel;

@SpringBootTest
class PersonByAddressModelTest {

	private PersonByAddressModel personByAddressModel = new PersonByAddressModel("John", "Boyd", "36999", 16, null,
			null);

	@Test
	void toStringTest() {
		assertEquals(
				"PersonByAddressModel(firstName=John, lastName=Boyd, phone=36999, age=16, medications=null, allergies=null)",
				personByAddressModel.toString());
	}

	@Test
	void hashCodeTest() {
		assertEquals(1314320086, personByAddressModel.hashCode());
	}

	@Test
	void equalsTest() {
		assertTrue(new PersonByAddressModel("John", "Boyd", "36999", 16, null, null).equals(personByAddressModel));
	}

	@Test
	void equalsSameObjetTest() {
		assertTrue(personByAddressModel.equals(personByAddressModel));
	}

	@Test
	void equalsNullObjetTest() {
		PersonByAddressModel personByAddressModelNull = null;
		assertFalse(personByAddressModel.equals(personByAddressModelNull));
	}

	@Test
	void equalsDifferentObjetTest() {
		Object objetDifferent = new Object();
		assertFalse(personByAddressModel.equals(objetDifferent));
	}

	@Test
	void setFirstNameTest() {
		personByAddressModel.setFirstName("Johnn");
		assertEquals("Johnn", personByAddressModel.getFirstName());
	}

	@Test
	void setLastNameTest() {
		personByAddressModel.setLastName("Boyduuu");
		assertEquals("Boyduuu", personByAddressModel.getLastName());
	}

	@Test
	void setPhoneTest() {
		personByAddressModel.setPhone("02558999");
		assertEquals("02558999", personByAddressModel.getPhone());
	}

	@Test
	void setAgeTest() {
		personByAddressModel.setAge(3);
		assertEquals(3, personByAddressModel.getAge());
	}

	@Test
	void setMedicationsTest() {
		personByAddressModel.setMedications(null);
		assertNull(personByAddressModel.getMedications());
	}

	@Test
	void setAllergiesTest() {
		personByAddressModel.setAllergies(null);
		assertNull(personByAddressModel.getAllergies());
	}

}
