package com.safetynet.safetynetalerts.repositoryTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetynetalerts.CustomProperties;
import com.safetynet.safetynetalerts.model.PersonModel;
import com.safetynet.safetynetalerts.repository.FileEntryRepository;
import com.safetynet.safetynetalerts.repository.JsonFileWriteRepository;

@SpringBootTest
public class JsonFileWriteRepositoryTest {

	private List<PersonModel> persons = new ArrayList<PersonModel>();

	@Autowired
	private CustomProperties prop;

	@Autowired
	private JsonFileWriteRepository JsonFilewriteRepository;

	@Test
	public void writeFile() {

		File fileTest = new File(prop.getJsonFileTestWritePath());
		fileTest.delete();
		assertEquals(false, fileTest.exists());

		// Ecriture d'un fichier test
		FileEntryRepository file = new FileEntryRepository();
		JsonFilewriteRepository.writeFile(file, prop.getJsonFileTestWritePath());
		assertEquals(true, fileTest.exists());

	}

	@Test
	public void writeFileBadPath() {

		File fileTest = new File(prop.getJsonFileTestWritePath());
		fileTest.delete();
		assertEquals(false, fileTest.exists());

		// Ecriture d'un fichier test
		FileEntryRepository file = new FileEntryRepository();
		JsonFilewriteRepository.writeFile(file, "");
		assertEquals(false, fileTest.exists());

	}
}
