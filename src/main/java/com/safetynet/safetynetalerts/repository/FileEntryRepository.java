package com.safetynet.safetynetalerts.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetynet.safetynetalerts.model.FirestationModel;
import com.safetynet.safetynetalerts.model.MedicalrecordModel;
import com.safetynet.safetynetalerts.model.PersonModel;

import lombok.Data;

/**
 * Classe permettant le stockage des données du fichier d'entrée Data.json
 * 
 * @author mickael
 *
 */
@Data
@Repository
public class FileEntryRepository {

	List<PersonModel> persons;
	List<FirestationModel> firestations;
	List<MedicalrecordModel> medicalrecords;

}