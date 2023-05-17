package com.safetynet.safetynetalerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.safetynetalerts.model.FirestationModel;
import com.safetynet.safetynetalerts.repository.FileEntryRepository;
import com.safetynet.safetynetalerts.repository.JsonFileWriteRepository;
import com.safetynet.safetynetalerts.service.FirestationService;

@RestController
public class FirestationController {

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
		return file.getFirestations();
	}

	@PostMapping("/firestation")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String ajouterFirestation(@RequestBody FirestationModel firestation) {
		majPointeur();
		String sVal = firestationService.addFirestation(firestation);
		jsonFileWrite.writeFile(file);
		return sVal;
	}

	@PatchMapping("/firestation")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String mettreAJourFirestation(@RequestBody FirestationModel firestation) {
		majPointeur();
		String sVal = firestationService.updateFirestation(firestation);
		jsonFileWrite.writeFile(file);
		return sVal;
	}

	@DeleteMapping("/firestation/{stationOrAddress}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String supprimerFirestation(@PathVariable String stationOrAddress) {
		majPointeur();
		String sVal = firestationService.deleteFirestation(stationOrAddress);
		jsonFileWrite.writeFile(file);
		return sVal;
	}

	void majPointeur() {
		file = controller.getFile();// à valider
		firestationService.setFirestations(file.getFirestations()); // à valider
	}

}
