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
import com.safetynet.safetynetalerts.model.FirestationModel;
import com.safetynet.safetynetalerts.repository.FileEntryRepository;
import com.safetynet.safetynetalerts.repository.JsonFileWriteRepository;
import com.safetynet.safetynetalerts.service.FirestationService;

/**
 * Cette classe gère les API au niveau de Firestation
 * 
 * @author Mickael Hayé
 */
@RestController
public class FirestationController {

	private static Logger logger = LoggerFactory.getLogger(FirestationController.class);

	@Autowired
	private Controller controller;

	@Autowired
	private CustomProperties prop;

	@Autowired
	private JsonFileWriteRepository jsonFileWrite;

	@Autowired
	private FirestationService firestationService;

	private FileEntryRepository file;

	/**
	 * API pour récupérer la liste des firestations
	 * 
	 * @return la liste des firestations
	 */
	@GetMapping(value = "/firestation")
	public List<FirestationModel> afficherListeFirestation() {
		majPointeur();
		logger.info("@GetMapping(value = \"/firestation\")");
		return file.getFirestations();
	}

	/**
	 * API pour rajouter un firestation
	 * 
	 * @param firestation
	 * @throws Exception
	 */
	@PostMapping("/firestation")
	public void ajouterFirestation(@RequestBody FirestationModel firestation) throws Exception {
		majPointeur();
		String sVal = firestationService.addFirestation(firestation);
		jsonFileWrite.writeFile(file, prop.getJsonFilePath());
		logger.info("@PostMapping(\"/firestation\")" + sVal);
	}

	/**
	 * API pour modifier un firestation
	 * 
	 * @param firestation
	 * @throws Exception
	 */
	@PatchMapping("/firestation")
	public void mettreAJourFirestation(@RequestBody FirestationModel firestation) throws Exception {

		majPointeur();
		String sVal = firestationService.updateFirestation(firestation);
		jsonFileWrite.writeFile(file, prop.getJsonFilePath());
		logger.info("@PatchMapping(\"/firestation\")", sVal);

	}

	/**
	 * API pour supprimer un firestation
	 * 
	 * @param stationOrAddress
	 * @throws Exception
	 */
	@DeleteMapping("/firestation/{stationOrAddress}")
	public void supprimerFirestation(@PathVariable String stationOrAddress) throws Exception {
		majPointeur();
		String sVal = firestationService.deleteFirestation(stationOrAddress);
		jsonFileWrite.writeFile(file, prop.getJsonFilePath());
		logger.info("@DeleteMapping(\"/firestation/{stationOrAddress}\")", sVal);

	}

	/**
	 * récupération des données, récupération de la liste des firestations
	 */
	void majPointeur() {
		file = controller.getFile();// à valider
		firestationService.setFirestations(file.getFirestations()); // à valider
	}

}
