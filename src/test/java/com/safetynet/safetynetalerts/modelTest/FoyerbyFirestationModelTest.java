package com.safetynet.safetynetalerts.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetynetalerts.model.FoyerbyFirestationModel;

@SpringBootTest
class FoyerbyFirestationModelTest {

	private FoyerbyFirestationModel foyerbyFirestationModel = new FoyerbyFirestationModel("1509 Culver St", null);

	@Test
	void toStringTest() {
		assertEquals("FoyerbyFirestationModel(address=1509 Culver St, listPersonByFoyer=null)",
				foyerbyFirestationModel.toString());
	}

	@Test
	void hashCodeTest() {
		assertEquals(-978717845, foyerbyFirestationModel.hashCode());
	}

	@Test
	void equalsTest() {
		assertTrue(new FoyerbyFirestationModel("1509 Culver St", null).equals(foyerbyFirestationModel));
	}

	@Test
	void equalsSameObjetTest() {
		assertTrue(foyerbyFirestationModel.equals(foyerbyFirestationModel));
	}

	@Test
	void equalsNullObjetTest() {
		FoyerbyFirestationModel foyerbyFirestationModelNull = null;
		assertFalse(foyerbyFirestationModel.equals(foyerbyFirestationModelNull));
	}

	@Test
	void equalsDifferentObjetTest() {
		Object objetDifferent = new Object();
		assertFalse(foyerbyFirestationModel.equals(objetDifferent));
	}

	@Test
	void setFirstNameTest() {
		foyerbyFirestationModel.setAddress("1510 Culver St");
		assertEquals("1510 Culver St", foyerbyFirestationModel.getAddress());
	}

	@Test
	void setLastNameTest() {
		foyerbyFirestationModel.setListPersonByFoyer(null);
		assertNull(foyerbyFirestationModel.getListPersonByFoyer());
	}

}
