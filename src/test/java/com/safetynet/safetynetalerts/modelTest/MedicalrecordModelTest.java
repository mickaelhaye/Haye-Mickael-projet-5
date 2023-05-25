package com.safetynet.safetynetalerts.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetynetalerts.model.MedicalrecordModel;

@SpringBootTest
class MedicalrecordModelTest {

	private MedicalrecordModel medicalrecordModel = new MedicalrecordModel("John", "Boyd", "03/06/1984", null, null);

	@Test
	void toStringTest() {
		assertEquals(
				"MedicalrecordModel(firstName=John, lastName=Boyd, birthdate=03/06/1984, medications=null, allergies=null)",
				medicalrecordModel.toString());
	}

	@Test
	void hashCodeTest() {
		assertEquals(2039384027, medicalrecordModel.hashCode());
	}

	@Test
	void equalsTest() {
		assertTrue(new MedicalrecordModel("John", "Boyd", "03/06/1984", null, null).equals(medicalrecordModel));
	}

	@Test
	void equalsSameObjetTest() {
		assertTrue(medicalrecordModel.equals(medicalrecordModel));
	}

	@Test
	void equalsNullObjetTest() {
		MedicalrecordModel medicalrecordModelNull = null;
		assertFalse(medicalrecordModel.equals(medicalrecordModelNull));
	}

	@Test
	void equalsDifferentObjetTest() {
		Object objetDifferent = new Object();
		assertFalse(medicalrecordModel.equals(objetDifferent));
	}

	@Test
	void setFirstNameTest() {
		medicalrecordModel.setFirstName("Jean");
		assertEquals("Jean", medicalrecordModel.getFirstName());
	}

	@Test
	void setLastNameTest() {
		medicalrecordModel.setLastName("Michel");
		assertEquals("Michel", medicalrecordModel.getLastName());
	}

	@Test
	void setBirthdateTest() {
		medicalrecordModel.setBirthdate("03/06/1986");
		assertEquals("03/06/1986", medicalrecordModel.getBirthdate());
	}

	@Test
	void setMedicationsTest() {
		medicalrecordModel.setMedications(null);
		assertNull(medicalrecordModel.getMedications());
	}

	@Test
	void setAllergiesTest() {
		medicalrecordModel.setAllergies(null);
		assertNull(medicalrecordModel.getAllergies());
	}

}
