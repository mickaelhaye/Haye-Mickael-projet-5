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
import com.safetynet.safetynetalerts.repository.FileEntryModelRepository;
import com.safetynet.safetynetalerts.repository.JsonFileWriteRepository;

@RestController
public class FirestationController {

	@Autowired
	private Controller controller;

	@Autowired
	private JsonFileWriteRepository jsonFileWrite;

	private FileEntryModelRepository file;

	@GetMapping(value = "/firestation")
	public List<FirestationModel> afficherListeFirestation() {
		file = controller.getFile();// à valider
		return file.getFirestations();
	}

	@PostMapping("/firestation")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String ajouterFirestation(@RequestBody FirestationModel firestation) {
		file = controller.getFile();// à valider
		String sVal = file.addFirestation(firestation);
		jsonFileWrite.writeFile(file);
		return sVal;
	}

	@PatchMapping("/firestation")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String mettreAJourPerson(@RequestBody FirestationModel firestation) {
		file = controller.getFile();// à valider
		String sVal = file.updateFirestation(firestation);
		jsonFileWrite.writeFile(file);
		return sVal;
	}

	@DeleteMapping("/firestation/{stationOrAddress}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String mettreAJourFirestation(@PathVariable String stationOrAddress) {
		file = controller.getFile();// à valider
		String sVal = file.deleteFirestation(stationOrAddress);
		jsonFileWrite.writeFile(file);
		return sVal;
	}

}
