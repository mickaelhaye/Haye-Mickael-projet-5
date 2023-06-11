package com.safetynet.safetynetalerts.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetynetalerts.model.FileEntryModel;
import com.safetynet.safetynetalerts.service.impl.JsonFileReadServiceImpl;

@SpringBootTest
class JsonFileReadServiceTest {

	@Autowired
	private JsonFileReadServiceImpl JsonFileReadRepository;

	@Test
	void recupFileTest() {
		FileEntryModel file;
		try {
			file = JsonFileReadRepository.recupFile();
			assertEquals(
					"PersonModel(firstName=John, lastName=Boyd, address=1509 Culver St, city=Culver, zip=97451, phone=841-874-6512, email=jaboyd@email.com)",
					file.getPersons().get(0).toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
