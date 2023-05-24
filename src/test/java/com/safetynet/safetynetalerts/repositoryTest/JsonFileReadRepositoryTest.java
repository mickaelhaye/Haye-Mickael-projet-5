package com.safetynet.safetynetalerts.repositoryTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetynetalerts.CustomProperties;
import com.safetynet.safetynetalerts.repository.FileEntryRepository;
import com.safetynet.safetynetalerts.repository.JsonFileReadRepository;

@SpringBootTest
class JsonFileReadRepositoryTest {

	@Autowired
	private CustomProperties prop;

	@Autowired
	private JsonFileReadRepository JsonFileReadRepository;

	@Test
	void recupFile() {
		FileEntryRepository file = JsonFileReadRepository.recupFile(prop.getJsonFileTestPath());
		assertNotNull(file);
	}

	@Test
	void recupFileBadPath() {
		FileEntryRepository file = JsonFileReadRepository.recupFile("");
		assertNull(file);
	}

}
