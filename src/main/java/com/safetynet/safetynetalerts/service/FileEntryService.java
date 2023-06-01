package com.safetynet.safetynetalerts.service;

import java.util.List;

import org.springframework.stereotype.Service;

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
@Service
public class FileEntryService {

	List<PersonModel> persons;
	List<FirestationModel> firestations;
	List<MedicalrecordModel> medicalrecords;

}