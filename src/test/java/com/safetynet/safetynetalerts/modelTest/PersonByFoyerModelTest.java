package com.safetynet.safetynetalerts.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetynetalerts.model.PersonByFoyerModel;

@SpringBootTest
class PersonByFoyerModelTest {

	private PersonByFoyerModel personByFoyerModel = new PersonByFoyerModel("John", "Boyd", "841-874-6512", 16);

	@Test
	void toStringTest() {
		assertEquals(
				"PersonByFoyerModel(firstName=John, lastName_medications_allergies=Boyd, phone=841-874-6512, age=16)",
				personByFoyerModel.toString());
	}

	@Test
	void hashCodeTest() {
		assertEquals(434242860, personByFoyerModel.hashCode());
	}

	@Test
	void equalsTest() {
		assertTrue(new PersonByFoyerModel("John", "Boyd", "841-874-6512", 16).equals(personByFoyerModel));
	}

	@Test
	void equalsSameObjetTest() {
		assertTrue(personByFoyerModel.equals(personByFoyerModel));
	}

	@Test
	void equalsNullObjetTest() {
		PersonByFoyerModel personByFoyerModelNull = null;
		assertFalse(personByFoyerModel.equals(personByFoyerModelNull));
	}

	@Test
	void equalsDifferentObjetTest() {
		Object objetDifferent = new Object();
		assertFalse(personByFoyerModel.equals(objetDifferent));
	}

	@Test
	void setFirstNameTest() {
		personByFoyerModel.setFirstName("Johnn");
		assertEquals("Johnn", personByFoyerModel.getFirstName());
	}

	@Test
	void setLastNameTest() {
		personByFoyerModel.setLastName_medications_allergies("Boyduuu");
		assertEquals("Boyduuu", personByFoyerModel.getLastName_medications_allergies());
	}

	@Test
	void setPhoneTest() {
		personByFoyerModel.setPhone("02558999");
		assertEquals("02558999", personByFoyerModel.getPhone());
	}

	@Test
	void setAgeTest() {
		personByFoyerModel.setAge(3);
		assertEquals(3, personByFoyerModel.getAge());
	}

}
