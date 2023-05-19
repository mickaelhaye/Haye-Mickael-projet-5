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

/**
 * Cette classe gère les API au niveau de Person
 * 
 * @author Mickael Hayé
 */
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

	/**
	 * API pour récupérer la liste des persons
	 * 
	 * @return la liste des persons
	 */
	@GetMapping(value = "/person")
	public List<PersonModel> afficherListePersonne() {
		majPointeur();
		logger.info("@GetMapping(value = \"/person\")");
		return file.getPersons();
	}

	/**
	 * API pour récupérer des personnes couvertes par une station
	 * 
	 * @param station (station d'entrée)
	 * @return une liste d'Objets (liste de persons + décompte adultes +décompte
	 *         enfants
	 */
	@GetMapping(value = "/firestation/{station}")
	public List<Object> afficherUneListePersonne(@PathVariable String station) {
		majPointeur();
		logger.info("@GetMapping(value = \"/firestation/{station}\")");
		return personService.findByFirestationAListPersons(station);
	}

	/**
	 * API pour récupérer des enfants en fonction d'une adresse
	 * 
	 * @param address (address d'entrée)
	 * @return une liste d'enfants
	 */
	@GetMapping(value = "/childAlert/{address}")
	public List<ChildAlertByAddressService> afficherUneListeEnfant(@PathVariable String address) {
		majPointeur();
		logger.info("@GetMapping(value = \"/childAlert/{address}\")");
		return personService.findByAddressAListChild(address);
	}

	/**
	 * Pour récupérer des numéros de téléphone desservis par la caserne
	 * 
	 * @param station (station d'entrée)
	 * @return une liste de numéros de téléphone
	 */
	@GetMapping(value = "/phoneAlert/{station}")
	public List<String> afficherUneListeNumTelephone(@PathVariable String station) {
		majPointeur();
		logger.info("@GetMapping(value = \"/phoneAlert/{station}\")");
		return personService.findByFirestationAPhone(station);
	}

	/**
	 * Pour récupérer des personnes en fonction d'une adresse
	 * 
	 * @param address (adress d'entrée)
	 * @return une liste d'objets (Liste de persons + numéro de station)
	 */
	@GetMapping(value = "/fire/{address}")
	public List<Object> afficherUneListePersonneParAddresse(@PathVariable String address) {
		majPointeur();
		logger.info("@GetMapping(value = \"/fire/{address}\")");
		return personService.findByAddressAPerson(address);
	}

	/**
	 * Pour récupérer des personnes en fonction d'une adresse
	 * 
	 * @param station (station d'entrée)
	 * @return une liste d'objets (Liste de foyers)
	 */
	@GetMapping(value = "/flood/stations/{station}")
	public List<Object> afficherUneListeFoyerParFirestation(@PathVariable String station) {
		majPointeur();
		logger.info("@GetMapping(value = \"/flood/stations/{station}\")");
		return personService.findByFirestationAFoyer(station);
	}

	/**
	 * Pour récupérer des personnes en fonction d'un prénom
	 * 
	 * @param firstName (prénom d'entrée)
	 * @return une liste de persons
	 */
	@GetMapping(value = "/personInfo/{firstName}")
	public List<String> afficherUneListePersonneParPrenom(@PathVariable String firstName) {
		majPointeur();
		logger.info("@GetMapping(value = \"/personInfo/{firstName}\")");
		return personService.findByFirstNameAPerson(firstName);
	}

	/**
	 * Pour récupérer des adresses mail en fonction d'une ville
	 * 
	 * @param city (city d'entrée)
	 * @return une liste d'emails
	 */
	// Récupération des adresses mail en fonction d'une ville
	@GetMapping(value = "/communityEmail/{city}")
	public List<String> afficherEmailParCity(@PathVariable String city) {
		majPointeur();
		logger.info("@GetMapping(value = \"/communityEmail/{city}\")");
		return personService.findByCityAEmail(city);
	}

	/**
	 * API pour rajouter une person
	 * 
	 * @param person
	 */
	@PostMapping("/person")
	public void ajouterPerson(@RequestBody PersonModel person) {
		majPointeur();
		String sVal = personService.addPerson(person);
		jsonFileWrite.writeFile(file);
		logger.info("@PostMapping(\"/person\")" + sVal);

	}

	/**
	 * API pour modifier une person
	 * 
	 * @param person
	 */
	@PatchMapping("/person")
	public void mettreAJourPerson(@RequestBody PersonModel person) {
		majPointeur();
		String sVal = personService.updatePerson(person);
		jsonFileWrite.writeFile(file);
		logger.info("@PatchMapping(\"/person\")", sVal);
	}

	/**
	 * API pour supprimer une person
	 * 
	 * @param firstNameLastName
	 */
	@DeleteMapping("/person/{firstNameLastName}")
	public void supprimerPerson(@PathVariable String firstNameLastName) {
		majPointeur();
		String sVal = personService.deletePerson(firstNameLastName);
		jsonFileWrite.writeFile(file);
		logger.info("@DeleteMapping(\"/person/{firstNameLastName}\")", sVal);
	}

	/**
	 * récupération des données, récupération des 3 listes
	 */
	void majPointeur() {
		file = controller.getFile();// à valider
		personService.setPersons(file.getPersons()); // à valider
		personService.setMedicalrecords(file.getMedicalrecords()); // à valider
		personService.setFirestations(file.getFirestations()); // à valider
	}
}
