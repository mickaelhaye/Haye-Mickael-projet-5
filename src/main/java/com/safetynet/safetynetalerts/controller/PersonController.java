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

import com.safetynet.safetynetalerts.model.PersonModel;
import com.safetynet.safetynetalerts.repository.FileEntryRepository;
import com.safetynet.safetynetalerts.repository.JsonFileWriteRepository;

@RestController
public class PersonController {

	@Autowired
	private Controller controller;

	@Autowired
	private JsonFileWriteRepository jsonFileWrite;

	private FileEntryRepository file;

	@GetMapping(value = "/person")
	public List<PersonModel> afficherListePersonne() {
		file = controller.getFile();// à valider
		return file.getPersons();
	}

	@PostMapping("/person")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String ajouterPerson(@RequestBody PersonModel person) {
		file = controller.getFile();// à valider
		String sVal = file.addPerson(person);
		jsonFileWrite.writeFile(file);
		return sVal;
	}

	@PatchMapping("/person")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String mettreAJourPerson(@RequestBody PersonModel person) {
		file = controller.getFile();// à valider
		String sVal = file.updatePerson(person);
		jsonFileWrite.writeFile(file);
		return sVal;
	}

	@DeleteMapping("/person/{firstNameLastName}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String mettreAJourPerson(@PathVariable String firstNameLastName) {
		file = controller.getFile();// à valider
		String sVal = file.deletePerson(firstNameLastName);
		jsonFileWrite.writeFile(file);
		return sVal;
	}

}
