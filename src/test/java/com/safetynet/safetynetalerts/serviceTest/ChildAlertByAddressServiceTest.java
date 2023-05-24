package com.safetynet.safetynetalerts.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetynetalerts.service.ChildAlertByAddressService;

@SpringBootTest
class ChildAlertByAddressServiceTest {

	private ChildAlertByAddressService childAlertByAddressService = new ChildAlertByAddressService("John", "Boyd", 16,
			null);

	@Test
	void toStringTest() {
		assertEquals("ChildAlertByAddressService(firstName=John, lastName=Boyd, age=16, personnDansMemeFoyer=null)",
				childAlertByAddressService.toString());
	}

	@Test
	void hashCodeTest() {
		assertEquals(-395093913, childAlertByAddressService.hashCode());
	}

	@Test
	void equalsTest() {
		assertTrue(new ChildAlertByAddressService("John", "Boyd", 16, null).equals(childAlertByAddressService));
	}

	@Test
	void equalsTestSameObjet() {
		assertTrue(childAlertByAddressService.equals(childAlertByAddressService));
	}

	@Test
	void equalsTestNullObjet() {
		ChildAlertByAddressService childAlertByAddressServiceNull = null;
		assertFalse(childAlertByAddressService.equals(childAlertByAddressServiceNull));
	}

	@Test
	void equalsTestDifferentObjet() {
		Object objetDifferent = new Object();
		assertFalse(childAlertByAddressService.equals(objetDifferent));
	}

	@Test
	void setFirstNameTest() {
		childAlertByAddressService.setFirstName("Johnn");
		assertEquals("Johnn", childAlertByAddressService.getFirstName());
	}

	@Test
	void setLastNameTest() {
		childAlertByAddressService.setLastName("Boyduuu");
		assertEquals("Boyduuu", childAlertByAddressService.getLastName());
	}

	@Test
	void setAgeTest() {
		childAlertByAddressService.setAge(3);
		assertEquals(3, childAlertByAddressService.getAge());
	}

	@Test
	void setPersonnDansMemeFoyerTest() {
		childAlertByAddressService.setPersonnDansMemeFoyer(null);
		assertNull(childAlertByAddressService.getPersonnDansMemeFoyer());
	}

}
