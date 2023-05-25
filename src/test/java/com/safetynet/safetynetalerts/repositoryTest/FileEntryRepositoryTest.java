package com.safetynet.safetynetalerts.repositoryTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetynetalerts.repository.FileEntryRepository;
import com.safetynet.safetynetalerts.service.PersonByAddressService;

@SpringBootTest
class FileEntryRepositoryTest {
	@Autowired
	FileEntryRepository fileEntryRepository;

	@Test
	void toStringTest() {
		assertEquals(

				"FileEntryRepository(persons=null, firestations=null, medicalrecords=null)",
				fileEntryRepository.toString());
	}

	@Test
	void equalsTest() {
		assertTrue(new FileEntryRepository().equals(fileEntryRepository));
	}

	@Test
	void equalsSameObjetTest() {
		assertTrue(fileEntryRepository.equals(fileEntryRepository));
	}

	@Test
	void equalsNullObjetTest() {
		PersonByAddressService fileEntryRepositoryNull = null;
		assertFalse(fileEntryRepository.equals(fileEntryRepositoryNull));
	}

	@Test
	void equalsDifferentObjetTest() {
		Object objetDifferent = new Object();
		assertFalse(fileEntryRepository.equals(objetDifferent));
	}

}
