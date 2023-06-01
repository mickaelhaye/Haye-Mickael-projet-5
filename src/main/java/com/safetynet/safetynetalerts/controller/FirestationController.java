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

import com.safetynet.safetynetalerts.model.FirestationModel;
import com.safetynet.safetynetalerts.service.FileEntryService;
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

	private FileEntryService file;

	/**
	 * API pour récupérer la liste des firestations
	 * 
	 * @return la liste des firestations
	 */
	@GetMapping(value = "/firestation")
	public List<FirestationModel> afficherListeFirestation() {
		file = jsonFileReadService.getFile();
		logger.info("@GetMapping(value = \"/firestation\")");
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
		logger.info("@PostMapping(\"/firestation\")" + sVal);
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
		logger.info("@PatchMapping(\"/firestation\")", sVal);
		return sVal;

	}

	/**
	 * API pour supprimer un firestation
	 * 
	 * @param stationOrAddress
	 * @throws Exception écriture fichier érroné
	 */
	@DeleteMapping("/firestation/{stationOrAddress}")
	public String supprimerFirestation(@PathVariable String stationOrAddress) throws Exception {
		String sVal = firestationService.deleteFirestation(stationOrAddress);
		logger.info("@DeleteMapping(\"/firestation/{stationOrAddress}\")", sVal);
		return sVal;

	}

}
