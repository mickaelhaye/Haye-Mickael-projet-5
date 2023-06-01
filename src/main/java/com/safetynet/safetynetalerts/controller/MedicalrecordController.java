package com.safetynet.safetynetalerts.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.safetynetalerts.model.MedicalrecordModel;
import com.safetynet.safetynetalerts.service.FileEntryService;
import com.safetynet.safetynetalerts.service.JsonFileReadService;
import com.safetynet.safetynetalerts.service.MedicalRecordService;

/**
 * Cette classe gère les API au niveau de Medicalrecord
 * 
 * @author Mickael Hayé
 */
@RestController
public class MedicalrecordController {

	private static Logger logger = LoggerFactory.getLogger(FirestationController.class);

	@Autowired
	private JsonFileReadService jsonFileReadService;

	@Autowired
	private MedicalRecordService medicalrecordService;

	private FileEntryService file;

	/**
	 * API pour récupérer la liste des medicalrecords
	 * 
	 * @return la liste des medicalrecords
	 */
	@GetMapping(value = "/medicalRecord")
	public List<MedicalrecordModel> afficherListeMedicalrecord() {
		file = jsonFileReadService.getFile();
		logger.info("Récupération de la liste des medicalrecords");
		return file.getMedicalrecords();
	}

	/**
	 * API pour rajouter un medicalrecord
	 * 
	 * @param medicalrecord
	 * @throws Exception écriture fichier érroné
	 */
	@PostMapping("/medicalRecord")
	public String ajouterMedicalRecord(@RequestBody MedicalrecordModel medicalrecord) throws Exception {
		String sVal = medicalrecordService.addMedicalRecord(medicalrecord);
		logger.info("Rajout d'un medicalrecord " + sVal);
		return sVal;
	}

	/**
	 * API pour modifier un medicalrecord
	 * 
	 * @param medicalrecord
	 * @throws Exception écriture fichier érroné
	 */
	@PatchMapping("/medicalRecord")
	public String mettreAJourMedicalRecord(@RequestBody MedicalrecordModel medicalrecord) throws Exception {
		String sVal = medicalrecordService.updateMedicalRecord(medicalrecord);
		logger.info("Modification d'un mediaclrecord " + sVal);
		return sVal;
	}

	/**
	 * API pour supprimer un medicalrecord
	 * 
	 * @param firstNameLastName
	 * @throws Exception écriture fichier érroné
	 */
	@DeleteMapping("/medicalRecord/{firstNameLastName}")
	public String supprimerMedicalRecord(@PathVariable String firstNameLastName) throws Exception {
		String sVal = medicalrecordService.deleteMedicalRecord(firstNameLastName);
		logger.info("Suppression d'un medicalrecord " + sVal);
		return sVal;

	}
}
