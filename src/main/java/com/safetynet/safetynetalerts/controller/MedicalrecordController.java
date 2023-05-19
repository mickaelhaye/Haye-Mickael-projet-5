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
	private JsonFileWriteRepository jsonFileWrite;

	@Autowired
	private MedicalRecordService medicalrecordService;

	private FileEntryRepository file;

	/**
	 * API pour récupérer la liste des medicalrecords
	 * 
	 * @return la liste des medicalrecords
	 */
	@GetMapping(value = "/medicalrecord")
	public List<MedicalrecordModel> afficherListeMedicalrecord() {
		majPointeur();
		logger.info("@GetMapping(value = \"/medicalrecord\")");
		return file.getMedicalrecords();
	}

	/**
	 * API pour rajouter un medicalrecord
	 * 
	 * @param medicalrecord
	 */
	@PostMapping("/medicalRecord")
	public void ajouterMedicalRecord(@RequestBody MedicalrecordModel medicalrecord) {
		majPointeur();
		String sVal = medicalrecordService.addMedicalRecord(medicalrecord);
		jsonFileWrite.writeFile(file);
		logger.info("@PostMapping(\"/medicalRecord\")" + sVal);
	}

	/**
	 * API pour modifier un medicalrecord
	 * 
	 * @param medicalrecord
	 */
	@PatchMapping("/medicalRecord")
	public void mettreAJourMedicalRecord(@RequestBody MedicalrecordModel medicalrecord) {
		majPointeur();
		String sVal = medicalrecordService.updateMedicalRecord(medicalrecord);
		jsonFileWrite.writeFile(file);
		logger.info("@PatchMapping(\"/medicalRecord\")", sVal);
	}

	/**
	 * API pour supprimer un medicalrecord
	 * 
	 * @param firstNameLastName
	 */
	@DeleteMapping("/medicalRecord/{firstNameLastName}")
	public void supprimerMedicalRecord(@PathVariable String firstNameLastName) {
		majPointeur();
		String sVal = medicalrecordService.deleteMedicalRecord(firstNameLastName);
		jsonFileWrite.writeFile(file);
		logger.info("@DeleteMapping(\"/medicalRecord/{firstNameLastName}\")", sVal);

	}

	/**
	 * Récupération des données, Récupération de la liste des medicalrecord
	 */
	void majPointeur() {
		file = controller.getFile();// à valider
		medicalrecordService.setMedicalrecords(file.getMedicalrecords()); // à valider
	}

}
