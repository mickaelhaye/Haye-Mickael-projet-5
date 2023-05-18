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
import com.safetynet.safetynetalerts.repository.FileEntryRepository;
import com.safetynet.safetynetalerts.repository.JsonFileWriteRepository;
import com.safetynet.safetynetalerts.service.FirestationService;

@RestController
public class FirestationController {

	private static Logger logger = LoggerFactory.getLogger(FirestationController.class);

	@Autowired
	private Controller controller;

	@Autowired
	private JsonFileWriteRepository jsonFileWrite;

	@Autowired
	private FirestationService firestationService;

	private FileEntryRepository file;

	@GetMapping(value = "/firestation")
	public List<FirestationModel> afficherListeFirestation() {
		majPointeur();
		logger.info("@GetMapping(value = \"/firestation\")");
		return file.getFirestations();
	}

	@PostMapping("/firestation")
	public void ajouterFirestation(@RequestBody FirestationModel firestation) {
		majPointeur();
		String sVal = firestationService.addFirestation(firestation);
		jsonFileWrite.writeFile(file);
		logger.info("@PostMapping(\"/firestation\")" + sVal);
	}

	@PatchMapping("/firestation")
	public void mettreAJourFirestation(@RequestBody FirestationModel firestation) {

		majPointeur();
		String sVal = firestationService.updateFirestation(firestation);
		jsonFileWrite.writeFile(file);
		logger.info("@PatchMapping(\"/firestation\")", sVal);

	}

	@DeleteMapping("/firestation/{stationOrAddress}")
	public void supprimerFirestation(@PathVariable String stationOrAddress) {
		majPointeur();
		String sVal = firestationService.deleteFirestation(stationOrAddress);
		jsonFileWrite.writeFile(file);
		logger.info("@DeleteMapping(\"/firestation/{stationOrAddress}\")", sVal);

	}

	void majPointeur() {
		file = controller.getFile();// à valider
		firestationService.setFirestations(file.getFirestations()); // à valider
	}

}
