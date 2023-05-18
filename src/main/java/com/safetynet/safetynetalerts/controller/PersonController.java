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

import com.safetynet.safetynetalerts.model.PersonModel;
import com.safetynet.safetynetalerts.repository.FileEntryRepository;
import com.safetynet.safetynetalerts.repository.JsonFileWriteRepository;
import com.safetynet.safetynetalerts.service.ChildAlertByAddressService;
import com.safetynet.safetynetalerts.service.PersonService;

@RestController
public class PersonController {

	private static Logger logger = LoggerFactory.getLogger(FirestationController.class);

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
		logger.info("@GetMapping(value = \"/person\")");
		return file.getPersons();
	}

	// Récupération des personnes couvertes par une station
	@GetMapping(value = "/firestation/{station}")
	public List<Object> afficherUneListePersonne(@PathVariable String station) {
		majPointeur();
		logger.info("@GetMapping(value = \"/firestation/{station}\")");
		return personService.findByFirestationAListPersons(station);
	}

	// Récupération des enfants en fonction d'une adresse
	@GetMapping(value = "/childAlert/{address}")
	public List<ChildAlertByAddressService> afficherUneListeEnfant(@PathVariable String address) {
		majPointeur();
		logger.info("@GetMapping(value = \"/childAlert/{address}\")");
		return personService.findByAddressAListChild(address);
	}

	// Récupération des numéros de téléphone desservis par la caserne
	@GetMapping(value = "/phoneAlert/{station}")
	public List<String> afficherUneListeNumTelephone(@PathVariable String station) {
		majPointeur();
		logger.info("@GetMapping(value = \"/phoneAlert/{station}\")");
		return personService.findByFirestationAPhone(station);
	}

	// Récupération des personnes en fonction d'une adresse
	@GetMapping(value = "/fire/{address}")
	public List<Object> afficherUneListePersonneParAddresse(@PathVariable String address) {
		majPointeur();
		logger.info("@GetMapping(value = \"/fire/{address}\")");
		return personService.findByAddressAPerson(address);
	}

	// Récupération des personnes en fonction d'une adresse
	@GetMapping(value = "/flood/stations/{station}")
	public List<Object> afficherUneListeFoyerParFirestation(@PathVariable String station) {
		majPointeur();
		logger.info("@GetMapping(value = \"/flood/stations/{station}\")");
		return personService.findByFirestationAFoyer(station);
	}

	// Récupération des personnes en fonction d'un prénom
	@GetMapping(value = "/personInfo/{firstName}")
	public List<String> afficherUneListePersonneParPrenom(@PathVariable String firstName) {
		majPointeur();
		logger.info("@GetMapping(value = \"/personInfo/{firstName}\")");
		return personService.findByFirstNameAPerson(firstName);
	}

	// Récupération des adresses mail en fonction d'une ville
	@GetMapping(value = "/communityEmail/{city}")
	public List<String> afficherEmailParCity(@PathVariable String city) {
		majPointeur();
		logger.info("@GetMapping(value = \"/communityEmail/{city}\")");
		return personService.findByCityAEmail(city);
	}

	@PostMapping("/person")
	public void ajouterPerson(@RequestBody PersonModel person) {
		majPointeur();
		String sVal = personService.addPerson(person);
		jsonFileWrite.writeFile(file);
		logger.info("@PostMapping(\"/person\")" + sVal);

	}

	@PatchMapping("/person")
	public void mettreAJourPerson(@RequestBody PersonModel person) {
		majPointeur();
		String sVal = personService.updatePerson(person);
		jsonFileWrite.writeFile(file);
		logger.info("@PatchMapping(\"/person\")", sVal);
	}

	@DeleteMapping("/person/{firstNameLastName}")
	public void supprimerPerson(@PathVariable String firstNameLastName) {
		majPointeur();
		String sVal = personService.deletePerson(firstNameLastName);
		jsonFileWrite.writeFile(file);
		logger.info("@DeleteMapping(\"/person/{firstNameLastName}\")", sVal);
	}

	void majPointeur() {
		file = controller.getFile();// à valider
		personService.setPersons(file.getPersons()); // à valider
		personService.setMedicalrecords(file.getMedicalrecords()); // à valider
		personService.setFirestations(file.getFirestations()); // à valider
	}
}
