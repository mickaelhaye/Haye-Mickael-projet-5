package com.safertyNet.safetyNetAlerts.repositoryTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.safetynet.safetynetalerts.CustomProperties;
import com.safetynet.safetynetalerts.model.PersonModel;
import com.safetynet.safetynetalerts.repository.FileEntryRepository;
import com.safetynet.safetynetalerts.repository.JsonFileWriteRepository;

@SpringBootTest
public class JsonFileWriteRepositoryTest {

	private List<PersonModel> persons = new ArrayList<PersonModel>();

	@MockBean // Mock
	private static CustomProperties prop;

	@Test
	public void writeFile() {

		File fileTest = new File("src/main/resources/datatest/dataTestWrite.json");
		fileTest.delete();
		assertEquals(false, fileTest.exists());

		when(prop.getJsonFilePath()).thenReturn("src/main/resources/datatest/dataTestWrite.json");

		// Ecriture d'un fichier test
		FileEntryRepository file = new FileEntryRepository();
		JsonFileWriteRepository JsonFilewriteRepository = new JsonFileWriteRepository(); // autowired
		JsonFilewriteRepository.writeFile(file);
		// verify(prop, Mockito.times(1)).getJsonFilePath(); remettre
		assertEquals(true, fileTest.exists());

	}

	@Test
	public void writeFileBadPath() {

		File fileTest = new File("src/main/resources/datatest/dataTestWrite.json");
		fileTest.delete();
		assertEquals(false, fileTest.exists());

		when(prop.getJsonFilePath()).thenReturn("");

		// Ecriture d'un fichier test
		FileEntryRepository file = new FileEntryRepository();
		JsonFileWriteRepository JsonFilewriteRepository = new JsonFileWriteRepository(); // autowired
		JsonFilewriteRepository.writeFile(file);
		// verify(prop, Mockito.times(1)).getJsonFilePath(); remettre
		assertEquals(false, fileTest.exists());

	}
}
