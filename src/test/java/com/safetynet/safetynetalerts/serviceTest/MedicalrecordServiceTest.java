package com.safetynet.safetynetalerts.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetynetalerts.CustomProperties;
import com.safetynet.safetynetalerts.model.MedicalrecordModel;
import com.safetynet.safetynetalerts.repository.FileEntryRepository;
import com.safetynet.safetynetalerts.repository.JsonFileReadRepository;
import com.safetynet.safetynetalerts.service.MedicalRecordService;

@SpringBootTest
class MedicalrecordServiceTest {

	@Autowired
	private CustomProperties prop;

	@Autowired
	private JsonFileReadRepository JsonFileReadRepository;

	private static MedicalRecordService medicalRecordService = new MedicalRecordService();

	@BeforeEach
	void setUpPerTest() {
		// chargement d'un fichierJson

		FileEntryRepository file;
		try {
			file = JsonFileReadRepository.recupFile(prop.getJsonFileTestPath());
			medicalRecordService.setMedicalrecords(file.getMedicalrecords());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void addMedicalRecordTest() {

		List<MedicalrecordModel> medicalrecords = medicalRecordService.getMedicalrecords();
		if (medicalrecords != null) {
			MedicalrecordModel medicalrecord = new MedicalrecordModel("Jean", "Gabin", "03/06/1984", null, null);
			medicalRecordService.addMedicalRecord(medicalrecord);
			medicalrecord = medicalrecords.get(medicalrecords.size() - 1);
			assertEquals("Gabin", medicalrecord.getLastName());
		}
	}

	@Test
	void addMedicalRecordBadMedicalRecordTest() {

		List<MedicalrecordModel> medicalrecords = medicalRecordService.getMedicalrecords();
		if (medicalrecords != null) {
			int nbrMedicalrecordOld = medicalrecords.size();
			MedicalrecordModel medicalrecord = new MedicalrecordModel("Tessa", "Carman", "02/18/2012", null, null);
			medicalRecordService.addMedicalRecord(medicalrecord);
			int nbrMedicalrecordNew = medicalrecords.size();
			assertEquals(nbrMedicalrecordOld, nbrMedicalrecordNew);
		}
	}

	@Test
	void updateMedicalRecordTest() {

		List<MedicalrecordModel> medicalrecords = medicalRecordService.getMedicalrecords();
		if (medicalrecords != null) {
			MedicalrecordModel medicalrecord = medicalrecords.get(0);
			String oldBirthdate = medicalrecord.getBirthdate();
			ArrayList<String> medications = new ArrayList<String>();
			medications.add("aznol:350mg");
			medications.add("hydrapermazol:100mg");
			ArrayList<String> allergies = new ArrayList<String>();
			allergies.add("nillacilan");
			medicalrecord = new MedicalrecordModel("John", "Boyd", "04/07/1985", medications, allergies);
			medicalRecordService.updateMedicalRecord(medicalrecord);
			medicalrecords = medicalRecordService.getMedicalrecords();
			medicalrecord = medicalrecords.get(0);
			String newBirthdate = medicalrecord.getBirthdate();
			assertNotEquals(oldBirthdate, newBirthdate);
		}
	}

	@Test
	void updateMedicalRecordBadMedicalRecordTest() {

		List<MedicalrecordModel> medicalrecords = medicalRecordService.getMedicalrecords();
		if (medicalrecords != null) {
			MedicalrecordModel medicalrecord = medicalrecords.get(0);
			String oldBirthdate = medicalrecord.getBirthdate();
			ArrayList<String> medications = new ArrayList<String>();
			medications.add("aznol:350mg");
			medications.add("hydrapermazol:100mg");
			ArrayList<String> allergies = new ArrayList<String>();
			allergies.add("nillacilan");
			medicalrecord = new MedicalrecordModel("Gilbert", "Boyd", "04/07/1985", medications, allergies);
			medicalRecordService.updateMedicalRecord(medicalrecord);
			medicalrecords = medicalRecordService.getMedicalrecords();
			medicalrecord = medicalrecords.get(0);
			String newBirthdate = medicalrecord.getBirthdate();
			assertEquals(oldBirthdate, newBirthdate);
		}
	}

	@Test
	void deleteMedicalRecordTest() {
		List<MedicalrecordModel> medicalrecords = medicalRecordService.getMedicalrecords();
		if (medicalrecords != null) {
			int nbrMedicalrecorOld = medicalrecords.size();
			medicalRecordService.deleteMedicalRecord("JohnBoyd");
			medicalrecords = medicalRecordService.getMedicalrecords();
			int nbrMedicalrecordNew = medicalrecords.size();
			assertEquals(nbrMedicalrecorOld - 1, nbrMedicalrecordNew);
		}
	}

	@Test
	void deletePersonBadPersonTest() {
		List<MedicalrecordModel> medicalrecords = medicalRecordService.getMedicalrecords();
		if (medicalrecords != null) {
			int nbrMedicalrecorOld = medicalrecords.size();

			medicalRecordService.deleteMedicalRecord("PatrickBoyd");
			medicalrecords = medicalRecordService.getMedicalrecords();
			int nbrMedicalrecordNew = medicalrecords.size();
			assertEquals(nbrMedicalrecorOld, nbrMedicalrecordNew);
		}
	}

	@Test
	void equalsTest() {
		MedicalRecordService medicalRecordService2 = new MedicalRecordService();
		MedicalRecordService medicalRecordService3 = new MedicalRecordService();
		assertTrue(medicalRecordService2.equals(medicalRecordService3));
	}

	@Test
	void equalsSameObjetTest() {
		assertTrue(medicalRecordService.equals(medicalRecordService));
	}

	@Test
	void equalsNullObjetTest() {
		MedicalRecordService medicalRecordServiceNull = null;
		assertFalse(medicalRecordService.equals(medicalRecordServiceNull));
	}

	@Test
	void equalsDifferentObjetTest() {
		Object objetDifferent = new Object();
		assertFalse(medicalRecordService.equals(objetDifferent));
	}

}
