package com.safetynet.safetynetalerts.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.safetynetalerts.controller.Controller;
import com.safetynet.safetynetalerts.controller.FirestationController;
import com.safetynet.safetynetalerts.model.MedicalrecordModel;

import lombok.Data;

/**
 * Cette classe permet de traiter les API concernant Medicalrecord
 * 
 * @author Mickael Hayé
 *
 */
@Data
@Service
public class MedicalRecordService {
	@Autowired
	private Controller controller;

	private static Logger logger = LoggerFactory.getLogger(FirestationController.class);

	/**
	 * //@PostMapping("/medicalRecord")
	 * 
	 * @param medicalRecord
	 * @return un String contenant le résultat du rajout d'une medicalrecord
	 */
	public String addMedicalRecord(MedicalrecordModel medicalRecord) {
		logger.debug("addMedicalRecord " + medicalRecord);
		for (MedicalrecordModel medicalRecordTest : controller.getFile().getMedicalrecords()) {
			if ((medicalRecordTest.getFirstName().equals(medicalRecord.getFirstName()))
					&& (medicalRecordTest.getLastName().equals(medicalRecord.getLastName()))) {
				return medicalRecord.getFirstName() + " " + medicalRecord.getLastName() + " deja present";
			}
		}

		controller.getFile().getMedicalrecords().add(medicalRecord);
		return medicalRecord.getFirstName() + " " + medicalRecord.getLastName() + " ajoute";
	}

	/**
	 * //@PatchMapping("/medicalRecord")
	 * 
	 * @param medicalRecord
	 * @return un String contenant le résultat de la modification d'une
	 *         medicalrecord
	 */
	public String updateMedicalRecord(MedicalrecordModel medicalRecord) {
		logger.debug("updateMedicalRecord " + medicalRecord);
		boolean medicalRecordModifiee = false;
		for (MedicalrecordModel medicalRecordTest : controller.getFile().getMedicalrecords()) {
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
	public String deleteMedicalRecord(String firstNameLastName) {
		logger.debug("deleteMedicalRecord " + firstNameLastName);
		boolean medicalRecordSupprimee = false;
		for (MedicalrecordModel medicalRecordTest : controller.getFile().getMedicalrecords()) {
			String firstNameLastNamePersonTest = medicalRecordTest.getFirstName() + medicalRecordTest.getLastName();
			if (firstNameLastNamePersonTest.equals(firstNameLastName)) {
				controller.getFile().getMedicalrecords().remove(medicalRecordTest);
				medicalRecordSupprimee = true;
				break;
			}
		}
		if (!medicalRecordSupprimee) {
			return firstNameLastName + " n'est pas reference";
		}
		return firstNameLastName + " supprime";
	}

}
