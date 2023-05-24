package com.safetynet.safetynetalerts.repositoryTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.safetynet.safetynetalerts.CustomProperties;
import com.safetynet.safetynetalerts.repository.FileEntryRepository;
import com.safetynet.safetynetalerts.repository.JsonFileReadRepository;

@SpringBootTest
class JsonFileReadRepositoryTest {

	@MockBean // Mock
	private static CustomProperties prop;

	@Test
	void recupFile() {
		when(prop.getJsonFilePath()).thenReturn("src/main/resources/datatest/dataTest.json");
		JsonFileReadRepository JsonFileReadRepository = new JsonFileReadRepository(); //autowired
		
		
		FileEntryRepository file = null;
		file = JsonFileReadRepository.recupFile();
		
		//verify(prop, Mockito.times(1)).getJsonFilePath(); remettre 
		assertNotNull(file);
	}

	@Test
	void recupFileBadPath() {
		when(prop.getJsonFilePath()).thenReturn("");
		JsonFileReadRepository jsonFileReadRepository = new JsonFileReadRepository(); //autowired
		
		
		FileEntryRepository file = null;
		file = jsonFileReadRepository.recupFile();
		
		//verify(prop, Mockito.times(1)).getJsonFilePath(); remettre 
		assertNull(file);
	}

}
