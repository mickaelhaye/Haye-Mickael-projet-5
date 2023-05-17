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
import com.safetynet.safetynetalerts.repository.FileEntryModelRepository;
import com.safetynet.safetynetalerts.repository.JsonFileRecupRepository;
import com.safetynet.safetynetalerts.repository.JsonFileWriteRepository;
import com.safetynet.safetynetalerts.service.ChildAlertByAddressService;

import jakarta.annotation.PostConstruct;

@RestController
public class Controller {

	@Autowired
	private JsonFileRecupRepository jsonFileRecup;

	@Autowired
	private JsonFileWriteRepository jsonFileWrite;

	private FileEntryModelRepository file;

	@PostConstruct
	// Recupération du fichier json
	public void init() {

		file = jsonFileRecup.recupFile();
	}

	@GetMapping(value = "/person")
	public List<PersonModel> afficherListePersonne() {
		return file.getPersons();
	}

	// Récupération des personnes couvertes par une station
	@GetMapping(value = "/firestation/{station}")
	public List<Object> afficherUneListePersonne(@PathVariable String station) {
		return file.findByFirestationAListPersons(station);
	}

	// Récupération des enfants en fonction d'une adresse
	@GetMapping(value = "/childAlert/{address}")
	public List<ChildAlertByAddressService> afficherUneListeEnfant(@PathVariable String address) {
		List<ChildAlertByAddressService> listChild = file.findByAddressAListChild(address);
		return listChild;
	}

	// Récupération des numéros de téléphone desservis par la caserne
	@GetMapping(value = "/phoneAlert/{station}")
	public List<String> afficherUneListeNumTelephone(@PathVariable String station) {
		List<String> listPhone = file.findByFirestationAPhone(station);
		return listPhone;
	}

	// Récupération des personnes en fonction d'une adresse
	@GetMapping(value = "/fire/{address}")
	public List<Object> afficherUneListePersonneParAddresse(@PathVariable String address) {
		List<Object> listPerson = file.findByAddressAPerson(address);
		return listPerson;
	}

	// Récupération des personnes en fonction d'une adresse
	@GetMapping(value = "/flood/stations/{station}")
	public List<Object> afficherUneListeFoyerParFirestation(@PathVariable String station) {
		List<Object> listFoyer = file.findByFirestationAFoyer(station);
		return listFoyer;
	}

	// Récupération des personnes en fonction d'un prénom
	@GetMapping(value = "/personInfo/{firstName}")
	public List<String> afficherUneListePersonneParPrenom(@PathVariable String firstName) {
		List<String> listPersonne = file.findByFirstNameAPerson(firstName);
		return listPersonne;
	}

	@PostMapping("/person")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String ajouterPerson(@RequestBody PersonModel person) {
		String sVal = file.addPerson(person);
		jsonFileWrite.writeFile(file);
		return sVal;
	}

	@PatchMapping("/person")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String mettreAJourPerson(@RequestBody PersonModel person) {
		String sVal = file.updatePerson(person);
		jsonFileWrite.writeFile(file);
		return sVal;
	}

	@DeleteMapping("/person/{firstNameLastName}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String mettreAJourPerson(@PathVariable String firstNameLastName) {
		String sVal = file.deletePerson(firstNameLastName);
		jsonFileWrite.writeFile(file);
		return sVal;
	}

}
