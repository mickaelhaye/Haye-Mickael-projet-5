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

import com.safetynet.safetynetalerts.model.FileEntryModel;
import com.safetynet.safetynetalerts.model.FirestationModel;
import com.safetynet.safetynetalerts.service.FirestationService;
import com.safetynet.safetynetalerts.service.JsonFileReadService;

/**
 * Cette classe gère les API au niveau de Firestation
 * 
 * @author Mickael Hayé
 */
@RestController
public class FirestationController {

	private static Logger logger = LoggerFactory.getLogger(FirestationController.class);

	@Autowired
	private JsonFileReadService jsonFileReadService;

	@Autowired
	private FirestationService firestationService;

	private FileEntryModel file;

	/**
	 * API pour récupérer la liste des firestations
	 * 
	 * @return la liste des firestations
	 */
	@GetMapping(value = "/firestation")
	public List<FirestationModel> afficherListeFirestation() {
		file = jsonFileReadService.getFile();
		logger.info("Récupération de la liste des firestations");
		return file.getFirestations();
	}

	/**
	 * API pour rajouter un firestation
	 * 
	 * @param firestation
	 * @throws Exception écriture fichier érroné
	 */
	@PostMapping("/firestation")
	public String ajouterFirestation(@RequestBody FirestationModel firestation) throws Exception {
		String sVal = firestationService.addFirestation(firestation);
		logger.info("Rajout d'une firestation " + sVal);
		return sVal;
	}

	/**
	 * API pour modifier un firestation
	 * 
	 * @param firestation
	 * @throws Exception écriture fichier érroné
	 */
	@PatchMapping("/firestation")
	public String mettreAJourFirestation(@RequestBody FirestationModel firestation) throws Exception {
		String sVal = firestationService.updateFirestation(firestation);
		logger.info("Modification d'une firestation " + sVal);
		return sVal;

	}

	/**
	 * API pour supprimer un firestation
	 * 
	 * @param station
	 * @throws Exception écriture fichier érroné
	 */
	@DeleteMapping("/firestation/station/{station}")
	public String supprimerFirestationByStation(@PathVariable String station) throws Exception {
		String sVal = firestationService.deleteFirestationByStation(station);
		logger.info("Suppression d'une firestation " + sVal);
		return sVal;

	}

	/**
	 * API pour supprimer un firestation
	 * 
	 * @param Address
	 * @throws Exception écriture fichier érroné
	 */
	@DeleteMapping("/firestation/address/{address}")
	public String supprimerFirestationByAddress(@PathVariable String address) throws Exception {
		String sVal = firestationService.deleteFirestationByAddress(address);
		logger.info("Suppression d'une firestation " + sVal);
		return sVal;

	}

}
