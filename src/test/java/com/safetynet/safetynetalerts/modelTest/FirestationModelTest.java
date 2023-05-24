package com.safetynet.safetynetalerts.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetynetalerts.model.FirestationModel;

@SpringBootTest
class FirestationModelTest {

	private FirestationModel firestationModel = new FirestationModel("1509 Culver St", "3");

	@Test
	void toStringTest() {
		assertEquals("FirestationModel(address=1509 Culver St, station=3)", firestationModel.toString());
	}

	@Test
	void hashCodeTest() {
		assertEquals(-978717837, firestationModel.hashCode());
	}

	@Test
	void equalsTest() {
		assertTrue(new FirestationModel("1509 Culver St", "3").equals(firestationModel));
	}

	@Test
	void equalsTestSameObjet() {
		assertTrue(firestationModel.equals(firestationModel));
	}

	@Test
	void equalsTestNullObjet() {
		FirestationModel firestationModelNull = null;
		assertFalse(firestationModel.equals(firestationModelNull));
	}

	@Test
	void equalsTestDifferentObjet() {
		Object objetDifferent = new Object();
		assertFalse(firestationModel.equals(objetDifferent));
	}

	@Test
	void setAddressTest() {
		firestationModel.setAddress("1509 Culver St");
		assertEquals("1509 Culver St", firestationModel.getAddress());
	}

	@Test
	void setStationTest() {
		firestationModel.setStation("10");
		assertEquals("10", firestationModel.getStation());
	}

}
