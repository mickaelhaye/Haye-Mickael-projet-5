package com.safetynet.safetynetalerts.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetynetalerts.model.MedicalrecordModel;
import com.safetynet.safetynetalerts.service.impl.JsonFileReadServiceImpl;
import com.safetynet.safetynetalerts.service.impl.MedicalRecordServiceImpl;

@SpringBootTest
class MedicalrecordServiceTest {

	@Autowired
	private JsonFileReadServiceImpl jsonFileReadRepository;

	@Autowired
	private MedicalRecordServiceImpl medicalRecordService;

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
	void addMedicalRecordTest() {

		try {
			List<MedicalrecordModel> medicalrecords = jsonFileReadRepository.getFile().getMedicalrecords();
			MedicalrecordModel medicalrecord = new MedicalrecordModel("Jean", "Gabin", "03/06/1984", null, null);
			medicalRecordService.addMedicalRecord(medicalrecord);
			medicalrecord = medicalrecords.get(medicalrecords.size() - 1);
			assertEquals("Gabin", medicalrecord.getLastName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void updateMedicalRecordTest() {

		try {
			List<MedicalrecordModel> medicalrecords = jsonFileReadRepository.getFile().getMedicalrecords();
			MedicalrecordModel medicalrecord = medicalrecords.get(0);
			String oldBirthdate = medicalrecord.getBirthdate();
			ArrayList<String> medications = new ArrayList<String>();
			medications.add("aznol:350mg");
			medications.add("hydrapermazol:100mg");
			ArrayList<String> allergies = new ArrayList<String>();
			allergies.add("nillacilan");
			medicalrecord = new MedicalrecordModel("John", "Boyd", "04/07/1985", null, null);
			medicalRecordService.updateMedicalRecord(medicalrecord);
			medicalrecords = jsonFileReadRepository.getFile().getMedicalrecords();
			medicalrecord = medicalrecords.get(0);
			String newBirthdate = medicalrecord.getBirthdate();
			assertNotEquals(oldBirthdate, newBirthdate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void updateMedicalRecordBadMedicalRecordTest() {

		try {
			List<MedicalrecordModel> medicalrecords = jsonFileReadRepository.getFile().getMedicalrecords();
			MedicalrecordModel medicalrecord = medicalrecords.get(0);
			String oldBirthdate = medicalrecord.getBirthdate();
			ArrayList<String> medications = new ArrayList<String>();
			medications.add("aznol:350mg");
			medications.add("hydrapermazol:100mg");
			ArrayList<String> allergies = new ArrayList<String>();
			allergies.add("nillacilan");
			medicalrecord = new MedicalrecordModel("Gilbert", "Boyd", "04/07/1985", null, null);
			medicalRecordService.updateMedicalRecord(medicalrecord);
			medicalrecords = jsonFileReadRepository.getFile().getMedicalrecords();
			medicalrecord = medicalrecords.get(0);
			String newBirthdate = medicalrecord.getBirthdate();
			assertEquals(oldBirthdate, newBirthdate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void deleteMedicalRecordTest() {
		try {
			List<MedicalrecordModel> medicalrecords = jsonFileReadRepository.getFile().getMedicalrecords();
			int nbrMedicalrecorOld = medicalrecords.size();
			medicalRecordService.deleteMedicalRecord("John", "Boyd");
			medicalrecords = jsonFileReadRepository.getFile().getMedicalrecords();
			int nbrMedicalrecordNew = medicalrecords.size();
			assertEquals(nbrMedicalrecorOld - 1, nbrMedicalrecordNew);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void deletePersonBadPersonTest() {
		try {
			List<MedicalrecordModel> medicalrecords = jsonFileReadRepository.getFile().getMedicalrecords();
			int nbrMedicalrecorOld = medicalrecords.size();

			medicalRecordService.deleteMedicalRecord("Patrick", "Boyd");
			medicalrecords = jsonFileReadRepository.getFile().getMedicalrecords();
			int nbrMedicalrecordNew = medicalrecords.size();
			assertEquals(nbrMedicalrecorOld, nbrMedicalrecordNew);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
