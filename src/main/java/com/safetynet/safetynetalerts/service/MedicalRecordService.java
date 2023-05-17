package com.safetynet.safetynetalerts.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.model.MedicalrecordModel;

import lombok.Data;

@Data
@Service
public class MedicalRecordService {
	List<MedicalrecordModel> medicalrecords;

	public String addMedicalRecord(MedicalrecordModel medicalRecord) {
		medicalrecords.add(medicalRecord);
		return medicalRecord.getFirstName() + " " + medicalRecord.getLastName() + " ajouté";
	}

	public String updateMedicalRecord(MedicalrecordModel medicalRecord) {
		boolean medicalRecordModifiee = false;
		for (MedicalrecordModel medicalRecordTest : medicalrecords) {
			if ((medicalRecordTest.getFirstName().equals(medicalRecord.getFirstName()))
					&& (medicalRecordTest.getLastName().equals(medicalRecord.getLastName()))) {
				medicalRecordTest.setBirthdate(medicalRecord.getBirthdate());
				medicalRecordTest.setMedications(medicalRecord.getMedications());
				medicalRecordTest.setAllergies(medicalRecord.getAllergies());
				medicalRecordModifiee = true;
				break;
			}
		}
		if (!medicalRecordModifiee) {
			return medicalRecord.getFirstName() + " " + medicalRecord.getLastName() + " n'est pas référencé";
		}
		return medicalRecord.getFirstName() + " " + medicalRecord.getLastName() + " modifié";
	}

	public String deleteMedicalRecord(String firstNameLastName) {
		boolean medicalRecordSupprimee = false;
		for (MedicalrecordModel medicalRecordTest : medicalrecords) {
			String firstNameLastNamePersonTest = medicalRecordTest.getFirstName() + medicalRecordTest.getLastName();
			if (firstNameLastNamePersonTest.equals(firstNameLastName)) {
				medicalrecords.remove(medicalRecordTest);
				medicalRecordSupprimee = true;
				break;
			}
		}
		if (!medicalRecordSupprimee) {
			return firstNameLastName + " n'est pas référencé";
		}
		return firstNameLastName + " supprimé";
	}

}
