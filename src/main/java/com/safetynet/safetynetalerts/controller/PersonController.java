package com.safetynet.safetynetalerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.safetynetalerts.model.PersonModel;
import com.safetynet.safetynetalerts.repository.FileEntryRepository;
import com.safetynet.safetynetalerts.repository.JsonFileWriteRepository;
import com.safetynet.safetynetalerts.service.ChildAlertByAddressService;
import com.safetynet.safetynetalerts.service.PersonService;

@RestController
public class PersonController {

	@Autowired
	private Controller controller;

	@Autowired
	private JsonFileWriteRepository jsonFileWrite;

	@Autowired
	private PersonService personService;

	private FileEntryRepository file;

	@GetMapping(value = "/person")
	public List<PersonModel> afficherListePersonne() {
		majPointeur();
		return file.getPersons();
	}

	// Récupération des personnes couvertes par une station
	@GetMapping(value = "/firestation/{station}")
	public List<Object> afficherUneListePersonne(@PathVariable String station) {
		majPointeur();
		return personService.findByFirestationAListPersons(station);
	}

	// Récupération des enfants en fonction d'une adresse
	@GetMapping(value = "/childAlert/{address}")
	public List<ChildAlertByAddressService> afficherUneListeEnfant(@PathVariable String address) {
		majPointeur();
		List<ChildAlertByAddressService> listChild = personService.findByAddressAListChild(address);
		return listChild;
	}

	// Récupération des numéros de téléphone desservis par la caserne
	@GetMapping(value = "/phoneAlert/{station}")
	public List<String> afficherUneListeNumTelephone(@PathVariable String station) {
		majPointeur();
		List<String> listPhone = personService.findByFirestationAPhone(station);
		return listPhone;
	}

	// Récupération des personnes en fonction d'une adresse
	@GetMapping(value = "/fire/{address}")
	public List<Object> afficherUneListePersonneParAddresse(@PathVariable String address) {
		majPointeur();
		List<Object> listPerson = personService.findByAddressAPerson(address);
		return listPerson;
	}

	// Récupération des personnes en fonction d'une adresse
	@GetMapping(value = "/flood/stations/{station}")
	public List<Object> afficherUneListeFoyerParFirestation(@PathVariable String station) {
		majPointeur();
		List<Object> listFoyer = personService.findByFirestationAFoyer(station);
		return listFoyer;
	}

	// Récupération des personnes en fonction d'un prénom
	@GetMapping(value = "/personInfo/{firstName}")
	public List<String> afficherUneListePersonneParPrenom(@PathVariable String firstName) {
		majPointeur();
		List<String> listPersonne = personService.findByFirstNameAPerson(firstName);
		return listPersonne;
	}

	// Récupération des adresses mail en fonction d'une ville
	@GetMapping(value = "/communityEmail/{city}")
	public List<String> afficherEmailParCity(@PathVariable String city) {
		majPointeur();
		List<String> listEmail = personService.findByCityAEmail(city);
		return listEmail;
	}

	@PostMapping("/person")
	public String ajouterPerson(@RequestBody PersonModel person) {
		majPointeur();
		String sVal = personService.addPerson(person);
		jsonFileWrite.writeFile(file);
		return sVal;
	}

	@PatchMapping("/person")
	public String mettreAJourPerson(@RequestBody PersonModel person) {
		majPointeur();
		String sVal = personService.updatePerson(person);
		jsonFileWrite.writeFile(file);
		return sVal;
	}

	@DeleteMapping("/person/{firstNameLastName}")
	public String supprimerPerson(@PathVariable String firstNameLastName) {
		majPointeur();
		String sVal = personService.deletePerson(firstNameLastName);
		jsonFileWrite.writeFile(file);
		return sVal;
	}

	void majPointeur() {
		file = controller.getFile();// à valider
		personService.setPersons(file.getPersons()); // à valider
		personService.setMedicalrecords(file.getMedicalrecords()); // à valider
		personService.setFirestations(file.getFirestations()); // à valider
	}
}
