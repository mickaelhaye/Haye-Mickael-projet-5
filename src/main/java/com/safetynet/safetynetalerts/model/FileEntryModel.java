package com.safetynet.safetynetalerts.model;

import java.util.List;

/**
 * Classe permettant le stockage des données du fichier d'entrée Data.json
 * 
 * @author mickael
 *
 */
public class FileEntryModel {

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