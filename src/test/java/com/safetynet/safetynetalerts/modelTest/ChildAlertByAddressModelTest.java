package com.safetynet.safetynetalerts.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetynetalerts.model.ChildAlertByAddressModel;

@SpringBootTest
class ChildAlertByAddressModelTest {

	private ChildAlertByAddressModel childAlertByAddressModel = new ChildAlertByAddressModel("John", "Boyd", 16, null);

	@Test
	void toStringTest() {
		assertEquals("ChildAlertByAddressModel(firstName=John, lastName=Boyd, age=16, personnDansMemeFoyer=null)",
				childAlertByAddressModel.toString());
	}

	@Test
	void hashCodeTest() {
		assertEquals(-395093913, childAlertByAddressModel.hashCode());
	}

	@Test
	void equalsTest() {
		assertTrue(new ChildAlertByAddressModel("John", "Boyd", 16, null).equals(childAlertByAddressModel));
	}

	@Test
	void equalsSameObjetTest() {
		assertTrue(childAlertByAddressModel.equals(childAlertByAddressModel));
	}

	@Test
	void equalsNullObjetTest() {
		ChildAlertByAddressModel childAlertByAddressModelNull = null;
		assertFalse(childAlertByAddressModel.equals(childAlertByAddressModelNull));
	}

	@Test
	void equalsDifferentObjetTest() {
		Object objetDifferent = new Object();
		assertFalse(childAlertByAddressModel.equals(objetDifferent));
	}

	@Test
	void setFirstNameTest() {
		childAlertByAddressModel.setFirstName("Johnn");
		assertEquals("Johnn", childAlertByAddressModel.getFirstName());
	}

	@Test
	void setLastNameTest() {
		childAlertByAddressModel.setLastName("Boyduuu");
		assertEquals("Boyduuu", childAlertByAddressModel.getLastName());
	}

	@Test
	void setAgeTest() {
		childAlertByAddressModel.setAge(3);
		assertEquals(3, childAlertByAddressModel.getAge());
	}

	@Test
	void setPersonnDansMemeFoyerTest() {
		childAlertByAddressModel.setPersonnDansMemeFoyer(null);
		assertNull(childAlertByAddressModel.getPersonnDansMemeFoyer());
	}

}
