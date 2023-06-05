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

import com.safetynet.safetynetalerts.model.FirestationModel;
import com.safetynet.safetynetalerts.service.FirestationService;
import com.safetynet.safetynetalerts.service.JsonFileReadService;

@SpringBootTest
class FirestationServiceTest {

	@Autowired
	private JsonFileReadService jsonFileReadRepository;

	@Autowired
	private FirestationService firestationService;

	@BeforeEach
	void setUpPerTest() {
		// chargement d'un fichierJson
		try {
			jsonFileReadRepository.recupFile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void addFirestationTest() {

		try {
			List<FirestationModel> firestations = jsonFileReadRepository.getFile().getFirestations();
			FirestationModel firestation = new FirestationModel("19 square cholet", "9");
			firestationService.addFirestation(firestation);
			firestation = firestations.get(firestations.size() - 1);
			assertEquals("19 square cholet", firestation.getAddress());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void addFirestationBadFirestationTest() {

		try {
			List<FirestationModel> firestations = jsonFileReadRepository.getFile().getFirestations();
			int nbrFirestationOld = firestations.size();
			FirestationModel firestation = new FirestationModel("1509 Culver St", "3");
			firestationService.addFirestation(firestation);
			int nbrFirestationNew = firestations.size();
			assertEquals(nbrFirestationOld, nbrFirestationNew);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void updateFirestationTest() {

		try {
			List<FirestationModel> firestations = jsonFileReadRepository.getFile().getFirestations();
			FirestationModel firestation = firestations.get(0);
			String oldStation = firestation.getStation();

			firestation = new FirestationModel("1509 Culver St", "8");
			firestationService.updateFirestation(firestation);
			firestations = jsonFileReadRepository.getFile().getFirestations();
			firestation = firestations.get(0);
			String newStation = firestation.getStation();
			assertNotEquals(oldStation, newStation);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void updateFirestationBadFirestationTest() {

		try {
			List<FirestationModel> firestations = jsonFileReadRepository.getFile().getFirestations();
			FirestationModel firestation = firestations.get(0);
			String oldStation = firestation.getStation();

			firestation = new FirestationModel("mars", "8");
			firestationService.updateFirestation(firestation);
			firestations = jsonFileReadRepository.getFile().getFirestations();
			firestation = firestations.get(0);
			String newStation = firestation.getStation();
			assertEquals(oldStation, newStation);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void deleteFirestationByStationTest() {
		try {
			List<FirestationModel> firestations = jsonFileReadRepository.getFile().getFirestations();
			int nbrFirestationOld = firestations.size();

			firestationService.deleteFirestationByStation("2");
			firestations = jsonFileReadRepository.getFile().getFirestations();
			int nbrFirestationNew = firestations.size();
			assertEquals(nbrFirestationOld - 3, nbrFirestationNew);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void deleteFirestationByStationBadFirestationTest() {
		try {
			List<FirestationModel> firestations = jsonFileReadRepository.getFile().getFirestations();
			int nbrFirestationOld = firestations.size();

			firestationService.deleteFirestationByStation("8");
			firestations = jsonFileReadRepository.getFile().getFirestations();
			int nbrFirestationNew = firestations.size();
			assertEquals(nbrFirestationOld, nbrFirestationNew);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void deleteFirestationByAddressTest() {
		try {
			List<FirestationModel> firestations = jsonFileReadRepository.getFile().getFirestations();
			int nbrFirestationOld = firestations.size();

			firestationService.deleteFirestationByAddress("1509 Culver St");
			firestations = jsonFileReadRepository.getFile().getFirestations();
			int nbrFirestationNew = firestations.size();
			assertEquals(nbrFirestationOld - 1, nbrFirestationNew);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void deleteFirestationByAddressBadFirestationTest() {
		try {
			List<FirestationModel> firestations = jsonFileReadRepository.getFile().getFirestations();
			int nbrFirestationOld = firestations.size();

			firestationService.deleteFirestationByAddress("mars");
			firestations = jsonFileReadRepository.getFile().getFirestations();
			int nbrFirestationNew = firestations.size();
			assertEquals(nbrFirestationOld, nbrFirestationNew);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
