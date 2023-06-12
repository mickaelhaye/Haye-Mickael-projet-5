package com.safetynet.safetynetalerts.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetynetalerts.model.PersonbyFirestationModel;

@SpringBootTest
class PersonbyFirestationModelTest {

	private PersonbyFirestationModel personbyFirestationModel = new PersonbyFirestationModel("John", "Boyd",
			"1509 Culver St", "841-874-6512");

	@Test
	void toStringTest() {
		assertEquals(
				"PersonbyFirestationModel(firstName=John, lastName=Boyd, address=1509 Culver St, phone=841-874-6512)",
				personbyFirestationModel.toString());
	}

	@Test
	void hashCodeTest() {
		assertEquals(1413191105, personbyFirestationModel.hashCode());
	}

	@Test
	void equalsTest() {
		assertTrue(new PersonbyFirestationModel("John", "Boyd", "1509 Culver St", "841-874-6512")
				.equals(personbyFirestationModel));
	}

	@Test
	void equalsSameObjetTest() {
		assertTrue(personbyFirestationModel.equals(personbyFirestationModel));
	}

	@Test
	void equalsTestNullObjet() {
		PersonbyFirestationModel personbyFirestationModelNull = null;
		assertFalse(personbyFirestationModel.equals(personbyFirestationModelNull));
	}

	@Test
	void equalsDifferentObjetTest() {
		Object objetDifferent = new Object();
		assertFalse(personbyFirestationModel.equals(objetDifferent));
	}

	@Test
	void setFirstNameTest() {
		personbyFirestationModel.setFirstName("Johnn");
		assertEquals("Johnn", personbyFirestationModel.getFirstName());
	}

	@Test
	void setLastNameTest() {
		personbyFirestationModel.setLastName("Boyduuu");
		assertEquals("Boyduuu", personbyFirestationModel.getLastName());
	}

	@Test
	void setAddressTest() {
		personbyFirestationModel.setAddress("1510 Culv");
		assertEquals("1510 Culv", personbyFirestationModel.getAddress());
	}

	@Test
	void setPhoneTest() {
		personbyFirestationModel.setPhone("02558999");
		assertEquals("02558999", personbyFirestationModel.getPhone());
	}

}
