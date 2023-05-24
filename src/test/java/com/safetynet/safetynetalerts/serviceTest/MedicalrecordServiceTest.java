package com.safetynet.safetynetalerts.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.safetynet.safetynetalerts.CustomProperties;
import com.safetynet.safetynetalerts.model.MedicalrecordModel;
import com.safetynet.safetynetalerts.repository.FileEntryRepository;
import com.safetynet.safetynetalerts.repository.JsonFileReadRepository;
import com.safetynet.safetynetalerts.service.MedicalRecordService;

@SpringBootTest
class MedicalrecordServiceTest {

	@MockBean // Mock
	private static CustomProperties prop;

	private static MedicalRecordService medicalRecordService = new MedicalRecordService();

	@BeforeEach
	void setUpPerTest() {
		// chargement d'un fichierJson
		
		when(prop.getJsonFilePath()).thenReturn("src/main/resources/datatest/dataTest.json");
		JsonFileReadRepository JsonFileReadRepository = new JsonFileReadRepository(); // autowired
		FileEntryRepository file = null;
		file = JsonFileReadRepository.recupFile();
		medicalRecordService.setMedicalrecords(file.getMedicalrecords()); // à valider
	}

	@Test
	void addMedicalRecord() {

		MedicalrecordModel medicalrecord = new MedicalrecordModel("Jean", "Gabin", "03/06/1984", null, null);
		medicalRecordService.addMedicalRecord(medicalrecord);
		List<MedicalrecordModel> medicalrecords = medicalRecordService.getMedicalrecords();
		medicalrecord = medicalrecords.get(medicalrecords.size() - 1);
		assertEquals("Gabin", medicalrecord.getLastName());
	}

	@Test
	void updateMedicalRecord() {

		List<MedicalrecordModel> medicalrecords = medicalRecordService.getMedicalrecords();
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

	@Test
	void updateMedicalRecordBadMedicalRecord() {

		List<MedicalrecordModel> medicalrecords = medicalRecordService.getMedicalrecords();
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

	@Test
	void deleteMedicalRecord() {
		List<MedicalrecordModel> medicalrecords = medicalRecordService.getMedicalrecords();
		int nbrMedicalrecorOld = medicalrecords.size();

		medicalRecordService.deleteMedicalRecord("JohnBoyd");
		medicalrecords = medicalRecordService.getMedicalrecords();
		int nbrMedicalrecordNew = medicalrecords.size();
		assertEquals(nbrMedicalrecorOld - 1, nbrMedicalrecordNew);

	}

	@Test
	void deletePersonBadPerson() {
		List<MedicalrecordModel> medicalrecords = medicalRecordService.getMedicalrecords();
		int nbrMedicalrecorOld = medicalrecords.size();

		medicalRecordService.deleteMedicalRecord("PatrickBoyd");
		medicalrecords = medicalRecordService.getMedicalrecords();
		int nbrMedicalrecordNew = medicalrecords.size();
		assertEquals(nbrMedicalrecorOld, nbrMedicalrecordNew);

	}

}
