package com.safetynet.safetynetalerts.repositoryTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetynetalerts.CustomProperties;
import com.safetynet.safetynetalerts.repository.FileEntryRepository;
import com.safetynet.safetynetalerts.repository.JsonFileWriteRepository;

@SpringBootTest
public class JsonFileWriteRepositoryTest {

	@Autowired
	private CustomProperties prop;

	@Autowired
	private JsonFileWriteRepository JsonFilewriteRepository;

	@Test
	public void writeFileTest() {

		File fileTest = new File(prop.getJsonFileTestWritePath());
		fileTest.delete();
		assertEquals(false, fileTest.exists());

		// Ecriture d'un fichier test
		FileEntryRepository file = new FileEntryRepository();
		try {
			JsonFilewriteRepository.writeFile(file, prop.getJsonFileTestWritePath());
			assertEquals(true, fileTest.exists());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void writeFileBadPathTest() {

		File fileTest = new File(prop.getJsonFileTestWritePath());
		fileTest.delete();
		assertEquals(false, fileTest.exists());

		// Ecriture d'un fichier test
		FileEntryRepository file = new FileEntryRepository();
		try {
			JsonFilewriteRepository.writeFile(file, "");
			assertEquals(false, fileTest.exists());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
