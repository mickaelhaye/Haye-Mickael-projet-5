package com.safetynet.safetynetalerts.repositoryTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetynetalerts.service.FileEntryService;
import com.safetynet.safetynetalerts.service.PersonByAddressService;

@SpringBootTest
class FileEntryRepositoryTest {
	@Autowired
	FileEntryService fileEntryService;

	@Test
	void toStringTest() {
		assertEquals(

				"FileEntryService(persons=null, firestations=null, medicalrecords=null)", fileEntryService.toString());
	}

	@Test
	void equalsTest() {
		assertTrue(new FileEntryService().equals(fileEntryService));
	}

	@Test
	void equalsSameObjetTest() {
		assertTrue(fileEntryService.equals(fileEntryService));
	}

	@Test
	void equalsNullObjetTest() {
		PersonByAddressService fileEntryServiceNull = null;
		assertFalse(fileEntryService.equals(fileEntryServiceNull));
	}

	@Test
	void equalsDifferentObjetTest() {
		Object objetDifferent = new Object();
		assertFalse(fileEntryService.equals(objetDifferent));
	}

}
