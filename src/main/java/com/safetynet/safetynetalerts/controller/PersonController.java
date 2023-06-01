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
import com.safetynet.safetynetalerts.service.ChildAlertByAddressService;
import com.safetynet.safetynetalerts.service.FileEntryService;
import com.safetynet.safetynetalerts.service.JsonFileReadService;
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
	private JsonFileReadService jsonFileReadService;

	@Autowired
	private PersonService personService;

	private FileEntryService file;

	/**
	 * API pour récupérer la liste des persons
	 * 
	 * @return la liste des persons
	 */
	@GetMapping(value = "/person")
	public List<PersonModel> afficherListePersonne() {
		file = jsonFileReadService.getFile();
		logger.info("@GetMapping(value = \"/person\")");
		return file.getPersons();
	}

	/**
	 * API pour récupérer des personnes couvertes par une station
	 * 
	 * @param station (station d'entrée)
	 * @return une liste d'Objets (liste de persons + décompte adultes +décompte
	 *         enfants
	 * @throws Exception mauvais retour personService.findByFirestationAListPersons
	 */
	@GetMapping(value = "/firestation/{station}")
	public List<Object> afficherUneListePersonne(@PathVariable String station) throws Exception {
		logger.info("@GetMapping(value = \"/firestation/{station}\")");
		return personService.findByFirestationAListPersons(station);
	}

	/**
	 * API pour récupérer des enfants en fonction d'une adresse
	 * 
	 * @param address (address d'entrée)
	 * @return une liste d'enfants
	 * @throws Exception mauvais retour
	 *                   personService.findByAddressAListChild(address)
	 */
	@GetMapping(value = "/childAlert/{address}")
	public List<ChildAlertByAddressService> afficherUneListeEnfant(@PathVariable String address) throws Exception {
		logger.info("@GetMapping(value = \"/childAlert/{address}\")");
		return personService.findByAddressAListChild(address);
	}

	/**
	 * Pour récupérer des numéros de téléphone desservis par la caserne
	 * 
	 * @param station (station d'entrée)
	 * @return une liste de numéros de téléphone
	 * @throws Exception mauvais retour
	 *                   personService.findByFirestationAPhone(station)
	 */
	@GetMapping(value = "/phoneAlert/{station}")
	public List<String> afficherUneListeNumTelephone(@PathVariable String station) throws Exception {
		logger.info("@GetMapping(value = \"/phoneAlert/{station}\")");
		return personService.findByFirestationAPhone(station);
	}

	/**
	 * Pour récupérer des personnes en fonction d'une adresse
	 * 
	 * @param address (adress d'entrée)
	 * @return une liste d'objets (Liste de persons + numéro de station)
	 * @throws Exception mauvais retour personService.findByAddressAPerson(address)
	 */
	@GetMapping(value = "/fire/{address}")
	public List<Object> afficherUneListePersonneParAddresse(@PathVariable String address) throws Exception {
		logger.info("@GetMapping(value = \"/fire/{address}\")");
		return personService.findByAddressAPerson(address);
	}

	/**
	 * Pour récupérer des personnes en fonction d'une adresse
	 * 
	 * @param station (station d'entrée)
	 * @return une liste d'objets (Liste de foyers)
	 * @throws Exception mauvais retour
	 *                   personService.findByFirestationAFoyer(station)
	 */
	@GetMapping(value = "/flood/stations/{station}")
	public List<Object> afficherUneListeFoyerParFirestation(@PathVariable String station) throws Exception {
		logger.info("@GetMapping(value = \"/flood/stations/{station}\")");
		return personService.findByFirestationAFoyer(station);
	}

	/**
	 * Pour récupérer des personnes en fonction d'un prénom
	 * 
	 * @param firstName (prénom d'entrée)
	 * @return une liste de persons
	 * @throws Exception mauvais retour
	 *                   personService.findByFirstNameAPerson(firstName)
	 */
	@GetMapping(value = "/personInfo/{firstName}")
	public List<String> afficherUneListePersonneParPrenom(@PathVariable String firstName) throws Exception {
		logger.info("@GetMapping(value = \"/personInfo/{firstName}\")");
		return personService.findByFirstNameAPerson(firstName);
	}

	/**
	 * Pour récupérer des adresses mail en fonction d'une ville
	 * 
	 * @param city (city d'entrée)
	 * @return une liste d'emails
	 * @throws Exception mauvais retour personService.findByCityAEmail(city)
	 */
	// Récupération des adresses mail en fonction d'une ville
	@GetMapping(value = "/communityEmail/{city}")
	public List<String> afficherEmailParCity(@PathVariable String city) throws Exception {
		logger.info("@GetMapping(value = \"/communityEmail/{city}\")");
		return personService.findByCityAEmail(city);
	}

	/**
	 * API pour rajouter une person
	 * 
	 * @param person
	 * @throws Exception écriture fichier érroné
	 */
	@PostMapping("/person")
	public String ajouterPerson(@RequestBody PersonModel person) throws Exception {
		String sVal = personService.addPerson(person);
		logger.info("@PostMapping(\"/person\")" + sVal);
		return sVal;

	}

	/**
	 * API pour modifier une person
	 * 
	 * @param person
	 * @throws Exception écriture fichier érroné
	 */
	@PatchMapping("/person")
	public String mettreAJourPerson(@RequestBody PersonModel person) throws Exception {
		String sVal = personService.updatePerson(person);
		logger.info("@PatchMapping(\"/person\")", sVal);
		return sVal;
	}

	/**
	 * API pour supprimer une person
	 * 
	 * @param firstNameLastName
	 * @throws Exception écriture fichier érroné
	 */
	@DeleteMapping("/person/{firstNameLastName}")
	public String supprimerPerson(@PathVariable String firstNameLastName) throws Exception {
		String sVal = personService.deletePerson(firstNameLastName);
		logger.info("@DeleteMapping(\"/person/{firstNameLastName}\")", sVal);
		return sVal;
	}

}
