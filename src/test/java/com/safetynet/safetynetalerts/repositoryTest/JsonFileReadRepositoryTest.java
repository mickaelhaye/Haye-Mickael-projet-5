package com.safetynet.safetynetalerts.repositoryTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
		FileEntryRepository file;
		try {
			file = JsonFileReadRepository.recupFile(prop.getJsonFileTestPath());
			assertEquals(
					"PersonModel(firstName=John, lastName=Boyd, address=1509 Culver St, city=Culver, zip=97451, phone=841-874-6512, email=jaboyd@email.com)",
					file.getPersons().get(0).toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void recupFileBadPath() {
		FileEntryRepository file;
		try {
			file = JsonFileReadRepository.recupFile("");
			assertNull(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
