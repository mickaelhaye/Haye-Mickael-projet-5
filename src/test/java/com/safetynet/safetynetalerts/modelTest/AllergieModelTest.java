package com.safetynet.safetynetalerts.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetynetalerts.model.AllergieModel;

@SpringBootTest
class AllergieModelTest {

	private AllergieModel allergieModel = new AllergieModel("illisoxian");

	@Test
	void toStringTest() {
		assertEquals("AllergieModel(allergie=illisoxian)", allergieModel.toString());
	}

	@Test
	void hashCodeTest() {
		assertEquals(251468117, allergieModel.hashCode());
	}

	@Test
	void equalsTest() {
		assertTrue(new AllergieModel("illisoxian").equals(allergieModel));
	}

	@Test
	void equalsSameObjetTest() {
		assertTrue(allergieModel.equals(allergieModel));
	}

	@Test
	void equalsNullObjetTest() {
		AllergieModel allergieModelNull = null;
		assertFalse(allergieModel.equals(allergieModelNull));
	}

	@Test
	void equalsDifferentObjetTest() {
		Object objetDifferent = new Object();
		assertFalse(allergieModel.equals(objetDifferent));
	}

	@Test
	void setFirstNameTest() {
		allergieModel.setAllergie("illisoxian");
		assertEquals("illisoxian", allergieModel.getAllergie());
	}

}