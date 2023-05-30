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

import com.safetynet.safetynetalerts.CustomProperties;
import com.safetynet.safetynetalerts.model.MedicalrecordModel;
import com.safetynet.safetynetalerts.repository.FileEntryRepository;
import com.safetynet.safetynetalerts.repository.JsonFileWriteRepository;
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
	private Controller controller;

	@Autowired
	private CustomProperties prop;

	@Autowired
	private JsonFileWriteRepository jsonFileWrite;

	@Autowired
	private MedicalRecordService medicalrecordService;

	private FileEntryRepository file;

	/**
	 * API pour récupérer la liste des medicalrecords
	 * 
	 * @return la liste des medicalrecords
	 */
	@GetMapping(value = "/medicalRecord")
	public List<MedicalrecordModel> afficherListeMedicalrecord() {
		majPointeur();
		logger.info("@GetMapping(value = \"/medicalrecord\")");
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
		majPointeur();
		String sVal = medicalrecordService.addMedicalRecord(medicalrecord);
		jsonFileWrite.writeFile(file, prop.getJsonFilePath());
		logger.info("@PostMapping(\"/medicalRecord\")" + sVal);
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
		majPointeur();
		String sVal = medicalrecordService.updateMedicalRecord(medicalrecord);
		jsonFileWrite.writeFile(file, prop.getJsonFilePath());
		logger.info("@PatchMapping(\"/medicalRecord\")", sVal);
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
		majPointeur();
		String sVal = medicalrecordService.deleteMedicalRecord(firstNameLastName);
		jsonFileWrite.writeFile(file, prop.getJsonFilePath());
		logger.info("@DeleteMapping(\"/medicalRecord/{firstNameLastName}\")", sVal);
		return sVal;

	}

	/**
	 * Récupération des données, Récupération de la liste des medicalrecord
	 */
	void majPointeur() {
		file = controller.getFile();// à valider
		medicalrecordService.setMedicalrecords(file.getMedicalrecords()); // à valider
	}

}
