package com.safetynet.safetynetalerts.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetynetalerts.model.PersonModel;

@SpringBootTest
class PersonModelTest {

	private PersonModel personModel = new PersonModel("John", "Boyd", "1509 Culver St", "Culver", "97451",
			"841-874-6512", "jaboyd@email.com");

	@Test
	void toStringTest() {
		assertEquals(
				"PersonModel(firstName=John, lastName=Boyd, address=1509 Culver St, city=Culver, zip=97451, phone=841-874-6512, email=jaboyd@email.com)",
				personModel.toString());
	}

	@Test
	void hashCodeTest() {
		assertEquals(-1126054722, personModel.hashCode());
	}

	@Test
	void equalsTest() {
		assertTrue(
				new PersonModel("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com")
						.equals(personModel));
	}

	@Test
	void equalsSameObjetTest() {
		assertTrue(personModel.equals(personModel));
	}

	@Test
	void equalsNullObjetTest() {
		PersonModel personModelNull = null;
		assertFalse(personModel.equals(personModelNull));
	}

	@Test
	void equalsDifferentObjetTest() {
		Object objetDifferent = new Object();
		assertFalse(personModel.equals(objetDifferent));
	}

	@Test
	void setFirstNameTest() {
		personModel.setFirstName("Jean");
		assertEquals("Jean", personModel.getFirstName());
	}

	@Test
	void setLastNameTest() {
		personModel.setLastName("Michel");
		assertEquals("Michel", personModel.getLastName());
	}

	@Test
	void setAddressTest() {
		personModel.setAddress("1509 Culver St");
		assertEquals("1509 Culver St", personModel.getAddress());
	}

	@Test
	void setCityTest() {
		personModel.setCity("Londres");
		assertEquals("Londres", personModel.getCity());
	}

	@Test
	void setZipTest() {
		personModel.setZip("98798");
		assertEquals("98798", personModel.getZip());
	}

	@Test
	void setPhoneTest() {
		personModel.setPhone("687697698");
		assertEquals("687697698", personModel.getPhone());
	}

	@Test
	void setEmailTest() {
		personModel.setEmail("yyyy@toto.fr");
		assertEquals("yyyy@toto.fr", personModel.getEmail());
	}

}
