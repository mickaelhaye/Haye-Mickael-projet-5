package com.safertyNet.safetyNetAlerts.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.safetynet.safetynetalerts.CustomProperties;
import com.safetynet.safetynetalerts.model.FirestationModel;
import com.safetynet.safetynetalerts.repository.FileEntryRepository;
import com.safetynet.safetynetalerts.repository.JsonFileReadRepository;
import com.safetynet.safetynetalerts.service.FirestationService;

@SpringBootTest
class FirestationServiceTest {

	@MockBean // Mock
	private static CustomProperties prop;

	private static FirestationService firestationService = new FirestationService();

	@BeforeEach
	void setUpPerTest() {
		// chargement d'un fichierJson
		
		when(prop.getJsonFilePath()).thenReturn("src/main/resources/datatest/dataTest.json");
		JsonFileReadRepository JsonFileReadRepository = new JsonFileReadRepository(); // autowired
		FileEntryRepository file = null;
		file = JsonFileReadRepository.recupFile();
		firestationService.setFirestations(file.getFirestations()); // à valider
	}

	@Test
	void addFirestation() {

		FirestationModel firestation = new FirestationModel("19 square cholet", "9");
		firestationService.addFirestation(firestation);
		List<FirestationModel> firestations = firestationService.getFirestations();
		firestation = firestations.get(firestations.size() - 1);
		assertEquals("19 square cholet", firestation.getAddress());
	}

	@Test
	void updateFirestation() {

		List<FirestationModel> firestations = firestationService.getFirestations();
		FirestationModel firestation = firestations.get(0);
		String oldStation = firestation.getStation();

		firestation = new FirestationModel("1509 Culver St", "8");
		firestationService.updateFirestation(firestation);
		firestations = firestationService.getFirestations();
		firestation = firestations.get(0);
		String newStation = firestation.getStation();
		assertNotEquals(oldStation, newStation);
	}

	@Test
	void updateFirestationBadFirestation() {

		List<FirestationModel> firestations = firestationService.getFirestations();
		FirestationModel firestation = firestations.get(0);
		String oldStation = firestation.getStation();

		firestation = new FirestationModel("mars", "8");
		firestationService.updateFirestation(firestation);
		firestations = firestationService.getFirestations();
		firestation = firestations.get(0);
		String newStation = firestation.getStation();
		assertEquals(oldStation, newStation);
	}

	@Test
	void deleteFirestationByStation() {
		List<FirestationModel> firestations = firestationService.getFirestations();
		int nbrFirestationOld = firestations.size();

		firestationService.deleteFirestation("2");
		firestations = firestationService.getFirestations();
		int nbrFirestationNew = firestations.size();
		assertEquals(nbrFirestationOld - 3, nbrFirestationNew);

	}

	@Test
	void deleteFirestationByStationBadFirestation() {
		List<FirestationModel> firestations = firestationService.getFirestations();
		int nbrFirestationOld = firestations.size();

		firestationService.deleteFirestation("8");
		firestations = firestationService.getFirestations();
		int nbrFirestationNew = firestations.size();
		assertEquals(nbrFirestationOld, nbrFirestationNew);

	}

	@Test
	void deleteFirestationByAddress() {
		List<FirestationModel> firestations = firestationService.getFirestations();
		int nbrFirestationOld = firestations.size();

		firestationService.deleteFirestation("1509 Culver St");
		firestations = firestationService.getFirestations();
		int nbrFirestationNew = firestations.size();
		assertEquals(nbrFirestationOld - 1, nbrFirestationNew);

	}

	@Test
	void deleteFirestationByAddressBadFirestation() {
		List<FirestationModel> firestations = firestationService.getFirestations();
		int nbrFirestationOld = firestations.size();

		firestationService.deleteFirestation("mars");
		firestations = firestationService.getFirestations();
		int nbrFirestationNew = firestations.size();
		assertEquals(nbrFirestationOld, nbrFirestationNew);

	}

}
