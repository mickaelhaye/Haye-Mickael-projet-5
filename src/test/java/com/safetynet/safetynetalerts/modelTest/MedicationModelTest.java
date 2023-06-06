package com.safetynet.safetynetalerts.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetynetalerts.model.MedicationModel;

@SpringBootTest
class MedicationModelTest {

	private MedicationModel medicationModel = new MedicationModel("tetracyclaz:650mg");

	@Test
	void toStringTest() {
		assertEquals("MedicationModel(medication=tetracyclaz:650mg)", medicationModel.toString());
	}

	@Test
	void hashCodeTest() {
		assertEquals(-1904170154, medicationModel.hashCode());
	}

	@Test
	void equalsTest() {
		assertTrue(new MedicationModel("tetracyclaz:650mg").equals(medicationModel));
	}

	@Test
	void equalsSameObjetTest() {
		assertTrue(medicationModel.equals(medicationModel));
	}

	@Test
	void equalsNullObjetTest() {
		MedicationModel medicationModelNull = null;
		assertFalse(medicationModel.equals(medicationModelNull));
	}

	@Test
	void equalsDifferentObjetTest() {
		Object objetDifferent = new Object();
		assertFalse(medicationModel.equals(objetDifferent));
	}

	@Test
	void setFirstNameTest() {
		medicationModel.setMedication("tetracyclaz:650mg");
		assertEquals("tetracyclaz:650mg", medicationModel.getMedication());
	}

}
