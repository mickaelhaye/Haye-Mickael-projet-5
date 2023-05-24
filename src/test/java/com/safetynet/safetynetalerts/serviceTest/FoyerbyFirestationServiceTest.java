package com.safetynet.safetynetalerts.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetynetalerts.service.FoyerbyFirestationService;

@SpringBootTest
class FoyerbyFirestationServiceTest {

	private FoyerbyFirestationService foyerbyFirestationService = new FoyerbyFirestationService("1509 Culver St", null);

	@Test
	void toStringTest() {
		String toto = foyerbyFirestationService.toString();
		assertEquals("FoyerbyFirestationService(address=1509 Culver St, listPersonByFoyer=null)",
				foyerbyFirestationService.toString());
	}

	@Test
	void hashCodeTest() {
		assertEquals(-978717845, foyerbyFirestationService.hashCode());
	}

	@Test
	void equalsTest() {
		assertTrue(new FoyerbyFirestationService("1509 Culver St", null).equals(foyerbyFirestationService));
	}

	@Test
	void equalsTestSameObjet() {
		assertTrue(foyerbyFirestationService.equals(foyerbyFirestationService));
	}

	@Test
	void equalsTestNullObjet() {
		FoyerbyFirestationService foyerbyFirestationServiceNull = null;
		assertFalse(foyerbyFirestationService.equals(foyerbyFirestationServiceNull));
	}

	@Test
	void equalsTestDifferentObjet() {
		Object objetDifferent = new Object();
		assertFalse(foyerbyFirestationService.equals(objetDifferent));
	}

	@Test
	void setFirstNameTest() {
		foyerbyFirestationService.setAddress("1510 Culver St");
		assertEquals("1510 Culver St", foyerbyFirestationService.getAddress());
	}

	@Test
	void setLastNameTest() {
		foyerbyFirestationService.setListPersonByFoyer(null);
		assertNull(foyerbyFirestationService.getListPersonByFoyer());
	}

}
