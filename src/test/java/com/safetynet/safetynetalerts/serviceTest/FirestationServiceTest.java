package com.safetynet.safetynetalerts.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetynetalerts.CustomProperties;
import com.safetynet.safetynetalerts.model.FirestationModel;
import com.safetynet.safetynetalerts.repository.FileEntryRepository;
import com.safetynet.safetynetalerts.repository.JsonFileReadRepository;
import com.safetynet.safetynetalerts.service.FirestationService;

@SpringBootTest
class FirestationServiceTest {

	@Autowired
	private CustomProperties prop;

	@Autowired
	private JsonFileReadRepository JsonFileReadRepository;

	private static FirestationService firestationService = new FirestationService();

	@BeforeEach
	void setUpPerTest() {
		// chargement d'un fichierJson
		FileEntryRepository file;
		try {
			file = JsonFileReadRepository.recupFile(prop.getJsonFileTestPath());
			firestationService.setFirestations(file.getFirestations());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void addFirestationTest() {

		List<FirestationModel> firestations = firestationService.getFirestations();
		if (firestations != null) {
			FirestationModel firestation = new FirestationModel("19 square cholet", "9");
			firestationService.addFirestation(firestation);
			firestation = firestations.get(firestations.size() - 1);
			assertEquals("19 square cholet", firestation.getAddress());
		}

	}

	@Test
	void updateFirestationTest() {

		List<FirestationModel> firestations = firestationService.getFirestations();
		if (firestations != null) {
			FirestationModel firestation = firestations.get(0);
			String oldStation = firestation.getStation();

			firestation = new FirestationModel("1509 Culver St", "8");
			firestationService.updateFirestation(firestation);
			firestations = firestationService.getFirestations();
			firestation = firestations.get(0);
			String newStation = firestation.getStation();
			assertNotEquals(oldStation, newStation);
		}
	}

	@Test
	void updateFirestationBadFirestationTest() {

		List<FirestationModel> firestations = firestationService.getFirestations();
		if (firestations != null) {
			FirestationModel firestation = firestations.get(0);
			String oldStation = firestation.getStation();

			firestation = new FirestationModel("mars", "8");
			firestationService.updateFirestation(firestation);
			firestations = firestationService.getFirestations();
			firestation = firestations.get(0);
			String newStation = firestation.getStation();
			assertEquals(oldStation, newStation);
		}
	}

	@Test
	void deleteFirestationByStationTest() {
		List<FirestationModel> firestations = firestationService.getFirestations();
		if (firestations != null) {
			int nbrFirestationOld = firestations.size();

			firestationService.deleteFirestation("2");
			firestations = firestationService.getFirestations();
			int nbrFirestationNew = firestations.size();
			assertEquals(nbrFirestationOld - 3, nbrFirestationNew);
		}
	}

	@Test
	void deleteFirestationByStationBadFirestationTest() {
		List<FirestationModel> firestations = firestationService.getFirestations();
		if (firestations != null) {
			int nbrFirestationOld = firestations.size();

			firestationService.deleteFirestation("8");
			firestations = firestationService.getFirestations();
			int nbrFirestationNew = firestations.size();
			assertEquals(nbrFirestationOld, nbrFirestationNew);
		}
	}

	@Test
	void deleteFirestationByAddressTest() {
		List<FirestationModel> firestations = firestationService.getFirestations();
		if (firestations != null) {
			int nbrFirestationOld = firestations.size();

			firestationService.deleteFirestation("1509 Culver St");
			firestations = firestationService.getFirestations();
			int nbrFirestationNew = firestations.size();
			assertEquals(nbrFirestationOld - 1, nbrFirestationNew);
		}
	}

	@Test
	void deleteFirestationByAddressBadFirestationTest() {
		List<FirestationModel> firestations = firestationService.getFirestations();
		if (firestations != null) {
			int nbrFirestationOld = firestations.size();

			firestationService.deleteFirestation("mars");
			firestations = firestationService.getFirestations();
			int nbrFirestationNew = firestations.size();
			assertEquals(nbrFirestationOld, nbrFirestationNew);
		}
	}

	@Test
	void equalsTest() {
		FirestationService firestationService2 = new FirestationService();
		FirestationService firestationService3 = new FirestationService();
		assertTrue(firestationService2.equals(firestationService3));
	}

	@Test
	void equalsSameObjetTest() {
		assertTrue(firestationService.equals(firestationService));
	}

	@Test
	void equalsNullObjetTest() {
		FirestationService firestationServiceNull = null;
		assertFalse(firestationService.equals(firestationServiceNull));
	}

	@Test
	void equalsDifferentObjetTest() {
		Object objetDifferent = new Object();
		assertFalse(firestationService.equals(objetDifferent));
	}

}
