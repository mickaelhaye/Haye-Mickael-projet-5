package com.safertyNet.safetyNetAlerts.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.safetynet.safetynetalerts.CustomProperties;
import com.safetynet.safetynetalerts.repository.FileEntryRepository;
import com.safetynet.safetynetalerts.repository.JsonFileReadRepository;
import com.safetynet.safetynetalerts.service.PersonService;

@SpringBootTest
class PersonServiceTest {

	@MockBean // Mock
	private static CustomProperties prop;

	private PersonService personService = new PersonService();

	@Test
	void findByFirestationAListPersons() {
		//chargement d'un fichierJson
		when(prop.getJsonFilePath()).thenReturn("src/main/resources/datatest/dataTest.json");
		JsonFileReadRepository JsonFileReadRepository = new JsonFileReadRepository(); //autowired
		FileEntryRepository file = null;
		file = JsonFileReadRepository.recupFile();
		personService.setPersons(file.getPersons()); // à valider
		personService.setMedicalrecords(file.getMedicalrecords()); // à valider
		personService.setFirestations(file.getFirestations()); // à valider
		
		List<Object> listObjet = personService.findByFirestationAListPersons("2");
		String lastObjet = (String) listObjet.get(listObjet.size()-1);
		assertEquals("le nombre d'enfants est de 1", lastObjet);
	}

}
