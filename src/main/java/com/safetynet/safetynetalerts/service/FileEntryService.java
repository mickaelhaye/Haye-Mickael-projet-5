package com.safetynet.safetynetalerts.service;

import java.util.List;

import com.safetynet.safetynetalerts.model.FirestationModel;
import com.safetynet.safetynetalerts.model.MedicalrecordModel;
import com.safetynet.safetynetalerts.model.PersonModel;

/**
 * Classe permettant le stockage des données du fichier d'entrée Data.json
 * 
 * @author mickael
 *
 */
public class FileEntryService {

	List<PersonModel> persons;
	List<FirestationModel> firestations;
	List<MedicalrecordModel> medicalrecords;

	public List<PersonModel> getPersons() {
		return persons;
	}

	public List<FirestationModel> getFirestations() {
		return firestations;
	}

	public List<MedicalrecordModel> getMedicalrecords() {
		return medicalrecords;
	}

}