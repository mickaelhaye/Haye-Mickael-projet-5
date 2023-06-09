package com.safetynet.safetynetalerts.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.controller.FirestationController;
import com.safetynet.safetynetalerts.model.MedicalrecordModel;
import com.safetynet.safetynetalerts.service.impl.JsonFileReadServiceImpl;
import com.safetynet.safetynetalerts.service.impl.MedicalRecordServiceImpl;

/**
 * Cette classe permet de traiter les API concernant Medicalrecord
 * 
 * @author Mickael Hayé
 *
 */
@Service
public class MedicalRecordService implements MedicalRecordServiceImpl {
	@Autowired
	private JsonFileReadServiceImpl jsonFileReadRepository;

	private static Logger logger = LoggerFactory.getLogger(FirestationController.class);

	/**
	 * //@PostMapping("/medicalRecord")
	 * 
	 * @param medicalRecord
	 * @return un String contenant le résultat du rajout d'une medicalrecord
	 */
	@Override
	public String addMedicalRecord(MedicalrecordModel medicalRecord) {
		logger.debug("addMedicalRecord " + medicalRecord);
		jsonFileReadRepository.getFile().getMedicalrecords().add(medicalRecord);
		return medicalRecord.getFirstName() + " " + medicalRecord.getLastName() + " ajoute";
	}

	/**
	 * //@PatchMapping("/medicalRecord")
	 * 
	 * @param medicalRecord
	 * @return un String contenant le résultat de la modification d'une
	 *         medicalrecord
	 */
	@Override
	public String updateMedicalRecord(MedicalrecordModel medicalRecord) {
		logger.debug("updateMedicalRecord " + medicalRecord);
		boolean medicalRecordModifiee = false;
		for (MedicalrecordModel medicalRecordTest : jsonFileReadRepository.getFile().getMedicalrecords()) {
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
			return medicalRecord.getFirstName() + " " + medicalRecord.getLastName() + " n'est pas reference";
		}
		return medicalRecord.getFirstName() + " " + medicalRecord.getLastName() + " modifie";
	}

	/**
	 * //@DeleteMapping("/firstNameLastName")
	 * 
	 * @param firstNameLastName
	 * @return un String contenant le résultat de la suppression d'une medicalrecord
	 */
	@Override
	public String deleteMedicalRecord(String firstName, String lastName) {
		logger.debug("deleteMedicalRecord " + firstName + lastName);
		boolean medicalRecordSupprimee = false;
		for (MedicalrecordModel medicalRecordTest : jsonFileReadRepository.getFile().getMedicalrecords()) {
			String firstNameLastNamePersonTest = medicalRecordTest.getFirstName() + medicalRecordTest.getLastName();
			if (firstNameLastNamePersonTest.equals(firstName + lastName)) {
				jsonFileReadRepository.getFile().getMedicalrecords().remove(medicalRecordTest);
				medicalRecordSupprimee = true;
				break;
			}
		}
		if (!medicalRecordSupprimee) {
			return firstName + " " + lastName + " n'est pas reference";
		}
		return firstName + " " + lastName + " supprime";
	}

}
